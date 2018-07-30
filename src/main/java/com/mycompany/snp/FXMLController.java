package com.mycompany.snp;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
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

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cate.getItems().add("Sales");
        cate.getItems().add("Purchase");
    }
Parent root;
BorderPane root1;
    @FXML
    private void LoginButtonHit(MouseEvent event) {
        if (username.getText().equalsIgnoreCase("admin") && password.getText().equalsIgnoreCase("admin") || true) {
            try {
                category = cate.getValue();
                if (category.equals("Sales")) {
                    //switch to sales controller
                    System.out.println("Hello");
                    Stage stage;
                    //Parent root;
                    stage = (Stage) LoginButton.getScene().getWindow();
                    //load up OTHER FXML document
                    root = FXMLLoader.load(getClass().getResource("/fxml/Sales.fxml"));
                     root1 = new BorderPane(root);

                    root1.setOnMousePressed((MouseEvent event1) -> {
                        xOffset = event1.getSceneX();
                        yOffset = event1.getSceneY();
                    });
                    root1.setOnMouseDragged((MouseEvent event1) -> {
                        stage.setX(event1.getScreenX() - xOffset);
                        stage.setY(event1.getScreenY() - yOffset);
                    });
                    // new FadeIn(root1).play(); 
                    Scene scene = new Scene(root1);
                    stage.setScene(scene);
                    stage.show();
                     new FadeIn(root1).play();
                } else if (category.equals("Purchase")) {
                    //switch to production controller
                    System.out.println("Hello Purchase");
                    Stage stage;
                    Parent root;
                    stage = (Stage) LoginButton.getScene().getWindow();
                    //load up OTHER FXML document
                    root = FXMLLoader.load(getClass().getResource("/fxml/Purchase.fxml"));
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
                     new FadeIn(root1).play();
                    System.out.println("Hello2");
                } else {
                    Utilities.AlertBox.notificationWarn("Error", "Category not chosen.");
                }
            } catch (Exception e) {
                Utilities.AlertBox.notificationWarn("Error", "Category not chosen.");
                e.printStackTrace();
            }
        } else {
            Utilities.AlertBox.notificationWarn("Error", "Incorrect login credentials.");
        }
    }

    @FXML
    private void handlemin(MouseEvent event) {
        Stage stage;
        stage = stage = (Stage) LoginButton.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void handleClose(MouseEvent event) {
        System.exit(0);
    }

}
