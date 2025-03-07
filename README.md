# Quran Android Application

The goal of this application is to provide a digital Quran reader that displays Quranic surahs in a clean, user-friendly interface. The app features a complete Quran with all 114 surahs, page navigation, bookmarking capabilities, and settings for customization.

## Technologies Used

- Kotlin programming language
- Dependency Injection with custom modules
- Room for database management
- MVVM architecture
- Clean Architecture principles
- Repository pattern
- Use case pattern
- Jetpack Compose for UI components
- Coroutines for asynchronous operations
- Unit testing

## Features

- Complete Quran with all 114 surahs
- Page-by-page navigation through the Quran
- Surah selection through drawer navigation
- Bookmark functionality to save reading positions
- Settings for customization
- Clean and intuitive user interface

## Architecture

The application follows Clean Architecture principles with three main layers:

- **Presentation Layer**: Contains UI components, screens, view models, and states
- **Domain Layer**: Contains business logic, models, repositories interfaces, and use cases
- **Data Layer**: Contains repository implementations, data sources, database, and mappers

## Installation

To install the Holy Quran Android App, follow the steps below:

1. Clone the repository to your local machine.
2. Open the project in Android Studio.
3. Build and run the app on an Android emulator or physical device.

## Usage

Upon opening the app, users are presented with the Quran reader screen showing the current page. Users can navigate through pages by swiping left or right. The drawer navigation allows users to select specific surahs directly.

The app remembers the last read position and automatically opens at that page when the app is launched again. The settings screen allows users to customize their reading experience.

## Project Structure

```
app/
├── src/
│   ├── main/
│   │   ├── assets/
│   │   │   └── quran_pages/ (604 Quran page images)
│   │   ├── java/com/example/holyquranapp/
│   │   │   ├── di/ (Dependency Injection)
│   │   │   ├── data/ (Data Layer)
│   │   │   ├── domain/ (Domain Layer)
│   │   │   ├── presentation/ (UI Layer)
│   │   │   └── util/ (Utilities)
│   │   └── res/ (Android Resources)
│   └── test/ (Unit tests)
```

## Contributing

If you would like to contribute to the Holy Quran Android App, please follow the steps below:

1. Fork the repository.
2. Create a new branch for your changes.
3. Make your changes and commit them.
4. Push your changes to your fork.
5. Submit a pull request.
