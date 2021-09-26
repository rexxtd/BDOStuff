package main.controller;

import javafx.fxml.FXMLLoader;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import main.FxmlLoader;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable
{
    @FXML
    private AnchorPane slider;

    @FXML
    private ImageView Exit;

    @FXML
    private Label nightTime;

    @FXML
    private Label dailyTime;

    @FXML
    private Label imperialTime;

    @FXML
    private Label Menu;

    @FXML
    private Label MenuClose;

    @FXML
    private BorderPane mainPane;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Exit.setOnMouseClicked(event -> {
            System.exit(0);
        });
        slider.setTranslateX(0);
        Menu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(0);
            slide.play();

            slider.setTranslateX(-270);

            slide.setOnFinished((ActionEvent e)-> {
                Menu.setVisible(false);
                MenuClose.setVisible(true);
            });
        });

        MenuClose.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(-270);
            slide.play();

            slider.setTranslateX(0);

            slide.setOnFinished((ActionEvent e)-> {
                Menu.setVisible(true);
                MenuClose.setVisible(false);
            });
        });

        //display default content for the first time
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("Home");
        mainPane.setCenter(view);
    }

    //display booking inside homepage when click on booking button
    @FXML
    private void loadDateTime(ActionEvent event) throws IOException
    {
        System.out.println("Cliked");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("DateTime");
        mainPane.setCenter(view);
    }
}
