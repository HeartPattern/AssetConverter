package io.heartpattern.assetconverter

import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.io.File
import java.net.URL

fun loadIndexFromJson(text: String): Map<String, Asset> {
    val json = Json(JsonConfiguration.Stable)
    val raw = json.parseJson(text)
    return json.fromJson(
        MapSerializer(String.serializer(), Asset.serializer()),
        raw.jsonObject["objects"]!!
    )
}

fun loadIndexFromFile(file: File): Map<String, Asset> = loadIndexFromJson(file.readText())

fun loadIndexFromUrl(url: URL): Map<String, Asset> = loadIndexFromJson(url.readText())