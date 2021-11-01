package config;

import br.com.adilson.util.Extenso;
import br.com.adilson.util.PrinterMatrix;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.List;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import modeloDTO.Cliente;
import modeloDTO.Empleado;
import modeloDTO.Venta;

/**
 *
 * @author Petuel
 */
public class Imprimir {

    ImprimirObject io = null;
    private static  final double IGV=0.18;

    public static void imprimirDocumento(List<Venta> lista, String serie,Cliente c,Empleado user) {
        PrinterMatrix printer = new PrinterMatrix();

        Extenso e = new Extenso();

        e.setNumber(50);

        //Definir el tamanho del papel para la impresion  aca 25 lineas y 80 columnas
        printer.setOutSize(100, 31);
        //Imprimir * de la 2da linea a 25 en la columna 1;
        // printer.printCharAtLin(2, 25, 1, "*");
        //Imprimir * 1ra linea de la columa de 1 a 80
        printer.printCharAtCol(1, 1, 31, "-");

        //Imprimir Encabezado nombre del La EMpresa
        printer.printTextWrap(1, 1, 6, 31, "COMPROBANTE DE PAGO");
        //printer.printTextWrap(linI, linE, colI, colE, null);
        printer.printTextWrap(2, 2, 0, 31, "DIRECCION:Km29 Panamericana sur");
        int longitudName = c.getNom().length();
        String texto = "CLI:" + (longitudName > 14 ? c.getNom().substring(0, 14) : c.getNom()) + " Dni:" + (c.getDni().length() > 8 ? c.getDni().substring(0, 8) : c.getDni());
        printer.printTextWrap(3, 3, 0, 31, texto);
        printer.printTextWrap(4, 4, 0, 31, "NRO:" + serie + " Fecha: " + Fecha.FechaBD());
        printer.printCharAtCol(6, 1, 31, "-");
        printer.printTextWrap(6, 7, 1, 4, "ID");
        printer.printTextWrap(6, 7, 4, 15, "PRODUCTO");
        printer.printTextWrap(6, 7, 16, 20, "PRE");
        printer.printTextWrap(6, 7, 20, 26, "CANT");
        printer.printTextWrap(6, 7, 26, 31, "TOTAL");
        printer.printCharAtCol(8, 1, 31, "-");
        int rowCount = lista.size();
        int row;
        int lenHeader = 8;

        double total=0.0;
        for (int i = 0; i < rowCount; i++) {
            row = (lenHeader + i);
            printer.printTextWrap(row, 1, 1, 4, String.valueOf(i+1));
            printer.printTextWrap(row, 1, 4, 15, lista.get(i).getDescripcionP());
            printer.printTextWrap(row, 1, 16, 22, String.valueOf(lista.get(i).getPrecio()));
            printer.printTextWrap(row, 1, 23, 24, String.valueOf(lista.get(i).getCantidad()));
            printer.printTextWrap(row, 1, 25, 31, String.valueOf(lista.get(i).getSubtotal()));
            total=total+lista.get(i).getSubtotal();
            lenHeader = lenHeader + 1;
        }
        DecimalFormat dfoFormat = new DecimalFormat("0.00");
        printer.printCharAtCol(rowCount + 12, 1, 31, "-");
        printer.printTextWrap(rowCount + 13, rowCount + 13, 1, 31, "SubTotal  :S/." + dfoFormat.format(total-(total*IGV)));
        printer.printTextWrap(rowCount + 14, rowCount + 14, 1, 31, "IGV       :S/." + dfoFormat.format(total*IGV));
        printer.printTextWrap(rowCount + 15, rowCount + 16, 1, 31, "Total     :S/." + total);
        printer.printCharAtCol(rowCount + 17, 1, 31, "-");
        printer.printTextWrap(rowCount + 17, rowCount + 17, 1, 31, "Vendededor:" + user.getNom());
        printer.printTextWrap(rowCount + 18, rowCount + 18, 0, 31, "Esta ticket no tiene valor fiscal solo uso interno");

        printer.toFile(serie + ".txt");

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(serie + ".txt");
        } catch (FileNotFoundException ex) {
            System.err.println("Error:"+e);
        }
        if (inputStream == null) {
            return;
        }

        DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc document = new SimpleDoc(inputStream, docFormat, null);

        PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();

        PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();

        if (defaultPrintService != null) {
            DocPrintJob printJob = defaultPrintService.createPrintJob();
            try {
                printJob.print(document, attributeSet);

            } catch (PrintException ex) {
                System.out.println("Error:"+e);
            }
        } else {
            System.err.println("No existen impresoras instaladas");
        }
    }
}
