# 🍥 Naruto App

<div align="center">

![Naruto Banner](![icon](https://github.com/user-attachments/assets/978f8441-6b2d-469c-8224-70b372fba846)
)

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.20-blue.svg)](https://kotlinlang.org/)
[![Android](https://img.shields.io/badge/Android-API%2024+-green.svg)](https://developer.android.com)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-1.5.4-orange.svg)](https://developer.android.com/jetpack/compose)
[![Clean Architecture](https://img.shields.io/badge/Architecture-Clean%20Architecture-brightgreen.svg)](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)

**Aplicación Android que muestra información completa de la serie Naruto consumiendo la API pública de Jikan (MyAnimeList)**

[Características](#-características) •
[Instalación](#-instalación) •
[Arquitectura](#-arquitectura) •
[Tecnologías](#-tecnologías-utilizadas) •
[API](#-api-utilizada)

</div>

---

## 📖 Tabla de Contenidos

* [Sobre el Proyecto](#-sobre-el-proyecto)
* [Características](#-características)
* [Tecnologías Utilizadas](#-tecnologías-utilizadas)
* [Arquitectura](#-arquitectura)
* [Instalación](#-instalación)
* [Estructura del Proyecto](#-estructura-del-proyecto)
* [API Utilizada](#-api-utilizada)

---

## 🎯 Sobre el Proyecto

**Naruto App** es una aplicación móvil Android nativa desarrollada como proyecto académico que demuestra la implementación de **Clean Architecture** y buenas prácticas modernas de desarrollo Android.

### Problemática

Los fanáticos de Naruto suelen necesitar:

* Acceso rápido a información confiable de la serie
* Identificar episodios **canon**, **filler** y **recap**
* Consultar calificaciones y fechas de emisión
* Centralizar toda la información en una sola aplicación

### Solución

Una aplicación Android que centraliza la información del anime Naruto utilizando datos actualizados de **MyAnimeList** a través de la **API de Jikan**, presentados de forma clara, visual y accesible.

---

## ✨ Características

### Funcionalidades Principales

* 📺 **Información del Anime**

  * Poster oficial en alta calidad
  * Título en inglés y japonés
  * Sinopsis detallada
  * Número total de episodios
  * Calificación en MyAnimeList
  * Estado de emisión y duración

* 📋 **Lista Completa de Episodios**

  * Títulos en inglés y japonés
  * Clasificación visual:

    * 🟢 **CANON**
    * 🔴 **FILLER**
    * 🟠 **RECAP**
  * Calificación individual
  * Fecha de emisión

* 🎨 **Interfaz Moderna**

  * Material Design 3
  * Tema personalizado inspirado en Naruto
  * Animaciones fluidas
  * Navegación intuitiva

* ⚡ **Rendimiento Optimizado**

  * Carga asíncrona con Coroutines
  * LazyColumn para listas eficientes
  * Caché automático de imágenes
  * Manejo de estados (Loading / Error / Success)

---

## 🛠️ Tecnologías Utilizadas

### Lenguaje y Framework

* **Kotlin**
* **Jetpack Compose**
* **Material Design 3**

### Arquitectura y Patrones

* **Clean Architecture**
* **MVVM**
* **Repository Pattern**
* **Use Cases**
* **Inyección de dependencias manual**

### Librerías

| Librería    | Versión    | Uso               |
| ----------- | ---------- | ----------------- |
| Retrofit    | 2.9.0      | Cliente HTTP      |
| Gson        | 2.10.1     | JSON Parsing      |
| Coil        | 2.5.0      | Carga de imágenes |
| Coroutines  | 1.7.3      | Asincronía        |
| Lifecycle   | 2.7.0      | Ciclo de vida     |
| Compose BOM | 2023.10.01 | Dependencias UI   |

### Herramientas

* Android Studio Hedgehog
* Gradle 8.2 (Kotlin DSL)
* Git y GitHub
* OkHttp Logging Interceptor

---

## 🏗️ Arquitectura

El proyecto sigue **Clean Architecture** con tres capas principales:

```
PRESENTATION
(UI, ViewModels, State)
        ↓
DOMAIN
(Use Cases, Entities, Repositories)
        ↓
DATA
(API, DTOs, Mappers)
```

### Flujo de Datos

1. Usuario interactúa con la UI
2. ViewModel recibe el evento
3. Se ejecuta un Use Case
4. El repositorio obtiene datos de la API
5. DTOs se mapean a entidades de dominio
6. El estado se actualiza y la UI se recompone

### Principios Aplicados

* Separación de responsabilidades
* Inversión de dependencias
* Abstracción mediante interfaces
* Single Responsibility Principle
* Alta testabilidad

---

## 📥 Instalación

### Requisitos

* Android Studio Hedgehog o superior
* JDK 17
* Android SDK API 24+
* Git (opcional)

### Pasos

1. Clonar el repositorio:

```bash
git clone https://github.com/FabricioPRZ/NARUTO_APP.git
cd NARUTO_APP
```

2. Abrir el proyecto en Android Studio

3. Sincronizar Gradle

4. Ejecutar la app en un emulador o dispositivo físico

### Configuración Opcional

Modificar la URL base de la API:

```kotlin
buildConfigField(
    "String",
    "BASE_URL",
    "\"https://api.jikan.moe/v4/\""
)
```

---

## 📂 Estructura del Proyecto

```
com.example.narutoapp
│
├── core
│   ├── di
│   ├── network
│   └── ui/theme
│
├── features/naruto
│   ├── data
│   ├── domain
│   ├── di
│   └── presentation
│
└── MainActivity.kt
```

---

## 🌐 API Utilizada

### Jikan API v4

* Base URL: `https://api.jikan.moe/v4/`
* Documentación: [https://docs.api.jikan.moe/](https://docs.api.jikan.moe/)
* Sin autenticación
* Rate limit: 3 req/s, 60 req/min

### Endpoints

```http
GET /anime/{id}
GET /anime/{id}/episodes?page={page}
```

### IDs Utilizados

```kotlin
const val NARUTO_ID = 20
const val NARUTO_SHIPPUDEN_ID = 1735
const val BORUTO_ID = 34566
```

---

## 👤 Autor

* **Fabricio Pérez**
* GitHub: [@FabricioPRZ](https://github.com/FabricioPRZ)
* Email: [fabricioperzcontacto@gmail.com](mailto:fabricioperzcontacto@gmail.com)
* LinkedIn: Victor Fabricio Pérez Constantino

---

<div align="center">

### ⭐ Si te gustó este proyecto, dale una estrella

</div>
