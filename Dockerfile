
FROM openjdk:11
RUN apk add --no-cache tzdata
RUN apk add busybox-extras
ENV TZ Europe/Helsinki
ARG JAR_FILE=target/springhack-1.0.0.jar
COPY ${JAR_FILE} springhack-1.0.0.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","springhack-1.0.0.jar"]
