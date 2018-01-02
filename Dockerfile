FROM maven:3-alpine as builder
ADD ./ app/
WORKDIR app/
RUN mvn -s settings-nexus.xml clean install -U package

# SonarScanner Stage , Upload the coverage and test reports to the sonar server
FROM eigdevstack/sonar-scanner
COPY --from=builder app/ /usr/src/myapp
WORKDIR /usr/src/myapp
RUN sonar-scanner -Dsonar.host.url=https://sonarqube.dstack.tech

FROM openjdk:8-jre-alpine
MAINTAINER Author Name piyush.ma@endurance.com
VOLUME /tmp
COPY --from=builder app/target/emdb-1.0-SNAPSHOT.jar emdb-1.0-SNAPSHOT.jar
RUN sh -c 'touch emdb-1.0-SNAPSHOT.jar'
EXPOSE 8080 3306
ENTRYPOINT ["java","-jar","emdb-1.0-SNAPSHOT.jar"]
