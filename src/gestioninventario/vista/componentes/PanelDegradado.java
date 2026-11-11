package gestioninventario.vista.componentes;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class PanelDegradado extends JPanel {
    private Color colorInicio = Estilos.ROSADO;
    private Color colorFin = Estilos.LILA;

    public PanelDegradado() {
        setOpaque(true);
    }

    public Color getColorInicio() {
        return colorInicio;
    }

    public void setColorInicio(Color colorInicio) {
        this.colorInicio = colorInicio;
        repaint();
    }

    public Color getColorFin() {
        return colorFin;
    }

    public void setColorFin(Color colorFin) {
        this.colorFin = colorFin;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setPaint(new GradientPaint(0, 0, colorInicio, getWidth(), getHeight(), colorFin));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();
    }
}
