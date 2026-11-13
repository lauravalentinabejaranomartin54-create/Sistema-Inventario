package gestioninventario.util;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import gestioninventario.modelo.DetallePedido;
import gestioninventario.modelo.Pedido;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public final class ReciboPdf {
    public static final String NEGOCIO = "Mi Inventario";
    public static final String DATOS_NEGOCIO = "Tecnología · Ropa · Peluches · Portarretratos";
    public static final String LOGO = "logo.png";

    private static final Color MORADO = new Color(0x8E, 0x62, 0xC4);
    private static final Color MORADO_OSCURO = new Color(0x5E, 0x3A, 0x8C);
    private static final Color LILA = new Color(0xF3, 0xEC, 0xFB);
    private static final Color ROSADO = new Color(0xFB, 0xE3, 0xEF);
    private static final Color TEXTO = new Color(0x4A, 0x2F, 0x5E);
    private static final Color BORDE = new Color(0xDC, 0xC8, 0xEE);

    private static final Font F_NEGOCIO = new Font(Font.HELVETICA, 22, Font.BOLD, Color.WHITE);
    private static final Font F_BLANCO = new Font(Font.HELVETICA, 10, Font.NORMAL, Color.WHITE);
    private static final Font F_TITULO = new Font(Font.HELVETICA, 13, Font.BOLD, MORADO_OSCURO);
    private static final Font F_ETIQUETA = new Font(Font.HELVETICA, 9, Font.BOLD, MORADO);
    private static final Font F_TEXTO = new Font(Font.HELVETICA, 10, Font.NORMAL, TEXTO);
    private static final Font F_NEGRITA = new Font(Font.HELVETICA, 10, Font.BOLD, TEXTO);
    private static final Font F_TOTAL = new Font(Font.HELVETICA, 14, Font.BOLD, MORADO_OSCURO);
    private static final Font F_PEQUENA = new Font(Font.HELVETICA, 8, Font.ITALIC, new Color(0x8A, 0x6F, 0xA0));

    private ReciboPdf() {
    }

    public static void generar(Pedido pedido, File destino) throws IOException {
        Document documento = new Document(PageSize.LETTER, 42, 42, 36, 36);
        try (FileOutputStream salida = new FileOutputStream(destino)) {
            PdfWriter.getInstance(documento, salida);
            documento.open();
            documento.add(encabezado(pedido));
            documento.add(espacio(14));
            documento.add(datosCliente(pedido));
            documento.add(espacio(14));
            documento.add(tablaProductos(pedido));
            documento.add(espacio(6));
            documento.add(total(pedido));
            documento.add(espacio(28));
            documento.add(firma(pedido));
            documento.add(espacio(24));
            Paragraph nota = new Paragraph("Este documento es un recibo de entrega del pedido. No reemplaza la factura electrónica.", F_PEQUENA);
            nota.setAlignment(Element.ALIGN_CENTER);
            documento.add(nota);
            documento.close();
        } catch (DocumentException e) {
            throw new IOException("No se pudo crear el PDF: " + e.getMessage(), e);
        }
    }

    private static PdfPTable encabezado(Pedido pedido) throws IOException, DocumentException {
        PdfPTable tabla = new PdfPTable(new float[]{3f, 2f});
        tabla.setWidthPercentage(100);
        PdfPCell izquierda = celda(MORADO, 16);
        File logo = new File(LOGO);
        if (logo.exists()) {
            Image imagen = Image.getInstance(logo.getAbsolutePath());
            imagen.scaleToFit(120, 50);
            izquierda.addElement(imagen);
        }
        izquierda.addElement(new Paragraph(NEGOCIO, F_NEGOCIO));
        izquierda.addElement(new Paragraph(DATOS_NEGOCIO, F_BLANCO));
        tabla.addCell(izquierda);
        PdfPCell derecha = celda(MORADO_OSCURO, 16);
        Paragraph titulo = new Paragraph("RECIBO DE PEDIDO", new Font(Font.HELVETICA, 13, Font.BOLD, Color.WHITE));
        titulo.setAlignment(Element.ALIGN_RIGHT);
        derecha.addElement(titulo);
        Paragraph numero = new Paragraph(String.format("N.º %06d", pedido.getId()), new Font(Font.HELVETICA, 18, Font.BOLD, Color.WHITE));
        numero.setAlignment(Element.ALIGN_RIGHT);
        derecha.addElement(numero);
        String fecha = pedido.getFecha() == null ? "" : pedido.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a"));
        Paragraph pFecha = new Paragraph(fecha, F_BLANCO);
        pFecha.setAlignment(Element.ALIGN_RIGHT);
        derecha.addElement(pFecha);
        tabla.addCell(derecha);
        return tabla;
    }

    private static PdfPTable datosCliente(Pedido pedido) {
        PdfPTable tabla = new PdfPTable(4);
        tabla.setWidthPercentage(100);
        agregarDato(tabla, "CLIENTE", pedido.getCliente());
        agregarDato(tabla, "DOCUMENTO", vacio(pedido.getDocumento()));
        agregarDato(tabla, "TIPO DE VENTA", pedido.getTipoVenta());
        agregarDato(tabla, "ATENDIDO POR", vacio(pedido.getUsuario()));
        return tabla;
    }

    private static void agregarDato(PdfPTable tabla, String etiqueta, String valor) {
        PdfPCell c = celda(LILA, 8);
        c.setBorderColor(Color.WHITE);
        c.setBorder(Rectangle.BOX);
        c.setBorderWidth(2);
        c.addElement(new Paragraph(etiqueta, F_ETIQUETA));
        c.addElement(new Paragraph(valor, F_NEGRITA));
        tabla.addCell(c);
    }

    private static PdfPTable tablaProductos(Pedido pedido) {
        PdfPTable tabla = new PdfPTable(new float[]{1.3f, 4f, 1f, 1.7f, 1.8f});
        tabla.setWidthPercentage(100);
        tabla.setHeaderRows(1);
        String[] titulos = {"Referencia", "Descripción", "Cant.", "Precio unit.", "Subtotal"};
        for (int i = 0; i < titulos.length; i++) {
            PdfPCell c = new PdfPCell(new Phrase(titulos[i], new Font(Font.HELVETICA, 10, Font.BOLD, Color.WHITE)));
            c.setBackgroundColor(MORADO);
            c.setBorderColor(MORADO);
            c.setPadding(7);
            c.setHorizontalAlignment(i >= 2 ? Element.ALIGN_RIGHT : Element.ALIGN_LEFT);
            tabla.addCell(c);
        }
        int fila = 0;
        for (DetallePedido d : pedido.getDetalles()) {
            Color fondo = fila++ % 2 == 0 ? Color.WHITE : LILA;
            tabla.addCell(dato(d.getReferencia(), fondo, Element.ALIGN_LEFT));
            tabla.addCell(dato(d.getDescripcion(), fondo, Element.ALIGN_LEFT));
            tabla.addCell(dato(String.valueOf(d.getCantidad()), fondo, Element.ALIGN_RIGHT));
            tabla.addCell(dato(Formatos.moneda(d.getPrecioUnitario()), fondo, Element.ALIGN_RIGHT));
            tabla.addCell(dato(Formatos.moneda(d.getSubtotal()), fondo, Element.ALIGN_RIGHT));
        }
        return tabla;
    }

    private static PdfPCell dato(String texto, Color fondo, int alineacion) {
        PdfPCell c = new PdfPCell(new Phrase(texto, F_TEXTO));
        c.setBackgroundColor(fondo);
        c.setBorderColor(BORDE);
        c.setPadding(6);
        c.setHorizontalAlignment(alineacion);
        return c;
    }

    private static PdfPTable total(Pedido pedido) {
        int unidades = 0;
        for (DetallePedido d : pedido.getDetalles()) {
            unidades += d.getCantidad();
        }
        PdfPTable tabla = new PdfPTable(new float[]{6f, 3f});
        tabla.setWidthPercentage(100);
        PdfPCell vacia = new PdfPCell(new Phrase(unidades + " unidades en el pedido", F_TEXTO));
        vacia.setBorder(Rectangle.NO_BORDER);
        vacia.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tabla.addCell(vacia);
        PdfPCell c = new PdfPCell(new Phrase("TOTAL  " + Formatos.moneda(pedido.getTotal()), F_TOTAL));
        c.setBackgroundColor(ROSADO);
        c.setBorderColor(ROSADO);
        c.setPadding(10);
        c.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(c);
        return tabla;
    }

    private static PdfPTable firma(Pedido pedido) throws IOException, DocumentException {
        PdfPTable tabla = new PdfPTable(new float[]{1f, 1f});
        tabla.setWidthPercentage(100);
        PdfPCell texto = new PdfPCell();
        texto.setBorder(Rectangle.NO_BORDER);
        texto.addElement(new Paragraph("Recibí a satisfacción los productos descritos en este recibo.", F_TEXTO));
        tabla.addCell(texto);
        PdfPCell cFirma = new PdfPCell();
        cFirma.setBorder(Rectangle.NO_BORDER);
        if (pedido.getFirma() != null && pedido.getFirma().length > 0) {
            Image imagen = Image.getInstance(pedido.getFirma());
            imagen.scaleToFit(230, 80);
            imagen.setAlignment(Element.ALIGN_CENTER);
            cFirma.addElement(imagen);
        }
        Paragraph linea = new Paragraph("_____________________________________", F_TEXTO);
        linea.setAlignment(Element.ALIGN_CENTER);
        cFirma.addElement(linea);
        Paragraph nombre = new Paragraph("Firma del cliente: " + pedido.getCliente(), F_NEGRITA);
        nombre.setAlignment(Element.ALIGN_CENTER);
        cFirma.addElement(nombre);
        tabla.addCell(cFirma);
        return tabla;
    }

    private static PdfPCell celda(Color fondo, float relleno) {
        PdfPCell c = new PdfPCell();
        c.setBackgroundColor(fondo);
        c.setBorder(Rectangle.NO_BORDER);
        c.setPadding(relleno);
        return c;
    }

    private static Paragraph espacio(float alto) {
        Paragraph p = new Paragraph(" ");
        p.setSpacingBefore(alto - 12);
        return p;
    }

    private static String vacio(String texto) {
        return texto == null || texto.isBlank() ? "—" : texto;
    }
}
