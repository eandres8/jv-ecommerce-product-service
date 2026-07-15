# --- ETAPA 1: BUILD (The construction)
FROM gradle:9.5.1-jdk21-alpine AS builder
WORKDIR /app
COPY . .
# Ejecutamos la construcción limpia omitiendo las pruebas
RUN ./gradlew clean build -x test

# --- ETAPA 2: RUNTIME (The final product)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Gradle genera el .jar dentro de build/libs/
COPY --from=builder /app/build/libs/*.jar app.jar
# EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]