package io.heartpattern.assetconverter

import java.io.File

fun main(args: Array<String>) {
    val version = args[0]
    val path = args[1]
    val destination = args[2]

    convertAssetFromMinecraftInstallation(
        version,
        File(path),
        if (destination.endsWith(".zip")) {
            val file = File(destination)
            file.parentFile?.mkdirs()
            file.createNewFile()
            file
        } else {
            val file = File(destination)
            file.mkdirs()
            file
        }
    )
}