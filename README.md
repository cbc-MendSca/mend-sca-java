
# Task

* Create an API server which can analyse content of big XML file
* Create distributable docker container running the server
* The files are based on stack overflow site with data per topic ([805KB](https://s3-eu-west-1.amazonaws.com/merapar-assessment/3dprinting-posts.xml), [71KB](https://s3-eu-west-1.amazonaws.com/merapar-assessment/arabic-posts.xml))


# Mvn run
```bash
$ export MAVEN_OPTS="-Xms16m -Xmx32m"
$ mvn dependency:tree
$ mvn spring-boot:run
```
# Docker run
```bash
$ docker run -e "JAVA_OPTS=-Xms16m -Xmx32m" -p 8080:8080 -t yolkhovyy/large-xml
```

# Test curls
```bash
$ curl -i -X POST -H "Content-Type:application/json" -d '{"url":"https://s3-eu-west-1.amazonaws.com/merapar-assessment/arabic-posts.xml"}' 'http://localhost:8080/analyze/'
$ curl -i -X POST -H "Content-Type:application/json" -d '{"url":"https://s3-eu-west-1.amazonaws.com/merapar-assessment/3dprinting-posts.xml"}' 'http://localhost:8080/analyze/'
```

