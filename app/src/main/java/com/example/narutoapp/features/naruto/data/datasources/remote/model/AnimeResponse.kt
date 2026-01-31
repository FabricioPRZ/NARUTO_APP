package com.example.narutoapp.features.naruto.data.datasources.remote.model

data class AnimeResponse(
    val data: AnimeDto
)

data class AnimeDto(
    val mal_id: Int,
    val title: String,
    val title_english: String?,
    val title_japanese: String?,
    val type: String?,
    val episodes: Int?,
    val status: String?,
    val aired: AiredDto?,
    val duration: String?,
    val score: Double?,
    val synopsis: String?,
    val images: ImagesDto?
)

data class AiredDto(
    val from: String?,
    val to: String?
)

data class ImagesDto(
    val jpg: ImageUrlDto?
)

data class ImageUrlDto(
    val large_image_url: String?
)