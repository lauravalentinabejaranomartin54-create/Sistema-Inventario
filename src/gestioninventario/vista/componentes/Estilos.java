package gestioninventario.vista.componentes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 * "Hoja de estilos" del sistema: aquí están TODOS los colores y fuentes.
 * Si quieres cambiar un color en todo el programa, cámbialo aquí.
 *
 * @author Master
 */
public final class Estilos {

    // ===== Paleta pastel rosado / morado =====
    public static final Color FONDO = new Color(0xFFF4F9);          // rosado casi blanco
    public static final Color ROSADO = new Color(0xF9D3E5);         // rosado pastel
    public static final Color LILA = new Color(0xE7DAF7);           // lila pastel
    public static final Color LILA_CLARO = new Color(0xF4EEFC);     // lila muy suave
    public static final Color MORADO = new Color(0x8E62C4);         // morado principal
    public static final Color MORADO_OSCURO = new Color(0x5E3A8C);  // títulos
    public static final Color ROSA_FUERTE = new Color(0xD45D93);    // acento rosado
    public static final Color TEXTO = new Color(0x4A2F5E);          // texto ciruela
    public static final Color TEXTO_SUAVE = new Color(0x8A6FA0);    // subtítulos
    public static final Color BORDE = new Color(0xDCC8EE);          // bordes de campos

    // ===== Fuentes =====
    // Se usa la primera fuente que esté instalada en el computador
    private static final String FUENTE_DECORATIVA =
            elegirFuente("Segoe Script", "Segoe Print", "Lucida Handwriting", "Serif");
    private static final String FUENTE_BASE =
            elegirFuente("Quicksand", "Nunito", "Poppins", "Segoe UI", "Verdana", "SansSerif");

    public static final Font TITULO = new Font(FUENTE_DECORATIVA, Font.BOLD, 34);
    public static final Font SUBTITULO = new Font(FUENTE_BASE, Font.PLAIN, 15);
    public static final Font ETIQUETA = new Font(FUENTE_BASE, Font.BOLD, 13);
    public static final Font TEXTO_NORMAL = new Font(FUENTE_BASE, Font.PLAIN, 14);
    public static final Font TEXTO_NEGRITA = new Font(FUENTE_BASE, Font.BOLD, 14);

    // Constructor privado: esta clase solo tiene constantes y métodos static
    private Estilos() {
    }

    private static String elegirFuente(String... candidatas) {
        Set<String> instaladas = new HashSet<>(Arrays.asList(
                GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()));
        for (String fuente : candidatas) {
            if (instaladas.contains(fuente)) {
                return fuente;
            }
        }
        return candidatas[candidatas.length - 1];
    }

    /**
     * Aplica los colores a los elementos estándar de Swing (mensajes,
     * barras de desplazamiento, combos...). Se llama una vez desde Main.
     */
    public static void aplicarTema() {
        try {
            // Tema multiplataforma: respeta nuestros colores en todos los sistemas
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            // Si falla seguimos con el tema por defecto
        }
        UIManager.put("Panel.background", FONDO);
        UIManager.put("OptionPane.background", FONDO);
        UIManager.put("OptionPane.messageFont", TEXTO_NORMAL);
        UIManager.put("OptionPane.messageForeground", TEXTO);
        UIManager.put("OptionPane.buttonFont", TEXTO_NEGRITA);
        UIManager.put("Button.background", LILA);
        UIManager.put("Button.foreground", TEXTO);
        UIManager.put("Button.select", ROSADO);
        UIManager.put("Button.focus", LILA);
        UIManager.put("Button.gradient", Arrays.asList(0.3f, 0f, LILA_CLARO, LILA, LILA));
        UIManager.put("TextField.selectionBackground", ROSADO);
        UIManager.put("PasswordField.selectionBackground", ROSADO);
        UIManager.put("ComboBox.background", Color.WHITE);
        UIManager.put("ComboBox.selectionBackground", ROSADO);
        UIManager.put("ComboBox.selectionForeground", TEXTO);
        UIManager.put("ComboBox.font", TEXTO_NORMAL);
        UIManager.put("ComboBox.buttonBackground", LILA);
        UIManager.put("ComboBox.buttonShadow", BORDE);
        UIManager.put("ComboBox.buttonDarkShadow", MORADO);
        UIManager.put("ComboBox.buttonHighlight", LILA_CLARO);
        UIManager.put("List.selectionBackground", ROSADO);
        UIManager.put("ScrollBar.thumb", LILA);
        UIManager.put("ScrollBar.thumbHighlight", LILA_CLARO);
        UIManager.put("ScrollBar.thumbShadow", BORDE);
        UIManager.put("ScrollBar.track", FONDO);
        UIManager.put("ToolTip.background", LILA_CLARO);
        UIManager.put("ToolTip.foreground", TEXTO);
        UIManager.put("ToolTip.font", TEXTO_NORMAL);
    }

