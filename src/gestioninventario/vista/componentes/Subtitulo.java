package gestioninventario.vista.componentes;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Subtitulo extends JLabel {
    public Subtitulo() {
        setFont(Estilos.SUBTITULO);
        setForeground(Estilos.TEXTO_SUAVE);
        setHorizontalAlignment(SwingConstants.CENTER);
    }
}
