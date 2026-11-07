package gestioninventario.vista.componentes;

import javax.swing.JPasswordField;

/**
 * JPasswordField con el estilo del sistema. Se usa igual que un JPasswordField.
 *
 * @author Master
 */
public class CampoContrasena extends JPasswordField {

    public CampoContrasena() {
        Estilos.estilizarCampo(this);
    }
}
