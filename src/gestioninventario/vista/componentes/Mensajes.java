package gestioninventario.vista.componentes;

import java.awt.Component;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public final class Mensajes {
    private Mensajes() {
    }

    public static void exito(Component padre, String mensaje) {
        JOptionPane.showMessageDialog(padre, mensaje, "Listo", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void advertencia(Component padre, String mensaje) {
        JOptionPane.showMessageDialog(padre, mensaje, "Revisa los datos", JOptionPane.WARNING_MESSAGE);
    }

    public static void errorBaseDatos(Component padre, SQLException e) {
        JOptionPane.showMessageDialog(padre,
                "No fue posible comunicarse con la base de datos.\n"
                + "Verifica que MySQL esté encendido y que exista la base gestion_inventario.\n\n"
                + "Detalle: " + e.getMessage(),
                "Error de base de datos", JOptionPane.ERROR_MESSAGE);
    }

    public static boolean confirmar(Component padre, String mensaje) {
        int respuesta = JOptionPane.showConfirmDialog(padre, mensaje, "Confirmar",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return respuesta == JOptionPane.YES_OPTION;
    }
}
