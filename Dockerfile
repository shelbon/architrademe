FROM maven:3-openjdk-18

WORKDIR /Architrademe
COPY . .
RUN mvn clean install

CMD mvn spring-boot:run