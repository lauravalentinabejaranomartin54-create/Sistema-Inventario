package gestioninventario.vista.componentes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanelFirma extends JPanel {
    private final List<List<Point>> trazos = new ArrayList<>();
    private Color colorTrazo = new Color(0x2B1D38);

    public PanelFirma() {
        setOpaque(false);
        setPreferredSize(new Dimension(400, 150));
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        MouseAdapter raton = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                List<Point> trazo = new ArrayList<>();
                trazo.add(e.getPoint());
                trazos.add(trazo);
                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (!trazos.isEmpty()) {
                    trazos.get(trazos.size() - 1).add(e.getPoint());
                    repaint();
                }
            }
        };
        addMouseListener(raton);
        addMouseMotionListener(raton);
    }

    public Color getColorTrazo() {
        return colorTrazo;
    }

    public void setColorTrazo(Color colorTrazo) {
        this.colorTrazo = colorTrazo;
        repaint();
    }

    public void limpiar() {
        trazos.clear();
        repaint();
    }

    public boolean estaVacia() {
        for (List<Point> trazo : trazos) {
            if (trazo.size() > 1) {
                return false;
            }
        }
        return true;
    }

    public byte[] getFirmaPng() {
        if (estaVacia()) {
            return new byte[0];
        }
        BufferedImage imagen = new BufferedImage(Math.max(1, getWidth()), Math.max(1, getHeight()),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = imagen.createGraphics();
        dibujarTrazos(g);
        g.dispose();
        try (ByteArrayOutputStream salida = new ByteArrayOutputStream()) {
            ImageIO.write(imagen, "png", salida);
            return salida.toByteArray();
        } catch (IOException e) {
            throw new IllegalStateException("No se pudo guardar la firma", e);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 18, 18);
        g2.setColor(Estilos.BORDE);
        g2.setStroke(new BasicStroke(1.5f));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 18, 18);
        int linea = getHeight() - 34;
        g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1f, new float[]{6f, 6f}, 0f));
        g2.drawLine(24, linea, getWidth() - 24, linea);
        if (estaVacia()) {
            g2.setColor(Estilos.TEXTO_SUAVE);
            g2.setFont(Estilos.SUBTITULO);
            String texto = "Firme aquí con el mouse";
            int ancho = g2.getFontMetrics().stringWidth(texto);
            g2.drawString(texto, (getWidth() - ancho) / 2, linea - 18);
        }
        dibujarTrazos(g2);
        g2.dispose();
    }

    private void dibujarTrazos(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(colorTrazo);
        g.setStroke(new BasicStroke(2.6f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        for (List<Point> trazo : trazos) {
            for (int i = 1; i < trazo.size(); i++) {
                Point a = trazo.get(i - 1);
                Point b = trazo.get(i);
                g.drawLine(a.x, a.y, b.x, b.y);
            }
        }
    }
}
