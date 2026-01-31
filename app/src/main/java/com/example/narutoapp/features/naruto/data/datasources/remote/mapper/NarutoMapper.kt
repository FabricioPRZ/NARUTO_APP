package com.example.narutoapp.features.naruto.data.datasources.remote.mapper

import com.example.narutoapp.features.naruto.data.datasources.remote.model.AnimeDto
import com.example.narutoapp.features.naruto.data.datasources.remote.model.EpisodeDto
import com.example.narutoapp.features.naruto.domain.entities.AnimeInfo
import com.example.narutoapp.features.naruto.domain.entities.Episode

fun AnimeDto.toDomain(): AnimeInfo {
    return AnimeInfo(
        malId = this.mal_id,
        title = this.title,
        titleEnglish = this.title_english,
        titleJapanese = this.title_japanese,
        type = this.type ?: "Unknown",
        episodes = this.episodes ?: 0,
        status = this.status ?: "Unknown",
        airedFrom = this.aired?.from,
        airedTo = this.aired?.to,
        duration = this.duration,
        score = this.score,
        synopsis = this.synopsis,
        imageUrl = this.images?.jpg?.large_image_url
    )
}

fun EpisodeDto.toDomain(): Episode {
    return Episode(
        malId = this.mal_id,
        title = this.title,
        titleJapanese = this.title_japanese,
        aired = this.aired,
        score = this.score,
        isFiller = this.filler,
        isRecap = this.recap
    )
}