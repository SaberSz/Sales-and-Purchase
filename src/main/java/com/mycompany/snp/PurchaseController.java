package com.mycompany.snp;

import static DBMS.Connect.DB_URL;
import static DBMS.Connect.PASS;
import static DBMS.Connect.USER;
import Utilities.Date;
import static Utilities.NumUtil.round;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;

import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import java.io.File;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import java.util.Arrays;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.swing.JFileChooser;

public class PurchaseController implements Initializable {

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
    private JFXTreeTableView<AnalysisDT1> Table1;
    @FXML
    private ScrollPane PurchaseOrderPane;
    @FXML
    private ScrollPane InvoicePaymentsPane1;
    @FXML
    private JFXComboBox<String> POqno;
    @FXML
    private Label Potick;
    @FXML
    private JFXTextArea supplierInfo;
    @FXML
    private JFXTextField Pjnumber;
    @FXML
    private TableView<Person4> Table2;
    @FXML
    private JFXTextArea Header;
    @FXML
    private JFXTextField PoTotal;
    @FXML
    private JFXTextField QNo;
    @FXML
    private JFXDatePicker Date_Qno;
    @FXML
    private JFXTextField Location_QNo;
    @FXML
    private JFXTextField POnumber;
    @FXML
    private JFXTextField paymentTerms;
    @FXML
    private JFXDatePicker OrderDate1;
    @FXML
    private JFXTextField PoTotal1;
    @FXML
    private JFXTextField PoTotal11;
    @FXML
    private JFXTextField GSTRate;

    static String comp_inv_gst = "0";
    @FXML
    private JFXComboBox<String> PO_Select;
    @FXML
    private Label tickPO2;
    @FXML
    private Label editPO;
    @FXML
    private Label plusPO;
    @FXML
    private JFXComboBox<String> PO_inv;
    @FXML
    private Label POtick_inv;
    @FXML
    private JFXTextField Inv_no;
    @FXML
    private JFXTextField inv_loc;
    @FXML
    private JFXTextField inv_amt;
    @FXML
    private JFXTextField inv_amt1;
    @FXML
    private JFXTreeTableView<AnalysisDT2> table3;

    @FXML
    private JFXTextField filter_inv;

    @FXML
    private Label Money_Paid;

    @FXML
    private Label Inv_pen;

    @FXML
    private Label inv_plus;
    @FXML
    private JFXToggleButton TogglePaid;
    @FXML
    private JFXDatePicker inv_date_recv;
    @FXML
    private JFXDatePicker inv_date_due;
    @FXML
    private Label pdfGen;
    @FXML
    private Label inv_del;
    @FXML
    private Label miniButton;

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

