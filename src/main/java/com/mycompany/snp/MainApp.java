package com.mycompany.snp;

import java.sql.Connection;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class MainApp extends Application {
     public static Connection conn=null;//clean and buil
    private double xa=0;
    private double ya=0;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        stage.initStyle(StageStyle.UNDECORATED);//waits    ok//shall i //saves plz autosave/ 
        BorderPane root1 =new BorderPane(root);
        root1.setOnMousePressed((MouseEvent event) ->{
        xa=event.getSceneX();//copy and paste down NICE YOU GETS MESSAGE FROM NETBEANS TEAM thenks
          ya=event.getSceneY();
        });
        root1.setOnMouseDragged((MouseEvent event) ->{
            stage.setX(event.getScreenX() - xa);//copy
            stage.setY(event.getScreenY() - ya);
        xa=event.getSceneX();//copy and paste down
          ya=event.getSceneY();
        });
        stage.setTitle("JavaFX and Maven");
        Scene s =new Scene(root1);
        stage.setScene(s);
        stage.show();//what?
        
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        conn=DBMS.Connect.ConnectDb();
        launch(args);
    }

}
