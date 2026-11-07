package gestioninventario.vista.componentes;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 * JLabel para los nombres de los campos ("Usuario", "Marca", ...):
 * letra en negrita color ciruela. Se usa igual que un JLabel.
 *
 * @author Master
 */
public class Etiqueta extends JLabel {

    public Etiqueta() {
        setFont(Estilos.ETIQUETA);
        setForeground(Estilos.TEXTO);
        // Pequeño margen derecho para que la última letra nunca se recorte
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 4));
    }
}
