
# Task

* Create an API server which can analyse content of big XML file
* Create distributable docker container running the server (TODO)


# Example files:
The files are based on stack overflow site with data per topic.
* [805Kb](https://s3-eu-west-1.amazonaws.com/merapar-assessment/3dprinting-posts.xml)
* [71Kb](https://s3-eu-west-1.amazonaws.com/merapar-assessment/arabic-posts.xml)

# Curl
```bash
curl -i -X POST -H "Content-Type:application/json" -d '{"url":"https://s3-eu-west-1.amazonaws.com/merapar-assessment/arabic-posts.xml"}' 'http://localhost:8080/analyze/'
curl -i -X POST -H "Content-Type:application/json" -d '{"url":"https://s3-eu-west-1.amazonaws.com/merapar-assessment/3dprinting-posts.xml"}' 'http://localhost:8080/analyze/'
```

#Spring Boot
[Example](https://docs.spring.io/spring-boot/docs/current/reference/html/getting-started.html#getting-started-first-application)
