FROM amazoncorretto:17.0.5

LABEL version="2.0"

EXPOSE 8080:8080

WORKDIR /app

COPY target/movie-review-service-0.0.1-SNAPSHOT.jar /app/movie-review.jar

ENTRYPOINT ["java", "-jar", "movie-review.jar"]