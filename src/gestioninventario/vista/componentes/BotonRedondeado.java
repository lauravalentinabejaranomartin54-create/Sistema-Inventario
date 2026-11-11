package gestioninventario.vista.componentes;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.JButton;

public class BotonRedondeado extends JButton {
    private Color colorFondo = Estilos.MORADO;
    private int radio = 22;

    public BotonRedondeado() {
        setForeground(Color.WHITE);
        setFont(Estilos.TEXTO_NEGRITA);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);
        setRolloverEnabled(true);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setBorder(BorderFactory.createEmptyBorder(10, 22, 10, 22));
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
        ButtonModel modelo = getModel();
        Color color = colorFondo;
        if (!isEnabled()) {
            color = Estilos.LILA;
        } else if (modelo.isPressed()) {
            color = mezclar(colorFondo, Color.BLACK, 0.15f);
        } else if (modelo.isRollover()) {
            color = mezclar(colorFondo, Color.WHITE, 0.18f);
        }
        g2.setColor(color);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radio, radio);
        g2.dispose();
        super.paintComponent(g);
    }

    private static Color mezclar(Color a, Color b, float proporcion) {
        float inv = 1 - proporcion;
        return new Color(
                Math.round(a.getRed() * inv + b.getRed() * proporcion),
                Math.round(a.getGreen() * inv + b.getGreen() * proporcion),
                Math.round(a.getBlue() * inv + b.getBlue() * proporcion));
    }
}
