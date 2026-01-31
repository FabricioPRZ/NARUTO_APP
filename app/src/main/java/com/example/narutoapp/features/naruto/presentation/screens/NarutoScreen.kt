package com.example.narutoapp.features.naruto.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.narutoapp.features.naruto.presentation.components.EpisodeCard
import com.example.narutoapp.features.naruto.presentation.viewmodels.NarutoViewModel
import com.example.narutoapp.features.naruto.presentation.viewmodels.NarutoViewModelFactory


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NarutoScreen(
    factory: NarutoViewModelFactory
) {
    val viewModel: NarutoViewModel = viewModel(factory = factory)
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Naruto",
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFFFF9933) // Naruto orange
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when {
                uiState.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = Color(0xFFFF9933)
                    )
                }

                uiState.error != null -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "X",
                            style = MaterialTheme.typography.displayLarge
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = uiState.error ?: "Error desconocido",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Red,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        // Header con información del anime
                        uiState.animeInfo?.let { animeInfo ->
                            item {
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        // Imagen
                                        animeInfo.imageUrl?.let { imageUrl ->
                                            AsyncImage(
                                                model = imageUrl,
                                                contentDescription = "Imagen de ${animeInfo.title}",
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .height(300.dp)
                                                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                                                contentScale = ContentScale.Crop
                                            )
                                        }

                                        Column(
                                            modifier = Modifier.padding(16.dp)
                                        ) {
                                            Text(
                                                text = animeInfo.title,
                                                style = MaterialTheme.typography.headlineMedium,
                                                fontWeight = FontWeight.Bold
                                            )

                                            animeInfo.titleJapanese?.let {
                                                Spacer(modifier = Modifier.height(4.dp))
                                                Text(
                                                    text = it,
                                                    style = MaterialTheme.typography.bodyLarge,
                                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                                )
                                            }

                                            Spacer(modifier = Modifier.height(16.dp))

                                            Row(
                                                modifier = Modifier.fillMaxWidth(),
                                                horizontalArrangement = Arrangement.SpaceBetween
                                            ) {
                                                Column {
                                                    Text(
                                                        text = "Episodios",
                                                        style = MaterialTheme.typography.labelMedium,
                                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                                    )
                                                    Text(
                                                        text = "${animeInfo.episodes}",
                                                        style = MaterialTheme.typography.titleLarge,
                                                        fontWeight = FontWeight.Bold
                                                    )
                                                }

                                                animeInfo.score?.let { score ->
                                                    Column(horizontalAlignment = Alignment.End) {
                                                        Text(
                                                            text = "Calificación",
                                                            style = MaterialTheme.typography.labelMedium,
                                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                                        )
                                                        Text(
                                                            text = "⭐ $score",
                                                            style = MaterialTheme.typography.titleLarge,
                                                            fontWeight = FontWeight.Bold
                                                        )
                                                    }
                                                }
                                            }

                                            Spacer(modifier = Modifier.height(16.dp))

                                            animeInfo.synopsis?.let {
                                                Text(
                                                    text = "Sinopsis",
                                                    style = MaterialTheme.typography.titleMedium,
                                                    fontWeight = FontWeight.Bold
                                                )
                                                Spacer(modifier = Modifier.height(8.dp))
                                                Text(
                                                    text = it,
                                                    style = MaterialTheme.typography.bodyMedium,
                                                    textAlign = TextAlign.Justify
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        // Título de episodios
                        item {
                            Text(
                                text = "Episodios (${uiState.episodes.size})",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                            )
                        }

                        // Lista de episodios
                        itemsIndexed(uiState.episodes) { index, episode ->
                            EpisodeCard(
                                episodeNumber = index + 1,
                                title = episode.title,
                                titleJapanese = episode.titleJapanese,
                                aired = episode.aired,
                                score = episode.score,
                                isFiller = episode.isFiller,
                                isRecap = episode.isRecap
                            )
                        }

                        // Espaciado final
                        item {
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
        }
    }
}