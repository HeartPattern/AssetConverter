package io.heartpattern.assetconverter

import java.io.InputStream
import java.io.OutputStream
import java.security.DigestInputStream
import java.security.MessageDigest

private val HEX_CHARS = "0123456789abcdef".toCharArray()

fun ByteArray.toHex(): String {
    val result = StringBuffer()

    forEach {
        val octet = it.toInt()
        val firstIndex = (octet and 0xF0).ushr(4)
        val secondIndex = octet and 0x0F
        result.append(HEX_CHARS[firstIndex])
        result.append(HEX_CHARS[secondIndex])
    }

    return result.toString()
}

fun InputStream.transferAndCheckSHA1(output: OutputStream, hash: String, size: Long): HashResult {
    val digest = MessageDigest.getInstance("SHA1")
    val input = DigestInputStream(this, digest)
    val length = input.copyTo(output)

    val sha1 = digest.digest().toHex()

    if (length != size)
        return HashResult.SizeFail(length)

    if (sha1 != hash)
        return HashResult.HashFail(sha1)

    return HashResult.Success
}

sealed class HashResult {
    object Success : HashResult()
    data class HashFail(val hash: String) : HashResult()
    data class SizeFail(val size: Long) : HashResult()
}