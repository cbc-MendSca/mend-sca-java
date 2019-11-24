
# Task

* Create an API server which can analyse content of big XML file
* Create distributable docker container running the server (TODO)

# Example files:
The files are based on stack overflow site with data per topic.
* [805Kb](https://s3-eu-west-1.amazonaws.com/merapar-assessment/3dprinting-posts.xml)
* [71Kb](https://s3-eu-west-1.amazonaws.com/merapar-assessment/arabic-posts.xml)

# Spring Boot
See [example](https://docs.spring.io/spring-boot/docs/current/reference/html/getting-started.html#getting-started-first-application)

# Mvn
## Run
```bash
$ export MAVEN_OPTS="-Xms16m -Xmx32m"
$ mvn dependency:tree
$ mvn spring-boot:run
```

# Docker
## Package
```bash
$ mvn package
```
## Build/push
See [example](https://spring.io/guides/gs/spring-boot-docker/)
```bash
$ mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)
$ docker build -t yolkhovyy/large-xml .
$ docker push yolkhovyy/large-xml
```
## Run
```bash
$ docker run -e "JAVA_OPTS=-Xms16m -Xmx32m" -p 8080:8080 -t yolkhovyy/large-xml
```

# Test
## Curl
```bash
$ curl -i -X POST -H "Content-Type:application/json" -d '{"url":"https://s3-eu-west-1.amazonaws.com/merapar-assessment/arabic-posts.xml"}' 'http://localhost:8080/analyze/'
$ curl -i -X POST -H "Content-Type:application/json" -d '{"url":"https://s3-eu-west-1.amazonaws.com/merapar-assessment/3dprinting-posts.xml"}' 'http://localhost:8080/analyze/'
```

## Integration, etc
See [example](https://spring.io/guides/gs/testing-web/)
