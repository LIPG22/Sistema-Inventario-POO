package sistemainventario;

public class Usuario {

    private int id_usuario;
    private String nombre;
    private String contra;

    public Usuario(int id_usuario, String nombre, String contra) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.contra = contra;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContra() {
        return contra;
    }

}
