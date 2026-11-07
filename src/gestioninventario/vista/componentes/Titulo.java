package gestioninventario.vista.componentes;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * JLabel para títulos grandes con letra manuscrita (Segoe Script) en morado.
 * Se usa igual que un JLabel.
 *
 * @author Master
 */
public class Titulo extends JLabel {

    public Titulo() {
        setFont(Estilos.TITULO);
        setForeground(Estilos.MORADO_OSCURO);
        setHorizontalAlignment(SwingConstants.CENTER);
    }
}
