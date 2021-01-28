Cake Manager Micro Service (fictitious)
=======================================

Welcome to the cake manager API microservice!

This is a RESTfull web API used to get and collect info on cakes.

It is my attempt at demonstrating a totally sand alone back end service that can be consumed in a variety of ways
and adheres to Richardson's maturity model for API'S.

Technology Used
================

-Spring Boot
-Spring Data
-Spring HATEOS
-Thymeleaf
-Hibernate
-Docker
-Travis CI
-HSQL In-Memory Database

Project Setup
==============
-Create a local folder on your machine to host the api
-From the CMD prompt
    - ~ Git init ~ - to initialize the folder as an empty Git Repo
    - ~ Git Clone <InsertProjectLink> ~ - copy the Project into the repo
    - ~ mvn clean install ~ - to install the needed dependencies
    - ~ mvn spring-boot:run ~ - to start the application on localhost 8080

-Alternatively all of the above can be done from your chosen IDE

API USAGE
===========
There are several ways to access and use the service.

You can use the following curl commands from a terminal.
    - This command will return a JSON payload of all cakes in the system
        ~ $ curl -v localhost:8080/cakes | json_pp
    - This command will return a specified cake based on the id supplied to it
        ~ $ curl -v localhost:8080/cakes/{id} | json_pp
    - This command will send a POST request, creating a new cake in the DB
        ~ $ curl -X POST localhost:8080/cakes -H 'Content-type:application/json' -d '{"title": "Insert your cake name", "desc": "Insert your cake description", "image": "Insert your cake image url"}'
    - This command is the same as the previous, but the path has a cake id added and can update existing entries
        ~ $ curl -X PUT localhost:8080/cakes/3 -H 'Content-type:application/json' -d '{"title": "Insert your cake name", "desc": "Insert your cake description", "image": "Insert your cake image url"}'
    - This command will simply delete a cake by the id supplied
        ~ $ curl -X DELETE localhost:8080/employees/3

You can also use a web browser of your choice at access the following api endpoints

    - This url path wil take you to the Thymeleaf homepage - http://www.localhost:8080/
    - This url path wil return a JSON body of all cakes and their meta data - http://www.localhost:8080/cakes
    - This url path wil take you to the specific cake specified by id - http://www.localhost:8080/cakes/{id}

Alternatively you can use a tool such as Postman to test out and browse the service.

Also if you would like to see an example of a Client side application consuming this service, then you can additionally
visit https://github.com/MJCRobertson/cake-manager-angular-ui and following the Readme instructions to set up the Cake Manager Angular front end. This will let you see
potential ways of consuming the service.

Additional Info
================
This project is available on Docker at https://hub.docker.com/r/mjcrobertson/cake-manager

Additionally this project has been integrated with Travis for continuous integration and will run a build everytime a commit is made.

Future Improvements
====================
- Increase Test Coverage - Had several issues relating to the testing of the endpoints and the data they return.
- AuthO2 Integration - Would like to implement security on the service itself, the thymeleaf view, and then figure out how to add this to a front end
  for a whole stack flow.
- A few niggling tidy ups with naming, checking that the changes don't cause any cascading errors/bugs.
- Add the docker image upload into the CI/CD pipeline with Travis
- Add in another entity to make use of the Spring HATEOS features as currently they don't add very much functionality other than checking that box

