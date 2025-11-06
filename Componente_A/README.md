# Componente A - API de Gestión

API REST para gestión de clientes y proveedores desarrollada con Spring Boot.

## 🚀 Características

- **CRUD de Clientes**: Crear, leer, actualizar y eliminar clientes
- **CRUD de Proveedores**: Gestión completa de proveedores
- **Códigos únicos autogenerados**: Cada entidad tiene un código único
- **Validación de datos**: Validaciones con Bean Validation
- **Base de datos H2**: Base de datos en memoria para desarrollo
- **Documentación OpenAPI 3**: Swagger UI integrado

## 🛠️ Tecnologías

- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- MariaDB Database
- SpringDoc OpenAPI 3
- Lombok
- Maven

## 📋 Requisitos Previos

- JDK 17 o superior
- Maven 3.6 o superior
- MariaDB 10.6 o superior en ejecución

## 🔧 Instalación y Ejecución

### 1. Configurar MariaDB

```sql
-- Crear la base de datos
CREATE DATABASE componenteadb;

-- Opcional: Crear usuario específico
CREATE USER 'componentea'@'localhost' IDENTIFIED BY 'componentea';
GRANT ALL PRIVILEGES ON componenteadb.* TO 'componentea'@'localhost';
FLUSH PRIVILEGES;
```

### 2. Compilar el proyecto

```powershell
mvn clean install
```

### 3. Ejecutar la aplicación

```powershell
mvn spring-boot:run
```

La aplicación se ejecutará en: `http://localhost:8081`

## 📚 Documentación API

Una vez iniciada la aplicación, accede a la documentación Swagger:

- **Swagger UI**: http://localhost:8081/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8081/api-docs

## 🗄️ Acceso a la Base de Datos

Puedes conectarte a MariaDB con:

- **Host**: localhost
- **Puerto**: 3306
- **Database**: componenteadb
- **Usuario**: root
- **Contraseña**: root

## 📡 Endpoints Principales

### Clientes

- `POST /api/clientes` - Crear cliente
- `GET /api/clientes` - Obtener todos los clientes
- `GET /api/clientes/{id}` - Obtener cliente por ID
- `GET /api/clientes/codigo/{codigo}` - Obtener cliente por código
- `GET /api/clientes/validacion/existe/{codigo}` - Validar existencia
- `PUT /api/clientes/{id}` - Actualizar cliente
- `DELETE /api/clientes/{id}` - Eliminar cliente

### Proveedores

- `POST /api/proveedores` - Crear proveedor
- `GET /api/proveedores` - Obtener todos los proveedores
- `GET /api/proveedores/{id}` - Obtener proveedor por ID
- `GET /api/proveedores/codigo/{codigo}` - Obtener proveedor por código
- `GET /api/proveedores/validacion/existe/{codigo}` - Validar existencia
- `PUT /api/proveedores/{id}` - Actualizar proveedor
- `DELETE /api/proveedores/{id}` - Eliminar proveedor

## 📝 Ejemplo de Uso

### Crear un Cliente

```bash
curl -X POST http://localhost:8081/api/clientes \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Juan Pérez",
    "nit": "12345678-9",
    "email": "juan@example.com",
    "telefono": "12345678",
    "direccion": "Ciudad de Guatemala"
  }'
```

## 👥 Autor

Proyecto desarrollado para el Examen Final - UMG
