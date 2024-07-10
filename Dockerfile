FROM openjdk:11
EXPOSE 8089
ADD target/ommpSpring-0.0.1.jar ommpSpring-0.0.1.jar
ENTRYPOINT ["java", "-jar", "ommpSpring-0.0.1.jar"]
