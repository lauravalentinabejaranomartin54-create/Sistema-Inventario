package gestioninventario.vista.componentes;

import gestioninventario.util.Formatos;
import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class RenderizadorCelda extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable tabla, Object valor,
            boolean seleccionado, boolean foco, int fila, int columna) {
        super.getTableCellRendererComponent(tabla, valor, seleccionado, foco, fila, columna);
        setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
        setFont(Estilos.TEXTO_NORMAL);

        if (!seleccionado) {
            setBackground(fila % 2 == 0 ? Color.WHITE : Estilos.LILA_CLARO);
            setForeground(Estilos.TEXTO);
        }

        if (valor instanceof Double precio) {
            setText(Formatos.moneda(precio));
            setHorizontalAlignment(SwingConstants.RIGHT);
        } else if (valor instanceof Integer) {
            setHorizontalAlignment(SwingConstants.CENTER);
        } else {
            setHorizontalAlignment(SwingConstants.LEFT);
        }
        return this;
    }
}
