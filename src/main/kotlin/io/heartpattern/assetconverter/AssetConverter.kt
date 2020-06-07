package io.heartpattern.assetconverter

import io.heartpattern.assetconverter.vfs.*
import java.io.File
import java.util.zip.ZipOutputStream

fun convertAsset(
    assets: Map<String, Asset>,
    source: ReadOnlyVirtualFileSystem,
    destination: WriteOnlyVirtualFileSystem,
    logger: Logger = ConsoleLogger()
) {
    for ((name, asset) in assets) {
        val (hash, size) = asset
        logger.debug("$name($hash, $size): Processing asset")

        val input = source.openInputStream("${hash.substring(0..1)}/$hash")
        if (input == null) {
            logger.warn("$name($hash, $size): Not found")
            continue
        }
        val output = destination.openOutputStream(name)

        val result = input.transferAndCheckSHA1(output, hash, size)
        input.close()
        output.close()

        if (result is HashResult.SizeFail) {
            logger.warn("$name($hash, $size): Size MisMatch(${result.size})")
        }
        if (result is HashResult.HashFail) {
            logger.warn("$name($hash, $size): Hash Mismatch(${result.hash})")
        }
    }
    logger.info("Done!")
}

fun convertAssetFromMinecraftInstallation(
    version: String,
    path: File,
    destination: File,
    logger: Logger = ConsoleLogger()
) {
    val baseDir = File(path, "assets")
    val indexDir = File(baseDir, "indexes")
    val objectDir = File(baseDir, "objects")

    val assets = loadIndexFromFile(File(indexDir, "$version.json"))
    val input = NativeReadOnlyVirtualFileSystem(objectDir)
    val output = if (destination.isFile) {
        destination.createNewFile()
        val stream = ZipOutputStream(destination.outputStream())
        ZipWriteOnlyVirtualFileSystem(stream)
    } else {
        NativeReadWriteVirtualFileSystem(destination)
    }

    convertAsset(assets, input, output, logger)

    if (output is ZipWriteOnlyVirtualFileSystem)
        output.stream.close()
}