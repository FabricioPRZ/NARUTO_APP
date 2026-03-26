package com.example.narutoapp.features.search.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.narutoapp.features.naruto.presentation.components.EpisodeCard
import com.example.narutoapp.features.search.presentation.viewmodels.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Buscar episodios",
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFFFF9933)
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Barra de búsqueda
            OutlinedTextField(
                value = uiState.query,
                onValueChange = { viewModel.onQueryChange(it) },
                placeholder = { Text("Buscar por título...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                singleLine = true
            )

            // Filtros
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(EpisodeFilter.entries.size) { index ->
                    val filter = EpisodeFilter.entries[index]
                    FilterChip(
                        selected = uiState.selectedFilter == filter,
                        onClick = { viewModel.onFilterChange(filter) },
                        label = { Text(filter.name) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            when {
                uiState.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = Color(0xFFFF9933))
                    }
                }

                uiState.error != null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = uiState.error ?: "Error desconocido",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Red
                        )
                    }
                }

                uiState.filteredEpisodes.isEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No se encontraron episodios",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                else -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        itemsIndexed(uiState.filteredEpisodes) { index, episode ->
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
                        item { Spacer(modifier = Modifier.height(16.dp)) }
                    }
                }
            }
        }
    }
}