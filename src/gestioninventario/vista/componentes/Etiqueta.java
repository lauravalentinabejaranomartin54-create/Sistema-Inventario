package gestioninventario.vista.componentes;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class Etiqueta extends JLabel {
    public Etiqueta() {
        setFont(Estilos.ETIQUETA);
        setForeground(Estilos.TEXTO);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 4));
    }
}
