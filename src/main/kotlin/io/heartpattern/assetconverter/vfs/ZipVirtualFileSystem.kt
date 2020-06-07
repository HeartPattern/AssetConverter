package io.heartpattern.assetconverter.vfs

import java.io.InputStream
import java.io.OutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipOutputStream

open class ZipReadOnlyVirtualFileSystem(
    val stream: ZipFile
) : ReadOnlyVirtualFileSystem {
    override fun openInputStream(entry: String): InputStream? {
        val zipEntry = stream.getEntry(entry) ?: return null
        return stream.getInputStream(zipEntry)
    }
}

class ZipWriteOnlyVirtualFileSystem(
    val stream: ZipOutputStream
) : WriteOnlyVirtualFileSystem {
    override fun openOutputStream(entry: String): OutputStream {
        stream.putNextEntry(ZipEntry(entry))
        return ZipEntryOutputStream(stream)
    }
}

private class ZipEntryOutputStream(
    val delegate: ZipOutputStream
) : OutputStream() {
    override fun write(b: Int) = delegate.write(b)

    override fun write(b: ByteArray) = delegate.write(b)

    override fun write(b: ByteArray, off: Int, len: Int) = delegate.write(b, off, len)

    override fun flush() = delegate.flush()

    override fun close() = delegate.closeEntry()
}