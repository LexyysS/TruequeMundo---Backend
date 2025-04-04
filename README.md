# Trueque Mundo - Backend 🌍🤝

## Descripción del Proyecto

Trueque Mundo es un marketplace innovador que permite a los usuarios intercambiar productos y servicios, creando una comunidad basada en el trueque y el intercambio colaborativo.

## 📋 Especificaciones Técnicas

### Tecnologías y Versiones
- **Java**: Java 17
- **Spring Boot**: 3.4.3
- **Base de Datos**: MySQL
- **Gestor de Dependencias**: Maven

### Dependencias Principales
- Spring Boot Starter Web
- Spring Data JPA
- Spring Security
- MySQL Connector
- JWT Authentication
- Validación
- BCrypt para encriptación de contraseñas

## 🚀 Configuración del Proyecto

### Requisitos Previos
- JDK 17
- Maven 3.6+
- MySQL Server

### Clonar el Repositorio
```bash
git clone [URL del repositorio]
cd trueque-mundo-backend
```

## Importar Base de Datos

Se ha incluido un archivo `trueque_db.sql` en la raíz del proyecto para importar la estructura y datos iniciales de la base de datos:

1. Abrir MySQL Workbench
2. Conectarse a su servidor local
3. Ir a Server > Data Import
4. Seleccionar "Import from Self-Contained File"
5. Elegir el archivo `trueque_db.sql`
6. Seleccionar el esquema de destino o crear uno nuevo llamado `trueque_db`
7. Iniciar la importación

### Compilar el Proyecto
```bash
# Compilar sin ejecutar tests
mvn clean install -DskipTests

# Compilar con tests
mvn clean install
```

### Ejecutar la Aplicación
```bash
mvn spring-boot:run
```

## 🔧 Estructura del Proyecto
```
src/
├── main/
│   ├── java/
│   │   └── com/grupito2/
│   │       ├── controller/     # Controladores REST
│   │       ├── service/        # Lógica de negocio
│   │       ├── repository/     # Repositorios JPA
│   │       ├── model/          # Entidades
│   │       ├── security/       # Configuración de seguridad
│   │       └── dto/            # Data Transfer Objects
│   └── resources/
│       ├── application.properties
│       └── application-dev.properties
└── test/
    └── java/
        └── com/grupito2/
            └── [clases de prueba]
```

## 🔐 Seguridad
- **Autenticación**: JWT (JSON Web Tokens)
- **Encriptación**: BCrypt para contraseñas
- **Configuración**: Spring Security

## 🧪 Pruebas
```bash
# Ejecutar pruebas unitarias
mvn test
```

## 📦 Empaquetado
```bash
# Generar JAR para producción
mvn package
```

## 🚀 Despliegue
- Método recomendado: JAR ejecutable
- Comando: `java -jar target/Grupo2-0.0.1-SNAPSHOT.jar`

### Desarrolladores
| 👤 Nombre | 💻 Rol | 🔗 GitHub |
| --------------------- | -------- | -------------------------------------------- |
| Bruno Taibo                | FrontEnd | [GitHub](https://github.com/BrunoTA278) |
| Anaís Ferrada | FrontEnd   | [GitHub](https://github.com/Ana-H25) |
| Vincent Bruna | BackEnd    | [GitHub](https://github.com/ZNK21) |
| Alexis Hojas | FullStack    | [GitHub](https://github.com/LexyysS) |
| Natalia Peña | FullStack    | [GitHub](https://github.com/StudentNPD) |
