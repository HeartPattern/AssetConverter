package io.heartpattern.assetconverter

import kotlinx.serialization.Serializable

@Serializable
data class Asset(
    val hash: String,
    val size: Long
)