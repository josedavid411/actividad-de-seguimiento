FROM eclipse-temurin:25-jdk

WORKDIR /app

COPY src/main/java/ ./src/

RUN mkdir -p classes \
    && find src -name "*.java" -print0 | xargs -0 javac --release 24 -d classes

VOLUME ["/data"]

WORKDIR /data

CMD ["java", "-cp", "/app/classes", "org.example.Main"]
