package com.mycompany.snp;

import static DBMS.Connect.DB_URL;
import static DBMS.Connect.PASS;
import static DBMS.Connect.USER;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import static com.mycompany.snp.SalesController.cid;
import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PurchaseController implements Initializable {

    @FXML
    private Label Power;
    @FXML
    private JFXComboBox<String> MainMenu;
    @FXML
    private ScrollPane EnquiryPane;
    @FXML
    private JFXTextField ENo;
    @FXML
    private JFXDatePicker Edate;
    @FXML
    private JFXComboBox<String> Type;
    @FXML
    private JFXTextArea EDes;
    @FXML
    private JFXComboBox<String> cmp;
    @FXML
    private JFXComboBox<String> Epjno;
    @FXML
    private JFXTextField CName;
    @FXML
    private JFXTextField CPhone;
    @FXML
    private JFXTextField Cmail;
    @FXML
    private JFXTextArea Cadd;
    @FXML
    private Label pencilinv;
    @FXML
    private JFXComboBox<String> EnqSelect;
    @FXML
    private Label inv_tick;
    @FXML
    private ScrollPane QuotationPane;
    @FXML
    private JFXComboBox<String> EnqSelect1;
    @FXML
    private Label inv_tick1;
    @FXML
    private JFXTreeTableView<?> Table1;
    @FXML
    private ScrollPane PurchaseOrderPane;
    @FXML
    private ScrollPane InvoicePaymentsPane1;
    @FXML
    private JFXComboBox<?> POqno;
    @FXML
    private Label Potick;
    @FXML
    private JFXTextArea supplierInfo;
    @FXML
    private JFXTextField Pjnumber;
    @FXML
    private JFXTextField ShipmentNumber;
    @FXML
    private JFXTextField ShipmentTerm;
    @FXML
    private JFXDatePicker OrderDate;
    @FXML
    private JFXTextField Attn;
    @FXML
    private TableView<?> Table2;
    @FXML
    private JFXTextArea Header;
    @FXML
    private JFXTextField PoTotal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        MainMenu.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String oldValue, String newValue) {

                if (newValue.equals("Enquiry")) {
                    ShowEnquiry();
                } else if (newValue.equals("Quotation")) {
                    ShowQuotation();

                } else if (newValue.equals("Purchase Order")) {
                    ShowPurchaseOrder();
                } else if (newValue.equals("Invoice Payments")) {
                    ShowInvoice();

                }
            }
        });
        initialSetups();
    }

    void ShowEnquiry() {
        EnquiryPane.setDisable(false);
        EnquiryPane.setVisible(true);
        QuotationPane.setDisable(true);
        PurchaseOrderPane.setDisable(true);
        InvoicePaymentsPane1.setDisable(true);
        QuotationPane.setVisible(false);
        PurchaseOrderPane.setVisible(false);
        InvoicePaymentsPane1.setVisible(false);
        EnqSelect.setDisable(true);
        EnqSelect.setVisible(false);
    }

    void ShowQuotation() {
        EnquiryPane.setDisable(true);
        EnquiryPane.setVisible(false);
        QuotationPane.setDisable(false);
        PurchaseOrderPane.setDisable(true);
        InvoicePaymentsPane1.setDisable(true);
        QuotationPane.setVisible(true);
        PurchaseOrderPane.setVisible(false);
        InvoicePaymentsPane1.setVisible(false);
    }

    void ShowPurchaseOrder() {
        EnquiryPane.setDisable(true);
        EnquiryPane.setVisible(false);
        QuotationPane.setDisable(true);
        PurchaseOrderPane.setDisable(false);
        InvoicePaymentsPane1.setDisable(true);
        QuotationPane.setVisible(false);
        PurchaseOrderPane.setVisible(true);
        InvoicePaymentsPane1.setVisible(false);
    }

    void ShowInvoice() {
        EnquiryPane.setDisable(true);
        EnquiryPane.setVisible(false);
        QuotationPane.setDisable(true);
        PurchaseOrderPane.setDisable(true);
        InvoicePaymentsPane1.setDisable(false);
        QuotationPane.setVisible(false);
        PurchaseOrderPane.setVisible(false);
        InvoicePaymentsPane1.setVisible(true);
    }

    void initialSetups() {

        Runnable task = new Runnable() {
            public void run() {

                runInitialSetUp1();
            }
        };

        Thread backgroundThread1 = new Thread(task);
        backgroundThread1.setDaemon(true);
        backgroundThread1.start();

    }

    void runInitialSetUp1() {

        try {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    MainMenu.getItems().add("Enquiry");
                    MainMenu.getItems().add("Quotation");
                    MainMenu.getItems().add("Purchase Order");
                    MainMenu.getItems().add("Invoice Payments");
                    MainMenu.setValue("Enquiry");
                    Type.getItems().add("Project Related");
                    Type.getItems().add("Regular");
                    cmp.getItems().add("Awin");
                    cmp.getItems().add("Steel");
                }
            });

            System.out.println("1 closed");
        } catch (Exception e) {
            e.printStackTrace();
        }

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
    private void New_Enquiry_Pane_Clear_Components(MouseEvent event) {
        //clear content in feilds of enquiry pane
        ENo.clear();
        ENo.setEditable(true);
        Edate.valueProperty().set(null);
        Edate.setEditable(true);
        Type.getItems().clear();
        EDes.clear();
        EDes.setEditable(true);
        CName.clear();
        CName.setEditable(true);
        Epjno.getItems().clear();
        CPhone.clear();
        CPhone.setEditable(true);
        Cadd.clear();
        Cadd.setEditable(true);
        Cmail.clear();
        Cmail.setEditable(true);                            //enq no format-    yy-cmpname-eq-001
        cmp.getItems().clear();
        edit_in_Enquiry_hit=false;
        EnqSelect.setDisable(true);
        EnqSelect.setVisible(false);
    }

    @FXML
    private void saveNewEnq(MouseEvent event) {

        /*    //save the new enquiry and generate the enquiry number. Make sure to inform the user about the generated enquiry numbe. make enquiry pane fields uneditable
        // there will be 2 conditions one where you updaet an existing enquiry and another to insert a new enquiry 
        int[] a=new int[100000];
        try {
            if (ENo.getText().trim().equals("") || EDes.getText().trim().equals("") || cmp.getValue().trim().equals("") || Cmail.getText().equals("") || Cadd.getText().equals("") || Edate.getValue().equals("") || Epjno.getValue().trim().equals("") || CName.getText().trim().equals("")
                    || CPhone.getText().trim().equals("")||Type.getValue().trim().equals("")) {
                Utilities.AlertBox.notificationWarn("Error", "Some of the fields seem to be empty");
            } else {
            try {
                    if (Utilities.AlertBox.alertoption("Alert!", "Are you Sure?", "Are you sure you wnat to save the entered enquiry details?"
                            + "Please note these details are not editable")) {
                        //The below code is used to fetch a CID with the same customer details. This is to see if the customer is already registered or not.

                        String sql = "Select CID from customer where name = ? and email = ? and phone = ? and address = ? ; ";
                        PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
                        stmt.setString(1, CName.getText().trim());
                        stmt.setString(2, Cmail.getText().trim());
                        stmt.setString(3, CPhone.getText().trim());
                        stmt.setString(4, Cadd.getText().trim());
                        ResultSet rs = stmt.executeQuery();

                        if (rs.next()) {
                            cid = rs.getString(1);
                        } else {    
                
                
            
                String res = "";
                String date = Utilities.Date.Date();
                date = date.substring(2, 4);
                System.out.println(date);
                res += date;
                if (cmp.getValue().equalsIgnoreCase("Awins")) {
                    res += "-AE-EQ-";
                } else {
                    res += "-SC-EQ-";
                }
                     
            try {
                                String suql = "SELECT Eqno FROM `enquiry` WHERE 1";
                                st = com.mycompany.snp.MainApp.conn.prepareStatement(suql);
                                rs = st.executeQuery(suql);
                                int i = 0, j = 0;
                                while (rs.next()) {

                                    mx = rs.getString("Qno");

                                    mx1 = mx.substring(10, 12);
                                    System.out.println("mx1 val:" + mx1);
                                    mxval[i] = Integer.parseInt(mx1);
                                    i++;
                                }
              int temp;
              
            } catch (SQLException exe) {
                                Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, exe);
                                Utilities.AlertBox.notificationWarn("Error", "Oops something went wrong!");
                                Utilities.AlertBox.showErrorMessage(exe);
                            }

         catch (NullPointerException e) {
            Utilities.AlertBox.notificationWarn("Error", "Some of the fields seem to be empty");
        }
    }*/
    }

    @FXML
    private void delNewEnq(MouseEvent event) {
        // delete a specific enquiry using input from alter box. ask the user to enter the enquiry number
        String entered = Utilities.AlertBox.alterinput("", "Delete Enquiry", "Enter the Enquiry number of the enquiry to be deleted", "Enquiry Number");
        if (entered.equals("Cancel")) {

        } else {
            try {

                String sql = "Delete FROM `purchase_enquiry` WHERE Eqno=?;";
                PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
                stmt.setString(1, entered);
                int no = stmt.executeUpdate();
                if (no != 0) {
                    Utilities.AlertBox.notificationInfo("Success", "Enquiry " + entered + " deleted.");
                } else {
                    Utilities.AlertBox.notificationWarn("Opps something went wrong :(", "Enquiry " + entered + " not found.");
                }

            } catch (SQLException ex) {
                Utilities.AlertBox.showErrorMessage(ex);
                Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    static boolean edit_in_Enquiry_hit=false;
    @FXML
    private void Enq_edit_hit(MouseEvent event) {
        //show combo box for selection of the enquiry number
        edit_in_Enquiry_hit=true;
        Eqnuiry_Edit_Threads();
    }

    void Eqnuiry_Edit_Threads() {

        Runnable task1 = new Runnable() {
            public void run() {

                runEqnuiry_Edit_Threads1();
            }
        };
        Runnable task2 = new Runnable() {
            public void run() {

                runEqnuiry_Edit_Threads2();
            }
        };

        Thread backgroundThread1 = new Thread(task1);
        backgroundThread1.setDaemon(true);
        backgroundThread1.start();
        Thread backgroundThread2 = new Thread(task2);
        backgroundThread2.setDaemon(true);
        backgroundThread2.start();

    }

    void runEqnuiry_Edit_Threads1() {

        try {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    EnqSelect.setDisable(false);
                    EnqSelect.setVisible(true);
                    inv_tick.setDisable(false);
                    inv_tick.setVisible(true);
                    ENo.setEditable(false);
                    Edate.setEditable(false);
                    Type.setEditable(false);
                    EDes.setEditable(false);
                    CName.setEditable(false);
                    Epjno.setEditable(false);
                    CPhone.setEditable(false);
                    Cmail.setEditable(false);
                    Cadd.setEditable(false);
                    cmp.setEditable(false);
                }
            });

            System.out.println("1 closed");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void runEqnuiry_Edit_Threads2() {
        try {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        String sql = "SELECT `Eqno`  FROM `purchase_enquiry` WHERE 1 ORDER BY `edate` DESC;";
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        ResultSet rs = stmt.executeQuery();
                        while (rs.next()) {
                            EnqSelect.getItems().add(rs.getString(1));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            System.out.println("1 closed");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void Selection_of_Enquiry_for_edit(MouseEvent event) {
        //retireve data if available from db
        try {
            String eqno = EnqSelect.getValue();
            String sql = "SELECT `Eqno`, `edate`,`Subject`, `Cmpname`,`Type`,`SID` FROM `purchase_enquiry` WHERE `Eqno`=? ";
            String cid = "";
            ResultSet rs;
            try {
                PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
                stmt.setString(1, eqno);
                rs = stmt.executeQuery();
                ENo.setEditable(false);
                Edate.setEditable(true);
                Type.setEditable(true);
                EDes.setEditable(true);
                CName.setEditable(true);
                Epjno.setEditable(true);
                CPhone.setEditable(true);
                Cmail.setEditable(true);
                Cadd.setEditable(true);
                cmp.setEditable(true);
                while (rs.next()) {
                    Edate.setValue(LocalDate.parse(rs.getString(2)));
                    ENo.setText(rs.getString(1));
                    EDes.setText(rs.getString(3));
                    cmp.setValue(rs.getString(4));
                    Type.setValue(rs.getString(5));
                    cid = rs.getString(6);
                }
                sql = "SELECT  `Address`, `Name`, `email`, `phone` FROM `customer` WHERE `CID`=?";
                stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
                stmt.setString(1, cid);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    CName.setText(rs.getString(2));
                    CPhone.setText(rs.getString(4));
                    Cmail.setText(rs.getString(3));
                    Cadd.setText(rs.getString(1));
                }

            } catch (SQLException ex) {
                Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
                Utilities.AlertBox.showErrorMessage(ex);
            }

        } catch (NullPointerException e) {
            Utilities.AlertBox.notificationWarn("Opps something went wrong :(", "Please select an enquiry from the Dropdown Box.");
        }

    }

    @FXML
    void Attach_Quotation_Button_Clicked(MouseEvent event) {

    }

    @FXML
    void Selection_of_Enquiry_for_Quotation_Entry(MouseEvent event) {

    }

    @FXML
    void saveNewQuotation(MouseEvent event) {

    }

    @FXML
    private void Selection_of_Quotation_for_PO_Entry(MouseEvent event) {
    }

    @FXML
    private void SaveNewPO(MouseEvent event) {
    }

    @FXML
    private void Gen_PO(MouseEvent event) {
    }

}
