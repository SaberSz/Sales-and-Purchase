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
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
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
    private AnchorPane oldEqPane;

    @FXML
    private AnchorPane QoutPane;

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


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SalesDraw.setDisable(false);
        SalesDraw.setVisible(true);
        SalesDraw.toBack();
        
        oldEqPane.setDisable(true);
        oldEqPane.setVisible(false);
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
                                oldEqPane.setDisable(true);
                                oldEqPane.setVisible(false);
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
                      else if(SD[1]){
                          oldEqPane.setDisable(false);
                                oldEqPane.setVisible(true);
                                QoutPane.setDisable(true);
                                QoutPane.setVisible(false);
                                oldPOPane.setDisable(true);
                                oldPOPane.setVisible(false);
                                InvoicePane.setDisable(true);
                                InvoicePane.setVisible(false);
                                newPOPane.setDisable(true);
                                newPOPane.setVisible(false);
                                newEqPane.setDisable(true);
                                newEqPane.setVisible(false); 
                          SD[1]=false;
                          }
                        else if(SD[2])
                        {
                            oldEqPane.setDisable(true);
                                oldEqPane.setVisible(false);
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
                            oldEqPane.setDisable(true);
                                oldEqPane.setVisible(false);
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
                            oldEqPane.setDisable(true);
                                oldEqPane.setVisible(false);
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
                            oldEqPane.setDisable(true);
                                oldEqPane.setVisible(false);
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

 static String cid; //for Customer Id
 static String eno; //for enquiry number

    @FXML
    private void saveNewEnq(MouseEvent event) {
        //store new enquiry data entered into the database
      
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

                        }
                        Utilities.AlertBox.notificationInfo("Success","New enquiry details saved");
                    
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
    
}            
            
            
            
        
    

