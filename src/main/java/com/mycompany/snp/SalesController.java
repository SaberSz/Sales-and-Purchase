/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.snp;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dylan
 */
public class SalesController implements Initializable {
    public static boolean SD[]= {false,false,false,false,false,false};
    static boolean tock=true;
    @FXML
    private JFXHamburger SalesHam;
    @FXML
    private JFXDrawer SalesDraw;

    @FXML
    private ScrollPane QoutPane;

    @FXML
    private AnchorPane oldPOPane;

    @FXML
    private ScrollPane InvoicePane;

    @FXML
    private ScrollPane newPOPane;

    @FXML
    private ScrollPane newEqPane;
    @FXML
    private JFXDatePicker Edate;
    @FXML
    private JFXTextField ENo;
    @FXML
    private JFXComboBox<String> cmp;
    @FXML
    private JFXTextArea EDes;
    @FXML
    private JFXTextField CName;
    @FXML
    private JFXTextField CPhone;
    @FXML
    private JFXTextField Cmail;
    @FXML
    private JFXTextArea Cadd;
    @FXML
    private JFXTextField Qno;
    @FXML
    private TableView<Person> table1;
    @FXML
    private JFXComboBox<String> QnoBox;
    @FXML
    private JFXDatePicker Edate1;
    @FXML
    private JFXTextField ENo1;
    @FXML
    private JFXTextArea EDes1;
    @FXML
    private JFXTextField CName1;
    @FXML
    private JFXTextField CPhone1;
    @FXML
    private JFXTextField Cmail1;
    @FXML
    private JFXTextArea Cadd1;
    @FXML
    private JFXTextField ECom1;
    @FXML
    private JFXTextField Qno1;
    @FXML
    private TableView<Person> table11;
    @FXML
    private AnchorPane ContentQNoPane;
    @FXML
    private TableView<Person2> table12;
    @FXML
    private TableView<Person2> table111;
    @FXML
    private JFXComboBox<String> QnoBox1;
    @FXML
    private JFXTextField PjNo;
    @FXML
    private JFXTextField PrNo;
    @FXML
    private JFXDatePicker DateRec;
    @FXML
    private JFXDatePicker EstDate;
    @FXML
    private JFXTextField EsVal;
    @FXML
    private JFXTextArea ProDes;
    @FXML
    private AnchorPane eq_newpane;
    @FXML
    private AnchorPane eq_delpane;
    @FXML
    private JFXComboBox<String> eqno_del;
    @FXML
    private JFXComboBox<String> cmp_del;
    @FXML
    private JFXComboBox<String> email_del;
    @FXML
    private JFXDatePicker date_del;
    @FXML
    private AnchorPane insideINVPane;
    @FXML
    private JFXTextField inv_no;
    @FXML
    private JFXTextField inv_cmp;
    @FXML
    private JFXTextField inv_tum;
    @FXML
    private JFXTextField inv_qno;
    @FXML
    private JFXTextField inv_po;
    @FXML
    private JFXTextField inv_sp;
    @FXML
    private JFXTextField inv_acc;
    @FXML
    private JFXTextArea inv_to;
    @FXML
    private TableView<Person3> inv_newtable;
    @FXML
    private JFXTextField inv_gst;
    @FXML
    private JFXTextField inv_total;
    @FXML
    private JFXTextField inv_amt;
    @FXML
    private AnchorPane eq_delpane1;
    @FXML
    private JFXComboBox<String> eqno_del1;
    @FXML
    private JFXComboBox<String> cmp_del1;
    @FXML
    private JFXComboBox<String> email_del1;
    @FXML
    private JFXDatePicker date_del1;
    @FXML
    private JFXComboBox<Integer> inv_pno;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SalesDraw.setDisable(false);
        SalesDraw.setVisible(true);
        SalesDraw.toBack();
        QoutPane.setDisable(true);
        QoutPane.setVisible(false);
        oldPOPane.setDisable(true);
        oldPOPane.setVisible(false);
        InvoicePane.setDisable(true);
        InvoicePane.setVisible(false);
        newPOPane.setDisable(true);
        newPOPane.setVisible(false);
        newEqPane.setDisable(false);
        newEqPane.setVisible(true);
        
