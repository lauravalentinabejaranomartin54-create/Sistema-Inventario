package gestioninventario.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Pattern;

public final class Formatos {
    private static final Locale COLOMBIA = Locale.of("es", "CO");
    private static final Pattern MILES_CON_PUNTO = Pattern.compile("\\d{1,3}(\\.\\d{3})+");
    private static final Pattern DECIMAL = Pattern.compile("\\d+(\\.\\d{1,2})?");
    private static final Pattern ENTERO = Pattern.compile("\\d+");

    private Formatos() {
    }

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

    public static double parsearPrecio(String texto, String campo) {
        String limpio = texto == null ? "" : texto.trim().replace("$", "").replace(" ", "");
        if (limpio.isEmpty()) {
            throw new IllegalArgumentException("El campo " + campo + " es obligatorio");
        }
        if (limpio.contains(",")) {
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

    public static String moneda(double valor) {
        NumberFormat formato = NumberFormat.getCurrencyInstance(COLOMBIA);
        formato.setMinimumFractionDigits(0);
        formato.setMaximumFractionDigits(2);
        return formato.format(valor);
    }

    public static String precioParaEditar(double valor) {
        return new DecimalFormat("0.##", DecimalFormatSymbols.getInstance(Locale.ROOT)).format(valor);
    }
}
