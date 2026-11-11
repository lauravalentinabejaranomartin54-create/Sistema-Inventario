package gestioninventario.vista.componentes;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JTextField;

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

    public String getTexto() {
        Object valor = getEditor().getItem();
        return valor == null ? "" : valor.toString().trim();
    }

    public void limpiar() {
        setSelectedItem("");
    }
}
