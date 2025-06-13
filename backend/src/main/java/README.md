# 💰 AhorroApp

**AhorroApp** es una aplicación móvil desarrollada en Java (Android Studio) que permite a los usuarios llevar un control de sus ingresos y gastos. La aplicación se conecta con un backend desarrollado en **Spring Boot**, y la información se almacena en una base de datos **PostgreSQL** (pgAdmin)

---
## 👥 Integrantes 
- JIMMY HERENCIA CHAMBI
- MICHEEL OLIVARES ROJAS
- YEYMI PAREJA YUTO

---

## 📱 Características

- Registro y visualización de **movimientos** (ingresos y gastos)
- Separación de interfaces para gestionar:
  - Usuario
  - Movimientos
- Conexión con API REST.
- Patrón **MVVM** para la arquitectura de la app
- Interfaz sencilla y adaptable.

**BASE_backend = "http://10.0.2.2:8081/api/"**

---

## 🛠️ Tecnologías utilizadas

### Backend
- Java
- Spring Boot
- PostgreSQL (pgAdmin)
- JPA / Hibernate
- Maven
- Postman (para pruebas)

### Frontend
- Java (Android Studio)
- Retrofit2 (es una biblioteca cliente HTTP para Android y Java que simplifica la comunicación con APIs REST)
- RecyclerView (componente más avanzado y flexible que ListView para mostrar listas o colecciones de datos)
- ConstraintLayout (permite crear interfaces complejas con un rendimiento mejorado)
- MVVM
- Emulador: Pixel 5 - API 30

---
- Estas tres herramientas son fundamentales en el desarrollo Android moderno para:

- Consumir APIs (Retrofit)

- Mostrar listas eficientes (RecyclerView)

- Crear interfaces complejas (ConstraintLayout)


---

## API REST

### Obtener movimientos (GET)
**http://localhost:8081/api/movimientos/**

### Crear nuevo movimiento (POST)
**http://localhost:8081/api/movimientos/**

{
 - "tipo": "Ingreso",   <-- (Puede ser Gastos o Ingreso)
 - "monto": 250.00, 
 - "descripcion": "Venta de ropa",
 - "fecha": "2025-06-12"  
}

### Eliminar movimiento (DELETE)
**http://localhost:8081/api/movimientos/1**  <-- (El numero es depende de que id quieres eliminar)

---
