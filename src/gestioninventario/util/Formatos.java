package gestioninventario.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Conversión de textos a números y formato de precios en pesos colombianos.
 *
 * Acepta precios escritos como: 25000, 25.000, 25.000,50, 25000.50 o $ 25.000
 *
 * @author Master
 */
public final class Formatos {

    private static final Locale COLOMBIA = Locale.of("es", "CO");
    // 1.500 / 25.000 / 1.250.000  -> el punto es separador de miles
    private static final Pattern MILES_CON_PUNTO = Pattern.compile("\\d{1,3}(\\.\\d{3})+");
    private static final Pattern DECIMAL = Pattern.compile("\\d+(\\.\\d{1,2})?");
    private static final Pattern ENTERO = Pattern.compile("\\d+");

    private Formatos() {
    }

    // Convierte el texto de un campo en número entero (ej. stock)
    public static int parsearEntero(String texto, String campo) {
        String limpio = texto == null ? "" : texto.trim().replace(" ", "");
        if (limpio.isEmpty()) {
            throw new IllegalArgumentException("El campo " + campo + " es obligatorio");
        }
        if (MILES_CON_PUNTO.matcher(limpio).matches()) {
            limpio = limpio.replace(".", "");
        }
        if (!ENTERO.matcher(limpio).matches()) {
            throw new IllegalArgumentException("El campo " + campo + " debe ser un número entero sin signos");
        }
        try {
            return Integer.parseInt(limpio);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El campo " + campo + " es demasiado grande");
        }
    }

    // Convierte el texto de un campo en precio
    public static double parsearPrecio(String texto, String campo) {
        String limpio = texto == null ? "" : texto.trim().replace("$", "").replace(" ", "");
        if (limpio.isEmpty()) {
            throw new IllegalArgumentException("El campo " + campo + " es obligatorio");
        }
        if (limpio.contains(",")) {
            // Formato colombiano: punto para miles y coma para decimales
            limpio = limpio.replace(".", "").replace(",", ".");
        } else if (MILES_CON_PUNTO.matcher(limpio).matches()) {
            limpio = limpio.replace(".", "");
        }
        if (!DECIMAL.matcher(limpio).matches()) {
            throw new IllegalArgumentException("El campo " + campo
                    + " debe ser un valor numérico, por ejemplo 25000 o 25.000");
        }
        return Double.parseDouble(limpio);
    }

    // Formato para mostrar en la tabla: $ 25.000
    public static String moneda(double valor) {
        NumberFormat formato = NumberFormat.getCurrencyInstance(COLOMBIA);
        formato.setMinimumFractionDigits(0);
        formato.setMaximumFractionDigits(2);
        return formato.format(valor);
    }

    // Formato para editar en el formulario: 25000 o 25000.5
    public static String precioParaEditar(double valor) {
        return new DecimalFormat("0.##", DecimalFormatSymbols.getInstance(Locale.ROOT)).format(valor);
    }
}
