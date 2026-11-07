package gestioninventario.vista.componentes;

import java.awt.Cursor;
import javax.swing.JButton;

/**
 * Botón con apariencia de enlace (solo texto rosado, sin fondo).
 * Ejemplo: "¿No tienes cuenta? Regístrate aquí".
 *
 * @author Master
 */
public class BotonEnlace extends JButton {

    public BotonEnlace() {
        setFont(Estilos.TEXTO_NEGRITA);
        setForeground(Estilos.ROSA_FUERTE);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
