# spring-reddit-clone
Reddit clone built using Spring Boot, Spring Security with JPA Authentication, Spring Data JPA with MySQL, Spring MVC. The frontend is built using Angular - You can find the frontend source code here - https://github.com/SaiUpadhyayula/angular-reddit-clone

# Recent Changes

- Updated to latest spring boot version - 3.0.3
- Updated Spring Security to v6
- Replaced Spring Fox Rest api documentation library with OpenAPI 3


## Redit clone

- this is a spring boot application with spring mvc, spring security with JwT authentication, spring data JPA with mysql, and angular 9.
- The user can login and create a post or upvote or downvote the existing vote or he can be able to create subreddit and post and comment the posts.
- for the mail i will be using the fake mail server, mail trap. and all the mails that our app will be sending to this mail box.

# Dependencies

- spring web
- lombok
- spring security
- spring data JPA
- Mysql java driver
- java mail sender
- thymeleaf - this will enable us to create the html templates and we will those templates to send the emails
- spring boot starter oAuth2 resource server for JWT
- map struct - for the java bean mapping, to map b/w 2 objs and so we can use the mapping, and its very useful for mapping b/w our class and the dto.. the @mapping(src, target)
- time ago lib (which is a kotlin based library)

# Model Entities

- we ve several modal entities such as comment, notification email, post, refresh token, subreddit, user, verification token, vote and voteType.
- then we can create a repository to save these entities to the database, and these repositories are all extended from the JPA repository
- spring security's class called BcryptPasswordEncoder to encrypt the password
# JWT Authentication

- it follows 6 steps the 1. the client will send a request to the server
- 2. the server will create the JWT  and 3. send it to the client
4. then the client will use the JWT to verify itself and send it to the server
5. the server will then validate the token
6. finally response to the client

- lets see the following overview architectures of the JWtAuthentication in our app
- <image src = "./JWT auth mechanism" > 
- if we see this high level auth flow -> we ve the Auth service - which verifies the username and p/w 
- then it create an username p/w auth token obj and then pass it to the "auth Manager"
- this auth manager will use the userDetails interface which can fetch the usr from multiple different sources, in our case its just the database
- if the user details are correct, it will then pass to the auth manager,
- and the auth manager will finally returns the authentication obj to the auth service

- install the spring boot starter oAuth2 resource server dependency
- and to create the token JWT provider in the security provider (refer JWT .io site for more info about the jwt and how it works)
- as we know the encoded JWt contains 3 parts 1. header 2. payload 3. verification signature

- the 5 th point where the server will validate the token -> the actual proces looks like the client will send the request
  - the req contains the headers, bearer token, the req is intercepted by the filter called JWT auth filter.
  - this filter will retrieves the access token and then validates it.
  - if the validation is successful then the request will be forwarded to the corresponding controller

# Logout using the refresh token

- there are 4 possible ways we can implement the jwt inauthorization mechanism, 
  1. Delete the token from the browser 
  2. Introduce the expiration time for the JWT
  3. use the refresh token (the idea is to provide the user an additional token at the time of authentication, we can use this refresh token to gen a access token, whenever the access token is about to expire, so in this way we can just keep on rotating the access token and when the user logs out we can just delete this refresh token )
  4. Token Blacklisting
  - the ideal way is to implement the 3 and 4, but in our case we can go with the 3rd solution.

# Document our REST API

  - we can Document the REST API so its easy for the client to understand
  - improves user adoption
  - allows easy maintenance of the API

# FrontEnd 

- lets create a new angular project 
  - ```ng new angular-reddit-clone
  - bootstrap ```npm i --save @ng-bootstrap/ng-bootstrap```
  - ngx web storage ```npm i ngx-webstorage``` for the local storage, so we can store the loginResponse (which has the auth Token, user name, refresh token, and expiration )
  - ngx toastr ```npm i ngx-toastr``` for the notification
  - font awesome ```npm i @fortawesome/angular-fontawesome```for the icons
  - TinyMCE ```npm i --save @tinymce/tinymce-angular``` for editing the posts

# HttpInterceptor

- the concept of the interceptor is similar to the servlet filters in java 
- in our case the interceptor will intercepts each request thats going to the backend by adding the token infos to the header.
- and we also maintains the logic for the refresh tokens inside this interceptor
- if the client sends an expired token to the server the server responds back with the 403 error.
- and at that point request a new token using the interceptor.

# Testing (Junit and mockito)

- with the mockito we can mock our service, repository and other dependencies