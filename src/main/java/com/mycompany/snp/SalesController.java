/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.snp;

import PdfGeneration.pdfInvoice;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.jfoenix.controls.JFXTreeTableColumn;
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
import javafx.scene.control.Label;
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
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.util.HashMap;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import static javafx.scene.input.MouseEvent.MOUSE_PRESSED;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Dylan,Abhishek
 */
public class SalesController implements Initializable {

    public static boolean SD[] = {false, false, false, false, false, false};
    static boolean tock = true;
    @FXML
    private NumberAxis yaxis_lc2;
    @FXML
    private CategoryAxis xaxis_lc1;
    @FXML
    private JFXComboBox<String> enq_year2;
    @FXML
    private NumberAxis yaxis_bc2;
    @FXML
    private CategoryAxis xaxis_bc1;
    @FXML
    private NumberAxis yaxis_pjlc;
    @FXML
    private CategoryAxis xaxis_pjlc;
    @FXML
    private NumberAxis yaxis_pjbc;
    @FXML
    private CategoryAxis xaxis_pjbc;
    @FXML
    private Label edit_comp;
    @FXML
    private Label save_comp;
    @FXML
    private JFXComboBox<String> enq_year21;
    @FXML
    private JFXTextField compgst;
    @FXML
    private NumberAxis yaxis_inv;
    @FXML
    private CategoryAxis xaxis_inv;
    @FXML
    private JFXTextField mycomp_name1;
    @FXML
    private JFXTextField mycomp_code1;
    @FXML
    private JFXTextField mycomp_ph1;
    @FXML
    private JFXTextField mycomp_ph2;
    @FXML
    private JFXTextField mycomp_ph11;
    @FXML
    private JFXToggleButton projcomp;

    public void enqpane() {

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
        SalesDraw.close();
        SalesDraw.toBack();

    }

