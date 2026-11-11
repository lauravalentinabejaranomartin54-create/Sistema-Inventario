package gestioninventario.vista.componentes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class PanelRedondeado extends JPanel {
    private static final int SOMBRA = 5;
    private Color colorFondo = Color.WHITE;
    private int radio = 30;

    public PanelRedondeado() {
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(20, 24, 24, 28));
    }

    public Color getColorFondo() {
        return colorFondo;
    }

    public void setColorFondo(Color colorFondo) {
        this.colorFondo = colorFondo;
        repaint();
    }

    public int getRadio() {
        return radio;
    }

    public void setRadio(int radio) {
        this.radio = radio;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int ancho = getWidth() - SOMBRA;
        int alto = getHeight() - SOMBRA;
        g2.setColor(new Color(142, 98, 196, 40));
        g2.fillRoundRect(SOMBRA, SOMBRA, ancho, alto, radio, radio);
        g2.setColor(colorFondo);
        g2.fillRoundRect(0, 0, ancho, alto, radio, radio);
        g2.setColor(Estilos.BORDE);
        g2.drawRoundRect(0, 0, ancho - 1, alto - 1, radio, radio);
        g2.dispose();
        super.paintComponent(g);
    }
}
