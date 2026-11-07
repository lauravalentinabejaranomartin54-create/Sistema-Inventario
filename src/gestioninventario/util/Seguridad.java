package gestioninventario.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Utilidades para proteger las contraseñas.
 *
 * Usamos PBKDF2 (incluido en Java) con una "sal" aleatoria por usuario.
 * En la base de datos se guarda el texto:  iteraciones:sal:hash
 * Así, aunque alguien vea la tabla usuarios, no puede leer las contraseñas.
 *
 * @author Master
 */
public final class Seguridad {

    private static final String ALGORITMO = "PBKDF2WithHmacSHA256";
    private static final int ITERACIONES = 120_000;
    private static final int LONGITUD_BITS = 256;
    private static final int LONGITUD_SAL = 16;
    private static final SecureRandom ALEATORIO = new SecureRandom();

    private Seguridad() {
    }

    // Genera el hash que se guarda en la base de datos
    public static String generarHash(char[] contrasena) {
        byte[] sal = new byte[LONGITUD_SAL];
        ALEATORIO.nextBytes(sal);
        byte[] hash = pbkdf2(contrasena, sal, ITERACIONES);
        Base64.Encoder b64 = Base64.getEncoder();
        return ITERACIONES + ":" + b64.encodeToString(sal) + ":" + b64.encodeToString(hash);
    }

    // Comprueba si la contraseña escrita coincide con el hash guardado
    public static boolean verificar(char[] contrasena, String hashGuardado) {
        if (contrasena == null || hashGuardado == null) {
            return false;
        }
        String[] partes = hashGuardado.split(":");
        if (partes.length != 3) {
            return false;
        }
        try {
            int iteraciones = Integer.parseInt(partes[0]);
            byte[] sal = Base64.getDecoder().decode(partes[1]);
            byte[] esperado = Base64.getDecoder().decode(partes[2]);
            byte[] calculado = pbkdf2(contrasena, sal, iteraciones);
            // Comparación en tiempo constante
            return MessageDigest.isEqual(esperado, calculado);
        } catch (IllegalArgumentException e) {
            // Hash con formato inválido
            return false;
        }
    }

    private static byte[] pbkdf2(char[] contrasena, byte[] sal, int iteraciones) {
        PBEKeySpec spec = new PBEKeySpec(contrasena, sal, iteraciones, LONGITUD_BITS);
        try {
            return SecretKeyFactory.getInstance(ALGORITMO).generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new IllegalStateException("No fue posible procesar la contraseña", e);
        } finally {
            spec.clearPassword();
        }
    }
}
