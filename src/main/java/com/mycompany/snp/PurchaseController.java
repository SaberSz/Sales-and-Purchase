package com.mycompany.snp;

import static DBMS.Connect.DB_URL;
import static DBMS.Connect.PASS;
import static DBMS.Connect.USER;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Arrays;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
        initialSetups();
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
        try {
            Type.valueProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue ov, String oldValue, String newValue) {
                    try {
                        if (newValue.equals("Project Related")) {
                            Epjno.setVisible(true);
                            Epjno.setDisable(false);
                            fill_relatedprojno_enquiry();

                        } else if (newValue.equals("Regular")) {
                            Epjno.setVisible(false);
                            Epjno.setDisable(true);
                        }
                    } catch (NullPointerException e) {
                        //ignore
                    }
                }
            });

            Epjno.valueProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue ov, String oldValue, String newValue) {
<<<<<<< HEAD
=======

>>>>>>> cd913140c114785fb7bb4f9b41a15a602363cced
                    try {
                        select_company_fill_combo_box(Integer.parseInt(newValue));
                       
                    } catch (NumberFormatException ne) {

                    }
<<<<<<< HEAD
                    select_company_fill_combo_box(Integer.parseInt(newValue));

=======

                    
>>>>>>> cd913140c114785fb7bb4f9b41a15a602363cced

                }
            });
        } catch (NullPointerException e) {

        }
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
        inv_tick.setDisable(true);
        inv_tick.setVisible(false);
        ENo.setEditable(false);
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
        Runnable task2 = new Runnable() {
            public void run() {

                runQuotation_Threads2();
            }
        };
        Thread backgroundThread2 = new Thread(task2);
        backgroundThread2.setDaemon(true);
        backgroundThread2.start();
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
                    Epjno.setDisable(true);
                    Epjno.setVisible(false);

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
        ENo.setEditable(false);
        Edate.valueProperty().set(null);
        Edate.setEditable(true);
        Type.getItems().clear();
        EDes.clear();
        EDes.setEditable(true);
        CName.clear();
        CName.setEditable(true);
        Epjno.getItems().clear();
        Epjno.setDisable(true);
        Epjno.setVisible(false);
        CPhone.clear();
        CPhone.setEditable(true);
        Cadd.clear();
        Cadd.setEditable(true);
        Cmail.clear();
        Cmail.setEditable(true);                            //enq no format-    yy-cmpname-eq-001
        cmp.getItems().clear();
        edit_in_Enquiry_hit = false;
        EnqSelect.setDisable(true);
        EnqSelect.setVisible(false);
<<<<<<< HEAD
=======

>>>>>>> cd913140c114785fb7bb4f9b41a15a602363cced
        Type.getItems().add("Project Related");
        Type.getItems().add("Regular");
        cmp.getItems().add("Awin");
        cmp.getItems().add("Steel");
<<<<<<< HEAD
        inv_tick.setDisable(true);
        inv_tick.setVisible(false);
=======

        inv_tick.setDisable(true);
        inv_tick.setVisible(false);

