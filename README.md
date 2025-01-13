# Bookstore API

This is a Bookstore API built using **Spring Boot** with **Java**, **MySQL** for database storage, and includes RESTful endpoints for managing books, authors, and categories. It also incorporates unit tests for service and repository layers.

## Features

- Manage books (create, read, update, delete).
- Manage authors (create, read, update, delete).
- Manage categories (create, read, update, delete).
- Unit tests for service layer.
- Custom exception handling (e.g., ResourceNotFoundException).

## Technologies Used

- **Java 17**
- **Spring Boot**
- **MySQL**
- **JUnit 5** for unit testing
- **Mockito** for mocking dependencies in tests

## API Endpoints

### Category API

- **GET** `/api/categories` - Retrieve all categories.
- **GET** `/api/categories/{id}` - Retrieve a category by ID.
- **POST** `/api/categories` - Create a new category.
- **PUT** `/api/categories/{id}` - Update an existing category.
- **DELETE** `/api/categories/{id}` - Delete a category by ID.

### Book API

- **GET** `/api/books` - Retrieve all books.
- **GET** `/api/books/{id}` - Retrieve a book by ID.
- **POST** `/api/books` - Create a new book.
- **PUT** `/api/books/{id}` - Update an existing book.
- **DELETE** `/api/books/{id}` - Delete a book by ID.

### Author API

- **GET** `/api/authors` - Retrieve all authors.
- **GET** `/api/authors/{id}` - Retrieve an author by ID.
- **POST** `/api/authors` - Create a new author.
- **PUT** `/api/authors/{id}` - Update an existing author.
- **DELETE** `/api/authors/{id}` - Delete an author by ID.

## Installation

### Prerequisites

Make sure you have the following installed on your machine:

- **Java 17** or later
- **Maven**
- **MySQL** database (or use an embedded database)

### Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/selimchikhzaouali/bookstore-backend.git
   ```

2. Navigate into the project directory and build the project:

```bash
cd bookstore-api
mvn clean install
```

3. Update the application.properties file to configure your MySQL database connection:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bookstore
spring.datasource.username=root
spring.datasource.password=yourpassword
```

4. Run the application:

```bash
mvn spring-boot:run
```

5. The API will be accessible at http://localhost:8080.

Running Tests
You can run the tests using the following Maven command:

```bash
mvn test
```
