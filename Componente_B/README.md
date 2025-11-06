# Componente B - API de Logística

API REST que integra servicios de logística usando el Componente C y consume el Componente A mediante Feign Client.

## 🚀 Características

- **Cálculo de totales**: Calcula totales de productos con IVA usando Componente C
- **Generación de códigos**: Genera códigos únicos para diferentes entidades
- **Integración con Componente A**: Consume APIs mediante Feign Client
- **Validación remota**: Valida clientes y proveedores del Componente A
- **Flujo circular**: Implementa flujo circular de integración entre componentes
- **Documentación OpenAPI 3**: Swagger UI integrado

## 🛠️ Tecnologías

- Java 17
- Spring Boot 3.2.0
- Spring Cloud OpenFeign
- Spring Data JPA
- PostgreSQL Database
- SpringDoc OpenAPI 3
- Lombok
- Maven
- Componente C (Dependencia local)

## 📋 Requisitos Previos

- JDK 17 o superior
- Maven 3.6 o superior
- PostgreSQL 13 o superior en ejecución
- **Componente C compilado e instalado** en el repositorio local Maven
- **Componente A en ejecución** en http://localhost:8081

## 🔧 Instalación y Ejecución

### 1. Configurar PostgreSQL

```sql
-- Crear la base de datos
CREATE DATABASE componentebdb;

-- Opcional: Crear usuario específico
CREATE USER componenteb WITH PASSWORD 'componenteb';
GRANT ALL PRIVILEGES ON DATABASE componentebdb TO componenteb;
```

### 2. Instalar Componente C en Maven local

Primero, navega al directorio del Componente C e instálalo:

```powershell
cd ..\Componente_C
mvn clean install
```

### 2. Compilar el Componente B

```powershell
cd ..\Componente_B
mvn clean install
```

### 4. Ejecutar la aplicación

```powershell
mvn spring-boot:run
```

La aplicación se ejecutará en: `http://localhost:8082`

## 📚 Documentación API

Una vez iniciada la aplicación, accede a la documentación Swagger:

- **Swagger UI**: http://localhost:8082/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8082/api-docs

## 📡 Endpoints Principales

### Cálculos y Generación (Componente C)

- `POST /api/logistica/calcular-total` - Calcular total de productos con IVA
- `POST /api/logistica/generar-codigo/{tipoEntidad}` - Generar código único

### Integración con Componente A (Feign Client)

- `GET /api/logistica/clientes` - Obtener todos los clientes
- `GET /api/logistica/proveedores` - Obtener todos los proveedores
- `GET /api/logistica/clientes/{codigo}` - Obtener cliente por código
- `GET /api/logistica/proveedores/{codigo}` - Obtener proveedor por código
- `GET /api/logistica/validar-cliente/{codigo}` - Validar existencia de cliente
- `GET /api/logistica/validar-proveedor/{codigo}` - Validar existencia de proveedor

### Flujo Circular

- `GET /api/logistica/flujo-circular` - Ejecutar flujo circular de integración

## 📝 Ejemplos de Uso

### Calcular Total de Productos

```bash
curl -X POST http://localhost:8082/api/logistica/calcular-total \
  -H "Content-Type: application/json" \
  -d '{
    "productos": [
      {
        "nombre": "Laptop",
        "precioUnitario": 5000.00,
        "cantidad": 2
      },
      {
        "nombre": "Mouse",
        "precioUnitario": 150.00,
        "cantidad": 5
      }
    ]
  }'
```

### Generar Código Único

```bash
curl -X POST http://localhost:8082/api/logistica/generar-codigo/ORDEN
```

### Validar Cliente (Feign Client)

```bash
curl http://localhost:8082/api/logistica/validar-cliente/CLI-12345678
```

### Flujo Circular

```bash
curl "http://localhost:8082/api/logistica/flujo-circular?idEntidad=CLI-12345678&urlBase=http://localhost:8081"
```

## 🔄 Flujo de Integración

1. **Componente B** consume **Componente A** mediante Feign Client
2. **Componente B** utiliza **Componente C** para cálculos y generación de códigos
3. **Componente C** tiene un método que puede invocar de vuelta al **Componente A** (flujo circular)

## ⚠️ Notas Importantes

- Asegúrate de que el **Componente A** esté en ejecución antes de iniciar el Componente B
- El **Componente C** debe estar instalado en el repositorio Maven local
- Los Feign Clients están configurados para conectarse a `http://localhost:8081`

## 👥 Autor

Proyecto desarrollado para el Examen Final - UMG
