package main.controller;

import com.sun.org.apache.xml.internal.security.Init;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.FxmlLoader;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeScreenController implements Initializable
{
    @FXML
    private Button appearBox;

    public void initialize(URL location, ResourceBundle resources)
    {
        appearTransition();
    }

    @FXML
    private void DiscordLink(ActionEvent event) throws IOException, URISyntaxException
    {
        Desktop.getDesktop().browse(new URI("https://discord.gg/CKrpMAZY6y"));
    }

    public void appearTransition()
    {
        //time for transition to complete
        double transDuration = 1;
        //Create new translate transition
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(transDuration));
        transition.setNode(appearBox);
        //Move in Y axis by 1600
        transition.setToY(1600);

        transition.play();
    }

    @FXML
    private void loadBossTimer(ActionEvent event) throws IOException
    {
        Parent anotherRoot = FXMLLoader.load(getClass().getResource("../ui/BossTimer.fxml"));
        Stage anotherStage = new Stage();
        Scene anotherScene = new Scene(anotherRoot);
        anotherStage.setScene(anotherScene);
        anotherStage.setResizable(false);
        anotherStage.show();
        anotherStage.setTitle("Boss Timer");
    }

    @FXML
    private void loadSimulator(ActionEvent event) throws IOException
    {
        Parent anotherRoot = FXMLLoader.load(getClass().getResource("../ui/Simulator.fxml"));
        Stage anotherStage = new Stage();
        Scene anotherScene = new Scene(anotherRoot);
        anotherStage.setScene(anotherScene);
        anotherStage.setResizable(false);
        anotherStage.show();
        anotherStage.setTitle("Simulator");
    }

    @FXML
    private void loadDonate(ActionEvent event) throws IOException
    {
        Parent anotherRoot = FXMLLoader.load(getClass().getResource("../ui/Donate.fxml"));
        Stage anotherStage = new Stage();
        Scene anotherScene = new Scene(anotherRoot);
        anotherStage.setScene(anotherScene);
        anotherStage.setResizable(false);
        anotherStage.show();
        anotherStage.setTitle("About me");
    }
}
