package gestioninventario.vista.componentes;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * JLabel para textos de apoyo debajo de los títulos (lila suave).
 * Se usa igual que un JLabel.
 *
 * @author Master
 */
public class Subtitulo extends JLabel {

    public Subtitulo() {
        setFont(Estilos.SUBTITULO);
        setForeground(Estilos.TEXTO_SUAVE);
        setHorizontalAlignment(SwingConstants.CENTER);
    }
}
