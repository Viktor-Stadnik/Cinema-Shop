# Cinema-Shop :cinema: <a id="anchor"></a>
### Project description:
It is a web application that simulates cinema's ticket-reservation system.
The application gives an opportunity purchasing a movie ticket and includes such functionality as
*registration*, *role authorization*, *authentication*, *CRUD operations*.

*Edpoints:*
- `[POST]   /register - registers a new user`
- - `For Admin role:`
- `[GET]    /users/by-email - returns user by email`
- `[POST]   /movies - creates a new movie`
- `[POST]   /cinema-halls - creates a new cinema hall`
- `[POST]   /movie-sessions - creates a new movie session`
- `[PUT]    /movie-sessions/{id} - updates a movie session`
- `[DELETE] /movie-sessions/{id} - deletes a movie session`
- - `For User role:`
- `[POST]   /orders/complete - completes an order`
- `[GET]    /orders - returns order history of user`
- `[PUT]    /shopping-carts/movie-sessions - adds a movie session to shopping cart`
- `[GET]    /shopping-carts/by-user - returns shopping cart of logged user`
- - `For Both role:`
- `[GET]    /movies - returns all movies`
- `[GET]    /cinema-halls - returns all cinema halls`
- `[GET]    /movie-sessions/available - returns all available movie sessions for the selected movie`

### Description:
This project represents N-tier architecture model that including *Model*, *DAO*, *Service*, *Controllers* and *DTO* layers.
This application based on *Spring* and *Hibernate* frameworks. *Hibernate* is used for CRUD operations with DB in DAO layer.
*Spring annotations* mark Dao, Service and Controller classes to create Spring Beans.
*Spring Security* part hashes passwords with BCrypt and also used  delegating roles, 
admin and users will have their own interface for managing and resolve their tasks.
*DTO* models let us transfer only the data that we need to share with the user interfaces.
Created `ResponseEntityExceptionHandler` exception report for front end part.
And used `javax.validation.constraints` for protection sending invalid data.

### Technologies have been used:
- Java 11 (Collections, Optional, Stream API)
- MySQL
- REST
- Spring (Core, MVC, Security)
- Hibernate (Fetch types, Criteria Query, Relations)
- Tomcat
- Maven
- Lombok
### Package model:

<img src="https://user-images.githubusercontent.com/90702060/192108862-542eaefc-6a25-46fc-9d6a-86fb6b0d4517.png">

### How to run this application:
1. Fork this project
2. Install MySQL and create a schema.
3. Install [Tomcat 9.0.58 version](https://tomcat.apache.org/download-90.cgi)
4. Write your credentials in the `resources/db.properties` file. (Be careful with adding URL. You should add a Timezone to it too)
5. Configure Tomcat.(Application context needs to be as "/")
6. Run this project using Tomcat's local server
7. You have already two registered users: 
   - **Admin:** *Email:* john@i.ua *Password:* john1234 
   - **User:**  *Email:* mery@i.ua *Password:* mery1234
8. To register new user send request to `localhost:8080` with body `/register` 
9. Use [Postman](https://www.postman.com) or other API to send HTTP-requests and receive HTTP-responses

[Up](#anchor)
