/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.snp;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
    private AnchorPane newEqPane;


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
    
}            
            
            
            
        
    

