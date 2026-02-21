# 🍥 Naruto App

<div align="center">

![Naruto Icon](https://github.com/user-attachments/assets/224c197c-1d26-4e3f-a4e6-f7a92e26a4ec)


[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.20-blue.svg)](https://kotlinlang.org/)
[![Android](https://img.shields.io/badge/Android-API%2024+-green.svg)](https://developer.android.com)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-1.5.4-orange.svg)](https://developer.android.com/jetpack/compose)
[![Clean Architecture](https://img.shields.io/badge/Architecture-Clean%20Architecture-brightgreen.svg)](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)

**Aplicación Android que muestra información completa de la serie Naruto consumiendo la API pública de Jikan (MyAnimeList)**

[Características](#-características) •
[Instalación](#-instalación) •
[Arquitectura](#-arquitectura) •
[Tecnologías](#-tecnologías) •
[API](#-api-utilizada)

</div>

---

## 📖 Tabla de Contenidos

- [Sobre el Proyecto](#-sobre-el-proyecto)
- [Características](#-características)
- [Tecnologías Utilizadas](#-tecnologías-utilizadas)
- [Arquitectura](#-arquitectura)
- [Instalación](#-instalación)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [API Utilizada](#-api-utilizada)
- [Contacto](#-contacto)

---

## 🎯 Sobre el Proyecto

**Naruto App** es una aplicación móvil Android nativa desarrollada como proyecto académico que demuestra la implementación de **Clean Architecture** y las mejores prácticas modernas de desarrollo Android.

### Problemática que Resuelve

Los fanáticos del anime Naruto enfrentan dificultades para:

- Acceder rápidamente a información completa de la serie
- Identificar qué episodios son relleno (filler) vs canon
- Consultar calificaciones y fechas de emisión
- Encontrar toda esta información en un solo lugar

### Solución

Una aplicación móvil que centraliza toda la información del anime Naruto, consultando datos actualizados desde MyAnimeList a través de la API de Jikan, presentándolos de forma visual, organizada y accesible.

---

## ✨ Características

### Funcionalidades Principales

#### 📺 Información Completa del Anime

- Poster oficial de alta calidad
- Título en inglés y japonés
- Sinopsis detallada
- Número total de episodios
- Calificación de MyAnimeList
- Estado de emisión y duración

#### 📋 Lista Completa de Episodios (220 episodios)

- Títulos en inglés y japonés
- Clasificación visual mediante badges:
  - 🟢 **CANON** - Episodios de la historia principal
  - 🔴 **FILLER** - Episodios de relleno
  - 🟠 **RECAP** - Episodios de recapitulación
- Calificación individual por episodio
- Fecha de emisión original

#### 🎨 Interfaz Moderna

- Diseño con Material Design 3
- Tema personalizado con colores de Naruto
- Animaciones fluidas
- Navegación intuitiva

#### ⚡ Rendimiento Optimizado

- Carga asíncrona de datos
- Renderizado eficiente con LazyColumn
- Caché automático de imágenes
- Manejo inteligente de estados (Loading/Error/Success)

---

## 🛠️ Tecnologías Utilizadas

### Lenguaje y Framework

- **Kotlin** - Lenguaje oficial de Android
- **Jetpack Compose** - UI Toolkit moderno y declarativo
- **Material Design 3** - Sistema de diseño de Google

### Arquitectura y Patrones

- **Clean Architecture** - Separación de responsabilidades en capas
- **MVVM** (Model-View-ViewModel) - Patrón de presentación
- **Repository Pattern** - Abstracción de fuentes de datos
- **Use Cases** - Encapsulación de lógica de negocio
- **Hilt** - Inyección de dependencias con Dagger Hilt

### Librerías Principales

| Librería | Versión | Propósito |
|----------|---------|-----------|
| Retrofit | 2.9.0 | Cliente HTTP para APIs REST |
| Gson | 2.10.1 | Serialización/Deserialización JSON |
| Coil | 2.5.0 | Carga asíncrona de imágenes |
| Kotlin Coroutines | 1.7.3 | Programación asíncrona |
| Lifecycle Components | 2.7.0 | Manejo de ciclo de vida |
| Compose BOM | 2023.10.01 | Bill of Materials para Compose |
| Hilt | 2.51.1 | Inyección de dependencias |
| Hilt Navigation Compose | 1.2.0 | Integración de Hilt con Compose |

### Herramientas de Desarrollo

- **Android Studio** Hedgehog (2023.1.1+)
- **Gradle** 8.2 con Kotlin DSL
- **Git & GitHub** - Control de versiones
- **OkHttp Logging Interceptor** - Debugging de requests

---

## 🏗️ Arquitectura

Este proyecto implementa **Clean Architecture** con separación en 3 capas:
```
┌─────────────────────────────────────────┐
│        PRESENTATION LAYER               │
│  (UI, ViewModels, States)              │
│  • Jetpack Compose                     │
│  • StateFlow                           │
└────────────────┬────────────────────────┘
                 │
                 ↓
┌─────────────────────────────────────────┐
│         DOMAIN LAYER                    │
│  (Entities, Use Cases, Repositories)   │
│  • Business Logic                      │
│  • Pure Kotlin                         │
└────────────────┬────────────────────────┘
                 │
                 ↓
┌─────────────────────────────────────────┐
│          DATA LAYER                     │
│  (API, DTOs, Repository Impl)          │
│  • Retrofit                            │
│  • Mappers                             │
└─────────────────────────────────────────┘
```

### Flujo de Datos
```
Usuario interactúa con UI
          ↓
ViewModel recibe evento
          ↓
ViewModel llama Use Case
          ↓
Use Case ejecuta lógica de negocio
          ↓
Use Case consulta Repository
          ↓
Repository obtiene datos de API
          ↓
Repository mapea DTO → Entity
          ↓
Use Case procesa y retorna Result
          ↓
ViewModel actualiza StateFlow
          ↓
UI se recompone automáticamente
```

### Principios Aplicados

- ✅ **Separación de Responsabilidades** - Cada capa tiene un propósito claro
- ✅ **Inversión de Dependencias** - Las capas externas dependen de las internas
- ✅ **Abstracción** - Interfaces separan implementaciones
- ✅ **Single Responsibility** - Clases con una única razón para cambiar
- ✅ **Testabilidad** - Fácil de mockear y testear

---

## 📥 Instalación

### Prerrequisitos

Asegúrate de tener instalado:

- ✅ **Android Studio** Hedgehog o superior
- ✅ **JDK 17** (incluido con Android Studio)
- ✅ **Android SDK** con API 24 o superior
- ✅ **Git** (opcional, para clonar el repositorio)

### Pasos de Instalación

#### 1. Clonar el repositorio
```bash
git clone https://github.com/FabricioPRZ/NARUTO_APP.git
cd NARUTO_APP
```

#### 2. Abrir en Android Studio
```
File > Open > Seleccionar la carpeta "NARUTO_APP"
```

#### 3. Sincronizar Gradle

Espera a que Android Studio sincronice automáticamente, o manualmente:
```
File > Sync Project with Gradle Files
```

#### 4. Ejecutar la aplicación

- Conecta un dispositivo Android o inicia un emulador
- Click en el botón **Run** (▶️) o usa `Shift + F10`

### Configuración Adicional (Opcional)

Si deseas modificar la URL base de la API:
```kotlin
// build.gradle.kts (app module)
defaultConfig {
    buildConfigField("String", "BASE_URL", "\"https://api.jikan.moe/v4/\"")
}
```

Asegúrate también de que `NarutoApp` esté registrada en el `AndroidManifest.xml`:
```xml
<application
    android:name=".NarutoApp"
    ...
>
```

---

## 📂 Estructura del Proyecto
```
com.example.narutoapp/
│
├── 📁 core/                                    # Módulos compartidos
│   ├── 📁 di/
│   │   └── 📄 NetworkModule.kt                # Módulo de red (Hilt)
│   │
│   ├── 📁 network/
│   │   └── 📄 JikanApi.kt                     # Definición de API
│   │
│   └── 📁 ui/theme/
│       ├── 📄 Theme.kt                        # Tema de la app
│       └── 📄 Type.kt                         # Tipografía
│
├── 📁 features/                               # Funcionalidades
│   └── 📁 naruto/
│       │
│       ├── 📁 data/                           # Capa de Datos
│       │   ├── 📁 datasources/remote/
│       │   │   ├── 📁 mapper/
│       │   │   │   └── 📄 NarutoMapper.kt    # DTO → Domain
│       │   │   │
│       │   │   └── 📁 model/
│       │   │       ├── 📄 AnimeResponse.kt   # DTOs
│       │   │       └── 📄 EpisodesResponse.kt
│       │   │
│       │   └── 📁 repositories/
│       │       └── 📄 NarutoRepositoryImpl.kt # Implementación
│       │
│       ├── 📁 domain/                         # Capa de Dominio
│       │   ├── 📁 entities/
│       │   │   ├── 📄 AnimeInfo.kt           # Entidades
│       │   │   └── 📄 Episode.kt
│       │   │
│       │   ├── 📁 repositories/
│       │   │   └── 📄 NarutoRepository.kt    # Interface
│       │   │
│       │   └── 📁 usecases/
│       │       ├── 📄 GetAnimeInfoUseCase.kt
│       │       └── 📄 GetEpisodesUseCase.kt
│       │
│       ├── 📁 di/
│       │   └── 📄 RepositoryModule.kt        # Módulo de repositorio (Hilt)
│       │
│       └── 📁 presentation/                   # Capa de Presentación
│           ├── 📁 components/
│           │   └── 📄 EpisodeCard.kt         # Componentes UI
│           │
│           ├── 📁 screens/
│           │   ├── 📄 NarutoScreen.kt        # Pantalla principal
│           │   └── 📄 NarutoUiState.kt       # Estado UI
│           │
│           └── 📁 viewmodels/
│               └── 📄 NarutoViewModel.kt     # @HiltViewModel
│
├── 📄 NarutoApp.kt                            # @HiltAndroidApp
└── 📄 MainActivity.kt                         # Activity principal
```

---

## 🌐 API Utilizada

### Jikan API v4

**Jikan** es la API REST no oficial más popular para MyAnimeList.

- 🌍 **Base URL:** `https://api.jikan.moe/v4/`
- 📚 **Documentación:** https://docs.api.jikan.moe/
- 🔓 **Sin autenticación** - Completamente gratuita
- ⚡ **Rate Limit:** 3 requests/segundo, 60 requests/minuto

### Endpoints Utilizados

#### 1. Obtener Información del Anime
```http
GET /anime/{id}
```

**Ejemplo de respuesta:**
```json
{
  "data": {
    "mal_id": 20,
    "title": "Naruto",
    "title_japanese": "ナルト",
    "synopsis": "...",
    "episodes": 220,
    "score": 7.99,
    "images": { ... }
  }
}
```

#### 2. Obtener Episodios
```http
GET /anime/{id}/episodes?page={page}
```

**Ejemplo de respuesta:**
```json
{
  "data": [
    {
      "mal_id": 1,
      "title": "Enter: Naruto Uzumaki!",
      "title_japanese": "参上!うずまきナルト",
      "aired": "2002-10-03",
      "score": 7.45,
      "filler": false
    }
  ]
}
```

### IDs de Series Naruto
```kotlin
const val NARUTO_ID = 20              // Naruto (220 episodios)
const val NARUTO_SHIPPUDEN_ID = 1735  // Naruto Shippuden (500 episodios)
const val BORUTO_ID = 34566           // Boruto: Naruto Next Generations
```

---

## 📞 Contacto

- 📧 **Email:** fabricioperzcontacto@gmail.com
- 🐙 **GitHub:** [@FabricioPRZ](https://github.com/FabricioPRZ)
- 💼 **LinkedIn:** [Victor Fabricio Pérez Constantino](https://linkedin.com/in/victor-fabricio-perez-constantino-4006453a3)

---

<div align="center">

### ⭐ Si te gustó este proyecto, dale una estrella!

**Desarrollado con ❤️ por Fabricio Pérez**

</div>
