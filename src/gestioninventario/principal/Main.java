package gestioninventario.principal;

import gestioninventario.vista.FrmLogin;
import gestioninventario.vista.componentes.Estilos;
import javax.swing.SwingUtilities;

/**
 * Punto de entrada del sistema: es la clase que se ejecuta al darle "Run" al proyecto.
 *
 * 1. Aplica el tema de colores pastel a toda la aplicación.
 * 2. Abre la ventana de inicio de sesión.
 *
 * SwingUtilities.invokeLater hace que las ventanas se creen en el hilo
 * de la interfaz gráfica, que es la forma correcta de iniciar Swing.
 *
 * @author Master
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Estilos.aplicarTema();
            new FrmLogin().setVisible(true);
        });
    }
}
