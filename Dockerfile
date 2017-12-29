FROM maven as builder
ADD ./ app/
WORKDIR app/
RUN mvn -s settings.xml clean package -DskipTests

FROM openjdk:8-jre-alpine
MAINTAINER Author Name piyush.ma@endurance.com
VOLUME /tmp
COPY --from=builder app/target/emdb-1.0-SNAPSHOT.jar emdb-1.0-SNAPSHOT.jar
RUN sh -c 'touch emdb-1.0-SNAPSHOT.jar'
EXPOSE 8080 3306
ENTRYPOINT ["java","-jar","emdb-1.0-SNAPSHOT.jar"]
