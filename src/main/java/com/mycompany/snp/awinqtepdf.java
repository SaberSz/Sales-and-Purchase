
package com.mycompany.snp;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageDataFactory;
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
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger; 
import java.util.HashMap;

 
public class awinqtepdf {
    static int pgnotot = 0;
    protected PdfFormXObject template;
      
    public static String DEST = "./hello_world.pdf";

 public  awinqtepdf(HashMap<String, Object> hm) throws IOException {

        DEST = "results/Quotation/Steel/";
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        DEST = DEST + hm.get("Quotation Number") + ".pdf";
        System.out.println(DEST);
        createPdf(DEST, hm);
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
        document.setMargins(18, 36, 36, 36);
        //Add paragraph to the document
        //  template = new PdfFormXObject(new Rectangle(550, 803, 30, 30));
        // PdfCanvas canvas = new PdfCanvas(template, pdf);

        // HeaderHandler headerHandler = new HeaderHandler();
        // pdf.addEventHandler(PdfDocumentEvent.START_PAGE, headerHandler);
        Image steelslogo = new Image(ImageDataFactory.create("InvoiceSC/Picture1.jpg"));
        Image extralogo1 = new Image(ImageDataFactory.create("InvoiceSC/Picture2.jpg"));
        Image extralogo2 = new Image(ImageDataFactory.create("InvoiceSC/Picture3.jpg"));
        Image extralogo3 = new Image(ImageDataFactory.create("InvoiceSC/Picture4.jpg"));

        //row 1 in pdf
        steelslogo.scaleAbsolute(215f, 50f);
        extralogo1.scaleAbsolute(130f, 55f);
        extralogo2.scaleAbsolute(130f, 55f);
        extralogo3.scaleAbsolute(90f, 55f);

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
        //address paragraph
        Paragraph address = new Paragraph().add(new Text("No. 12, Tuas View Place, Singapore - 637864.\n"
                + "No.109 Tuas South Ave.8 Singapore - 637037.\n"
                + "Tel: +65 6265 9476  Fax: +65 6265 7685.\n"
                + "Email: Enquires@steelcoat.com.sg  \n"
                + "Website: www.steelcoat.com.sg\n" + "Co. Reg No:   201410749G\n"
                + "GST. Reg No: 201410749G").addStyle(cal));
        address.setFixedLeading(11.5f);//line spacing
        address.setMarginLeft(15);//set text in paragraph a little to left
        Paragraph p = new Paragraph().add(steelslogo).add(address);
        // p.setAlignment(Element.ALIGN_JUSTIFIED);
        document.add(p);

        //row 2 in pdf
        Paragraph p1 = new Paragraph().add(extralogo1);
        Paragraph p2 = new Paragraph().add(extralogo2);
        Paragraph p3 = new Paragraph().add(extralogo3);
        Paragraph row2 = new Paragraph().add(p1).add(o1).add(p2).add(o1).add(p3);
        document.add(row2);

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

        //Paragraph q1=new Paragraph();
        Paragraph q11 = new Paragraph().add("Aquatic Asia Pacific Pte Ltd\n"
                + "Loyang Offshore Supply Base\n"
                + "25 Loyang Crescent\n"
                + "TOPS Avenue 1, Block 103 #01-06\n"
                + "Singapore 508988")
                .setTextAlignment(TextAlignment.LEFT).addStyle(arial).setFixedLeading(13);
        Cell q2 = new Cell(1, 1).add(q11).setBorder(Border.NO_BORDER).setPadding(0);
        Text t = new Text("Our Quote:  18-SC-AA-QT- 001");

        Paragraph q12 = new Paragraph().add("Quoted By: Vijay\n"
                + "Phone: +65 6778 8271 / +65 81899206\n"
                + "Fax: +65 6265 7685\n"
                + "email: vijay@steelcoat.com.sg\n"
                + "Date:08.Jan.18\n").add(t)
                .setTextAlignment(TextAlignment.RIGHT).addStyle(arial).setFixedLeading(13);
        t.setFont(PdfFontFactory.createFont("arial-narrow/ARIALNB.TTF", PdfEncodings.IDENTITY_H, true));
        Cell q21 = new Cell(1, 1).add(q12).setBorder(Border.NO_BORDER).setPadding(0);
        q1.addCell(q2).addCell(q21).setWidthPercent(100);

        Style arialn = new Style();//arial-narrow/arial.ttf
        PdfFont f1fn = PdfFontFactory.createFont("arial-narrow/Arialn.ttf", PdfEncodings.IDENTITY_H, true);//espar-arial-classic/esparac.ttf
        arialn.setFont(f1fn).setFontSize(9.5f);
        Paragraph q3 = new Paragraph();
        q3.add("Attention to Project Manager,\n\n"
                + "Thank you for inviting us to quote for the above mentioned job. We are pleased to submit our quotation for your\n"
                + "perusal and approval.\n"
                + "Provision of Skilled Manpowers for Strcutrual Fabrication @ Aquatic Asia Pacific Yard at Loyang.\n").addStyle(arialn);

        document.add(r1).add(q1).add(q3);

     /*   //main table
        Table t2 = new Table(new float[]{0.25f, 0.5f, 3, 1, 1, 1, 1});
        t2.addHeaderCell(new Cell(1, 1).setPadding(0.1f).setBorder(Border.NO_BORDER).add(new Paragraph("").addStyle(arialn).setTextAlignment(TextAlignment.CENTER)));
        t2.addHeaderCell(new Cell(1, 1).setPadding(0.1f).setBorder(Border.NO_BORDER).add(new Paragraph("S.N").addStyle(arialn).setTextAlignment(TextAlignment.CENTER)));
        t2.addHeaderCell(new Cell(1, 1).setPadding(0.1f).setBorder(Border.NO_BORDER).add(new Paragraph("Position").addStyle(arialn).setTextAlignment(TextAlignment.CENTER)));
        t2.addHeaderCell(new Cell(1, 1).setPadding(0.1f).setBorder(Border.NO_BORDER).add(new Paragraph("Normal Rate/Hr (S$)\n" + "(Mon to Fri 8am to 5pm)").addStyle(arialn).setTextAlignment(TextAlignment.CENTER)));
        t2.addHeaderCell(new Cell(1, 1).setPadding(0.1f).setBorder(Border.NO_BORDER).add(new Paragraph("Beyond Normal Hours and\n" + "Saturdays (S$)	").addStyle(arialn).setTextAlignment(TextAlignment.CENTER)));
        t2.addHeaderCell(new Cell(1, 1).setPadding(0.1f).setBorder(Border.NO_BORDER).add(new Paragraph("Sundays and Public\n" + "Holidays (S$)").addStyle(arialn).setTextAlignment(TextAlignment.CENTER)));
        t2.addHeaderCell(new Cell(1, 1).setPadding(0.1f).setBorder(Border.NO_BORDER).add(new Paragraph("Remarks").addStyle(arialn).setTextAlignment(TextAlignment.CENTER)));

        t2.addCell(new Cell(1, 1).setPadding(0).setBorder(Border.NO_BORDER).add(new Paragraph("l")
                .setFont(PdfFontFactory.createFont("arial-narrow/ARIALNB.TTF", PdfEncodings.IDENTITY_H, true))
                .setFontSize(9.5f).setTextAlignment(TextAlignment.CENTER)));
        t2.startNewRow();
        String arr[] = {"Skilled Fitter",
            "Skilled /Certified Welder",
            "Skilled Grinder / General Workers",
            "Supervisor (Steel & Piping)",
            "QC Inspector / Engineer (AWS)",
            "Safety Supervisor"};
        for (int i = 0; i < 6; i++) {

            t2.addCell(new Cell(1, 1).setPadding(0.1f).setBorder(Border.NO_BORDER).add(new Paragraph("").addStyle(arialn).setTextAlignment(TextAlignment.CENTER)));
            t2.addCell(new Cell(1, 1).setPadding(0.1f).setWidth(50.0f).setBorder(Border.NO_BORDER).add(new Paragraph(Integer.valueOf(i + 1).toString()).addStyle(arialn).setTextAlignment(TextAlignment.CENTER)));
            t2.addCell(new Cell(1, 1).setPadding(0.1f).setBorder(Border.NO_BORDER).add(new Paragraph(arr[i]).addStyle(arialn).setTextAlignment(TextAlignment.LEFT)));
            t2.addCell(new Cell(1, 1).setPadding(0.1f).setBorder(Border.NO_BORDER).add(new Paragraph("$18.00").addStyle(arialn).setTextAlignment(TextAlignment.CENTER)));
            t2.addCell(new Cell(1, 1).setPadding(0.1f).setBorder(Border.NO_BORDER).add(new Paragraph("1.5 x Normal Rate").addStyle(arialn).setTextAlignment(TextAlignment.CENTER)));
            t2.addCell(new Cell(1, 1).setPadding(0.1f).setBorder(Border.NO_BORDER).add(new Paragraph("2 x Normal Rate").addStyle(arialn).setTextAlignment(TextAlignment.CENTER)));
            t2.addCell(new Cell(1, 1).setPadding(0.1f).setBorder(Border.NO_BORDER).add(new Paragraph("").addStyle(arialn).setTextAlignment(TextAlignment.CENTER)));

        }
        t2.addCell(new Cell(1, 1).setPadding(0).setBorder(Border.NO_BORDER).add(new Paragraph("ll")
                .setFont(PdfFontFactory.createFont("arial-narrow/ARIALNB.TTF", PdfEncodings.IDENTITY_H, true))
                .setFontSize(9.5f).setTextAlignment(TextAlignment.CENTER)));
        t2.startNewRow();
        String arr1[] = {"Skilled / Certified Blaster",
            "Skilled / Certified Painter",
            "Painting Helper",
            "Power Tool Men",
            "Nace Inspector Level.2"};
        for (int i = 0; i < 5; i++) {

            t2.addCell(new Cell(1, 1).setPadding(0.1f).setBorder(Border.NO_BORDER).add(new Paragraph("").addStyle(arialn).setTextAlignment(TextAlignment.CENTER)));
            t2.addCell(new Cell(1, 1).setPadding(0.1f).setWidth(50.0f).setBorder(Border.NO_BORDER).add(new Paragraph(Integer.valueOf(i + 1).toString()).addStyle(arialn).setTextAlignment(TextAlignment.CENTER)));
            t2.addCell(new Cell(1, 1).setPadding(0.1f).setBorder(Border.NO_BORDER).add(new Paragraph(arr1[i]).addStyle(arialn).setTextAlignment(TextAlignment.LEFT)));
            t2.addCell(new Cell(1, 1).setPadding(0.1f).setBorder(Border.NO_BORDER).add(new Paragraph("$18.00").addStyle(arialn).setTextAlignment(TextAlignment.CENTER)));
            t2.addCell(new Cell(1, 1).setPadding(0.1f).setBorder(Border.NO_BORDER).add(new Paragraph("1.5 x Normal Rate").addStyle(arialn).setTextAlignment(TextAlignment.CENTER)));
            t2.addCell(new Cell(1, 1).setPadding(0.1f).setBorder(Border.NO_BORDER).add(new Paragraph("2 x Normal Rate").addStyle(arialn).setTextAlignment(TextAlignment.CENTER)));
            t2.addCell(new Cell(1, 1).setPadding(0.1f).setBorder(Border.NO_BORDER).add(new Paragraph("").addStyle(arialn).setTextAlignment(TextAlignment.CENTER)));

        }
        //t2.setWidthPercent(100);
        //document.add(t2);
        Paragraph p7 = new Paragraph("\nTerms & Condition.").addStyle(arialn);
        t2.addCell(new Cell(1, 3).add(p7).setBorder(Border.NO_BORDER));
        t2.startNewRow();
        //Table t4 =new Table(new float[]{0.5f,3});
        String arr3[] = {"Quote shall remain valid for 7days.",
            "Workmen Compensatioin by Steel Coat Pte Ltd & Other",
            "Work Coordination by Acqatic at site",
            "PPE, Trasportaion & Lodging by Steel Coat Pte Ltd",
            "Invoice will be issued twice month and timecard to be released on every 15th and 30th/31st of the month.",
            "Payment Terms : 30days from the date of invoice",
            "Work order to be issued prior to commencement of works"};
        for (int i = 0; i < 7; i++) {
            t2.addCell(new Cell(1, 1).setPadding(0.1f).setBorder(Border.NO_BORDER).add(new Paragraph("").addStyle(arialn).setTextAlignment(TextAlignment.CENTER)));
            t2.addCell(new Cell(1, 1).setWidth(50.0f).setPadding(0.1f).setBorder(Border.NO_BORDER).add(new Paragraph(Integer.valueOf(i + 1).toString()).addStyle(arialn).setTextAlignment(TextAlignment.CENTER)));
            t2.addCell(new Cell(1, 5).setPadding(0.1f).setBorder(Border.NO_BORDER).add(new Paragraph(arr3[i]).addStyle(arialn).setTextAlignment(TextAlignment.LEFT)));
        }
        Image extralogo4 = new Image(ImageDataFactory.create("InvoiceSC/Picture5.jpg"));
        t2.setWidthPercent(100);
        t2.addCell(new Cell(1, 1).setPadding(0.1f).setBorder(Border.NO_BORDER).add(new Paragraph("").addStyle(arialn).setTextAlignment(TextAlignment.CENTER)));
        t2.addCell(new Cell(1, 1).setPadding(0.1f).setBorder(Border.NO_BORDER).add(new Paragraph("").addStyle(arialn).setTextAlignment(TextAlignment.CENTER)));
        t2.addCell(new Cell(1, 1).setPadding(0.1f).setBorder(Border.NO_BORDER).add(new Paragraph("").add(extralogo4)));
        extralogo4.scaleAbsolute(100f, 55f);
        document.add(t2);
        document.add(p);
        document.add(row2);
//          document.add(o2);

        Paragraph p4 = new Paragraph("Should you have further enquiries,please contact Vijay @ 81899206, if this quotes"
                + " meet your requirments, \nKindly endorese and return for our record. "
                + "\n\nWe trust our offer is acceptable to your and look forward to receiving your favourable reply soon.\n\n")
                .addStyle(arialn).setFontSize(9.5f).setTextAlignment(TextAlignment.LEFT);

        Table t5 = new Table(new float[]{1, 1, 2});
        t5.addCell(new Cell(1, 1).setBorder(Border.NO_BORDER).add(new Paragraph("Thank you.\n\nYours faithfully,").addStyle(arialn).setFontSize(9.5f).setTextAlignment(TextAlignment.LEFT)));
        t5.addCell(new Cell(1, 1).setBorder(Border.NO_BORDER).add(extralogo4).setHorizontalAlignment(HorizontalAlignment.LEFT));
        t5.addCell(new Cell(1, 1).setBorder(Border.NO_BORDER).add(new Paragraph().add(new Text("M/S : Aquatic Asia Pacific Pte Ltd".toUpperCase()).setFontSize(9.5f)
                .setFont(PdfFontFactory.createFont("arial-narrow/ARIALNB.TTF", PdfEncodings.IDENTITY_H, true)))
                .add("\nWe accept the terms and conditions").addStyle(arialn).setFontSize(9.5f)));
        Image steelslogo1 = new Image(ImageDataFactory.create("/Users/dylan/Desktop/InvoiceSC/Picture1.jpg"));
        steelslogo1.scaleAbsolute(130f, 45f);
        t5.addCell(new Cell(1, 2).setBorder(Border.NO_BORDER).add(steelslogo1));
        t5.setWidthPercent(100);
        t5.addCell(new Cell(2, 1).setBorder(Border.NO_BORDER).add(new Paragraph("Name:-------------------------------------------------"
                + "\n\nDesignation:-----------------------------------------").addStyle(arialn)
                .setFontSize(9.5f).setTextAlignment(TextAlignment.LEFT)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        document.add(p4).add(t5);
        event.writeTotal(pdf);*/
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