>>>>>>> cd913140c114785fb7bb4f9b41a15a602363cced
    }

    public void fill_relatedprojno_enquiry() {
        try {
            Epjno.getItems().clear();
            String sql = "Select PjNo from `product` where 1;";

            PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Epjno.getItems().add(rs.getString("PjNo"));
            }
        } catch (SQLException exe) {
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, exe);
            Utilities.AlertBox.notificationWarn("Error", "Oops something went wrong!");
            Utilities.AlertBox.showErrorMessage(exe);
        }

    }

    void select_company_fill_combo_box(int newValue) {
        try {
            String sql = "Select Qno from `qprel` where PjNo = ?;";

            PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
            stmt.setInt(1, newValue);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            String a = rs.getString(1);
            if (a.contains("AE")) {
                cmp.setValue("Awin");
            } else {
                cmp.setValue("Steel");
            }

        } catch (SQLException exe) {
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, exe);
            Utilities.AlertBox.notificationWarn("Error", "Oops something went wrong!");
            Utilities.AlertBox.showErrorMessage(exe);
        }

    }

    void save_edited_Enquiry() {

        try {
            if (Edate.getValue().toString().isEmpty() || cmp.getValue().isEmpty() || EDes.getText().trim().isEmpty()
                    || CName.getText().trim().isEmpty() || CPhone.getText().trim().isEmpty() || Cmail.getText().trim().isEmpty() || Cadd.getText().trim().isEmpty()) {
                Utilities.AlertBox.notificationWarn("Error", "Some of the fields seem to be empty");
            } else {
                try {
                    if (Utilities.AlertBox.alertoption("Alert!", "Are you Sure?", "Are you sure you wnat to save the entered enquiry details?"
                    )) {
                        Save_Eqnuiry_Edit_Threads();
                    }
                } catch (Exception e) {

                }
            }
        } catch (NullPointerException e) {
            Utilities.AlertBox.notificationWarn("Opps something went worng :(", "Some fields are empty.");
        }

    }

    void Save_Eqnuiry_Edit_Threads() {

        Runnable task1 = new Runnable() {
            public void run() {

                runSave_Eqnuiry_Edit_Threads1();
            }
        };
        Runnable task2 = new Runnable() {
            public void run() {

                runSave_Eqnuiry_Edit_Threads2();
            }
        };

        Thread backgroundThread1 = new Thread(task1);
        backgroundThread1.setDaemon(true);
        backgroundThread1.start();
        Thread backgroundThread2 = new Thread(task2);
        backgroundThread2.setDaemon(true);
        backgroundThread2.start();

    }

    void runSave_Eqnuiry_Edit_Threads1() {

        try {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        String cid;
                        String sql = "SELECT  `SID` FROM `purchase_enquiry` WHERE `Eqno`=?;";
                        PreparedStatement stmt;
                        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        stmt = conn.prepareStatement(sql);
                        stmt.setString(1, ENo.getText().trim());
                        ResultSet rs = stmt.executeQuery();

                        if (rs.next()) {
                            cid = rs.getString(1);
                            sql = "UPDATE `customer` SET `Address`=? ,`Name`="
                                    + " ? ,`email`= ?,`phone`= ? WHERE `CID`=?";
                            stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
                            stmt.setString(2, CName.getText().trim());
                            stmt.setString(3, Cmail.getText().trim());
                            stmt.setString(4, CPhone.getText().trim());
                            stmt.setString(1, Cadd.getText().trim());
                            stmt.setInt(5, Integer.valueOf(cid));
                            stmt.executeUpdate();
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

    void runSave_Eqnuiry_Edit_Threads2() {

        try {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        String sql = "UPDATE `purchase_enquiry` SET `edate`= ?  ,`Subject`= ? ,`Cmpname`= ? ,`Type`=?  WHERE `Eqno`= ? ;";
                        PreparedStatement stmt;
                        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        stmt = conn.prepareStatement(sql);
                        stmt.setString(1, Edate.getValue().toString());
                        stmt.setString(2, EDes.getText().trim());
                        stmt.setString(3, cmp.getValue().toString().trim());
                        stmt.setString(4, Type.getValue().toString().trim());
                        stmt.setString(5, ENo.getText().trim());
                        stmt.executeUpdate();

                        sql = "UPDATE `purchase_eprel` SET `Pjno`= ? WHERE `Eqno`= ? ;";
                        stmt = conn.prepareStatement(sql);
                        stmt.setString(1, Epjno.getValue().trim());
                        stmt.setString(2, ENo.getText().trim());
                        stmt.executeUpdate();

                    } catch (SQLException ex) {
                        Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            System.out.println("2 closed");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void saveNewEnq(MouseEvent event) {
<<<<<<< HEAD
        
        if (edit_in_Enquiry_hit == false) {
    
=======
        System.out.println("Enter saveNewNeq");
        String cid="";
        if (!edit_in_Enquiry_hit) {
            String mx;
>>>>>>> cd913140c114785fb7bb4f9b41a15a602363cced
            //save the new enquiry and generate the enquiry number. Make sure to inform the user about the generated enquiry numbe. make enquiry pane fields uneditable
            //enq no format-    yy-cmpname-eq-001
            //save the new enquiry and generate the enquiry number. Make sure to inform the user about the generated enquiry numbe. make enquiry pane fields uneditable
            // there will be 2 conditions one where you updaet an existing enquiry and another to insert a new enquiry 
<<<<<<< HEAD

        String mx,cid;
        //save the new enquiry and generate the enquiry number. Make sure to inform the user about the generated enquiry numbe. make enquiry pane fields uneditable

        //save the new enquiry and generate the enquiry number. Make sure to inform the user about the generated enquiry numbe. make enquiry pane fields uneditable
        // there will be 2 conditions one where you updaet an existing enquiry and another to insert a new enquiry 
        int[] a = new int[100000];
        try {
            if (Edate.getValue().toString().isEmpty() || cmp.getValue().isEmpty() || EDes.getText().trim().isEmpty()
                    || CName.getText().trim().isEmpty() || CPhone.getText().trim().isEmpty() || Cmail.getText().trim().isEmpty() || Cadd.getText().trim().isEmpty()) {
                Utilities.AlertBox.notificationWarn("Error", "Some of the fields seem to be empty");
            } else {
                try {
                    if (Utilities.AlertBox.alertoption("Alert!", "Are you Sure?", "Are you sure you wnat to save the entered enquiry details?")) {
                        //The below code is used to fetch a CID with the same customer details. This is to see if the customer is already registered or not.

                        String sql = "Select CID from customer where name = ? and email = ? and phone = ? and address = ? ; ";
                        stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
                        stmt.setString(1, CName.getText().trim());
                        stmt.setString(2, Cmail.getText().trim());
                        stmt.setString(3, CPhone.getText().trim());
                        stmt.setString(4, Cadd.getText().trim());
                        ResultSet rs = stmt.executeQuery();
                        if (rs.next()) {
                     cid = rs.getString(1);
                        
                        } else {
                         try{   //The below code is used to add a new customer with a sequential CID number.
                            String sql1 = "{ call insertCustomer(?,?,?,?)}";
                            stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql1);
                            stmt.setString(1, CName.getText().trim());
                            stmt.setString(3, Cmail.getText().trim());
                            stmt.setString(4, CPhone.getText().trim());
                            stmt.setString(2, Cadd.getText().trim());
                            stmt.executeQuery();

                            //The below code is used to fetch the CID of newly added customer.
                            String sql2 = "Select CID from customer where name = ? and email = ? and phone = ? and address = ? ; ";
                            stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql2);
=======
            int[] a = new int[100000];
            try {
                if (Edate.getValue().toString().isEmpty() || cmp.getValue().isEmpty() || EDes.getText().trim().isEmpty()
                        || CName.getText().trim().isEmpty() || CPhone.getText().trim().isEmpty() || Cmail.getText().trim().isEmpty() || Cadd.getText().trim().isEmpty()) {
                    Utilities.AlertBox.notificationWarn("Error", "Some of the fields seem to be empty");
                } else {
                    System.out.println("Enter saveNewNeq non edit");
                    try {
                         System.out.println("Enter saveNewNeq non edit try block");
                        if (Utilities.AlertBox.alertoption("Alert!", "Are you Sure?", "Are you sure you wnat to save the entered enquiry details?")) {
                                    
                            //The below code is used to fetch a CID with the same customer details. This is to see if the customer is already registered or not.

                            String sql = "Select CID from customer where name = ? and email = ? and phone = ? and address = ? ; ";
                            PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
>>>>>>> cd913140c114785fb7bb4f9b41a15a602363cced
                            stmt.setString(1, CName.getText().trim());
                            stmt.setString(2, Cmail.getText().trim());
                            stmt.setString(3, CPhone.getText().trim());
                            stmt.setString(4, Cadd.getText().trim());
<<<<<<< HEAD
                            rs = stmt.executeQuery();

                            if (rs.next()) {
                                cid = rs.getString(1);
                            } else {
                                //The below code is used to add a new customer with a sequential CID number.
                                 sql1 = "{ call insertCustomer(?,?,?,?)}";
                                stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql1);
                                stmt.setString(1, CName.getText().trim());
                                stmt.setString(3, Cmail.getText().trim());
                                stmt.setString(4, CPhone.getText().trim());
                                stmt.setString(2, Cadd.getText().trim());
                                stmt.executeQuery();

                                //The below code is used to fetch the CID of newly added customer.
                                 sql2 = "Select CID from customer where name = ? and email = ? and phone = ? and address = ? ; ";
                                stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql2);
                                stmt.setString(1, CName.getText().trim());
                                stmt.setString(2, Cmail.getText().trim());
                                stmt.setString(3, CPhone.getText().trim());
                                stmt.setString(4, Cadd.getText().trim());
                                rs = stmt.executeQuery();

                                rs.next();
                                cid = rs.getString(1);
                            }

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
                            String mx1;
                            int temp;
                            int[] mxval = new int[100000];
                            try {
                                String suql = "SELECT Eqno FROM `purchase_enquiry` WHERE 1";
                                stmt = com.mycompany.snp.MainApp.conn.prepareStatement(suql);
                                rs = stmt.executeQuery(suql);
                                if (!rs.next()) {
                                    temp = 1;
                                    System.out.println("temp val=" + temp);
=======
                            ResultSet rs = stmt.executeQuery();
                            System.out.println("Enter saveNewNeq after checking if cid available");
                            if (rs.next()) {
                                cid = rs.getString(1);
                            } else {
                                String sql1 = "";
//                               
                                String sql2 = "";
                                    //The below code is used to add a new customer with a sequential CID number.
                                    System.out.println("New Customer");
                                    sql1 = "{ call insertCustomer(?,?,?,?)}";
                                    stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql1);
                                    stmt.setString(1, CName.getText().trim());
                                    stmt.setString(3, Cmail.getText().trim());
                                    stmt.setString(4, CPhone.getText().trim());
                                    stmt.setString(2, Cadd.getText().trim());
                                    stmt.executeQuery();

                                    //The below code is used to fetch the CID of newly added customer.
                                    sql2 = "Select CID from customer where name = ? and email = ? and phone = ? and address = ? ; ";
                                    stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql2);
                                    stmt.setString(1, CName.getText().trim());
                                    stmt.setString(2, Cmail.getText().trim());
                                    stmt.setString(3, CPhone.getText().trim());
                                    stmt.setString(4, Cadd.getText().trim());
                                    rs = stmt.executeQuery();

                                    rs.next();
                                    cid = rs.getString(1);
                                }
                                System.out.println("cid value is "+cid);
                                String res = "";
                                String date = Utilities.Date.Date();
                                date = date.substring(2, 4);
                                System.out.println(date);
                                res += date;
                                if (cmp.getValue().equalsIgnoreCase("Awin")) {
                                    res += "-AE-EQ-";
>>>>>>> cd913140c114785fb7bb4f9b41a15a602363cced
                                } else {
                                    res += "-SC-EQ-";
                                }
                                String mx1;
                                int temp;
                                ArrayList<Integer> mxval = new ArrayList();
                                //int[] mxval = new int[100000];
                                try {
                                    String suql = "SELECT Eqno FROM `purchase_enquiry` WHERE `Cmpname` = '" + cmp.getValue().trim() + "' ;";
                                    stmt = com.mycompany.snp.MainApp.conn.prepareStatement(suql);
                                    //stmt.setString(1,cmp.getValue().trim());
                                    rs = stmt.executeQuery(suql);

                                    int i = 0, j = 0;
                                    while (rs.next()) {

                                        mx = rs.getString(1);

                                        mx1 = mx.substring(9, 12);//18-AE-EQ-001
                                        System.out.println("mx1 val:" + mx1);
                                        mxval.add(Integer.parseInt(mx1));
                                        i++;
                                    }
                                    if (i == 0) {

                                        temp = 1;
                                        System.out.println("temp val=" + temp);

                                    }
                                    temp = Collections.max(mxval);

                                    System.out.println("temp val after db fetch before inc" + temp);
                                    temp++;

                                    System.out.println("temp val after inc=" + temp);
                                    String te = "";
                                    if (temp < 10) {
                                        te += "00";
                                    } else if (temp >= 10 && temp < 100) {
                                        te = "0";
                                    } else {
                                        te = "";
                                    }
                                    te += String.valueOf(temp);
                                    System.out.println("res out=" + res);
                                    res += te;
                                    ENo.setText(res);
                                    String sql3 = "INSERT INTO `purchase_enquiry`(`Eqno`, `edate`, `SID`, `Subject`, `Cmpname`,`Type`) VALUES (?,?,?,?,?,?)";
                                    stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql3);
                                    stmt.setString(1, res);
                                    stmt.setString(2, Edate.getValue().toString());
                                    stmt.setString(3, cid);
                                    stmt.setString(4, EDes.getText().trim());
                                    stmt.setString(5, cmp.getValue());
                                    stmt.setString(6, Type.getValue());
                                    stmt.executeUpdate();
                                    System.out.println(res + " entered into Purchase_Enquiry");
                                    String sql4;
                                    if (Type.getValue() == "Project Related") {
                                        try {
                                            sql4 = "INSERT INTO `purchase_eprel`(`Eqno`,`PjNo`) VALUES (?,?)";
                                            try {
                                                stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql4);
                                                stmt.setString(1, res);
                                                stmt.setString(2, Epjno.getValue().trim());
                                                stmt.executeUpdate();
                                                System.out.println(res + " entered into Purchase_eqrel");
                                            } catch (SQLException ex) {
                                                Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
                                            }

                                        } catch (NullPointerException e) {

                                        }
                                    }
                                    Utilities.AlertBox.notificationInfo("Success", "Enquiry details saved. Your Enquiry number is :" + res);

                                } catch (SQLException exe) {
                                    Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, exe);
                                    Utilities.AlertBox.notificationWarn("Error", "Oops something went wrong!");
                                    Utilities.AlertBox.showErrorMessage(exe);
                                }
                            }
<<<<<<< HEAD
                        catch (SQLException exe) {
                                Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, exe);
                                Utilities.AlertBox.notificationWarn("Error", "Oops something went wrong!");
                                Utilities.AlertBox.showErrorMessage(exe);
                            }
                        }
                      catch (SQLException ex) {
=======
                        
                
                    } catch (SQLException ex) {
>>>>>>> cd913140c114785fb7bb4f9b41a15a602363cced
                        Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
                        Utilities.AlertBox.notificationWarn("Error", "Please make sure you have entered all the details correctly.");
                        Utilities.AlertBox.showErrorMessage(ex);
                    }
<<<<<<< HEAD
            }
        }
                }catch (NullPointerException e) {
                Utilities.AlertBox.notificationWarn("Error", "Some of the fields seem to be empty");
            }

    }


    
    


    }catch (NullPointerException e) {
            Utilities.AlertBox.notificationWarn("Error", "Some of the fields seem to be empty");
        }
        }else {
=======
                }
            } catch (NullPointerException e) {
                Utilities.AlertBox.notificationWarn("Error", "Some of the fields seem to be empty");
            }

        } else {
>>>>>>> cd913140c114785fb7bb4f9b41a15a602363cced
            //run_enquiry
            if (!ENo.getText().isEmpty()) {
                save_edited_Enquiry();
                Utilities.AlertBox.notificationInfo("Success", "Your chnages have been recorded to Enquiry number " + ENo.getText());
            }
        }
    }

