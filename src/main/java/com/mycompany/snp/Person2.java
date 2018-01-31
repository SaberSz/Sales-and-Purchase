/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.snp;

import com.jfoenix.controls.JFXTextArea;

/**
 *
 * @author dylan
 */
public class Person2 {
    private JFXTextArea SN;
    private  JFXTextArea Position;
    private  JFXTextArea NormalRate;
    private JFXTextArea BeyondNormalHours;  
    private  JFXTextArea Holidays;
    private  JFXTextArea Remarks;
    
 
     Person2(String fName, String lName, String NormalRate, String value,String Holidays1,String Remarks) {
        this.SN= new JFXTextArea();
        //this.SN.setText(fName);
        //this.SN.setEditable(false);
        //this.Position = new SimpleStringProperty(lName);
       // this.NormalRate = new SimpleStringProperty(NormalRate);
        this.BeyondNormalHours = new JFXTextArea();   
        this.Position = new JFXTextArea(); 
        this.NormalRate = new JFXTextArea(); 
        this.Holidays = new JFXTextArea(); 
        this.Remarks = new JFXTextArea(); 
        
    }
 
    public JFXTextArea getSN() {
        return SN;
    }
    public void setSN(JFXTextArea fName) {
        this.SN = fName;
    }
        
    public JFXTextArea getPosition() {
        return Position;
    }
    public void setPosition(JFXTextArea fName) {
        this.Position =  fName;
    }
    
    public JFXTextArea getNormalRate() {
        return NormalRate;
    }
    public void setNormalRate(JFXTextArea fName) {
        this.NormalRate = fName;
    }
    

 public JFXTextArea getBeyondNormalHours() {
        return BeyondNormalHours;
    }
 
    public void setBeyondNormalHours(JFXTextArea BeyondNormalHours) {
        this.BeyondNormalHours = BeyondNormalHours;
    }
    
    public void setHolidays(JFXTextArea fName) {
            this.Holidays = fName;
        }
     public JFXTextArea getHolidays() {
            return Holidays;
        }
     public void setRemarks(JFXTextArea fName) {
            this.Remarks = fName;
        }
     public JFXTextArea getRemarks() {
            return Remarks;
        }

}
