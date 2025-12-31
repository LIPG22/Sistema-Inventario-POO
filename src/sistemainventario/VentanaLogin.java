package sistemainventario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaLogin extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private InventarioDAO dao;

    public VentanaLogin() {
        dao = new InventarioDAO();
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Acceso al Sistema");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1, 10, 10));
    }

    private void inicializarComponentes() {
        // --- Panel 1: Usuario ---
        JPanel panelUser = new JPanel();
        panelUser.add(new JLabel("Usuario:"));
        txtUsuario = new JTextField(15);
        panelUser.add(txtUsuario);

        // --- Panel 2: Password ---
        JPanel panelPass = new JPanel();
        panelPass.add(new JLabel("Contraseña:"));
        txtPassword = new JPasswordField(15);
        panelPass.add(txtPassword);

        // --- Panel 3: Botón ---
        JPanel panelBoton = new JPanel();
        JButton btnIngresar = new JButton("Ingresar");

        // ACCIÓN DEL BOTÓN
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarCredenciales();
            }
        });

        panelBoton.add(btnIngresar);

        add(panelUser);
        add(panelPass);
        add(panelBoton);
    }

    private void verificarCredenciales() {
        String usuario = txtUsuario.getText();
        String password = new String(txtPassword.getPassword());

        Usuario usuarioLogueado = dao.iniciarSesion(usuario, password);

        if (usuarioLogueado != null) {
            // 1. Si es correcto (diferente de null), pasamos el usuario a la ventana principal
            VentanaPrincipal principal = new VentanaPrincipal(usuarioLogueado);
            principal.setVisible(true);

            // 2. Cerramos ventana de login
            this.dispose();
        } else {
            // Si es null, credenciales incorrectas
            JOptionPane.showMessageDialog(this, "Credenciales Incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        new VentanaLogin().setVisible(true);
    }
}
