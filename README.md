# 📦 Sistema de Inventario y Ventas (Java + Oracle)

<div align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white" />
  <img src="https://img.shields.io/badge/JDBC-007396?style=for-the-badge&logo=java&logoColor=white" />
</div>

<br>

Aplicación de escritorio robusta para la gestión empresarial de inventarios. Desarrollada en **Java SE** implementando arquitectura por capas (MVC/DAO) y persistencia de datos de alto rendimiento con **Oracle Database**.

## 📸 Capturas de Pantalla


## 🚀 Características Principales

* **🔐 Seguridad y Autenticación:** Sistema de Login seguro con validación de credenciales contra base de datos y encriptación básica de sesión.
* **👤 Personalización:** La interfaz se adapta mostrando el nombre del usuario activo (Admin/Empleado).
* **🛠️ CRUD Completo:**
    * **Crear:** Alta de nuevos productos físicos.
    * **Leer:** Visualización de inventario en tiempo real con `JTable`.
    * **Actualizar:** Edición de precios, stock y categorías.
    * **Borrar:** Eliminación lógica/física de productos.
* **📊 Arquitectura Profesional:** Uso de **Patrón DAO** (Data Access Object) para separar la lógica de negocio de la base de datos.

## 🛠️ Tecnologías Utilizadas

* **Lenguaje:** Java (JDK 21)
* **Interfaz Gráfica:** Java Swing (JFrame, JPanel, JTable)
* **Base de Datos:** Oracle Database (PL/SQL)
* **Conectividad:** JDBC (OJDBC Driver)
* **Herramientas:** NetBeans IDE / VS Code

## 💾 Instalación y Uso

1.  **Base de Datos:** Ejecutar el script `database.sql` (incluido en la carpeta docs) para crear la tabla `PRODUCTOS` y `USUARIOS`.
2.  **Configuración:** Ajustar las credenciales de Oracle en `InventarioDAO.java`.
3.  **Ejecución:** Iniciar desde la clase `VentanaLogin.java`.

---
<div align="center">
  Desarrollado por <a href="https://github.com/LIPG22">Liam Isaac Peña Gutiérrez</a>
</div>
