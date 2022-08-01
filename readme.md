## Test tasks for Artplancom

### Warm up

Write a method that reverses a string and measure the running time of this method for 1000, 10,000, 100,000 repetitions.  
It should be issued in the form of a stand-alone java application with a console string input.  
The result of the work should be a string, an expanded string and 3 digits (working time).

#### Start *reverse-strings* app

Go to the project folder and run in terminal:  
<pre>java -jar reverse-strings-1.0.jar</pre>

##### Output:

<img src="https://i.imgur.com/naD1N2S.png" alt="Output" width="600px" height="200px"/>

---

### Main task

Write a set of services for a SOA WEB application. The application must implement the following services:
- Creating a user with registration. A user must be created with a username and password. The name must be unique. Immediately after creation, the current user must log in with the same request.
- A non-registered user should be able to check the availability of the name through the service (validations).
- The user created in the system must be able to log in by passing the name and password to the service. The number of unsuccessful authorization attempts - should not exceed 10 in 1 hour and reset upon successful authorization.

An authorized user must be able to:
- create / edit / delete animals:
  - ID
  - date of birth (ISO 8601)
  - gender
  - nickname (unique)
- get a list of your animals.
- get details of any animal by id. 

All interaction must be done using JSON data format.  
All errors must have numbers and text decoding.
Errors, in case of occurrence, should also be transmitted as a JSON object.  
As a database, you can take PostgreSQL, Mongo or any InMemory database (but then the jar must be added to the archive).  

It is recommended to use Spring and Hibernate (it is possible with JPA).