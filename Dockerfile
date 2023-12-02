FROM openjdk:17
EXPOSE 8080
ADD target/mortgage-loans-service.jar mortgage-loans-service.jar
ENTRYPOINT ["java","-jar","mortgage-loans-service.jar"]