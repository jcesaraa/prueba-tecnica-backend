# Product Service
Microservicio para la gestión de productos, desarrollado con Java y Spring Boot.

## Configuración principal (application.yml o application.properties)
* Puerto: 8080
* Context Path: /api (todos los endpoints comienzan con /api)
* Base de datos: H2 en memoria (jdbc:h2:mem:productdb)
* JPA: Hibernate actualiza el esquema automáticamente (ddl-auto: update)
* API Key: Configurada para autenticación interna entre microservicios
* Actuator: Exposición de endpoints para health, info y métricas
* Logging: Configurado para mostrar logs informativos y debug

## Estructura del proyecto
* config/ – Configuraciones (Swagger, RestTemplate, seguridad YAML)
* controller/ – Controladores REST
* model/ – Entidades JPA
* repository/ – Repositorios Spring Data JPA
* service/ – Lógica de negocio
* exception/ – Manejo de excepciones personalizado
* health/ – Health Indicators personalizados

## Cómo ejecutar el servicio
### Requisitos previos
* Java 17 instalado (java -version)
* Maven instalado (mvn -v)

### Pasos para ejecutar
1. Clona o descarga el repositorio.
2. Abre una terminal en la carpeta product-service.
3. Ejecuta: mvn clean spring-boot:run
4. El microservicio se levantará en http://localhost:8080/api.

## Endpoints principales
* GET /api/productos – Lista productos paginados
* POST /api/productos – Crear un producto nuevo
* GET /api/productos/{id} – Obtener producto por ID
* PUT /api/productos/{id} – Actualizar producto
* DELETE /api/productos/{id} – Eliminar producto

## Documentación API (Swagger UI)
Para consultar la documentación interactiva accede a:
http://localhost:8080/swagger-ui/index.html

## Monitorización
Los endpoints de actuator están disponibles en:
* http://localhost:8080/actuator/health
* http://localhost:8080/actuator/info
* http://localhost:8080/actuator/metrics
