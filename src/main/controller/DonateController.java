package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class DonateController
{
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
}
