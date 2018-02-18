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
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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
    private AnchorPane InvoicePane;

    @FXML
    private AnchorPane newPOPane;

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
 
 void newEnquiryPane_PriceBoxFill_Steels(TableView<Person2> tables){
      SNCol.setSortable(false);
     PosCol.setSortable(false);
     NRCol.setSortable(false);
     BeyondCol.setSortable(false);
     HolidayCol.setSortable(false);
     remarkCol.setSortable(false);
     SNCol.setPrefWidth(50);
        
        
           
       
    tables.getColumns().addAll(SNCol, PosCol, NRCol, BeyondCol ,HolidayCol,remarkCol);
    ObservableList<Person2> data;    
    data = FXCollections.observableArrayList(
    new Person2("", "", "","", "",""),
    new Person2("", "", "","", "",""),
    new Person2("", "", "","", "",""),
    new Person2("", "", "","", "",""),
    new Person2("", "", "","", "",""),
    new Person2("", "", "","", "",""),
    new Person2("", "", "","", "",""),
    new Person2("", "", "","", "",""),
    new Person2("", "", "","", "",""),
    new Person2("", "", "","", "",""),
    new Person2("", "", "","", "",""),
    new Person2("", "", "","", "",""),
    new Person2("", "", "","", "",""),
    new Person2("", "", "","", "",""),
    new Person2("", "", "","", "",""),
    new Person2("", "", "","", "",""),
    new Person2("", "", "","", "",""),
    new Person2("", "", "","", "",""),
    new Person2("", "", "","", "",""),
    new Person2("", "", "","", "",""),
    new Person2("", "", "","", "","")
    );
    
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
                            
                            if(cmpname.equals("Awin"))
                            {
                                table12.setDisable(true);
                                table12.setVisible(false);
                                table1.setDisable(false);
                                table1.setVisible(true);
                                table1.getItems().clear();
                                table1.getColumns().clear();
                                 ObservableList<Person> data;    
                                    data = FXCollections.observableArrayList(
                                    new Person("1", "", "","", ""),
                                    new Person("2", "", "","", ""),
                                    new Person("3", "", "","", ""),
                                    new Person("4", "", "","", ""),
                                    new Person("5", "", "","", ""),
                                    new Person("6", "", "","", ""),
                                    new Person("7", "", "","", ""),
                                    new Person("7", "", "","", ""),
                                    new Person("8", "", "","", ""),
                                    new Person("9", "", "","", ""),
                                    new Person("10", "", "","", ""),
                                    new Person("11", "", "","", ""),
                                    new Person("12", "", "","", ""),
                                    new Person("13", "", "","", ""),
                                    new Person("14", "", "","", ""),
                                    new Person("15", "", "","", ""),
                                    new Person("16", "", "","", ""),
                                    new Person("17", "", "","", ""),
                                    new Person("18", "", "","", ""),
                                    new Person("19", "", "","", ""),
                                    new Person("20", "", "","", "")
                                    );
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
                                newEnquiryPane_PriceBoxFill_Steels(table12);
                                table12.setEffect(new ColorAdjust());
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
          String compname=cmp.getValue();
       if(compname.equalsIgnoreCase("Awin")){
           compname="AE";
       }else{
           compname="SC";
       }
       String date= String.valueOf(Edate.getValue());
       date=date.substring(2,5);
       System.out.println(date);
      compname=date+compname;
      compname=compname+"-QT-";
      
       System.out.println(compname);
    //  String price=pr.getText();
      String descr=EDes.getText();
       Statement st;
     
     try{       
 String suql = "SELECT Qno FROM `qoutation` WHERE 1";
                            st = com.mycompany.snp.MainApp.conn.prepareStatement(suql);
                           ResultSet rs=st.executeQuery(suql);
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
        
      } catch (SQLException exe){
                   Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, exe);
                    Utilities.AlertBox.notificationWarn("Error","Oops something went wrong!");
                    Utilities.AlertBox.showErrorMessage(exe);
      }
    
         PreparedStatement stmt;
        try{
   
     String suql = "INSERT INTO `qoutation`(`QNo`) VALUES (?)";
                            stmt = com.mycompany.snp.MainApp.conn.prepareStatement(suql);
                            stmt.setString(1,compname);
                            stmt.executeUpdate();
      }catch (SQLException exe){
                   Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, exe);
                    Utilities.AlertBox.notificationWarn("Error","Oops something went wrong!");
                    Utilities.AlertBox.showErrorMessage(exe);
      }
    

    
      

      
      try{
      
     String suql1 = "INSERT INTO `eqrel`(`Eno`,`QNo`) VALUES (?,?)";
                            stmt = com.mycompany.snp.MainApp.conn.prepareStatement(suql1);
                            stmt.setString(1,eno);
                            stmt.setString(2,compname);
                            stmt.executeUpdate();
      }
      catch(SQLException exe){
                   Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, exe);
                    Utilities.AlertBox.notificationWarn("Error","Oops something went wrong!");
                    Utilities.AlertBox.showErrorMessage(exe);
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

        if (cmp.getValue()=="Awin"){
       //this is for awin table 
       try{
                        PreparedStatement stmt;

                         ObservableList<Person> trc;
                      trc =FXCollections.observableArrayList(table1.getItems());
                      int i=0;
                      while(i<20){
                      Person p= trc.get(i);
                      if(p.getLastName().getText().equalsIgnoreCase("")){
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
                        String qo=Qno.getText();
                        System.out.println("the quotation no is "+qo);
                        int no=Integer.parseInt(p.getFirstName().getText());
                      try{

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
             }
             
            }
       catch(Exception e){
            Utilities.AlertBox.notificationWarn("Error","Oops something went wrong!");
           Utilities.AlertBox.showErrorMessage(e);
       }
        }
        else
        {
           //for steels table 
            
            
            
        }

        
        
        PreparedStatement stmt;
        
         ObservableList<Person> trc;
      trc =FXCollections.observableArrayList(table1.getItems());
      int i=0;
      while(i<20){
      Person p= trc.get(i);
      if(p.getLastName().getText().equalsIgnoreCase("")){
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
        String qo=Qno.getText();
        System.out.println("the quotation no is "+qo);
        int no=i+1;
     
      
    }
      }
    }

    @FXML
    private void Fill_details_of_existing_Qno_and_Eno(MouseEvent event) {
        //retrieve the details of enquiry using the quotation number.
        try{
            System.out.println("sdfsdfsfddsfsdfsdfsdfsdfs");
            String qno= QnoBox.getValue();
            String sql="SELECT e.Date,e.Eqno,e.Cmpname,e.Subject,c.Name,c.phone,c.email,c.Address  FROM eqrel er join enquiry e on er.eno=e.eqno join customer c on e.cid=c.cid WHERE er.qno= ? ;";
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
                 
            }
            if(ECom1.getText().equals("Awin"))
            {
               //Generate the Awin Quotation Table 
                table111.setDisable(true);
                                    table111.setVisible(false);
                                    table11.setDisable(false);
                                    table11.setVisible(true);
                                    table11.getItems().clear();
                                    table11.getColumns().clear();
                                    //fill details of Awin table
               String sql1="SELECT * FROM `QuotationDetails_Awin` WHERE qno = ?";
                stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql1);
                stmt.setString(1, qno);
                rs=stmt.executeQuery();
                ObservableList<Person> data;    
                data = FXCollections.observableArrayList();
                while(rs.next()){
                    data.add(new Person(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4), rs.getString(5)));
                    System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+"  "+rs.getString(5));
                }                     
                newEnquiryPane_PriceBoxFill_Awin(table11,data);
                

                table11.setEffect(new ColorAdjust());
                
            }
            else{
                if(ECom1.getText().equals("Steels"))
            {
                //Generate the Steels Quotation Table 
                table11.setDisable(true);
                                    table11.setVisible(false);
                                    table111.setDisable(false);
                                    table111.setVisible(true);
                                    table111.getItems().clear();
                                    table111.getColumns().clear();
                newEnquiryPane_PriceBoxFill_Steels(table111);
                table111.getItems().clear();
                String sql1="SELECT * FROM `QuotationDetails_Steels` WHERE qno = ?";
                stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql1);
                stmt.setString(1, qno);
                rs=stmt.executeQuery();
                ObservableList<Person2> data;    
                data = FXCollections.observableArrayList();
                while(rs.next()){
                    data.add(new Person2(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4), rs.getString(5),rs.getString(6)));
                }
                table111.setItems(data);
                table111.setEffect(new ColorAdjust());
                //fill details of Steels Table
            }
            }
        }
        catch(NullPointerException e){
             Utilities.AlertBox.notificationWarn("Error","Please select a quotation number first.");             
        } catch (SQLException ex) {
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Edit_Quotation_in_QuotationPane(MouseEvent event) {
    }

    @FXML
    private void Save_Quotation_in_QuotationPane(MouseEvent event) {
    }

    @FXML
    private void Generate_Quotation_in_QuotationPane(MouseEvent event) {
    }

    @FXML
    private void Revise_Quotation_in_QuotationPane(MouseEvent event) {
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
    

    
    
    
}

            
            
            
        
    

