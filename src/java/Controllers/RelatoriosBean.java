/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

/**
 *
 * @author fridr
 */
@Named(value = "relatoriosBean")
@ManagedBean
@SessionScoped
public class RelatoriosBean implements Serializable {

    private Paragraph getHeader(String texto) {
        Paragraph p = new Paragraph();
        Chunk c = new Chunk();
        p.setAlignment(Element.ALIGN_CENTER);
        c.append(texto);
        p.add(c);
        return p;
    }

    public void gerarRelatorio() {

        Document doc = new Document();

        try {
            FileOutputStream os = new FileOutputStream("D:\\Downloads\\Documents\\Relatorio.pdf");
            PdfWriter.getInstance(doc, os);

            doc.open();
            doc.setPageSize(PageSize.A4);
            Paragraph p = new Paragraph("Relatório de usuários cadastrados no sistema");
            
            p.setAlignment(Element.ALIGN_CENTER);
            
            doc.add(p);

            Image imagem = Image.getInstance("D:\\Downloads\\samlogo.png");
            imagem.scaleToFit(100, 100);

            doc.add(imagem);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            doc.close();
        }
    }

}
