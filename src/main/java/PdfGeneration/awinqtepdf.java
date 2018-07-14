
package com.mycompany.snp;
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
      
    public static String DEST = "./hello_world.pdf";

 public  awinqtepdf(HashMap<String, Object> hm) throws IOException {

        DEST = "/results/awinquo";
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
 Style normal = new Style();
        PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);
        normal.setFont(font).setFontSize(8);
       Color cuntz=new DeviceRgb(8,186,236);
       Color cuntz2=new DeviceRgb(33,28,33);
        // Initialize document
        Document document = new Document(pdf, PageSize.A4);
        Image steelslogo = new Image(ImageDataFactory.create("images/Picturek.jpg"));//C:/Users/Admin/Desktop/Sales-and-Purchase/
        Image extralogo1 = new Image(ImageDataFactory.create("images/yoyo.png"));//change path here
        Image extralogo2 = new Image(ImageDataFactory.create("images/bubbby.png"));//change path here
        Image extralogo3 = new Image(ImageDataFactory.create("images/Picture4.jpg"));//change path here
         //  steelslogo.scaleAbsolute(63f,63f);
          // steelslogo.setFixedPosition(100,760);
      extralogo1.scaleAbsolute(50f,35f);
      extralogo2.scaleAbsolute(73f, 30f);
      extralogo3.scaleAbsolute(77f, 42f);
          Paragraph address = new Paragraph().add(new Text(" AWIN ENGINEERING PTE LTD\n").setFont(PdfFontFactory.createFont("ttffiles/ARLRDBD.ttf")).setFontColor(cuntz).setFontSize(12)).add(new Text("Works: No. 109, Tuas South Ave. 8, Singapore 637 037.\n" +
        "Regd. No. 12 Tuas View Place, Singapore 637 034.\n" +
        "Tel: +65 6778 8271, Fax: +65 6265 7685.\n" +
        " Email: vijay@awin.com.sg,  Web site: www.awin.com.sg").addStyle(normal)).setFont(PdfFontFactory.createFont("arial-unicode-ms/ARIALUNI.TTF")).setFontSize(9.5f);//change path here 
        address.setFixedLeading(18);//line spacing
        address.setMarginLeft(15);//set text in paragraph a little to left
        Paragraph dets=new Paragraph().add(steelslogo).add(new Text("\nCo.Reg No: 201012187G\n"+"GST.Reg No: 201012187G")).setTextAlignment(TextAlignment.JUSTIFIED_ALL).setFont(PdfFontFactory.createFont("calibri/Calibri.ttf")).setFontSize(9.5f);//change path here
        extralogo1.setMarginLeft(15);
    extralogo1.setFixedPosition(365,755);
     extralogo2.setFixedPosition(455,755);
     extralogo3.setFixedPosition(450,705);
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
        Paragraph row2=new Paragraph().add(o1);
        document.add(row2);

        //Paragraph o2 = new Paragraph("\n");
        o1.setMarginLeft(17);//increase this value for increase in space
        //document.add(o2);

        //table formation
        float[] columnWidths = {1,2,1,1,1};
        Table table = new Table(columnWidths);
        table.setWidthPercent(100);
        PdfFont f = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
        Cell cell1 = new Cell(1, 5)
                .add(new Paragraph("TAX INVOICE").setFixedLeading(15))
                .setFont(f)
                .setFontSize(17)
                //.setBackgroundColor(DeviceGray.BLACK)
                .setTextAlignment(TextAlignment.CENTER);
        table.addHeaderCell(cell1);

        Cell celladd = new Cell(1,2);

        Paragraph p9=new Paragraph();
         PdfFont f1 = PdfFontFactory.createFont(FontConstants.HELVETICA);
        p9.add(new Text("To :").setFont(f).setFontSize(8))
                .add(new Paragraph(new Text("Menck Pte Ltd"))
                        .setFont(f).setFontSize(8).setMarginLeft(50));
       //p9.add(new Text("107, Tuas South Avenue 8, Acteon Singapore Operations Centre\n")
         //       .setFont(f1).setFontSize(8));
        Paragraph p10=new Paragraph("Kind Attn. :").setFont(f).setFontSize(8);
        p10.add(new Paragraph("Accounts Payable").setFont(f1).setFontSize(8).setMarginLeft(21));
