package PdfGeneration;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceCmyk;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.mycompany.snp.Person;
import com.mycompany.snp.Person3;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;
import java.util.ListIterator;
import javafx.collections.ObservableList;

public class awinqtepdf {

    static int pgnotot = 0;
    protected PdfFormXObject template;

    public static String DEST = "results/Quotation/Awin/";

    public awinqtepdf(HashMap<String, Object> hm) throws IOException {

        DEST = "results/Quotation/Awin/";
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        DEST = DEST + hm.get("Quotation Number") + ".pdf";
        System.out.println(DEST);
        createPdf(DEST, hm);
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File(DEST);
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                Utilities.AlertBox.showErrorMessage(ex);
            }
        }
    }

    public void createPdf(String dest, HashMap<String, Object> hm) throws IOException {
        PdfWriter writer = new PdfWriter(dest);

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);
        pdf.addEventHandler(PdfDocumentEvent.START_PAGE,
                new Header(""));
        PageXofY event = new PageXofY(pdf);
        pdf.addEventHandler(PdfDocumentEvent.END_PAGE, event);
        Style normal = new Style();
        PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);
        normal.setFont(font).setFontSize(8);
        Color cuntz = new DeviceRgb(8, 186, 236);
        Color cuntz2 = new DeviceRgb(33, 28, 33);
        // Initialize document
        Document document = new Document(pdf, PageSize.A4);
        document.setMargins(18, 36, 36, 36);
        Image steelslogo = new Image(ImageDataFactory.create("images/AwinLogo.png"));//C:/Users/Admin/Desktop/Sales-and-Purchase/
        Image extralogo1 = new Image(ImageDataFactory.create("images/yoyo.png"));//change path here
        Image extralogo2 = new Image(ImageDataFactory.create("images/bubbby.png"));//change path here
        Image extralogo3 = new Image(ImageDataFactory.create("images/Picture4.jpg"));//change path here
        steelslogo.scaleAbsolute(93f, 73f);
        // steelslogo.setFixedPosition(100,760);
        extralogo1.scaleAbsolute(50f, 35f);
        extralogo2.scaleAbsolute(73f, 30f);
        extralogo3.scaleAbsolute(77f, 42f);
        Paragraph address = new Paragraph().setTextAlignment(TextAlignment.CENTER).add(new Text(" AWIN ENGINEERING PTE LTD\n").setUnderline().setFont(PdfFontFactory.createFont("ttffiles/ARLRDBD.ttf")).setFontColor(cuntz).setFontSize(14))
                .add(new Text("Reg Office: No.12, Tuas View Place, Singapore - 637864\n"
                        + "Workshop: No.109, Tuas South Ave.8, Singapore - 637037\n"
                        + "Tel: +65 6778 8271, Fax: +65 6265 7685.\n"
                        + " Email: enquiry@awin.com.sg,  Web site: www.awin.com.sg")).setFont(PdfFontFactory.createFont("arial-unicode-ms/ARIALUNI.TTF")).setFontSize(9.5f);//change path here 
        address.setFixedLeading(18);//line spacing
        address.setMarginLeft(15);//set text in paragraph a little to left
        Paragraph dets = new Paragraph().add(steelslogo).setTextAlignment(TextAlignment.JUSTIFIED_ALL).setFont(PdfFontFactory.createFont("calibri/Calibri.ttf")).setFontSize(9.5f);//change path here
        extralogo1.setMarginLeft(15);
        extralogo1.setFixedPosition(395, 773);
        extralogo2.setFixedPosition(485, 773);
        extralogo3.setFixedPosition(480, 723);
        Paragraph p = new Paragraph().add(dets).add(new Text("\t")).add(address);
        document.add(p);
        //document.add(dets);
        document.add(extralogo1);
        document.add(extralogo2);
        document.add(extralogo3);

        p.setMarginLeft(10);
        Paragraph o1 = new Paragraph(" ");
        o1.setMarginLeft(7);

        Paragraph p3 = new Paragraph();
        Paragraph row2 = new Paragraph().add(o1);
        //document.add(row2);

        //Paragraph o2 = new Paragraph("\n");
        o1.setMarginLeft(17);//increase this value for increase in space
        //document.add(o2);
        Paragraph r1 = new Paragraph("Offer").setTextAlignment(TextAlignment.RIGHT)
                .setFont(PdfFontFactory.createFont("trebuchet-ms/trebucbd.ttf", PdfEncodings.IDENTITY_H, true))
                .setFontSize(12);
        Style arial = new Style();//arial-narrow/arial.ttf
        PdfFont f1f = PdfFontFactory.createFont("arial-unicode-ms/ARIALUNI.TTF", PdfEncodings.IDENTITY_H, true);//espar-arial-classic/esparac.ttf
        arial.setFont(f1f).setFontSize(9.5f);
        Table q1 = new Table(new float[]{1, 1});
        Text he = new Text((String) hm.get("Customer Name"));
        //Paragraph q1=new Paragraph();
        Paragraph q11 = new Paragraph().add(he).add("\n"
                + (String) hm.get("toAddress") + "\n"
        )
                .setTextAlignment(TextAlignment.LEFT).setFont(f1f).setFontSize(9.5f).setFixedLeading(13);
        Cell q2 = new Cell(1, 1).add(q11).setBorder(Border.NO_BORDER).setPadding(0);
        Text t = new Text("Our Quote:  " + (String) hm.get("Quotation Number"));
        he.setFontSize(11).setFont(PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD));
        Paragraph q12 = new Paragraph(new Text("Offer").setTextAlignment(TextAlignment.RIGHT)
                .setFont(PdfFontFactory.createFont("trebuchet-ms/trebucbd.ttf", PdfEncodings.IDENTITY_H, true))
                .setFontSize(12)).add("\n\n"
                + "Quoted By: Vijay\n"
                + "Phone: +65 6778 8271 / +65 81899206\n"
                + "Fax: +65 6265 7685\n"
                + "email: vijay@steelcoat.com.sg\n"
                + "Date:" + (String)hm.get("Date") + "\n").add(t)
                .setTextAlignment(TextAlignment.RIGHT).addStyle(arial).setFixedLeading(13);
        t.setFont(PdfFontFactory.createFont("trebuchet-ms/trebucbd.ttf", PdfEncodings.IDENTITY_H, true));
        Cell q21 = new Cell(1, 1).add(q12).setBorder(Border.NO_BORDER).setPadding(0);
        q1.addCell(q2).addCell(q21).setWidthPercent(100);

        Style arialn = new Style();//arial-narrow/arial.ttf
        PdfFont f1fn = PdfFontFactory.createFont("arial-narrow/Arialn.ttf", PdfEncodings.IDENTITY_H, true);//espar-arial-classic/esparac.ttf
        arialn.setFont(f1fn).setFontSize(9.5f);
        Paragraph q3 = new Paragraph();
        q3.add(new Text("Attention to Project Manager,").setFont(PdfFontFactory.createFont("trebuchet-ms/trebucbd.ttf", PdfEncodings.IDENTITY_H, true)))
                .add("\n\n"
                        + "Your Enquiry: "+((String) hm.get("Subject")).trim()  + "\n"
                        + "Thank you for inviting us to quote for the above mentioned job. We are pleased to submit our quotation for your\n"
                        + "perusal and approval.\n").addStyle(arial);

        document.add(q1).add(q3);
        float[] columnWidths = {1, 3, 1, 1, 1};
        Table table = new Table(columnWidths);
        PdfFont f = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
        table.setWidthPercent(100);
        Style cellHeads = new Style().setFont(f).setFontSize(11);
        Cell c1 = new Cell(1, 1).add(new Paragraph("S. No")
                .addStyle(cellHeads)).setWidth(35f).setTextAlignment(TextAlignment.CENTER);
        Cell c2 = new Cell(1, 1).add(new Paragraph("Description")
                .addStyle(cellHeads)).setTextAlignment(TextAlignment.CENTER);
        Cell c3 = new Cell(1, 1).add(new Paragraph("Quantity")
                .addStyle(cellHeads)).setTextAlignment(TextAlignment.CENTER);
        Cell c4 = new Cell(1, 1).add(new Paragraph("Unit/(SGD)")
                .addStyle(cellHeads)).setTextAlignment(TextAlignment.CENTER);
        Cell c5 = new Cell(1, 1).add(new Paragraph("Total(SGD)")
                .addStyle(cellHeads)).setTextAlignment(TextAlignment.CENTER);
        table.addHeaderCell(c1).addHeaderCell(c2).addHeaderCell(c3).addHeaderCell(c4).addHeaderCell(c5);
        String[] Des1 = {"Chute - Blasting & Painting\n"
            + "\n"
            + "Scope of Work:\n"
            + "Chute - Topside \"sliding part\" entire surface to be blast at SA2.5 and applictaion of two coat painiting system as per Aquatic painting spec.\n"
            + "Chute external - side wall and undesr side will be spot blast at SA2.5 and application of two caot painting system as per Aquatic painting spec.\n"
            + "\n"
            + "02 Nos of base frame will be blast at SA2.5 and application of two coat painting system as per Aquatic painiting spec",
             "Chute - Transportation\n"
            + "Transportation to and from Loyang Crescent to Tuas \"40ft Trailer\".\n"
            + "Loading / Unloading at Aquatic yard by client.\n"
            + "loading / Unloading at Awin eEng yard by Awin Eng."};
        int n = 2;
        ObservableList<Person> trc = (ObservableList) hm.get("TableItems");
        ListIterator<Person> li = trc.listIterator();
       while(li.hasNext()) {
            Person pe = li.next();
            if (pe.getLastName().getText().isEmpty()) {
                break;
            }
            table.addCell(new Cell(1, 1).setWidth(35f).add(new Paragraph((String)pe.getFirstName().getText().trim())).addStyle(cellHeads).setTextAlignment(TextAlignment.CENTER))
                    .addCell(new Cell(1, 1).add(new Paragraph((String)pe.getLastName().getText().trim())).setTextAlignment(TextAlignment.LEFT).addStyle(arial))
                    .addCell(new Cell(1, 1).add(new Paragraph((String)pe.getEmail().getText().trim())).setTextAlignment(TextAlignment.CENTER).addStyle(arial))
                    .addCell(new Cell(1, 1).add(new Paragraph("$"+(String)pe.getRemark().getText().trim())).setTextAlignment(TextAlignment.CENTER).addStyle(arial))
                    .addCell(new Cell(1, 1).add(new Paragraph("$"+(String)pe.getTotal().getText().trim())).setTextAlignment(TextAlignment.CENTER).addStyle(arial));

        }
        Image extralogo12 = new Image(ImageDataFactory.create("InvoiceSC/Picture5.jpg"));
        document.add(table);
        Paragraph x = new Paragraph()
                .add("Exclusions\t:\t" + "Third Party fdsfsdfsfsfdsdfddf" + "\n")
                .add("Terms\t:\t" + "30 weeks from the date of Invoice" + "\n")
                .add("Delivery\t:\t" + "2 Week upon receipt of PO" + "\n")
                .add("Unless and otherwise stated the price is states in S$ excluding 7% GST, merchandise packed and uninsured. This quote is subject to our"
                        + " general terms & conditions of sales and will apply as amended from time to time." + "\n")
                .add("We hope that this offer meets your expectations and we are looking forward to receiving your order. You can rest assured that your"
                        + " order will be carried out to your entire satisfaction" + "\n")
                .add(new Text("AWIN ENGINEERING PTE LTD\n").addStyle(arial).setFontColor(cuntz))
                .addStyle(arial).setTextAlignment(TextAlignment.LEFT)
                .add(extralogo12);
        extralogo12.scaleAbsolute(100f, 55f);
        document.add(x);
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
                arialn.setFont(f1fn).setFontSize(8.5f);//espar-arial-classic/esparac.ttf
            } catch (IOException ex) {
                Logger.getLogger(awinqtepdf.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(awinqtepdf.class.getName()).log(Level.SEVERE, null, ex);
            }
            canvas.showTextAligned(String.valueOf(pdf.getNumberOfPages()),
                    0, descent, TextAlignment.LEFT);
        }
    }
}
