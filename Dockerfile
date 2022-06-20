FROM openjdk:8-jre-alpine

#EXPOSE 8080

COPY ./target/HelloProject*.jar /usr/app/
WORKDIR /usr/app

CMD java -jar HelloProject*.jar
