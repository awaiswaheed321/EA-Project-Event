FROM openjdk:23

VOLUME /tmp
COPY . tmp/target/
ADD target/EA-2024-EMS.jar app.jar
RUN sh -c 'touch /app.jar'
EXPOSE 8080
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]