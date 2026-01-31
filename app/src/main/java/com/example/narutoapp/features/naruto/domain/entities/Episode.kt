package com.example.narutoapp.features.naruto.domain.entities

data class Episode(
    val malId: Int,
    val title: String,
    val titleJapanese: String?,
    val aired: String?,
    val score: Double?,
    val isFiller: Boolean,
    val isRecap: Boolean
)