    /**
     * Da estilo a un campo de texto: letra, colores y borde redondeado
     * que se vuelve morado cuando el campo tiene el cursor.
     * Lo usan CampoTexto y CampoContrasena.
     */
    public static void estilizarCampo(JTextField campo) {
        campo.setFont(TEXTO_NORMAL);
        campo.setForeground(TEXTO);
        campo.setBackground(Color.WHITE);
        campo.setCaretColor(MORADO);
        campo.setSelectionColor(ROSADO);
        Border normal = bordeCampo(BORDE);
        Border enfocado = bordeCampo(MORADO);
        campo.setBorder(normal);
        campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                campo.setBorder(enfocado);
            }

            @Override
            public void focusLost(FocusEvent e) {
                campo.setBorder(normal);
            }
        });
    }

    private static Border bordeCampo(Color color) {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color, 2, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10));
    }

    /**
     * Da estilo a una tabla: filas intercaladas, precios con formato $,
     * encabezado del color indicado y anchos de columna sugeridos.
     */
    public static void estilizarTabla(JTable tabla, JScrollPane scroll, Color colorEncabezado) {
        tabla.setFont(TEXTO_NORMAL);
        tabla.setForeground(TEXTO);
        tabla.setBackground(Color.WHITE);
        tabla.setRowHeight(30);
        tabla.setSelectionBackground(ROSADO);
        tabla.setSelectionForeground(TEXTO);
        tabla.setGridColor(LILA);
        tabla.setShowVerticalLines(false);
        tabla.setFillsViewportHeight(true);

        RenderizadorCelda renderizador = new RenderizadorCelda();
        tabla.setDefaultRenderer(Object.class, renderizador);
        tabla.setDefaultRenderer(String.class, renderizador);
        tabla.setDefaultRenderer(Number.class, renderizador);
        tabla.setDefaultRenderer(Integer.class, renderizador);
        tabla.setDefaultRenderer(Double.class, renderizador);

        JTableHeader encabezado = tabla.getTableHeader();
        encabezado.setReorderingAllowed(false);
        encabezado.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object valor,
                    boolean seleccionado, boolean foco, int fila, int columna) {
                super.getTableCellRendererComponent(t, valor, seleccionado, foco, fila, columna);
                // Títulos de varias palabras en dos líneas: "Precio<br>importador"
                String texto = valor == null ? "" : valor.toString();
                int espacio = texto.indexOf(' ');
                if (espacio > 0) {
                    setText("<html><div style='text-align:center'>" + texto.substring(0, espacio)
                            + "<br>" + texto.substring(espacio + 1) + "</div></html>");
                }
                setBackground(colorEncabezado);
                setForeground(Color.WHITE);
                setFont(TEXTO_NEGRITA);
                setHorizontalAlignment(SwingConstants.CENTER);
                setBorder(BorderFactory.createEmptyBorder(10, 6, 10, 6));
                return this;
            }
        });

        // Anchos sugeridos: ID angosta, Característica ancha
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            String titulo = tabla.getColumnName(i);
            int ancho = switch (titulo) {
                case "ID" -> 45;
                case "Característica" -> 170;
                case "Stock" -> 65;
                default -> titulo.startsWith("Precio") ? 120 : 100;
            };
            tabla.getColumnModel().getColumn(i).setPreferredWidth(ancho);
        }
        if (tabla.getColumnCount() > 0) {
            tabla.getColumnModel().getColumn(0).setMaxWidth(70);
        }

        scroll.setBorder(BorderFactory.createLineBorder(BORDE, 1, true));
        scroll.getViewport().setBackground(Color.WHITE);
    }
}
