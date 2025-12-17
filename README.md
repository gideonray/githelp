### Overview
GitHelp is a Spring Boot REST service that retrieves a GitHub user and their public repos and returns a normalized response.

The service has the following endpoint:

GET /api/users/{username}

It calls the following public GitHub API:
- GET /users/{username}
- GET /users/{username}/repos

It returns a normalized response that includes some user information and their repos.

#### Example response:
```
{
  "user_name": "gideonray",
  "display_name": "Gideon",
  "avatar": "https://avatars.githubusercontent.com/u/7468296?v=4",
  "geo_location": "United States",
  "email": null,
  "url": "https://api.github.com/users/gideonray",
  "created_at": "Fri, 02 May 2014 16:05:50 GMT",
  "repos": [
    {
      "name": "docker-nginx",
      "url": "https://api.github.com/repos/gideonray/docker-nginx"
    },...
  ]
}
```

---
### Package structure
The project is split by responsibility to keep concerns separate and easy to follow:

- api  
  - Contains the REST controller, service, API DTOs, and mapping logic.  
  - This layer defines the public contract of the service.

- github  
  - Contains the GitHub client, its configuration, and DTOs that match GitHub’s API.  
  - This is the only place that knows about GitHub HTTP details.

---

### Error handling
Errors from the GitHub API are handled in the GitHub client and translated into meaningful exceptions.

- If the GitHub user does not exist, the service returns 404.
- If GitHub rate limits or rejects the request, the service returns 503.
- Other GitHub failures are treated as upstream errors and return 502.

Input validation errors, such as an invalid username, are handled at the API boundary and return 400.

---

## Input validation
The username path variable is validated at the controller boundary using Bean Validation:
- must not be blank
- maximum length of 39 characters (based on Github)
- only alphanumeric characters and hyphens

Invalid input results in a 400 Bad Request.

---

## Running the application

### Prerequisites
- Java 17 or newer
- Gradle (wrapper included)

### Run locally
`./gradlew bootRun`

The service will start on:

http://localhost:8080

---

## Running tests
`./gradlew test`

Controller tests validate request handling and input validation.

---

## Using the API

### Get user info and repositories
`curl http://localhost:8080/api/users/GITHUB-USERNAME`
