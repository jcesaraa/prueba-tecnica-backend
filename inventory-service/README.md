# Inventory Service
Microservicio para la gestión de inventario, desarrollado con Java y Spring Boot.

## Configuración principal (application.yml)
* Puerto: 8081 
* Context Path: /api (todos los endpoints comienzan con /api)
* Base de datos: H2 en memoria (jdbc:h2:mem:inventorydb)
* JPA: Hibernate actualiza el esquema automáticamente (ddl-auto: update)
* Servicio Productos: Configurado con URL y API Key para llamadas internas
* Actuator: Se exponen endpoints de health, info y métricas para monitoreo
* Logging: Logs informativos para Spring y detallados para el paquete del servicio

## Estructura del proyecto
* config/ - Configuraciones del servicio (Swagger, RestTemplate, YAML seguro)
* controller/ - Controladores REST que exponen la API 
* model/ - Entidades JPA que representan datos persistentes 
* repository/ - Interfaces para acceso a base de datos con Spring Data JPA 
* service/ - Lógica de negocio
* exception/ - Manejo de excepciones personalizadas
* health/ - Indicadores personalizados para Actuator

## Cómo ejecutar el servicio

### Requisitos previos
* Java 17 instalado y configurado (java -version)
* Maven instalado y configurado (mvn -v)

### Pasos para levantar el servicio
1. Clonar o descargar el repositorio.
2. Abrir terminal en la carpeta inventory-service.
3. Ejecutar el comando: mvn clean spring-boot:run 
4. El servicio arrancará en http://localhost:8081/api. 
5. Puedes probar la API con herramientas como Postman o curl.

## Endpoints principales
* GET /api/inventory - Lista los items de inventario (paginado)
* POST /api/inventory - Crea un nuevo item
* GET /api/inventory/{id} - Obtiene un item por ID
* PUT /api/inventory/{id} - Actualiza un item existente
* DELETE /api/inventory/{id} - Elimina un item por ID

## Documentación API (Swagger UI)
Para acceder a la documentación interactiva, abre:
http://localhost:8081/swagger-ui/index.html

## Monitorización
Los endpoints de monitorización están disponibles en:
* http://localhost:8081/actuator/health
* http://localhost:8081/actuator/info
* http://localhost:8081/actuator/metrics

