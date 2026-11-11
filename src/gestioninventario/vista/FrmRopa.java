package gestioninventario.vista;

import gestioninventario.controlador.RopaControlador;
import gestioninventario.modelo.Ropa;
import gestioninventario.util.Formatos;
import gestioninventario.vista.componentes.Estilos;
import gestioninventario.vista.componentes.Mensajes;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class FrmRopa extends javax.swing.JFrame {
    private final JFrame menu;
    private final RopaControlador controlador;
    private DefaultTableModel modeloTabla;
    private TableRowSorter<DefaultTableModel> ordenador;
    private List<Ropa> productos = new ArrayList<>();
    private int idSeleccionado;

    public FrmRopa(JFrame menu) {
        initComponents();
        this.menu = menu;
        controlador = new RopaControlador();
        idSeleccionado = 0;
        configurarTabla();
        limpiarFormulario();
        cargarTabla();
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        pnlFondo = new gestioninventario.vista.componentes.PanelDegradado();
        lblTitulo = new gestioninventario.vista.componentes.Titulo();
        lblSubtitulo = new gestioninventario.vista.componentes.Subtitulo();
        btnVolver = new gestioninventario.vista.componentes.BotonRedondeado();
        pnlFormulario = new gestioninventario.vista.componentes.PanelRedondeado();
        lblDatos = new gestioninventario.vista.componentes.Titulo();
        lblTipoRopa = new gestioninventario.vista.componentes.Etiqueta();
        cmbTipoRopa = new gestioninventario.vista.componentes.ComboEditable();
        lblMarca = new gestioninventario.vista.componentes.Etiqueta();
        txtMarca = new gestioninventario.vista.componentes.CampoTexto();
        lblColor = new gestioninventario.vista.componentes.Etiqueta();
        txtColor = new gestioninventario.vista.componentes.CampoTexto();
        lblCaracteristica = new gestioninventario.vista.componentes.Etiqueta();
        txtCaracteristica = new gestioninventario.vista.componentes.CampoTexto();
        lblTallas = new gestioninventario.vista.componentes.Etiqueta();
        txtTallas = new gestioninventario.vista.componentes.CampoTexto();
        lblStock = new gestioninventario.vista.componentes.Etiqueta();
        txtStock = new gestioninventario.vista.componentes.CampoTexto();
        lblPrecioImportador = new gestioninventario.vista.componentes.Etiqueta();
        txtPrecioImportador = new gestioninventario.vista.componentes.CampoTexto();
        lblPrecioMayorista = new gestioninventario.vista.componentes.Etiqueta();
        txtPrecioMayorista = new gestioninventario.vista.componentes.CampoTexto();
        lblPrecioDetal = new gestioninventario.vista.componentes.Etiqueta();
        txtPrecioDetal = new gestioninventario.vista.componentes.CampoTexto();
        btnGuardar = new gestioninventario.vista.componentes.BotonRedondeado();
        btnActualizar = new gestioninventario.vista.componentes.BotonRedondeado();
        btnEliminar = new gestioninventario.vista.componentes.BotonRedondeado();
        btnLimpiar = new gestioninventario.vista.componentes.BotonRedondeado();
        pnlTabla = new gestioninventario.vista.componentes.PanelRedondeado();
        lblBuscar = new gestioninventario.vista.componentes.Etiqueta();
        txtBuscar = new gestioninventario.vista.componentes.CampoTexto();
        lblTotal = new gestioninventario.vista.componentes.Etiqueta();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRopa = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Inventario - Ropa");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pnlFondo.setColorInicio(new java.awt.Color(255, 244, 249));

        lblTitulo.setForeground(new java.awt.Color(212, 93, 147));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitulo.setText("Ropa");

        lblSubtitulo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblSubtitulo.setText("Registra, consulta, actualiza y elimina los productos de esta categoría");

        btnVolver.setForeground(new java.awt.Color(74, 47, 94));
        btnVolver.setText("Volver al menú");
        btnVolver.setColorFondo(new java.awt.Color(231, 218, 247));
        btnVolver.addActionListener(this::btnVolverActionPerformed);

        lblDatos.setFont(new java.awt.Font("Segoe Script", 1, 26));
        lblDatos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDatos.setText("Datos del producto");

        lblTipoRopa.setText("Tipo de ropa");

        cmbTipoRopa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Blusa", "Camiseta", "Camisa", "Pantalón", "Jean", "Falda", "Vestido", "Short", "Chaqueta", "Buzo", "Pijama", "Conjunto" }));

        lblMarca.setText("Marca");

        lblColor.setText("Color");

        lblCaracteristica.setText("Característica");

        lblTallas.setText("Tallas");

        txtTallas.setToolTipText("Ejemplo: S, M, L  o  6-8-10");

        lblStock.setText("Stock");

        txtStock.setToolTipText("Unidades disponibles, por ejemplo 24");

        lblPrecioImportador.setText("Precio importador");

        txtPrecioImportador.setToolTipText("Ejemplo: 25000 o 25.000");

        lblPrecioMayorista.setText("Precio mayorista");

        txtPrecioMayorista.setToolTipText("Ejemplo: 25000 o 25.000");

        lblPrecioDetal.setText("Precio al detal");

        txtPrecioDetal.setToolTipText("Ejemplo: 25000 o 25.000");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);

        btnActualizar.setText("Actualizar");
        btnActualizar.setColorFondo(new java.awt.Color(212, 93, 147));
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);

        btnEliminar.setText("Eliminar");
        btnEliminar.setColorFondo(new java.awt.Color(201, 80, 122));
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);

        btnLimpiar.setForeground(new java.awt.Color(74, 47, 94));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setColorFondo(new java.awt.Color(231, 218, 247));
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDatos, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTipoRopa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCaracteristica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTallas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrecioImportador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrecioMayorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrecioDetal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbTipoRopa, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(txtMarca, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(txtColor, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(txtCaracteristica, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(txtTallas, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(txtStock, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(txtPrecioImportador, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(txtPrecioMayorista, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(txtPrecioDetal, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addComponent(lblDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoRopa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTipoRopa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCaracteristica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCaracteristica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTallas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTallas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrecioImportador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecioImportador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrecioMayorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecioMayorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrecioDetal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecioDetal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 6, Short.MAX_VALUE)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        lblBuscar.setText("Buscar:");

        txtBuscar.setToolTipText("Escribe para filtrar por cualquier columna");
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        lblTotal.setForeground(new java.awt.Color(138, 111, 160));
        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotal.setText("0 productos");

        tblRopa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "ID", "Tipo de ropa", "Marca", "Color", "Característica", "Tallas", "Stock", "Precio importador", "Precio mayorista", "Precio al detal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblRopa);

        javax.swing.GroupLayout pnlTablaLayout = new javax.swing.GroupLayout(pnlTabla);
        pnlTabla.setLayout(pnlTablaLayout);
        pnlTablaLayout.setHorizontalGroup(
            pnlTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTablaLayout.createSequentialGroup()
                .addComponent(lblBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        pnlTablaLayout.setVerticalGroup(
            pnlTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTablaLayout.createSequentialGroup()
                .addGroup(pnlTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1))
        );

        javax.swing.GroupLayout pnlFondoLayout = new javax.swing.GroupLayout(pnlFondo);
        pnlFondo.setLayout(pnlFondoLayout);
        pnlFondoLayout.setHorizontalGroup(
            pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFondoLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFondoLayout.createSequentialGroup()
                        .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblSubtitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20)
                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlFondoLayout.createSequentialGroup()
                        .addComponent(pnlFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(pnlTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(24, 24, 24))
        );
        pnlFondoLayout.setVerticalGroup(
            pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFondoLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(pnlFondoLayout.createSequentialGroup()
                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(lblSubtitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24))
        );

        getContentPane().add(pnlFondo, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        volverAlMenu();
    }//GEN-LAST:event_formWindowClosing

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        volverAlMenu();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            Ropa producto = obtenerProductoFormulario();
            producto.setId(0);
            if (controlador.registrar(producto)) {
                Mensajes.exito(this, "Producto guardado correctamente");
                limpiarFormulario();
                cargarTabla();
            }
        } catch (IllegalArgumentException e) {
            Mensajes.advertencia(this, e.getMessage());
        } catch (SQLException e) {
            Mensajes.errorBaseDatos(this, e);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if (idSeleccionado <= 0) {
            Mensajes.advertencia(this, "Selecciona en la tabla el producto que quieres actualizar");
            return;
        }
        try {
            if (controlador.actualizar(obtenerProductoFormulario())) {
                Mensajes.exito(this, "Producto actualizado correctamente");
                limpiarFormulario();
            } else {
                Mensajes.advertencia(this, "El producto ya no existe; la tabla se actualizará");
            }
            cargarTabla();
        } catch (IllegalArgumentException e) {
            Mensajes.advertencia(this, e.getMessage());
        } catch (SQLException e) {
            Mensajes.errorBaseDatos(this, e);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (idSeleccionado <= 0) {
            Mensajes.advertencia(this, "Selecciona en la tabla el producto que quieres eliminar");
            return;
        }
        if (!Mensajes.confirmar(this, "¿Seguro que deseas eliminar este producto?\nEsta acción no se puede deshacer.")) {
            return;
        }
        try {
            controlador.eliminar(idSeleccionado);
            Mensajes.exito(this, "Producto eliminado correctamente");
            limpiarFormulario();
            cargarTabla();
        } catch (IllegalArgumentException e) {
            Mensajes.advertencia(this, e.getMessage());
        } catch (SQLException e) {
            Mensajes.errorBaseDatos(this, e);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarFormulario();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        filtrar();
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void configurarTabla() {
        modeloTabla = (DefaultTableModel) tblRopa.getModel();
        ordenador = new TableRowSorter<>(modeloTabla);
        tblRopa.setRowSorter(ordenador);
        Estilos.estilizarTabla(tblRopa, jScrollPane1, lblTitulo.getForeground());
        tblRopa.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                seleccionarFila();
            }
        });
    }

    private void cargarTabla() {
        try {
            List<Ropa> lista = controlador.listar();
            modeloTabla.setRowCount(0);
            productos = lista;
            for (Ropa p : productos) {
                modeloTabla.addRow(new Object[]{p.getId(), p.getTipoRopa(), p.getMarca(), p.getColor(), p.getCaracteristica(), p.getTallas(), p.getStock(), p.getPrecioImportador(), p.getPrecioMayorista(), p.getPrecioDetal()});
            }
        } catch (SQLException e) {
            Mensajes.errorBaseDatos(this, e);
        }
        actualizarTotal();
    }

    private void seleccionarFila() {
        int filaVista = tblRopa.getSelectedRow();
        if (filaVista < 0) {
            return;
        }
        Ropa p = productos.get(tblRopa.convertRowIndexToModel(filaVista));
        idSeleccionado = p.getId();
        mostrarProductoFormulario(p);
    }

    private Ropa obtenerProductoFormulario() {
        Ropa p = new Ropa();
        p.setId(idSeleccionado);
        p.setTipoRopa(cmbTipoRopa.getTexto());
        p.setMarca(txtMarca.getText().trim());
        p.setColor(txtColor.getText().trim());
        p.setCaracteristica(txtCaracteristica.getText().trim());
        p.setTallas(txtTallas.getText().trim());
        p.setStock(Formatos.parsearEntero(txtStock.getText(), "Stock"));
        p.setPrecioImportador(Formatos.parsearPrecio(txtPrecioImportador.getText(), "Precio importador"));
        p.setPrecioMayorista(Formatos.parsearPrecio(txtPrecioMayorista.getText(), "Precio mayorista"));
        p.setPrecioDetal(Formatos.parsearPrecio(txtPrecioDetal.getText(), "Precio al detal"));
        return p;
    }

    private void mostrarProductoFormulario(Ropa p) {
        cmbTipoRopa.setSelectedItem(p.getTipoRopa());
        txtMarca.setText(p.getMarca());
        txtColor.setText(p.getColor());
        txtCaracteristica.setText(p.getCaracteristica());
        txtTallas.setText(p.getTallas());
        txtStock.setText(String.valueOf(p.getStock()));
        txtPrecioImportador.setText(Formatos.precioParaEditar(p.getPrecioImportador()));
        txtPrecioMayorista.setText(Formatos.precioParaEditar(p.getPrecioMayorista()));
        txtPrecioDetal.setText(Formatos.precioParaEditar(p.getPrecioDetal()));
    }

    private void limpiarFormulario() {
        cmbTipoRopa.limpiar();
        txtMarca.setText("");
        txtColor.setText("");
        txtCaracteristica.setText("");
        txtTallas.setText("");
        txtStock.setText("");
        txtPrecioImportador.setText("");
        txtPrecioMayorista.setText("");
        txtPrecioDetal.setText("");
        idSeleccionado = 0;
        tblRopa.clearSelection();
    }

    private void filtrar() {
        String texto = txtBuscar.getText().trim();
        if (texto.isEmpty()) {
            ordenador.setRowFilter(null);
        } else {
            ordenador.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(texto)));
        }
        actualizarTotal();
    }

    private void actualizarTotal() {
        int visibles = tblRopa.getRowCount();
        int total = productos.size();
        if (visibles == total) {
            lblTotal.setText(total + (total == 1 ? " producto" : " productos"));
        } else {
            lblTotal.setText(visibles + " de " + total + " productos");
        }
    }

    private void volverAlMenu() {
        dispose();
        menu.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gestioninventario.vista.componentes.BotonRedondeado btnActualizar;
    private gestioninventario.vista.componentes.BotonRedondeado btnEliminar;
    private gestioninventario.vista.componentes.BotonRedondeado btnGuardar;
    private gestioninventario.vista.componentes.BotonRedondeado btnLimpiar;
    private gestioninventario.vista.componentes.BotonRedondeado btnVolver;
    private gestioninventario.vista.componentes.ComboEditable cmbTipoRopa;
    private javax.swing.JScrollPane jScrollPane1;
    private gestioninventario.vista.componentes.Etiqueta lblBuscar;
    private gestioninventario.vista.componentes.Etiqueta lblCaracteristica;
    private gestioninventario.vista.componentes.Etiqueta lblColor;
    private gestioninventario.vista.componentes.Titulo lblDatos;
    private gestioninventario.vista.componentes.Etiqueta lblMarca;
    private gestioninventario.vista.componentes.Etiqueta lblPrecioDetal;
    private gestioninventario.vista.componentes.Etiqueta lblPrecioImportador;
    private gestioninventario.vista.componentes.Etiqueta lblPrecioMayorista;
    private gestioninventario.vista.componentes.Etiqueta lblStock;
    private gestioninventario.vista.componentes.Subtitulo lblSubtitulo;
    private gestioninventario.vista.componentes.Etiqueta lblTallas;
    private gestioninventario.vista.componentes.Etiqueta lblTipoRopa;
    private gestioninventario.vista.componentes.Titulo lblTitulo;
    private gestioninventario.vista.componentes.Etiqueta lblTotal;
    private gestioninventario.vista.componentes.PanelDegradado pnlFondo;
    private gestioninventario.vista.componentes.PanelRedondeado pnlFormulario;
    private gestioninventario.vista.componentes.PanelRedondeado pnlTabla;
    private javax.swing.JTable tblRopa;
    private gestioninventario.vista.componentes.CampoTexto txtBuscar;
    private gestioninventario.vista.componentes.CampoTexto txtCaracteristica;
    private gestioninventario.vista.componentes.CampoTexto txtColor;
    private gestioninventario.vista.componentes.CampoTexto txtMarca;
    private gestioninventario.vista.componentes.CampoTexto txtPrecioDetal;
    private gestioninventario.vista.componentes.CampoTexto txtPrecioImportador;
    private gestioninventario.vista.componentes.CampoTexto txtPrecioMayorista;
    private gestioninventario.vista.componentes.CampoTexto txtStock;
    private gestioninventario.vista.componentes.CampoTexto txtTallas;
    // End of variables declaration//GEN-END:variables
}
