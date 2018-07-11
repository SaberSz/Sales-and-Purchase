/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.snp;

import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dylan
 */
public class PurchaseController implements Initializable {

    @FXML
    private ScrollPane EnquiryPane;
    @FXML
    private ScrollPane QuotationPane;
    @FXML
    private ScrollPane PurchaseOrderPane;
    @FXML
    private ScrollPane InvoicePaymentsPane1;
    @FXML
    private Label Power;
    @FXML
    private JFXComboBox<String> MainMenu;

    public static String category = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        MainMenu.getItems().add("Enquiry");
        MainMenu.getItems().add("Quotation");
        MainMenu.getItems().add("Purchase Order");
        MainMenu.getItems().add("Invoice Payments");
        

    }

    @FXML
    private void Power_Button_Clicked(MouseEvent event) {
        try {
            Stage stage;
            Parent root;
            stage = (Stage) Power.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Menu_Menu_ComboBox_Hit(MouseEvent event) {
        try {
            category = MainMenu.getValue();
            if (category.equals("Enquiry")) {
                EnquiryPane.setDisable(false);
                EnquiryPane.setVisible(true);
                QuotationPane.setDisable(true);
                PurchaseOrderPane.setDisable(true);
                InvoicePaymentsPane1.setDisable(true);
                QuotationPane.setVisible(false);
                PurchaseOrderPane.setVisible(false);
                InvoicePaymentsPane1.setVisible(false);
            } else if (category.equals("Quotation")) {
                EnquiryPane.setDisable(true);
                EnquiryPane.setVisible(false);
                QuotationPane.setDisable(false);
                PurchaseOrderPane.setDisable(true);
                InvoicePaymentsPane1.setDisable(true);
                QuotationPane.setVisible(true);
                PurchaseOrderPane.setVisible(false);
                InvoicePaymentsPane1.setVisible(false);

            } else if (category.equals("Purchase Order")) {
                EnquiryPane.setDisable(true);
                EnquiryPane.setVisible(false);
                QuotationPane.setDisable(true);
                PurchaseOrderPane.setDisable(false);
                InvoicePaymentsPane1.setDisable(true);
                QuotationPane.setVisible(false);
                PurchaseOrderPane.setVisible(true);
                InvoicePaymentsPane1.setVisible(false);

            } else if (category.equals("Invoice Payments")) {
                EnquiryPane.setDisable(true);
                EnquiryPane.setVisible(false);
                QuotationPane.setDisable(true);
                PurchaseOrderPane.setDisable(true);
                InvoicePaymentsPane1.setDisable(false);
                QuotationPane.setVisible(false);
                PurchaseOrderPane.setVisible(false);
                InvoicePaymentsPane1.setVisible(true);

            }
        } catch (Exception e) {
            Utilities.AlertBox.showErrorMessage(e);
        }
    }

}
