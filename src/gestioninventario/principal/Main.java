package gestioninventario.principal;

import gestioninventario.vista.FrmLogin;
import gestioninventario.vista.componentes.Estilos;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Estilos.aplicarTema();
            new FrmLogin().setVisible(true);
        });
    }
}
