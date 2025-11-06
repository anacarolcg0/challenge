FROM gradle:jdk21-graal AS build
WORKDIR /usr/app/
COPY . .
RUN gradle clean build -x test --no-daemon

FROM eclipse-temurin:21-jdk-jammy
WORKDIR /usr/app/
COPY --from=build /usr/app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]