//"Tel: (65) 62622282").setFont(f1).setFontSize(8).setMarginLeft(44));

        //p9.add(p10);
        celladd.add(p9)
                .add(new Paragraph("107, Tuas South Avenue 8, Acteon Singapore Operations Centre")
                .setFont(f1).setFontSize(8).setMarginLeft(64))
                .add(new Paragraph("Singapore - 637036")
                .setFont(f1).setFontSize(8).setMarginLeft(64))
                .add(p10)
                .add(new Paragraph("Tel: (65) 62622282").setFont(f1).setFontSize(8).setMarginLeft(64))
                .setBorderRight(Border.NO_BORDER);


       Table in1=new Table(new float[] {1,1});

        in1.setWidthPercent(100);
        Cell inv =new Cell(1,1);

        //inv.setNextRenderer(new DottedLineCellRenderer(inv));
        inv.add(new Paragraph("Invoice No:\n" + (String) hm.get("Invoice Number")).setFont(f).setFontSize(8)).setBorderTop(Border.NO_BORDER);
        Cell d =new Cell(1,1).setBorderTop(Border.NO_BORDER);

        d.add(new Paragraph("Date:")
                .setFont(f).setFontSize(8));
                 d.add(new Paragraph((String) hm.get("Date")).setFont(f1).setFontSize(8).setFontColor(Color.RED));
        Cell so =new Cell(1,1)
                .add(new Paragraph("Our S/O No: N/A.").setFont(f).setFontSize(8));//.setBorder(Border.NO_BORDER);
        //so.setNextRenderer(new DottedLineCellRenderer(so));
        Cell sp =new Cell(2,1);
        sp.add(new Paragraph("Salesperson:\n").setFont(f).setFontSize(8)
                  .add(new Text((String) hm.get("Salesperson")).setFont(f1).setFontSize(8)));
        Cell jo =new Cell(1,1)
                .add(new Paragraph("Our Job No:N/A.").setFont(f).setFontSize(8));
        Cell pjo =new Cell(1,1)
                .add(new Paragraph("Your Order Ref No:\nProject No:").setFont(f).setFontSize(8));
        Cell pjno1 =new Cell(1,1)
                .add(new Paragraph((String) hm.get("POno") + " \n" + (String) hm.get("ProjectNo")).setFont(f1).setFontSize(8));
        Cell tem =new Cell(1,1)
                .add(new Paragraph("Terms of payment:").setFont(f).setFontSize(8));
        Cell jo1 =new Cell(1,1)
                .add(new Paragraph((String) hm.get("Termofpay") + " days").setFont(f1).setFontSize(8));
        Cell kp =new Cell(1,2).add(new Paragraph("Page No 1 of 1").setTextAlignment(TextAlignment.CENTER).setFont(f).setFontSize(8));
        in1.addCell(inv).addCell(d).addCell(so).addCell(sp).addCell(jo).addCell(pjo).addCell(pjno1)
                .addCell(tem).addCell(jo1).addCell(kp);

        Cell a= new Cell(1,3).add(in1).setPadding(0).setBorder(Border.NO_BORDER);

        table.addCell(celladd)
                .addCell(a);

        //Invoice Table


        //Table ti =new Table(new float[]{1,4,1,1,1});
       //ti.setWidthPercent(100);
        table.addCell(new Cell(1,1).setPaddingTop(10).setPaddingBottom(10).add(new Paragraph().setTextAlignment(TextAlignment.CENTER).add(new Text("Item/No:").setFont(f).setFontSize(8))));
        Cell b =new Cell(1,1).setPaddingTop(10).setPaddingBottom(10).add(new Paragraph().setTextAlignment(TextAlignment.CENTER).add(new Text("Description").setFont(f)).setFontSize(8));
        b.setWidthPercent(100);

        table.addCell(b);
        table.addCell(new Cell(1,1).setPadding(10).add(new Paragraph().setTextAlignment(TextAlignment.CENTER).add(new Text("Qty(Hrs)").setFont(f).setFontSize(8))));

        table.addCell(new Cell(1,1).setPaddingTop(10).setPaddingBottom(10).add(new Paragraph().setTextAlignment(TextAlignment.CENTER).add(new Text("Unit Price (SGD)").setFont(f).setFontSize(8))));
        table.addCell(new Cell(1,1).setPaddingTop(10).setPaddingBottom(10).add(new Paragraph().setTextAlignment(TextAlignment.CENTER).add(new Text("Total Price (SGD)").setFont(f).setFontSize(8))));

        //---------------------
               DecimalFormat df = new DecimalFormat("#.00");
        ObservableList<Person3> trc = (ObservableList) hm.get("TableItems");
        ListIterator<Person3> li = trc.listIterator();
        int draw_constant = 28;
        int draw_counter = -1;
        while (li.hasNext()) {
            Person3 pe = li.next();
            if (pe.getItemNo().getText().isEmpty()) {
                break;
            }
            table.addCell(new Cell(1, 1).setPadding(8).add(new Paragraph(pe.getItemNo().getText()).setTextAlignment(TextAlignment.CENTER).setFont(f).setFontSize(8)));
            table.addCell(new Cell(1, 1).setPadding(8).add(new Paragraph(pe.getDes().getText()).setTextAlignment(TextAlignment.CENTER).setFont(f1).setFontSize(8)));
            table.addCell(new Cell(1, 1).setPadding(8).add(new Paragraph(pe.getQty().getText()).setFont(f).setFontSize(8).setTextAlignment(TextAlignment.CENTER)));
            table.addCell(new Cell(1, 1).setPadding(8).add(new Paragraph("$" + df.format(Double.valueOf(pe.getUnitPrice().getText()))).setFont(f).setFontSize(8).setTextAlignment(TextAlignment.CENTER)));
            table.addCell(new Cell(1, 1).setPadding(8).add(new Paragraph("$" + df.format(Double.valueOf(pe.getTotal().getText()))).setFont(f).setFontSize(8).setTextAlignment(TextAlignment.CENTER)));
            draw_counter++;
        }
        for (int j = 0; j < (4 - draw_counter); j++) {
            for (int i = 0; i < 5; i++) {
                table.addCell(new Cell(1, 1).setPadding(12).add(new Paragraph().add(new Text("\t")).setTextAlignment(TextAlignment.CENTER).setFont(f1).setFontSize(8)));
            }
        }
        
        
  
        Color magentaColor = new DeviceCmyk(0.f, 0.f, 0.f, 1.f);


                //table.addCell(new Cell(1,3).add(ti).setBorder(Border.NO_BORDER).setPadding(0));


        Double s1 = Utilities.NumUtil.round(Double.valueOf((Double) hm.get("Total")), 2);
        Float s12 = Utilities.NumUtil.round(Float.valueOf((Float) hm.get("GST")), 2);
        Double s123 = Utilities.NumUtil.round(Double.valueOf((Double) hm.get("Total") + (Float) hm.get("GST")), 2);
        
         Table t1=new Table(new float[]{3,2,1});
         t1.setWidthPercent(100);
          PdfFont f2 = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLDOBLIQUE);
         t1.addCell(new Cell(3,1).setWidth(250).add(new Paragraph("Note. Any discrepancy in the invoice or the time sheet shall be "
                 + "brought to our notice within 48 hours of submission of the invoice.").setFont(f2).setFontSize(8)));
         t1.addCell(new Cell(1,1).setWidth(202.2f).add(new Paragraph("Total").setTextAlignment(TextAlignment.RIGHT).setMarginRight(7).setFont(f).setFontSize(8)));
         t1.addCell(new Cell(1, 1).add(new Paragraph("$" + df.format(s1)).setTextAlignment(TextAlignment.CENTER).setFont(f).setFontSize(8)));
         t1.addCell(new Cell(1,1).setWidth(202.2f).add(new Paragraph("Add GST 7%").setTextAlignment(TextAlignment.RIGHT).setMarginRight(7).setFont(f).setFontSize(8)));
        t1.addCell(new Cell(1, 1).add(new Paragraph("$" + df.format(s12)).setTextAlignment(TextAlignment.CENTER).setFont(f).setFontSize(8)));
         t1.addCell(new Cell(1,1).setWidth(202.2f).add(new Paragraph("Amount Due").setTextAlignment(TextAlignment.RIGHT).setMarginRight(7).setFont(f).setFontSize(8)));
        t1.addCell(new Cell(1, 1).add(new Paragraph("$" + df.format(s123)).setTextAlignment(TextAlignment.CENTER).setFont(f).setFontSize(8)));

           Cell c = new Cell(1,5).add(t1).setBorder(Border.NO_BORDER).setPadding(0);

         table.addCell(c);
         Table t2= new Table(2);

         Cell fin=new Cell(1,1)
                .add(new Paragraph("All cheques should be crossed and made payable to \"Awin Engineering Pte. Ltd\".").setFont(f1).setFontSize(8))
                .add(new Paragraph("\n"))
                .add(new Paragraph("OCBC A/c No: 686515487001").setFont(f1).setFontSize(8))
                .add(new Paragraph("All cheques accounts will accrue interest at a rate of 1.5% for each 60days or part thereof beyond due date.")
                .setFont(f1).setFontSize(8)).add(new Paragraph("\n ")).setBorder(Border.NO_BORDER);
         //fin.setWidth(350);
         t2.addCell(fin);
         t2.addCell(new Cell(1,1).setBorder(Border.NO_BORDER).add(new Paragraph("\n\n\n\n\n\n\n\tFor and on behalf of Awin Engineering Pte. Ltd\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"
                 + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t").setFont(f1).setFontSize(8).setUnderline(1.0f, 7.5f)));
        table.addCell(new Cell(1,5).add(t2).setPadding(0));
          document.add(table);

        System.out.println();
         System.out.println(pdf.getNumberOfPages());
         if (draw_counter < 4) {
            PdfCanvas canvas = new PdfCanvas(pdf.getFirstPage());
            int yaxis = 413 - draw_constant * draw_counter;
            canvas.setStrokeColor(magentaColor)
                    .moveTo(55, yaxis).lineTo(546, 329).closePathStroke();//moveTo(55, 413).lineTo(546, 329).closePathStroke();//419-395
         }
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




