/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.snp;

import com.jfoenix.controls.JFXTextArea;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author dylan
 */
/*
This class consists of the components in the Invoice Table 
 */
public class Person4 {

    private JFXTextArea UOM;
    private JFXTextArea Des;
    private JFXTextArea Qty;
    private JFXTextArea UnitPrice;
    private JFXTextArea Total;
    private JFXTextArea Discount;
    private String a = "0", b = "0", c = "0";

    Person4(String des, String uom, String Qty, String value, String discount, String Total1) {
        this.UOM = new JFXTextArea();
        this.UOM.setText(uom);
        //this.UOM.setEditable(false);
        //this.Des = new SimpleStringProperty(lName);
        // this.Qty = new SimpleStringProperty(Qty);
        this.UnitPrice = new JFXTextArea();
        this.UnitPrice.setText(value);
        //this.remark.setEditable(false);
        this.Des = new JFXTextArea();
        this.Des.setText(des);
        //this.remark.setEditable(false);
        this.Qty = new JFXTextArea();
        this.Qty.setText(Qty);
        //this.remark.setEditable(false);
        this.Total = new JFXTextArea();
        this.Total.setText(Total1);
        this.Total.setEditable(false);
        this.Discount = new JFXTextArea();
        this.Discount.setText(discount);

        this.Qty.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {

            if (newValue.matches("^\\d*\\.?\\d+|\\d+\\.?\\d*$")) {
                this.a = newValue;

            } else if (newValue.matches("^(\\s).+")) {
                this.a = "1";
            } else {
                this.a = "0";
            }
            if (this.c.equals("0")) {
                if (!(this.a.equals("0") && this.b.equals("0"))) {
                    this.Total.setText(String.valueOf(Math.round(Double.valueOf(a) * Double.valueOf(b) * 100d) / 100d));
                    //Math.round((Integer.valueOf(a)*Double.valueOf(b))* 100d) / 100d)
                }
            }else {
                if (!(this.a.equals("0") && this.b.equals("0"))) {
                    Double t = Double.valueOf(a) * Double.valueOf(b);
                    Double v = t - (t * Double.valueOf(c) / 100);
                    this.Total.setText(String.valueOf(Math.round(v * 100d) / 100d));
                }
            }

        });
        this.UnitPrice.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("^\\d*\\.?\\d+|\\d+\\.?\\d*$")) {
                this.UnitPrice.setText(newValue.replaceAll("[^\\d.]", ""));
                this.b = this.UnitPrice.getText();
            }
            if (this.c.equals("0")) {
                if (!(this.a.equals("0") && !this.b.matches("^\\d*\\.?\\d+|\\d+\\.?\\d*$"))) {
                    this.Total.setText(String.valueOf(Math.round(Double.valueOf(a) * Double.valueOf(b) * 100d) / 100d));
                    //Math.round((Integer.valueOf(a)*Double.valueOf(b))* 100d) / 100d)
                }
            }else {
                if (!(this.a.equals("0") && this.b.equals("0"))) {
                    Double t = Double.valueOf(a) * Double.valueOf(b);
                    Double v = t - (t * Double.valueOf(c) / 100);
                    this.Total.setText(String.valueOf(Math.round(v * 100d) / 100d));
                }
            }
        });

        this.Discount.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("^\\d*\\.?\\d+|\\d+\\.?\\d*$")) {
                this.Discount.setText(newValue.replaceAll("[^\\d.]", ""));
                this.c = this.Discount.getText();
            }
            if(this.Discount.getText().isEmpty()){
                this.c="0";
            }
            if (this.c.equals("0")) {
                if (!(this.a.equals("0") && this.b.equals("0"))) {
                    this.Total.setText(String.valueOf(Math.round(Double.valueOf(a) * Double.valueOf(b) * 100d) / 100d));
                    //Math.round((Integer.valueOf(a)*Double.valueOf(b))* 100d) / 100d)
                }
            } else {
                if (!(this.a.equals("0") && this.b.equals("0"))) {
                    Double t = Double.valueOf(a) * Double.valueOf(b);
                    Double v = t - (t * Double.valueOf(c) / 100);
                    this.Total.setText(String.valueOf(Math.round(v * 100d) / 100d));
                }
            }
        });

    }

    public void setEdit(boolean input) {
        if (!input) {
            this.UOM.setEditable(false);
            this.Des.setEditable(false);
            this.Qty.setEditable(false);
            this.UnitPrice.setEditable(false);
            this.Discount.setEditable(false);
            this.Total.setEditable(false);
        } else {
            this.UOM.setEditable(true);
            this.Des.setEditable(true);
            this.Qty.setEditable(true);
            this.UnitPrice.setEditable(true);
            this.Discount.setEditable(true);
            this.Total.setEditable(false);
        }
    }

    public JFXTextArea getUOM() {
        return UOM;
    }

    public void setUOM(JFXTextArea fName) {
        this.UOM = fName;
    }

    public JFXTextArea getDes() {
        return Des;
    }

    public void setDes(JFXTextArea fName) {
        this.Des = fName;
    }

    public JFXTextArea getQty() {
        return Qty;
    }

    public void setQty(JFXTextArea fName) {
        this.Qty = fName;
    }

    public JFXTextArea getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(JFXTextArea remark) {
        this.UnitPrice = remark;
    }

    public void setTotal(JFXTextArea fName) {
        this.Total = fName;
    }

    public JFXTextArea getTotal() {
        return Total;
    }
    
    public void setDiscount(JFXTextArea fName) {
        this.Discount = fName;
    }

    public JFXTextArea getDiscount() {
        return Discount;
    }

}
