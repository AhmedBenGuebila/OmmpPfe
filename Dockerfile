FROM openjdk:11
EXPOSE 8089
ADD target/ommpSpring-0.0.1-SNAPSHOT.jar ommpSpring-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "ommpSpring-0.0.1-SNAPSHOT.jar"]
