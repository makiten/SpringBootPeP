FROM maven:3.6.0-jdk-8-alpine as builder
WORKDIR /app
RUN git clone https://github.com/makiten/SpringBootPeP.git .
RUN mvn clean install

# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
FROM openjdk:8-jdk-alpine
RUN apk --no-cache add ca-certificates
VOLUME /tmp
COPY --from=builder /app/target/SpringBootMySQL-0.1.0.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]

