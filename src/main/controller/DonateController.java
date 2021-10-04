package main.controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.util.Duration;

import javafx.scene.control.Button;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class DonateController implements Initializable
{
    @FXML
    private Button appearBox;

    public void initialize(URL location, ResourceBundle resources)
    {
        appearTransition();
    }

    @FXML
    private void GitHubLink(ActionEvent event) throws IOException, URISyntaxException
    {
        Desktop.getDesktop().browse(new URI("https://github.com/rexxtd"));
    }

    @FXML
    private void MomoLink(ActionEvent event) throws IOException, URISyntaxException
    {
        Desktop.getDesktop().browse(new URI("https://momo.vn/"));
    }

    @FXML
    private void PaypalLink(ActionEvent event) throws IOException, URISyntaxException
    {
        Desktop.getDesktop().browse(new URI("https://www.paypal.com/paypalme/dat1812"));
    }

    public void appearTransition()
    {
        //time for transition to complete
        double transDuration = 1.5;
        //Create new translate transition
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(transDuration));
        transition.setNode(appearBox);
        //Move in Y axis by 1600
        transition.setToY(1600);

        transition.play();
    }
}
