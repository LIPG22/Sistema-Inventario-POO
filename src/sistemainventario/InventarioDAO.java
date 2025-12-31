package sistemainventario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventarioDAO {

    // Datos de conexión
    private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private final String USER = "USUARIO_LIAM";
    private final String PASS = "Li!Isa0!";

    // Método para conectar
    private Connection conectar() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
        return con;
    }

    // LISTAR (Read)
    public List<ProductoFisico> listarProductos() {
        List<ProductoFisico> lista = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCTOS ORDER BY ID_PRODUCTO";

        try (Connection con = conectar(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // Convertimos filas de la BD a Objetos Java
                lista.add(new ProductoFisico(
                        rs.getInt("ID_PRODUCTO"),
                        rs.getString("NOMBRE"),
                        rs.getDouble("PRECIO"),
                        rs.getInt("CANTIDAD"),
                        rs.getString("CATEGORIA")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // USUARIOS
    public Usuario iniciarSesion(String nombreUsuario, String password) {
        Usuario usuarioEncontrado = null;
        // OJO: Ahora seleccionamos los datos reales, no COUNT
        String sql = "SELECT * FROM USUARIOS WHERE NOMBRE = ? AND PASSWORD = ?";

        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, nombreUsuario);
            pst.setString(2, password);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    // ¡Login exitoso! Construimos el objeto con los datos de la BD
                    int id = rs.getInt("ID_USUARIO");
                    String nombre = rs.getString("NOMBRE");
                    String contra = rs.getString("PASSWORD");
                    // Si tu clase Usuario tiene más campos, agrégalos aquí
                    usuarioEncontrado = new Usuario(id, nombre, contra);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al loguear: " + e.getMessage());
        }
        return usuarioEncontrado; // Retorna el objeto lleno o null
    }

    // INSERTAR (Create)
    public boolean agregarProducto(ProductoFisico p) {
        String sql = "INSERT INTO PRODUCTOS (NOMBRE, PRECIO, CANTIDAD, CATEGORIA) VALUES (?, ?, ?, ?)";

        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, p.getNombre());
            pst.setDouble(2, p.getPrecio());
            pst.setInt(3, p.getCantidad());
            pst.setString(4, p.getCategoria());

            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ELIMINAR (Delete)
    public boolean eliminarProducto(int id) {
        String sql = "DELETE FROM PRODUCTOS WHERE ID_PRODUCTO = ?";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ACTUALIZAR (Update)
    public boolean actualizarProducto(ProductoFisico p) {
        String sql = "UPDATE PRODUCTOS SET NOMBRE=?, PRECIO=?, CANTIDAD=?, CATEGORIA=? WHERE ID_PRODUCTO=?";

        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql)) {

            // Llenamos los datos a cambiar
            pst.setString(1, p.getNombre());
            pst.setDouble(2, p.getPrecio());
            pst.setInt(3, p.getCantidad());
            pst.setString(4, p.getCategoria());

            pst.setInt(5, p.getId());

            int filasAfectadas = pst.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
