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
public class Person {
    private JFXTextArea firstName;
    private  JFXTextArea lastName;
    private  JFXTextArea email;
    private JFXTextArea remark;  
    private  JFXTextArea total;
    
 
     Person(String fName, String lName, String email, String value,String total1) {
        this.firstName= new JFXTextArea();
        this.firstName.setText(fName);
        this.firstName.setEditable(false);
        //this.lastName = new SimpleStringProperty(lName);
       // this.email = new SimpleStringProperty(email);
        this.remark = new JFXTextArea();   
        this.lastName = new JFXTextArea(); 
        this.email = new JFXTextArea(); 
        this.total = new JFXTextArea(); 
        
    }
 
    public JFXTextArea getFirstName() {
        return firstName;
    }
    public void setFirstName(JFXTextArea fName) {
        this.firstName = fName;
    }
        
    public JFXTextArea getLastName() {
        return lastName;
    }
    public void setLastName(JFXTextArea fName) {
        this.lastName =  fName;
    }
    
    public JFXTextArea getEmail() {
        return email;
    }
    public void setEmail(JFXTextArea fName) {
        this.email = fName;
    }
    

 public JFXTextArea getRemark() {
        return remark;
    }
 
    public void setRemark(JFXTextArea remark) {
        this.remark = remark;
    }
    
    public void setTotal(JFXTextArea fName) {
            this.total = fName;
        }
     public JFXTextArea getTotal() {
            return total;
        }

}
