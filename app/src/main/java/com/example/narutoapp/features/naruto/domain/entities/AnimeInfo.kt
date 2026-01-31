package com.example.narutoapp.features.naruto.domain.entities

data class AnimeInfo(
    val malId: Int,
    val title: String,
    val titleEnglish: String?,
    val titleJapanese: String?,
    val type: String,
    val episodes: Int,
    val status: String,
    val airedFrom: String?,
    val airedTo: String?,
    val duration: String?,
    val score: Double?,
    val synopsis: String?,
    val imageUrl: String?
)