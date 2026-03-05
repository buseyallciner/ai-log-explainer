# AI Log Explainer (Spring Boot)

This is a small Spring Boot REST API I built to experiment with log analysis and simple backend endpoints.

The idea is simple: send an error log to the API and get a possible explanation or suggestion about what might be wrong.

I mainly built this project to practice:
- Spring Boot REST controllers
- building small APIs
- using Git and GitHub in a real project

## Endpoints

GET /hello  
Simple test endpoint.

GET /health  
Health check endpoint.

POST /explain  
Accepts a log message and returns a possible explanation.

Example request:

{
  "log": "NullPointerException at UserService.java:42"
}

Example response:

{
  "possibleCause": "A null object might be used in UserService",
  "suggestion": "Check if the object is initialized before calling methods on it."
}
