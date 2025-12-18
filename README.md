# Sistema de Inventario (Java + Oracle)

Aplicación de escritorio para la gestión de productos, diseñada para demostrar la implementación práctica de la Programación Orientada a Objetos (POO).

## 🚀 Tecnologías
* **Lenguaje:** Java (JDK 21 / NetBeans 25)
* **Base de Datos:** Oracle Database (SQL Developer)
* **Conexión:** JDBC Puro (Ojdbc11)
* **Arquitectura:** DAO (Data Access Object)

## 📸 Capturas
<img width="783" height="589" alt="image" src="https://github.com/user-attachments/assets/341bfec0-79ca-4caa-94dc-b83342a0799c" />
<img width="780" height="586" alt="image" src="https://github.com/user-attachments/assets/6fa65f10-e7e3-4467-82e7-611453690037" />
<img width="779" height="584" alt="image" src="https://github.com/user-attachments/assets/eec3103a-3b1e-4cf4-b748-c2f166a20473" />
<img width="780" height="589" alt="image" src="https://github.com/user-attachments/assets/190d4f7c-6ea5-4ba6-8158-7fd857b8ad8d" />

Eliminar datos:

<img width="786" height="588" alt="image" src="https://github.com/user-attachments/assets/1d3fd8b8-ce5a-48c8-8e51-da12baf2bfe4" />

Base de datos:

<img width="726" height="118" alt="image" src="https://github.com/user-attachments/assets/7c7e93bd-7706-44cd-bda4-7a278a088f46" />
<img width="693" height="92" alt="image" src="https://github.com/user-attachments/assets/84b4c62f-d2a6-42bb-8271-54f568c224f4" />


## 💡 Conceptos Aplicados
* **Herencia:** Clase base `Producto` y derivada `ProductoFisico`.
* **Polimorfismo:** Método `obtenerDetalles()` dinámico en la tabla.
* **Encapsulamiento:** Protección de datos con getters/setters.
* **Persistencia:** CRUD completo (Crear, Leer, Eliminar) conectado a BD real.

## 🛠️ Instalación
1. Clonar el repositorio.
2. Ejecutar el script `sql/script_creacion.sql` en tu base de datos Oracle.
3. Configurar las credenciales en `InventarioDAO.java`.
4. Ejecutar `VentanaPrincipal.java`.
