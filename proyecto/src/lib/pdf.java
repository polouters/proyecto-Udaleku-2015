/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

/**
 *
 * @author Ruben
 */
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
public class pdf {

    public void crearPDF(String texto){
   Document documento = new Document();
   FileOutputStream ficheroPdf;
   try 
{
 ficheroPdf = new FileOutputStream("/../../Descargas");// valido para ordenadores OS X 
 PdfWriter.getInstance(
                       documento, 
                       ficheroPdf
                       ).setInitialLeading(20);
}
catch (Exception ex) 
{
 System.out.println(ex.toString());
}
        
   try{
 documento.open();
       documento.add(new Paragraph(texto));
 documento.close();
}catch(Exception ex){
 System.out.println(ex.toString());
}
    }
   
    
    
}
