# Student Registration System

This is an academic project developed for a Computer Science course at PUC-SP (1st Semester of 2026). The system consists of a Java application with a Graphical User Interface (GUI) focused on managing student registrations. It applies solid Object-Oriented Programming (OOP) concepts, MVC architecture, and separation of concerns.

## Features (Functional Requirements)

* **FR01 - Insert student record:** Allows the registration of new students, featuring protection against duplicate enrollment numbers (RA - *Registro Acadêmico*) and system maximum capacity validation.
* **FR02 - Remove a specific student:** Deletes records by searching for their unique identifier (RA).
* **FR03 - List registered students:** Dynamically displays all registered students. Allows listing in two formats: Standard or Bibliographic Format (e.g., SOUZA, Pedro Chagas).
* **FR04 - Update student data:** Allows the modification of data (Name, Age, and Course/Major) for an existing student by searching for their enrollment number.

## Business Rules and Validations

The system was built to be user-friendly, preventing technical error messages from being displayed to the user. All inputs undergo strict validation:
* Registering students with the same enrollment number (RA) is not allowed.
* The RA (*Registro Acadêmico* / Enrollment Number) accepts numbers only.
* The Name and Course fields exclusively accept letters and spaces.
* Age is validated within a logical range (16 to 80 years).
* Removal and update operations block attempts to modify non-existent enrollments.

## Architecture and Organization (Packages)

The code was refactored and divided into packages to ensure readability, reusability, and ease of maintenance:

* `modelo/` **(Model):** Contains the domain classes (`Aluno`, `Pessoa`, `Texto`, `NomePessoa`). Encapsulation was strictly applied.
* `armazenamento/` **(Storage):** Data persistence layer. It uses the Dependency Inversion Principle via the `IArmazenador` interface, allowing the current implementation (`ArmazenadorArray`) to be replaced in the future (e.g., by a Database) without breaking the code.
* `controle/` **(Controller):** The bridge that applies Business Rules (`CadastroAlunos`), connecting the View to the Storage layer.
* `visao/` **(View):** User interface. It uses the `IMenu` interface and was implemented via `MenuGrafico` (Java Swing via `JOptionPane`), ensuring that inputs and outputs do not mix with the business logic.

## How to Run

1.  Ensure you have the JDK (Java Development Kit) installed.
2.  Clone this repository.
3.  Compile the files or open the root folder in an IDE (such as BlueJ, Eclipse, or IntelliJ).
4.  Run the main file: `App.java`.

---
**Institution:** PUC-SP - School of Exact Sciences and Technology  
**Laboratory:** LED - Dynamic Structures Laboratory