    void qoutpane() {
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
        try {
            QnoBox.getItems().clear();
            String sql = "SELECT `Qno` FROM `qoutation` WHERE 1";
            PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                QnoBox.getItems().add(rs.getString(1));
                System.out.println(rs.getString(1));
            }
        } catch (Exception e) {

        }
        SalesDraw.close();
        SalesDraw.toBack();

    }

    void invpane() {
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

        try {

            String sql1 = "SELECT PjNo FROM `product` WHERE 1 ";
            PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql1);
            ResultSet rs = stmt.executeQuery();
            inv_pno.getItems().clear();
            while (rs.next()) {
                int a = rs.getInt("PjNo");
                inv_pno.getItems().add(a);
                SD[5] = false;
            }
            sql1 = "SELECT Ino FROM `invoice` WHERE 1 ";
            stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql1);
            rs = stmt.executeQuery();
            inv_invbox.getItems().clear();
            while (rs.next()) {
                String a = rs.getString(1);
                inv_invbox.getItems().add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        SalesDraw.close();
        SalesDraw.toBack();

    }

    void newpopane() {
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
        try {
            QnoBox1.getItems().clear();
            String sql = "SELECT `Qno` FROM `qoutation` WHERE 1";
            PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                QnoBox1.getItems().add(rs.getString(1));
                System.out.println(rs.getString(1));
            }
        } catch (Exception e) {

        }
        SalesDraw.close();
        SalesDraw.toBack();

    }

    void dashpane() {
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
        SalesDraw.close();
        SalesDraw.toBack();

    }

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
    @FXML
    private Label pno_tick;
    @FXML
    private JFXComboBox<String> inv_invbox;
    @FXML
    private Label inv_tick;
    @FXML
    private Label pencilinv;
    @FXML
    private Label plusinv;

    @FXML
    private JFXComboBox<String> action;

    @FXML
    private JFXTreeTableView<AnalysisDT> enq_tables;

    @FXML
    private JFXTextField enq_filter;

    @FXML
    private BarChart<?, ?> enq_bar;

    @FXML
    private PieChart enq_pie;

    @FXML
    private LineChart<?, ?> enq_line;

    @FXML
    private Label enq_label;

    @FXML
    private JFXComboBox<String> action1;

    @FXML
    private JFXTreeTableView<AnalysisDT1> qno_tables;

    @FXML
    private JFXTextField qno_filter;

    @FXML
    private Label qno_label;

    @FXML
    private LineChart<?, ?> qno_line;

    @FXML
    private PieChart qno_pie;

    @FXML
    private BarChart<?, ?> qno_bar;

    @FXML
    private JFXComboBox<String> action11;

    @FXML
    private JFXTreeTableView<AnalysisDT2> proj_table;

    @FXML
    private JFXTextField project_filter;

    @FXML
    private Label project_label;

    @FXML
    private LineChart<?, ?> proj_line;

    @FXML
    private PieChart proj_pie;

    @FXML
    private BarChart<?, ?> proj_bar;

    @FXML
    private JFXComboBox<String> action111;

    @FXML
    private JFXTreeTableView<AnalysisDT3> inv_tables;

    @FXML
    private JFXTextField inv_filter;

    @FXML
    private Label inv_label;

    @FXML
    private LineChart<?, ?> inv_line;

    @FXML
    private PieChart inv_pie;

    @FXML
    private JFXTextField mycomp_name;

    @FXML
    private JFXComboBox<String> mycmp_box;

    @FXML
    private JFXTextField mycomp_ph;

    @FXML
    private JFXTextArea mycomp_add;
    @FXML
    private NumberAxis yaxis_lc;
    @FXML
    private CategoryAxis xaxis_lc;
    @FXML
    private JFXComboBox<String> enq_year;
    @FXML
    private NumberAxis yaxis_bc;
    @FXML
    private CategoryAxis xaxis_bc;
    @FXML
    private JFXComboBox<String> enq_year1;

    @FXML
    private void edit_in_comp(MouseEvent event) {
        // now git pls no chutiyapa
        //save_comp
        //edit_comp
        save_comp.setDisable(false);
        save_comp.setVisible(true);
        System.out.println("fdsdfsdfsfeuglfiugoiufgliuegflsiuefg;soifgs;oi");
        mycomp_name.setEditable(true);
        mycomp_code1.setEditable(true);
        mycomp_ph.setEditable(true);
        mycomp_add.setEditable(true);
        compgst.setEditable(true);

        edit_comp.setVisible(false);
        edit_comp.setDisable(true);
        save_comp.setDisable(false);
        save_comp.setVisible(true);

        mycomp_name.setEditable(true);
        // mycomp_code.setEditable(true);
        mycomp_ph.setEditable(true);
        mycomp_add.setEditable(true);
        compgst.setEditable(true);
        mycomp_name1.setEditable(true);
        mycomp_code1.setEditable(true);
        mycomp_ph1.setEditable(true);
        mycomp_ph11.setEditable(true);
        mycomp_ph2.setEditable(true);

    }

    @FXML
    private void save_in_comp(MouseEvent event) {

        try {

            if (mycomp_name.getText().trim().equals("") || mycomp_add.getText().trim().equals("") || mycomp_name1.getText().trim().equals("") || mycomp_ph.getText().trim().equals("")
                    || mycomp_ph2.getText().trim().equals("") || mycomp_ph11.getText().trim().equals("") || mycomp_code1.getText().trim().equals("") || mycomp_ph1.getText().trim().equals("") || compgst.getText().trim().equals("")) {
            } else {
                try {

                    String sql2 = "DELETE FROM `company` WHERE CmpName = ?";
                    PreparedStatement ps = com.mycompany.snp.MainApp.conn.prepareStatement(sql2);
                    ps.setString(1, mycomp_name.getText().trim());
                    ps.executeUpdate();
                    String sql = "INSERT INTO `company`(`CmpName`, `Address`, `Phone`, `GST`, `Fax`, `CompRegNo`, `GSTRegNo`, `Email`, `Website`) VALUES "
                            + "(?,?,?,?,?,?,?,?,?)";
                    ps = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
                    ps.setString(1, mycomp_name.getText().trim());
                    ps.setString(2, mycomp_add.getText().trim());
                    ps.setString(3, mycomp_ph.getText().trim());
                    ps.setFloat(4, Float.valueOf(compgst.getText()));
                    ps.setString(5, mycomp_name1.getText().trim());
                    ps.setString(6, mycomp_ph2.getText().trim());
                    ps.setString(7, mycomp_ph11.getText().trim());
                    ps.setString(8, mycomp_code1.getText().trim());
                    ps.setString(9, mycomp_ph1.getText().trim());

                    ps.executeUpdate();
                    edit_comp.setVisible(true);
                    edit_comp.setDisable(false);
                    save_comp.setDisable(true);
                    save_comp.setVisible(false);
                    mycomp_name.setEditable(false);
                    mycomp_add.setEditable(false);
                    mycomp_ph.setEditable(false);
                    compgst.setEditable(false);
                    mycomp_name1.setEditable(false);
                    mycomp_ph2.setEditable(false);
                    mycomp_ph11.setEditable(false);
                    mycomp_code1.setEditable(false);
                    mycomp_ph1.setEditable(false);

                } catch (SQLException ex) {
                    Utilities.AlertBox.showErrorMessage(ex);
                    Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (Exception e) {
            Utilities.AlertBox.showErrorMessage(e);
            Utilities.AlertBox.notificationWarn("Error", "You seem to have missed a field");
        }

    }

    @FXML
    private void Gen_Invoice(MouseEvent event) {
        if (Utilities.AlertBox.alertoption("Invoice PDF Generation", "Are you sure you want to generate the PDF file for this Invoice?", "Note that once the pdf is generated we assume that the generated PDF"
                + " file is sent to the customer")) {
            if (Invoice_Save_InvPane()) {
                if (inv_cmp.getText().toLowerCase().equals("steels")) {
                    try {
                        HashMap<String, Object> hm = new HashMap<String, Object>();
                        hm.put("Invoice Number", inv_no.getText());
                        hm.put("Date", Utilities.Date.Date());
                        hm.put("Salesperson", inv_sp.getText().trim());
                        hm.put("Termofpay", inv_tum.getText().trim());
                        hm.put("ProjectNo", Integer.valueOf(combopno).toString());
                        hm.put("POno", inv_po.getText());
                        String inv_to1[] = inv_to.getText().split("\\r?\\n");
                        hm.put("toAddress", inv_to1);
                        hm.put("Total", Double.valueOf(inv_total.getText()));
                        hm.put("GST", Float.valueOf(inv_gst.getText()));
                        ObservableList<Person3> trc;
                        trc = FXCollections.observableArrayList(inv_newtable.getItems());
                        hm.put("TableItems", trc);
                        new PdfGeneration.pdfInvoice(hm);
                    } catch (Exception e) {

                        Utilities.AlertBox.showErrorMessage(e);

                    }
                }
            }

        }
    }

    boolean Invoice_Save_InvPane() {
        try {
            if (inv_sp.getText().isEmpty() || inv_tum.getText().isEmpty() || inv_acc.getText().isEmpty()) {
                Utilities.AlertBox.notificationWarn("Error", "Some of the fields seem to be empty");
                return false;
            } else {

                try {

                    String sql = "DELETE FROM `invoice` WHERE INo=?";

                    PreparedStatement ps;
                    ps = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
                    ps.setString(1, inv_no.getText());
                    ps.executeUpdate();

                    String ql = "INSERT INTO `invoice`(`INo`, `Total_amt`, `Salesperson`, `Acc No`, `Termofpay`, `addedgst`) VALUES (?,?,?,?,?,?)";
                    ps = com.mycompany.snp.MainApp.conn.prepareStatement(ql);
                    ps.setString(1, inv_no.getText());
                    ps.setDouble(2, Double.valueOf(inv_total.getText()));
                    //ps.setString(3,.getValue());//pdf generation date will be used.

                    ps.setString(3, inv_sp.getText().trim());
                    ps.setString(4, inv_acc.getText().trim());
                    ps.setString(5, inv_tum.getText().trim());
                    ps.setFloat(6, Float.valueOf(inv_gst.getText()));
                    ps.executeUpdate();

                    String sl = "INSERT INTO `pirel`(`PjNo`, `INo`) VALUES (?,?)";
                    ps = com.mycompany.snp.MainApp.conn.prepareStatement(sl);
                    ps.setString(2, inv_no.getText());
                    ps.setInt(1, combopno);
                    ps.executeUpdate();
                    ObservableList<Person3> trc;
                    trc = FXCollections.observableArrayList(inv_newtable.getItems());
                    trc.add(new Person3("", "", "", "", ""));
                    int i = 0;
                    System.out.println("Hello");
                    while (i < 20) {
                        Person3 pe = trc.get(i);
                        if (pe.getItemNo().getText().trim().equalsIgnoreCase("")) {
                            System.out.println("HelloOut");
                            break;
                        } else {
                            /* System.out.print(p.getFirstName().getText()+"\t");
                        System.out.print(p.getLastName().getText()+"\t");
                        System.out.print(p.getEmail().getText()+"\t");
                        System.out.print(p.getRemark().getText()+"\t");
                        System.out.println(p.getTotal().getText()+"\t");
                             */

                            String d = pe.getItemNo().getText();
                            String q = pe.getDes().getText();
                            String r = pe.getQty().getText();
                            float s = Float.valueOf(pe.getUnitPrice().getText());
                            double h = Double.valueOf(pe.getTotal().getText());
                            String sq = "INSERT INTO `invoice_details`(`Item/No`, `Descr`, `Qty`, `UnitPrice`, `total`, `Invno`) VALUES (?,?,?,?,?,?)";
                            ps = com.mycompany.snp.MainApp.conn.prepareStatement(sq);
                            ps.setString(1, d);
                            ps.setString(2, q);
                            ps.setString(3, r);
                            ps.setFloat(4, s);
                            ps.setDouble(5, h);
                            ps.setString(6, inv_no.getText());
                            ps.executeUpdate();

                        }
                        i++;
                    }
                    Utilities.AlertBox.notificationInfo("Success", "Invoice details have been saved.");
                    return true;

                } catch (Exception e) {
                    Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, e);
                    Utilities.AlertBox.showErrorMessage(e);
                    Utilities.AlertBox.notificationWarn("Error", "Error in invoice generation");
                    return false;
                }

            }
        } catch (Exception e) {
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, e);
            Utilities.AlertBox.showErrorMessage(e);
            Utilities.AlertBox.notificationWarn("Error", "Some of the fields seem to be empty");
            return false;
        }

    }

    enum mths {
        JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC
    }

    public void fillcomp(String d) {
        try {

            String sql = "SELECT `CmpName`, `Address`, `Phone`, `GST`, `Fax`, `CompRegNo`, `GSTRegNo`, `Email`, `Website` FROM `company` WHERE CMPNAMe LIKE \"" + d + "%\" ";
            PreparedStatement ps = com.mycompany.snp.MainApp.conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                mycomp_name.setText(rs.getString(1));
                mycomp_add.setText(rs.getString(2));
                mycomp_ph.setText(rs.getString(3));
                compgst.setText(rs.getString(4));
                mycomp_name1.setText(rs.getString(5));
                mycomp_ph2.setText(rs.getString(6));
                mycomp_ph11.setText(rs.getString(7));
                mycomp_code1.setText(rs.getString(8));
                mycomp_ph1.setText(rs.getString(9));
                mycomp_name.setEditable(false);
                mycomp_add.setEditable(false);
                mycomp_ph.setEditable(false);
                compgst.setEditable(false);
                mycomp_name1.setEditable(false);
                mycomp_ph2.setEditable(false);
                mycomp_ph11.setEditable(false);
                mycomp_code1.setEditable(false);
                mycomp_ph1.setEditable(false);

            }

        } catch (SQLException ex) {
            Utilities.AlertBox.showErrorMessage(ex);
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
        oldPOPane.setDisable(false);
        oldPOPane.setVisible(true);
        InvoicePane.setDisable(true);
        InvoicePane.setVisible(false);
        newPOPane.setDisable(true);
        newPOPane.setVisible(false);
        newEqPane.setDisable(true);
        newEqPane.setVisible(false);

        inv_invbox.setDisable(true);
        inv_invbox.setVisible(false);
        inv_tick.setDisable(true);
        inv_tick.setVisible(false);
        pencilinv.setDisable(false);
        pencilinv.setVisible(true);
        plusinv.setDisable(true);
        plusinv.setVisible(false);

        cmp.getItems().add("Awin");
        cmp.getItems().add("Steels");
        mycmp_box.getItems().add("Awin");
        mycmp_box.getItems().add("Steel");
        try {
            String sql = "SELECT `Qno` FROM `qoutation` WHERE 1";
            PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                QnoBox.getItems().add(rs.getString(1));
                QnoBox1.getItems().add(rs.getString(1));
                System.out.println(rs.getString(1));

            }

            String sql1 = "SELECT PjNo FROM `product` WHERE 1 ";
            stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql1);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int a = rs.getInt("PjNo");
                inv_pno.getItems().add(a);
            }
            sql1 = "SELECT Ino FROM `invoice` WHERE 1 ";
            stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql1);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String a = rs.getString(1);
                inv_invbox.getItems().add(a);

            }

        } catch (Exception e) {

        }

        try {
            VBox box = FXMLLoader.load(getClass().getResource("/fxml/SalesDrawer.fxml"));
            System.out.println(2);
            SalesDraw.setSidePane(box);
            /*  for (Node node: box.getChildren()){
            if(node.getAccessibleText()!=null){
                node.addEventHandler(MouseEvent.MOUSE_CLICKED,(e) ->{
                    switch(node.getAccessibleText()){
                        case "Dashboard" :dashpane();SalesDraw.close(); 
      SalesDraw.toBack();break;
                            
                        case "Enquiry & Quotation" : enqpane();SalesDraw.close(); 
      SalesDraw.toBack();break;
                            
                        case "Edit & Revise Quotation" : qoutpane();SalesDraw.close(); 
      SalesDraw.toBack();break;
                            
                            
                        case "Purchase Order" :   newpopane();SalesDraw.close(); 
      SalesDraw.toBack();break;
                            
                        case "Invoice" :    invpane();SalesDraw.close(); 
      SalesDraw.toBack();break;
                    }
                });
            }
        }*/
        } catch (IOException ex) {
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(SalesHam);//for left arrow
        // HamburgerSlideCloseTransition transition = new HamburgerSlideCloseTransition(SalesHam);//For X Mark
        transition.setRate(-1);

        SalesHam.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();
            if (SalesDraw.isShown()) {
                SalesDraw.close();
                SalesDraw.toBack();
            } else {
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
        tock = true;
        mycmp_box.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String oldValue, String newValue) {

                fillcomp(newValue.toUpperCase());
            }
        });
        inv_total.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                inv_total.setText(newValue.replaceAll("[^\\d]", ""));
            } else {
                if (inv_total.getText().equals("")) {
                    inv_gst.setText("0");
                    inv_amt.setText("0");

                } else {

                    inv_gst.setText(String.valueOf(Math.round((Double.valueOf(inv_total.getText()) * (Float.valueOf(comp_inv_gst) / 100)) * 100d) / 100d));//Math.round(value * 100000d) / 100000d
                    inv_amt.setText(String.valueOf(Float.valueOf(inv_total.getText()) + Float.valueOf(inv_gst.getText())));
                }
            }
        });
        /**
         * inv_total.textProperty().addListener(new ChangeListener<String>() {
         *
         * @Override public void changed(ObservableValue<? extends String>
         * observable, String oldValue, String newValue) { if
         * (!newValue.matches("\\d*")) {
         * inv_total.setText(newValue.replaceAll("[^\\d]", "")); } } });
         */
        action.getItems().add("Decline Enquiries");
        action.getItems().add("Enquiries for which Quotations are not generated");
        action.getItems().add("Enquires for which Quotations are generated");
        action11.getItems().add("Completed Projects");
        action11.getItems().add("Projects yet to be completed");
        action11.getItems().add("Projects that have exceeded the estimated deadline");
        action.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String oldValue, String newValue) {

                if (newValue.equals("Declined Enquiries")) {
                    decline_enq();
                    enq_filter.clear();

                } else if (newValue.equals("Enquiries for which Quotations are not generated")) {
                    notquoted_enq();
                    enq_filter.clear();
                } else if (newValue.equals("Enquires for which Quotations are generated")) {
                    quoted_enq();
                    enq_filter.clear();
                }
            }
        });
        ResultSet rs;
        try {
            String suql = "select distinct Date_format (Date1, '%Y') FROM enquiry where 1";
            PreparedStatement ps;

            ps = com.mycompany.snp.MainApp.conn.prepareStatement(suql);
            rs = ps.executeQuery();
            while (rs.next()) {
                enq_year.getItems().add(rs.getString(1));

            }
            suql = "select distinct Date_format (Date, '%Y') FROM product where 1";

            ps = com.mycompany.snp.MainApp.conn.prepareStatement(suql);
            rs = ps.executeQuery();
            while (rs.next()) {
                enq_year2.getItems().add(rs.getString(1));

            }
            suql = "select distinct Date_format (Date, '%Y') FROM invoice where Date IS NOT NULL";

            ps = com.mycompany.snp.MainApp.conn.prepareStatement(suql);
            rs = ps.executeQuery();
            while (rs.next()) {
                enq_year21.getItems().add(rs.getString(1));

            }
        } catch (SQLException ex) {
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        action11.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String oldValue, String newValue) {

                if (newValue.equals("Completed Projects")) {
                    prj_completed();
                    project_filter.clear();

                } else if (newValue.equals("Projects yet to be completed")) {

                    prj_yetcomp();
                    project_filter.clear();
                } else if (newValue.equals("Projects that have exceeded the estimated deadline")) {
                    prj_xedead();
                    project_filter.clear();
                }
            }
        });
        action111.getItems().add("Invoices Generated but not paid.");
        action111.getItems().add("Invoices paid.");
        action111.getItems().add("Invoices that have not been generated yet.");
        action111.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String oldValue, String newValue) {

                if (newValue.equals("Invoices Generated but not paid.")) {
                    inv_gen_but_not_paid(0);
                    inv_filter.clear();

                } else if (newValue.equals("Invoices paid.")) {

                    inv_gen_but_not_paid(1);
                    inv_filter.clear();
                } else if (newValue.equals("Invoices that have not been generated yet.")) {

                    inv_gen_but_not_paid(2);
                    inv_filter.clear();
                }
            }
        });

        enq_year.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String oldValue, String newValue) {

                enq_line(newValue);
                enq_pie(newValue);
                enq_bar(newValue);

            }
        });

        action1.getItems().add("Accepted by customer");
        action1.getItems().add("Pending quotation");
        action1.getItems().add("Quotation waiting for customer's approval/ Project not started");

        action1.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String oldValue, String newValue) {

                if (newValue.equals("Accepted by customer")) {
                    accepted_quots();
                    qno_filter.clear();

                } else if (newValue.equals("Pending quotation")) {
                    notsent_qno();
                    qno_filter.clear();
                } else if (newValue.equals("Quotation waiting for customer's approval/ Project not started")) {
                    pending_qno();
                    qno_filter.clear();
                }
            }
        });

        try {

            String suql = "SELECT DISTINCT Substring(Sentdate,1,4) FROM `qoutation` WHERE Sentdate is NOT null";
            PreparedStatement st;
            st = com.mycompany.snp.MainApp.conn.prepareStatement(suql);
            rs = st.executeQuery();
            while (rs.next()) {
                enq_year1.getItems().add(rs.getString(1));
            }

        } catch (Exception e) {
            System.out.println(539);
            Utilities.AlertBox.showErrorMessage(e);
        }

        //label in piechart
        enq_year1.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String oldValue, String newValue) {

                qno_line(newValue);
                qno_pie(newValue);
                qno_bar(newValue);

            }

        });
        enq_year2.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String oldValue, String newValue) {

                prj_lc(newValue);
                // prj_pie(newValue);
                prj_bc(newValue);

            }

        });
        enq_year21.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String oldValue, String newValue) {

                inv_lc_method(newValue);
                inv_pie_method(newValue);

            }

        });

        action.setValue("Declined Enquiries");
        action1.setValue("Accepted by customer");
        enq_year1.setValue(String.valueOf(LocalDate.now().getYear()));
        enq_year21.setValue(String.valueOf(LocalDate.now().getYear()));
        enq_year.setValue(String.valueOf(LocalDate.now().getYear()));
        enq_year2.setValue(String.valueOf(LocalDate.now().getYear()));
        action11.setValue("Completed Projects");
        action111.setValue("Invoices Generated but not paid.");
        threadtock();
    }

    public void qno_bar(String d) {
        int arr[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        try {
            qno_bar.getData().add(new XYChart.Series(FXCollections.observableArrayList(new XYChart.Data("", 0))));
            qno_bar.getData().clear();
            xaxis_bc1.getCategories().clear();
            XYChart.Series dataSeries1 = new XYChart.Series();
            XYChart.Series dataSeries2 = new XYChart.Series();
            qno_bar.setTitle("Quotations Sent Monthly");
            dataSeries1.setName("Awin");
            dataSeries2.setName("Steels");
            xaxis_bc1.setLabel("Month");
            yaxis_bc2.setLabel("Quotations");
            yaxis_bc2.setSide(Side.LEFT);
            xaxis_bc1.setSide(Side.BOTTOM);
            dataSeries1.getData().clear();
            xaxis_bc1.setAnimated(true);
            yaxis_bc2.setAnimated(true);
            qno_bar.setAnimated(true);

            xaxis_bc1.setAnimated(false);
            yaxis_bc2.setAnimated(true);
            qno_bar.setAnimated(true);
            xaxis_bc1.getCategories().addAll("Awin", "Steels");
            String sql = "SELECT COUNT(*),substring(Sentdate,6,2) as a FROM qoutation WHERE substring(Qno,4,2) LIKE \"AE\" AND substring(Sentdate,1,4) LIKE '" + d + "%' GROUP BY a";
            Statement stmt = com.mycompany.snp.MainApp.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getString(1));
                for (mths k : mths.values()) {
                    if ((rs.getString(2).equals("0" + String.valueOf(k.ordinal() + 1))) || (rs.getString(2).equals(String.valueOf(k.ordinal() + 1)))) {
                        //  s.getData().add(new XYChart.Data(k.toString(),rs.getInt("z"))); 
                        arr[k.ordinal()] += rs.getInt(1);
                    }
                }

            }
            for (mths k : mths.values()) {
                dataSeries1.getData().add(new XYChart.Data(k.toString(), arr[k.ordinal()]));
                System.out.println(arr[k.ordinal()] + " " + k);
            }
            int[] arr2 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            sql = "SELECT COUNT(*),substring(Sentdate,6,2) as a FROM qoutation WHERE substring(Qno,4,2) LIKE \"SC\" AND substring(Sentdate,1,4) LIKE '" + d + "%' GROUP BY a";
            stmt = com.mycompany.snp.MainApp.conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getString(1));
                for (mths k : mths.values()) {
                    if ((rs.getString(2).equals("0" + String.valueOf(k.ordinal() + 1))) || (rs.getString(2).equals(String.valueOf(k.ordinal() + 1)))) {
                        //  s.getData().add(new XYChart.Data(k.toString(),rs.getInt("z"))); 
                        arr2[k.ordinal()] += rs.getInt(1);
                    }
                }

            }
            for (mths k : mths.values()) {
                dataSeries2.getData().add(new XYChart.Data(k.toString(), arr2[k.ordinal()]));
                //System.out.println(arr[k.ordinal()]+" "+k);
            }

            qno_bar.getData().addAll(dataSeries1, dataSeries2);

        } catch (SQLException ex) {
            Utilities.AlertBox.showErrorMessage(ex);
        }
    }

    public void qno_line(String d) {
        xaxis_lc1.setLabel("Months");
        yaxis_lc2.setLabel("Number of Quotations");
        yaxis_lc2.setSide(Side.LEFT);
        xaxis_lc1.setSide(Side.BOTTOM);
        xaxis_lc1.getCategories().clear();
        qno_line.getData().clear();
        mths m;
        XYChart.Series s = new XYChart.Series();
        XYChart.Series s1 = new XYChart.Series();
        s1.getData().clear();
        s.getData().clear();
        s.setName("Quotations sent");
        s1.setName("Quotation accepted");
        xaxis_lc1.setAnimated(false);
        yaxis_lc2.setAnimated(true);
        qno_line.setAnimated(true);
        int arr[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        ResultSet rs;
        try {
            /* for (mths k : mths.values()){
                                       
                                       s.getData().add(new XYChart.Data(k.toString(),0));
                           s1.getData().add(new XYChart.Data(k.toString(),0));
                           }*/
            String suql = "SELECT substring(Sentdate,6,2) as m,COUNT(*) FROM `qoutation` WHERE sent=1 and substring(Sentdate,1,4) LIKE '" + d + "%' GROUP BY m";
            PreparedStatement st;
            st = com.mycompany.snp.MainApp.conn.prepareStatement(suql);

            rs = st.executeQuery();
            int c = 0, l = 0;
            while (rs.next()) {
                System.out.println(rs.getString(1));
                for (mths k : mths.values()) {
                    if ((rs.getString(1).equals("0" + String.valueOf(k.ordinal() + 1))) || (rs.getString(1).equals(String.valueOf(k.ordinal() + 1)))) {
                        //  s.getData().add(new XYChart.Data(k.toString(),rs.getInt("z"))); 
                        arr[k.ordinal()] += rs.getInt(2);
                    }
                }

            }
            for (mths k : mths.values()) {
                s.getData().add(new XYChart.Data(k.toString(), arr[k.ordinal()]));
                System.out.println(arr[k.ordinal()] + " " + k);
            }
            int arr1[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            suql = "SELECT substring(Sentdate,6,2) as m,COUNT(*) FROM `qoutation` q WHERE sent=1 AND  EXISTS(SELECT * FROM qprel qp WHERE\n"
                    + "qp.Qno=q.Qno) AND substring(Sentdate,1,4) LIKE '" + d + "%'  GROUP BY m";

            Statement st1 = com.mycompany.snp.MainApp.conn.createStatement();

            rs = st1.executeQuery(suql);

            while (rs.next()) {
                //      System.out.println(rs.getString(1));
                for (mths k : mths.values()) {
                    if ((rs.getString(1).equals("0" + String.valueOf(k.ordinal() + 1))) || (rs.getString(1).equals(String.valueOf(k.ordinal() + 1)))) {
                        // s1.getData().add(new XYChart.Data(k.toString(),rs.getInt("z"))); 
                        arr1[k.ordinal()] += rs.getInt(2);
                    }
                }

            }
            for (mths k : mths.values()) {
                s1.getData().add(new XYChart.Data(k.toString(), arr1[k.ordinal()]));
                System.out.println(arr1[k.ordinal()] + " " + k);
            }

            qno_line.getData().addAll(s, s1);

        } catch (Exception e) {
            Utilities.AlertBox.showErrorMessage(e);
        }
    }

    public void qno_pie(String d) {
        ResultSet rs;
        try {

            String suql = "SELECT COUNT(*) from qprel qp,product p WHERE qp.PjNo=p.PjNo and p.Date LIKE '" + d + "%'";
            PreparedStatement st;
            st = com.mycompany.snp.MainApp.conn.prepareStatement(suql);
            rs = st.executeQuery();
            rs.next();
            int accep_customno = rs.getInt(1);

            String seql = "SELECT COUNT(*) FROM `qoutation` WHERE sent=0 AND Substring(times,1,4) LIKE '" + d + "%'";
            st = com.mycompany.snp.MainApp.conn.prepareStatement(seql);
            rs = st.executeQuery();
            rs.next();
            int notsent = rs.getInt(1);

            String seql2 = "SELECT count(*) FROM `qoutation` q WHERE sent=1 AND Substring(times,1,4) LIKE '" + d + "%' and NOT EXISTS(SELECT * FROM `qprel` qp where q.Qno=qp.Qno)";
            st = com.mycompany.snp.MainApp.conn.prepareStatement(seql2);
            rs = st.executeQuery();
            rs.next();
            int sent_botnoaccept = rs.getInt(1);

            ObservableList<PieChart.Data> pc = FXCollections.observableArrayList(
                    new PieChart.Data("Accepted quotations", accep_customno),
                    new PieChart.Data("Not sent quotations", notsent),
                    new PieChart.Data("Sent not accepted", sent_botnoaccept));
            qno_pie.setData(pc);
            qno_pie.setLegendVisible(true);

        } catch (Exception e) {
            System.out.println(539);
            Utilities.AlertBox.showErrorMessage(e);
        }
    }

    public void pending_qno() {
        JFXTreeTableColumn<AnalysisDT1, String> subjec = new JFXTreeTableColumn<>("Quotation Number");
        subjec.setPrefWidth(200);
        subjec.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT1, String> param) -> param.getValue().getValue().qo);

        JFXTreeTableColumn<AnalysisDT1, String> cod = new JFXTreeTableColumn<>("Quotation Date");
        cod.setPrefWidth(200);
        cod.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT1, String> param) -> param.getValue().getValue().dates);

        JFXTreeTableColumn<AnalysisDT1, String> attende = new JFXTreeTableColumn<>("Enquiry Number");
        attende.setPrefWidth(200);
        attende.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT1, String> param) -> param.getValue().getValue().eno);

        JFXTreeTableColumn<AnalysisDT1, String> need11 = new JFXTreeTableColumn<>("Company");
        need11.setPrefWidth(200);
        need11.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT1, String> param) -> param.getValue().getValue().company);
        JFXTreeTableColumn<AnalysisDT1, String> need22 = new JFXTreeTableColumn<>("Subject");
        need22.setPrefWidth(200);
        need22.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT1, String> param) -> param.getValue().getValue().sub);
        JFXTreeTableColumn<AnalysisDT1, String> need33 = new JFXTreeTableColumn<>("CustomerName");
        need33.setPrefWidth(200);
        need33.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT1, String> param) -> param.getValue().getValue().custname);

        ObservableList<AnalysisDT1> users1 = FXCollections.observableArrayList();

        try {

            String suql = "Select q.qno,q.Sentdate,eq.Eno,eq.Cmpname,e.Subject,c.Name from qoutation q,eqrel eq,enquiry e,customer c "
                    + "where q.sent=1 and not exists(Select * from qprel qp where qp.Qno=q.Qno) "
                    + "and q.Qno=eq.Qno and eq.CID=c.CID and eq.Cmpname=e.Cmpname and eq.cid=e.cid and e.date1=eq.date1;";

            PreparedStatement st;
            st = com.mycompany.snp.MainApp.conn.prepareStatement(suql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                users1.add(new AnalysisDT1(rs.getString(1), rs.getDate(2).toString(), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
            rs.previous();
            qno_label.setText("Pending customer approval /project not started : " + rs.getRow());
            final TreeItem<AnalysisDT1> root = new RecursiveTreeItem<AnalysisDT1>(users1, RecursiveTreeObject::getChildren);
            qno_tables.getColumns().clear();
            qno_tables.getColumns().setAll(subjec, cod, attende, need11, need22, need33);
            qno_tables.setRoot(root);
            qno_tables.setShowRoot(false);

            qno_filter.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                qno_tables.setPredicate((TreeItem<AnalysisDT1> t) -> {
                    Boolean flag = t.getValue().qo.getValue().toUpperCase().contains(newValue.toUpperCase()) || t.getValue().dates.getValue().toUpperCase().contains(newValue.toUpperCase()) || t.getValue().eno.getValue().contains(newValue) || t.getValue().company.getValue().toUpperCase().contains(newValue.toUpperCase()) || t.getValue().sub.getValue().toUpperCase().contains(newValue.toUpperCase()) || t.getValue().custname.getValue().toUpperCase().contains(newValue.toUpperCase());
                    return flag;
                });
            });
        } catch (Exception ex) {
            Utilities.AlertBox.showErrorMessage(ex);
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void notsent_qno() {
        JFXTreeTableColumn<AnalysisDT1, String> subjec = new JFXTreeTableColumn<>("Quotation Number");
        subjec.setPrefWidth(200);
        subjec.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT1, String> param) -> param.getValue().getValue().qo);

        JFXTreeTableColumn<AnalysisDT1, String> attende = new JFXTreeTableColumn<>("Enquiry Number");
        attende.setPrefWidth(200);
        attende.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT1, String> param) -> param.getValue().getValue().eno);

        JFXTreeTableColumn<AnalysisDT1, String> need11 = new JFXTreeTableColumn<>("Company");
        need11.setPrefWidth(200);
        need11.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT1, String> param) -> param.getValue().getValue().company);
        JFXTreeTableColumn<AnalysisDT1, String> need22 = new JFXTreeTableColumn<>("Subject");
        need22.setPrefWidth(200);
        need22.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT1, String> param) -> param.getValue().getValue().sub);
        JFXTreeTableColumn<AnalysisDT1, String> need33 = new JFXTreeTableColumn<>("CustomerName");
        need33.setPrefWidth(200);
        need33.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT1, String> param) -> param.getValue().getValue().custname);

        ObservableList<AnalysisDT1> users1 = FXCollections.observableArrayList();

        try {

            String suql = "Select q.qno,eq.eno,eq.Cmpname,e.Subject,c.name from qoutation q,enquiry e,"
                    + "customer c,eqrel eq where q.sent=0 and q.Qno=eq.QNo and eq.CID=c.CID and eq.Date1=e.Date1 and "
                    + "eq.Eno=e.Eqno and eq.Cmpname=e.Cmpname and c.CID=e.CID";

            PreparedStatement st;
            st = com.mycompany.snp.MainApp.conn.prepareStatement(suql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                users1.add(new AnalysisDT1(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            rs.previous();
            qno_label.setText("Quotations not sent : " + rs.getRow());
            final TreeItem<AnalysisDT1> root = new RecursiveTreeItem<AnalysisDT1>(users1, RecursiveTreeObject::getChildren);
            qno_tables.getColumns().clear();
            qno_tables.getColumns().setAll(subjec, attende, need11, need22, need33);
            qno_tables.setRoot(root);
            qno_tables.setShowRoot(false);

            qno_filter.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                qno_tables.setPredicate((TreeItem<AnalysisDT1> t) -> {
                    Boolean flag = t.getValue().qo.getValue().toUpperCase().contains(newValue.toUpperCase()) || t.getValue().eno.getValue().contains(newValue) || t.getValue().company.getValue().toUpperCase().contains(newValue.toUpperCase()) || t.getValue().sub.getValue().toUpperCase().contains(newValue.toUpperCase()) || t.getValue().custname.getValue().toUpperCase().contains(newValue.toUpperCase());
                    return flag;
                });
            });
        } catch (Exception ex) {
            Utilities.AlertBox.showErrorMessage(ex);
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void accepted_quots() {

        JFXTreeTableColumn<AnalysisDT1, String> subjec = new JFXTreeTableColumn<>("Quotation Number");
        subjec.setPrefWidth(200);
        subjec.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT1, String> param) -> param.getValue().getValue().qo);

        JFXTreeTableColumn<AnalysisDT1, String> cod = new JFXTreeTableColumn<>("Quotation Date");
        cod.setPrefWidth(200);
        cod.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT1, String> param) -> param.getValue().getValue().dates);

        JFXTreeTableColumn<AnalysisDT1, String> attende = new JFXTreeTableColumn<>("Enquiry Number");
        attende.setPrefWidth(200);
        attende.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT1, String> param) -> param.getValue().getValue().eno);

        JFXTreeTableColumn<AnalysisDT1, String> need11 = new JFXTreeTableColumn<>("Company");
        need11.setPrefWidth(200);
        need11.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT1, String> param) -> param.getValue().getValue().company);
        JFXTreeTableColumn<AnalysisDT1, String> need22 = new JFXTreeTableColumn<>("Subject");
        need22.setPrefWidth(200);
        need22.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT1, String> param) -> param.getValue().getValue().sub);
        JFXTreeTableColumn<AnalysisDT1, String> need33 = new JFXTreeTableColumn<>("CustomerName");
        need33.setPrefWidth(200);
        need33.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT1, String> param) -> param.getValue().getValue().custname);

        ObservableList<AnalysisDT1> users1 = FXCollections.observableArrayList();

        try {

            String suql = "Select q.qno,q.Sentdate,eq.Eno,eq.Cmpname,e.Subject,c.Name from qoutation q,qprel qp,"
                    + "eqrel eq,customer c,enquiry e where q.qno=qp.qno and "
                    + "q.Qno=eq.QNo and eq.CID=c.CID and c.CID=e.CID";

            PreparedStatement st;
            st = com.mycompany.snp.MainApp.conn.prepareStatement(suql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                users1.add(new AnalysisDT1(rs.getString(1), rs.getDate(2).toString(), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
            rs.previous();
            qno_label.setText("Accepted Quotations : " + rs.getRow());
            final TreeItem<AnalysisDT1> root = new RecursiveTreeItem<AnalysisDT1>(users1, RecursiveTreeObject::getChildren);
            qno_tables.getColumns().clear();
            qno_tables.getColumns().setAll(subjec, cod, attende, need11, need22, need33);
            qno_tables.setRoot(root);
            qno_tables.setShowRoot(false);

            qno_filter.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                qno_tables.setPredicate((TreeItem<AnalysisDT1> t) -> {
                    Boolean flag = t.getValue().qo.getValue().toUpperCase().contains(newValue.toUpperCase()) || t.getValue().dates.getValue().toUpperCase().contains(newValue.toUpperCase()) || t.getValue().eno.getValue().contains(newValue) || t.getValue().company.getValue().toUpperCase().contains(newValue.toUpperCase()) || t.getValue().sub.getValue().toUpperCase().contains(newValue.toUpperCase()) || t.getValue().custname.getValue().toUpperCase().contains(newValue.toUpperCase());
                    return flag;
                });
            });
        } catch (Exception ex) {
            Utilities.AlertBox.showErrorMessage(ex);
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void enq_pie(String d) {
        ResultSet rs;
        try {

            String suql = "SELECT COUNT(*) FROM `enquirybin` WHERE Date1 LIKE '" + d + "%'";
            PreparedStatement st;
            st = com.mycompany.snp.MainApp.conn.prepareStatement(suql);
            rs = st.executeQuery();
            rs.next();
            int dec_enqno = rs.getInt(1);
            //  System.out.println("FIRST QUERY FINE");

            String seql = "SELECT count(*) FROM `enquiry` e WHERE NOT EXISTS(SELECT * FROM `eqrel` eq WHERE eq.eno=e.eqno and e.cid=eq.cid) and e.Date1 LIKE '" + d + "%'";
            st = com.mycompany.snp.MainApp.conn.prepareStatement(seql);
            rs = st.executeQuery();
            rs.next();
            int enq_noqo = rs.getInt(1);
            // System.out.println("SECOND QUERY FINE");
            String seql2 = "SELECT count(*) FROM `enquiry` e WHERE EXISTS(SELECT * FROM `eqrel` eq WHERE eq.eno=e.eqno and eq.cid=e.cid) and e.Date1 LIKE '" + d + "%'";
            st = com.mycompany.snp.MainApp.conn.prepareStatement(seql2);
            rs = st.executeQuery();
            rs.next();
            int enq_qo = rs.getInt(1);
            System.out.println("THIRD QUERY FINE");
            ObservableList<PieChart.Data> pcd = FXCollections.observableArrayList(
                    new PieChart.Data("Declined Enquiries", dec_enqno),
                    new PieChart.Data("Qoutationless Enquiries", enq_noqo),
                    new PieChart.Data("Qouted Enquiries", enq_qo));
            enq_pie.setData(pcd);
            enq_pie.setLegendVisible(true);

        } catch (Exception e) {
            System.out.println(539);
            Utilities.AlertBox.showErrorMessage(e);
        }
    }

    public void enq_bar(String d) {
        try {
            enq_bar.getData().add(new XYChart.Series(FXCollections.observableArrayList(new XYChart.Data("", 0))));
            enq_bar.getData().clear();
            xaxis_bc.getCategories().clear();
            XYChart.Series dataSeries1 = new XYChart.Series();
            XYChart.Series dataSeries2 = new XYChart.Series();
            enq_bar.setTitle("Declined Enquiries");
            dataSeries1.setName("Awin");
            dataSeries2.setName("Steels");
            xaxis_bc.setLabel("Reason");
            yaxis_bc.setLabel("Number of Declined Enquiries");
            yaxis_bc.setSide(Side.LEFT);
            xaxis_bc.setSide(Side.BOTTOM);
            dataSeries1.getData().clear();
            xaxis_bc.setAnimated(true);
            yaxis_bc.setAnimated(true);
            enq_bar.setAnimated(true);

            xaxis_bc.setAnimated(false);
            yaxis_bc.setAnimated(true);
            enq_bar.setAnimated(true);
            xaxis_bc.getCategories().addAll("Awin", "Steels");
            String sql = "SELECT q.`Reason`,count(*) FROM (Select * from enquirybin w where w.Date1 LIKE '" + d + "%' and cmpname='Awin') q group by q.Reason";
            Statement stmt = com.mycompany.snp.MainApp.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                dataSeries1.getData().add(new XYChart.Data(rs.getString(1), rs.getInt(2)));  //Declined enquiries with reason

            }
            sql = "SELECT q.`Reason`,count(*) FROM (Select * from enquirybin w where w.Date1 LIKE '" + d + "%' and cmpname='Steels') q group by q.Reason";
            stmt = com.mycompany.snp.MainApp.conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                dataSeries2.getData().add(new XYChart.Data(rs.getString(1), rs.getInt(2)));  //Declined enquiries with reason

            }

            enq_bar.getData().addAll(dataSeries1, dataSeries2);

        } catch (SQLException ex) {
            Utilities.AlertBox.showErrorMessage(ex);
        }

    }

    public void enq_line(String d) {
        xaxis_lc.setLabel("Months");
        yaxis_lc.setLabel("Number of Enquiries");
        yaxis_lc.setSide(Side.LEFT);
        xaxis_lc.setSide(Side.BOTTOM);
        xaxis_lc.getCategories().clear();
        enq_line.getData().clear();
        mths m;
        XYChart.Series s = new XYChart.Series();
        XYChart.Series s1 = new XYChart.Series();
        s1.getData().clear();
        s.getData().clear();
        s.setName("Declined Enquiries");
        s1.setName("Received Enquiries");
        xaxis_lc.setAnimated(false);
        yaxis_lc.setAnimated(true);
        enq_line.setAnimated(true);
        int arr[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        ResultSet rs;
        try {
            /* for (mths k : mths.values()){
                                       
                                       s.getData().add(new XYChart.Data(k.toString(),0));
                           s1.getData().add(new XYChart.Data(k.toString(),0));
                           }*/
            String suql = "SELECT substring(q.deldate,6,2) as m,count(*) as z FROM (Select * from enquirybin w where w.Date1 LIKE '" + d + "%') q group by substring(q.deldate,6,2)";
            PreparedStatement st;
            st = com.mycompany.snp.MainApp.conn.prepareStatement(suql);

            rs = st.executeQuery();
            int c = 0, l = 0;
            while (rs.next()) {
                System.out.println(rs.getString(1));
                for (mths k : mths.values()) {
                    if ((rs.getString(1).equals("0" + String.valueOf(k.ordinal() + 1))) || (rs.getString(1).equals(String.valueOf(k.ordinal() + 1)))) {
                        //  s.getData().add(new XYChart.Data(k.toString(),rs.getInt("z"))); 
                        arr[k.ordinal()] += rs.getInt(2);
                    }
                }

            }
            for (mths k : mths.values()) {
                s.getData().add(new XYChart.Data(k.toString(), arr[k.ordinal()]));
                System.out.println(arr[k.ordinal()] + " " + k);
            }
            int arr1[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            suql = "SELECT substring(q.date1,6,2) as m,count(*) as z FROM (Select * from enquiry w where w.Date1 LIKE '" + d + "%') q group by substring(q.date1,6,2) ";

            Statement st1 = com.mycompany.snp.MainApp.conn.createStatement();

            rs = st1.executeQuery(suql);

            while (rs.next()) {
                //      System.out.println(rs.getString(1));
                for (mths k : mths.values()) {
                    if ((rs.getString(1).equals("0" + String.valueOf(k.ordinal() + 1))) || (rs.getString(1).equals(String.valueOf(k.ordinal() + 1)))) {
                        // s1.getData().add(new XYChart.Data(k.toString(),rs.getInt("z"))); 
                        arr1[k.ordinal()] += rs.getInt(2);
                    }
                }

            }
            for (mths k : mths.values()) {
                s1.getData().add(new XYChart.Data(k.toString(), arr1[k.ordinal()]));
                System.out.println(arr1[k.ordinal()] + " " + k);
            }

            enq_line.getData().addAll(s, s1);

        } catch (Exception e) {
            Utilities.AlertBox.showErrorMessage(e);
        }

    }

    public void decline_enq() {
        JFXTreeTableColumn<AnalysisDT, String> subject = new JFXTreeTableColumn<>("Enquiry Number");
        subject.setPrefWidth(200);
        subject.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT, String> param) -> param.getValue().getValue().enqno);

        JFXTreeTableColumn<AnalysisDT, String> code = new JFXTreeTableColumn<>("Submission Date");
        code.setPrefWidth(200);
        code.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT, String> param) -> param.getValue().getValue().Subdate);

        JFXTreeTableColumn<AnalysisDT, String> attended = new JFXTreeTableColumn<>("Company Name");
        attended.setPrefWidth(200);
        attended.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT, String> param) -> param.getValue().getValue().comname);

        JFXTreeTableColumn<AnalysisDT, String> total = new JFXTreeTableColumn<>("Decline Date");
        total.setPrefWidth(200);
        total.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT, String> param) -> param.getValue().getValue().decdate);

        JFXTreeTableColumn<AnalysisDT, String> need1 = new JFXTreeTableColumn<>("Subject");
        need1.setPrefWidth(200);
        need1.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT, String> param) -> param.getValue().getValue().sub);
        JFXTreeTableColumn<AnalysisDT, String> need2 = new JFXTreeTableColumn<>("Customer Name");
        need2.setPrefWidth(200);
        need2.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT, String> param) -> param.getValue().getValue().cname);
        JFXTreeTableColumn<AnalysisDT, String> need3 = new JFXTreeTableColumn<>("Reason");
        need3.setPrefWidth(200);
        need3.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT, String> param) -> param.getValue().getValue().rsn);

        ObservableList<AnalysisDT> users1 = FXCollections.observableArrayList();

        try {

            String suql = "SELECT `Eqno`, `Date1`, `Cmpname`,`delDate`, `Subject`, c.name, `Reason` FROM `enquirybin` e join customer c on "
                    + "e.cid=c.cid  WHERE 1 order by e.`deldate`";
            PreparedStatement st;
            st = com.mycompany.snp.MainApp.conn.prepareStatement(suql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                users1.add(new AnalysisDT(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
            }
            rs.previous();
            enq_label.setText("Enquiries Declined : " + rs.getRow());
            final TreeItem<AnalysisDT> root = new RecursiveTreeItem<AnalysisDT>(users1, RecursiveTreeObject::getChildren);
            enq_tables.getColumns().clear();
            enq_tables.getColumns().setAll(subject, code, attended, total, need1, need2, need3);
            enq_tables.setRoot(root);
            enq_tables.setShowRoot(false);

            enq_filter.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                enq_tables.setPredicate((TreeItem<AnalysisDT> t) -> {
                    Boolean flag = t.getValue().enqno.getValue().toUpperCase().contains(newValue.toUpperCase()) || t.getValue().Subdate.getValue().contains(newValue) || t.getValue().comname.getValue().toUpperCase().contains(newValue.toUpperCase()) || t.getValue().decdate.getValue().contains(newValue) || t.getValue().rsn.getValue().toUpperCase().contains(newValue.toUpperCase()) || t.getValue().cname.getValue().toUpperCase().contains(newValue.toUpperCase());
                    return flag;
                });
            });
        } catch (Exception ex) {
            Utilities.AlertBox.showErrorMessage(ex);
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void notquoted_enq() {
        JFXTreeTableColumn<AnalysisDT, String> subject = new JFXTreeTableColumn<>("Enquiry Number");
        subject.setPrefWidth(200);
        subject.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT, String> param) -> param.getValue().getValue().enqno);

        JFXTreeTableColumn<AnalysisDT, String> code = new JFXTreeTableColumn<>("Submission Date");
        code.setPrefWidth(200);
        code.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT, String> param) -> param.getValue().getValue().Subdate);

        JFXTreeTableColumn<AnalysisDT, String> attended = new JFXTreeTableColumn<>("Company Name");
        attended.setPrefWidth(200);
        attended.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT, String> param) -> param.getValue().getValue().comname);

        JFXTreeTableColumn<AnalysisDT, String> need1 = new JFXTreeTableColumn<>("Subject");
        need1.setPrefWidth(200);
        need1.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT, String> param) -> param.getValue().getValue().sub);
        JFXTreeTableColumn<AnalysisDT, String> need2 = new JFXTreeTableColumn<>("Customer Name");
        need2.setPrefWidth(200);
        need2.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT, String> param) -> param.getValue().getValue().cname);

        ObservableList<AnalysisDT> users1 = FXCollections.observableArrayList();

        try {

            String suql = "SELECT e.`Eqno`, e.`Date1`, e.`Cmpname`, e.`Subject`, c.name FROM `enquiry` e,`customer` c WHERE NOT EXISTS(SELECT * FROM eqrel er "
                    + "WHERE er.Eno=e.`Eqno` and er.Date1=e.Date1 and er.Cmpname=e.Cmpname and er.CID=e.cid) AND e.cid=c.cid;";
            PreparedStatement st;
            st = com.mycompany.snp.MainApp.conn.prepareStatement(suql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                users1.add(new AnalysisDT(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            rs.previous();
            enq_label.setText("Not Quoted Enquiries  : " + rs.getRow());
            final TreeItem<AnalysisDT> root = new RecursiveTreeItem<AnalysisDT>(users1, RecursiveTreeObject::getChildren);
            enq_tables.getColumns().clear();
            enq_tables.getColumns().setAll(subject, code, attended, need1, need2);
            enq_tables.setRoot(root);
            enq_tables.setShowRoot(false);

            enq_filter.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                enq_tables.setPredicate((TreeItem<AnalysisDT> t) -> {
                    Boolean flag = t.getValue().enqno.getValue().toUpperCase().contains(newValue.toUpperCase()) || t.getValue().Subdate.getValue().contains(newValue) || t.getValue().comname.getValue().toUpperCase().contains(newValue.toUpperCase()) || t.getValue().cname.getValue().toUpperCase().contains(newValue.toUpperCase());
                    return flag;
                });
            });
        } catch (Exception ex) {
            Utilities.AlertBox.showErrorMessage(ex);
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void quoted_enq() {
        JFXTreeTableColumn<AnalysisDT, String> subject = new JFXTreeTableColumn<>("Enquiry Number");
        subject.setPrefWidth(200);
        subject.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT, String> param) -> param.getValue().getValue().enqno);

        JFXTreeTableColumn<AnalysisDT, String> code = new JFXTreeTableColumn<>("Submission Date");
        code.setPrefWidth(200);
        code.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT, String> param) -> param.getValue().getValue().Subdate);

        JFXTreeTableColumn<AnalysisDT, String> attended = new JFXTreeTableColumn<>("Company Name");
        attended.setPrefWidth(200);
        attended.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT, String> param) -> param.getValue().getValue().comname);

        JFXTreeTableColumn<AnalysisDT, String> need1 = new JFXTreeTableColumn<>("Subject");
        need1.setPrefWidth(200);
        need1.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT, String> param) -> param.getValue().getValue().sub);
        JFXTreeTableColumn<AnalysisDT, String> need2 = new JFXTreeTableColumn<>("Customer Name");
        need2.setPrefWidth(200);
        need2.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT, String> param) -> param.getValue().getValue().cname);
        JFXTreeTableColumn<AnalysisDT, String> need3 = new JFXTreeTableColumn<>("Quotation Number");
        need3.setPrefWidth(200);
        need3.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT, String> param) -> param.getValue().getValue().qno);

        ObservableList<AnalysisDT> users1 = FXCollections.observableArrayList();

        try {

            String suql = "SELECT e.`Eqno`, e.`Date1`, e.`Cmpname`, e.`Subject`, c.name,er.qno FROM `enquiry` e,`customer` c, eqrel er, qoutation q   WHERE "
                    + "                                     e.cid=c.cid and er.Eno=e.`Eqno` and er.Date1=e.Date1 and er.Cmpname=e.Cmpname and er.CID=e.cid and er.qno=q.qno order by q.times";
            PreparedStatement st;
            st = com.mycompany.snp.MainApp.conn.prepareStatement(suql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                users1.add(new AnalysisDT(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
            rs.previous();
            enq_label.setText("Quoted Enquiries : " + rs.getRow());
            final TreeItem<AnalysisDT> root = new RecursiveTreeItem<AnalysisDT>(users1, RecursiveTreeObject::getChildren);
            enq_tables.getColumns().clear();
            enq_tables.getColumns().setAll(subject, code, attended, need1, need2, need3);
            enq_tables.setRoot(root);
            enq_tables.setShowRoot(false);

            enq_filter.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                enq_tables.setPredicate((TreeItem<AnalysisDT> t) -> {
                    Boolean flag = t.getValue().qno.getValue().toUpperCase().contains(newValue.toUpperCase()) || t.getValue().enqno.getValue().toUpperCase().contains(newValue.toUpperCase()) || t.getValue().Subdate.getValue().contains(newValue) || t.getValue().comname.getValue().toUpperCase().contains(newValue.toUpperCase()) || t.getValue().cname.getValue().toUpperCase().contains(newValue.toUpperCase());
                    return flag;
                });
            });
        } catch (Exception ex) {
            Utilities.AlertBox.showErrorMessage(ex);
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    class AnalysisDT extends RecursiveTreeObject<AnalysisDT> {

        //this inner class is used for the enquiry tab on the dashboard
        StringProperty enqno;
        StringProperty Subdate;
        StringProperty comname;
        StringProperty decdate;
        StringProperty sub;
        StringProperty cname;
        StringProperty rsn;
        StringProperty qno;

        public AnalysisDT(String Subject, String SubjectCode, String Attended, String Total, String need, String c, String r) {
            this.enqno = new SimpleStringProperty(Subject);
            this.comname = new SimpleStringProperty(Attended);
            this.decdate = new SimpleStringProperty(Total);
            this.Subdate = new SimpleStringProperty(SubjectCode);
            this.sub = new SimpleStringProperty(need);
            //this.sub=new JFXTextArea();this.sub.setText(need);
            this.cname = new SimpleStringProperty(c);
            this.rsn = new SimpleStringProperty(r);

        }

        private AnalysisDT(String string, String string0, String string1, String string2, String string3) {
            this.enqno = new SimpleStringProperty(string);
            this.comname = new SimpleStringProperty(string1);
            this.cname = new SimpleStringProperty(string3);
            this.Subdate = new SimpleStringProperty(string0);
            this.sub = new SimpleStringProperty(string2);

        }

        private AnalysisDT(String string, String string0, String string1, String string2, String string3, String string4) {
            this.enqno = new SimpleStringProperty(string);
            this.comname = new SimpleStringProperty(string1);
            this.cname = new SimpleStringProperty(string3);
            this.Subdate = new SimpleStringProperty(string0);
            this.sub = new SimpleStringProperty(string2);
            this.qno = new SimpleStringProperty(string4);

        }
    }

    class AnalysisDT1 extends RecursiveTreeObject<AnalysisDT1> {

        //this inner class is used for the enquiry tab on the dashboard
        StringProperty qo;
        StringProperty dates;
        StringProperty eno;
        StringProperty company;
        StringProperty sub;
        StringProperty custname;

        public AnalysisDT1(String q, String dates, String eno, String company, String su, String cuname) {
            this.qo = new SimpleStringProperty(q);
            this.dates = new SimpleStringProperty(dates);
            this.eno = new SimpleStringProperty(eno);
            this.company = new SimpleStringProperty(company);
            this.sub = new SimpleStringProperty(su);
            this.custname = new SimpleStringProperty(cuname);

        }

        public AnalysisDT1(String q, String eno, String company, String su, String cuname) {
            this.qo = new SimpleStringProperty(q);
            this.eno = new SimpleStringProperty(eno);
            this.company = new SimpleStringProperty(company);
            this.sub = new SimpleStringProperty(su);
            this.custname = new SimpleStringProperty(cuname);

        }

    }

    class AnalysisDT3 extends RecursiveTreeObject<AnalysisDT3> {

        //this inner class is used for the enquiry tab on the dashboard
        StringProperty ino;
        StringProperty totamt;
        StringProperty date;
        StringProperty duedate;
        StringProperty salesperson;
        StringProperty top;
        StringProperty daysleft;
        StringProperty desc;
        StringProperty amt;
        StringProperty custname;

        public AnalysisDT3(String io, String toamt, String dat, String dudate, String salperson, String topp, String dayslef, String desc, String a, String c) {
            this.ino = new SimpleStringProperty(io);
            this.totamt = new SimpleStringProperty(toamt);
            this.date = new SimpleStringProperty(dat);
            this.duedate = new SimpleStringProperty(dudate);
            this.salesperson = new SimpleStringProperty(salperson);
            this.top = new SimpleStringProperty(topp);
            this.daysleft = new SimpleStringProperty(dayslef);
            this.desc = new SimpleStringProperty(desc);
            this.amt = new SimpleStringProperty(a);
            this.custname = new SimpleStringProperty(c);

        }
        /* public AnalysisDT3(String q ,String eno, String company,String su,String cuname){
            this.qo = new SimpleStringProperty(q);
            this.eno = new SimpleStringProperty(eno);
            this.company= new SimpleStringProperty(company);
            this.sub= new SimpleStringProperty(su);
            this.custname=new SimpleStringProperty(cuname);
       
            
        }*/

    }

    public void inv_gen_but_not_paid(int i) {
        JFXTreeTableColumn<AnalysisDT3, String> subject = new JFXTreeTableColumn<>("Invoice Number");
        subject.setPrefWidth(200);
        subject.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT3, String> param) -> param.getValue().getValue().ino);

        JFXTreeTableColumn<AnalysisDT3, String> code = new JFXTreeTableColumn<>("Total Amount");
        code.setPrefWidth(200);
        code.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT3, String> param) -> param.getValue().getValue().totamt);

        JFXTreeTableColumn<AnalysisDT3, String> attended = new JFXTreeTableColumn<>("Date");
        attended.setPrefWidth(200);
        attended.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT3, String> param) -> param.getValue().getValue().date);

        JFXTreeTableColumn<AnalysisDT3, String> need1 = new JFXTreeTableColumn<>("Due Date");
        need1.setPrefWidth(200);
        need1.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT3, String> param) -> param.getValue().getValue().duedate);
        JFXTreeTableColumn<AnalysisDT3, String> need2 = new JFXTreeTableColumn<>("Salesperson");
        need2.setPrefWidth(200);
        need2.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT3, String> param) -> param.getValue().getValue().salesperson);
        JFXTreeTableColumn<AnalysisDT3, String> need3 = new JFXTreeTableColumn<>("Term of Pay");
        need3.setPrefWidth(200);
        need3.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT3, String> param) -> param.getValue().getValue().top);
        JFXTreeTableColumn<AnalysisDT3, String> need4 = new JFXTreeTableColumn<>("Days Left");
        need4.setPrefWidth(200);
        need4.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT3, String> param) -> param.getValue().getValue().daysleft);

        JFXTreeTableColumn<AnalysisDT3, String> need5 = new JFXTreeTableColumn<>("Description");
        need5.setPrefWidth(200);
        need5.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT3, String> param) -> param.getValue().getValue().desc);
        JFXTreeTableColumn<AnalysisDT3, String> need6 = new JFXTreeTableColumn<>("Amount paid");
        need6.setPrefWidth(200);
        need6.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT3, String> param) -> param.getValue().getValue().amt);
        JFXTreeTableColumn<AnalysisDT3, String> need7 = new JFXTreeTableColumn<>("Customer Name");
        need7.setPrefWidth(200);
        need7.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT3, String> param) -> param.getValue().getValue().custname);

        ObservableList<AnalysisDT3> users3 = FXCollections.observableArrayList();

        String suql;
        try {
            if (i == 0) {

                suql = "select i.INo,i.Total_amt+i.addedgst as m,ifnull(i.Date,\"-\"),ifnull(i.Duedate,\"-\"),i.Salesperson,"
                        + "ifnull(i.Termofpay,\"-\"),ifnull(DATEDIFF(i.Duedate,CURDATE()),\"-\"),p.Des,i.Amount_paid, c.name from `invoice` i,`product` p,"
                        + "`pirel` pi, `qprel` qp, eqrel e,customer c where i.INo=pi.INo and pi.PjNo=p.PjNo and "
                        + "qp.pjno=pi.pjno and e.QNo=qp.qno  and c.cid=e.cid and i.invgen=1 and i.Amount_paid<(i.Total_amt+i.addedgst);";
            } else if (i == 1) {
                suql = "select i.INo,i.Total_amt+i.addedgst as m,ifnull(i.Date,\"-\"),ifnull(i.Duedate,\"-\"),i.Salesperson,"
                        + "ifnull(i.Termofpay,\"-\"),ifnull(DATEDIFF(i.Duedate,CURDATE()),\"-\"),p.Des,i.Amount_paid, c.name from `invoice` i,`product` p,"
                        + "`pirel` pi, `qprel` qp, eqrel e,customer c where i.INo=pi.INo and pi.PjNo=p.PjNo and "
                        + "qp.pjno=pi.pjno and e.QNo=qp.qno  and c.cid=e.cid and i.invgen=1 and i.Amount_paid>=(i.Total_amt+i.addedgst);";

            } else {
                suql = "select i.INo,i.Total_amt+i.addedgst as m,ifnull(i.Date,\"-\"),ifnull(i.Duedate,\"-\"),ifnull(i.Salesperson,\"-\"),"
                        + "ifnull(i.Termofpay,\"-\"),ifnull(DATEDIFF(i.Duedate,CURDATE()),\"-\"),p.Des,i.Amount_paid, c.name from `invoice` i,`product` p,"
                        + "`pirel` pi, `qprel` qp, eqrel e,customer c where i.INo=pi.INo and pi.PjNo=p.PjNo and "
                        + "qp.pjno=pi.pjno and e.QNo=qp.qno  and c.cid=e.cid and i.invgen=0";

            }
            PreparedStatement st;
            st = com.mycompany.snp.MainApp.conn.prepareStatement(suql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                users3.add(new AnalysisDT3(rs.getString(1), (rs.getString(2)), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)));
            }
            rs.previous();

            if (i == 0) {
                inv_label.setText("Generated but not paid invoices: " + rs.getRow());
            } else if (i == 1) {
                inv_label.setText("Paid invoices: " + rs.getRow());
            } else {
                inv_label.setText("Invoices not generated: " + rs.getRow());
            }

            final TreeItem<AnalysisDT3> root = new RecursiveTreeItem<AnalysisDT3>(users3, RecursiveTreeObject::getChildren);
            inv_tables.getColumns().clear();
            if (i == 0) {
                inv_tables.getColumns().setAll(subject, code, need6, attended, need1, need2, need3, need4, need5, need7);
                inv_tables.setRoot(root);
                inv_tables.setShowRoot(false);

                inv_filter.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    inv_tables.setPredicate((TreeItem<AnalysisDT3> t) -> {
                        Boolean flag = t.getValue().daysleft.getValue().contains(newValue) || t.getValue().top.getValue().contains(newValue) || t.getValue().ino.getValue().toUpperCase().contains(newValue.toUpperCase()) || t.getValue().totamt.getValue().contains(newValue) || t.getValue().date.getValue().contains(newValue) || t.getValue().duedate.getValue().contains(newValue) || t.getValue().salesperson.getValue().toUpperCase().contains(newValue.toUpperCase());
                        return flag;
                    });
                });
            } else if (i == 1) {
                inv_tables.getColumns().setAll(subject, need6, attended, need3, need5, need7);
                inv_tables.setRoot(root);
                inv_tables.setShowRoot(false);

                inv_filter.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    inv_tables.setPredicate((TreeItem<AnalysisDT3> t) -> {
                        Boolean flag = t.getValue().custname.getValue().toUpperCase().contains(newValue.toUpperCase()) || t.getValue().ino.getValue().toUpperCase().contains(newValue.toUpperCase()) || t.getValue().date.getValue().contains(newValue) || t.getValue().salesperson.getValue().toUpperCase().contains(newValue.toUpperCase());
                        return flag;
                    });
                });
            } else {
                inv_tables.getColumns().setAll(subject, code, attended, need1, need2, need3, need4, need5, need7);
                inv_tables.setRoot(root);
                inv_tables.setShowRoot(false);

                inv_filter.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    inv_tables.setPredicate((TreeItem<AnalysisDT3> t) -> {
                        Boolean flag = t.getValue().daysleft.getValue().contains(newValue) || t.getValue().top.getValue().contains(newValue) || t.getValue().ino.getValue().toUpperCase().contains(newValue.toUpperCase()) || t.getValue().totamt.getValue().contains(newValue) || t.getValue().date.getValue().contains(newValue) || t.getValue().duedate.getValue().contains(newValue) || t.getValue().salesperson.getValue().toUpperCase().contains(newValue.toUpperCase());
                        return flag;
                    });
                });
            }
        } catch (Exception ex) {
            Utilities.AlertBox.showErrorMessage(ex);
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void inv_lc_method(String d) {
        xaxis_inv.setLabel("Months");
        yaxis_inv.setLabel("Cash Inflow in $");

        yaxis_inv.setSide(Side.LEFT);
        xaxis_inv.setSide(Side.BOTTOM);
        xaxis_inv.getCategories().clear();
        inv_line.getData().clear();
        mths m;
        XYChart.Series s = new XYChart.Series();
        XYChart.Series s1 = new XYChart.Series();
        s1.getData().clear();
        s.getData().clear();
        s.setName("Awin");
        s1.setName("Steels");
        xaxis_inv.setAnimated(false);
        yaxis_inv.setAnimated(true);
        inv_line.setAnimated(true);
        int arr[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        ResultSet rs;
        try {
            /* for (mths k : mths.values()){
                                       
                                       s.getData().add(new XYChart.Data(k.toString(),0));
                           s1.getData().add(new XYChart.Data(k.toString(),0));
                           }*/
            String suql = "Select sum(m.Total_amt)+sum(m.addedgst) k,substring(m.Date,6,2) "
                    + "from (Select * from invoice i where i.Date like \"" + d + "%\" and substring(i.INo,6,2) like"
                    + " \"AE\" and i.invgen=1) m group by substring(m.Date,6,2); ";
            PreparedStatement st;
            st = com.mycompany.snp.MainApp.conn.prepareStatement(suql);

            rs = st.executeQuery();
            //int c=0,l=0;
            while (rs.next()) {
                System.out.println(rs.getString(1));
                for (mths k : mths.values()) {
                    if ((rs.getString(2).equals("0" + String.valueOf(k.ordinal() + 1))) || (rs.getString(2).equals(String.valueOf(k.ordinal() + 1)))) {
                        //  s.getData().add(new XYChart.Data(k.toString(),rs.getInt("z"))); 
                        arr[k.ordinal()] += rs.getInt(1);
                    }
                }

            }
            for (mths k : mths.values()) {
                s.getData().add(new XYChart.Data(k.toString(), arr[k.ordinal()]));
                //System.out.println(arr[k.ordinal()]+" "+k);
            }
            int arr1[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            suql = "Select sum(m.Total_amt)+sum(m.addedgst) k,substring(m.Date,6,2) "
                    + "from (Select * from invoice i where i.Date like \"" + d + "%\" and substring(i.INo,6,2) like"
                    + " \"SC\" and i.invgen=1) m group by substring(m.Date,6,2); ";

            Statement st1 = com.mycompany.snp.MainApp.conn.createStatement();

            rs = st1.executeQuery(suql);

            while (rs.next()) {
                //      System.out.println(rs.getString(1));
                for (mths k : mths.values()) {
                    if ((rs.getString(2).equals("0" + String.valueOf(k.ordinal() + 1))) || (rs.getString(2).equals(String.valueOf(k.ordinal() + 1)))) {
                        // s1.getData().add(new XYChart.Data(k.toString(),rs.getInt("z"))); 
                        arr1[k.ordinal()] += rs.getInt(1);
                    }
                }

            }
            for (mths k : mths.values()) {
                s1.getData().add(new XYChart.Data(k.toString(), arr1[k.ordinal()]));
                //System.out.println(arr1[k.ordinal()]+" "+k);
            }
            inv_line.getData().addAll(s, s1);

        } catch (Exception e) {
            Utilities.AlertBox.showErrorMessage(e);
        }

    }

    public void inv_pie_method(String d) {
        ResultSet rs;
        try {

            String suql = "Select count(*) from invoice i where i.invgen=1 and (i.Total_amt+i.addedgst)<>0 and i.Amount_paid=0 AND i.Date LIKE '" + d + "%'";
            PreparedStatement st;
            st = com.mycompany.snp.MainApp.conn.prepareStatement(suql);
            rs = st.executeQuery();
            rs.next();
            int genbutnotp = rs.getInt(1);
            System.out.println(genbutnotp);
            //  System.out.println("FIRST QUERY FINE");

            String seql = "Select count(*) from invoice i where i.invgen=1 and (i.Total_amt+i.addedgst)<>0 and i.Amount_paid>=(i.Total_amt+i.addedgst) AND i.Date LIKE '" + d + "%'";
            st = com.mycompany.snp.MainApp.conn.prepareStatement(seql);
            rs = st.executeQuery();
            rs.next();
            int genandp = rs.getInt(1);
            System.out.println(genandp);
            // System.out.println("SECOND QUERY FINE");
            String seql2 = "SELECT count(*) FROM invoice i where i.invgen=0 and i.Date LIKE '" + d + "%' || i.Date IS NULL";
            st = com.mycompany.snp.MainApp.conn.prepareStatement(seql2);
            rs = st.executeQuery();
            rs.next();

            int notgen = rs.getInt(1);
            System.out.println(notgen);
            String seql23 = "SELECT count(*) FROM invoice i where (i.Total_amt+i.addedgst)<>0 and i.Amount_paid<(i.Total_amt+i.addedgst) and i.Amount_paid<>0 and i.invgen=1 and i.Date LIKE '" + d + "%'";
            st = com.mycompany.snp.MainApp.conn.prepareStatement(seql23);
            rs = st.executeQuery();
            rs.next();
            int partialp = rs.getInt(1);
            System.out.println(partialp);

            ObservableList<PieChart.Data> pcd = FXCollections.observableArrayList();
            if (genbutnotp != 0) {
                pcd.add(new PieChart.Data("Generated but not paid", genbutnotp));
            }
            if (genandp != 0) {
                pcd.add(new PieChart.Data("Paid invoices", genandp));
            }
            if (notgen != 0) {
                pcd.add(new PieChart.Data("Not generated", notgen));
            }
            if (partialp != 0) {
                pcd.add(new PieChart.Data("Partially paid invoices", partialp));
            }

            inv_pie.setData(pcd);
            inv_pie.setLegendVisible(true);

        } catch (Exception e) {
            System.out.println(539);
            Utilities.AlertBox.showErrorMessage(e);
        }

    }

    public void prj_pie(String d) {
        ResultSet rs;
        try {

            String suql = "SELECT count(*) FROM `product` p join qprel qp on p.pjno=qp.PjNo join eqrel e on "
                    + "qp.Qno=e.QNo join customer c on c.cid=e.CID WHERE p.`Comp`=0 and p.`Estdate`< CURDATE() and substring(p.Date,1,4)=\"" + d + "\";";
            PreparedStatement st;
            st = com.mycompany.snp.MainApp.conn.prepareStatement(suql);
            rs = st.executeQuery();
            rs.next();
            int accep_customno = rs.getInt(1);

            String seql = "SELECT count(*) FROM `product` p "
                    + "join qprel qp on p.pjno=qp.PjNo join eqrel e on qp.Qno=e.QNo join customer c on c.cid=e.CID WHERE p.`Comp`=1 and substring(p.date,1,4)=\"" + d + "\";";
            st = com.mycompany.snp.MainApp.conn.prepareStatement(seql);
            rs = st.executeQuery();
            rs.next();
            int notsent = rs.getInt(1);

            String seql2 = "SELECT count(*) FROM `product` p join qprel qp on p.pjno=qp.PjNo join eqrel e on "
                    + "qp.Qno=e.QNo join customer c on c.cid=e.CID WHERE p.`Comp`=0 and p.`Estdate`>= CURDATE() and substring(p.Date,1,4)=\"" + d + "\";";
            st = com.mycompany.snp.MainApp.conn.prepareStatement(seql2);
            rs = st.executeQuery();
            rs.next();
            int sent_botnoaccept = rs.getInt(1);

            ObservableList<PieChart.Data> pc = FXCollections.observableArrayList(
                    new PieChart.Data("Projects that have exceeded the deadline", accep_customno),
                    new PieChart.Data("Projects that have been completed.", notsent),
                    new PieChart.Data("Projects that not have exceeded the deadline", sent_botnoaccept));
            proj_pie.setData(pc);
            proj_pie.setLegendVisible(true);

        } catch (Exception e) {
            Utilities.AlertBox.showErrorMessage(e);
        }

    }

    public void prj_bc(String d) {
        try {
            proj_bar.getData().add(new XYChart.Series(FXCollections.observableArrayList(new XYChart.Data("", 0))));
            proj_bar.getData().clear();
            xaxis_pjbc.getCategories().clear();
            xaxis_pjbc.getCategories().addAll("Awin", "Steels");
            XYChart.Series dataSeries1 = new XYChart.Series();
            XYChart.Series dataSeries2 = new XYChart.Series();
            proj_bar.setTitle("Delayed Projects");
            dataSeries1.setName("Awin");
            dataSeries2.setName("Steels");
            xaxis_bc.setLabel("Reason");
            yaxis_pjbc.setLabel("Number of Delayed Projects");
            yaxis_pjbc.setSide(Side.LEFT);
            xaxis_pjbc.setSide(Side.BOTTOM);
            dataSeries1.getData().clear();
            dataSeries2.getData().clear();
            xaxis_pjbc.setAnimated(false);
            yaxis_pjbc.setAnimated(true);
            proj_bar.setAnimated(true);

            String sql = "SELECT (CASE substring(m.qno,4,2) when \"AE\" then \"Awin\" when \"SC\" then \"Steels\" END) as k, count(*) FROM "
                    + "(Select * from product p natural join qprel q where substring(Date,1,4)=\"" + d + "\" and CURDATE()>p.Date) m group by substring(m.qno,4,2) ;";
            Statement stmt = com.mycompany.snp.MainApp.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if (rs.getString(1).equals("Awin")) {
                    dataSeries1.getData().add(new XYChart.Data(rs.getString(1), rs.getInt(2)));
                } else if (rs.getString(1).equals("Steels")) {
                    dataSeries2.getData().add(new XYChart.Data(rs.getString(1), rs.getInt(2)));
                }

            }

            proj_bar.getData().addAll(dataSeries1, dataSeries2);

        } catch (SQLException ex) {
            Utilities.AlertBox.showErrorMessage(ex);
        }

    }

    public void prj_lc(String d) {
        xaxis_pjlc.setLabel("Months");
        yaxis_pjlc.setLabel("Number of Projects");
        yaxis_pjlc.setSide(Side.LEFT);
        xaxis_pjlc.setSide(Side.BOTTOM);
        xaxis_pjlc.getCategories().clear();
        proj_line.getData().clear();
        mths m;
        XYChart.Series s = new XYChart.Series();
        XYChart.Series s1 = new XYChart.Series();
        s1.getData().clear();
        s.getData().clear();
        s.setName("Awin");
        s1.setName("Steels");
        xaxis_pjlc.setAnimated(false);
        yaxis_pjlc.setAnimated(true);
        proj_line.setAnimated(true);
        int arr[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        ResultSet rs;
        try {
            /* for (mths k : mths.values()){
                                       
                                       s.getData().add(new XYChart.Data(k.toString(),0));
                           s1.getData().add(new XYChart.Data(k.toString(),0));
                           }*/
            String suql = "SELECT substring(m.`Date`,6,2), count(*) FROM (Select * from `product` p natural "
                    + "join qprel q where substring(`qno`,4,2) like \"AE\" and substring(`Date`,1,4)=\"" + d + "\") m group by substring(m.`Date`,6,2) ;";
            PreparedStatement st;
            st = com.mycompany.snp.MainApp.conn.prepareStatement(suql);

            rs = st.executeQuery();
            //int c=0,l=0;
            while (rs.next()) {
                System.out.println(rs.getString(1));
                for (mths k : mths.values()) {
                    if ((rs.getString(1).equals("0" + String.valueOf(k.ordinal() + 1))) || (rs.getString(1).equals(String.valueOf(k.ordinal() + 1)))) {
                        //  s.getData().add(new XYChart.Data(k.toString(),rs.getInt("z"))); 
                        arr[k.ordinal()] += rs.getInt(2);
                    }
                }

            }
            for (mths k : mths.values()) {
                s.getData().add(new XYChart.Data(k.toString(), arr[k.ordinal()]));
                System.out.println(arr[k.ordinal()] + " " + k);
            }
            int arr1[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            suql = "SELECT substring(m.`Date`,6,2), count(*) FROM (Select * from `product` p natural "
                    + "join qprel q where substring(`qno`,4,2) like \"SC\" and substring(`Date`,1,4)=\"" + d + "\") m group by substring(m.`Date`,6,2) ;";

            Statement st1 = com.mycompany.snp.MainApp.conn.createStatement();

            rs = st1.executeQuery(suql);

            while (rs.next()) {
                //      System.out.println(rs.getString(1));
                for (mths k : mths.values()) {
                    if ((rs.getString(1).equals("0" + String.valueOf(k.ordinal() + 1))) || (rs.getString(1).equals(String.valueOf(k.ordinal() + 1)))) {
                        // s1.getData().add(new XYChart.Data(k.toString(),rs.getInt("z"))); 
                        arr1[k.ordinal()] += rs.getInt(2);
                    }
                }

            }
            for (mths k : mths.values()) {
                s1.getData().add(new XYChart.Data(k.toString(), arr1[k.ordinal()]));
                System.out.println(arr1[k.ordinal()] + " " + k);
            }

            proj_line.getData().addAll(s, s1);

        } catch (Exception e) {
            Utilities.AlertBox.showErrorMessage(e);
        }

    }

    public void prj_xedead() {
        JFXTreeTableColumn<AnalysisDT2, String> pjno = new JFXTreeTableColumn<>("Project Number");
        pjno.setPrefWidth(200);
        pjno.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT2, String> param) -> param.getValue().getValue().PjNo);

        JFXTreeTableColumn<AnalysisDT2, String> pno = new JFXTreeTableColumn<>("Purchase Order Number");
        pno.setPrefWidth(200);
        pno.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT2, String> param) -> param.getValue().getValue().Pno);

        JFXTreeTableColumn<AnalysisDT2, String> val = new JFXTreeTableColumn<>("Total Value");
        val.setPrefWidth(200);
        val.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT2, String> param) -> param.getValue().getValue().val);
        JFXTreeTableColumn<AnalysisDT2, String> sdate = new JFXTreeTableColumn<>("Start Date");
        sdate.setPrefWidth(200);
        sdate.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT2, String> param) -> param.getValue().getValue().sdate);

        JFXTreeTableColumn<AnalysisDT2, String> edate = new JFXTreeTableColumn<>("Estimated Date");
        edate.setPrefWidth(200);
        edate.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT2, String> param) -> param.getValue().getValue().edate);
        JFXTreeTableColumn<AnalysisDT2, String> delay = new JFXTreeTableColumn<>("Exceeded by ");
        delay.setPrefWidth(200);
        delay.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT2, String> param) -> param.getValue().getValue().delay);

        JFXTreeTableColumn<AnalysisDT2, String> cname = new JFXTreeTableColumn<>("Customer Name");
        cname.setPrefWidth(200);
        cname.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT2, String> param) -> param.getValue().getValue().cname);
        JFXTreeTableColumn<AnalysisDT2, String> cmpname = new JFXTreeTableColumn<>("Company Name");
        cmpname.setPrefWidth(200);
        cmpname.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT2, String> param) -> param.getValue().getValue().cmpname);
        JFXTreeTableColumn<AnalysisDT2, String> des = new JFXTreeTableColumn<>("Description");
        des.setPrefWidth(200);
        des.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT2, String> param) -> param.getValue().getValue().des);

        ObservableList<AnalysisDT2> users1 = FXCollections.observableArrayList();

        try {

            String suql = "SELECT p.`PNo`, p.`PjNo`, p.`Value`, p.`Des`,p.`Date`, p.`Estdate`,DATEDIFF(CURDATE(),p.`Estdate`),"
                    + " e.Cmpname,c.Name FROM `product` p join qprel qp on p.pjno=qp.PjNo join eqrel e on "
                    + "qp.Qno=e.QNo join customer c on c.cid=e.CID WHERE p.`Comp`=0 and p.`Estdate`< CURDATE();";
            PreparedStatement st;
            st = com.mycompany.snp.MainApp.conn.prepareStatement(suql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                users1.add(new AnalysisDT2(rs.getString(2), rs.getString(1), rs.getString(3), rs.getString(5), rs.getString(6), rs.getString(7) + " days", rs.getString(9), rs.getString(8), rs.getString(4)));
                //System.out.println(rs.getString(2)+rs.getString(1)+rs.getString(3)+rs.getString(5)+rs.getString(7)+rs.getString(6)+rs.getString(4));
            }
            rs.previous();
            project_label.setText("Number of Incomplete Projects : " + rs.getRow());
            final TreeItem<AnalysisDT2> root = new RecursiveTreeItem<AnalysisDT2>(users1, RecursiveTreeObject::getChildren);
            proj_table.getColumns().clear();
            proj_table.getColumns().setAll(pjno, pno, val, sdate, edate, delay, cname, cmpname, des);
            proj_table.setRoot(root);
            proj_table.setShowRoot(false);

            project_filter.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                proj_table.setPredicate((TreeItem<AnalysisDT2> t) -> {
                    Boolean flag = t.getValue().PjNo.getValue().contains(newValue) || t.getValue().Pno.getValue().toUpperCase().contains(newValue.toUpperCase()) || t.getValue().delay.getValue().contains(newValue) || t.getValue().val.getValue().contains(newValue) || t.getValue().cmpname.getValue().toUpperCase().contains(newValue.toUpperCase()) || t.getValue().cname.getValue().toUpperCase().contains(newValue.toUpperCase());
                    return flag;
                });
            });
        } catch (Exception ex) {
            Utilities.AlertBox.showErrorMessage(ex);
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void prj_yetcomp() {
        JFXTreeTableColumn<AnalysisDT2, String> pjno = new JFXTreeTableColumn<>("Project Number");
        pjno.setPrefWidth(200);
        pjno.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT2, String> param) -> param.getValue().getValue().PjNo);

        JFXTreeTableColumn<AnalysisDT2, String> pno = new JFXTreeTableColumn<>("Purchase Order Number");
        pno.setPrefWidth(200);
        pno.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT2, String> param) -> param.getValue().getValue().Pno);

        JFXTreeTableColumn<AnalysisDT2, String> val = new JFXTreeTableColumn<>("Total Value");
        val.setPrefWidth(200);
        val.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT2, String> param) -> param.getValue().getValue().val);
        JFXTreeTableColumn<AnalysisDT2, String> sdate = new JFXTreeTableColumn<>("Start Date");
        sdate.setPrefWidth(200);
        sdate.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT2, String> param) -> param.getValue().getValue().sdate);

        JFXTreeTableColumn<AnalysisDT2, String> edate = new JFXTreeTableColumn<>("Estimated Date");
        edate.setPrefWidth(200);
        edate.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT2, String> param) -> param.getValue().getValue().edate);

        JFXTreeTableColumn<AnalysisDT2, String> cname = new JFXTreeTableColumn<>("Customer Name");
        cname.setPrefWidth(200);
        cname.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT2, String> param) -> param.getValue().getValue().cname);
        JFXTreeTableColumn<AnalysisDT2, String> cmpname = new JFXTreeTableColumn<>("Company Name");
        cmpname.setPrefWidth(200);
        cmpname.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT2, String> param) -> param.getValue().getValue().cmpname);
        JFXTreeTableColumn<AnalysisDT2, String> des = new JFXTreeTableColumn<>("Description");
        des.setPrefWidth(200);
        des.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT2, String> param) -> param.getValue().getValue().des);

        ObservableList<AnalysisDT2> users1 = FXCollections.observableArrayList();

        try {

            String suql = "SELECT p.`PNo`, p.`PjNo`, p.`Value`, p.`Des`,p.`Date`, p.`Estdate`, e.Cmpname,c.Name FROM `product` p "
                    + "join qprel qp on p.pjno=qp.PjNo join eqrel e on qp.Qno=e.QNo join customer c on c.cid=e.CID WHERE p.`Comp`=0;";
            PreparedStatement st;
            st = com.mycompany.snp.MainApp.conn.prepareStatement(suql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                users1.add(new AnalysisDT2(rs.getString(2), rs.getString(1), rs.getString(3), rs.getString(5), rs.getString(6), rs.getString(8), rs.getString(7), rs.getString(4)));
                System.out.println(rs.getString(5) + "\n" + rs.getString(6));
            }
            rs.previous();
            project_label.setText("Number of Incomplete Projects : " + rs.getRow());
            final TreeItem<AnalysisDT2> root = new RecursiveTreeItem<AnalysisDT2>(users1, RecursiveTreeObject::getChildren);
            proj_table.getColumns().clear();
            proj_table.getColumns().setAll(pjno, pno, val, sdate, edate, cname, cmpname, des);
            proj_table.setRoot(root);
            proj_table.setShowRoot(false);

            project_filter.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                proj_table.setPredicate((TreeItem<AnalysisDT2> t) -> {
                    Boolean flag = t.getValue().PjNo.getValue().contains(newValue) || t.getValue().Pno.getValue().toUpperCase().contains(newValue.toUpperCase()) || t.getValue().val.getValue().contains(newValue) || t.getValue().cmpname.getValue().toUpperCase().contains(newValue.toUpperCase()) || t.getValue().cname.getValue().toUpperCase().contains(newValue.toUpperCase());
                    return flag;
                });
            });
        } catch (Exception ex) {
            Utilities.AlertBox.showErrorMessage(ex);
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void prj_completed() {
        JFXTreeTableColumn<AnalysisDT2, String> pjno = new JFXTreeTableColumn<>("Project Number");
        pjno.setPrefWidth(200);
        pjno.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT2, String> param) -> param.getValue().getValue().PjNo);

        JFXTreeTableColumn<AnalysisDT2, String> pno = new JFXTreeTableColumn<>("Purchase Order Number");
        pno.setPrefWidth(200);
        pno.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT2, String> param) -> param.getValue().getValue().Pno);

        JFXTreeTableColumn<AnalysisDT2, String> val = new JFXTreeTableColumn<>("Total Value");
        val.setPrefWidth(200);
        val.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT2, String> param) -> param.getValue().getValue().val);

        JFXTreeTableColumn<AnalysisDT2, String> cdate = new JFXTreeTableColumn<>("Completion Date");
        cdate.setPrefWidth(200);
        cdate.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT2, String> param) -> param.getValue().getValue().cdate);

        JFXTreeTableColumn<AnalysisDT2, String> cname = new JFXTreeTableColumn<>("Customer Name");
        cname.setPrefWidth(200);
        cname.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT2, String> param) -> param.getValue().getValue().cname);
        JFXTreeTableColumn<AnalysisDT2, String> cmpname = new JFXTreeTableColumn<>("Company Name");
        cmpname.setPrefWidth(200);
        cmpname.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT2, String> param) -> param.getValue().getValue().cmpname);
        JFXTreeTableColumn<AnalysisDT2, String> des = new JFXTreeTableColumn<>("Description");
        des.setPrefWidth(200);
        des.setCellValueFactory((TreeTableColumn.CellDataFeatures<AnalysisDT2, String> param) -> param.getValue().getValue().des);

        ObservableList<AnalysisDT2> users1 = FXCollections.observableArrayList();

        try {

            String suql = "SELECT p.`PNo`, p.`PjNo`, p.`Value`, p.`Des`, p.`Compdate`, e.Cmpname,c.Name FROM `product` p "
                    + "join qprel qp on p.pjno=qp.PjNo join eqrel e on qp.Qno=e.QNo join customer c on c.cid=e.CID WHERE p.`Comp`=1;";
            PreparedStatement st;
            st = com.mycompany.snp.MainApp.conn.prepareStatement(suql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                users1.add(new AnalysisDT2(rs.getString(2), rs.getString(1), rs.getString(3), rs.getString(5), rs.getString(7), rs.getString(6), rs.getString(4)));
                System.out.println(rs.getString(5));
            }
            rs.previous();
            project_label.setText("Completed Projects : " + rs.getRow());
            final TreeItem<AnalysisDT2> root = new RecursiveTreeItem<AnalysisDT2>(users1, RecursiveTreeObject::getChildren);
            proj_table.getColumns().clear();
            proj_table.getColumns().setAll(pjno, pno, val, cdate, cname, cmpname, des);
            proj_table.setRoot(root);
            proj_table.setShowRoot(false);

            project_filter.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                proj_table.setPredicate((TreeItem<AnalysisDT2> t) -> {
                    Boolean flag = t.getValue().PjNo.getValue().contains(newValue) || t.getValue().Pno.getValue().toUpperCase().contains(newValue.toUpperCase()) || t.getValue().val.getValue().contains(newValue) || t.getValue().cmpname.getValue().toUpperCase().contains(newValue.toUpperCase()) || t.getValue().cname.getValue().toUpperCase().contains(newValue.toUpperCase());
                    return flag;
                });
            });
        } catch (Exception ex) {
            Utilities.AlertBox.showErrorMessage(ex);
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    class AnalysisDT2 extends RecursiveTreeObject<AnalysisDT2> {

        //this inner class is used for the enquiry tab on the dashboard
        StringProperty PjNo;
        StringProperty Pno;
        StringProperty val;
        StringProperty sdate;
        StringProperty edate;
        StringProperty cname;
        StringProperty cmpname;
        StringProperty des;
        StringProperty cdate;
        StringProperty delay;

        public AnalysisDT2(String Subject, String Attended, String Total, String need, String c, String r, String k) {
            this.PjNo = new SimpleStringProperty(Subject);
            this.Pno = new SimpleStringProperty(Attended);
            this.val = new SimpleStringProperty(Total);

            this.cdate = new SimpleStringProperty(need);
            //this.sub=new JFXTextArea();this.sub.setText(need);
            this.cname = new SimpleStringProperty(c);
            this.cmpname = new SimpleStringProperty(r);
            this.des = new SimpleStringProperty(k);

        }

        private AnalysisDT2(String Subject, String Attended, String Total, String need, String need1, String c, String r, String k) {
            this.PjNo = new SimpleStringProperty(Subject);
            this.Pno = new SimpleStringProperty(Attended);
            this.val = new SimpleStringProperty(Total);

            this.sdate = new SimpleStringProperty(need);
            this.edate = new SimpleStringProperty(need1);

            //this.sub=new JFXTextArea();this.sub.setText(need);
            this.cname = new SimpleStringProperty(c);
            this.cmpname = new SimpleStringProperty(r);
            this.des = new SimpleStringProperty(k);

        }

        private AnalysisDT2(String Subject, String Attended, String Total, String need, String need1, String del, String c, String r, String k) {
            this.PjNo = new SimpleStringProperty(Subject);
            this.Pno = new SimpleStringProperty(Attended);
            this.val = new SimpleStringProperty(Total);

            this.sdate = new SimpleStringProperty(need);
            this.edate = new SimpleStringProperty(need1);
            this.delay = new SimpleStringProperty(del);
            //this.sub=new JFXTextArea();this.sub.setText(need);
            this.cname = new SimpleStringProperty(c);
            this.cmpname = new SimpleStringProperty(r);
            this.des = new SimpleStringProperty(k);

        }

        /* private AnalysisDT2(String string, String string0, String string1, String string2, String string3, String string4) {
            this.enqno = new SimpleStringProperty(string);
            this.comname = new SimpleStringProperty(string1);
            this.cname = new SimpleStringProperty(string3);
            this.Subdate= new SimpleStringProperty(string0);
            this.sub= new SimpleStringProperty(string2);
            this.qno=new SimpleStringProperty(string4);
            
        }*/
    }

    public void threadtock() {
        final java.util.Timer timer = new java.util.Timer();
        final TimerTask delayedThreadStartTask;
        delayedThreadStartTask = new TimerTask() {
            public void run() {
                try {
                    if (tock) {
                        if (SD[0]) {

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

                            // SalesHam.fireEvent(new Event(MouseEvent.MOUSE_PRESSED));
                            SD[0] = false;
                        } else if (SD[2]) {

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
                            try {
                                QnoBox.getItems().clear();
                                String sql = "SELECT `Qno` FROM `qoutation` WHERE 1";
                                PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
                                ResultSet rs = stmt.executeQuery();
                                while (rs.next()) {
                                    QnoBox.getItems().add(rs.getString(1));
                                    System.out.println(rs.getString(1));
                                }
                            } catch (Exception e) {

                            }

                            // SalesHam.fireEvent(new Event(MouseEvent.MOUSE_PRESSED));
                            SD[2] = false;
                        } else if (SD[3]) {

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
                            try {
                                QnoBox1.getItems().clear();
                                String sql = "SELECT `Qno` FROM `qoutation` WHERE 1";
                                PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
                                ResultSet rs = stmt.executeQuery();
                                while (rs.next()) {
                                    QnoBox1.getItems().add(rs.getString(1));
                                    System.out.println(rs.getString(1));
                                }
                            } catch (Exception e) {

                            }

                            //SalesHam.fireEvent(new Event(MouseEvent.MOUSE_PRESSED));
                            SD[3] = false;
                        } else if (SD[4]) {

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

                            //SalesHam.fireEvent(new Event(MouseEvent.MOUSE_PRESSED));
                            SD[4] = false;
                        } else if (SD[5]) {

                            SD[5] = false;
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

                            try {

                                String sql1 = "SELECT PjNo FROM `product` WHERE 1 ";
                                PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql1);
                                ResultSet rs = stmt.executeQuery();
                                inv_pno.getItems().clear();
                                while (rs.next()) {
                                    int a = rs.getInt("PjNo");
                                    inv_pno.getItems().add(a);
                                    SD[5] = false;
                                }
                                sql1 = "SELECT Ino FROM `invoice` WHERE 1 ";
                                stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql1);
                                rs = stmt.executeQuery();
                                inv_invbox.getItems().clear();
                                while (rs.next()) {
                                    String a = rs.getString(1);
                                    inv_invbox.getItems().add(a);
                                }

                            } catch (SQLException ex) {
                                Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            //SalesHam.fireEvent(new Event(MouseEvent.MOUSE_PRESSED));
                        } else {
                            timer.cancel();  // Terminates this timer, discarding any currently scheduled tasks.
                            timer.purge();   // Removes all cancelled tasks from this timer's task queue.
                        }

                        if (tock) {
                            threadtock();
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        };

        timer.schedule(delayedThreadStartTask, 1500);//0.5 second
    }
    ;
 
 //columns for table1
 TableColumn indexCol = new TableColumn("S. No.");
    TableColumn desCol = new TableColumn("Description");
    TableColumn quantityCol = new TableColumn("Quantity");
    TableColumn unitCol = new TableColumn("Unit/(SGD)");
    TableColumn totalCol = new TableColumn("Total (SGD)");

    void newEnquiryPane_PriceBoxFill_Awin(TableView<Person> tables, ObservableList<Person> data) {
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

        tables.getColumns().addAll(indexCol, desCol, quantityCol, unitCol, totalCol);

        indexCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("firstName")
        );
        desCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("lastName")
        );
        quantityCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("email")
        );

        unitCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("remark")
        );
        totalCol.setCellValueFactory(new PropertyValueFactory<Person, String>("total"));

        tables.setItems(data);
        //return tables;
    }

    TableColumn SNCol = new TableColumn("S.N");
    TableColumn PosCol = new TableColumn("Position");
    TableColumn NRCol = new TableColumn("Normal Rate/HR(S$) (Mon to Fri 8am to 5pm)");
    TableColumn BeyondCol = new TableColumn("Beyond Normal Hours and Saturdays (S$)");
    TableColumn HolidayCol = new TableColumn("Sundays and Public Holidays (S$)");
    TableColumn remarkCol = new TableColumn("Remarks");

    void newEnquiryPane_PriceBoxFill_Steels(TableView<Person2> tables, ObservableList<Person2> data) {
        SNCol.setSortable(false);
        PosCol.setSortable(false);
        NRCol.setSortable(false);
        BeyondCol.setSortable(false);
        HolidayCol.setSortable(false);
        remarkCol.setSortable(false);
        SNCol.setPrefWidth(50);
        PosCol.setPrefWidth(200);
        BeyondCol.setPrefWidth(200);
        HolidayCol.setPrefWidth(200);
        remarkCol.setPrefWidth(200);
        NRCol.setPrefWidth(200);

        tables.getColumns().addAll(SNCol, PosCol, NRCol, BeyondCol, HolidayCol, remarkCol);

        SNCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("SN")
        );
        PosCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("Position")
        );
        NRCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("NormalRate")
        );

        BeyondCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("BeyondNormalHours")
        );
        HolidayCol.setCellValueFactory(new PropertyValueFactory<Person, String>("Holidays"));

        remarkCol.setCellValueFactory(new PropertyValueFactory<Person, String>("Remarks"));

        tables.setItems(data);
    }

    TableColumn Item = new TableColumn("S. No.");
    TableColumn DESCOl = new TableColumn("Description");
    TableColumn QtyCOl = new TableColumn("Quantity");
    TableColumn UtCol = new TableColumn("Unit/(SGD)");
    TableColumn Total = new TableColumn("Total (SGD)");

    void newInvoicePane_PriceBoxFill(TableView<Person3> tables, ObservableList<Person3> data) {
        Item.setSortable(false);
        DESCOl.setSortable(false);
        QtyCOl.setSortable(false);
        UtCol.setSortable(false);
        Total.setSortable(false);
        Item.setPrefWidth(50);
        DESCOl.setPrefWidth(250);
        QtyCOl.setPrefWidth(200);
        UtCol.setPrefWidth(250);
        Total.setPrefWidth(100);

        tables.getColumns().addAll(Item, DESCOl, QtyCOl, UtCol, Total);

        Item.setCellValueFactory(
                new PropertyValueFactory<Person, String>("ItemNo")
        );
        DESCOl.setCellValueFactory(
                new PropertyValueFactory<Person, String>("Des")
        );
        QtyCOl.setCellValueFactory(
                new PropertyValueFactory<Person, String>("Qty")
        );

        UtCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("UnitPrice")
        );
        Total.setCellValueFactory(new PropertyValueFactory<Person, String>("Total"));

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
        int[] mxval = new int[100000];
        try {
            if (Edate.getValue().toString().isEmpty() || ENo.getText().trim().isEmpty() || cmp.getValue().isEmpty() || EDes.getText().trim().isEmpty()
                    || CName.getText().trim().isEmpty() || CPhone.getText().trim().isEmpty() || Cmail.getText().trim().isEmpty() || Cadd.getText().trim().isEmpty()) {
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
                            //The below code is used to add a new customer with a sequential CID number.
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
                            stmt.setString(1, CName.getText().trim());
                            stmt.setString(2, Cmail.getText().trim());
                            stmt.setString(3, CPhone.getText().trim());
                            stmt.setString(4, Cadd.getText().trim());
                            rs = stmt.executeQuery();

                            rs.next();
                            cid = rs.getString(1);
                        }

                        //The below code is used to fetch enquiry numbers with the same details.
                        String sql1 = "Select * from enquiry where Eqno = ? and date1 = ? and Cmpname = ? and CID = ? ; ";
                        stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql1);
                        stmt.setString(1, ENo.getText().trim());
                        stmt.setString(2, Edate.getValue().toString());
                        stmt.setString(3, cmp.getValue());
                        stmt.setString(4, cid);
                        rs = stmt.executeQuery();

                        //The below code is used to replace some character due to duplicate enquiry numbers.
                        if (rs.next()) {
                            eno = rs.getString(1);
                            eno = eno + "_r";
                            Utilities.AlertBox.notificationInfo("Note", "This enquiry number has been"
                                    + " repeated by the same customer on the same"
                                    + " date. Therefore the enquiry "
                                    + "number has been replaced with the following : " + eno);
                        } else {
                            eno = ENo.getText().trim();
                        }

                        //The below code is used to insert the details into the enquiry table
                        String sql3 = "INSERT INTO `enquiry`(`Eqno`, `Date1`, `Cmpname`, `Subject`, `CID`) VALUES (?,?,?,?,?)";
                        stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql3);
                        stmt.setString(1, eno);
                        stmt.setString(2, Edate.getValue().toString());
                        stmt.setString(3, cmp.getValue());
                        stmt.setString(4, EDes.getText().trim());
                        stmt.setString(5, cid);
                        stmt.executeUpdate();
                        Utilities.AlertBox.notificationInfo("Success", "New enquiry details saved");
                        cmpname = cmp.getValue();
                        if (Utilities.AlertBox.alertoption("Quotation", "Do you want to generate the quotation number?", " Note that once a quotation number is generated, "
                                + "you can edit/revise the quotation in the Quotation Pane.")) {
                            if (cmpname.equals("Awin")) {
                                table12.setDisable(true);
                                table12.setVisible(false);
                                table1.setDisable(false);
                                table1.setVisible(true);
                                table1.getItems().clear();
                                table1.getColumns().clear();
                                ObservableList<Person> data;
                                data = FXCollections.observableArrayList();
                                for (int z = 1; z < 101; z++) {
                                    data.add(new Person(String.valueOf(z), "", "", "", ""));
                                }
                                newEnquiryPane_PriceBoxFill_Awin(table1, data);
                                table1.setEffect(new ColorAdjust());
                            } else {
                                table1.setDisable(true);
                                table1.setVisible(false);
                                table12.setDisable(false);
                                table12.setVisible(true);
                                table12.getItems().clear();
                                table12.getColumns().clear();
                                ObservableList<Person2> data;
                                data = FXCollections.observableArrayList();
                                for (int z = 0; z < 100; z++) {
                                    data.add(new Person2("", "", "", "", "", ""));
                                }

                                newEnquiryPane_PriceBoxFill_Steels(table12, data);
                                table12.setEffect(new ColorAdjust());
                            }
                            String compname1 = cmp.getValue();
                            String compname = cmp.getValue();
                            if (compname.equalsIgnoreCase("Awin")) {
                                compname = "AE";
                            } else {
                                compname = "SC";
                            }
                            String date = Utilities.Date.Date();
                            date = date.substring(2, 5);
                            compname = date + compname;
                            compname = compname + "-QT-";

                            System.out.println(compname);
                            String descr = EDes.getText();
                            Statement st;

                            try {
                                String suql = "SELECT Qno FROM `qoutation` WHERE 1";
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
                                //bubble sorted the array and picked the last element to obtain the max value
                                for (int x = 0; x < i - 1; x++) {
                                    for (int y = 0; y <= i - x - 1; y++) {
                                        if (mxval[y] > mxval[y + 1]) {
                                            temp = mxval[y];
                                            mxval[y] = mxval[y + 1];
                                            mxval[y + 1] = temp;
                                        }

                                    }
                                }
                                temp = mxval[i];
                                System.out.println("temp=" + temp);

// this is the auto quote gen thing 
                                CallableStatement cs;
                                String sql008 = "{ call quotelastdigitautogen(?,?)}";
                                cs = com.mycompany.snp.MainApp.conn.prepareCall(sql008);
                                cs.registerOutParameter(1, java.sql.Types.INTEGER);
                                cs.setInt(2, temp);
                                cs.executeQuery();
                                int dig = cs.getInt(1);
                                if (dig < 10) {
                                    dg = String.valueOf(dig);
                                    dg = "00" + dg;
                                } else if (dig >= 10 && dig < 100) {
                                    dg = String.valueOf(dig);
                                    dg = "0" + dg;
                                } else {
                                    dg = String.valueOf(dig);
                                }
                                //18-AE or SC - QT - 001
                                compname = compname + dg + ".Rev.0";
                                Qno.setText(compname);
                                System.out.println(compname);
                                Utilities.AlertBox.notificationWarn("Quotation Number", "Please make sure you that you have noted down the generated quotation number.");

                                suql = "INSERT INTO `qoutation`(`QNo`) VALUES (?)";
                                stmt = com.mycompany.snp.MainApp.conn.prepareStatement(suql);
                                stmt.setString(1, compname);
                                stmt.executeUpdate();
                                String suql1 = "INSERT INTO `eqrel`(`Eno`, `QNo`, `Date1`, `Cmpname`, `CID`) VALUES (?,?,?,?,?)";
                                stmt = com.mycompany.snp.MainApp.conn.prepareStatement(suql1);
                                stmt.setString(1, eno);
                                stmt.setString(2, compname);
                                stmt.setString(3, Edate.getValue().toString());
                                stmt.setString(4, compname1);
                                stmt.setString(5, cid);

                                stmt.executeUpdate();

                            } catch (SQLException exe) {
                                Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, exe);
                                Utilities.AlertBox.notificationWarn("Error", "Oops something went wrong!");
                                Utilities.AlertBox.showErrorMessage(exe);
                            }

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
    }

    void Quotation_insert_into_awin_table(String qo, TableView<Person> tables) {//method to insert quotaion details into database for awin
        PreparedStatement stmt;
        try {

            //first deleting quotations details of the particular qno
            String suqdel = "DELETE FROM `quotationdetails_awin` WHERE qno=?";

            stmt = com.mycompany.snp.MainApp.conn.prepareStatement(suqdel);
            stmt.setString(1, qo);
            stmt.executeUpdate();
            System.out.println("the deleting is done if the contents were present before");

            ObservableList<Person> trc;
            trc = FXCollections.observableArrayList(tables.getItems());
            trc.add(new Person("", "", "", "", ""));
            int i = 0;
            while (i < 100) {
                System.out.println("IN while loop");
                Person p = trc.get(i);
                if (p.getLastName().getText().trim().equalsIgnoreCase("")) {
                    System.out.println("done table awin quotation");
                    break;
                } else {
                    /* System.out.print(p.getFirstName().getText()+"\t");
                        System.out.print(p.getLastName().getText()+"\t");
                        System.out.print(p.getEmail().getText()+"\t");
                        System.out.print(p.getRemark().getText()+"\t");
                        System.out.println(p.getTotal().getText()+"\t");
                     */

                    String d = p.getLastName().getText();
                    int q = Integer.parseInt(p.getEmail().getText());
                    int r = Integer.parseInt(p.getRemark().getText());
                    long s = Long.parseLong(p.getTotal().getText());

                    System.out.println("the quotation no is " + qo);
                    int no = Integer.parseInt(p.getFirstName().getText());
                    try {

                        //first deleting quotations details of the particular qno
                        String suql1 = "INSERT INTO `quotationdetails_awin`(`Sno`, `Des`, `quantity`, `unit`, `total`, `qno`) VALUES (?,?,?,?,?,?)";
                        stmt = com.mycompany.snp.MainApp.conn.prepareStatement(suql1);
                        stmt.setInt(1, no);
                        stmt.setString(2, d);
                        stmt.setInt(3, q);
                        stmt.setInt(4, r);
                        stmt.setLong(5, s);
                        stmt.setString(6, qo);
                        stmt.executeUpdate();
                    } catch (SQLException exe) {
                        Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, exe);
                        Utilities.AlertBox.notificationWarn("Error", "Oops something went wrong!");
                        Utilities.AlertBox.showErrorMessage(exe);
                    }

                }
                i++;
            }
            if (i == 0) {
                Utilities.AlertBox.notificationWarn("Blank Quotation", "The quotation box seems to be blank.");
            } else {
                Utilities.AlertBox.notificationInfo("Success", "Your Quotation was saved successfully!");
                edit_button_hit_in_QPane = false;
                table11.setEditable(false);
                table111.setEditable(false);
            }

        } catch (Exception e) {
            Utilities.AlertBox.notificationWarn("Error", "Oops something went wrong!");
            Utilities.AlertBox.showErrorMessage(e);
        }
    }

    void Quotation_insert_into_steel(String qo, TableView<Person2> tables) {//method to insert quotaion details into database for steel

        PreparedStatement stmt;
        try {

            //deleting befoehand just in case...
            String suqdel = "DELETE FROM `quotationdetails_steels` WHERE qno=?";

            stmt = com.mycompany.snp.MainApp.conn.prepareStatement(suqdel);
            stmt.setString(1, qo);
            stmt.executeUpdate();
            System.out.println("the deleting is done if the contents were present before");

            ObservableList<Person2> trc;
            trc = FXCollections.observableArrayList(tables.getItems());
            trc.add(new Person2("", "", "", "", "", ""));
            int i = 0;
            while (i < 100) {
                Person2 p = trc.get(i);
                if (p.getPosition().getText().trim().equalsIgnoreCase("")) {
                    break;
                } else {
                    /* System.out.print(p.getFirstName().getText()+"\t");
                        System.out.print(p.getLastName().getText()+"\t");
                        System.out.print(p.getEmail().getText()+"\t");
                        System.out.print(p.getRemark().getText()+"\t");
                        System.out.println(p.getTotal().getText()+"\t");
                     */

                    String d = p.getPosition().getText();
                    String q = p.getNormalRate().getText();
                    String r = p.getBeyondNormalHours().getText();
                    String s = p.getRemarks().getText();
                    String h = p.getHolidays().getText();

                    System.out.println("the quotation no is " + qo);
                    int no = Integer.parseInt(p.getSN().getText());

                    try {//deleting befoehand just in case...

                        String suql1 = "INSERT INTO `quotationdetails_steels`(`Sno`, `Pos`, `NormalRate`, `BeyondNormalRate`, `Holidays`, `Remarks`, `qno`) VALUES (?,?,?,?,?,?,?)";
                        stmt = com.mycompany.snp.MainApp.conn.prepareStatement(suql1);
                        stmt.setInt(1, no);
                        stmt.setString(2, d);
                        stmt.setString(3, q);
                        stmt.setString(4, r);
                        stmt.setString(5, h);
                        stmt.setString(6, s);
                        stmt.setString(7, qo);
                        stmt.executeUpdate();
                    } catch (SQLException exe) {
                        Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, exe);
                        Utilities.AlertBox.notificationWarn("Error", "Oops something went wrong!");
                        Utilities.AlertBox.showErrorMessage(exe);
                    }

                }
                i++;
            }
            if (i == 0) {
                Utilities.AlertBox.notificationWarn("Blank Quotation", "The quotation box seems to be blank.");
            } else {
                Utilities.AlertBox.notificationInfo("Done", "");
            }

        } catch (Exception e) {
            Utilities.AlertBox.notificationWarn("Error", "Oops something went wrong!");
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
        String qo = Qno.getText();
        System.out.println(qo.substring(3, 5));
        if (qo.substring(3, 5).equalsIgnoreCase("AE")) {
            // if (cmp.getValue()=="Awin"){
            //this is for awin table 

            Quotation_insert_into_awin_table(qo, table1);
        } else if (qo.substring(3, 5).equalsIgnoreCase("SC")) {
            //for steels table 
            Quotation_insert_into_steel(qo, table12);

        }

    }

    void generate_Awin_Table(boolean input) {
        try {
            //here input is used to indicate if table is editable or not
            String qno = Qno1.getText();
            table111.setDisable(true);
            table111.setVisible(false);
            table111.setEditable(false);
            table11.setDisable(false);
            table11.setVisible(true);
            table11.setEditable(false);
            table11.getItems().clear();
            table11.getColumns().clear();
            //fill details of Awin table
            String sql1 = "SELECT * FROM `QuotationDetails_Awin` WHERE qno = ?";
            PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql1);
            stmt.setString(1, qno);
            ResultSet rs = stmt.executeQuery();
            ObservableList<Person> data;
            data = FXCollections.observableArrayList();
            while (rs.next()) {
                Person p = new Person(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                p.setEdit(input);
                data.add(p);
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + "  " + rs.getString(5));
            }
            newEnquiryPane_PriceBoxFill_Awin(table11, data);
            table11.setEditable(false);
            table11.setEffect(new ColorAdjust());
        } catch (SQLException ex) {
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void generate_Awin_Table(boolean input, boolean extra) {
        try {
            //here input is used to indicate if table is editable or not
            String qno = Qno1.getText();
            table111.setDisable(true);
            table111.setVisible(false);
            table111.setEditable(false);
            table11.setDisable(false);
            table11.setVisible(true);
            table11.setEditable(false);
            table11.getItems().clear();
            table11.getColumns().clear();
            //fill details of Awin table
            String sql1 = "SELECT * FROM `QuotationDetails_Awin` WHERE qno = ?";
            PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql1);
            stmt.setString(1, qno);
            ResultSet rs = stmt.executeQuery();
            ObservableList<Person> data;
            data = FXCollections.observableArrayList();
            int count = 0;
            while (rs.next()) {
                Person p = new Person(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                p.setEdit(input);
                data.add(p);
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + "  " + rs.getString(5));
                count++;
            }
            if (extra) {
                for (int o = count + 1; o < count + 100; o++) {
                    data.add(new Person(String.valueOf(o), "", "", "", ""));
                }
            }
            newEnquiryPane_PriceBoxFill_Awin(table11, data);
            table11.setEditable(false);
            table11.setEffect(new ColorAdjust());
        } catch (SQLException ex) {
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void generate_Steels_Table(boolean input) {
        //here input is used to indicate if table is editable or not
        try {
            //Generate the Steels Quotation Table 
            String qno = QnoBox.getValue();
            table11.setDisable(true);
            table11.setVisible(false);
            table11.setEditable(false);
            table111.setDisable(false);
            table111.setVisible(true);
            table111.setEditable(false);
            table111.getItems().clear();
            table111.getColumns().clear();
            String sql1 = "SELECT * FROM `QuotationDetails_Steels` WHERE qno = ?";
            PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql1);
            stmt.setString(1, qno);
            ResultSet rs = stmt.executeQuery();
            ObservableList<Person2> data;
            data = FXCollections.observableArrayList();
            while (rs.next()) {
                Person2 p = new Person2(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                p.setEdit(input);
                data.add(p);
            }
            newEnquiryPane_PriceBoxFill_Steels(table111, data);
            table111.setEffect(new ColorAdjust());
            //fill details of Steels Table

        } catch (SQLException ex) {
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void generate_Steels_Table(boolean input, boolean extra) {
        //here input is used to indicate if table is editable or not
        try {
            //Generate the Steels Quotation Table 
            String qno = QnoBox.getValue();
            table11.setDisable(true);
            table11.setVisible(false);
            table11.setEditable(false);
            table111.setDisable(false);
            table111.setVisible(true);
            table111.setEditable(false);
            table111.getItems().clear();
            table111.getColumns().clear();
            String sql1 = "SELECT * FROM `QuotationDetails_Steels` WHERE qno = ?";
            PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql1);
            stmt.setString(1, qno);
            ResultSet rs = stmt.executeQuery();
            ObservableList<Person2> data;
            data = FXCollections.observableArrayList();
            while (rs.next()) {
                Person2 p = new Person2(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                p.setEdit(input);
                data.add(p);
            }
            if (extra) {
                for (int o = 0; o < 100; o++) {
                    data.add(new Person2("", "", "", "", "", ""));
                }
            }
            newEnquiryPane_PriceBoxFill_Steels(table111, data);
            table111.setEffect(new ColorAdjust());
            //fill details of Steels Table

        } catch (SQLException ex) {
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Fill_details_of_existing_Qno_and_Eno(MouseEvent event) {
        //retrieve the details of enquiry using the quotation number.
        edit_button_hit_in_QPane = false;
        try {
            System.out.println("sdfsdfsfddsfsdfsdfsdfsdfs");
            String qno = QnoBox.getValue();
            String sql = "SELECT e.Date1,e.Eqno,e.Cmpname,e.Subject,c.Name,c.phone,c.email,c.Address  FROM eqrel er join enquiry e on er.eno=e.eqno "
                    + "and er.Date1=e.Date1 and er.cmpname=e.cmpname and er.cid=e.cid join customer c on e.cid=c.cid WHERE er.qno= ? ;";
            PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
            stmt.setString(1, qno);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

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
            if (ECom1.getText().equals("Awin")) {
                //Generate the Awin Quotation Table 
                generate_Awin_Table(false);

            } else {
                if (ECom1.getText().equals("Steels")) {
                    //Generate the Steels Quotation Table 
                    generate_Steels_Table(false);
                }
            }
        } catch (NullPointerException e) {
            Utilities.AlertBox.notificationWarn("Error", "Please select a quotation number first.");
        } catch (SQLException ex) {
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static boolean edit_button_hit_in_QPane = false;
    static boolean revise_button_hit_in_QPane = false;

    @FXML
    private void Edit_Quotation_in_QuotationPane(MouseEvent event) {
        if (ECom1.getText().equals("Awin")) {
            table11.setEditable(true);
            table111.setEditable(false);
            generate_Awin_Table(true, true);//overloaded
            edit_button_hit_in_QPane = true;
            revisedQno = "";
        } else if (ECom1.getText().equals("Steels")) {
            table11.setEditable(true);
            table111.setEditable(false);
            edit_button_hit_in_QPane = true;
            generate_Steels_Table(true, true);//overloaded
            revisedQno = "";
        }

    }

    @FXML
    private void Save_Quotation_in_QuotationPane(MouseEvent event) {
        String qno = Qno1.getText();
        if (edit_button_hit_in_QPane) {
            if (ECom1.getText().equals("Awin")) {
                Quotation_insert_into_awin_table(qno, table11);
                generate_Awin_Table(false);

            } else if (ECom1.getText().equals("Steels")) {
                Quotation_insert_into_steel(qno, table111);
                generate_Steels_Table(false);

            }

        } else if (revisedQno != "") {
            String k = ENo1.getText();
            PreparedStatement stmt;
            try {

                String sql5 = "Select e.cid from eqrel e where e.qno=? ";//qnoforquery
                stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql5);
                stmt.setString(1, qnoforquery);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    Integer abc = rs.getInt(1);
                    String suql1 = "INSERT INTO `qoutation`(`Qno`,`RevNo`) VALUES (?,?)";
                    stmt = com.mycompany.snp.MainApp.conn.prepareStatement(suql1);
                    stmt.setString(1, revisedQno);
                    stmt.setInt(2, revisedno);
                    stmt.executeUpdate();
                    String suql2 = "INSERT INTO `eqrel`(`Eno`, `QNo`, `Date1`, `Cmpname`, `CID`) VALUES (?,?,?,?,?)";
                    stmt = com.mycompany.snp.MainApp.conn.prepareStatement(suql2);
                    stmt.setString(1, k);
                    stmt.setString(2, revisedQno);
                    stmt.setString(3, Edate1.getValue().toString());
                    stmt.setString(4, CName1.getText());
                    stmt.setInt(5, abc);

                    stmt.executeUpdate();
                    Qno1.setText(revisedQno);
                    if (ECom1.getText().equals("Awin")) {
                        Quotation_insert_into_awin_table(revisedQno, table11);

                        generate_Awin_Table(false);

                    } else if (ECom1.getText().equals("Steels")) {
                        Quotation_insert_into_steel(revisedQno, table111);
                        generate_Steels_Table(false);

                    }
                }
            } catch (SQLException exe) {
                Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, exe);
                Utilities.AlertBox.notificationWarn("Error", "Oops something went wrong!");
                Utilities.AlertBox.showErrorMessage(exe);
            }

        } else {
            Utilities.AlertBox.notificationInfo("Sticky Buttons", "The quotation has already been saved.");
        }
    }//revision of quotation also handled here

    @FXML
    private void Generate_Quotation_in_QuotationPane(MouseEvent event) {
    }
    static String revisedQno = "";
    static int revisedno;
    static String qnoforquery = "";

    @FXML
    private void Revise_Quotation_in_QuotationPane(MouseEvent event) {
        if (Utilities.AlertBox.alertoption("Revision", "You just clicked the Revise button!", " Are you sure you want to revise quotation number :" + Qno1.getText())) {
            revise_button_hit_in_QPane = true;
            edit_button_hit_in_QPane = false;

            try {
                qnoforquery = Qno1.getText();
                String qno = Qno1.getText();
                String suql = "SELECT Qno,RevNo FROM `qoutation` WHERE qno= ? ;";
                PreparedStatement st = com.mycompany.snp.MainApp.conn.prepareStatement(suql);
                st.setString(1, qno);
                ResultSet rs = st.executeQuery();

                while (rs.next()) {

                    int k = Integer.valueOf(rs.getString(2));

                    k++;
                    revisedno = k;
                    //if(k==1){
                    //first revision
                    // revisedQno=Qno1.getText()+".Rev."+String.valueOf(k);

                    // }
                    //else{
                    //not first revision
                    int len = Qno1.getText().length();
                    int v = Qno1.getText().lastIndexOf('.');
                    String s = Qno1.getText().substring(v + 1, len);
                    int z = Integer.valueOf(s);
                    z++;
                    String x = Qno1.getText().substring(0, v + 1);
                    revisedQno = x + String.valueOf(z);
                    // }
                    System.out.println(revisedQno);
                }

                if (ECom1.getText().equals("Awin")) {
                    Quotation_insert_into_awin_table(qno, table11);
                    generate_Awin_Table(true, true);

                } else if (ECom1.getText().equals("Steels")) {
                    Quotation_insert_into_steel(qno, table111);
                    generate_Steels_Table(true, true);

                }

            } catch (Exception e) {
                Utilities.AlertBox.showErrorMessage(e);
            }
        } else {
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
            tock = false;
            Stage stage;
            Parent root;
            stage = (Stage) table1.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    static String qno_static = "";

    @FXML
    private void tick_in_project(MouseEvent event) {
        try {
            qno_static = QnoBox1.getValue();
            String sql = "SELECT `PjNo` FROM `qprel` where  qno =? ;";
            PreparedStatement ps = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
            ps.setString(1, qno_static);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                //fill datails
                int PjNOs = rs.getInt(1);
                String sql1 = "SELECT `PNo`, `PjNo`, `Value`, `Date`, `EstDate`, `Des` FROM `product` WHERE PjNo=?";
                ps = com.mycompany.snp.MainApp.conn.prepareStatement(sql1);
                ps.setInt(1, PjNOs);
                rs = ps.executeQuery();
                while (rs.next()) {
                    projcomp.setVisible(false);
                    projcomp.setDisable(true);
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
            } else {
                //setEditable(true)
                projcomp.setVisible(false);
                projcomp.setDisable(true);
                String sql1 = "SELECT IFNULL(MAX(`PjNo`)+1,1) as 'your value'  FROM `product` WHERE 1;";
                ps = com.mycompany.snp.MainApp.conn.prepareStatement(sql1);
                // ps.setInt(1,PjNOs);
                rs = ps.executeQuery();
                while (rs.next()) {
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

    void insert_into_proj_table() {
        try {
            int pjno = Integer.parseInt(PjNo.getText());
            int prno = Integer.parseInt(PrNo.getText());
            int esval = Integer.parseInt(EsVal.getText());
            String daterec = DateRec.getValue().toString();
            String estrec = EstDate.getValue().toString();
            String qnum = qno_static;
            String prodes = ProDes.getText();

            String sql = "INSERT INTO `product`(`PNo`, `PjNo`, `Value`, `Date`, `EstDate`,`Des`) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
            ps.setInt(1, prno);
            ps.setInt(2, pjno);
            ps.setInt(3, esval);
            ps.setString(4, daterec);
            ps.setString(5, estrec);
            ps.setString(6, prodes);

            ps.executeUpdate();

            String sql1 = "INSERT INTO `qprel`(`Qno`, `PjNo`) VALUES (?,?)";
            ps = com.mycompany.snp.MainApp.conn.prepareStatement(sql1);
            ps.setString(1, qnum);
            ps.setInt(2, pjno);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
            Utilities.AlertBox.showErrorMessage(ex);
            Utilities.AlertBox.notificationInfo("Sticky Buttons", "The project information has already been saved.");
        }
    }

    void insert_into_proj_table(boolean b) {
        try {
            int pjno = Integer.parseInt(PjNo.getText());
            int prno = Integer.parseInt(PrNo.getText());
            int esval = Integer.parseInt(EsVal.getText());
            String daterec = DateRec.getValue().toString();
            String estrec = EstDate.getValue().toString();
            // String qnum=qno_static;
            String prodes = ProDes.getText();

            String sql = "UPDATE `product` SET `PNo`=?,`Value`=?,`Date`=?,`EstDate`=?,`Des`=?,`Comp`=?,`Compdate`=? WHERE PJNO = ? ;";
            PreparedStatement ps = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
            ps.setInt(1, prno);

            ps.setInt(2, esval);
            ps.setString(3, daterec);
            ps.setString(4, estrec);
            ps.setString(5, prodes);
            ps.setBoolean(6, b);
            ps.setString(7, LocalDate.now().toString());
            ps.setInt(8, pjno);

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
            Utilities.AlertBox.showErrorMessage(ex);
            Utilities.AlertBox.notificationInfo("Sticky Buttons", "The project information has already been saved.");
        }
    }

    @FXML
    private void save_in_project(MouseEvent event) {

        if (edit_button_hit_in_PPane) {
            try {

                String comp = "";
                boolean b = projcomp.isSelected();
                insert_into_proj_table(b);
                Utilities.AlertBox.notificationInfo("Updated", "The project information has been updated.");

            } catch (Exception e) {
                Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, e);
                Utilities.AlertBox.notificationWarn("Error", "Error in input!");
                Utilities.AlertBox.showErrorMessage(e);

            }

        } else {

            insert_into_proj_table();
            Utilities.AlertBox.notificationInfo("Saved", "The project information has  been saved.");
        }
        PjNo.setEditable(false);
        PrNo.setEditable(false);
        EsVal.setEditable(false);
        DateRec.setEditable(false);
        EstDate.setEditable(false);
        ProDes.setEditable(false);
        edit_button_hit_in_PPane = false;
        projcomp.setVisible(false);
        projcomp.setDisable(true);

    }
    static boolean edit_button_hit_in_PPane = false;

    @FXML

    private void edit_in_project(MouseEvent event) {
        edit_button_hit_in_PPane = true;
        PjNo.setEditable(false);
        PrNo.setEditable(true);
        EsVal.setEditable(true);
        DateRec.setEditable(true);
        EstDate.setEditable(true);
        ProDes.setEditable(true);
        projcomp.setVisible(true);
        projcomp.setDisable(false);
        Utilities.AlertBox.notificationInfo("Edit mode", "You're now in edit mode.");
    }

    @FXML
    private void refresh_project_pane(MouseEvent event) {
        try {
            QnoBox1.getItems().clear();//quotation combo box
            String sql = "SELECT `Qno` FROM `qoutation` WHERE 1";
            PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
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

        } catch (Exception e) {
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
        try {
            eqno_del.getItems().clear();
            cmp_del.getItems().clear();
            email_del.getItems().clear();

            String sql = "SELECT `Eqno` FROM `enquiry` WHERE 1 order by `Date1` DESC ";
            PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                eqno_del.getItems().add(rs.getString(1));
                //System.out.println(rs.getString(1));
            }

            cmp_del.getItems().add("AWIN");
            cmp_del.getItems().add("STEEL");

            String sql1 = "SELECT email FROM `customer` WHERE 1 order by `CID` DESC";
            stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql1);
            rs = stmt.executeQuery();
            while (rs.next()) {
                email_del.getItems().add(rs.getString(1));

            }

        } catch (Exception e) {
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, e);
            Utilities.AlertBox.showErrorMessage(e);
        }
    }

    @FXML
    private void Select_for_delete_of_Enquiry(MouseEvent event) {
        try {
            if (eqno_del.getValue().toString().isEmpty() || cmp_del.getValue().toString().isEmpty() || email_del.getValue().toString().isEmpty() || date_del.getValue().toString().isEmpty()) {
                Utilities.AlertBox.notificationWarn("Error", "Some of the fields seem to be empty");
            } else {
                String sql = "SELECT * FROM `enquiry` e NATURAL JOIN `customer` c WHERE eqno=? "
                        + "and email=? and cmpname=? and `Date1`=? ";
                PreparedStatement ps;
                //
                System.out.println("inside else block nice statements really.");
                boolean f = true;
                ps = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
                ps.setString(1, eqno_del.getValue());
                ps.setString(2, email_del.getValue());
                ps.setString(3, cmp_del.getValue());
                ps.setString(4, date_del.getValue().toString());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    f = false;
                    System.out.println("rs not 0");
                    if (Utilities.AlertBox.alertoption("Decline", "Enquiry Deletion", "Are you sure you want to decline this enquiry?")) {
                        String ar[] = {"lack of man power", "lack of equipments", "too many requests", "lack of raw materials", "not profitable", "others"};
                        String res;

                        res = Utilities.AlertBox.alterchoice(ar, "Reason", "Any particular reason for declining "
                                + "this enquiry?", "Please choose from the drop down menu below.");
                        if (!res.equals("Cancel")) {
                            CallableStatement cs;
                            String sql008 = "{ call enquiry_del_bkup(?,?,?,?,?)}";
                            cs = com.mycompany.snp.MainApp.conn.prepareCall(sql008);

                            cs.setString(1, eqno_del.getValue());
                            cs.setString(2, email_del.getValue());
                            cs.setDate(3, java.sql.Date.valueOf(date_del.getValue()));
                            cs.setString(4, cmp_del.getValue());
                            cs.setString(5, res);

                            cs.executeQuery();
                            Utilities.AlertBox.notificationInfo("Success", "Enquiry " + eqno_del.getValue() + " has been deleted.");
                        } else {
                            break;
                        }

                    } else {
                        break;
                    }
                }
                if (f) {
                    Utilities.AlertBox.notificationInfo("Error", "Enquiry " + eqno_del.getValue() + " doesn't exist.");
                }

            }
        } catch (Exception e) {
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, e);
            Utilities.AlertBox.showErrorMessage(e);
            Utilities.AlertBox.notificationWarn("Error", "Some of the fields seem to be empty");
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
        /*                 @FXML
    private JFXComboBox<Integer> inv_pno;

    private Label pno_tick;

    private JFXComboBox<String> inv_invbox;

    private Label inv_tick;

    private JFXDatePicker due_date;

    private JFXTextField inv_no;

    private JFXTextField inv_cmp;

    private JFXTextField inv_tum;

    private JFXTextField inv_qno;

    private JFXTextField inv_po;

    private JFXTextField inv_sp;

    private JFXTextField inv_acc;

    private JFXTextArea inv_to;

    private TableView<Person3> inv_newtable;

    private JFXTextField inv_gst;

    private JFXTextField inv_total;

    private JFXTextField inv_amt;
        
         */
        Invoice_Save_InvPane();
    }

    @FXML
    private void Select_for_quotation_generation(MouseEvent event) {
        //please change codes
        try {
            if (eqno_del1.getValue().toString().isEmpty() || cmp_del1.getValue().toString().isEmpty() || email_del1.getValue().toString().isEmpty() || date_del1.getValue().toString().isEmpty()) {
                Utilities.AlertBox.notificationWarn("Error", "Some of the fields seem to be empty");
            } else {
                String sql = "SELECT CID FROM `enquiry` e NATURAL JOIN `customer` c WHERE eqno=? "
                        + "and email=? and cmpname=? and `Date1`=? ";
                PreparedStatement ps;
                //
                System.out.println("inside else block nice statements really.");
                boolean f = true;
                ps = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
                ps.setString(1, eqno_del1.getValue());
                ps.setString(2, email_del1.getValue());
                ps.setString(3, cmp_del1.getValue());
                ps.setString(4, date_del1.getValue().toString());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    f = false;
                    System.out.println("rs not 0");
                    String a = rs.getString(1);
                    PreparedStatement cs;
                    String sql008 = "SELECT IFNULL(MAX(CAST(SUBSTRING(`Qno`,10,3) AS SIGNED))+1,1) as 'your value'  FROM `qoutation` WHERE 1;";
                    //SELECT IFNULL(MAX(CAST(SUBSTRING(`Qno`, CHAR_LENGTH(`Qno`)-2) AS SIGNED))+1,1) as 'your value'  FROM `qoutation` WHERE 1;

                    cs = com.mycompany.snp.MainApp.conn.prepareStatement(sql008);
                    ResultSet rs1 = cs.executeQuery();

                    if (rs1.next()) {
                        int dig = Integer.valueOf(rs1.getString(1));
                        System.out.println(dig);
                        String compname1 = cmp_del1.getValue();
                        String compname = cmp_del1.getValue();
                        if (compname.equalsIgnoreCase("Awin")) {
                            compname = "AE";
                        } else {
                            compname = "SC";
                        }
                        String date = Utilities.Date.Date();
                        date = date.substring(2, 5);
                        compname = date + compname;
                        compname = compname + "-QT-";
                        String dg;
                        System.out.println(compname);
                        if (dig < 10) {
                            dg = String.valueOf(dig);
                            dg = "00" + dg;
                        } else if (dig >= 10 && dig < 100) {
                            dg = String.valueOf(dig);
                            dg = "0" + dg;
                        } else {
                            dg = String.valueOf(dig);
                        }
                        //18-AE or SC - QT - 001
                        System.out.println(compname);
                        compname = compname + dg + ".Rev.0";
                        Qno.setText(compname);
                        PreparedStatement stmt;

                        String suql = "INSERT INTO `qoutation`(`QNo`) VALUES (?)";
                        stmt = com.mycompany.snp.MainApp.conn.prepareStatement(suql);
                        stmt.setString(1, compname);
                        stmt.executeUpdate();

                        String suql1 = "INSERT INTO `eqrel`(`Eno`, `QNo`, `Date1`, `Cmpname`, `CID`) VALUES (?,?,?,?,?)";
                        stmt = com.mycompany.snp.MainApp.conn.prepareStatement(suql1);
                        stmt.setString(1, eqno_del1.getValue());
                        stmt.setString(2, compname);

                        stmt.setString(3, date_del1.getValue().toString());
                        stmt.setString(4, cmp_del1.getValue());
                        stmt.setInt(5, Integer.valueOf(a));

                        stmt.executeUpdate();
                        Utilities.AlertBox.notificationWarn("Quotation Number", "Please make sure you that you have noted down the generated quotation number.");
                    }

                    if (cmp_del1.getValue().equalsIgnoreCase("AWIN")) {
                        System.out.println("AWinnnnnnn");
                        table12.setDisable(true);
                        table12.setVisible(false);
                        table1.setDisable(false);
                        table1.setVisible(true);
                        table1.getItems().clear();
                        table1.getColumns().clear();
                        ObservableList<Person> data;
                        data = FXCollections.observableArrayList();
                        for (int z = 1; z < 101; z++) {
                            data.add(new Person(String.valueOf(z), "", "", "", ""));
                        }
                        newEnquiryPane_PriceBoxFill_Awin(table1, data);
                        table1.setEffect(new ColorAdjust());
                    } else {
                        System.out.println("ASteellslsdlsldlsl");
                        table1.setDisable(true);
                        table1.setVisible(false);
                        table12.setDisable(false);
                        table12.setVisible(true);
                        table12.getItems().clear();
                        table12.getColumns().clear();
                        ObservableList<Person2> data;
                        data = FXCollections.observableArrayList();
                        for (int z = 0; z < 100; z++) {
                            data.add(new Person2("", "", "", "", "", ""));
                        }

                        newEnquiryPane_PriceBoxFill_Steels(table12, data);
                        table12.setEffect(new ColorAdjust());
                    }
                }
                if (f) {
                    Utilities.AlertBox.notificationInfo("Error", "Enquiry " + eqno_del1.getValue() + " doesn't exist.");
                }
            }

        } catch (Exception e) {
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, e);
            Utilities.AlertBox.showErrorMessage(e);
            Utilities.AlertBox.notificationWarn("Error", "Some of the fields seem to be empty");
        }

    }

    @FXML
    private void back_for_quotation_generation(MouseEvent event) {
        eq_newpane.setEffect(new ColorAdjust());
        eq_newpane.setDisable(false);
        eq_delpane1.setVisible(false);
        eq_delpane1.setDisable(true);
    }
    String comp_inv_gst = "";
    static int combopno;

    @FXML
    private void tick_in_invoice(MouseEvent event) {


        /*    
=======
              PreparedStatement ps;
               ResultSet rs;
               inv_pno.getItems().clear();
        try {
             
               
>>>>>>> f7f8d55e2fab98543203565eef911fb67f92cbdd
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
        inv_cmp.setText(cn);
          String ssl="SELECT IFNULL(MAX(CAST(SUBSTRING(`INo`, CHAR_LENGTH(`IN`)-2) AS SIGNED))+1,1) as 'your value'  FROM `invoice` WHERE 1";
               ps= com.mycompany.snp.MainApp.conn.prepareStatement(ssl);
               ResultSet ri=ps.executeQuery();
               dt=dt+ri.getInt(1);
               inv_no.setText(dt);
               
    
        }catch (SQLException ex) {
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        try {
            PreparedStatement ps;
            String sl = "SELECT * from product p,qprel qp,eqrel er WHERE p.PjNo=qp.PjNo and qp.Qno=er.Qno and p.PjNo=?";
            ps = com.mycompany.snp.MainApp.conn.prepareStatement(sl);
            combopno = inv_pno.getValue();
            ps.setInt(1, combopno);
            ResultSet rs = ps.executeQuery();
            rs.next();
            inv_cmp.setText(rs.getString("Cmpname"));
            inv_qno.setText(rs.getString("Qno"));
            inv_po.setText(rs.getString("PNo"));
            String comint = rs.getString("Qno");
            int cval = rs.getInt("CID");
            String compname = comint.substring(3, 5);
            System.out.println("company name after ss is:" + compname);
            String date = Utilities.Date.Date();
            String dt = date.substring(2, 4);
            String d;

            if (inv_pno.getValue() < 10) {
                //dg=String.valueOf(dig);dg="00"+dg;
                dt = dt + "0" + inv_pno.getValue();
            } else //1801-AE-inv-001
            {
                dt = dt + inv_pno.getValue();
            }

            dt = dt + "-";
            if (compname.equalsIgnoreCase("AE")) {
                dt = dt + "AE";
                String ss = "SELECT `GST` FROM `company` WHERE cmpname like \"AWIN%\";";
                ps = com.mycompany.snp.MainApp.conn.prepareStatement(ss);
                ResultSet ri = ps.executeQuery();
                ri.next();
                comp_inv_gst = ri.getString(1);

            } else {
                dt = dt + "SC";
                String ss = "SELECT `GST` FROM `company` WHERE cmpname like \"STEEL%\";";
                ps = com.mycompany.snp.MainApp.conn.prepareStatement(ss);
                ResultSet ri = ps.executeQuery();
                ri.next();
                comp_inv_gst = ri.getString(1);
            }
            dt = dt + "-INV-";

            String ssl = "SELECT IFNULL(MAX(CAST(SUBSTRING(`INo`, CHAR_LENGTH(`INo`)-2) AS SIGNED))+1,1) as 'your value'  FROM `invoice` WHERE INO like \"%" + compname + "%\" ;";
            ps = com.mycompany.snp.MainApp.conn.prepareStatement(ssl);
            ResultSet ri = ps.executeQuery();
            ri.next();
            if (ri.getInt(1) < 10) {
                dt = dt + "00" + ri.getInt(1);
            } else if (ri.getInt(1) > 10 && ri.getInt(1) < 100) {
                dt = dt + "0" + ri.getInt(1);
            } else {
                dt = dt + ri.getInt(1);
            }

            inv_no.setText(dt);
            String tum = inv_tum.getText();
            String sp = inv_sp.getText();
            String acc = inv_acc.getText();
            String tot = inv_total.getText();

            String pl = "SELECT `Address`, `Name`, `email`, `phone` FROM `customer` WHERE CID=?";
            ps = com.mycompany.snp.MainApp.conn.prepareStatement(pl);
            ps.setInt(1, cval);
            ResultSet r = ps.executeQuery();
            r.next();
            String add = r.getString("Address");
            String nme = r.getString("Name");
            String em = r.getString("email");
            String phno = r.getString("phone");
            inv_to.setText(nme + "\n\n" + add + "\n\n" + em + "\n\n" + phno);
        } catch (Exception e) {
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, e);
        }
        inv_newtable.getColumns().clear();
        inv_newtable.getItems().clear();
        ObservableList<Person3> data;
        data = FXCollections.observableArrayList();
        for (int o = 0; o < 20; o++) {
            data.add(new Person3("", "", "", "", ""));
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
        try {
            eqno_del1.getItems().clear();
            cmp_del1.getItems().clear();
            email_del1.getItems().clear();

            String sql = "SELECT e.`Eqno` FROM `enquiry` e where 1 order by `Date1` DESC ";
            PreparedStatement stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                eqno_del1.getItems().add(rs.getString(1));
                //System.out.println(rs.getString(1));
            }

            cmp_del1.getItems().add("AWIN");
            cmp_del1.getItems().add("STEEL");

            String sql1 = "SELECT email FROM `customer` WHERE 1 order by `CID` DESC";
            stmt = com.mycompany.snp.MainApp.conn.prepareStatement(sql1);
            rs = stmt.executeQuery();
            while (rs.next()) {
                email_del1.getItems().add(rs.getString(1));

            }

        } catch (Exception e) {
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, e);
            Utilities.AlertBox.showErrorMessage(e);
        }
    }

    @FXML
    private void tick_out_invoice(MouseEvent event) {

        PreparedStatement ps;
        ResultSet rs;

        try {

            String sl = "SELECT i.`INo`, i.`Total_amt`, "
                    + "                     i.`Salesperson`, i.`Acc No`, i.`Termofpay`, "
                    + "                     pr.pno,q.qno, e.cmpname,pr.pjno,c.name,c.address,c.phone,c.email FROM "
                    + "                    `invoice` i join pirel p on p.ino=i.ino join product "
                    + "                     pr on p.pjno=pr.pjno join qprel q on q.pjno=pr.pjno join eqrel e on e.qno=q.qno "
                    + "join enquiry en on e.eno=en.eqno and e.date1=en.date1 and e.cmpname=en.cmpname "
                    + "                    and e.cid=en.cid join customer c on c.cid=en.cid WHERE i.ino=? ";
            ps = com.mycompany.snp.MainApp.conn.prepareStatement(sl);
            ps.setString(1, inv_invbox.getValue());
            rs = ps.executeQuery();
            while (rs.next()) {
                inv_no.setText(rs.getString(1));
                inv_total.setText(rs.getString(2));
                inv_sp.setText(rs.getString(3));
                inv_acc.setText(rs.getString(4));
                inv_tum.setText(rs.getString(5));
                inv_po.setText(rs.getString(6));
                inv_qno.setText(rs.getString(7));
                inv_cmp.setText(rs.getString(8));
                combopno = rs.getInt(9);
                inv_to.setText(rs.getString(10) + "\n\n" + rs.getString(11) + "\n\n" + rs.getString(13) + "\n\n" + rs.getString(12));
            }
            sl = "SELECT `Item/No`, `Descr`, `Qty`, `UnitPrice`, `total` FROM `invoice_details` i WHERE i.invno=?";
            ps = com.mycompany.snp.MainApp.conn.prepareStatement(sl);
            ps.setString(1, inv_invbox.getValue());
            rs = ps.executeQuery();
            ObservableList<Person3> data;
            data = FXCollections.observableArrayList();
            while (rs.next()) {

                data.add(new Person3(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));

            }
            for (int o = 0; o < 20; o++) {
                data.add(new Person3("", "", "", "", ""));
            }
            inv_newtable.getColumns().clear();
            newInvoicePane_PriceBoxFill(inv_newtable, data);
            insideINVPane.setEffect(new ColorAdjust());

        } catch (SQLException ex) {
            Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void inv_edit_hit(MouseEvent event) {
        /*           
    private JFXComboBox<Integer> inv_pno;

    private Label pno_tick;

    private JFXComboBox<String> inv_invbox;

    private Label inv_tick;


    private JFXTextField inv_no;

    private JFXTextField inv_cmp;

    private JFXTextField inv_tum;

    private JFXTextField inv_qno;

    private JFXTextField inv_po;

    private JFXTextField inv_sp;

    private JFXTextField inv_acc;

    private JFXTextArea inv_to;

    private TableView<Person3> inv_newtable;

    private JFXTextField inv_gst;

    private JFXTextField inv_total;

    private JFXTextField inv_amt;
        
         */
        inv_pno.setDisable(true);
        inv_invbox.setDisable(false);

        pno_tick.setDisable(true);
        inv_invbox.setVisible(true);
        inv_tick.setDisable(false);
        inv_tick.setVisible(true);
        inv_pno.setVisible(false);
        pno_tick.setVisible(false);
        inv_no.clear();
        inv_cmp.clear();
        inv_tum.clear();
        inv_po.clear();
        inv_sp.clear();
        inv_acc.clear();
        inv_newtable.getColumns().clear();
        inv_newtable.getItems().clear();
        inv_gst.clear();
        inv_total.clear();
        inv_amt.clear();
        insideINVPane.setEffect(new GaussianBlur());

        pencilinv.setDisable(true);
        pencilinv.setVisible(false);
        plusinv.setDisable(false);
        plusinv.setVisible(true);

    }

    @FXML
    private void inv_plus_hit(MouseEvent event) {

        inv_pno.setDisable(false);
        pno_tick.setDisable(false);
        inv_invbox.setVisible(false);
        inv_tick.setVisible(false);

        inv_invbox.setDisable(true);

        inv_tick.setDisable(true);

        inv_pno.setVisible(true);
        pno_tick.setVisible(true);
        inv_no.clear();
        inv_cmp.clear();
        inv_tum.clear();
        inv_po.clear();
        inv_sp.clear();
        inv_acc.clear();
        inv_newtable.getColumns().clear();
        inv_newtable.getItems().clear();
        inv_gst.clear();
        inv_total.clear();
        inv_amt.clear();
        insideINVPane.setEffect(new GaussianBlur());

        pencilinv.setDisable(false);
        pencilinv.setVisible(true);
        plusinv.setDisable(true);
        plusinv.setVisible(false);
    }

}
