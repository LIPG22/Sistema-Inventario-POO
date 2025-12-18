package sistemainventario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaPrincipal extends JFrame {

    private InventarioDAO dao;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JTextField txtNombre, txtPrecio, txtCantidad, txtCategoria;
    private JButton btnGuardarAction; // Botón agregar
    private JButton btnCancelarEdicion;
    private Integer idProductoEnEdicion = null; // Si es null, estamos agregando. Si tiene número, estamos editando.

    public VentanaPrincipal() {
        dao = new InventarioDAO();
        configurarUI();
        cargarDatos();
    }

    private void configurarUI() {
        setTitle("Sistema de Inventario POO - Oracle");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

// --- 1. PANEL SUPERIOR (Contenedor Principal) ---
        // Usamos BorderLayout para poner inputs al centro y botones abajo
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // --- 2. PANEL DE DATOS (Solo Etiquetas y Cajas de Texto) ---
        // GridLayout de 4 filas y 2 columnas
        JPanel panelDatos = new JPanel(new GridLayout(4, 2, 10, 10));

        txtNombre = new JTextField();
        txtPrecio = new JTextField();
        txtCantidad = new JTextField();
        txtCategoria = new JTextField();

        panelDatos.add(new JLabel("Nombre:"));
        panelDatos.add(txtNombre);
        panelDatos.add(new JLabel("Precio:"));
        panelDatos.add(txtPrecio);
        panelDatos.add(new JLabel("Cantidad:"));
        panelDatos.add(txtCantidad);
        panelDatos.add(new JLabel("Categoría:"));
        panelDatos.add(txtCategoria);

        panelSuperior.add(panelDatos, BorderLayout.CENTER);

        // --- 3. PANEL DE BOTONES (Alineados abajo) ---
        // FlowLayout hace que los botones tengan su tamaño natural y se centren
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));

        btnGuardarAction = new JButton("Agregar Producto");
        btnCancelarEdicion = new JButton("Cancelar Edición");
        btnCancelarEdicion.setEnabled(false); // Apagado al inicio

        JButton btnEliminar = new JButton("Eliminar Seleccionado");
        btnEliminar.setForeground(Color.RED);

        panelBotones.add(btnGuardarAction);
        panelBotones.add(btnCancelarEdicion);
        panelBotones.add(btnEliminar);

        panelSuperior.add(panelBotones, BorderLayout.SOUTH);

        // Agregamos todo el panel superior a la ventana
        add(panelSuperior, BorderLayout.NORTH);

        // --- PANEL CENTRAL (Tabla) ---
        modeloTabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // NINGUNA celda será editable al dar doble clic
            }
        };
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Cantidad");
        modeloTabla.addColumn("Categoría");

        tabla = new JTable(modeloTabla);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        // --- EVENTOS (Acciones de botones) ---
        // Acción AGREGAR
        // 1. Al hacer clic en la TABLA para cargar datos
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int fila = tabla.getSelectedRow();
                if (fila >= 0) {
                    // Obtenemos los datos de la fila seleccionada
                    idProductoEnEdicion = (int) modeloTabla.getValueAt(fila, 0); // Guardamos el ID
                    txtNombre.setText(modeloTabla.getValueAt(fila, 1).toString());
                    txtPrecio.setText(modeloTabla.getValueAt(fila, 2).toString());
                    txtCantidad.setText(modeloTabla.getValueAt(fila, 3).toString());
                    txtCategoria.setText(modeloTabla.getValueAt(fila, 4).toString());

                    // Cambiamos la interfaz a "Modo Edición"
                    btnGuardarAction.setText("Actualizar Producto");
                    btnCancelarEdicion.setEnabled(true);
                }
            }
        });

        // 2. Acción PRINCIPAL (Agregar o Actualizar)
        btnGuardarAction.addActionListener(e -> {
            try {
                // Lee los campos
                String nom = txtNombre.getText();
                double pre = Double.parseDouble(txtPrecio.getText());
                int cant = Integer.parseInt(txtCantidad.getText());
                String cat = txtCategoria.getText();

                if (idProductoEnEdicion == null) {
                    // --- MODO AGREGAR (ID es 0 porque la BD lo genera) ---
                    ProductoFisico nuevo = new ProductoFisico(0, nom, pre, cant, cat);
                    if (dao.agregarProducto(nuevo)) {
                        JOptionPane.showMessageDialog(this, "¡Agregado correctamente!");
                        resetearFormulario();
                        cargarDatos();
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al agregar");
                    }
                } else {
                    // --- MODO ACTUALIZAR (Usamos el ID guardado) ---
                    ProductoFisico aActualizar = new ProductoFisico(idProductoEnEdicion, nom, pre, cant, cat);
                    if (dao.actualizarProducto(aActualizar)) {
                        JOptionPane.showMessageDialog(this, "¡Actualizado correctamente!");
                        resetearFormulario();
                        cargarDatos();
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al actualizar (quizás el ID ya no existe)");
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "FAVOR DE LLENAR TODOS LOS CAMPOS",
                        "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            }
        });

        // 3. Acción CANCELAR EDICIÓN
        btnCancelarEdicion.addActionListener(e -> {
            resetearFormulario();
            tabla.clearSelection(); // Quitar selección azul de la tabla
        });

        // 4. Acción ELIMINAR
        btnEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila != -1) {
                int respuesta = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar este producto?");
                if (JOptionPane.OK_OPTION == respuesta) {
                    int id = (int) modeloTabla.getValueAt(fila, 0);
                    if (dao.eliminarProducto(id)) {
                        JOptionPane.showMessageDialog(this, "Producto eliminado.");
                        cargarDatos();
                        resetearFormulario();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona una fila primero");
            }
        }
        );
    }

    private void cargarDatos() {
        modeloTabla.setRowCount(0); // Limpiar tabla
        List<ProductoFisico> lista = dao.listarProductos();

        for (ProductoFisico p : lista) {
            modeloTabla.addRow(new Object[]{
                p.getId(), p.getNombre(), p.getPrecio(),
                p.getCantidad(), p.getCategoria(), //p.obtenerDetalles() // Vemos el método sobreescrito
            });
        }
    }

    private void resetearFormulario() {
        // Limpia textos
        txtNombre.setText("");
        txtPrecio.setText("");
        txtCantidad.setText("");
        txtCategoria.setText("");

        // Resetea estados
        idProductoEnEdicion = null; // Volvemos a modo "Agregar"
        btnGuardarAction.setText("Agregar Producto");
        btnCancelarEdicion.setEnabled(false);
    }

    public static void main(String[] args) {
        // Hacemos visible la ventana en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }
}
