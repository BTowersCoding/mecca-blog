FROM openjdk:8-alpine

COPY target/uberjar/mecca-blog.jar /mecca-blog/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/mecca-blog/app.jar"]