                    try {
                        select_company_fill_combo_box(Integer.parseInt(newValue));

                    } catch (NumberFormatException ne) {

                    }

                }
            });
            PoTotal.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                try {
                    if (!newValue.matches("^\\d*\\.?\\d+|\\d+\\.?\\d*$")) {
                        PoTotal.setText(newValue.replaceAll("[^\\d.]", ""));
                    } else {
                        if (PoTotal.getText().isEmpty()) {
                            PoTotal1.setText("0");
                            PoTotal11.setText("0");

                        } else {
                            PoTotal1.setText(String.valueOf(Math.round((Double.valueOf(PoTotal.getText()) * (Float.valueOf(comp_inv_gst) / 100)) * 100d) / 100d));//Math.round(value * 100000d) / 100000d
                            PoTotal11.setText(String.valueOf(Float.valueOf(PoTotal.getText()) + Float.valueOf(PoTotal1.getText())));
                        }
                    }
                } catch (Exception e) {

                }

            });

            GSTRate.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                try {
                    if (!newValue.matches("^\\d*\\.?\\d+|\\d+\\.?\\d*$")) {
                        GSTRate.setText(newValue.replaceAll("[^\\d.]", ""));
                        comp_inv_gst = GSTRate.getText();
                        System.out.println("comp_inv_gst : " + comp_inv_gst);
                    } else if (newValue.matches("^\\d*\\.?\\d+|\\d+\\.?\\d*$")) {
                        comp_inv_gst = GSTRate.getText();
                        System.out.println("comp_inv_gst" + comp_inv_gst);
                    }

                } catch (Exception e) {

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

                runQuotation_Threads();
            }
        };

        Thread backgroundThread1 = new Thread(task2);
        backgroundThread1.setDaemon(true);
        backgroundThread1.start();

    }

    void runQuotation_Threads() {
        try {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        String sql = "SELECT `Eqno`  FROM `purchase_enquiry` WHERE 1 ORDER BY `edate` DESC;";
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        ResultSet rs = stmt.executeQuery();
                        EnqSelect1.getItems().clear();
                        while (rs.next()) {
                            EnqSelect1.getItems().add(rs.getString(1));

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

    void show_poquot_threads() {
        try {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        String sql = "Select q.Qno, c.Name from `purchase_quotation` q, `purchase_enquiry` e, `customer` c where q.Eqno=e.Eqno and e.SID=c.CID";
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        ResultSet rs = stmt.executeQuery();
                        POqno.getItems().clear();
                        while (rs.next()) {
                            POqno.getItems().add(rs.getString(1) + " : " + rs.getString(2));

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);

                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
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
        Runnable task2 = new Runnable() {
            public void run() {
                show_poquot_threads();

            }
        };

        Thread backgroundThread3 = new Thread(task2);
        backgroundThread3.setDaemon(true);
        backgroundThread3.start();
        PO_Select.setDisable(true);
        PO_Select.setVisible(false);
        tickPO2.setDisable(true);
        tickPO2.setVisible(false);
        plusPO.setDisable(true);
        plusPO.setVisible(false);
        Table2.getColumns().clear();
        POqno.setDisable(false);
        POqno.setVisible(true);
        Potick.setDisable(false);
        Potick.setVisible(true);
        editPO.setDisable(false);
        editPO.setVisible(true);
        edit_bttn = false;
        pdfGen.setDisable(true);
        pdfGen.setVisible(false);

        //PO_Tabel_Generation();
    }

    void ShowInvoice() {
        Runnable task3 = new Runnable() {
            public void run() {
                show_showpos_threads();

            }
        };

        Thread backgroundThread3 = new Thread(task3);
        backgroundThread3.setDaemon(true);
        backgroundThread3.start();
        EnquiryPane.setDisable(true);
        EnquiryPane.setVisible(false);
        QuotationPane.setDisable(true);
        PurchaseOrderPane.setDisable(true);
        InvoicePaymentsPane1.setDisable(false);
        QuotationPane.setVisible(false);
        PurchaseOrderPane.setVisible(false);
        InvoicePaymentsPane1.setVisible(true);
        Money_Paid.setVisible(false);
        Money_Paid.setDisable(true);
        Inv_pen.setVisible(true);
        Inv_pen.setDisable(false);
        inv_plus.setVisible(false);
        inv_plus.setDisable(true);
        inv_del.setVisible(false);
        inv_del.setDisable(true);
        edit_btn_hit = false;
        table3.getColumns().clear();
    }

    void show_showpos_threads() {
        try {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        String sql = "SELECT Po_NO FROM `purchase_po` WHERE 1";
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        ResultSet rs = stmt.executeQuery();
                        PO_inv.getItems().clear();
                        while (rs.next()) {
                            PO_inv.getItems().add(rs.getString(1));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);

                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
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
    private double xOffset = 0;
    private double yOffset = 0;
    private void Power_Button_Clicked(MouseEvent event) {
        try {
            Stage stage;
            Parent root;
            stage = (Stage) Power.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
             BorderPane root1 = new BorderPane(root);
            root1.setOnMousePressed((MouseEvent event1) -> {
                xOffset = event1.getSceneX();
                yOffset = event1.getSceneY();
            });
            root1.setOnMouseDragged((MouseEvent event1) -> {
                stage.setX(event1.getScreenX() - xOffset);
                stage.setY(event1.getScreenY() - yOffset);
            });
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

        Type.getItems().add("Project Related");
        Type.getItems().add("Regular");
        cmp.getItems().add("Awin");
        cmp.getItems().add("Steel");

        inv_tick.setDisable(true);
        inv_tick.setVisible(false);

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
        System.out.println("Enter saveNewNeq");
        String cid = "";
        if (!edit_in_Enquiry_hit) {
            String mx;
            //save the new enquiry and generate the enquiry number. Make sure to inform the user about the generated enquiry numbe. make enquiry pane fields uneditable
            //enq no format-    yy-cmpname-eq-001
            //save the new enquiry and generate the enquiry number. Make sure to inform the user about the generated enquiry numbe. make enquiry pane fields uneditable
            // there will be 2 conditions one where you updaet an existing enquiry and another to insert a new enquiry
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
                            stmt.setString(1, CName.getText().trim());
                            stmt.setString(2, Cmail.getText().trim());
                            stmt.setString(3, CPhone.getText().trim());
                            stmt.setString(4, Cadd.getText().trim());
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
                            System.out.println("cid value is " + cid);
                            String res = "";
                            String date = Utilities.Date.Date();
                            date = date.substring(2, 4);
                            System.out.println(date);
                            res += date;
                            if (cmp.getValue().equalsIgnoreCase("Awin")) {
                                res += "-AE-EQ-";
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

                                } else {
                                    temp = Collections.max(mxval);
                                }

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

                    } catch (SQLException ex) {
                        Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
                        Utilities.AlertBox.notificationWarn("Error", "Please make sure you have entered all the details correctly.");
                        Utilities.AlertBox.showErrorMessage(ex);
                    }
                }
            } catch (NullPointerException e) {
                Utilities.AlertBox.notificationWarn("Error", "Some of the fields seem to be empty");
            }

        } else {
            //run_enquiry
            if (!ENo.getText().isEmpty()) {
                save_edited_Enquiry();
                Utilities.AlertBox.notificationInfo("Success", "Your chnages have been recorded to Enquiry number " + ENo.getText());
            }
        }
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
                sql = "SELECT  `Pjno` FROM `purchase_eprel` WHERE `Eqno` = ?";
                stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
                stmt.setString(1, eqno);
                rs = stmt.executeQuery();
                boolean flag = false;
                {
                    while (rs.next()) {
                        flag = true;
                        Epjno.setValue(rs.getString(1));
                        Epjno.setDisable(!flag);
                        Epjno.setVisible(flag);

                    }
                    if (!flag) {
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
    static String f = "Unknown";

    @FXML
    void Attach_Quotation_Button_Clicked(MouseEvent event) {

        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new ExtensionFilter("PDF Files", "*.pdf"));
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
            f = selectedFile.getAbsolutePath();
            Location_QNo.setText(f);
        }

    }

    @FXML
    void Selection_of_Enquiry_for_Quotation_Entry(MouseEvent event) {
        try {
            if (!EnqSelect1.getValue().isEmpty()) {
                String eqno = EnqSelect1.getValue();
                Location_QNo.setText("Unknown");
                Gen_Table_in_QuotationPane(eqno);
            }
        } catch (NullPointerException e) {
            Utilities.AlertBox.notificationWarn("Opps something went wrong :(", "Enquiry number not chosen.");
        }
    }

    void Gen_Table_in_QuotationPane(String eqno) {
        JFXTreeTableColumn<AnalysisDT1, String> subjec = new JFXTreeTableColumn<>("Quotation Number");
        subjec.setPrefWidth(200);
        subjec.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT1, String> param) -> param.getValue().getValue().qo);

        JFXTreeTableColumn<AnalysisDT1, String> cod = new JFXTreeTableColumn<>(" Date Received");
        cod.setPrefWidth(100);
        cod.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT1, String> param) -> param.getValue().getValue().dates);

        JFXTreeTableColumn<AnalysisDT1, String> attende = new JFXTreeTableColumn<>("File Location");
        attende.setPrefWidth(200);
        attende.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT1, String> param) -> param.getValue().getValue().loc);

        ObservableList<AnalysisDT1> users1 = FXCollections.observableArrayList();

        try {

            String suql = "SELECT p.`Qno`, `date_recv`, `location` FROM `purchase_quotation` as p  WHERE p.Eqno = ?;";

            PreparedStatement st;
            st = com.mycompany.snp.MainApp.conn.prepareStatement(suql);
            st.setString(1, eqno);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                users1.add(new AnalysisDT1(rs.getString(1), rs.getDate(2).toString(), rs.getString(3)));
            }
            Table1.getColumns().clear();
            final TreeItem<AnalysisDT1> root = new RecursiveTreeItem<AnalysisDT1>(users1, RecursiveTreeObject::getChildren);
            Table1.getColumns().setAll(subjec, cod, attende);
            Table1.setRoot(root);
            Table1.setShowRoot(false);
        } catch (SQLException e) {
            Utilities.AlertBox.notificationWarn("Error", "We are currently facing some problems. Please let us resolve them and get back to you");
            Utilities.AlertBox.showErrorMessage(e);
        }
    }

    @FXML
    private void Select_Purchase_Order_Number_For_Edit(MouseEvent event) {
        try {
            POnumber.setText(PO_Select.getValue());
            String sql1 = "Select ep.Pjno,po.PaymentTerm,po.DeliveryDate,po.Description,"
                    + " po.Rate from `purchase_po` po Join `purchase_qprel` qp on po.Po_NO=qp.Po_NO"
                    + " LEFT OUTER JOIN `purchase_eprel` ep on qp.Eqno=ep.Eqno where po.Po_NO=?";
            PreparedStatement stmt1 = com.mycompany.snp.MainApp.conn.prepareStatement(sql1);
            stmt1.setString(1, POnumber.getText());
            ResultSet rs1 = stmt1.executeQuery();
            if (rs1.next()) {
                try {
                    if (!rs1.getString(1).equals("")) {

                        Pjnumber.setText(rs1.getString(1));
                    }
                } catch (NullPointerException e) {

                    Pjnumber.setText(Utilities.Date.Date().substring(2, 4) + "CONS");

                }

                paymentTerms.setText(rs1.getString(2));
                OrderDate1.setValue(LocalDate.parse(rs1.getString(3)));
                Header.setText(rs1.getString(4));
                GSTRate.setText(rs1.getString(5));
            } else {

            }

            String sql2 = "Select  `UOM`, `Description`, `Qty`, `Price`, `TotalAmt`, `Discount`"
                    + " from `purchase_potabledetails` where Po_NO=? ORDER BY `RC` ASC";
            PreparedStatement stmt2 = com.mycompany.snp.MainApp.conn.prepareStatement(sql2);
            stmt2.setString(1, POnumber.getText());
            ResultSet rs2 = stmt2.executeQuery();
            ObservableList<Person4> trc = FXCollections.observableArrayList();
            while (rs2.next()) {
                trc.add(new Person4(rs2.getString(2), rs2.getString(1),
                        rs2.getString(3), rs2.getString(4), rs2.getString(6), rs2.getString(5)));

            }
            ;
            for (int o = 0; o < 100; o++) {
                trc.add(new Person4("", "", "", "", "", ""));
            }
            Table2.getItems().clear();
            PO_Tabel_Generation(trc);


            /*  String sql2 = "Select * from `purchase_potabledetails` where Po_NO=?";
                  PreparedStatement stmt2 = com.mycompany.snp.MainApp.conn.prepareStatement(sql2);
                  stmt2.setString(1,POnumber.getText());
                  ResultSet rs2=stmt2.executeQuery();
                  while(rs.next()){ 
                  }*/
            String sql3 = "Select GST,Total,SubTotal from `purchase_po` WHERE Po_NO=?";
            PreparedStatement stmt3 = com.mycompany.snp.MainApp.conn.prepareStatement(sql3);
            stmt3.setString(1, POnumber.getText());
            ResultSet rs3 = stmt3.executeQuery();
            rs3.next();
//            Double res = (rs3.getDouble(1) / rs3.getDouble(2)) * 100;;
//
//            GSTRate.setText(String.valueOf(res));
//
//            GSTRate.setText(String.valueOf(round(res, 2)));

            comp_inv_gst = GSTRate.getText();
            PoTotal11.setText(String.valueOf(rs3.getDouble(2)));
            PoTotal1.setText(String.valueOf(rs3.getDouble(1)));
            PoTotal.setText(String.valueOf(rs3.getDouble(3)));

            String sql4 = "Select c.Name,c.Address from `customer` c,`purchase_po` po,"
                    + "`purchase_qprel` qp,`purchase_enquiry` pe"
                    + " where po.Po_NO=? and po.Po_NO=qp.Po_NO and qp.Eqno=pe. Eqno and pe.SID=c.CID";
            PreparedStatement stmt4 = com.mycompany.snp.MainApp.conn.prepareStatement(sql4);
            stmt4.setString(1, POnumber.getText());
            ResultSet rs4 = stmt4.executeQuery();
            rs4.next();
            supplierInfo.setText(rs4.getString(1) + "\n" + rs4.getString(2));
            pdfGen.setDisable(false);
            pdfGen.setVisible(true);
        } catch (SQLException ex) {
            Utilities.AlertBox.showErrorMessage(ex);
            Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    static boolean edit_bttn = false;

    @FXML
    private void Edit_An_Existing_Purchase_Order(MouseEvent event) {
        edit_bttn = true;
        PO_Select.setDisable(false);
        PO_Select.setVisible(true);
        tickPO2.setDisable(false);
        tickPO2.setVisible(true);
        plusPO.setDisable(false);
        plusPO.setVisible(true);
        Pjnumber.clear();
        supplierInfo.clear();
        Table2.getItems().clear();
        POqno.setDisable(true);
        POqno.setVisible(false);
        Potick.setDisable(true);
        Potick.setVisible(false);
        editPO.setDisable(true);
        editPO.setVisible(false);
        PO_Select.getItems().clear();
        POnumber.clear();
        Header.clear();
        OrderDate1.setValue(null);
        PoTotal11.clear();
        PoTotal.clear();
        paymentTerms.clear();
        PoTotal1.clear();
        GSTRate.clear();
        comp_inv_gst = "0";
        try {
            String sql = "SELECT Po_NO FROM `purchase_po` WHERE 1 Order BY DeliveryDate DESC;";
            PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PO_Select.getItems().add(rs.getString(1));
                // 

            }
        } catch (SQLException ex) {
            Utilities.AlertBox.showErrorMessage(ex);
            Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Add_Purchase_Order(MouseEvent event) {
        PO_Select.setDisable(true);
        PO_Select.setVisible(false);
        tickPO2.setDisable(true);
        tickPO2.setVisible(false);
        plusPO.setDisable(true);
        plusPO.setVisible(false);

        POqno.setDisable(false);
        POqno.setVisible(true);
        Potick.setDisable(false);
        Potick.setVisible(true);
        editPO.setDisable(false);
        editPO.setVisible(true);

        Pjnumber.clear();
        supplierInfo.clear();
        Table2.getItems().clear();
        POnumber.clear();
        Header.clear();
        OrderDate1.setValue(null);
        PoTotal11.clear();
        PoTotal.clear();
        paymentTerms.clear();
        PoTotal1.clear();
        GSTRate.clear();
        comp_inv_gst = "0";
        edit_bttn = false;
    }

    @FXML
    private void handleClose(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void handlemin(MouseEvent event) {
        Stage stage;
        stage = stage = (Stage) miniButton.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void power_off(MouseEvent event) {
          try {
            Stage stage;
            Parent root;
            stage = (Stage) Table1.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
              BorderPane root1 = new BorderPane(root);
            root1.setOnMousePressed((MouseEvent event1) -> {
                xOffset = event1.getSceneX();
                yOffset = event1.getSceneY();
            });
            root1.setOnMouseDragged((MouseEvent event1) -> {
                stage.setX(event1.getScreenX() - xOffset);
                stage.setY(event1.getScreenY() - yOffset);
            });
            
            Scene scene = new Scene(root1);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    class AnalysisDT2 extends RecursiveTreeObject<AnalysisDT2> {
        //ino,daterecv,totalamt,paid?,amtpaid,payduedate
        //this inner class is used for the enquiry tab on the dashboard

        StringProperty ino;
        StringProperty daterecv;
        StringProperty totalamt;
        StringProperty paid;
        StringProperty amtpaid;
        StringProperty payduedate;
        StringProperty latey;
        StringProperty supps;
        StringProperty pono;

        public AnalysisDT2(String ino, String daterecv, String totalamt, String paid, String amtpaid, String payduedate, String late) {
            this.ino = new SimpleStringProperty(ino);
            this.daterecv = new SimpleStringProperty(daterecv);
            this.totalamt = new SimpleStringProperty(totalamt);
            this.paid = new SimpleStringProperty(paid);
            this.amtpaid = new SimpleStringProperty(amtpaid);
            this.payduedate = new SimpleStringProperty(payduedate);
            this.latey = new SimpleStringProperty(late);
        }

        public AnalysisDT2(String ino, String pono, String supps, String daterecv, String totalamt, String paid, String amtpaid, String payduedate, String late) {
            this.ino = new SimpleStringProperty(ino);
            this.supps = new SimpleStringProperty(supps);
            this.pono = new SimpleStringProperty(pono);
            this.daterecv = new SimpleStringProperty(daterecv);
            this.totalamt = new SimpleStringProperty(totalamt);
            this.paid = new SimpleStringProperty(paid);
            this.amtpaid = new SimpleStringProperty(amtpaid);
            this.payduedate = new SimpleStringProperty(payduedate);
            this.latey = new SimpleStringProperty(late);
        }

    }

    static String loc = "UNKNOWN";

    @FXML
    private void Selection_of_PO_for_Invoice_Entry(MouseEvent event) {
        Inv_no.clear();
        inv_date_recv.setValue(null);
        inv_date_due.setValue(null);
        inv_amt1.clear();
        inv_amt.clear();

        inv_del.setVisible(false);
        inv_del.setDisable(true);

        inv_loc.clear();
        TogglePaid.setSelected(false);

        inv_loc.setText(loc);
        if (edit_btn_hit == false) {
            Generate_Invoice_Table();
            inv_del.setVisible(false);
            inv_del.setDisable(true);

        } else {
            Fill_invoice_details_in_edit_mode();
            System.out.print("me im printy boi");
            inv_del.setVisible(!false);
            inv_del.setDisable(!true);

        }
    }

    public void Fill_invoice_details_in_edit_mode() {
//        /Select date_recv,PayDueDate,AmtwithGST,AmtwoGST from `purchase_invoice` where Ino=?
        System.out.print("me im printy boi2");
        try {
            Double gstamtt;
            String invno = PO_inv.getValue();
            int indexreq = invno.indexOf(':');
            String actinvno = invno.substring(0, indexreq);
            System.out.println(invno);
            String sql = "Select date_recv,PayDueDate,AmtwithGST,AmtwoGST,Location,paid from `purchase_invoice` where Ino=?";
            PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
            stmt.setString(1, actinvno);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.print("me im printy boi3");
                gstamtt = Double.valueOf(rs.getString(3)) - Double.valueOf(rs.getString(4));
                inv_amt1.setText(String.valueOf(gstamtt));
                inv_amt.setText(rs.getString(3));
                Inv_no.setText(actinvno.trim());
                inv_date_due.setValue(LocalDate.parse(rs.getString(2)));
                inv_date_recv.setValue(LocalDate.parse(rs.getString(1)));
                inv_loc.setText(rs.getString(5));
                if (rs.getString(6).equals("0")) {
                    TogglePaid.setSelected(false);
                } else {
                    TogglePaid.setSelected(!false);
                }

            }
        } catch (SQLException ex) {
            Utilities.AlertBox.showErrorMessage(ex);
            Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Attach_Invoice_Button_Clicked(MouseEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new ExtensionFilter("PDF Files", "*.pdf"));
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
            loc = selectedFile.getAbsolutePath();

        }
        inv_loc.setText(loc);

    }

    class AnalysisDT1 extends RecursiveTreeObject<AnalysisDT1> {

        //this inner class is used for the enquiry tab on the dashboard
        StringProperty qo;
        StringProperty dates;
        StringProperty loc;

        public AnalysisDT1(String q, String dates, String loc) {
            this.qo = new SimpleStringProperty(q);
            this.dates = new SimpleStringProperty(dates);
            this.loc = new SimpleStringProperty(loc);
        }

    }

    @FXML
    private void delQuotation(MouseEvent event) {
        String no[];
        ArrayList<String> a = new ArrayList();
        String entered = Utilities.AlertBox.alterinput("", "Delete Quotation", "Enter the Enquiry number of the quotation to be deleted", "Enquiry Number");
        if (entered.equals("Cancel")) {

        } else {
            try {

                String sql = "Select QNo FROM `purchase_quotation` WHERE Eqno=?;";
                PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
                stmt.setString(1, entered);
                ResultSet rs = stmt.executeQuery();
                int i = 0;
                while (rs.next()) {
                    System.out.println("Inside " + i);
                    a.add(rs.getString(1));
                    //no[i] = rs.getString(1);
                    i++;
                }
                no = new String[i];
                if (i == 0 || a.size() == 0) {
                    Utilities.AlertBox.notificationWarn("Opps something went wrong :(", "Either Enquiry " + entered + " doesn't have any Quoations or it doesn't exist.");
                } else {
                    String alter = Utilities.AlertBox.alterchoice(a.toArray(no), "Delete Quotation", "Choose the Quotation you wish to delete", "Quotation Number :");
                    if (alter == "Cancel") {

                    } else {
                        sql = "Delete FROM `purchase_quotation` WHERE Qno=? AND EQno = ? ;";
                        stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
                        stmt.setString(1, alter);
                        stmt.setString(2, entered);
                        int k = stmt.executeUpdate();
                        if (k != 0) {
                            Utilities.AlertBox.notificationInfo("Success", "Quotation " + alter + " deleted.");
                        } else {
                            Utilities.AlertBox.notificationWarn("Opps something went wrong :(", "Please try again.");
                        }
                    }
                }

            } catch (SQLException ex) {
                Utilities.AlertBox.showErrorMessage(ex);
                Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    void saveNewQuotation(MouseEvent event) {
        /*
            private JFXTextField QNo;
    @FXML
    private JFXDatePicker Date_Qno;
    @FXML
    private JFXTextField Location_QNo;
    @FXML
    private JFXTextField POnumber;
         */
        String q = QNo.getText();
        String date = Date_Qno.getValue().toString();
        if (EnqSelect1.getValue().isEmpty()) {
            Utilities.AlertBox.notificationWarn("Error", "Some of the fields seem to be empty");
        } else {

            try {
                String sql = "INSERT INTO `purchase_quotation`(`Qno`, `date_recv`, `location`, `EQno`) VALUES (?,?,?,?);";
                PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
                stmt.setString(1, q);
                stmt.setString(2, date);
                stmt.setString(3, f);
                stmt.setString(4, EnqSelect1.getValue());
                stmt.executeUpdate();
                Gen_Quotation_Table_After_Save(EnqSelect1.getValue());
                Utilities.AlertBox.notificationInfo("Success", "The Quotation details have been saved");

            } catch (SQLException ex) {
                Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);

                Utilities.AlertBox.showErrorMessage(ex);
            }
        }
    }

    void Gen_Quotation_Table_After_Save(String eqno) {
        Runnable task2 = new Runnable() {
            public void run() {
                runGen_Quotation_Table_After_Save(eqno);

            }
        };

        Thread backgroundThread1 = new Thread(task2);
        backgroundThread1.setDaemon(true);
        backgroundThread1.start();
    }

    void runGen_Quotation_Table_After_Save(String eqno) {
        try {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Gen_Table_in_QuotationPane(eqno);
                }
            });

            System.out.println("1 closed");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void Selection_of_Quotation_for_PO_Entry(MouseEvent event) {
        POnumber.clear();
        Header.clear();
        OrderDate1.setValue(null);
        PoTotal11.clear();
        PoTotal.clear();
        paymentTerms.clear();
        PoTotal1.clear();
        GSTRate.clear();
        comp_inv_gst = "0";
        //proj-cmpname-PO-seqno or proj(cons)-cmpname-PO-seqno
        POnumber.clear();
        Header.clear();
        OrderDate1.setValue(null);
        PoTotal11.clear();
        PoTotal.clear();
        paymentTerms.clear();
        PoTotal1.clear();
        GSTRate.clear();
        comp_inv_gst = "0";
        String res = "";
        String projn = "", cmps = "";
        String da = Utilities.Date.Date().substring(2, 4);
        Table2.setEditable(true);
        pdfGen.setDisable(true);
        pdfGen.setVisible(false);

        try {
            String sql = "Select q.Qno, e.Cmpname, e.Type, ep.Pjno,c.Name,c.Address from \n"
                    + "`purchase_quotation` q Join `purchase_enquiry` e ON q.EQno = e.EQno  JOIN `customer` c ON e.SID=c.CID  LEFT OUTER JOIN `purchase_eprel` ep ON e.EQno = ep.EQno \n"
                    + "where q.Qno=?";
            PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
            //System.out.println("res val afugcliucfcfpouofvv[ipg'piter db fetch before inc" + POqno.getValue().substring(0, POqno.getValue().indexOf(':')-2));
            stmt.setString(1, POqno.getValue().substring(0, POqno.getValue().indexOf(':') - 1));
            ResultSet rs = stmt.executeQuery();
            String toname = "";
            String toaddr = "";
            while (rs.next()) {
                System.out.println("res val afugcliucfcfpouofvv[ipg'piter db fetch before inc" + res);
                projn = rs.getString(4);
                toname = rs.getString(5);
                toaddr = rs.getString(6);
                if (rs.getString(3).equals("Regular")) {
                    res += da;
                    res += "CONS-";
                    Pjnumber.setText(res.substring(0, res.length() - 1));
                } else {
                    res += projn;
                    res += "-";
                    Pjnumber.setText(projn);
                }
                if (rs.getString(2).equals("Awin")) {
                    res += "AE";
                } else {
                    res += "SC";
                }
                res += "-PO-";
                cmps = rs.getString(2);

            }

            supplierInfo.setText(toname + "\n" + toaddr);
            String mx1;
            String mx;
            int temp;
            ArrayList<Integer> mxval = new ArrayList();
            String suql;
            try {
                if (cmps.equalsIgnoreCase("AWIN")) {
                    suql = "SELECT Po_NO,PaymentTerm FROM `purchase_po` WHERE Po_NO LIKE '%AE%'";
                } else {
                    suql = "SELECT Po_NO,PaymentTerm FROM `purchase_po` WHERE Po_NO LIKE '%SC%'";
                }
                stmt = com.mycompany.snp.MainApp.conn.prepareStatement(suql);
                rs = stmt.executeQuery(suql);
                int x;
                String v = "";
                int i = 0, j = 0;
                while (rs.next()) {
                    x = rs.getString(1).lastIndexOf("-");
                    v = rs.getString(1).substring(x + 1, rs.getString(1).length());
                    mxval.add(Integer.parseInt(v));
                    i++;
                }

                if (i == 0) {

                    temp = 0;
                    System.out.println("temp val=" + temp);

                } else {
                    temp = Collections.max(mxval);
                }

                System.out.println("res val after db fetch before inc" + res);
                temp++;

                System.out.println("projn val after inc=" + projn);
                String te = "";

                if (temp < 10) {
                    te += "00";
                } else if (temp >= 10 && temp < 100) {
                    te = "0";
                } else {
                    te = "";
                }
                te += String.valueOf(temp);
                res += te;
                POnumber.setText(res);
                PO_Table_for_Entry_of_data(100);

            } catch (SQLException ex) {
                Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);

                Utilities.AlertBox.showErrorMessage(ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);

            Utilities.AlertBox.showErrorMessage(ex);
        }
    }

    TableColumn uomCol = new TableColumn("UOM");
    TableColumn desCol = new TableColumn("Description");
    TableColumn quantityCol = new TableColumn("Quantity");
    TableColumn unitCol = new TableColumn("Unit Price");
    TableColumn totalCol = new TableColumn("Total");
    TableColumn DiscountCol = new TableColumn("Discount");

    void PO_Tabel_Generation(ObservableList<Person4> data) {
        Table2.getColumns().clear();
        uomCol.setSortable(false);
        desCol.setSortable(false);
        quantityCol.setSortable(false);
        unitCol.setSortable(false);
        totalCol.setSortable(false);
        DiscountCol.setSortable(false);
        uomCol.setPrefWidth(50);
        desCol.setPrefWidth(150);
        quantityCol.setPrefWidth(100);
        unitCol.setPrefWidth(150);
        totalCol.setPrefWidth(50);
        DiscountCol.setPrefWidth(50);

        Table2.getColumns().addAll(desCol, uomCol, quantityCol, unitCol, DiscountCol, totalCol);

        uomCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("UOM")
        );
        desCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("Des")
        );
        quantityCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("Qty")
        );

        unitCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("UnitPrice")
        );
        totalCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("Total")
        );
        DiscountCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("Discount")
        );

        Table2.setItems(data);

    }

    void PO_Tabel_Generation() {
        Table2.getColumns().clear();
        uomCol.setSortable(false);
        desCol.setSortable(false);
        quantityCol.setSortable(false);
        unitCol.setSortable(false);
        totalCol.setSortable(false);
        DiscountCol.setSortable(false);
        uomCol.setPrefWidth(50);
        desCol.setPrefWidth(150);
        quantityCol.setPrefWidth(100);
        unitCol.setPrefWidth(150);
        totalCol.setPrefWidth(50);
        DiscountCol.setPrefWidth(50);

        Table2.getColumns().addAll(desCol, uomCol, quantityCol, unitCol, DiscountCol, totalCol);

        uomCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("UOM")
        );
        desCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("Des")
        );
        quantityCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("Qty")
        );

        unitCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("UnitPrice")
        );
        totalCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("Total")
        );
        DiscountCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("Discount")
        );

    }

    void PO_Table_for_Entry_of_data(int times) {

        ObservableList<Person4> data;
        data = FXCollections.observableArrayList();
        for (int o = 0; o < times; o++) {
            data.add(new Person4("", "", "", "", "", ""));
        }
        PO_Tabel_Generation(data);
    }

    boolean PO_Table_Insert_into_Database(Connection conn) {
        PreparedStatement stmt;
        try {

            //first deleting quotations details of the particular qno
            String suqdel = "DELETE FROM `purchase_potabledetails` WHERE `po_NO`= ? ;";
            String pono = POnumber.getText();
            stmt = conn.prepareStatement(suqdel);
            stmt.setString(1, POnumber.getText());
            stmt.executeUpdate();

            ObservableList<Person4> trc;
            trc = FXCollections.observableArrayList(Table2.getItems());
            trc.add(new Person4("", "", "", "", "", ""));
            int i = 0;
            while (i < 100) {
                System.out.println("IN while loop");
                Person4 p = trc.get(i);
                if (p.getDes().getText().trim().equalsIgnoreCase("")) {
                    System.out.println("done table PO");
                    break;
                } else {
                    String uom = p.getUOM().getText();
                    String qty = p.getQty().getText();
                    String up = p.getUnitPrice().getText();
                    String d = p.getDes().getText();
                    String Dis = p.getDiscount().getText();
                    String tot = p.getTotal().getText();

                    try {

                        //first deleting quotations details of the particular qno
                        String suql1 = "INSERT INTO `purchase_potabledetails`(`Po_NO`, `UOM`, `Description`, `Qty`, `Price`, `TotalAmt`,"
                                + " `Discount`) VALUES (?,?,?,?,?,?,?)";
                        stmt = conn.prepareStatement(suql1);
                        stmt.setString(1, pono);
                        stmt.setString(2, uom);
                        stmt.setString(3, d);
                        stmt.setString(4, qty);
                        stmt.setString(5, up);
                        stmt.setString(6, tot);
                        stmt.setString(7, Dis);
                        stmt.executeUpdate();

                    } catch (SQLException exe) {
                        Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, exe);
                        Utilities.AlertBox.notificationWarn("Error", "Oops something went wrong!");
                        Utilities.AlertBox.showErrorMessage(exe);
                    }

                }
                i++;
            }
            conn.close();
            if (i == 0) {
                Utilities.AlertBox.notificationWarn("Blank Purchaser Order", "The purchase order table seems to be blank");

            } else {
                Utilities.AlertBox.notificationInfo("Success", "Your Purchase Order was saved successfully!");
                Table2.setEditable(false);
                return true;

            }

        } catch (Exception e) {

            Utilities.AlertBox.notificationWarn("Error", "Oops something went wrong!");
            Utilities.AlertBox.showErrorMessage(e);
        }
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    boolean PO_Pane_Insert_into_Database(Connection conn) {
        PreparedStatement stmt;//paymentTerms
//OrderDate1
//OrderDate
//Header
//PoTotal
//PoTotal1
//PoTotal11
        if (edit_bttn == true) {

            System.out.println("Inside edit function");

            try {
                if (POnumber.getText().trim().equals("") || paymentTerms.getText().trim().equals("") || Header.getText().trim().equals("")
                        || PoTotal1.getText().trim().equals("") || PoTotal11.getText().trim().equals("")) {
                    Utilities.AlertBox.notificationWarn("Error", "Somer fields are left blank!");
                } else {
                    System.out.println("Inside if");
                    String s2 = "";

                    try {

                        s2 = OrderDate1.getValue().toString();
                        System.out.println("Delivery given");
                        String suqdel = "UPDATE `purchase_po` SET `Description`=?,`DeliveryDate`=?,`Total`=?,`SubTotal`=?,`PaymentTerm`=?"
                                + ",`GST`=?,`Rate`=? WHERE `Po_NO`=?";
                        stmt = conn.prepareStatement(suqdel);
                        stmt.setString(8, POnumber.getText());
                        stmt.setString(1, Header.getText());
                        stmt.setString(2, s2);
                        stmt.setString(3, PoTotal11.getText());
                        stmt.setString(4, PoTotal.getText());
                        stmt.setString(5, paymentTerms.getText());
                        stmt.setString(6, PoTotal1.getText());
                        stmt.setString(7, GSTRate.getText());
                        stmt.executeUpdate();
                    } catch (NullPointerException e) {
                        String suqdel = "UPDATE `purchase_po` SET `Description`=?,`Total`=?,`SubTotal`=?,`PaymentTerm`=?"
                                + ",`GST`=?,`Rate`=? WHERE `Po_NO`=?";
                        System.out.println("Delivery not given");
                        stmt = conn.prepareStatement(suqdel);
                        stmt.setString(7, POnumber.getText());
                        stmt.setString(1, Header.getText());
                        stmt.setString(2, PoTotal11.getText());
                        stmt.setString(3, PoTotal.getText());
                        stmt.setString(4, paymentTerms.getText());
                        stmt.setString(5, PoTotal1.getText());
                        stmt.setString(6, GSTRate.getText());
                        stmt.executeUpdate();
                    }

                    System.out.println("Outside try");
                    Utilities.AlertBox.notificationInfo("Success", "Details of the Purchase Order have been saved.");
                    //edit_bttn=false;
                    return true;
                }
            } catch (SQLException e) {
                Utilities.AlertBox.notificationWarn("Error", "Oops something went wrong!");
                Utilities.AlertBox.showErrorMessage(e);

            } catch (NullPointerException e) {
                Utilities.AlertBox.notificationWarn("Error", "Oops something went wrong!");
                Utilities.AlertBox.showErrorMessage(e);
            }
        } else {

            System.out.println("Inside non edit function");

            try {
                if (POnumber.getText().trim().equals("") || paymentTerms.getText().trim().equals("") || Header.getText().trim().equals("")
                        || PoTotal1.getText().trim().equals("") || PoTotal11.getText().trim().equals("")) {
                    Utilities.AlertBox.notificationWarn("Error", "Somer fields are left blank!");
                } else {
                    System.out.println("Inside if");
                    String s2 = "";

                    try {

                        s2 = OrderDate1.getValue().toString();
                        System.out.println("Delivery given");
                        String suqdel = "INSERT INTO `purchase_po`(`Po_NO`, `Description`, `DeliveryDate`, `Total`, `SubTotal`, `PaymentTerm`, `GST`,`Rate`)"
                                + " VALUES (?,?,?,?,?,?,?,?)";
                        stmt = conn.prepareStatement(suqdel);
                        stmt.setString(1, POnumber.getText());
                        stmt.setString(2, Header.getText());
                        stmt.setString(3, s2);
                        stmt.setString(4, PoTotal11.getText());
                        stmt.setString(5, PoTotal.getText());
                        stmt.setString(6, paymentTerms.getText());
                        stmt.setString(7, PoTotal1.getText());
                        stmt.setString(8, GSTRate.getText());
                        stmt.executeUpdate();
                        System.out.println("Insert into po done");
                    } catch (NullPointerException e) {
                        String suqdel = "INSERT INTO `purchase_po`(`Po_NO`, `Description`, `Total`, `SubTotal`, `PaymentTerm`, `GST`,`Rate`)"
                                + " VALUES (?,?,?,?,?,?,?)";
                        System.out.println("Delivery not given");
                        stmt = conn.prepareStatement(suqdel);
                        stmt.setString(1, POnumber.getText());
                        stmt.setString(2, Header.getText());
                        stmt.setString(3, PoTotal11.getText());
                        stmt.setString(4, PoTotal.getText());
                        stmt.setString(5, paymentTerms.getText());
                        stmt.setString(6, PoTotal1.getText());
                        stmt.setString(7, GSTRate.getText());
                        stmt.executeUpdate();
                        System.out.println("Insert into po done");
                    }
                    String suqdel = "SELECT p.EQno FROM purchase_quotation p join purchase_enquiry q on p.EQno=q.Eqno"
                            + " Join customer c on q.SID=c.CID where p.Qno=? AND c.Name=?";
                    stmt = conn.prepareStatement(suqdel);
                    System.out.println("qno " + POqno.getValue().substring(0, POqno.getValue().indexOf(':') - 1));
                    System.out.println("Name " + POqno.getValue().substring(POqno.getValue().indexOf(':') + 2, POqno.getValue().length()));
                    stmt.setString(1, POqno.getValue().substring(0, POqno.getValue().indexOf(':') - 1));
                    stmt.setString(2, POqno.getValue().substring(POqno.getValue().indexOf(':') + 2, POqno.getValue().length()));
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {

                        suqdel = "INSERT INTO `purchase_qprel`(`Qno`, `Po_NO`, `Eqno`) VALUES (?,?,?)";
                        System.out.println("eqno retreived " + rs.getString(1));
                        stmt = conn.prepareStatement(suqdel);
                        stmt.setString(1, POqno.getValue().substring(0, POqno.getValue().indexOf(':') - 1));
                        stmt.setString(2, POnumber.getText());
                        stmt.setString(3, rs.getString(1));
                        stmt.executeUpdate();
                        System.out.println("");
                    }
                    System.out.println("Outside try");
                    Utilities.AlertBox.notificationInfo("Success", "Details of the Purchase Order have been saved.");
                    return true;
                }
            } catch (SQLException e) {
                Utilities.AlertBox.notificationWarn("Error", "Oops something went wrong!");
                Utilities.AlertBox.showErrorMessage(e);

            } catch (NullPointerException e) {
                Utilities.AlertBox.notificationWarn("Error", "Oops something went wrong!");
                Utilities.AlertBox.showErrorMessage(e);
            }
        }
        return false;
    }

    void runPO_Pane_Threads1() {
        try {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        PO_Table_Insert_into_Database(conn);
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

    void runPO_Pane_Threads2() {
        try {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        System.out.println("Entering function");
                        PO_Pane_Insert_into_Database(conn);
                        System.out.println("Leaving function");
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
    private void SaveNewPO(MouseEvent event) {

        Runnable task1 = new Runnable() {
            public void run() {

                runPO_Pane_Threads2();
            }
        };
        Runnable task2 = new Runnable() {
            public void run() {

                runPO_Pane_Threads1();
            }
        };

        Thread backgroundThread1 = new Thread(task1);
        backgroundThread1.setDaemon(true);
        backgroundThread1.start();
        Thread backgroundThread2 = new Thread(task2);
        backgroundThread2.setDaemon(true);
        backgroundThread2.start();

        //save button
    }

    @FXML
    private void Gen_PO(MouseEvent event) {
        if (Utilities.AlertBox.alertoption("Purchase Order PDF Generation", "Are you sure you want to generate the PDF file for this Purcahse Order?", "Note that once the pdf"
                + " is generated we assume that the generated PDF"
                + " file is sent to the customer.")) {
            try {
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Connection conn1 = DriverManager.getConnection(DB_URL, USER, PASS);
                if (PO_Pane_Insert_into_Database(conn) && PO_Table_Insert_into_Database(conn1)) {

                    try {
                        HashMap<String, Object> hm = new HashMap<String, Object>();
                        hm.put("Purchase Order Number", POnumber.getText());
                        hm.put("Date", Utilities.Date.Date());
                        System.out.println("Company is : "
                                + POnumber.getText()
                                        .substring(POnumber.getText().indexOf("-") + 1,
                                                POnumber.getText().indexOf("-") + 3));
                        if (POnumber.getText()
                                .substring(POnumber.getText().indexOf("-") + 1,
                                        POnumber.getText().indexOf("-") + 3).trim() == "SC");

                        if (POnumber.getText().contains("SC")) {
                            hm.put("Company", "Steel");
                            System.out.println("Company is Steel: "
                                    + POnumber.getText()
                                            .substring(POnumber.getText().indexOf("-") + 1,
                                                    POnumber.getText().indexOf("-") + 3));
                        } else {
                            hm.put("Company", "Awin");
                            System.out.println("Company is Awin: "
                                    + POnumber.getText()
                                            .substring(POnumber.getText().indexOf("-") + 1,
                                                    POnumber.getText().indexOf("-") + 3));
                        }
                        String Sql = "SELECT `Qno` FROM `purchase_qprel` WHERE  `Po_NO`=?";

                        PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(Sql);

                        stmt.setString(1, POnumber.getText());

                        ResultSet rs = stmt.executeQuery();
                        if (rs.next()) {
                            hm.put("Quotation Number", rs.getString(1));
                        }
                        hm.put("Payment Terms", paymentTerms.getText().trim());
                        hm.put("ProjectNo", Pjnumber.getText());

                        hm.put("toAddress", supplierInfo.getText());
                        hm.put("Total", Double.valueOf(PoTotal11.getText()));
                        hm.put("GST Rate", Float.valueOf(GSTRate.getText()));
                        hm.put("Sub Total", Double.valueOf(PoTotal.getText()));
                        hm.put("GST Amount", Double.valueOf(PoTotal1.getText()));
                        hm.put("Delivery Required By", OrderDate1.getValue().toString());
                        hm.put("Header", Header.getText().trim());
                        ObservableList<Person4> trc;
                        trc = FXCollections.observableArrayList(Table2.getItems());
                        hm.put("TableItems", trc);
                        new PdfGeneration.pdfPurchaseOrder(hm);
                        //Update database that invoice has been generated
                        String sql = "UPDATE `purchase_po` SET `Sentdate`= ? ,`Sent`=?  WHERE `Po_NO`= ? AND Sent = 0";
                        PreparedStatement ps;
                        ps = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
                        ps.setString(1, Utilities.Date.Date());
                        ps.setInt(2, 1);
                        ps.setString(3, POnumber.getText().trim());
                        ps.executeUpdate();
                    } catch (Exception e) {

                        Utilities.AlertBox.showErrorMessage(e);
                        Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, e);
                    }

                }
            } catch (SQLException ex) {
                Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @FXML
    void Add_a_New_Invoice_for_PO(MouseEvent event) {
        Runnable task3 = new Runnable() {
            public void run() {
                show_showpos_threads();

            }
        };

        Thread backgroundThread3 = new Thread(task3);
        backgroundThread3.setDaemon(true);
        backgroundThread3.start();
        edit_btn_hit = false;
        inv_plus.setVisible(!true);
        inv_plus.setDisable(!false);
        Inv_pen.setVisible(!false);
        Inv_pen.setDisable(!true);
        PO_inv.setPromptText("Purchase Order Number");
        Inv_no.clear();
        inv_date_recv.setValue(null);
        inv_date_due.setValue(null);
        inv_amt1.clear();
        inv_amt.clear();
        inv_loc.clear();
        TogglePaid.setSelected(false);
        inv_del.setVisible(!true);
        inv_del.setDisable(!false);
        Money_Paid.setVisible(false);
        Money_Paid.setDisable(true);

    }

    @FXML
    void Delete_Invoice_Selected_By_ComboBox(MouseEvent event) {
        if (Utilities.AlertBox.alertoption("Delete Invoice", "Delete Invoice " + Inv_no.getText(), "Are you are you want "
                + "to delete this invoice? Note this acttion cannot be undone.")) {

            try {
                String sql = "DELETE FROM `purchase_invoice` WHERE `Ino`=?";
                PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
                stmt.setString(1, Inv_no.getText());
                stmt.executeUpdate();
                Utilities.AlertBox.notificationInfo("Success", "The Invoice has been deleted");
                //update table
                Generate_Table_Invoices_in_Invoice_Pane();
                PO_inv.getItems().clear();
                //update the box
                invoice_Combo();
                Inv_no.clear();
                inv_date_recv.setValue(null);
                inv_date_due.setValue(null);
                inv_amt1.clear();
                inv_amt.clear();

                inv_loc.clear();

                TogglePaid.setSelected(false);
            } catch (SQLException ex) {
                Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    static boolean edit_btn_hit = false;

    @FXML
    void Edit_An_Existing_Invoice_for_PO(MouseEvent event) {
        inv_plus.setVisible(true);
        inv_plus.setDisable(false);
        Inv_pen.setVisible(false);
        Inv_pen.setDisable(true);
        edit_btn_hit = true;
        PO_inv.setPromptText("Invoice Number");
        Inv_no.clear();
        inv_date_recv.setValue(null);
        inv_date_due.setValue(null);
        inv_amt1.clear();
        inv_amt.clear();
        inv_loc.clear();
        TogglePaid.setSelected(false);
        invoice_Combo();
        inv_del.setVisible(true);
        inv_del.setDisable(false);
        Money_Paid.setVisible(!false);
        Money_Paid.setDisable(!true);
        Generate_Table_Invoices_in_Invoice_Pane();
    }

    void invoice_Combo() {

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "Select  CONCAT(pi.Ino,' : ',c.`Name`) AS np  FROM\n"
                    + "`purchase_invoice` pi JOIN `purchase_pirel` pp \n"
                    + "on pi.Ino=pp.Ino  \n"
                    + "JOiN `purchase_qprel` qp \n"
                    + "ON pp.`Po_NO`=qp.`Po_NO` \n"
                    + "Join `purchase_enquiry` ep \n"
                    + "ON ep.`Eqno`=qp.`Eqno` \n"
                    + "JOin `customer` c \n"
                    + "on c.`CID`=ep.`SID` \n"
                    + "where 1;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            PO_inv.getItems().clear();
            while (rs.next()) {
                PO_inv.getItems().add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @FXML

    void Invoice_Save_Button_Clicked_in_Invoice_Pane(MouseEvent event) {
        boolean ef = false;
        if (Inv_no.getText().trim().isEmpty() || inv_amt.getText().trim().isEmpty() || inv_amt1.getText().trim().isEmpty()
                || inv_date_recv.getValue().toString().isEmpty() || inv_date_due.getValue().toString().isEmpty() || inv_loc.getText().trim().isEmpty()) {
            Utilities.AlertBox.notificationWarn("Error", "Some of the fields seem to be empty");
        } else {
            if (!edit_btn_hit) {
                try {
                    String sql = "INSERT INTO `purchase_invoice`(`Ino`, `AmtwoGST`, `AmtwithGST`, `date_recv`, `paid`, `Location`, `PayDueDate`,`amtpaid`) VALUES "
                            + "(?,?,?,?,?,?,?,?);";
                    PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
                    Double wogst = Double.valueOf(inv_amt.getText()) - Double.valueOf((inv_amt1.getText()));
                    stmt.setString(1, Inv_no.getText());
                    stmt.setString(2, String.valueOf(wogst));
                    stmt.setString(3, inv_amt.getText());
                    stmt.setString(4, inv_date_recv.getValue().toString());
                    if (TogglePaid.isSelected()) {
                        ef = true;
                        stmt.setInt(5, 1);
                        stmt.setDouble(8, Double.valueOf(inv_amt.getText()));
                    } else {
                        stmt.setInt(5, 0);
                        stmt.setDouble(8, 0);
                    }
                    stmt.setString(6, loc);
                    stmt.setString(7, inv_date_due.getValue().toString());
                    stmt.executeUpdate();
                    String sqlll = "INSERT INTO `purchase_pirel`(`Po_NO`, `Ino`) VALUES (?,?)";
                    PreparedStatement stmttt = com.mycompany.snp.MainApp.conn.prepareStatement(sqlll);
                    stmttt.setString(1, PO_inv.getValue());
                    stmttt.setString(2, Inv_no.getText());
                    stmttt.executeUpdate();
                    if (ef) {
                        try {
                            String ssql = "INSERT INTO `purchase_invoicepayments`(`Ino`, `paidDate`, `amount`,`Late`) VALUES (?,?,?,?)";
                            PreparedStatement stmtql = com.mycompany.snp.MainApp.conn.prepareStatement(ssql);
                            stmtql.setString(1, Inv_no.getText());
                            stmtql.setString(2, String.valueOf(Utilities.Date.Date()));
                            stmtql.setDouble(3, Double.valueOf(inv_amt.getText()));
                            if (Date.beforeOrAfter(inv_date_due.getValue().toString()) != 0) {
                                stmtql.setInt(4, 1);
                            } else {
                                stmtql.setInt(4, 0);
                            }
                            stmtql.executeUpdate();

                        } catch (ParseException ex) {
                            Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    Utilities.AlertBox.notificationInfo("Success", "Purchase Information was saved successfully!");
                    Generate_Invoice_Table();
                } catch (SQLException ex) {
                    Utilities.AlertBox.showErrorMessage(ex);
                    Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                try {
                    String x = "";
                    if (TogglePaid.isSelected()) {
                        ef = true;

                        x = ",`amtpaid`= " + inv_amt.getText();//stmt.setDouble(8, Double.valueOf(inv_amt.getText()));
                    } else {

                        x = "";
                    }
                    String sql = "Update `purchase_invoice` SET `Ino`=?, `AmtwoGST`=?, `AmtwithGST`=?, `date_recv`=?, `paid`=?, `Location`=?, `PayDueDate`=?" + x + "where `Ino`=?";
                    PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
                    Double wogst = Double.valueOf(inv_amt.getText()) - Double.valueOf((inv_amt1.getText()));
                    stmt.setString(1, Inv_no.getText());
                    stmt.setString(2, String.valueOf(wogst));
                    stmt.setString(3, inv_amt.getText());
                    stmt.setString(4, inv_date_recv.getValue().toString());
                    if (ef) {
                        stmt.setInt(5, 1);
                    } else {
                        stmt.setInt(5, 0);
                    }
                    stmt.setString(6, loc);
                    stmt.setString(7, inv_date_due.getValue().toString());
                    stmt.setString(8, PO_inv.getValue().toString().substring(0, PO_inv.getValue().toString().indexOf(":") - 1));
                    stmt.executeUpdate();
                    String sqlll = "Update `purchase_pirel` Set  `Ino`=? where `INo`=?";
                    PreparedStatement stmttt = com.mycompany.snp.MainApp.conn.prepareStatement(sqlll);
                    stmttt.setString(1, Inv_no.getText());
                    stmttt.setString(2, PO_inv.getValue().toString().substring(0, PO_inv.getValue().toString().indexOf(":") - 1));
                    stmttt.executeUpdate();
                    if (ef) {
                        try {
                            String sq = "Select sum(amount) from `purchase_invoicepayments` where `Ino`= ? ";
                            PreparedStatement stmtql = com.mycompany.snp.MainApp.conn.prepareStatement(sq);
                            stmtql.setString(1, Inv_no.getText());
                            ResultSet rs = stmtql.executeQuery();
                            if (rs.next()) {

                                String ssql = "INSERT INTO `purchase_invoicepayments`(`Ino`, `paidDate`, `amount`,`Late`) VALUES (?,?,?,?)";
                                stmtql = com.mycompany.snp.MainApp.conn.prepareStatement(ssql);
                                stmtql.setString(1, Inv_no.getText());
                                stmtql.setString(2, String.valueOf(Utilities.Date.Date()));
                                stmtql.setDouble(3, Double.valueOf(inv_amt.getText()) - rs.getDouble(1));
                                if (Date.beforeOrAfter(inv_date_due.getValue().toString()) != 0) {
                                    stmtql.setInt(4, 1);
                                } else {
                                    stmtql.setInt(4, 0);
                                }
                                stmtql.executeUpdate();
                            }
                        } catch (ParseException ex) {
                            Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    Utilities.AlertBox.notificationInfo("Success", "Purchase Invoice Information was updated successfully!");
                    Generate_Table_Invoices_in_Invoice_Pane();
                    invoice_Combo();
                } catch (SQLException ex) {
                    Utilities.AlertBox.showErrorMessage(ex);
                    Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

    @FXML
    void Invoice_Amount_Paid(MouseEvent event) {
        String amt, p, sq, due;
        double amtadd, tot, paid;
        PreparedStatement ps;
        outer:
        while (true) {
            p = Utilities.AlertBox.alterinput("", "Verification", "Please enter the admin password", "Passsword");
            if (p.equals("admin")) {
                try {
                    sq = "SELECT `AmtwithGST`, "
                            + "`amtpaid`,`PayDueDate` FROM `purchase_invoice` WHERE `Ino`=?";

                    ps = com.mycompany.snp.MainApp.conn.prepareStatement(sq);
                    ps.setString(1, Inv_no.getText().trim());

                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        tot = rs.getInt(1);
                        paid = rs.getInt(2);
                        due = rs.getString(3);
                        inner:
                        while (true) {
                            amt = Utilities.AlertBox.alterinput((Double.valueOf(tot - paid)).toString(), "Payment", "Amount for Invoice " + Inv_no.getText(), "Enter the paid amount");
                            if (amt.equals("Cancel")) {
                                break outer;
                            } else {
                                try {
                                    amtadd = Double.parseDouble(amt);
                                    if (Utilities.AlertBox.alertoption("Confirmation", "Add $" + amtadd + " ?", "Are you sure you want to add $" + amtadd + " to the amount paid for Invoice " + Inv_no.getText().trim() + " ?")) {

                                        int no = 0;
                                        if ((amtadd + paid) >= tot) {
                                            no = 1;
                                        }
                                        sq = "UPDATE `purchase_invoice` SET"
                                                + "`paid`= ?,`amtpaid`= ? WHERE `Ino`=?;";
                                        ps = com.mycompany.snp.MainApp.conn.prepareStatement(sq);
                                        ps.setInt(1, no);
                                        ps.setDouble(2, paid + amtadd);
                                        ps.setString(3, Inv_no.getText().trim());
                                        ps.executeUpdate();

                                        sq = "INSERT INTO `purchase_invoicepayments`(`Ino`, `paidDate`, `amount`, `Late`) VALUES (?,?,?,?)";
                                        ps = com.mycompany.snp.MainApp.conn.prepareStatement(sq);
                                        ps.setDouble(3, amtadd);
                                        ps.setString(1, Inv_no.getText().trim());
                                        ps.setString(2, Utilities.Date.Date());
                                        ps.setInt(4, (int) Utilities.Date.beforeOrAfter(due));
                                        ps.executeUpdate();
                                        break outer;
                                    } else {
                                        continue inner;
                                    }

                                } catch (NumberFormatException e) {
                                    Utilities.AlertBox.notificationWarn("Error", "Please enter only Integers");
                                    continue inner;
                                }
                            }

                        }
                    }
                } catch (Exception e) {
                    Utilities.AlertBox.showErrorMessage(e);
                    continue;
                }
                break outer;

            } else if (p.equals("Cancel")) {
                break;
            } else {
                Utilities.AlertBox.notificationInfo("Incorrect Password", "There seems to be an error in the entered password");

            }
        }
        Generate_Table_Invoices_in_Invoice_Pane();

    }

    void Generate_Table_Invoices_in_Invoice_Pane() {
        JFXTreeTableColumn<AnalysisDT2, String> subjec = new JFXTreeTableColumn<>("Invoice Number");
        subjec.setPrefWidth(100);
        subjec.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT2, String> param) -> param.getValue().getValue().ino);

        JFXTreeTableColumn<AnalysisDT2, String> pono = new JFXTreeTableColumn<>("Purchase Order");
        pono.setPrefWidth(100);
        pono.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT2, String> param) -> param.getValue().getValue().pono);

        JFXTreeTableColumn<AnalysisDT2, String> supps = new JFXTreeTableColumn<>("Supplier");
        supps.setPrefWidth(75);
        supps.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT2, String> param) -> param.getValue().getValue().supps);

        JFXTreeTableColumn<AnalysisDT2, String> cod = new JFXTreeTableColumn<>("Date Received");
        cod.setPrefWidth(90);
        cod.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT2, String> param) -> param.getValue().getValue().daterecv);

        JFXTreeTableColumn<AnalysisDT2, String> attende = new JFXTreeTableColumn<>("Total Amount");
        attende.setPrefWidth(100);
        attende.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT2, String> param) -> param.getValue().getValue().totalamt);

        JFXTreeTableColumn<AnalysisDT2, String> atte = new JFXTreeTableColumn<>("Paid");
        atte.setPrefWidth(75);
        atte.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT2, String> param) -> param.getValue().getValue().paid);

        JFXTreeTableColumn<AnalysisDT2, String> attender = new JFXTreeTableColumn<>("Amount Paid");
        attender.setPrefWidth(90);
        attender.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT2, String> param) -> param.getValue().getValue().amtpaid);

        JFXTreeTableColumn<AnalysisDT2, String> attendety = new JFXTreeTableColumn<>("Pay DueDate");
        attendety.setPrefWidth(90);
        attendety.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT2, String> param) -> param.getValue().getValue().payduedate);

        JFXTreeTableColumn<AnalysisDT2, String> attendetyr = new JFXTreeTableColumn<>("LATE");
        attendetyr.setPrefWidth(75);
        attendetyr.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT2, String> param) -> param.getValue().getValue().latey);
        String paid, amtpaid, late;

        ObservableList<AnalysisDT2> users2 = FXCollections.observableArrayList();
        try {

            String sql = "Select  pi.Ino,pi.date_recv,pi.AmtwithGST, pi.paid, pi.amtpaid,pi.PayDueDate,pp.Po_NO,c.`Name` FROM "
                    + "`purchase_invoice` pi JOIN `purchase_pirel` pp "
                    + "on pi.Ino=pp.Ino  "
                    + "JOiN `purchase_qprel` qp "
                    + "ON pp.`Po_NO`=qp.`Po_NO` "
                    + "Join `purchase_enquiry` ep "
                    + "ON ep.`Eqno`=qp.`Eqno` "
                    + "JOin `customer` c "
                    + "on c.`CID`=ep.`SID` "
                    + "where 1 ";
            PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
            //stmt.setString(1, PO_inv.getValue());
            ResultSet rs = stmt.executeQuery();
            late = "NA";
            while (rs.next()) {
                late = "NA";
                if (rs.getString(4).equals("0")) {

                    try {
                        late = Utilities.Date.beforeOrAfter(rs.getString(6)) == 0 ? "Not Late" : "Late";
                        users2.add(new AnalysisDT2(rs.getString(1), rs.getString(7), rs.getString(8), rs.getString(2), rs.getString(3), "No", rs.getString(5), rs.getString(6), late));
                    } catch (ParseException ex) {
                        Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    users2.add(new AnalysisDT2(rs.getString(1), rs.getString(7), rs.getString(8), rs.getString(2),
                            rs.getString(3), "Yes", rs.getString(5), rs.getString(6), late));
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, e);
        }
        table3.getColumns().clear();
        final TreeItem<AnalysisDT2> root1 = new RecursiveTreeItem<AnalysisDT2>(users2, RecursiveTreeObject::getChildren);
        table3.getColumns().setAll(subjec, pono, supps, cod, attende, atte, attender, attendety, attendetyr);
        table3.setRoot(root1);
        table3.setShowRoot(false);
        filter_inv.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            table3.setPredicate((TreeItem<AnalysisDT2> t) -> {
                Boolean flag = t.getValue().pono.getValue().toUpperCase().contains(newValue.toUpperCase()) || t.getValue().supps.getValue().toUpperCase().contains(newValue.toUpperCase()) || t.getValue().ino.getValue().toUpperCase().contains(newValue.toUpperCase()) || t.getValue().latey.getValue().toUpperCase().contains(newValue.toUpperCase()) || t.getValue().paid.getValue().toUpperCase().contains(newValue.toUpperCase());

                return flag;
            });
        });
    }

    void Generate_Invoice_Table() {
        JFXTreeTableColumn<AnalysisDT2, String> subjec = new JFXTreeTableColumn<>("Invoice Number");
        subjec.setPrefWidth(100);
        subjec.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT2, String> param) -> param.getValue().getValue().ino);

        JFXTreeTableColumn<AnalysisDT2, String> cod = new JFXTreeTableColumn<>("Date Received");
        cod.setPrefWidth(90);
        cod.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT2, String> param) -> param.getValue().getValue().daterecv);

        JFXTreeTableColumn<AnalysisDT2, String> attende = new JFXTreeTableColumn<>("Total Amount");
        attende.setPrefWidth(100);
        attende.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT2, String> param) -> param.getValue().getValue().totalamt);

        JFXTreeTableColumn<AnalysisDT2, String> atte = new JFXTreeTableColumn<>("Paid");
        atte.setPrefWidth(75);
        atte.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT2, String> param) -> param.getValue().getValue().paid);

        JFXTreeTableColumn<AnalysisDT2, String> attender = new JFXTreeTableColumn<>("Amount Paid");
        attender.setPrefWidth(90);
        attender.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT2, String> param) -> param.getValue().getValue().amtpaid);

        JFXTreeTableColumn<AnalysisDT2, String> attendety = new JFXTreeTableColumn<>("Pay DueDate");
        attendety.setPrefWidth(90);
        attendety.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT2, String> param) -> param.getValue().getValue().payduedate);

        JFXTreeTableColumn<AnalysisDT2, String> attendetyr = new JFXTreeTableColumn<>("LATE");
        attendetyr.setPrefWidth(75);
        attendetyr.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT2, String> param) -> param.getValue().getValue().latey);
        String paid, amtpaid, late;

        ObservableList<AnalysisDT2> users2 = FXCollections.observableArrayList();
        try {

            String sql = "Select  pi.Ino,pi.date_recv,pi.AmtwithGST, pi.paid, pi.amtpaid,pi.PayDueDate FROM"
                    + " `purchase_invoice` pi,`purchase_pirel` pp where pi.Ino=pp.Ino and pp.Po_NO=?";
            PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
            stmt.setString(1, PO_inv.getValue());
            ResultSet rs = stmt.executeQuery();
            late = "NA";
            while (rs.next()) {
                late = "NA";
                if (rs.getString(4).equals("0")) {
                    try {
                        late = Utilities.Date.beforeOrAfter(rs.getString(6)) == 0 ? "Not Late" : "Late";
                        users2.add(new AnalysisDT2(rs.getString(1), rs.getString(2), rs.getString(3), "No", rs.getString(5), rs.getString(6), late));
                    } catch (ParseException ex) {
                        Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    users2.add(new AnalysisDT2(rs.getString(1), rs.getString(2), rs.getString(3), "Yes", rs.getString(5), rs.getString(6), late));
                }
            }
        } catch (SQLException e) {

        }
        table3.getColumns().clear();
        final TreeItem<AnalysisDT2> root1 = new RecursiveTreeItem<AnalysisDT2>(users2, RecursiveTreeObject::getChildren);
        table3.getColumns().setAll(subjec, cod, attende, atte, attender, attendety, attendetyr);
        table3.setRoot(root1);
        table3.setShowRoot(false);
        filter_inv.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            table3.setPredicate((TreeItem<AnalysisDT2> t) -> {
                Boolean flag = t.getValue().ino.getValue().toUpperCase().contains(newValue.toUpperCase()) || t.getValue().latey.getValue().toUpperCase().contains(newValue.toUpperCase()) || t.getValue().paid.getValue().toUpperCase().contains(newValue.toUpperCase());

                return flag;
            });
        });

    }
}
