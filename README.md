# Sistema de Inventario (Java + Oracle)

Aplicación de escritorio para la gestión de productos, diseñada para demostrar la implementación práctica de la Programación Orientada a Objetos (POO).

## 🚀 Tecnologías
* **Lenguaje:** Java (JDK 21 / NetBeans 25)
* **Base de Datos:** Oracle Database (SQL Developer)
* **Conexión:** JDBC Puro (Ojdbc11)
* **Arquitectura:** DAO (Data Access Object)

## 📸 Capturas
### Agregar Nuevo Producto
Al llenar el formulario y hacer clic en "Agregar Producto", los datos se envían a la base de datos mediante una sentencia `INSERT`. Una ventana emergente confirma que la operación ha sido exitosa.
<img width="789" height="591" alt="image" src="https://github.com/user-attachments/assets/e06dd46d-574f-4810-b74b-54815362685a" />
<img width="784" height="587" alt="image" src="https://github.com/user-attachments/assets/f4df6c70-115b-48a3-b8f7-fd802fe3646b" />
<img width="783" height="592" alt="image" src="https://github.com/user-attachments/assets/794bf12a-1a72-4ac0-9c8f-61925bd41e8d" />

Datos enviados a la base de datos.

<img width="418" height="150" alt="image" src="https://github.com/user-attachments/assets/4e0ffa94-9183-4a7b-a7cc-525c1089bec2" />

### Validación de Datos
Lanzará un mensaje notificando que falta llenar campos obligatorios para poder agregar un producto.
<img width="781" height="588" alt="image" src="https://github.com/user-attachments/assets/03396146-9f29-4089-984f-b1f82d12b4f0" />

### Eliminar Producto y Persistencia
Esta imagen comprueba la funcionalidad de eliminación.
<img width="781" height="587" alt="image" src="https://github.com/user-attachments/assets/c4ee412e-f13a-467b-a1f5-e84096c5d0ad" />
<img width="783" height="589" alt="image" src="https://github.com/user-attachments/assets/737a6b5e-759f-4c4e-8a9d-3dddc17b9de4" />
<img width="785" height="589" alt="image" src="https://github.com/user-attachments/assets/89aa0790-2620-430d-be15-51626c7fa996" />
1.  **Arriba:** La aplicación muestra un mensaje de confirmación para eliminar el dato seleccionado.
2.  **Abajo:** La captura de SQL Developer confirma la operación, mostrando la tabla de la base de datos **después** de la eliminación del registro.
<img width="418" height="134" alt="image" src="https://github.com/user-attachments/assets/eb1369f3-f930-41ca-9f57-396af38c7b32" />

### Actualización de Datos
<img width="782" height="590" alt="image" src="https://github.com/user-attachments/assets/d78c5599-e902-4f2d-afdc-1fe9dba32312" />
<img width="786" height="589" alt="image" src="https://github.com/user-attachments/assets/15e2ed06-3faf-423c-8765-38c008ac3732" />

Datos actualizados en la base de datos.

<img width="414" height="129" alt="image" src="https://github.com/user-attachments/assets/811e7d5b-0591-4fe1-a068-ada04b2ca672" />

## 💡 Conceptos Aplicados
* **Herencia:** Clase base `Producto` y derivada `ProductoFisico`.
* **Polimorfismo:** Método `obtenerDetalles()` dinámico en la tabla.
* **Encapsulamiento:** Protección de datos con getters/setters.
* **Persistencia:** CRUD completo (Crear, Leer, Actualizar, Eliminar) conectado a BD real.

## 🛠️ Instalación
1. Clonar el repositorio.
2. Ejecutar el script `sql/script_creacion.sql` en tu base de datos Oracle.
3. Configurar las credenciales en `InventarioDAO.java`.
4. Ejecutar `VentanaPrincipal.java`.
