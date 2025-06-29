# ICD8_xlsxTcsv

This project is a simple Swing based application for converting ICD8 Excel files to CSV and optionally storing the results in a MySQL database.  The build is managed with Gradle and uses Kotlin DSL for configuration.

## Requirements

- **Java**: JDK 11 or higher must be installed and available on your `PATH`.
- **MySQL**: A running MySQL instance.  Update `src/main/java/com/migojj/icd8/db/DbConfig.java` with your JDBC URL, user and password before running.

## Building

From the project directory run:

```bash
gradle build
```

This will download dependencies (Apache POI, MySQL connector, SLF4J and Logback) and compile the project.

## Running

To launch the application use:

```bash
gradle run
```

The main class is `com.migojj.icd8.MainApplication` which opens the Swing user interface.

## Testing

Placeholder JUnit tests are provided.  They can be executed with:

```bash
gradle test
```


