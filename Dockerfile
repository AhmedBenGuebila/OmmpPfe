FROM openjdk:11
EXPOSE 8089
ADD target/ommp-3.0.jar ommp-3.0.jar
ENTRYPOINT ["java", "-jar", "ommp-3.0.jar"]