        cmp.getItems().add("Awin");
        cmp.getItems().add("Steels");
        try{
            String sql="SELECT `Qno` FROM `qoutation` WHERE 1";
            PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                QnoBox.getItems().add(rs.getString(1));
                QnoBox1.getItems().add(rs.getString(1));
                System.out.println(rs.getString(1));
            }
        }
        catch(Exception e){
            
        }
            
        try{
        VBox box = FXMLLoader.load(getClass().getResource("/fxml/SalesDrawer.fxml"));
       System.out.println(2);
        SalesDraw.setSidePane(box);
        } catch (IOException ex) {
           Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
       }
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(SalesHam);//for left arrow
       // HamburgerSlideCloseTransition transition = new HamburgerSlideCloseTransition(SalesHam);//For X Mark
       transition.setRate(-1);
        SalesHam.addEventHandler(MouseEvent.MOUSE_PRESSED,(e)->{
        transition.setRate(transition.getRate()*-1);
        transition.play();
        if(SalesDraw.isShown()){
      SalesDraw.close(); 
      SalesDraw.toBack();
        }
        else{
            SalesDraw.toFront();
            SalesDraw.open();
        } 
        });
       // newEnquiryPane_PriceBoxFill();
        table1.setEffect(new GaussianBlur(20));
        table12.setEffect(new GaussianBlur(20));
        table11.setEffect(new GaussianBlur(20));
        table111.setEffect(new GaussianBlur(20));
        insideINVPane.setEffect(new GaussianBlur(20));
        tock=true;
        
        inv_total.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
             if (!newValue.matches("\\d*")) {
                inv_total.setText(newValue.replaceAll("[^\\d]", ""));  
            }
            else
            {  
                if(inv_total.getText().equals(""))
            {
                inv_gst.setText("0");
                inv_amt.setText("0");     
                
            }
                else
            {
                inv_gst.setText(String.valueOf(Math.round((Double.valueOf(inv_total.getText())*0.07)* 100d) / 100d));//Math.round(value * 100000d) / 100000d
                inv_amt.setText(String.valueOf(Float.valueOf(inv_total.getText())+Float.valueOf(inv_gst.getText())));
            }
            }
        });
        /**
         *        inv_total.textProperty().addListener(new ChangeListener<String>() {
    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, 
        String newValue) {
        if (!newValue.matches("\\d*")) {
            inv_total.setText(newValue.replaceAll("[^\\d]", ""));
        }
    }
});
         */
        threadtock();
    }

 public void threadtock() {
  final java.util.Timer timer = new java.util.Timer();
      final TimerTask delayedThreadStartTask = new TimerTask() {
          public void run() {
  try
          {
              if(tock){
                      if(SD[0])
                          {
                               
                                QoutPane.setDisable(true);
                                QoutPane.setVisible(false);
                                oldPOPane.setDisable(true);
                                oldPOPane.setVisible(false);
                                InvoicePane.setDisable(true);
                                InvoicePane.setVisible(false);
                                newPOPane.setDisable(true);
                                newPOPane.setVisible(false);
                                newEqPane.setDisable(false);
                                newEqPane.setVisible(true); 

                           SD[0]=false;
                          }
                        else if(SD[2])
                        {
                            
                                QoutPane.setDisable(false);
                                QoutPane.setVisible(true);
                                oldPOPane.setDisable(true);
                                oldPOPane.setVisible(false);
                                InvoicePane.setDisable(true);
                                InvoicePane.setVisible(false);
                                newPOPane.setDisable(true);
                                newPOPane.setVisible(false);
                                newEqPane.setDisable(true);
                                newEqPane.setVisible(false); 
                                try{
                                    QnoBox.getItems().clear();
            String sql="SELECT `Qno` FROM `qoutation` WHERE 1";
            PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                QnoBox.getItems().add(rs.getString(1));
                System.out.println(rs.getString(1));
            }
        }
        catch(Exception e){
            
        }
                            SD[2]=false;
                        }
                        else if(SD[3])
                        {
                            
                                QoutPane.setDisable(true);
                                QoutPane.setVisible(false);
                                oldPOPane.setDisable(true);
                                oldPOPane.setVisible(false);
                                InvoicePane.setDisable(true);
                                InvoicePane.setVisible(false);
                                newPOPane.setDisable(false);
                                newPOPane.setVisible(true);
                                newEqPane.setDisable(true);
                                newEqPane.setVisible(false); 
                                 try{
                                    QnoBox1.getItems().clear();
            String sql="SELECT `Qno` FROM `qoutation` WHERE 1";
            PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                QnoBox1.getItems().add(rs.getString(1));
                System.out.println(rs.getString(1));
            }
        }
        catch(Exception e){
            
        }
                            SD[3]=false;
                        } 
                        else if(SD[4])
                        {
                            
                                QoutPane.setDisable(true);
                                QoutPane.setVisible(false);
                                oldPOPane.setDisable(false);
                                oldPOPane.setVisible(true);
                                InvoicePane.setDisable(true);
                                InvoicePane.setVisible(false);
                                newPOPane.setDisable(true);
                                newPOPane.setVisible(false);
                                newEqPane.setDisable(true);
                                newEqPane.setVisible(false); 
                            SD[4]=false;
                        }
                      else if(SD[5])
                        {
                            
                                QoutPane.setDisable(true);
                                QoutPane.setVisible(false);
                                oldPOPane.setDisable(true);
                                oldPOPane.setVisible(false);
                                InvoicePane.setDisable(false);
                                InvoicePane.setVisible(true);
                                newPOPane.setDisable(true);
                                newPOPane.setVisible(false);
                                newEqPane.setDisable(true);
                                newEqPane.setVisible(false); 
                            SD[5]=false;
                        }
              }
              else{
                  timer.cancel();  // Terminates this timer, discarding any currently scheduled tasks.
                  timer.purge();   // Removes all cancelled tasks from this timer's task queue.
              }

                         if(tock)
                          {
                             threadtock();
                          }

          }
          catch(Exception e)
          {
              e.printStackTrace();
          }

                  }
      };

      timer.schedule(delayedThreadStartTask, 500);//0.5 second
  }
 
 //columns for table1
 TableColumn indexCol = new TableColumn("S. No.");
 TableColumn desCol = new TableColumn("Description");
 TableColumn quantityCol = new TableColumn("Quantity"); 
 TableColumn unitCol = new TableColumn("Unit/(SGD)"); 
 TableColumn totalCol = new TableColumn("Total (SGD)"); 
 
 
  void newEnquiryPane_PriceBoxFill_Awin(TableView<Person> tables, ObservableList<Person> data){
      indexCol.setSortable(false);
     desCol.setSortable(false);
     quantityCol.setSortable(false);
     unitCol.setSortable(false);
     totalCol.setSortable(false);
     indexCol.setPrefWidth(50);
     desCol.setPrefWidth(150); 
     quantityCol.setPrefWidth(100);
     unitCol.setPrefWidth(150);
     totalCol.setPrefWidth(50);
        
           
       
    tables.getColumns().addAll(indexCol, desCol, quantityCol, unitCol ,totalCol);
   
    
    indexCol.setCellValueFactory(
    new PropertyValueFactory<Person,String>("firstName")
    );
    desCol.setCellValueFactory(
    new PropertyValueFactory<Person,String>("lastName")
    );
     quantityCol.setCellValueFactory(     
             new PropertyValueFactory<Person,String>("email")
    );             
    
    unitCol.setCellValueFactory(
             new PropertyValueFactory<Person,String>("remark")
    );  
    totalCol.setCellValueFactory(new PropertyValueFactory<Person,String>("total"));       
     
    tables.setItems(data);
    //return tables;
 }
 
 
 TableColumn SNCol = new TableColumn("S.N");
 TableColumn PosCol = new TableColumn("Position");
 TableColumn NRCol = new TableColumn("Normal Rate/HR(S$) (Mon to Fri 8am to 5pm)"); 
 TableColumn BeyondCol = new TableColumn("Beyond Normal Hours and Saturdays (S$)"); 
 TableColumn HolidayCol = new TableColumn("Sundays and Public Holidays (S$)"); 
 TableColumn remarkCol = new TableColumn("Remarks"); 
 
 void newEnquiryPane_PriceBoxFill_Steels(TableView<Person2> tables,ObservableList<Person2> data){
      SNCol.setSortable(false);
     PosCol.setSortable(false);
     NRCol.setSortable(false);
     BeyondCol.setSortable(false);
     HolidayCol.setSortable(false);
     remarkCol.setSortable(false);
     SNCol.setPrefWidth(50);
        
        
           
       
    tables.getColumns().addAll(SNCol, PosCol, NRCol, BeyondCol ,HolidayCol,remarkCol);
    
    
    SNCol.setCellValueFactory(
    new PropertyValueFactory<Person,String>("SN")
    );
    PosCol.setCellValueFactory(
    new PropertyValueFactory<Person,String>("Position")
    );
     NRCol.setCellValueFactory(     
             new PropertyValueFactory<Person,String>("NormalRate")
    );             
    
    BeyondCol.setCellValueFactory(
             new PropertyValueFactory<Person,String>("BeyondNormalHours")
    );  
    HolidayCol.setCellValueFactory(new PropertyValueFactory<Person,String>("Holidays")); 
    
    remarkCol.setCellValueFactory(new PropertyValueFactory<Person,String>("Remarks")); 
     
    tables.setItems(data);                         
 }
 
 TableColumn Item = new TableColumn("S. No.");
 TableColumn DESCOl = new TableColumn("Description");
 TableColumn QtyCOl = new TableColumn("Quantity"); 
 TableColumn UtCol = new TableColumn("Unit/(SGD)"); 
 TableColumn Total = new TableColumn("Total (SGD)"); 
 
 
  void newInvoicePane_PriceBoxFill(TableView<Person3> tables, ObservableList<Person3> data){
      Item.setSortable(false);
     DESCOl.setSortable(false);
     QtyCOl.setSortable(false);
     UtCol.setSortable(false);
     Total.setSortable(false);
     Item.setPrefWidth(50);
     DESCOl.setPrefWidth(150); 
     QtyCOl.setPrefWidth(100);
     UtCol.setPrefWidth(150);
     Total.setPrefWidth(50);
        
           
       
    tables.getColumns().addAll(Item, DESCOl, QtyCOl, UtCol ,Total);
   
    
    Item.setCellValueFactory(
    new PropertyValueFactory<Person,String>("ItemNo")
    );
    DESCOl.setCellValueFactory(
    new PropertyValueFactory<Person,String>("Des")
    );
     QtyCOl.setCellValueFactory(     
             new PropertyValueFactory<Person,String>("Qty")
    );             
    
    UtCol.setCellValueFactory(
             new PropertyValueFactory<Person,String>("UnitPrice")
    );  
    Total.setCellValueFactory(new PropertyValueFactory<Person,String>("Total"));       
     
    tables.setItems(data);
    //return tables;
  } 

 static String cid; //for Customer Id
 static String eno; //for enquiry number
 static String cmpname;
    @FXML
    private void saveNewEnq(MouseEvent event) {
        //store new enquiry data entered into the database
           String dg;
           String mx;
           String mx1;
           int[] mxval=new int[100000];
        try{
            if(Edate.getValue().toString().isEmpty()|| ENo.getText().trim().isEmpty() || cmp.getValue().isEmpty() || EDes.getText().trim().isEmpty() 
                    || CName.getText().trim().isEmpty() ||CPhone.getText().trim().isEmpty() || Cmail.getText().trim().isEmpty() ||Cadd.getText().trim().isEmpty())
            {
                Utilities.AlertBox.notificationWarn("Error","Some of the fields seem to be empty");
            }
            else
            {
                try {
                        if(Utilities.AlertBox.alertoption("Alert!","Are you Sure?","Are you sure you wnat to save the entered enquiry details?"
                                + "Please note these details are not editable"))
                        {
                            //The below code is used to fetch a CID with the same customer details. This is to see if the customer is already registered or not.
                            
                            String sql= "Select CID from customer where name = ? and email = ? and phone = ? and address = ? ; ";
                            PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
                            stmt.setString(1,CName.getText().trim());
                            stmt.setString(2,Cmail.getText().trim());
                            stmt.setString(3,CPhone.getText().trim());
                            stmt.setString(4,Cadd.getText().trim());
                            ResultSet rs = stmt.executeQuery();
                        
                            if(rs.next()){
                                cid= rs.getString(1);
                            }
                            else
                            {
                                    //The below code is used to add a new customer with a sequential CID number.
                                    String sql1= "{ call insertCustomer(?,?,?,?)}";
                                stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql1);
                                stmt.setString(1,CName.getText().trim());
                                stmt.setString(3,Cmail.getText().trim());
                                stmt.setString(4,CPhone.getText().trim());
                                stmt.setString(2,Cadd.getText().trim());
                                stmt.executeQuery();
                               
                                
                                //The below code is used to fetch the CID of newly added customer.
                                String sql2= "Select CID from customer where name = ? and email = ? and phone = ? and address = ? ; ";
                                stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql2);
                                stmt.setString(1,CName.getText().trim());
                                stmt.setString(2,Cmail.getText().trim());
                                stmt.setString(3,CPhone.getText().trim());
                                stmt.setString(4,Cadd.getText().trim());
                                rs = stmt.executeQuery();
                                
                                rs.next();
                                cid= rs.getString(1);
                            }
                            
                            //The below code is used to fetch enquiry numbers with the same details.
                             String sql1= "Select * from enquiry where Eqno = ? and date = ? and Cmpname = ? and CID = ? ; ";
                            stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql1);
                            stmt.setString(1,ENo.getText().trim());
                            stmt.setString(2,Edate.getValue().toString());
                            stmt.setString(3,cmp.getValue());
                            stmt.setString(4,cid);
                            rs = stmt.executeQuery();
                            
                            
                            //The below code is used to replace some character due to duplicate enquiry numbers.
                            if(rs.next()){
                                eno=rs.getString(1);
                                eno=eno+"_r";
                                Utilities.AlertBox.notificationInfo("Note","This enquiry number has been"
                                        + " repeated by the same customer on the same"
                                        + " date. Therefore the enquiry "
                                        + "number has been replaced with the following : "+eno);
                            }
                            else{
                                eno = ENo.getText().trim();
                            }
                            
                            //The below code is used to insert the details into the enquiry table
                            String sql3 = "INSERT INTO `enquiry`(`Eqno`, `Date`, `Cmpname`, `Subject`, `CID`) VALUES (?,?,?,?,?)";
                            stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql3);
                            stmt.setString(1,eno);
                            stmt.setString(2,Edate.getValue().toString());
                            stmt.setString(3,cmp.getValue());
                            stmt.setString(4,EDes.getText().trim());
                            stmt.setString(5,cid);
                            stmt.executeUpdate();
                            Utilities.AlertBox.notificationInfo("Success","New enquiry details saved");
                            cmpname=cmp.getValue();
                            if(Utilities.AlertBox.alertoption("Quotation","Do you want to generate the quotation number?"," Note that once a quotation number is generated, "
                                    + "you can edit/revise the quotation in the Quotation Pane.")){
                            if(cmpname.equals("Awin"))
                            {
                                table12.setDisable(true);
                                table12.setVisible(false);
                                table1.setDisable(false);
                                table1.setVisible(true);
                                table1.getItems().clear();
                                table1.getColumns().clear();
                                 ObservableList<Person> data;    
                                    data = FXCollections.observableArrayList();
                                    for(int z=1; z<101;z++){
                                data.add(new Person(String.valueOf(z),"","","",""));
                            }
                                newEnquiryPane_PriceBoxFill_Awin(table1,data);
                                table1.setEffect(new ColorAdjust());
                            }
                            else
                            {
                                table1.setDisable(true);
                                table1.setVisible(false);
                                table12.setDisable(false);
                                table12.setVisible(true);
                                table12.getItems().clear();
                                table12.getColumns().clear();
                                ObservableList<Person2> data;    
                                data = FXCollections.observableArrayList();
                                for(int z=0; z<100;z++){
                                data.add(new Person2("","","","","",""));
                            }
                               
                                newEnquiryPane_PriceBoxFill_Steels(table12,data);
                                table12.setEffect(new ColorAdjust());
                            }
                            String compname1=cmp.getValue();
                               String compname=cmp.getValue();
       if(compname.equalsIgnoreCase("Awin")){
           compname="AE";
       }else{
           compname="SC";
       }
       String date= Utilities.Date.Date();
       date=date.substring(2,5);
      compname=date+compname;
      compname=compname+"-QT-";
      
       System.out.println(compname);
      String descr=EDes.getText();
       Statement st;
     
     try{       
 String suql = "SELECT Qno FROM `qoutation` WHERE 1";
                            st = com.mycompany.snp.MainApp.conn.prepareStatement(suql);
                           rs=st.executeQuery(suql);
                           int i=0,j=0;
                           while(rs.next()){
                    
                           mx=rs.getString("Qno");
                 
                           mx1=mx.substring(10,12);
                                System.out.println("mx1 val:"+mx1);
                           mxval[i]=Integer.parseInt(mx1);
                           i++;      
                           }
                           int temp;
                           //bubble sorted the array and picked the last element to obtain the max value
                     for(int x=0;x<i-1;x++){
                          for(int y=0;y<=i-x-1;y++){
                              if(mxval[y]>mxval[y+1]){
                                  temp=mxval[y];
                                  mxval[y]=mxval[y+1];
                                  mxval[y+1]=temp;
                              }
                                  
                          }
                      }
                    temp=mxval[i];
                    System.out.println("temp="+temp);
                           
// this is the auto quote gen thing 
         CallableStatement cs;
       String sql008= "{ call quotelastdigitautogen(?,?)}";
       cs=com.mycompany.snp.MainApp.conn.prepareCall(sql008);
       cs.registerOutParameter(1,java.sql.Types.INTEGER);
       cs.setInt(2,temp);
       cs.executeQuery();
        int dig=cs.getInt(1);
        if(dig<10){
         dg=String.valueOf(dig);dg="00"+dg;
        }
        else if(dig>=10 && dig<100)
        {
             dg=String.valueOf(dig);dg="0"+dg;
        }  
        else{
             dg=String.valueOf(dig);
        }
       //18-AE or SC - QT - 001
       compname=compname+dg;
       Qno.setText(compname);
       System.out.println(compname);
    Utilities.AlertBox.notificationWarn("Quotation Number","Please make sure you that you have noted down the generated quotation number.");
    
    suql = "INSERT INTO `qoutation`(`QNo`) VALUES (?)";
                            stmt = com.mycompany.snp.MainApp.conn.prepareStatement(suql);
                            stmt.setString(1,compname);
                            stmt.executeUpdate();
    String suql1 = "INSERT INTO `eqrel`(`Eno`, `QNo`, `Date1`, `Cmpname`, `CID`) VALUES (?,?,?,?,?)";
                            stmt = com.mycompany.snp.MainApp.conn.prepareStatement(suql1);
                            stmt.setString(1,eno);
                            stmt.setString(2,compname);
                            stmt.setString(3,Edate.getValue().toString());
                            stmt.setString(4,compname1);
                            stmt.setString(5,cid);
                            
                            stmt.executeUpdate();
        
      } catch (SQLException exe){
                   Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, exe);
                    Utilities.AlertBox.notificationWarn("Error","Oops something went wrong!");
                    Utilities.AlertBox.showErrorMessage(exe);
      }
    
                            
        }
                        
                        }   
                } catch (SQLException ex) {
                    Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
                    Utilities.AlertBox.notificationWarn("Error","Please make sure you have entered all the details correctly.");
                    Utilities.AlertBox.showErrorMessage(ex);
                }
                
            }
        }
        catch(NullPointerException e){
          Utilities.AlertBox.notificationWarn("Error","Some of the fields seem to be empty"); 
        }
}


       void Quotation_insert_into_awin_table(String qo,TableView<Person> tables){//method to insert quotaion details into database for awin
           PreparedStatement stmt;
           try{

                         //first deleting quotations details of the particular qno
                        

                        String suqdel="DELETE FROM `quotationdetails_awin` WHERE qno=?";

                                 stmt = com.mycompany.snp.MainApp.conn.prepareStatement(suqdel);
                                 stmt.setString(1,qo);
                                 stmt.executeUpdate();
                                 System.out.println("the deleting is done if the contents were present before");




                         ObservableList<Person> trc;
                      trc =FXCollections.observableArrayList(tables.getItems());
                      trc.add(new Person("","","","",""));
                      int i=0;
                      while(i<100){
                      Person p= trc.get(i);
                      if(p.getLastName().getText().trim().equalsIgnoreCase("")){
                          break;
                      }
                      else{
                       /* System.out.print(p.getFirstName().getText()+"\t");
                        System.out.print(p.getLastName().getText()+"\t");
                        System.out.print(p.getEmail().getText()+"\t");
                        System.out.print(p.getRemark().getText()+"\t");
                        System.out.println(p.getTotal().getText()+"\t");
                          */

                        String d=p.getLastName().getText();
                        int q = Integer.parseInt(p.getEmail().getText());
                        int r=Integer.parseInt(p.getRemark().getText());
                        long s=Long.parseLong(p.getTotal().getText());
                       
                        System.out.println("the quotation no is "+qo);
                        int no=Integer.parseInt(p.getFirstName().getText());
                      try{

                         
                    

                          //first deleting quotations details of the particular qno
                     

                                         
                     String suql1 = "INSERT INTO `quotationdetails_awin`(`Sno`, `Des`, `quantity`, `unit`, `total`, `qno`) VALUES (?,?,?,?,?,?)";
                                            stmt = com.mycompany.snp.MainApp.conn.prepareStatement(suql1);
                                            stmt.setInt(1,no);
                                            stmt.setString(2,d);
                                            stmt.setInt(3,q);
                                            stmt.setInt(4,r);
                                            stmt.setLong(5,s);
                                            stmt.setString(6,qo);
                                            stmt.executeUpdate();
                      }
                      catch(SQLException exe){
                                   Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, exe);
                                    Utilities.AlertBox.notificationWarn("Error","Oops something went wrong!");
                                    Utilities.AlertBox.showErrorMessage(exe);
                      }

                      }
                        i++;
                      }
             if(i==0){
                 Utilities.AlertBox.notificationWarn("Blank Quotation","The quotation box seems to be blank.");           
             }else{
               Utilities.AlertBox.notificationInfo("Success","Your Quotation was saved successfully!");
              edit_button_hit_in_QPane=false;
              table11.setEditable(false);
              table111.setEditable(false);
             }
             
            }
       catch(Exception e){
            Utilities.AlertBox.notificationWarn("Error","Oops something went wrong!");
           Utilities.AlertBox.showErrorMessage(e);
       }
       }
       
       
       void Quotation_insert_into_steel(String qo,TableView<Person2> tables){//method to insert quotaion details into database for steel
   
            PreparedStatement stmt;
             try{

                   
                        //deleting befoehand just in case...
                         

                      String suqdel="DELETE FROM `quotationdetails_steels` WHERE qno=?";

                                 stmt = com.mycompany.snp.MainApp.conn.prepareStatement(suqdel);
                                 stmt.setString(1,qo);
                                 stmt.executeUpdate();
                                 System.out.println("the deleting is done if the contents were present before");



                         ObservableList<Person2> trc;
                      trc =FXCollections.observableArrayList(tables.getItems());
                      trc.add(new Person2("","","","","",""));
                      int i=0;
                      while(i<100){
                      Person2 p= trc.get(i);
                      if(p.getPosition().getText().trim().equalsIgnoreCase("")){
                          break;
                      }
                      else{
                       /* System.out.print(p.getFirstName().getText()+"\t");
                        System.out.print(p.getLastName().getText()+"\t");
                        System.out.print(p.getEmail().getText()+"\t");
                        System.out.print(p.getRemark().getText()+"\t");
                        System.out.println(p.getTotal().getText()+"\t");
                          */

                        String d=p.getPosition().getText();
                        String q =p.getNormalRate().getText();
                        String r=p.getBeyondNormalHours().getText();
                        String s=p.getRemarks().getText();
                        String h=p.getHolidays().getText();
                     
                        System.out.println("the quotation no is "+qo);
                        int no=Integer.parseInt(p.getSN().getText());

                      

                      try{//deleting befoehand just in case...
                         

                     String suql1 = "INSERT INTO `quotationdetails_steels`(`Sno`, `Pos`, `NormalRate`, `BeyondNormalRate`, `Holidays`, `Remarks`, `qno`) VALUES (?,?,?,?,?,?,?)";
                                            stmt = com.mycompany.snp.MainApp.conn.prepareStatement(suql1);
                                            stmt.setInt(1,no);
                                            stmt.setString(2,d);
                                            stmt.setString(3,q);
                                            stmt.setString(4,r);
                                            stmt.setString(5,h);
                                            stmt.setString(6,s);
                                            stmt.setString(7,qo);
                                            stmt.executeUpdate();
                      }
                      catch(SQLException exe){
                                   Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, exe);
                                    Utilities.AlertBox.notificationWarn("Error","Oops something went wrong!");
                                    Utilities.AlertBox.showErrorMessage(exe);
                      }

                      }
                        i++;
                      }
             if(i==0){
                 Utilities.AlertBox.notificationWarn("Blank Quotation","The quotation box seems to be blank.");           
             }else{
               Utilities.AlertBox.notificationInfo("Done","");           
             }
             
            }
            
       catch(Exception e){
            Utilities.AlertBox.notificationWarn("Error","Oops something went wrong!");
           Utilities.AlertBox.showErrorMessage(e);
       }
    
             }
       
       
    

    @FXML
    private void Quotation_Save_Button_Clicked_inEnqPane(MouseEvent event) {
        //also note that as soon as the enquiry is detials are saved. A quotation number should be generated. The person can then either
        //enter the details there itself or can just hit the save button to save as a draft(quotaion). 
        //When the enquiry details are saved aert the user to note the quotation number
        //We will not require any combo box for selections here. 
        //That can be done in the quotation pane where if he wants to edit the quoataion draft  or even revise it.
        //to get the data from the table
        String qo=Qno.getText();   
        if (cmp.getValue()=="Awin"){
       //this is for awin table 
       
       Quotation_insert_into_awin_table(qo,table1);
        }
        else
        {
           //for steels table 
       Quotation_insert_into_steel(qo,table12);
       
                      }
        
        }

       
    void generate_Awin_Table(boolean input){
        try {
            //here input is used to indicate if table is editable or not
            String qno= Qno1.getText();
            table111.setDisable(true);
            table111.setVisible(false);
            table111.setEditable(false);
            table11.setDisable(false);
            table11.setVisible(true);
            table11.setEditable(false);
            table11.getItems().clear();
            table11.getColumns().clear();
            //fill details of Awin table
            String sql1="SELECT * FROM `QuotationDetails_Awin` WHERE qno = ?";
            PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql1);
            stmt.setString(1, qno);
            ResultSet rs=stmt.executeQuery();
            ObservableList<Person> data;
            data = FXCollections.observableArrayList();
            while(rs.next()){
                Person p=new Person(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4), rs.getString(5));
                p.setEdit(input);
                data.add(p);
                System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+"  "+rs.getString(5));
            }
            newEnquiryPane_PriceBoxFill_Awin(table11,data);
            table11.setEditable(false);
            table11.setEffect(new ColorAdjust());
        } catch (SQLException ex) {
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
     void generate_Awin_Table(boolean input,boolean extra){
        try {
            //here input is used to indicate if table is editable or not
            String qno= Qno1.getText();
            table111.setDisable(true);
            table111.setVisible(false);
            table111.setEditable(false);
            table11.setDisable(false);
            table11.setVisible(true);
            table11.setEditable(false);
            table11.getItems().clear();
            table11.getColumns().clear();
            //fill details of Awin table
            String sql1="SELECT * FROM `QuotationDetails_Awin` WHERE qno = ?";
            PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql1);
            stmt.setString(1, qno);
            ResultSet rs=stmt.executeQuery();
            ObservableList<Person> data;
            data = FXCollections.observableArrayList();
            int count=0;
            while(rs.next()){
                Person p=new Person(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4), rs.getString(5));
                p.setEdit(input);
                data.add(p);
                System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+"  "+rs.getString(5));
                count++;
            }
             if(extra){
                for(int o=count+1;o<count+100;o++){
                    data.add(new Person(String.valueOf(o),"","","",""));
                }
                }
            newEnquiryPane_PriceBoxFill_Awin(table11,data);
            table11.setEditable(false);
            table11.setEffect(new ColorAdjust());
        } catch (SQLException ex) {
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     
    void generate_Steels_Table(boolean input){
        //here input is used to indicate if table is editable or not
        try {
            //Generate the Steels Quotation Table 
            String qno= QnoBox.getValue();
                table11.setDisable(true);
                                    table11.setVisible(false);
                                     table11.setEditable(false);
                                    table111.setDisable(false);
                                    table111.setVisible(true);
                                     table111.setEditable(false);
                                    table111.getItems().clear();
                                    table111.getColumns().clear();
                 String sql1="SELECT * FROM `QuotationDetails_Steels` WHERE qno = ?";
                PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql1);
                stmt.setString(1, qno);
                ResultSet rs=stmt.executeQuery();
                ObservableList<Person2> data;    
                data = FXCollections.observableArrayList();
                while(rs.next()){
                    Person2 p= new Person2(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4), rs.getString(5),rs.getString(6));
                    p.setEdit(input);
                    data.add(p );
                }                    
                newEnquiryPane_PriceBoxFill_Steels(table111,data);
                table111.setEffect(new ColorAdjust());
                //fill details of Steels Table
            
        } catch (SQLException ex) {
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    void generate_Steels_Table(boolean input,boolean extra){
        //here input is used to indicate if table is editable or not
        try {
            //Generate the Steels Quotation Table 
            String qno= QnoBox.getValue();
                table11.setDisable(true);
                                    table11.setVisible(false);
                                     table11.setEditable(false);
                                    table111.setDisable(false);
                                    table111.setVisible(true);
                                     table111.setEditable(false);
                                    table111.getItems().clear();
                                    table111.getColumns().clear();
                 String sql1="SELECT * FROM `QuotationDetails_Steels` WHERE qno = ?";
                PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql1);
                stmt.setString(1, qno);
                ResultSet rs=stmt.executeQuery();
                ObservableList<Person2> data;    
                data = FXCollections.observableArrayList();
                while(rs.next()){
                    Person2 p= new Person2(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4), rs.getString(5),rs.getString(6));
                    p.setEdit(input);
                    data.add(p );
                }
                if(extra){
                for(int o=0;o<100;o++){
                    data.add(new Person2("","","","","",""));
                }
                }
                newEnquiryPane_PriceBoxFill_Steels(table111,data);
                table111.setEffect(new ColorAdjust());
                //fill details of Steels Table
            
        } catch (SQLException ex) {
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @FXML
    private void Fill_details_of_existing_Qno_and_Eno(MouseEvent event) {
        //retrieve the details of enquiry using the quotation number.
        edit_button_hit_in_QPane=false;
        try{
            System.out.println("sdfsdfsfddsfsdfsdfsdfsdfs");
            String qno= QnoBox.getValue();
            String sql="SELECT e.Date,e.Eqno,e.Cmpname,e.Subject,c.Name,c.phone,c.email,c.Address  FROM eqrel er join enquiry e on er.eno=e.eqno and er.Date1=e.Date1 and er.cmpname=e.cmpname and er.cid=e.cid join customer c on e.cid=c.cid WHERE er.qno= ? ;";
            PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
            stmt.setString(1, qno);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                
               Edate1.setValue(LocalDate.parse(rs.getString(1)));
               ENo1.setText(rs.getString(2));
               ECom1.setText(rs.getString(3));
               EDes1.setText(rs.getString(4));
                CName1.setText(rs.getString(5));
                 CPhone1.setText(rs.getString(6));
                Cmail1.setText(rs.getString(7));
                 Cadd1.setText(rs.getString(8));
                 Qno1.setText(qno);
                 Edate1.setEditable(false);
  
            }
            if(ECom1.getText().equals("Awin"))
            {
               //Generate the Awin Quotation Table 
                generate_Awin_Table(false);
                
            }
            else{
                if(ECom1.getText().equals("Steels"))
            {
                //Generate the Steels Quotation Table 
                generate_Steels_Table(false);
            }
            }
        }
        catch(NullPointerException e){
             Utilities.AlertBox.notificationWarn("Error","Please select a quotation number first.");             
        } catch (SQLException ex) {
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static boolean edit_button_hit_in_QPane=false;
    static boolean revise_button_hit_in_QPane=false;
    @FXML
    private void Edit_Quotation_in_QuotationPane(MouseEvent event) {
        if(ECom1.getText().equals("Awin")){
        table11.setEditable(true);
        table111.setEditable(false);
        generate_Awin_Table(true,true);//overloaded
        edit_button_hit_in_QPane=true;
        revisedQno="";
        }else if(ECom1.getText().equals("Steels"))
        {
            table11.setEditable(true);
        table111.setEditable(false);
        edit_button_hit_in_QPane=true;
        generate_Steels_Table(true,true);//overloaded
        revisedQno="";
        }
        
        
    }

    @FXML
    private void Save_Quotation_in_QuotationPane(MouseEvent event) {
        String qno=Qno1.getText();
        if(edit_button_hit_in_QPane){
                if(ECom1.getText().equals("Awin")){
                    Quotation_insert_into_awin_table(qno,table11);
                    generate_Awin_Table(false);

                }else if(ECom1.getText().equals("Steels"))
                {
                    Quotation_insert_into_steel(qno,table111);
                     generate_Steels_Table(false);

                }
        
        }
        else if(revisedQno!=""){
            String k=ENo1.getText();
              PreparedStatement stmt;  
      try{
          
          String sql5="Select cid from eqrel where e.qno=? ";//qnoforquery
          stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql5);
                            stmt.setString(1,qnoforquery);
            
            ResultSet rs=stmt.executeQuery();  
            if(rs.next()){
                Integer abc=rs.getInt(1);
     String suql1 = "INSERT INTO `qoutation`(`Qno`,`RevNo`) VALUES (?,?)";
                            stmt = com.mycompany.snp.MainApp.conn.prepareStatement(suql1);
                            stmt.setString(1,revisedQno);
                            stmt.setInt(2,revisedno);
                            stmt.executeUpdate();
     String suql2 = "INSERT INTO `eqrel`(`Eno`, `QNo`, `Date1`, `Cmpname`, `CID`) VALUES (?,?,?,?,?)";
                            stmt = com.mycompany.snp.MainApp.conn.prepareStatement(suql2);
                            stmt.setString(1,k);
                            stmt.setString(2,revisedQno);
                              stmt.setString(3,Edate1.getValue().toString());  stmt.setString(4,CName1.getText());
                                stmt.setInt(5,abc);
                              
                            stmt.executeUpdate();
                            Qno1.setText(revisedQno);
                 if(ECom1.getText().equals("Awin")){
                    Quotation_insert_into_awin_table(revisedQno,table11);
                    
                    generate_Awin_Table(false);

                }else if(ECom1.getText().equals("Steels"))
                {
                    Quotation_insert_into_steel(revisedQno,table111);
                     generate_Steels_Table(false);

                }
            }
      }
      catch(SQLException exe){
                   Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, exe);
                    Utilities.AlertBox.notificationWarn("Error","Oops something went wrong!");
                    Utilities.AlertBox.showErrorMessage(exe);
      }
          
            
        }
        else{
            Utilities.AlertBox.notificationInfo("Sticky Buttons","The quotation has already been saved.");
        }
    }

    @FXML
    private void Generate_Quotation_in_QuotationPane(MouseEvent event) {
    }
    static String revisedQno="";
    static int revisedno;
    static String qnoforquery="";
    @FXML
    private void Revise_Quotation_in_QuotationPane(MouseEvent event) {
       if( Utilities.AlertBox.alertoption("Revision","You just clicked the Revise button!"," Are you sure you want to revise quotation number :"+Qno1.getText())){
                revise_button_hit_in_QPane=true;
                edit_button_hit_in_QPane=false;
                
                try{ 
                    qnoforquery=Qno1.getText();
                     String qno=Qno1.getText();
                           String suql = "SELECT Qno,RevNo FROM `qoutation` WHERE qno= ? ;";
                           PreparedStatement st = com.mycompany.snp.MainApp.conn.prepareStatement(suql);
                           st.setString(1,qno);
                           ResultSet rs=st.executeQuery();
                           
                           while(rs.next()){
                    
                           int k=Integer.valueOf(rs.getString(2));
                           
                           k++;revisedno=k;
                           if(k==1){
                               //first revision
                            revisedQno=Qno1.getText()+".Rev."+String.valueOf(k);
                            
                              }
                           else{
                               //not first revision
                               int len=Qno1.getText().length();
                               int v =Qno1.getText().lastIndexOf('.');
                               String s=Qno1.getText().substring(v+1, len);
                               int z=Integer.valueOf(s);
                               z++;
                               String x=Qno1.getText().substring(0, v+1);
                               revisedQno=x+String.valueOf(z);
                           }
                           System.out.println(revisedQno);
                           }
                                  
                          
                    if(ECom1.getText().equals("Awin")){
                    Quotation_insert_into_awin_table(qno,table11);
                    generate_Awin_Table(true,true);

                }else if(ECom1.getText().equals("Steels"))
                {
                    Quotation_insert_into_steel(qno,table111);
                     generate_Steels_Table(true,true);

                }  
                    
                
                }
                catch(Exception e ){
                  Utilities.AlertBox.showErrorMessage(e);
                }
       }
       else
       {
           System.out.println("dfsdfsdfaysfgsldfugasfoa8sfaof4395723957=3459263935[20");
           //accidental hit
           
           
       }
    }

    @FXML
    private void New_Enquiry_Pane_Clear_Components(MouseEvent event) {      
        ENo.clear();
        Edate.valueProperty().set(null);
        cmp.valueProperty().set(null);
        EDes.clear();
        CName.clear();
        CPhone.clear();
        Cmail.clear();
        Cadd.clear();
        Qno.clear();
        table1.setEffect(new GaussianBlur(20));
        table12.setEffect(new GaussianBlur(20));
     }

    @FXML
    private void power_off(MouseEvent event) {
        try {
            tock=false;
            Stage stage;
            Parent root;
            stage=(Stage) table1.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    static String qno_static="";
    @FXML
    private void tick_in_project(MouseEvent event) {
        try {
            qno_static=QnoBox1.getValue();
            String sql="SELECT `PjNo` FROM `qprel` where  qno =? ;";
            PreparedStatement ps= com.mycompany.snp.MainApp.conn.prepareStatement(sql);
            ps.setString(1,qno_static);
            ResultSet rs =ps.executeQuery();
            if(rs.next())
            {
             //fill datails
             int PjNOs=rs.getInt(1); 
             String sql1="SELECT `PNo`, `PjNo`, `Value`, `Date`, `EstDate`, `Des` FROM `product` WHERE PjNo=?";
             ps= com.mycompany.snp.MainApp.conn.prepareStatement(sql1);
             ps.setInt(1,PjNOs);
             rs =ps.executeQuery();
             while(rs.next()){
                PjNo.setText(rs.getString(2));
                PrNo.setText(rs.getString(1));
                EsVal.setText(rs.getString(3));
                DateRec.setValue(LocalDate.parse(rs.getString(4)));
                EstDate.setValue(LocalDate.parse(rs.getString(5)));
                ProDes.setText(rs.getString(6));
                PjNo.setEditable(false);
                PrNo.setEditable(false);
                EsVal.setEditable(false);
                DateRec.setEditable(false);
                EstDate.setEditable(false);
                ProDes.setEditable(false);
             }
            }
            else{
                //setEditable(true)
                 
                String sql1="SELECT IFNULL(MAX(`PjNo`)+1,1) as 'your value'  FROM `product` WHERE 1;";
             ps= com.mycompany.snp.MainApp.conn.prepareStatement(sql1);
            // ps.setInt(1,PjNOs);
             rs =ps.executeQuery();
             while(rs.next()){
              String PjNOs = rs.getString(1);
              PjNo.setText(PjNOs);
              PjNo.setEditable(false);
                PrNo.setEditable(true);
                EsVal.setEditable(true);
                DateRec.setEditable(true);
                EstDate.setEditable(true);
                ProDes.setEditable(true);
             }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void insert_into_proj_table(){
          try {
            int pjno=Integer.parseInt(PjNo.getText());
            int prno=Integer.parseInt(PrNo.getText());
            int esval=Integer.parseInt(EsVal.getText());
            String daterec= DateRec.getValue().toString();
            String estrec= EstDate.getValue().toString();
            String qnum=qno_static;
            String prodes=ProDes.getText();
            
            
            
            String sql="INSERT INTO `product`(`PNo`, `PjNo`, `Value`, `Date`, `EstDate`,`Des`) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps= com.mycompany.snp.MainApp.conn.prepareStatement(sql);
            ps.setInt(1,prno);
            ps.setInt(2,pjno);
            ps.setInt(3,esval);
            ps.setString(4,daterec);
            ps.setString(5,estrec);
            ps.setString(6,prodes);
            
            
            ps.executeUpdate();
             
            String sql1="INSERT INTO `qprel`(`Qno`, `PjNo`) VALUES (?,?)";
            ps= com.mycompany.snp.MainApp.conn.prepareStatement(sql1);
            ps.setString(1,qnum);
            ps.setInt(2,pjno);
                 ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
            Utilities.AlertBox.showErrorMessage(ex);
             Utilities.AlertBox.notificationInfo("Sticky Buttons","The project information has already been saved.");
        }
    }
    
    
    
    @FXML
    private void save_in_project(MouseEvent event) {
         
        if(edit_button_hit_in_PPane){
            try {
             
                PreparedStatement ps;
                String sql1="DELETE FROM `qprel` WHERE pjno=?;";
                ps= com.mycompany.snp.MainApp.conn.prepareStatement(sql1);
               ps.setInt(1,Integer.valueOf(PjNo.getText()));
             
       
                ps.executeUpdate();
                
                sql1="DELETE FROM `product` WHERE pjno=?";
                ps= com.mycompany.snp.MainApp.conn.prepareStatement(sql1);
               ps.setInt(1,Integer.valueOf(PjNo.getText()));
            
                ps.executeUpdate();
                insert_into_proj_table();
                 Utilities.AlertBox.notificationInfo("Updated","The project information has  been updated.");
            } catch (SQLException ex) {
                Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
                Utilities.AlertBox.showErrorMessage(ex);
            }catch (Exception e) {
                Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, e);
                Utilities.AlertBox.notificationWarn("Error","Error in input!");
                    Utilities.AlertBox.showErrorMessage(e);
                
            }
            
        }
        else{
            
            insert_into_proj_table();
            Utilities.AlertBox.notificationInfo("Saved","The project information has  been saved.");
        }
          PjNo.setEditable(false);
          PrNo.setEditable(false);
          EsVal.setEditable(false);
          DateRec.setEditable(false);
          EstDate.setEditable(false);
          ProDes.setEditable(false);
        
    }
 static boolean edit_button_hit_in_PPane=false;
    @FXML
    
    private void edit_in_project(MouseEvent event) {
        edit_button_hit_in_PPane=true;
         PjNo.setEditable(false);
         PrNo.setEditable(true);
         EsVal.setEditable(true);
         DateRec.setEditable(true);
         EstDate.setEditable(true);
         ProDes.setEditable(true);
            Utilities.AlertBox.notificationInfo("Edit mode","You're now in edit mode.");
    }

    @FXML
    private void refresh_project_pane(MouseEvent event) {
        try{
            QnoBox1.getItems().clear();//quotation combo box
            String sql="SELECT `Qno` FROM `qoutation` WHERE 1";
            PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                QnoBox1.getItems().add(rs.getString(1));
                System.out.println(rs.getString(1));
            }
            PjNo.clear();//project number text box
            PrNo.clear();//product number text box
            EsVal.clear();//estimated value of project text box
            DateRec.valueProperty().set(null);//date the project order was received
            EstDate.valueProperty().set(null);//date completion estimate
            ProDes.clear();//description text area
            PjNo.setEditable(false);
                PrNo.setEditable(false);
                EsVal.setEditable(false);
                DateRec.setEditable(false);
                EstDate.setEditable(false);
                ProDes.setEditable(false);
            
        }
        catch(Exception e){
              Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, e);
                Utilities.AlertBox.showErrorMessage(e);
                System.out.println("error y u comes?");
            
        } 
    }

    @FXML
    private void delNewEnq(MouseEvent event) {
        eq_newpane.setEffect(new GaussianBlur(20));
        eq_newpane.setDisable(true);
        eq_delpane.setVisible(true);
        eq_delpane.setDisable(false);
        try{
            eqno_del.getItems().clear();
            cmp_del.getItems().clear();
            email_del.getItems().clear();
          
            String sql="SELECT `Eqno` FROM `enquiry` WHERE 1 order by `Date` DESC ";
            PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                eqno_del.getItems().add(rs.getString(1));
                //System.out.println(rs.getString(1));
            }
            
                cmp_del.getItems().add("AWIN");
                cmp_del.getItems().add("STEEL");
                
             String sql1="SELECT email FROM `customer` WHERE 1 order by `CID` DESC";
             stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql1);
             rs=stmt.executeQuery();
            while(rs.next()){
                email_del.getItems().add(rs.getString(1));
                
            }
                
            
    } catch(Exception e){
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, e);
                Utilities.AlertBox.showErrorMessage(e);
        } }

    @FXML
    private void Select_for_delete_of_Enquiry(MouseEvent event) {
       try{
         if(eqno_del.getValue().toString().isEmpty()|| cmp_del.getValue().toString().isEmpty() || email_del.getValue().toString().isEmpty() || date_del.getValue().toString().isEmpty())
            {
                Utilities.AlertBox.notificationWarn("Error","Some of the fields seem to be empty");
            }
         else {
             String sql="SELECT * FROM `enquiry` e NATURAL JOIN `customer` c WHERE eqno=? "
                        + "and email=? and cmpname=? and `Date`=? ";
               PreparedStatement ps;
                //
                System.out.println("inside else block nice statements really.");
              boolean f =true;
               ps= com.mycompany.snp.MainApp.conn.prepareStatement(sql);
               ps.setString(1,eqno_del.getValue());
               ps.setString(2,email_del.getValue());
               ps.setString(3,cmp_del.getValue());
               ps.setString(4,date_del.getValue().toString());
               ResultSet rs =ps.executeQuery(); 
             while(rs.next()){
                 f=false;
                 System.out.println("rs not 0");
                 if(Utilities.AlertBox.alertoption("Decline","Enquiry Deletion","Are you sure you want to decline this enquiry?")){
                  String ar[]={"lack of man power","lack of equipments","too many requests","lack of raw materials","not profitable","others"};
                  String res;
                    
                      res= Utilities.AlertBox.alterchoice(ar,"Reason","Any particular reason for declining "
                            + "this enquiry?","Please choose from the drop down menu below.");
                           if(!res.equals("Cancel")){
                           CallableStatement cs;
                           String sql008= "{ call enquiry_del_bkup(?,?,?,?,?)}";
                           cs=com.mycompany.snp.MainApp.conn.prepareCall(sql008);
                         
                           cs.setString(1,eqno_del.getValue());
                           cs.setString(2,email_del.getValue());
                           cs.setDate(3,java.sql.Date.valueOf(date_del.getValue()));
                           cs.setString(4,cmp_del.getValue());
                           cs.setString(5,res);
                         
                           cs.executeQuery();
                           Utilities.AlertBox.notificationInfo("Success","Enquiry "+eqno_del.getValue()+" has been deleted.");
                           }
                           else{
                               break;
                           }
                           
                    
                     }
                 else{
                     break;
                 }
             }
             if(f){
                  Utilities.AlertBox.notificationInfo("Error","Enquiry "+eqno_del.getValue()+" doesn't exist.");
             }

         }  
       }catch(Exception e){
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, e);
                Utilities.AlertBox.showErrorMessage(e);
                Utilities.AlertBox.notificationWarn("Error","Some of the fields seem to be empty"); 
        }
    
}

    @FXML
    private void back_for_delete_of_Enquiry(MouseEvent event) {
          eq_newpane.setEffect(new ColorAdjust());
        eq_newpane.setDisable(false);
        eq_delpane.setVisible(false);
        eq_delpane.setDisable(true); 
        eq_delpane1.setVisible(false);
        eq_delpane1.setDisable(true); 
    }

    @FXML
    private void Invoice_Save_Button_Clicked_inInvPane(MouseEvent event) {
    }

    @FXML
    private void Select_for_quotation_generation(MouseEvent event) {
        //please change codes
         try{
         if(eqno_del1.getValue().toString().isEmpty()|| cmp_del1.getValue().toString().isEmpty() || email_del1.getValue().toString().isEmpty() || date_del1.getValue().toString().isEmpty())
            {
                Utilities.AlertBox.notificationWarn("Error","Some of the fields seem to be empty");
            }
         else {
             String sql="SELECT * FROM `enquiry` e NATURAL JOIN `customer` c WHERE eqno=? "
                        + "and email=? and cmpname=? and `Date`=? ";
               PreparedStatement ps;
                //
                System.out.println("inside else block nice statements really.");
              boolean f =true;
               ps= com.mycompany.snp.MainApp.conn.prepareStatement(sql);
               ps.setString(1,eqno_del1.getValue());
               ps.setString(2,email_del1.getValue());
               ps.setString(3,cmp_del1.getValue());
               ps.setString(4,date_del1.getValue().toString());
               ResultSet rs =ps.executeQuery(); 
             while(rs.next()){
                 f=false;
                 System.out.println("rs not 0");
                     
                           PreparedStatement cs;
                           String sql008= "SELECT IFNULL(MAX(CAST(SUBSTRING(`Qno`,10,3) AS SIGNED))+1,1) as 'your value'  FROM `qoutation` WHERE 1;";
                            //SELECT IFNULL(MAX(CAST(SUBSTRING(`Qno`, CHAR_LENGTH(`Qno`)-2) AS SIGNED))+1,1) as 'your value'  FROM `qoutation` WHERE 1;
                           
                           cs=com.mycompany.snp.MainApp.conn.prepareStatement(sql008);
                            ResultSet rs1=cs.executeQuery();
                             
                           
                         
                          
                           if(rs1.next()){
                              int dig= Integer.valueOf(rs1.getString(1));
                              System.out.println(dig);
                              String compname1=cmp_del1.getValue();
                               String compname=cmp_del1.getValue();
                            if(compname.equalsIgnoreCase("Awin")){
                                compname="AE";
                            }else{
                                compname="SC";
                            }
                            String date= Utilities.Date.Date();
                            date=date.substring(2,5);
                           compname=date+compname;
                           compname=compname+"-QT-";
                           String dg;
                           System.out.println(compname);
                           if(dig<10){
                                    dg=String.valueOf(dig);
                                    dg="00"+dg;
                                   }
                                   else if(dig>=10 && dig<100)
                                   {
                                        dg=String.valueOf(dig);
                                        dg="0"+dg;
                                   }  
                                   else{
                                        dg=String.valueOf(dig);
                                   }
                                  //18-AE or SC - QT - 001
                                  System.out.println(compname);
                                  compname=compname+dg;
                                  Qno.setText(compname);
                                  PreparedStatement stmt;
                                    Utilities.AlertBox.notificationWarn("Quotation Number","Please make sure you that you have noted down the generated quotation number.");

    String suql = "INSERT INTO `qoutation`(`QNo`) VALUES (?)";
                            stmt = com.mycompany.snp.MainApp.conn.prepareStatement(suql);
                            stmt.setString(1,compname);
                            stmt.executeUpdate();

                            String suql1 = "INSERT INTO `eqrel`(`Eno`, `QNo`, `Date1`, `Cmpname`, `CID`) VALUES (?,?,?,?,?)";
                            stmt = com.mycompany.snp.MainApp.conn.prepareStatement(suql1);
                            stmt.setString(1,eno);
                            stmt.setString(2,compname);
                            stmt.setString(3,Edate.getValue().toString());
                            stmt.setString(4,compname1);
                            stmt.setString(5,cid);
                            
                            stmt.executeUpdate();
                           }
                            
                           
                    if(cmp_del1.getValue().equalsIgnoreCase("AWIN"))
                            {
                                System.out.println("AWinnnnnnn");
                                table12.setDisable(true);
                                table12.setVisible(false);
                                table1.setDisable(false);
                                table1.setVisible(true);
                                table1.getItems().clear();
                                table1.getColumns().clear();
                                 ObservableList<Person> data;    
                                    data = FXCollections.observableArrayList();
                                    for(int z=1; z<101;z++){
                                data.add(new Person(String.valueOf(z),"","","",""));
                            }
                                newEnquiryPane_PriceBoxFill_Awin(table1,data);
                                table1.setEffect(new ColorAdjust());
                            }
                            else
                            {
                                  System.out.println("ASteellslsdlsldlsl");
                                table1.setDisable(true);
                                table1.setVisible(false);
                                table12.setDisable(false);
                                table12.setVisible(true);
                                table12.getItems().clear();
                                table12.getColumns().clear();
                                ObservableList<Person2> data;    
                                data = FXCollections.observableArrayList();
                                for(int z=0; z<100;z++){
                                data.add(new Person2("","","","","",""));
                            }

                                newEnquiryPane_PriceBoxFill_Steels(table12,data);
                                table12.setEffect(new ColorAdjust());
                            }
             }
             if(f){
                  Utilities.AlertBox.notificationInfo("Error","Enquiry "+eqno_del1.getValue()+" doesn't exist.");
             }
         }
         
         
         
         
         
        }catch(Exception e){
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, e);
                Utilities.AlertBox.showErrorMessage(e);
                Utilities.AlertBox.notificationWarn("Error","Some of the fields seem to be empty"); 
        }
    
    }

    @FXML
    private void back_for_quotation_generation(MouseEvent event) {
         eq_newpane.setEffect(new ColorAdjust());
        eq_newpane.setDisable(false);
        eq_delpane1.setVisible(false);
        eq_delpane1.setDisable(true); 
    }

    @FXML
    private void tick_in_invoice(MouseEvent event) {
        
              PreparedStatement ps;
               ResultSet rs;
               inv_pno.getItems().clear();
        try {
             
               String sql="SELECT PjNo FROM `product` WHERE 1 ";
               ps= com.mycompany.snp.MainApp.conn.prepareStatement(sql);
               rs=ps.executeQuery();
            while(rs.next()){
               int a= rs.getInt("PNo");
               inv_pno.getItems().add(a);
                
            }
            String sl="SELECT * from product p,pirel pi,invoice id WHERE p.PjNo=pi.PjNo AND pi.INo=id.INo AND p.PjNo=?";
               ps= com.mycompany.snp.MainApp.conn.prepareStatement(sl);
               ps.setInt(1,inv_pno.getValue());
               rs=ps.executeQuery();
               inv_tum.setText(rs.getString("Termofpay"));
               inv_sp.setText(rs.getString("Salesperson"));
               inv_acc.setText(rs.getString("Acc No"));
               inv_to.setText(rs.getString("To:"));//1801-AE-inv-001 //;
               String cn=rs.getString("Company");
               String date=Utilities.Date.Date();
               String dt=date.substring(2,5);
               String d;
               if(inv_pno.getValue()<10){
         //dg=String.valueOf(dig);dg="00"+dg;
        dt="0"+inv_pno;
        }
        else
        dt=dt+inv_pno;
        
        dt=dt+"-";
        if(cn.equalsIgnoreCase("AWIN")){
            dt=dt+"AE";
           
        }else{
            dt=dt+"SE";
            
        }
        dt=dt+"-inv";
          String ssl="SELECT IFNULL(MAX(CAST(SUBSTRING(`INo`, CHAR_LENGTH(`IN`)-2) AS SIGNED))+1,1) as 'your value'  FROM `invoice` WHERE 1";
               ps= com.mycompany.snp.MainApp.conn.prepareStatement(ssl);
               ResultSet ri=ps.executeQuery();
               dt=dt+ri.getInt(1);
               inv_no.setText(dt);
               
    
        }catch (SQLException ex) {
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        inv_newtable.getColumns().clear();
        inv_newtable.getItems().clear();
        ObservableList<Person3> data;    
                data = FXCollections.observableArrayList();
                for(int o=0;o<20;o++){
                    data.add(new Person3("","","","",""));
                }
                newInvoicePane_PriceBoxFill(inv_newtable, data);
                 insideINVPane.setEffect(new ColorAdjust());
       
         
    }

    @FXML
    private void New_Enquiry_Pane_Quotation_gen(MouseEvent event) {
          eq_newpane.setEffect(new GaussianBlur(20));
        eq_newpane.setDisable(true);
        eq_delpane1.setVisible(true);
        eq_delpane1.setDisable(false);
        try{
            eqno_del1.getItems().clear();
            cmp_del1.getItems().clear();
            email_del1.getItems().clear();
          
            String sql="SELECT e.`Eqno` FROM `enquiry` e WHERE e  a order by `Date` DESC ";
            PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                eqno_del1.getItems().add(rs.getString(1));
                //System.out.println(rs.getString(1));
            }
            
                cmp_del1.getItems().add("AWIN");
                cmp_del1.getItems().add("STEEL");
                
             String sql1="SELECT email FROM `customer` WHERE 1 order by `CID` DESC";
             stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql1);
             rs=stmt.executeQuery();
            while(rs.next()){
                email_del1.getItems().add(rs.getString(1));
                
            }
                
            
    } catch(Exception e){
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, e);
                Utilities.AlertBox.showErrorMessage(e);
        }
    }

}
             
    


            
            
            
        
    

