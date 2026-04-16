# Student Registration System

A Java-based application designed to manage student records, developed as part of the Computer Science curriculum at PUC-SP. This project demonstrates the practical application of Object-Oriented Programming (OOP) principles, focusing on modularity and clean code.

## Technical Overview

The system is built using Java and follows a structured architectural approach to ensure maintainability and clear separation of concerns.

### Key Features
- **Student Management:** Full CRUD (Create, Read, Update, Delete) operations for student data.
- **Data Persistence:** Local storage implementation using flat files (`banco.txt`).
- **Modular Architecture:** Organized into specific packages for data handling, business logic, and user interface.

### Architectural Principles
Unlike traditional inheritance-based models, this project strictly adheres to **Composition over Inheritance**. This approach was chosen to:
- Reduce system coupling.
- Increase flexibility in object behavior.
- Ensure a more robust implementation of the domain logic as per academic requirements.

## Project Structure

The project is organized into the following packages:
- `modelo`: Contains the core entities and business logic.
- `visao`: Handles the user interface components.
- `controle`: Manages the communication between the model and the view.
- `dados` & `armazenamento`: Responsible for file I/O and data persistence.

## Requirements
- Java Development Kit (JDK) 8 or higher.
- BlueJ IDE (recommended) or any Java-compatible terminal.

## How to Run
1. Clone the repository.
2. Compile the `App.java` file located in the root directory.
3. Execute the main method to launch the application.

## Author
**Pedro Chagas Neves de Farias Nascimento ** Computer Science Student - PUC-SP
