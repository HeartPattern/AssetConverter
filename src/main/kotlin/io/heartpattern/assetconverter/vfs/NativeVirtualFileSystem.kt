package io.heartpattern.assetconverter.vfs

import java.io.File
import java.io.InputStream
import java.io.OutputStream

open class NativeReadOnlyVirtualFileSystem(
    val root: File
) : ReadOnlyVirtualFileSystem {
    override fun openInputStream(entry: String): InputStream? = file(entry)?.inputStream()

    fun file(entry: String): File? {
        val file = File(root, entry)
        return if (file.exists())
            file
        else
            null
    }
}

class NativeReadWriteVirtualFileSystem(
    root: File
) : NativeReadOnlyVirtualFileSystem(root), WriteOnlyVirtualFileSystem {
    override fun openOutputStream(entry: String): OutputStream{
        val file = File(root, entry)
        file.parentFile.mkdirs()
        file.createNewFile()
        return file.outputStream()
    }
}