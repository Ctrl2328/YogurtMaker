# 🥛 Yogurt Maker API RESTful

![Spring Boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)
![Java](https://img.shields.io/badge/Java_17-ED8B00?style=for-the-badge&logo=java&logoColor=white)

API RESTful desarrollada en Spring Boot para la gestión de fórmulas y producción de yogurt.

## 🚀 Arquitectura
Este proyecto implementa una arquitectura en capas:
* **Controllers:** Exposición de endpoints HTTP.
* **Services:** Lógica de negocio.
* **Repositories:** Persistencia con Spring Data JPA.
* **Entities:** Modelado de datos.

## 📖 Manual de Ejecución
1. Clonar este repositorio.
2. Abrir en el IDE y actualizar las dependencias de Maven.
3. Ejecutar la aplicación (`mvn spring-boot:run`).
4. Acceder a Swagger UI en: `http://localhost:8080/swagger-ui.html`

## 📸 Evidencias
* El archivo `openapi.json` se encuentra en la raíz del repositorio.
* El diagrama UML se encuentra en el archivo `diagrama.md`.
* Las capturas de prueba HTTP están en el informe técnico en formato PDF.