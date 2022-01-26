FROM adoptopenjdk/openjdk11:alpine-jre
WORKDIR /workspace/app
ADD target/*.jar app.jar
CMD java -jar ./app.jar