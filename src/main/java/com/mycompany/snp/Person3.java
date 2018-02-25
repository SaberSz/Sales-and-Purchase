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
public class Person3 {
      private JFXTextArea ItemNo;
    private  JFXTextArea Des;
    private  JFXTextArea Qty;
    private JFXTextArea UnitPrice;  
    private  JFXTextArea Total;
    private String a="0",b="0";  
 
     Person3(String fName, String lName, String Qty, String value,String Total1) {
        this.ItemNo= new JFXTextArea();
        this.ItemNo.setText(fName);
        //this.ItemNo.setEditable(false);
        //this.Des = new SimpleStringProperty(lName);
       // this.Qty = new SimpleStringProperty(Qty);
        this.UnitPrice = new JFXTextArea(); 
        this.UnitPrice.setText(value);
        //this.remark.setEditable(false);
        this.Des = new JFXTextArea(); 
        this.Des.setText(lName);
        //this.remark.setEditable(false);
        this.Qty = new JFXTextArea(); 
        this.Qty.setText(Qty);
        //this.remark.setEditable(false);
        this.Total = new JFXTextArea(); 
        this.Total.setText(Total1);
        this.Total.setEditable(false);
        
        this.Qty.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
           
             if (newValue.matches("\\d+")) {
                this.a=newValue;
                
            }
             else if(newValue.matches("^(?!\\s*$).+"))
            {  
                this.a="1";
            }
             else{
                 this.a ="0";
             }
             if(!(this.a.equals("0") && this.b.equals("0"))){
                 this.Total.setText(String.valueOf(Math.round(Double.valueOf(a)*Double.valueOf(b)* 100d) / 100d));
                 //Math.round((Integer.valueOf(a)*Double.valueOf(b))* 100d) / 100d)
             }
             
        });
        this.UnitPrice.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                this.UnitPrice.setText(newValue.replaceAll("[^\\d]", "")); 
                this.b=this.UnitPrice.getText();
            }
            if(!(this.a.equals("0") && this.b.equals("0"))){
                 this.Total.setText(String.valueOf(Math.round(Double.valueOf(a)*Double.valueOf(b)* 100d) / 100d));
                 //Math.round((Integer.valueOf(a)*Double.valueOf(b))* 100d) / 100d)
             }
        });
        


        
    }
     
     public void setEdit(boolean input){
        if(!input){
           this.ItemNo.setEditable(false);
           this.Des.setEditable(false);
           this.Qty.setEditable(false);
           this.UnitPrice.setEditable(false);
           this. Total.setEditable(false);
        }
        else{
            this.ItemNo.setEditable(true);
           this.Des.setEditable(true);
           this.Qty.setEditable(true);
           this.UnitPrice.setEditable(true);
           this.Total.setEditable(true);
        }
    }
    public JFXTextArea getItemNo() {
        return ItemNo;
    }
    public void setItemNo(JFXTextArea fName) {
        this.ItemNo = fName;
    }
        
    public JFXTextArea getDes() {
        return Des;
    }
    public void setDes(JFXTextArea fName) {
        this.Des =  fName;
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

}

