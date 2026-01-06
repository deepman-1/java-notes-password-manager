# Java Notes & Password Manager

A beginner–intermediate Java portfolio project that demonstrates a simple password and notes manager using core Java concepts.

## What this project does
- Stores password entries (service, username, password, note)
- Stores secure notes (title + content)
- Uses HashMaps to organise data
- Saves and loads data using Java file handling (serialization)

## Why it solves a real problem
People often store passwords and private notes in unsafe places (random notes apps, screenshots, or unorganised documents).  
This project shows how a simple Java program can manage personal information in a structured way.

## Key Java concepts used
- Classes and packages
- Java records (data models)
- HashMap / Map collections
- Menu-based program flow
- File I/O (save/load)

## Files
- `Main.java` → menu + user interaction
- `VaultData.java` → stores passwords + notes
- `Models.java` → defines Credential and Note
- `VaultStorage.java` → saves/loads data to a file
