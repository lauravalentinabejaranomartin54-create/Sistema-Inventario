package gestioninventario.vista.componentes;

import javax.swing.JTextField;

/**
 * JTextField con el estilo del sistema (borde redondeado lila que se
 * vuelve morado al escribir). Se usa igual que un JTextField normal.
 *
 * @author Master
 */
public class CampoTexto extends JTextField {

    public CampoTexto() {
        Estilos.estilizarCampo(this);
    }
}
