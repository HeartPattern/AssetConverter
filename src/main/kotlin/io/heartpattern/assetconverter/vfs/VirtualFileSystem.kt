package io.heartpattern.assetconverter.vfs

import java.io.InputStream
import java.io.OutputStream

interface ReadOnlyVirtualFileSystem {
    fun openInputStream(entry: String): InputStream?
}

interface WriteOnlyVirtualFileSystem {
    fun openOutputStream(entry: String): OutputStream
}