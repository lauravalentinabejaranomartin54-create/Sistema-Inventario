package gestioninventario.vista.componentes;

import java.awt.Cursor;
import javax.swing.JButton;

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