<<<<<<< HEAD
   
=======
    @FXML
>>>>>>> cd913140c114785fb7bb4f9b41a15a602363cced
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
                sql = "Delete FROM `purchase_eprel` WHERE Eqno=?;";
                stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
                stmt.setString(1, entered);
                stmt.executeUpdate();
                if (no != 0) {
                    Utilities.AlertBox.notificationInfo("Success", "Enquiry " + entered + " deleted.");
                } else {
                    Utilities.AlertBox.notificationWarn("Opps something went wrong :(", "Enquiry " + entered + " not found.");
                }

            } catch (SQLException ex) {
                Utilities.AlertBox.showErrorMessage(ex);
                Logger
                        .getLogger(PurchaseController.class
                                .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    static boolean edit_in_Enquiry_hit = false;

    @FXML
    private void Enq_edit_hit(MouseEvent event) {
        //show combo box for selection of the enquiry number
        edit_in_Enquiry_hit = true;
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
                        EnqSelect.getItems().clear();
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
            String sql = "SELECT `Eqno`, `edate`,`Subject`, `Cmpname`,`Type`,`SID`  FROM `purchase_enquiry` WHERE `Eqno`=? ";
            String cid = "";
            ResultSet rs;
            try {
                PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
                stmt.setString(1, eqno);
                rs = stmt.executeQuery();
                ENo.setEditable(false);
                Edate.setEditable(true);
                Type.setEditable(false);
                EDes.setEditable(true);
                CName.setEditable(true);
                CPhone.setEditable(true);
                Cmail.setEditable(true);
                Cadd.setEditable(true);
                cmp.setEditable(false);
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
                sql="SELECT  `Pjno` FROM `purchase_eprel` WHERE `Eqno` = ?";
                stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
                stmt.setString(1, eqno);
                rs = stmt.executeQuery();
                boolean flag=false;
                {
                    while(rs.next()){
                        flag=true;
                        Epjno.setValue(rs.getString(1));
                        Epjno.setDisable(!flag);
                        Epjno.setVisible(flag);
                        
                    }
                    if(!flag){
                        Epjno.setDisable(!flag);
                        Epjno.setVisible(flag);
                    }
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
        String eqno=EnqSelect1.getValue();
        
        
    }

    @FXML
    private void SaveNewPO(MouseEvent event) {
    }

    @FXML
    private void Gen_PO(MouseEvent event) {
    }

}
