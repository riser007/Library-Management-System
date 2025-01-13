Library Management System

The Library Management System is designed to manage books, patrons, and lending processes efficiently. This project demonstrates the use of Object-Oriented Programming principles, adherence to SOLID principles, and implementation of relevant design patterns like Factory and Observer.

1 Features -

1.1 Book Management:
Add, remove, and update books in the library inventory.
Search for books by title, author, or ISBN.

1.2 Patron Management:
Add new patrons and update their information.
Track patron borrowing history.

1.3 Lending Process:
Checkout and return books with real-time availability updates.

1.4 Inventory Management:
Keep track of available and borrowed books.


2 Design Principles and Patterns -

2.1 OOP Principles:

Encapsulation: Classes like Book and Patron encapsulate their attributes and behavior.

Abstraction: Abstracted the complexities of inventory and lending management into reusable classes.

Polymorphism: Extensible design for future expansion (e.g., different lending policies).

2.2 SOLID Principles:

Single Responsibility Principle: Each class has a single responsibility (e.g., Book for book attributes, LibraryInventory for inventory management).

Open/Closed Principle: System is open for extension but closed for modification.

Dependency Inversion: High-level modules (like LibraryManagementSystem) depend on abstractions rather than concrete implementations.

2.3 Design Patterns:

Factory Pattern: Used for creating Book and Patron objects dynamically.

Observer Pattern: Notify patrons when a book becomes available.
