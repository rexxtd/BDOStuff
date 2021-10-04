package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class SimulatorController implements Initializable
{
    @FXML
    private ComboBox<String> filter;
    @FXML
    private Button invisibleBox;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        appearTransition();
        addFilter();
    }

    public void appearTransition()
    {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(1));
        transition.setNode(invisibleBox);

        transition.setToY(250);

        transition.play();
    }

    public void addFilter()
    {
        filter.getItems().add("Gear Enhancement");
        filter.getItems().add("Fairy Sprouting");
        filter.getItems().add("Pet Exchanging");
        filter.getItems().add("Horse Leveling");
    }
}
