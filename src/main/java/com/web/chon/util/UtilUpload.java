package com.web.chon.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * * Clase para administrar las utilidades relacionadas con la subida de un
 * archivo
 *
 * @author Juan
 */
public class UtilUpload {

    /**
     * Guarda un archivo pdf temporal para la impresion de tickets
     *
     * @param bytes
     * @param nombreArchivo
     * @return
     */
    public static String saveFileTemp(byte[] bytes, String nombreArchivo) {

        String ubicacionPdf = null;
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String ruta = servletContext.getRealPath("") + File.separatorChar + "resources" + File.separatorChar + "pdf" + File.separatorChar + "temp" + File.separatorChar + nombreArchivo;

        File file = null;
        InputStream inputStream = null;

        try {
            file = new File(ruta + ".pdf");
            int cont = 0;
            while (file.exists()) {
                cont++;
                ruta += cont;
                nombreArchivo += cont;
                file = new File(ruta + ".pdf");
            }

            inputStream = new ByteArrayInputStream(bytes);
            FileOutputStream out = new FileOutputStream(file.getAbsolutePath());

            int c = 0;

            while ((c = inputStream.read()) >= 0) {
                out.write(c);
            }

            out.flush();
            out.close();
            ubicacionPdf = "/resources/pdf/temp/" + nombreArchivo + ".pdf";
            System.out.println("archivo upload succes");
        } catch (Exception e) {
            System.out.println("error > " + e.getMessage());
            System.out.println("Error al Generar el temporal del PDF");
            e.getStackTrace();
        }
        return ubicacionPdf;
    }

    public static boolean deleteFile(String rutaArchivo) {
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

        if (rutaArchivo != null && !rutaArchivo.isEmpty()) {
            String ruta = servletContext.getRealPath("") + rutaArchivo;

            try {
                File file = new File(ruta);

                if (file.exists()) {
                    return file.delete();
                }

            } catch (Exception e) {
                System.out.println("Error > " + e.getMessage());
                return false;
            }
        }

        return false;
    }

}