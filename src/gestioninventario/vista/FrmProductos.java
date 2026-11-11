package gestioninventario.vista;

import gestioninventario.controlador.ProductoControlador;
import gestioninventario.modelo.Producto;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FrmProductos extends javax.swing.JFrame {
    private final ProductoControlador controlador;
    private int idSeleccionado;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmProductos.class.getName());

    public FrmProductos() {
        initComponents();
        controlador = new ProductoControlador();
        idSeleccionado = 0;
        setLocationRelativeTo(null);
        cargarTabla();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        lblTitulo = new javax.swing.JLabel();
        lblCodigo = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblMarca = new javax.swing.JLabel();
        lblCaracteristicas = new javax.swing.JLabel();
        lblTono = new javax.swing.JLabel();
        lblStock = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtMarca = new javax.swing.JTextField();
        txtCaracteristicas = new javax.swing.JTextField();
        txtTono = new javax.swing.JTextField();
        spnStock = new javax.swing.JSpinner();
        btnGuardar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inventario de productos");

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 16));
        lblTitulo.setText("INVENTARIO DE PRODUCTOS");

        lblCodigo.setText("Código:");

        lblNombre.setText("Producto:");

        lblMarca.setText("Marca:");

        lblCaracteristicas.setText("Características:");

        lblTono.setText("Tono:");

        lblStock.setText("Stock:");

        spnStock.setModel(new javax.swing.SpinnerNumberModel());

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(this::btnBuscarActionPerformed);

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {
            }
        ));
        tblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProductos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar)
                        .addGap(18, 18, 18)
                        .addComponent(btnActualizar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpiar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblMarca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCaracteristicas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblStock, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitulo)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                .addComponent(txtMarca, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                .addComponent(txtTono, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                .addComponent(spnStock, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCaracteristicas)))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblTitulo)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodigo)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMarca)
                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCaracteristicas)
                    .addComponent(txtCaracteristicas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTono)
                    .addComponent(txtTono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStock)
                    .addComponent(spnStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnBuscar)
                    .addComponent(btnActualizar)
                    .addComponent(btnEliminar)
                    .addComponent(btnLimpiar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try{
            Producto producto = obtenerProductoFormulario();
            boolean registrado = controlador.registrar(producto);
            if(registrado){
                JOptionPane.showMessageDialog(this, "Producto registrado correctamente");
                limpiarFormulario();
                cargarTabla();
            }
        }catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Validación", JOptionPane.WARNING_MESSAGE);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, "Error de base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try{
            Producto producto = controlador.buscar(txtCodigo.getText());
            if(producto == null){
                JOptionPane.showMessageDialog(this, "No existe un producto con ese código");
                return;
            }
            mostrarProductoFormulario(producto);
            seleccionarFilaPorId(producto.getId());
        }catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Validación", JOptionPane.WARNING_MESSAGE);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, "Error de base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        try{
            Producto producto = obtenerProductoFormulario();
            boolean actualizado = controlador.actualizar(producto);
            if(actualizado){
                JOptionPane.showMessageDialog(this, "Producto actualizado correctamente");
                limpiarFormulario();
                cargarTabla();
            }
        }catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Validación", JOptionPane.WARNING_MESSAGE);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, "Error de base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if(idSeleccionado <= 0){
            JOptionPane.showMessageDialog(this, "Debe seleccionar un producto", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int respuesta = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de eliminar el producto seleccionado?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        if(respuesta != JOptionPane.YES_OPTION){
            return;
        }
        try{
            boolean eliminado = controlador.eliminar(idSeleccionado);
            if(eliminado){
                JOptionPane.showMessageDialog(this, "Producto eliminado correctamente");
                limpiarFormulario();
                cargarTabla();
            }
        }catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Validación", JOptionPane.WARNING_MESSAGE);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, "Error de base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarFormulario();
        tblProductos.clearSelection();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked
        int fila = tblProductos.getSelectedRow();
        if(fila < 0){
            return;
        }
        Producto producto = new Producto();
        producto.setId((Integer) tblProductos.getValueAt(fila, 0));
        producto.setCodigo((String) tblProductos.getValueAt(fila, 1));
        producto.setNombre((String) tblProductos.getValueAt(fila, 2));
        producto.setMarca((String) tblProductos.getValueAt(fila, 3));
        producto.setCaracteristicas((String) tblProductos.getValueAt(fila, 4));
        producto.setTono((String) tblProductos.getValueAt(fila, 5));
        producto.setStock((Integer) tblProductos.getValueAt(fila, 6));
        mostrarProductoFormulario(producto);
    }//GEN-LAST:event_tblProductosMouseClicked

    private Producto obtenerProductoFormulario(){
        Producto producto = new Producto();
        producto.setId(idSeleccionado);
        producto.setCodigo(txtCodigo.getText().trim());
        producto.setNombre(txtNombre.getText().trim());
        producto.setMarca(txtMarca.getText().trim());
        producto.setCaracteristicas(txtCaracteristicas.getText().trim());
        producto.setTono(txtTono.getText().trim());
        producto.setStock((Integer) spnStock.getValue());
        return producto;
    }

    private void mostrarProductoFormulario(Producto producto){
        idSeleccionado = producto.getId();
        txtCodigo.setText(producto.getCodigo());
        txtNombre.setText(producto.getNombre());
        txtMarca.setText(producto.getMarca());
        txtCaracteristicas.setText(producto.getCaracteristicas());
        txtTono.setText(producto.getTono());
        spnStock.setValue(producto.getStock());
    }

    private void seleccionarFilaPorId(int id){
        for(int fila = 0; fila < tblProductos.getRowCount(); fila++){
            if((Integer) tblProductos.getValueAt(fila, 0) == id){
                tblProductos.setRowSelectionInterval(fila, fila);
                tblProductos.scrollRectToVisible(tblProductos.getCellRect(fila, 0, true));
                return;
            }
        }
    }

    private void limpiarFormulario(){
        idSeleccionado = 0;
        txtCodigo.setText("");
        txtNombre.setText("");
        txtMarca.setText("");
        txtCaracteristicas.setText("");
        txtTono.setText("");
        spnStock.setValue(0);
        txtCodigo.requestFocus();
    }

    private void cargarTabla(){
        DefaultTableModel modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int fila, int columna){
                return false;
            }
        };
        modelo.addColumn("ID");
        modelo.addColumn("Código");
        modelo.addColumn("Producto");
        modelo.addColumn("Marca");
        modelo.addColumn("Características");
        modelo.addColumn("Tono");
        modelo.addColumn("Stock");

        try{
            for(Producto producto : controlador.listar()){
                Object[] fila = {
                    producto.getId(),
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getMarca(),
                    producto.getCaracteristicas(),
                    producto.getTono(),
                    producto.getStock()
                };
                modelo.addRow(fila);
            }
            tblProductos.setModel(modelo);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, "Error cargando productos: " + e.getMessage());
        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new FrmProductos().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCaracteristicas;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblMarca;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblStock;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTono;
    private javax.swing.JSpinner spnStock;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtCaracteristicas;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTono;
    // End of variables declaration//GEN-END:variables
}
