package com.itexpertnepal.simpleinvoice.ui.pdf.facade;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;

/**
 *
 * @author binay
 */
public class PdfGenerateUtility {

    public static void PlaceChunck(String text, int x, int y, BoldHead boldHead, PdfWriter writer) throws DocumentException, IOException {

        PdfContentByte cb = writer.getDirectContent();
        BaseFont bf = null;
        if (boldHead.equals(BoldHead.TIMES_BOLD)) {
            bf = BaseFont.createFont(BaseFont.TIMES_BOLD, BaseFont.CP1250, BaseFont.NOT_EMBEDDED);
        } else {
            bf = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1250, BaseFont.NOT_EMBEDDED);
        }

        cb.setFontAndSize(bf, 10);
        cb.saveState();
        cb.beginText();
        cb.moveText(x, y);
        cb.showText(text);
        cb.endText();
        cb.lineTo(35, 655);
        cb.stroke();
        //first line
        cb.moveTo(35, 655);
        cb.lineTo(550, 655);

        //second line
//        cb.moveTo(35, 585);
//        cb.lineTo(550, 585);
//        cb.stroke();
        cb.restoreState();

    }
    
    
     public static void PlaceChunck(String text, int x, int y, BoldHead boldHead, PdfWriter writer,int invoiceSize) throws DocumentException, IOException {

        PdfContentByte cb = writer.getDirectContent();
        BaseFont bf = null;
        if (boldHead.equals(BoldHead.TIMES_BOLD)) {
            bf = BaseFont.createFont(BaseFont.TIMES_BOLD, BaseFont.CP1250, BaseFont.NOT_EMBEDDED);
        } else {
            bf = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1250, BaseFont.NOT_EMBEDDED);
        }

        cb.setFontAndSize(bf, 10);
        cb.saveState();
        cb.beginText();
        cb.moveText(x, y);
        cb.showText(text);
        cb.endText();
        cb.lineTo(35, 655);
        cb.stroke();
        //first line
        cb.moveTo(35, 655 );
        cb.lineTo(550, 655);

        //second line
        cb.moveTo(35, 585 - invoiceSize *10);
        cb.lineTo(550, 585 - invoiceSize *10);
        cb.stroke();
        cb.restoreState();

    }

    public enum BoldHead {

        TIMES_BOLD, TIMES_ROMAN
    }

}
