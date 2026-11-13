package gestioninventario.vista;

import gestioninventario.controlador.PedidoControlador;
import gestioninventario.controlador.PelucheControlador;
import gestioninventario.controlador.PortarretratoControlador;
import gestioninventario.controlador.RopaControlador;
import gestioninventario.controlador.TecnologiaControlador;
import gestioninventario.modelo.ArticuloInventario;
import gestioninventario.modelo.DetallePedido;
import gestioninventario.modelo.Pedido;
import gestioninventario.modelo.Usuario;
import gestioninventario.util.Formatos;
import gestioninventario.util.ReciboPdf;
import gestioninventario.vista.componentes.Estilos;
import gestioninventario.vista.componentes.Mensajes;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class FrmPedido extends javax.swing.JFrame {
    private final JFrame menu;
    private final Usuario usuario;
    private final PedidoControlador controlador;
    private final List<DetallePedido> detalles = new ArrayList<>();
    private final List<ArticuloInventario> articulos = new ArrayList<>();
    private DefaultTableModel modeloTabla;

    public FrmPedido(JFrame menu, Usuario usuario) {
        initComponents();
        this.menu = menu;
        this.usuario = usuario;
        controlador = new PedidoControlador();
        configurar();
        cargarProductos();
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
        pnlDatos = new gestioninventario.vista.componentes.PanelRedondeado();
        lblDatos = new gestioninventario.vista.componentes.Titulo();
        lblCliente = new gestioninventario.vista.componentes.Etiqueta();
        txtCliente = new gestioninventario.vista.componentes.CampoTexto();
        lblDocumento = new gestioninventario.vista.componentes.Etiqueta();
        txtDocumento = new gestioninventario.vista.componentes.CampoTexto();
        lblTipoVenta = new gestioninventario.vista.componentes.Etiqueta();
        cmbTipoVenta = new javax.swing.JComboBox<>();
        lblCategoria = new gestioninventario.vista.componentes.Etiqueta();
        cmbCategoria = new javax.swing.JComboBox<>();
        lblProducto = new gestioninventario.vista.componentes.Etiqueta();
        cmbProducto = new javax.swing.JComboBox<>();
        lblCantidad = new gestioninventario.vista.componentes.Etiqueta();
        txtCantidad = new gestioninventario.vista.componentes.CampoTexto();
        lblInfo = new gestioninventario.vista.componentes.Subtitulo();
        btnAgregar = new gestioninventario.vista.componentes.BotonRedondeado();
        pnlDetalle = new gestioninventario.vista.componentes.PanelRedondeado();
        lblDetalle = new gestioninventario.vista.componentes.Titulo();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalle = new javax.swing.JTable();
        btnQuitar = new gestioninventario.vista.componentes.BotonRedondeado();
        lblTotal = new gestioninventario.vista.componentes.Titulo();
        lblFirma = new gestioninventario.vista.componentes.Etiqueta();
        btnBorrarFirma = new gestioninventario.vista.componentes.BotonEnlace();
        pnlFirma = new gestioninventario.vista.componentes.PanelFirma();
        btnConfirmar = new gestioninventario.vista.componentes.BotonRedondeado();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Inventario - Nuevo pedido");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pnlFondo.setColorInicio(new java.awt.Color(255, 244, 249));

        lblTitulo.setForeground(new java.awt.Color(142, 98, 196));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitulo.setText("Nuevo pedido");

        lblSubtitulo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblSubtitulo.setText("Elige los productos, pide la firma del cliente y descarga el recibo en PDF");

        btnVolver.setForeground(new java.awt.Color(74, 47, 94));
        btnVolver.setText("Volver al menú");
        btnVolver.setColorFondo(new java.awt.Color(231, 218, 247));
        btnVolver.addActionListener(this::btnVolverActionPerformed);

        lblDatos.setFont(new java.awt.Font("Segoe Script", 1, 26));
        lblDatos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDatos.setText("Datos del pedido");

        lblCliente.setText("Cliente");

        txtCliente.setToolTipText("Nombre de quien recibe el pedido");

        lblDocumento.setText("Documento");

        txtDocumento.setToolTipText("Cédula o NIT (opcional)");

        lblTipoVenta.setText("Tipo de venta");

        cmbTipoVenta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Al detal", "Al por mayor" }));
        cmbTipoVenta.addActionListener(this::cmbTipoVentaActionPerformed);

        lblCategoria.setText("Categoría");

        cmbCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tecnología", "Ropa", "Peluches", "Portarretratos" }));
        cmbCategoria.addActionListener(this::cmbCategoriaActionPerformed);

        lblProducto.setText("Producto");

        cmbProducto.addActionListener(this::cmbProductoActionPerformed);

        lblCantidad.setText("Cantidad");

        txtCantidad.setToolTipText("Unidades que se lleva el cliente");

        lblInfo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblInfo.setText("Elige un producto");

        btnAgregar.setText("Agregar al pedido");
        btnAgregar.setColorFondo(new java.awt.Color(142, 98, 196));
        btnAgregar.addActionListener(this::btnAgregarActionPerformed);

        javax.swing.GroupLayout pnlDatosLayout = new javax.swing.GroupLayout(pnlDatos);
        pnlDatos.setLayout(pnlDatosLayout);
        pnlDatosLayout.setHorizontalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTipoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(txtDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(cmbTipoVenta, 0, 200, Short.MAX_VALUE)
                    .addComponent(cmbCategoria, 0, 200, Short.MAX_VALUE)
                    .addComponent(cmbProducto, 0, 200, Short.MAX_VALUE)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
            .addComponent(lblInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlDatosLayout.setVerticalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addComponent(lblDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTipoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(lblInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE)
                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lblDetalle.setFont(new java.awt.Font("Segoe Script", 1, 26));
        lblDetalle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDetalle.setText("Productos del pedido");

        tblDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Referencia", "Descripción", "Cantidad", "Precio unitario", "Subtotal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblDetalle);

        btnQuitar.setText("Quitar producto");
        btnQuitar.setColorFondo(new java.awt.Color(201, 80, 122));
        btnQuitar.addActionListener(this::btnQuitarActionPerformed);

        lblTotal.setForeground(new java.awt.Color(212, 93, 147));
        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotal.setText("Total: $ 0");

        lblFirma.setText("Firma del cliente");

        btnBorrarFirma.setText("Borrar firma");
        btnBorrarFirma.addActionListener(this::btnBorrarFirmaActionPerformed);

        btnConfirmar.setText("Confirmar pedido y descargar recibo");
        btnConfirmar.setColorFondo(new java.awt.Color(212, 93, 147));
        btnConfirmar.addActionListener(this::btnConfirmarActionPerformed);

        javax.swing.GroupLayout pnlDetalleLayout = new javax.swing.GroupLayout(pnlDetalle);
        pnlDetalle.setLayout(pnlDetalleLayout);
        pnlDetalleLayout.setHorizontalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addComponent(btnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addComponent(lblFirma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnBorrarFirma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(pnlFirma, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addComponent(btnConfirmar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlDetalleLayout.setVerticalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addComponent(lblDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblFirma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBorrarFirma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(pnlFirma, 150, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(pnlDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(pnlDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)))
                .addGap(24, 24, 24))
        );
        pnlFondoLayout.setVerticalGroup(
            pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFondoLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(pnlFondoLayout.createSequentialGroup()
                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSubtitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDatos, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
                    .addComponent(pnlDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE))
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

    private void cmbTipoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoVentaActionPerformed
        for (int i = 0; i < detalles.size(); i++) {
            detalles.get(i).setPrecioUnitario(precio(articulos.get(i)));
        }
        actualizarTabla();
        mostrarInfoProducto();
    }//GEN-LAST:event_cmbTipoVentaActionPerformed

    private void cmbCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCategoriaActionPerformed
        cargarProductos();
    }//GEN-LAST:event_cmbCategoriaActionPerformed

    private void cmbProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProductoActionPerformed
        mostrarInfoProducto();
    }//GEN-LAST:event_cmbProductoActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        try {
            ArticuloInventario articulo = (ArticuloInventario) cmbProducto.getSelectedItem();
            if (articulo == null) {
                throw new IllegalArgumentException("Elige un producto");
            }
            int cantidad = Formatos.parsearEntero(txtCantidad.getText(), "Cantidad");
            if (cantidad <= 0) {
                throw new IllegalArgumentException("La cantidad debe ser mayor que cero");
            }
            int enPedido = cantidadEnPedido(articulo);
            if (enPedido + cantidad > articulo.getStock()) {
                throw new IllegalArgumentException("Solo hay " + articulo.getStock() + " unidades de "
                        + articulo.getReferencia() + (enPedido > 0 ? " (ya agregaste " + enPedido + ")" : ""));
            }
            int indice = indiceEnPedido(articulo);
            if (indice >= 0) {
                DetallePedido d = detalles.get(indice);
                d.setCantidad(d.getCantidad() + cantidad);
            } else {
                detalles.add(new DetallePedido(categoriaActual(), articulo.getId(), articulo.getReferencia(),
                        articulo.getDescripcion(), cantidad, precio(articulo)));
                articulos.add(articulo);
            }
            txtCantidad.setText("");
            actualizarTabla();
            mostrarInfoProducto();
        } catch (IllegalArgumentException e) {
            Mensajes.advertencia(this, e.getMessage());
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        int fila = tblDetalle.getSelectedRow();
        if (fila < 0) {
            Mensajes.advertencia(this, "Selecciona en la tabla el producto que quieres quitar");
            return;
        }
        detalles.remove(fila);
        articulos.remove(fila);
        actualizarTabla();
        mostrarInfoProducto();
    }//GEN-LAST:event_btnQuitarActionPerformed

    private void btnBorrarFirmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarFirmaActionPerformed
        pnlFirma.limpiar();
    }//GEN-LAST:event_btnBorrarFirmaActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        Pedido pedido = new Pedido();
        pedido.setCliente(txtCliente.getText().trim());
        String documento = txtDocumento.getText().trim();
        pedido.setDocumento(documento.isEmpty() ? null : documento);
        pedido.setTipoVenta((String) cmbTipoVenta.getSelectedItem());
        pedido.setFirma(pnlFirma.getFirmaPng());
        pedido.setUsuarioId(usuario.getId());
        pedido.setUsuario(usuario.getUsuario());
        pedido.setDetalles(new ArrayList<>(detalles));
        try {
            controlador.validar(pedido);
            File destino = elegirDestino();
            if (destino == null) {
                return;
            }
            int id = controlador.registrar(pedido);
            try {
                ReciboPdf.generar(pedido, destino);
            } catch (IOException e) {
                Mensajes.advertencia(this, "El pedido N.º " + id + " se guardó, pero no se pudo crear el PDF:\n" + e.getMessage());
                return;
            }
            Mensajes.exito(this, "Pedido N.º " + id + " registrado. Se descontó el stock y el recibo quedó en:\n"
                    + destino.getAbsolutePath());
            abrirPdf(destino);
            nuevoPedido();
        } catch (IllegalArgumentException e) {
            Mensajes.advertencia(this, e.getMessage());
        } catch (SQLException e) {
            Mensajes.errorBaseDatos(this, e);
        }
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void configurar() {
        modeloTabla = (DefaultTableModel) tblDetalle.getModel();
        Estilos.estilizarTabla(tblDetalle, jScrollPane1, Estilos.MORADO);
        int[] anchos = {120, 380, 90, 140, 140};
        for (int i = 0; i < anchos.length; i++) {
            tblDetalle.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        for (JComboBox<?> combo : List.of(cmbTipoVenta, cmbCategoria, cmbProducto)) {
            combo.setFont(Estilos.TEXTO_NORMAL);
            combo.setBackground(java.awt.Color.WHITE);
            combo.setForeground(Estilos.TEXTO);
        }
    }

    private void cargarProductos() {
        try {
            List<? extends ArticuloInventario> lista;
            switch (categoriaActual()) {
                case "ropa":
                    lista = new RopaControlador().listar();
                    break;
                case "peluches":
                    lista = new PelucheControlador().listar();
                    break;
                case "portarretratos":
                    lista = new PortarretratoControlador().listar();
                    break;
                default:
                    lista = new TecnologiaControlador().listar();
            }
            DefaultComboBoxModel<ArticuloInventario> modelo = new DefaultComboBoxModel<>();
            for (ArticuloInventario a : lista) {
                modelo.addElement(a);
            }
            cmbProducto.setModel(modelo);
            mostrarInfoProducto();
        } catch (SQLException e) {
            Mensajes.errorBaseDatos(this, e);
        }
    }

    private void mostrarInfoProducto() {
        ArticuloInventario a = (ArticuloInventario) cmbProducto.getSelectedItem();
        if (a == null) {
            lblInfo.setText("No hay productos en esta categoría");
            return;
        }
        int disponibles = a.getStock() - cantidadEnPedido(a);
        lblInfo.setText("Disponibles: " + disponibles + " unidades   ·   Precio: " + Formatos.moneda(precio(a)));
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        for (DetallePedido d : detalles) {
            modeloTabla.addRow(new Object[]{d.getReferencia(), d.getDescripcion(), d.getCantidad(),
                d.getPrecioUnitario(), d.getSubtotal()});
        }
        double total = 0;
        for (DetallePedido d : detalles) {
            total += d.getSubtotal();
        }
        lblTotal.setText("Total: " + Formatos.moneda(total));
    }

    private String categoriaActual() {
        switch (cmbCategoria.getSelectedIndex()) {
            case 1:
                return "ropa";
            case 2:
                return "peluches";
            case 3:
                return "portarretratos";
            default:
                return "tecnologia";
        }
    }

    private double precio(ArticuloInventario a) {
        return cmbTipoVenta.getSelectedIndex() == 1 ? a.getPrecioMayorista() : a.getPrecioDetal();
    }

    private int indiceEnPedido(ArticuloInventario a) {
        String categoria = categoriaActual();
        for (int i = 0; i < detalles.size(); i++) {
            DetallePedido d = detalles.get(i);
            if (d.getProductoId() == a.getId() && d.getCategoria().equals(categoria)) {
                return i;
            }
        }
        return -1;
    }

    private int cantidadEnPedido(ArticuloInventario a) {
        int i = indiceEnPedido(a);
        return i >= 0 ? detalles.get(i).getCantidad() : 0;
    }

    private File elegirDestino() {
        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Guardar recibo en PDF");
        selector.setFileFilter(new FileNameExtensionFilter("Documento PDF", "pdf"));
        String nombre = "Recibo-" + txtCliente.getText().trim().replaceAll("[^A-Za-z0-9]+", "-") + ".pdf";
        selector.setSelectedFile(new File(System.getProperty("user.home") + File.separator + "Documents", nombre));
        if (selector.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
            return null;
        }
        File archivo = selector.getSelectedFile();
        if (!archivo.getName().toLowerCase().endsWith(".pdf")) {
            archivo = new File(archivo.getParentFile(), archivo.getName() + ".pdf");
        }
        return archivo;
    }

    private void abrirPdf(File archivo) {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(archivo);
            }
        } catch (IOException e) {
            Mensajes.advertencia(this, "No se pudo abrir el PDF automáticamente. Búscalo en:\n" + archivo.getAbsolutePath());
        }
    }

    private void nuevoPedido() {
        detalles.clear();
        articulos.clear();
        txtCliente.setText("");
        txtDocumento.setText("");
        txtCantidad.setText("");
        pnlFirma.limpiar();
        actualizarTabla();
        cargarProductos();
    }

    private void volverAlMenu() {
        dispose();
        menu.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gestioninventario.vista.componentes.BotonRedondeado btnAgregar;
    private gestioninventario.vista.componentes.BotonEnlace btnBorrarFirma;
    private gestioninventario.vista.componentes.BotonRedondeado btnConfirmar;
    private gestioninventario.vista.componentes.BotonRedondeado btnQuitar;
    private gestioninventario.vista.componentes.BotonRedondeado btnVolver;
    private javax.swing.JComboBox<String> cmbCategoria;
    private javax.swing.JComboBox<gestioninventario.modelo.ArticuloInventario> cmbProducto;
    private javax.swing.JComboBox<String> cmbTipoVenta;
    private javax.swing.JScrollPane jScrollPane1;
    private gestioninventario.vista.componentes.Etiqueta lblCantidad;
    private gestioninventario.vista.componentes.Etiqueta lblCategoria;
    private gestioninventario.vista.componentes.Etiqueta lblCliente;
    private gestioninventario.vista.componentes.Titulo lblDatos;
    private gestioninventario.vista.componentes.Titulo lblDetalle;
    private gestioninventario.vista.componentes.Etiqueta lblDocumento;
    private gestioninventario.vista.componentes.Etiqueta lblFirma;
    private gestioninventario.vista.componentes.Subtitulo lblInfo;
    private gestioninventario.vista.componentes.Etiqueta lblProducto;
    private gestioninventario.vista.componentes.Subtitulo lblSubtitulo;
    private gestioninventario.vista.componentes.Etiqueta lblTipoVenta;
    private gestioninventario.vista.componentes.Titulo lblTitulo;
    private gestioninventario.vista.componentes.Titulo lblTotal;
    private gestioninventario.vista.componentes.PanelRedondeado pnlDatos;
    private gestioninventario.vista.componentes.PanelRedondeado pnlDetalle;
    private gestioninventario.vista.componentes.PanelFirma pnlFirma;
    private gestioninventario.vista.componentes.PanelDegradado pnlFondo;
    private javax.swing.JTable tblDetalle;
    private gestioninventario.vista.componentes.CampoTexto txtCantidad;
    private gestioninventario.vista.componentes.CampoTexto txtCliente;
    private gestioninventario.vista.componentes.CampoTexto txtDocumento;
    // End of variables declaration//GEN-END:variables
}
