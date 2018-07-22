/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PdfGeneration;

/**
 *
 * @author dylan
 */
import static PdfGeneration.pdfInvoice.DEST;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceCmyk;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.border.Border;
import static com.itextpdf.kernel.pdf.PdfName.DeviceGray;
import static com.itextpdf.kernel.pdf.PdfName.Text;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.itextpdf.layout.renderer.CellRenderer;
import com.itextpdf.layout.renderer.DrawContext;
import com.itextpdf.layout.renderer.TableRenderer;
import com.itextpdf.test.annotations.WrapToTest;
import com.mycompany.snp.Person4;
import java.awt.Desktop;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 * Simple Hello World example.
 */
@WrapToTest
public class pdfPurchaseOrder {

    static int pgnotot = 0;
    protected PdfFormXObject template;
    public static String DEST = "results/PurchaseOrder/Steel/";

    public pdfPurchaseOrder(HashMap<String, Object> hm) throws IOException {
        if (hm.get("Company").toString() == "Steel") {
            DEST = "results/PurchaseOrder/Steel/";
        } else {
            DEST = "results/PurchaseOrder/Awin/";
        }
        DEST += hm.get("Purchase Order Number").toString().replace('.', '-') + ".pdf";
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        createPdf(DEST, hm);
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop desktop = Desktop.getDesktop();
                File myFile = new File(DEST);
                desktop.open(myFile);
            } catch (IOException ex) {
            }
        }
    }

    public void createPdf(String dest, HashMap<String, Object> hm) throws IOException {
        //Initialize PDF writer
        PdfWriter writer = new PdfWriter(dest);

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);
        pdf.addEventHandler(PdfDocumentEvent.START_PAGE,
                new Header(""));
        PageXofY event = new PageXofY(pdf);
        pdf.addEventHandler(PdfDocumentEvent.END_PAGE, event);
        // Initialize document
        Document document = new Document(pdf, PageSize.A4);
        document.setMargins(18, 26, 36, 36);
        Image steelslogo = null;
        String Steeladd = "InvoiceSC/SteelLogo.png";
        String Awinadd = "InvoiceSC/AwinLogo.png";
        String path = "";
        String cadd = "Awin Engineering Pte. Ltd.\n"
                + "Regd. office: No. 12, Tuas View Place, Singapore. 637864\n"
                + "Works: No. 109, Tuas South Avenue 8,\n"
                + "Singapore. 637037\n"
                + "Co. Regd. No. 201012187G\n"
                + "GST Regd. No. 201012187G\n";
        String cadd1 = "No. 12, Tuas View Place, Singapore - 637864.\n"
                + "No.109 Tuas South Ave.8 Singapore - 637037.\n"
                + "Tel: +65 6265 9476 Fax: +65 6265 7685.\n"
                + "Email: Enquires@steelcoat.com.sg\n"
                + "Website: www.steelcoat.com.sg\n"
                + "Co. Reg No: 201410749G\n"
                + "GST. Reg No: 201410749G\n";
        String adds = "";
        System.out.println(hm.get("Company").toString());
        if (hm.get("Company").toString() == "Steel") {
            path = Steeladd;
            adds = cadd1;
            steelslogo = new Image(ImageDataFactory.create(path));
            steelslogo.scaleAbsolute(215f, 85f);
        } else {
            path = Awinadd;
            adds = cadd;
            steelslogo = new Image(ImageDataFactory.create(path));
            steelslogo.scaleAbsolute(177f, 115f);
        }

        Image extralogo3 = new Image(ImageDataFactory.create("InvoiceSC/Picture5.jpg"));

        // steelslogo.scaleAbsolute(215f, 85f);//177f,115f for Awin
        //for space
        Paragraph o1 = new Paragraph(" ");
        o1.setMarginLeft(17);//increase this value for increase in space

        //for font of address
        Style normal = new Style();
        PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
        normal.setFont(font).setFontSize(9.5f);
        PdfFont calibri = PdfFontFactory.createFont("calibri/CALIBRIB.TTF", PdfEncodings.IDENTITY_H, true);
        Style cal = new Style();
        cal.setFont(calibri).setFontSize(9.5f);

        Paragraph head = new Paragraph("Purchase Order").setFontSize(28f).setFont(PdfFontFactory.createFont("trebuchet-ms/trebucbd.ttf", PdfEncodings.IDENTITY_H, true));
        //address paragraph

        Table tbeg = new Table(new float[]{1, 1});
        tbeg.setWidthPercent(100);
        tbeg.addCell(new Cell().add(head).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT));
        tbeg.addCell(new Cell().add(new Paragraph().add(steelslogo).setTextAlignment(TextAlignment.RIGHT)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
        document.add(tbeg);
        document.add(new Paragraph(""));
        Table tadd = new Table(new float[]{1, 1, 1});
        tadd.setWidthPercent(100);
//        String sadd = "FORSPAC Steel Works Pte Ltd\n"
//                + "7030, Ang Mo Kio Avenue 5,\n"
//                + "#08 - 44,\n"
//                + "Northstar@AMK,\n"
//                + "Singapore - 569880\n";
        String sadd = hm.get("toAddress").toString();

        Paragraph padd = new Paragraph(sadd).setFontSize(9.0f).setFont(PdfFontFactory.createFont(FontConstants.HELVETICA));
        padd.setTextAlignment(TextAlignment.LEFT);
        Paragraph padd1 = new Paragraph(adds).setFontSize(9.0f).setFont(PdfFontFactory.createFont(FontConstants.HELVETICA));
        padd1.setTextAlignment(TextAlignment.LEFT);

        String details = "Issue date\n"
                + hm.get("Date").toString() + "\n"
                + "\nPurchase Order Number\n"
                + hm.get("Purchase Order Number").toString() + "\n"
                + "\nQuotation Number\n"
                + hm.get("Quotation Number").toString() + "\n"
                + "\nProject Number\n"
                + hm.get("ProjectNo").toString() + "\n"
                + "\nDelivery Required By\n"
                + hm.get("Delivery Required By").toString();
        Paragraph padd2 = new Paragraph(details).setFontSize(9.0f).setFont(PdfFontFactory.createFont(FontConstants.HELVETICA));
        padd2.setTextAlignment(TextAlignment.RIGHT);
        tadd.addCell(new Cell().add(padd).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT).setWidth(200f));
        tadd.addCell(new Cell().add(padd2).setWidth(135f).setBorderBottom(Border.NO_BORDER).setPaddingRight(15f).setBorderLeft(Border.NO_BORDER).setBorderTop(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT));
        tadd.addCell(new Cell().add(padd1).setBorder(Border.NO_BORDER).setPaddingLeft(15f).setTextAlignment(TextAlignment.LEFT));
        document.add(tadd);
        document.add(new Paragraph("\n"));

        String st = hm.get("Header").toString();
        Paragraph subject = new Paragraph(st).setFont(PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD)).setFontSize(11f);
        document.add(subject);
        document.add(new Paragraph(""));
        Table destab = new Table(new float[]{3, 2, 1, 1, 1, 1});
        DecimalFormat df = new DecimalFormat("#.00");
        destab.addHeaderCell(new Cell().setWidth(250f).setPaddings(8f, 0, 8f, 0).add(new Paragraph("Description").setFont(PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD)).setFontSize(9.5f).setTextAlignment(TextAlignment.CENTER)));
        destab.addHeaderCell(new Cell().setPaddings(8f, 0, 8f, 0).add(new Paragraph("UOM").setFont(PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD)).setFontSize(9.5f).setTextAlignment(TextAlignment.CENTER)));
        destab.addHeaderCell(new Cell().setPaddings(8f, 0, 8f, 0).add(new Paragraph("Qty").setFont(PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD)).setFontSize(9.5f).setTextAlignment(TextAlignment.CENTER)));
        destab.addHeaderCell(new Cell().setPaddings(8f, 0, 8f, 0).add(new Paragraph("Unit Price").setFont(PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD)).setFontSize(9.5f).setTextAlignment(TextAlignment.CENTER)));
        destab.addHeaderCell(new Cell().setPaddings(8f, 0, 8f, 0).add(new Paragraph("Discount").setFont(PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD)).setFontSize(9.5f).setTextAlignment(TextAlignment.CENTER)));
        destab.addHeaderCell(new Cell().setPaddings(8f, 0, 8f, 0).add(new Paragraph("Amount").setFont(PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD)).setFontSize(9.5f).setTextAlignment(TextAlignment.CENTER)));
        ObservableList<Person4> trc = (ObservableList) hm.get("TableItems");
        ListIterator<Person4> li = trc.listIterator();
        while (li.hasNext()) {
            Person4 pe = li.next();

            if (pe.getDes().getText().isEmpty()) {
                break;
            }
            Person4 pe1 = li.next();
            if (pe1.getDes().getText().isEmpty()) {
                // pe contains final row
                destab.addCell(new Cell().setWidth(250f).setBorderTop(Border.NO_BORDER).setPaddings(5f, 0, 5f, 5f).add(new Paragraph(pe.getDes().getText()).setFont(PdfFontFactory.createFont(FontConstants.HELVETICA)).setFontSize(9.0f).setTextAlignment(TextAlignment.LEFT)));
                destab.addCell(new Cell().setBorderTop(Border.NO_BORDER).setPaddings(5f, 0, 5f, 5f).add(new Paragraph(pe.getUOM().getText()).setFont(PdfFontFactory.createFont(FontConstants.HELVETICA)).setFontSize(9.0f).setTextAlignment(TextAlignment.LEFT)));
                destab.addCell(new Cell().setBorderTop(Border.NO_BORDER).setPaddings(5f, 0, 5f, 0).add(new Paragraph(pe.getQty().getText()).setFont(PdfFontFactory.createFont(FontConstants.HELVETICA)).setFontSize(9.0f).setTextAlignment(TextAlignment.CENTER)));
                destab.addCell(new Cell().setBorderTop(Border.NO_BORDER).setPaddings(5f, 0, 5f, 0).add(new Paragraph("$" + df.format(Double.valueOf(pe.getUnitPrice().getText()))).setFont(PdfFontFactory.createFont(FontConstants.HELVETICA)).setFontSize(9.0f).setTextAlignment(TextAlignment.CENTER)));
                destab.addCell(new Cell().setBorderTop(Border.NO_BORDER).setPaddings(5f, 0, 5f, 0).add(new Paragraph(pe.getDiscount().getText()).setFont(PdfFontFactory.createFont(FontConstants.HELVETICA)).setFontSize(9.0f).setTextAlignment(TextAlignment.CENTER)));
                destab.addCell(new Cell().setBorderTop(Border.NO_BORDER).setPaddings(5f, 0, 5f, 0).add(new Paragraph("$" + df.format(Double.valueOf(pe.getTotal().getText()))).setFont(PdfFontFactory.createFont(FontConstants.HELVETICA)).setFontSize(9.0f).setTextAlignment(TextAlignment.CENTER)));
            } else {
                li.previous();
                destab.addCell(new Cell().setWidth(250f).setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER).setPaddings(5f, 0, 5f, 5f).add(new Paragraph(pe.getDes().getText()).setFont(PdfFontFactory.createFont(FontConstants.HELVETICA)).setFontSize(9.0f).setTextAlignment(TextAlignment.LEFT)));
                destab.addCell(new Cell().setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER).setPaddings(5f, 0, 5f, 5f).add(new Paragraph(pe.getUOM().getText()).setFont(PdfFontFactory.createFont(FontConstants.HELVETICA)).setFontSize(9.0f).setTextAlignment(TextAlignment.LEFT)));
                destab.addCell(new Cell().setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER).setPaddings(5f, 0, 5f, 0).add(new Paragraph(pe.getQty().getText()).setFont(PdfFontFactory.createFont(FontConstants.HELVETICA)).setFontSize(9.0f).setTextAlignment(TextAlignment.CENTER)));
                destab.addCell(new Cell().setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER).setPaddings(5f, 0, 5f, 0).add(new Paragraph("$" + df.format(Double.valueOf(pe.getUnitPrice().getText()))).setFont(PdfFontFactory.createFont(FontConstants.HELVETICA)).setFontSize(9.0f).setTextAlignment(TextAlignment.CENTER)));
                destab.addCell(new Cell().setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER).setPaddings(5f, 0, 5f, 0).add(new Paragraph(pe.getDiscount().getText()).setFont(PdfFontFactory.createFont(FontConstants.HELVETICA)).setFontSize(9.0f).setTextAlignment(TextAlignment.CENTER)));
                destab.addCell(new Cell().setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER).setPaddings(5f, 0, 5f, 0).add(new Paragraph("$" + df.format(Double.valueOf(pe.getTotal().getText()))).setFont(PdfFontFactory.createFont(FontConstants.HELVETICA)).setFontSize(9.0f).setTextAlignment(TextAlignment.CENTER)));
            }
        }

        for (int i = 0; i < 4; i++) {
            destab.addCell(new Cell().setPaddings(5f, 0, 5f, 5f).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        }
        destab.addCell(new Cell()
                .setPaddings(5f, 0, 5f, 0)
                .setBorder(Border.NO_BORDER)
                .add(new Paragraph("Sub-total").setFont(PdfFontFactory.createFont(FontConstants.HELVETICA)).setFontSize(9.0f).setTextAlignment(TextAlignment.CENTER)));
        destab.addCell(new Cell()
                .setPaddings(5f, 0, 5f, 0)
                .add(new Paragraph("$" + df.format(Double.valueOf(hm.get("Sub Total").toString()))).setFont(PdfFontFactory.createFont(FontConstants.HELVETICA)).setFontSize(9.0f).setTextAlignment(TextAlignment.CENTER)));
        for (int i = 0; i < 4; i++) {
            destab.addCell(new Cell().setPaddings(5f, 0, 5f, 5f).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        }

        destab.addCell(new Cell()
                .setPaddings(5f, 0, 5f, 0)
                .setBorder(Border.NO_BORDER)
                .add(new Paragraph("GST " + df.format(Double.valueOf(hm.get("GST Rate").toString())) + "%").setFont(PdfFontFactory.createFont(FontConstants.HELVETICA)).setFontSize(9.0f).setTextAlignment(TextAlignment.CENTER)));
        destab.addCell(new Cell()
                .setPaddings(5f, 0, 5f, 0)
                .add(new Paragraph("$" + df.format(Double.valueOf(hm.get("GST Amount").toString()))).setFont(PdfFontFactory.createFont(FontConstants.HELVETICA)).setFontSize(9.0f).setTextAlignment(TextAlignment.CENTER)));
        for (int i = 0; i < 4; i++) {
            destab.addCell(new Cell().setPaddings(5f, 0, 5f, 5f).add(new Paragraph("")).setBorder(Border.NO_BORDER));
        }

        destab.addCell(new Cell()
                .setPaddings(5f, 0, 5f, 0)
                .setBorder(Border.NO_BORDER)
                .add(new Paragraph("Total").setFont(PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD)).setFontSize(9.0f).setTextAlignment(TextAlignment.CENTER)));
        destab.addCell(new Cell()
                .setPaddings(5f, 0, 5f, 0)
                .add(new Paragraph("$" + df.format(Double.valueOf(hm.get("Total").toString()))).setFont(PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD)).setFontSize(9.0f).setTextAlignment(TextAlignment.CENTER)));

        destab.setWidthPercent(100);
        document.add(destab);
        document.add(new Paragraph(""));
        Paragraph fin = new Paragraph("Payment Terms\n"
                + hm.get("Payment Terms").toString() + "\n");
        extralogo3.scaleAbsolute(113f, 80f);
        fin.setFont(PdfFontFactory.createFont(FontConstants.HELVETICA)).setFontSize(9.0f).setTextAlignment(TextAlignment.LEFT);
        fin.add(extralogo3);
        fin.add("\n" + "Authorised Signatory\n"
                + "Vijay (Director - Operations)" + "\n");
        document.add(fin);
        event.writeTotal(pdf);
        document.close();

    }

    protected class Header implements IEventHandler {

        String header;

        public Header(String header) {
            this.header = header;
        }

        @Override
        public void handleEvent(Event event) {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            PdfDocument pdf = docEvent.getDocument();
            PdfPage page = docEvent.getPage();
            if (pdf.getPageNumber(page) == 1) {
                return;
            }
            Rectangle pageSize = page.getPageSize();
            PdfCanvas pdfCanvas = new PdfCanvas(
                    page.newContentStreamBefore(), page.getResources(), pdf);
            Canvas canvas = new Canvas(pdfCanvas, pdf, pageSize);
            canvas.showTextAligned(header,
                    pageSize.getWidth() / 2,
                    pageSize.getTop() - 30, TextAlignment.CENTER);
        }
    }

    protected class PageXofY implements IEventHandler {

        protected PdfFormXObject placeholder;
        protected float side = 20;
        protected float x = 300;
        protected float y = 25;
        protected float space = 4.5f;
        protected float descent = 3;

        public PageXofY(PdfDocument pdf) {
            placeholder = new PdfFormXObject(new Rectangle(0, 0, side, side));
        }

        @Override
        public void handleEvent(Event event) {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            PdfDocument pdf = docEvent.getDocument();
            PdfPage page = docEvent.getPage();
            int pageNumber = pdf.getPageNumber(page);
            Rectangle pageSize = page.getPageSize();
            PdfCanvas pdfCanvas = new PdfCanvas(
                    page.newContentStreamBefore(), page.getResources(), pdf);
            Canvas canvas = new Canvas(pdfCanvas, pdf, pageSize);
            Style arialn = new Style();//arial-narrow/arial.ttf
            PdfFont f1fn;
            try {
                f1fn = PdfFontFactory.createFont("arial-narrow/Arialn.ttf", PdfEncodings.IDENTITY_H, true);
                f1fn = PdfFontFactory.createFont(FontConstants.HELVETICA);
                arialn.setFont(f1fn).setFontSize(8.5f);//espar-arial-classic/esparac.ttf
            } catch (IOException ex) {
                Logger.getLogger(pdfPurchaseOrder.class.getName()).log(Level.SEVERE, null, ex);
            }

            Paragraph p = new Paragraph()
                    .add("Page ").add(String.valueOf(pageNumber)).add(" of").addStyle(arialn);
            canvas.showTextAligned(p, x, y, TextAlignment.RIGHT);
            pdfCanvas.addXObject(placeholder, x + space, y - descent);
            pdfCanvas.release();
        }

        public void writeTotal(PdfDocument pdf) {
            Canvas canvas = new Canvas(placeholder, pdf);
            try {
                canvas.setFont(PdfFontFactory.createFont("arial-unicode-ms/ARIALUNI.TTF", PdfEncodings.IDENTITY_H, true)).setFontSize(8.5f);
            } catch (IOException ex) {
                Logger.getLogger(pdfPurchaseOrder.class.getName()).log(Level.SEVERE, null, ex);
            }
            canvas.showTextAligned(String.valueOf(pdf.getNumberOfPages()),
                    0, descent, TextAlignment.LEFT);
        }
    }
}
