package main;

import com.dustinredmond.fxtrayicon.FXTrayIcon;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application
{
    double x,y = 0;
    public static Stage getStage;
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        getStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("ui/MainUI.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);

        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - x);
            primaryStage.setY(event.getScreenY() - y);
        });

        FXTrayIcon trayIcon = new FXTrayIcon(primaryStage, getClass().getResource("small_aag.png"));
        trayIcon.show();
        trayIcon.showMessage("BDOStuff", "Welcome to BDOStuff!");


        primaryStage.setScene(new Scene(root, 1700, 900));
        primaryStage.show();
        primaryStage.setTitle("BDO Stuff");
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
