package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HomeScreenController {

    @FXML
    private void DiscordLink(ActionEvent event) throws IOException, URISyntaxException
    {
        Desktop.getDesktop().browse(new URI("https://discord.gg/CKrpMAZY6y"));
    }
}
