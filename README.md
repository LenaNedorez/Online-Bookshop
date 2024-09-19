# Online bookshop
This project is a web application implementing an online bookstore. It allows users to register, log in, browse the book catalog, get detailed information about each book, add products to the cart, and place an order. Frontend part provided by Skillbox.

## Technologies
- Server part: Java, Spring Boot
- Framework: Spring
- ORM: Spring Data JPA
- Security: Spring Security, OAuth 2.0, JWT
- Database: PostgreSQL
- Build tools: Maven

## Functionality
#### Registration and Authorization
- Users can register using their credentials
- Authorization system is implemented using Spring Security
- Authentication via OAuth 2.0 is supported (e.g. OpenAI, Facebook)
- JWTs are used for user authentication and authorization
#### Book Catalog
- Creating and managing a book catalog
- Searching for books by keywords, author, genre, and other criteria
- Viewing detailed information about each book, including cover, description, author, price, and reviews
#### Shopping Cart
- Adding books to the cart
- Changing the number of items in the cart
- Removing items from the cart
- Viewing the contents of the cart
#### Checkout
- Creating an order
- Selecting a shipping method
- Selecting a payment method
- Tracking the order status

## Installation
1. Make sure you have Java JDK and Maven installed
2. Download or clone the project repository
3. In the project root, run mvn install
4. Create a PostgreSQL database and configure the connection in the application.properties file
5. Run the application with mvn spring-boot:run

## Thank you
- Thank you everyone who contributed to the development of this project, especially Skillbox developers who provided frontend part of the project.
- Special thanks to the developers of the technologies used - Java, Spring Framework,	Spring Boot, Spring Data JPA,	Spring Security,	PostgreSQL,	Maven, OAuth 2.0,	JWT!
