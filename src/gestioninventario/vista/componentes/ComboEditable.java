package gestioninventario.vista.componentes;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 * Lista desplegable en la que también se puede ESCRIBIR un valor que no
 * esté en la lista (por ejemplo un tipo de ropa nuevo).
 * Las opciones se editan en el diseñador con la propiedad "model".
 *
 * @author Master
 */
public class ComboEditable extends JComboBox<String> {

    public ComboEditable() {
        setEditable(true);
        setFont(Estilos.TEXTO_NORMAL);
        setForeground(Estilos.TEXTO);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Estilos.BORDE, 2, true));
        Component editor = getEditor().getEditorComponent();
        if (editor instanceof JTextField texto) {
            texto.setFont(Estilos.TEXTO_NORMAL);
            texto.setForeground(Estilos.TEXTO);
            texto.setBorder(BorderFactory.createEmptyBorder(5, 8, 5, 8));
        }
    }

    // Devuelve lo que el usuario escribió o eligió
    public String getTexto() {
        Object valor = getEditor().getItem();
        return valor == null ? "" : valor.toString().trim();
    }

    // Deja el combo vacío
    public void limpiar() {
        setSelectedItem("");
    }
}
