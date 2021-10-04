package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import main.module.SimulatorModule;

import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ResourceBundle;

public class SimulatorController implements Initializable
{
    String[] simulator = {"Enhancement", "Fairy Sprouting", "Pet Exchange", "Horse Leveling"};
    String[] enhanceType = {"Weapon/Armor", "Accessory"};
    String[] enhanceLevel = {"9->10","10->11","12->13","13->14","14->15","15->PRI","PRI->DOU", "DOU->TRI", "TRI->TET", "TET->PEN"};
    SimulatorModule simulatorModule = new SimulatorModule();
    String functionOption = "Enhancement";     //set it to default option
    String typeOption,levelOption;

    boolean loop = true;

    @FXML
    private ComboBox<String> function;
    @FXML
    private ComboBox<String> type;
    @FXML
    private ComboBox<String> level;
    @FXML
    private Button appearBox;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        appearTransition();
        addFunction();

        function.showingProperty().addListener((obs, oldItem, newItem) ->
        {
            if (!newItem)
            {
                loadOption();
            }
        });
        loadOption();
    }

    public void appearTransition()
    {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(1.2));
        transition.setNode(appearBox);
        transition.setToY(1600);

        transition.play();
    }

    public void addFunction()
    {
        //create List of String
        ObservableList<String> data = FXCollections.observableArrayList();
        for (int i=0; i<simulator.length; i++)
        {
            data.add(simulator[i]);
        }
        function.setItems(data);
        function.getSelectionModel().selectFirst();

        // Create action event
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e)
                    {
                        functionOption = function.getValue();
                    }
                };
        function.setOnAction(event);
    }

    public void addEnhanceType()
    {
        ObservableList<String> data = FXCollections.observableArrayList();
        for (int i = 0; i< enhanceType.length; i++)
        {
            data.add(enhanceType[i]);
        }
        type.setItems(data);
        type.getSelectionModel().selectFirst();

        // Create action event
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                typeOption = type.getValue();
            }
        };
        type.setOnAction(event);
    }

    public void addEnhanceLevel()
    {
        ObservableList<String> data = FXCollections.observableArrayList();
        for (int i = 0; i< enhanceLevel.length; i++)
        {
            data.add(enhanceLevel[i]);
        }
        level.setItems(data);
        level.getSelectionModel().selectFirst();

        // Create action event
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                levelOption = level.getValue();
            }
        };
        level.setOnAction(event);
    }

    public void loadOption()
    {
        switch (functionOption)
        {
            case "Enhancement":
            {
                addEnhanceType();
                addEnhanceLevel();
                break;
            }
            case "Fairy Sprouting":
            {
                type.getItems().clear();
                level.getItems().clear();
                break;
            }
            case "Pet Exchange":
            {

            }
            case "Horse Leveling":
            {

            }

            default:
            {
                System.out.println("Error has been occurred, please contact admin!");
                break;
            }
        }
    }
}
