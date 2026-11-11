package gestioninventario.vista;

import gestioninventario.modelo.Usuario;
import javax.swing.JFrame;

public class FrmMenu extends javax.swing.JFrame {
    public FrmMenu(Usuario usuario) {
        initComponents();
        lblSaludo.setText("Hola, " + usuario.getUsuario() + ". Elige la categoría que quieres gestionar");
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        pnlFondo = new gestioninventario.vista.componentes.PanelDegradado();
        lblTitulo = new gestioninventario.vista.componentes.Titulo();
        lblSaludo = new gestioninventario.vista.componentes.Subtitulo();
        btnTecnologia = new gestioninventario.vista.componentes.BotonRedondeado();
        btnRopa = new gestioninventario.vista.componentes.BotonRedondeado();
        btnPeluches = new gestioninventario.vista.componentes.BotonRedondeado();
        btnPortarretratos = new gestioninventario.vista.componentes.BotonRedondeado();
        btnCerrarSesion = new gestioninventario.vista.componentes.BotonRedondeado();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inventario - Menú principal");

        lblTitulo.setText("Inventario por categorías");

        lblSaludo.setText("Hola. Elige la categoría que quieres gestionar");

        btnTecnologia.setText("<html><center>Tecnología<br><font face=\"Segoe UI\" size=\"4\">Referencias, marcas y precios</font></center></html>");
        btnTecnologia.setFont(new java.awt.Font("Segoe Script", 1, 28));
        btnTecnologia.setRadio(36);
        btnTecnologia.addActionListener(this::btnTecnologiaActionPerformed);

        btnRopa.setText("<html><center>Ropa<br><font face=\"Segoe UI\" size=\"4\">Tipos, colores y tallas</font></center></html>");
        btnRopa.setColorFondo(new java.awt.Color(212, 93, 147));
        btnRopa.setFont(new java.awt.Font("Segoe Script", 1, 28));
        btnRopa.setRadio(36);
        btnRopa.addActionListener(this::btnRopaActionPerformed);

        btnPeluches.setText("<html><center>Peluches<br><font face=\"Segoe UI\" size=\"4\">Colores, tamaños y referencias</font></center></html>");
        btnPeluches.setColorFondo(new java.awt.Color(140, 123, 209));
        btnPeluches.setFont(new java.awt.Font("Segoe Script", 1, 28));
        btnPeluches.setRadio(36);
        btnPeluches.addActionListener(this::btnPeluchesActionPerformed);

        btnPortarretratos.setText("<html><center>Portarretratos<br><font face=\"Segoe UI\" size=\"4\">Referencias y precios</font></center></html>");
        btnPortarretratos.setColorFondo(new java.awt.Color(181, 105, 159));
        btnPortarretratos.setFont(new java.awt.Font("Segoe Script", 1, 28));
        btnPortarretratos.setRadio(36);
        btnPortarretratos.addActionListener(this::btnPortarretratosActionPerformed);

        btnCerrarSesion.setForeground(new java.awt.Color(74, 47, 94));
        btnCerrarSesion.setText("Cerrar sesión");
        btnCerrarSesion.setColorFondo(new java.awt.Color(231, 218, 247));
        btnCerrarSesion.addActionListener(this::btnCerrarSesionActionPerformed);

        javax.swing.GroupLayout pnlFondoLayout = new javax.swing.GroupLayout(pnlFondo);
        pnlFondo.setLayout(pnlFondoLayout);
        pnlFondoLayout.setHorizontalGroup(
            pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFondoLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSaludo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlFondoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlFondoLayout.createSequentialGroup()
                        .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnPeluches)
                            .addComponent(btnTecnologia, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnRopa)
                            .addComponent(btnPortarretratos, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE))))
                .addGap(50, 50, 50))
        );
        pnlFondoLayout.setVerticalGroup(
            pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFondoLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(lblSaludo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTecnologia, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                    .addComponent(btnRopa))
                .addGap(26, 26, 26)
                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPeluches, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                    .addComponent(btnPortarretratos))
                .addGap(26, 26, 26)
                .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        getContentPane().add(pnlFondo, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTecnologiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTecnologiaActionPerformed
        abrirCategoria(new FrmTecnologia(this));
    }//GEN-LAST:event_btnTecnologiaActionPerformed

    private void btnRopaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRopaActionPerformed
        abrirCategoria(new FrmRopa(this));
    }//GEN-LAST:event_btnRopaActionPerformed

    private void btnPeluchesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPeluchesActionPerformed
        abrirCategoria(new FrmPeluches(this));
    }//GEN-LAST:event_btnPeluchesActionPerformed

    private void btnPortarretratosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPortarretratosActionPerformed
        abrirCategoria(new FrmPortarretratos(this));
    }//GEN-LAST:event_btnPortarretratosActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        dispose();
        new FrmLogin().setVisible(true);
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void abrirCategoria(JFrame ventana) {
        ventana.setVisible(true);
        setVisible(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gestioninventario.vista.componentes.BotonRedondeado btnCerrarSesion;
    private gestioninventario.vista.componentes.BotonRedondeado btnPeluches;
    private gestioninventario.vista.componentes.BotonRedondeado btnPortarretratos;
    private gestioninventario.vista.componentes.BotonRedondeado btnRopa;
    private gestioninventario.vista.componentes.BotonRedondeado btnTecnologia;
    private gestioninventario.vista.componentes.Subtitulo lblSaludo;
    private gestioninventario.vista.componentes.Titulo lblTitulo;
    private gestioninventario.vista.componentes.PanelDegradado pnlFondo;
    // End of variables declaration//GEN-END:variables
}
