# Proyecto de Gestión de Pagos con Tarjetas de Crédito

Este proyecto es un microservicio de gestión de pagos de tarjetas de crédito.

## Tabla de Contenidos
- [Características](#características)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Instalación](#instalación)
- [Uso](#uso)
- [Estructura de Ramas (Gitflow)](#estructura-de-ramas-gitflow)
- [Documentación de la API](#documentación-de-la-api)

## Características
- Registro de pagos de tarjetas de crédito.
- Visualización de un listado de pagos existentes.
- Validación de datos mediante DTOs.
- Persistencia de datos en **PostgreSQL**.
- Documentación de la API con **Swagger**.
- Gestión del ciclo de vida del desarrollo con **Gitflow**.

## Tecnologías Utilizadas
- **Spring Boot**
- **PostgreSQL**
- **Swagger** para la documentación de la API
- **Maven** para la gestión de dependencias
- **Gitflow** para la gestión de versiones
- **Jakarta Persistence API (JPA)** para la persistencia de datos (en proceso de cambio para jdbc)

## Instalación

### Prerrequisitos
- Tener instalado **JDK 17+**.
- Tener instalado **PostgreSQL** y configurado con un usuario y base de datos.
- Tener **Maven** instalado para gestionar las dependencias.
- **Git** y **Gitflow** configurados.

### Pasos para ejecutar el proyecto

1. Clona este repositorio:

   ```bash
   git clone https://github.com/tu-usuario/nombre-del-repositorio.git
   cd nombre-del-repositorio

2. Configura la base de datos **PostgreSQL** en el archivo *application.properties*:

   ```bash
    spring.datasource.url=jdbc:postgresql://localhost:5432/nombre_db
    spring.datasource.username=usuario_db
    spring.datasource.password=contraseña_db

3. En caso de no tener un IDE que pueda manejar Maven y sus dependencias habria que abrir una terminal y luego ubicarse
   en la raiz del proyecto, donde **mvn** funcione, luego ejecutar el siguiente comando:

    ```bash
    mvn clean install
    mvn spring-boot:run

4. Si todo se ha ejecutado sin errores deberias poder acceder a la documentacion del proyecto en:

    ```bash
   http://localhost:8080/swagger-ui.html


## Uso

### Endpoints de la API

- **Registrar Pago:**
    - **POST** `/api/payments`
    - Cuerpo JSON:
      ```json
      {
        "cardNumber": "1234-5678-1234-5678",
        "amount": 100.50,
        "paymentDate": "2024-01-01",
        "description": "Pago mensual"
      }
      ```

- **Listar Pagos:**
    - **GET** `/api/payments`

## Estructura de Ramas (Gitflow)

Este proyecto sigue el flujo de trabajo de **Gitflow**, con las siguientes ramas principales:

- **`master`**: Contiene las versiones estables y lanzadas a producción.
- **`develop`**: Rama principal de desarrollo donde se integran nuevas funcionalidades antes de ser lanzadas a producción.
- **`feature/*`**: Ramas para el desarrollo de nuevas características o funcionalidades. Se crean a partir de `develop` y, al completarse, se integran de vuelta en `develop`.

## Documentación de la API

La API está documentada utilizando **Swagger**, lo que permite interactuar con los endpoints de forma fácil y visualizar los esquemas de las peticiones y respuestas.

### Acceder a la documentación

Una vez que la aplicación esté en funcionamiento, puedes acceder a la documentación interactiva de Swagger navegando a la siguiente URL:
`http://localhost:8080/swagger-ui.html`

### Ejemplo de los principales endpoints:

- **Registrar Pago** (`POST /api/payments`): Permite registrar un nuevo pago de tarjeta de crédito.
- **Listar Pagos** (`GET /api/payments`): Devuelve un listado de todos los pagos registrados, incluyendo el número de la tarjeta, la cantidad, la fecha y la descripción.

