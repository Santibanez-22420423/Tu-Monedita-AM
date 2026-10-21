# Tu Monedita Architecture

## Technology

- Kotlin Multiplatform
- Compose Multiplatform
- Material 3
- Gradle Kotlin DSL

## Project modules

- `androidApp`: Android application entry point.
- `iosApp`: iOS application entry point.
- `shared`: Shared UI and business logic.

## Architecture

The application follows a feature-based layered architecture.

Each feature is organized into three layers:

### Presentation

Screens, reusable components, UI state, and ViewModels.

### Domain

Business models, repository contracts, and use cases.

### Data

Repository implementations, local data sources, remote data sources, and data mappers.

## Core packages

- `designsystem`: Shared UI theme and components.
- `navigation`: Application routes and navigation.
- `database`: Database infrastructure.
- `network`: HTTP client infrastructure.
- `di`: Dependency injection configuration.
- `utils`: Shared utilities.

## Features

- Authentication
- Transactions
- Dashboard
- Categories
- Budgets
- Reports

## Shared resources

Images, icons, fonts, and other shared resources are stored in `shared/src/commonMain/composeResources`.
