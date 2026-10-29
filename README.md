# Gestión de Enfermedades – Corte 2

Proyecto correspondiente al **Corte 2** de la asignatura, desarrollado para el ejercicio de **Gestión de Enfermedades**.

La solución está compuesta por una aplicación móvil desarrollada en **Kodular** y una API REST desarrollada con **Spring Boot**, conectada a una base de datos PostgreSQL en línea.

## Autor

**Fabrizio Antonio D'Amato Osorio**

Universidad de Cartagena  
Ingeniería de Software  

Correo institucional:  
**fdamatoo@unicartagena.edu.co**

Ejercicio asignado: **Ejercicio 9 – Enfermedades**

---

## Tecnologías utilizadas

### Aplicación móvil

- Kodular
- Web Component
- TinyDB para almacenamiento local del token de sesión
- Sensor de acelerómetro
- Sensor de ubicación

### Backend

- Java 17
- Spring Boot
- Maven
- Spring Data JPA
- PostgreSQL
- JWT
- API REST
- Brevo para envío de correos electrónicos
- Docker

### Despliegue

La API se encuentra desplegada en Render:

`https://gestion-enfermedades-api.onrender.com`

---

## Arquitectura

El backend fue organizado siguiendo principios de **Arquitectura Hexagonal**, separando la lógica del dominio de los mecanismos de entrada, persistencia y servicios externos.

La estructura contempla:

- Domain
- Application
- Infrastructure
- Ports
- Adapters
- REST Controllers
- Persistence Adapters
- Security
- Email Service

---

## Entidad Usuario

Campos principales:

- username
- password
- nombre
- email

## Entidad Enfermedad

Campos principales:

- id
- nombre
- nombreCientifico
- nivelGravedad
- sintomas
- medicamentos
- esContagiosa
- esCubiertaPorPost
- requiereIncapacidad

---

## Autenticación

La aplicación permite iniciar sesión mediante usuario y contraseña.

Después de una autenticación correcta, la API genera un **token JWT**, que la aplicación almacena temporalmente mediante TinyDB.

Las solicitudes protegidas utilizan el encabezado:

`Authorization: Bearer TOKEN`

---

## Gestión de Usuarios

La aplicación permite:

- Crear usuarios
- Consultar usuarios
- Seleccionar usuarios
- Actualizar usuarios
- Eliminar usuarios

La persistencia principal se realiza mediante PostgreSQL y la API REST.

---

## Gestión de Enfermedades

La aplicación permite:

- Registrar enfermedades
- Consultar enfermedades
- Seleccionar enfermedades
- Actualizar enfermedades
- Eliminar enfermedades

---

## Reportes parametrizados

Se implementaron cuatro reportes parametrizados.

### Usuarios por nombre

`GET /api/usuarios/reportes/nombre?nombre=VALOR`

### Usuarios por correo electrónico

`GET /api/usuarios/reportes/email?email=VALOR`

### Enfermedades por gravedad

`GET /api/enfermedades/reportes/gravedad?nivelGravedad=VALOR`

Valores utilizados:

- Leve
- Media
- Alta

### Enfermedades según condición contagiosa

`GET /api/enfermedades/reportes/contagiosa?valor=true`

o:

`GET /api/enfermedades/reportes/contagiosa?valor=false`

---

## Recuperación de contraseña

La aplicación dispone de una pantalla de recuperación de contraseña.

El correo del usuario se envía al endpoint:

`POST /api/auth/recuperar`

Ejemplo:

```json
{
  "email": "usuario@correo.com"
}
```

El backend procesa la solicitud y utiliza el servicio de correo configurado para enviar el recordatorio al correo registrado.

---

## Sensores

La aplicación incorpora dos sensores.

### Acelerómetro

El evento de movimiento permite detectar cuando el dispositivo es agitado.

La aplicación muestra:

`Movimiento detectado`

### Ubicación

El sensor de ubicación obtiene y muestra:

- Latitud
- Longitud

Ambos sensores fueron comprobados desde un dispositivo móvil.

---

## Persistencia

Los usuarios y las enfermedades se almacenan en PostgreSQL.

TinyDB se conserva únicamente para información local relacionada con la sesión, principalmente:

- `jwt_token`
- `usuario_actual`

---

## Principales endpoints

### Autenticación

`POST /api/auth/login`

`POST /api/auth/recuperar`

### Usuarios

`GET /api/usuarios`

`POST /api/usuarios`

`PUT /api/usuarios/{username}`

`DELETE /api/usuarios/{username}`

### Enfermedades

`GET /api/enfermedades`

`POST /api/enfermedades`

`PUT /api/enfermedades/{id}`

`DELETE /api/enfermedades/{id}`

---

## Entregables

La carpeta:

`entregables/`

contiene:

- `GestionEnfermedadesCorte2.aia`
- `GestionEnfermedadesCorte2.apk`

El archivo `.aia` contiene el proyecto fuente desarrollado en Kodular.

El archivo `.apk` permite instalar la aplicación en un dispositivo Android.

---

## Repositorio

Repositorio público:

`https://github.com/fdamatoo-bit/GestionEnfermedades-Corte2`

---

## Estado del proyecto

Se encuentran implementados y probados:

- API REST desplegada en Internet.
- PostgreSQL en línea.
- Arquitectura hexagonal.
- Registro de usuarios.
- Login mediante JWT.
- CRUD de usuarios.
- CRUD de enfermedades.
- Cuatro reportes parametrizados.
- Recuperación de contraseña por correo electrónico.
- Sensor de acelerómetro.
- Sensor de ubicación.
- Aplicación Kodular conectada a la API REST.
- Proyecto fuente `.aia`.
- Aplicación `.apk`.

## Corte 2 – Gestión de Enfermedades

Universidad de Cartagena  
Ingeniería de Software  
**Fabrizio Antonio D'Amato Osorio**