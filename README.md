# Technical Test for Java Developer Position - [-----------]

**Version 1.0.0**

- Project uploaded at Heroku: [Movie's App API](https://movies-app-26-march.herokuapp.com/api/v1/movies) 

This repository contains source code for the RESTful API to manage a small movie rental 
---

## (This spring boot project uses Annotation Processing when imported in any IDE, it should be enable first!)
- This project uses Lombok, it should be installed in any IDEA that will be used to review it!

**Spring boot Commands to run it locally:**

> Build your project using the maven command line: 
```shell
  $ mvn clean
  $ mvn package
```
> Run the generated jar file
```shell
  $ mvn spring-boot:run
```

This API has Swagger UI, when run locally you can check it out at `https://localhost:9200/swagger-ui.html`

This app uses by default a dev profile, which connects and saves all our data into a in-memory database (H2)

However, if we need to use this in a production server, we should pass our production profile and database's credentials to connect our API.

To change it, we execute the following command:

```shell
$ java -jar movies-app-1.0.jar --spring.profiles.active=prod --host="{YOU_HOST_NAME}" --port="{YOUR_PORT}" --database="{YOUR_DATABASE_NAME}" --username="{USERNAME}" --password="{PASSWORD}" 
```

For Example:
```shell
$ java -jar movies-app-1.0.jar --spring.profiles.active=prod --host="192.168.1.1" --port="3306" --database="moviesdb" --username="root" --password="toor"
```
---
**Angular commands to run it locally:**

> First, we need to install our dependencies locally and then we run our app
```shell
  $ npm install
  $ ng serve
```
> We could also put another port
```shell
$ ng serve --open --port 4000
```

If port is not specified, it will run at 4200 by default.

- To edit and specify which URL should request our Restful API, we edit our **enviroments.ts** file: 

```javascript
export const environment = {
  production: false,
  HOST: 'https://localhost:9200/api/v1'
};
```
---
**To run ALL our monolith APP, follow the next steps (Also this takes a while to setup and run... might change this later, putting a jar file instead of letting maven to build it up):**

> Build our compose file and then we run all our containers  
```shell
$ docker-compose build
$ docker-compose up
```
> Or if we want to run it in background: 
```shell
$ docker-compose up -d
```

- Every service would be exposed in the follow URLs:
  1. Movie's Restful API: `https://localhost:9200`. (**Don't forget it's using SSL!**)
  2. Frontend App: `http://localhost:4000`
  3. MySql database Server: `http://localhost:3306`, Username: `root`, password: `toor`