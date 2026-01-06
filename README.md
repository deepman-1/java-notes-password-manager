# Java Notes & Password Manager

A beginner–intermediate Java portfolio project that demonstrates a simple password and notes manager using core Java concepts.

## What this project does
- Stores password entries (service, username, password, note)
- Stores secure notes (title + content)
- Uses HashMaps to organise and manage data
- Saves and loads data using Java file handling (serialization)

## Features
- Add and view password entries (password values are hidden when displayed)
- Add and view secure notes
- Search passwords and notes by keyword
- Delete a password entry or note
- Save/load stored data via a local file

## Why it solves a real problem
People often store passwords and private notes in unsafe places (random notes apps, screenshots, or unorganised documents).  
This project shows how a simple Java program can manage personal information in a structured way.

## Key Java concepts used
- Classes and packages
- Java records (data models)
- HashMap / Map collections
- Menu-based program flow (switch + loops)
- File I/O (save/load)

## How it works (high level)
- The program displays a menu for adding, viewing, searching, and deleting entries.
- Data is stored in memory using HashMaps.
- On exit, the vault is saved to a file and can be loaded again later.

## Files
- `Main.java` → menu + user interaction (add/view/search/delete)
- `VaultData.java` → stores passwords + notes in HashMaps
- `Models.java` → defines `Credential` and `Note`
- `VaultStorage.java` → saves/loads vault data to a file
