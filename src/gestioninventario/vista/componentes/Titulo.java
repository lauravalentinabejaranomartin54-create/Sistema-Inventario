package gestioninventario.vista.componentes;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Titulo extends JLabel {
    public Titulo() {
        setFont(Estilos.TITULO);
        setForeground(Estilos.MORADO_OSCURO);
        setHorizontalAlignment(SwingConstants.CENTER);
    }
}
