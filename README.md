# Examen Final - Arquitectura de Microservicios con Spring Boot

Proyecto del examen final que implementa una arquitectura de microservicios integrados usando Spring Boot, Feign Client y flujos circulares de comunicación.

## 📁 Estructura del Proyecto

```
Examen/
├── Componente_A/          # API REST de Gestión (Clientes y Proveedores)
├── Componente_B/          # API REST de Logística (Integración)
└── Componente_C/          # Librería de Utilidades (Dependencia Maven)
```

## 🏗️ Arquitectura

### Componente C - Librería de Utilidades (Puerto: N/A - JAR)
- **Tipo**: Librería Maven (JAR)
- **Función**: Proveer utilidades de cálculo y generación de códigos
- **Características**:
  - Cálculo de totales con IVA (12%)
  - Generación de códigos únicos con UUID
  - Método para invocar validaciones remotas (flujo circular)

### Componente A - API de Gestión (Puerto: 8081)
- **Tipo**: API REST con Spring Boot
- **Función**: Gestión de clientes y proveedores
- **Características**:
  - CRUD completo de Clientes
  - CRUD completo de Proveedores
  - Validación de existencia de entidades
  - Base de datos H2 en memoria
  - Documentación OpenAPI 3 / Swagger

### Componente B - API de Logística (Puerto: 8082)
- **Tipo**: API REST con Spring Boot
- **Función**: Orquestador de servicios de logística
- **Características**:
  - Usa Componente C como dependencia Maven
  - Consume Componente A mediante Feign Client
  - Cálculo de totales de productos
  - Generación de códigos únicos
  - Validación remota de clientes/proveedores
  - Flujo circular de integración
  - Documentación OpenAPI 3 / Swagger

## 🔄 Flujo de Integración

```
┌─────────────────┐
│  Componente B   │
│  (Puerto 8082)  │
└────────┬────────┘
         │
         ├─── Usa como dependencia ──→ Componente C (JAR)
         │
         └─── Consume via Feign ────→ Componente A (Puerto 8081)
                                               │
                                               └─── Puede ser invocado por ──→ Componente C
                                                     (Flujo Circular)
```

## 🚀 Orden de Ejecución

### 1. Compilar e Instalar Componente C

```powershell
cd Componente_C
mvn clean install
```

Este paso instala el Componente C en tu repositorio Maven local para que el Componente B pueda usarlo como dependencia.

### 2. Iniciar Componente A

```powershell
cd ..\Componente_A
mvn spring-boot:run
```

El Componente A se ejecutará en `http://localhost:8081`

### 3. Iniciar Componente B

```powershell
cd ..\Componente_B
mvn spring-boot:run
```

El Componente B se ejecutará en `http://localhost:8082`

## 📚 Documentación de APIs

Una vez iniciados los componentes, accede a la documentación:

### Componente A
- **Swagger UI**: http://localhost:8081/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8081/api-docs
- **Consola H2**: http://localhost:8081/h2-console

### Componente B
- **Swagger UI**: http://localhost:8082/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8082/api-docs

## 🧪 Pruebas de Integración

### 1. Crear un Cliente en Componente A

```powershell
curl -X POST http://localhost:8081/api/clientes -H "Content-Type: application/json" -d '{\"nombre\":\"Juan Perez\",\"nit\":\"12345678-9\",\"email\":\"juan@example.com\",\"telefono\":\"12345678\",\"direccion\":\"Guatemala\"}'
```

Respuesta: Retorna el cliente creado con su código único (ej: `CLI-A1B2C3D4`)

### 2. Calcular Total de Productos (Componente B usa Componente C)

```powershell
curl -X POST http://localhost:8082/api/logistica/calcular-total -H "Content-Type: application/json" -d '{\"productos\":[{\"nombre\":\"Laptop\",\"precioUnitario\":5000.00,\"cantidad\":2},{\"nombre\":\"Mouse\",\"precioUnitario\":150.00,\"cantidad\":5}]}'
```

Respuesta: Total con IVA calculado usando la librería del Componente C

### 3. Validar Cliente desde Componente B (Feign Client)

```powershell
curl http://localhost:8082/api/logistica/validar-cliente/CLI-A1B2C3D4
```

Respuesta: Resultado de validación obtenido del Componente A mediante Feign Client

### 4. Demostrar Flujo Circular

```powershell
curl "http://localhost:8082/api/logistica/flujo-circular?idEntidad=CLI-A1B2C3D4&urlBase=http://localhost:8081"
```

Respuesta: Resultado de la invocación circular donde Componente C llama de vuelta al Componente A

## 🛠️ Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Cloud OpenFeign** (para consumo de APIs)
- **Spring Data JPA** (Componente A)
- **H2 Database** (Componente A)
- **SpringDoc OpenAPI 3** (Swagger UI)
- **Lombok** (reducir boilerplate)
- **Maven** (gestión de dependencias)

## 📊 Endpoints Principales

### Componente A - Gestión
- `POST /api/clientes` - Crear cliente
- `GET /api/clientes` - Listar clientes
- `GET /api/clientes/validacion/existe/{codigo}` - Validar cliente
- `POST /api/proveedores` - Crear proveedor
- `GET /api/proveedores` - Listar proveedores
- `GET /api/proveedores/validacion/existe/{codigo}` - Validar proveedor

### Componente B - Logística
- `POST /api/logistica/calcular-total` - Calcular total con IVA
- `POST /api/logistica/generar-codigo/{tipo}` - Generar código único
- `GET /api/logistica/clientes` - Obtener clientes (via Feign)
- `GET /api/logistica/proveedores` - Obtener proveedores (via Feign)
- `GET /api/logistica/validar-cliente/{codigo}` - Validar cliente (via Feign)
- `GET /api/logistica/flujo-circular` - Demostrar flujo circular

## 📝 Commits Semánticos

El proyecto sigue la convención de **Conventional Commits**:

- `feat:` - Nueva funcionalidad
- `fix:` - Corrección de bugs
- `docs:` - Documentación
- `chore:` - Tareas de mantenimiento
- `refactor:` - Refactorización de código
- `test:` - Pruebas

### Ver Historial de Commits

```powershell
# Componente A
cd Componente_A
git log --oneline

# Componente B
cd ..\Componente_B
git log --oneline

# Componente C
cd ..\Componente_C
git log --oneline
```

## ⚠️ Notas Importantes

1. **Orden de inicio**: Debes iniciar primero el Componente A y luego el Componente B
2. **Componente C**: Debe estar compilado e instalado en Maven local antes de compilar el Componente B
3. **Feign Client**: Asegúrate de que el Componente A esté corriendo cuando uses endpoints del Componente B que lo requieran

## 👥 Autor

Proyecto desarrollado para el Examen Final - Universidad Mariano Gálvez de Guatemala (UMG)

## 📄 Licencia

Este proyecto es parte de un examen académico.
