#Shortener-App

This application creates a short alias or pointer to a longer page link when the application is running the endpoint's documentation
are in a swagger component like in the link below

http://127.0.0.1:10222/swagger-ui.html

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)   
- [Docker](https://www.docker.com/)
- [MySql](https://hub.docker.com/_/mysql)

## Running the application locally

There are several ways to execute this application on your local machine.
One way is to execute the main method in the 
`com.shortener.jaedmono.JaedmonoApplication` class from your IDE. 

Alternatively you can use the [Spring-Boot-Gradle-Plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins.html#build-tool-plugins-gradle-plugin) like so:

```shell
./gradlew bootRun
```

This way needs a MySql data base running, you can execute and run a Mysql docker image running this command.

```shell
$ docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql:tag
```

Finally, you can use Docker compose to run the data base and the application.

```shell
docker-compose up
```

## Endpoints deployed

There are two main endpoints exposed one to create the shorted link,
and the second to allows the shorted link access to the long link.

First endpoint POST Method.

Request Body:
```shell
{
  "url": "https://www.linkedin.com/notifications/"
}
```


Response Body:
```shell
{
  "url": "http://localhost:10222/url/yGJj8B"
}
```

Second endpoint GET method: `http://localhost:10222/url/yGJj8B`

