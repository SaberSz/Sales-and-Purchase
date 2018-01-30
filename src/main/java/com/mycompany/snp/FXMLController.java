package com.mycompany.snp;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FXMLController implements Initializable {

     public static String category = "";
     
     //FXML variables
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXComboBox<String> cate;
    @FXML
    private JFXButton LoginButton;
    
   
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         cate.getItems().add("Sales");
         cate.getItems().add("Production");
    } 
private double xa=0;
    private double ya=0;
    @FXML
    private void LoginButtonHit(MouseEvent event) {
        if(username.getText().equalsIgnoreCase("admin") && password.getText().equalsIgnoreCase("admin"))
        {
            try{
            category=cate.getValue();
            if(category.equals("Sales"))
            {
                    //switch to sales controller
                    System.out.println("Hello");
                    Stage stage; 
                    Parent root;
                    stage=(Stage)LoginButton.getScene().getWindow();
                    //load up OTHER FXML document
                    root = FXMLLoader.load(getClass().getResource("/fxml/Sales.fxml"));
                    
                    
                    //stage.initStyle(StageStyle.UNDECORATED);//waits    ok//shall i //saves plz autosave/ NOt needed actually
        BorderPane root1 =new BorderPane(root);
        root1.setOnMousePressed((MouseEvent even) ->{
        xa=even.getSceneX();//copy and paste down NICE YOU GETS MESSAGE FROM NETBEANS TEAM thenks
          ya=even.getSceneY();
        });
        root1.setOnMouseDragged((MouseEvent even) ->{
            stage.setX(even.getScreenX() - xa);//copy
            stage.setY(even.getScreenY() - ya);
        xa=event.getSceneX();//copy and paste down
          ya=event.getSceneY();//yakkappa??????
        });
        
        Scene s =new Scene(root1);
                    
                    
                    
                    //Scene scene = new Scene(root);
                    stage.setScene(s);
                    stage.show();//saves
            }
            else if(category.equals("Production"))
            {
                //switch to production controller
                System.out.println("Hello2");
            }
            else
                {
                  Utilities.AlertBox.notificationWarn("Error", "Category not chosen.");
                }
            }
            catch(Exception e)
            {
                Utilities.AlertBox.notificationWarn("Error", "Category not chosen.");
                Utilities.AlertBox.showErrorMessage(e);
            }
        }
        else
                {
                  Utilities.AlertBox.notificationWarn("Error", "Incorrect login credentials.");
                }
    }

}

