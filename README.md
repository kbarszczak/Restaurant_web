![banner (1)](https://user-images.githubusercontent.com/72699445/231725714-bae7accc-014e-41c9-a797-90a1ecef9112.png)

This project is a simple web application for restaurant services such as online orders and reservations. Moreover, the application is also an informational card for the restaurant. It is a complete system with a backend written in Java and a frontend written in Angular. The application provides the layout for a guest, user, and admin.

## Motivation

This project was made to learn the Angular framework and make me more familiar and skilled with the Java Spring Boot framework.

## Build Status

1. The backend is a read-to-use complete application
2. The frontend is not completely finished. There are missing layout features for guest and admin view. Moreover, the frontend source code may be significantly improved.

## Screenshots

The application layout (guest view):

![Screenshot 2023-04-13 124853](https://user-images.githubusercontent.com/72699445/231736627-38e5eecb-b89d-4652-9515-0185c44bf54a.png)

![Screenshot 2023-04-13 124908](https://user-images.githubusercontent.com/72699445/231736635-c94df424-71d0-410d-89f8-97a8f779730f.png)

## Tech/Framework used

The system uses:
1. For backend:
- Java 17
- Java Spring Boot 3.0.1
- Lombok library 1.18.24
- JSON Web Token library 0.9.1
- Jaxby XML API 2.3.1
- MongoDB
2. For frontend:
- Typescript, CSS, HTML
- Angular 

## Features

The key features of this application:
- JWT authentication method
- CORS configured to apply only certain requests

## Installation

1. First of all clone the repository:
```
mkdir restaurant
cd restaurant
git clone https://github.com/kbarszczak/Restaurant_web .
```

2. The next step is to download all dependencies and create jar file for the backend:
```
cd restaurant_backend
mvn clean install
```

3. Now create a docker image for the backend app:
```
docker build -t restaurant-api .
```

4. The last thing in the backend setup is to run all required images:
```
docker compose up -d
```

From now on the MongoDB, Mongo Express, and the restaurant backend are set up and accessible under the specified in the docker-compose.yml file ports.

5. The next step is to run the frontend app. To do so do the following:
```
cd ../restaurant_frontend
npm install
ng serve --port 9091
```

Henceforth our restaurant is accessible under the url: [localhost](http://localhost:9091/)

## Contribute
- clone the repository
- either make the changes or implement missing code
- create the pull request with a detailed description of your changes
