/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.snp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author dylan
 */
public class SalesDrawerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void newEnqHit(MouseEvent event) {
        SalesController.SD[0]=true;
    }

    @FXML
    private void oldEnqHit(MouseEvent event) {
        SalesController.SD[1]=true;
    }

    @FXML
    private void QoutHit(MouseEvent event) {
        SalesController.SD[2]=true;
    }

    @FXML
    private void newPOHit(MouseEvent event) {
        SalesController.SD[3]=true;
    }

    @FXML
    private void oldPOHit(MouseEvent event) {
        SalesController.SD[4]=true;
    }

    @FXML
    private void InvoiceHit(MouseEvent event) {
        SalesController.SD[5]=true;
    }
    
}
