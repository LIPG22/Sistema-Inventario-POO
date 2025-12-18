package sistemainventario;

// ABSTRACCIÓN: Definimos una plantilla genérica
public abstract class Producto {

    // ENCAPSULAMIENTO: Atributos protegidos (visibles para hijos) o privados
    protected int id;
    protected String nombre;
    protected double precio;

    public Producto(int id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    // Método abstracto (POLIMORFISMO): Obligamos a las clases hijas a definir cómo mostrar detalles
    public abstract String obtenerDetalles();

    // Getters y Setters (Encapsulamiento)
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }
}
