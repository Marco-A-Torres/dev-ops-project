# Imagen base con JDK
FROM openjdk:17

# Establecer directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el archivo .java al contenedor
COPY califModulares.java .

# Compilar el programa
RUN javac califModulares.java

# Ejecutar el programa
CMD ["java", "califModulares"]
