package com.mycompany.snp;

import animatefx.animation.BounceIn;
import animatefx.animation.FadeIn;
import animatefx.animation.Flip;
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

    public static Connection conn = null;
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage stage) throws Exception {
        ConnectToDb();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
       
        BorderPane root1 = new BorderPane(root);

        root1.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        root1.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
//        Extras.ResizeHelper.addResizeListener(stage);
         Scene scene = new Scene(root1);
        stage.setScene(scene);
        stage.show();
        new FadeIn(root).play();
//        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");

//        stage.setTitle("JavaFX and Maven");
//        stage.setScene(scene);
//        stage.show();
    }

    public void ConnectToDb() {
        // Create a Runnable
        Runnable task = new Runnable() {
            public void run() {
                ConnectionThread();
            }
        };

        // Run the task in a background thread
        Thread backgroundThread = new Thread(task);
        // Terminate the running thread if the application exits
        backgroundThread.setDaemon(true);
        // Start the thread
        backgroundThread.start();
    }

    public void ConnectionThread() {

        try {
            conn = DBMS.Connect.ConnectDb();
            //Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

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

        launch(args);
    }

}
