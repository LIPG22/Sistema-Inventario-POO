package sistemainventario;

// HERENCIA: ProductoFisico "es un" Producto
public class ProductoFisico extends Producto {

    private int cantidad;
    private String categoria;

    public ProductoFisico(int id, String nombre, double precio, int cantidad, String categoria) {
        super(id, nombre, precio); // Llamada al constructor del padre
        this.cantidad = cantidad;
        this.categoria = categoria;
    }

    // POLIMORFISMO: Sobreescribimos el método abstracto
    @Override
    public String obtenerDetalles() {
        return "ID: " + id + " | " + nombre + " (" + categoria + ") - Stock: " + cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getCategoria() {
        return categoria;
    }
}
