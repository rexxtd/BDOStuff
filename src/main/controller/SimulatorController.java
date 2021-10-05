package main.controller;

import javafx.animation.FadeTransition;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import main.module.SimulatorModule;

import java.net.URL;
import java.util.ResourceBundle;

public class SimulatorController implements Initializable
{
    String[] simulator = {"Enhancement", "Fairy Sprouting", "Pet Exchange", "Horse Leveling"};
    String[] enhanceData;
    String[] enhanceEquip = {"Weapon", "Armor", "Accessory"};
    String[] enhanceType = {"Blue/Yellow", "Green", "Blackstar", "Fallen God's", "Tuvala"};
    String[] enhanceLevel = {"9->10","10->11","12->13","13->14","14->15","15->PRI","PRI->DOU", "DOU->TRI", "TRI->TET", "TET->PEN"};
    SimulatorModule simulatorModule = new SimulatorModule();
    String functionOption = "Enhancement";     //set it to default option
    String typeOption,equipOption,levelOption;

    boolean isSelected;

    @FXML
    private ComboBox<String> function;
    @FXML
    private ComboBox<String> equip;
    @FXML
    private ComboBox<String> type;
    @FXML
    private ComboBox<String> level;
    @FXML
    private Button appearBox;
    @FXML
    private ImageView realIcon;
    @FXML
    private ImageView simulatorIcon;
    @FXML
    private Pane realBox;
    @FXML
    private Pane simulatorBox;
    @FXML
    private CheckBox realCheckBox;
    @FXML
    private AnchorPane enhancePane;
    @FXML
    private Label equipStore,levelStore,typeStore;

    //for fade transition
    private FadeTransition fadeIn = new FadeTransition(
            Duration.seconds(0.5)
    );

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        appearTransition();
        addFunction();

        //listener for function combobox
        function.showingProperty().addListener((obs, oldItem, newItem) -> {
            if (!newItem)
            {
                loadOption();
            }
        });

        iconHoverEffect(realIcon, realBox);
        iconHoverEffect(simulatorIcon, simulatorBox);

        //checkbox listener
        realCheckBox.selectedProperty().addListener(
                (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) ->
                {
                    isSelected = realCheckBox.isSelected();
                });
    }

    public void iconHoverEffect(ImageView icon, Pane box)
    {
    //? icon effect
        icon.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue)
            {
                box.setVisible(true);
                fadeIn.setNode(box);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.playFromStart();
            }
            else
            {
                box.setVisible(false);
            }
        });
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

        // Create action event
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>()
        {
                    public void handle(ActionEvent e)
                    {
                        functionOption = function.getValue();
                    }
                };
        function.setOnAction(event);
    }

    public void addEnhanceEquip()
    {
        ObservableList<String> data = FXCollections.observableArrayList();
        for (int i = 0; i< enhanceEquip.length; i++)
        {
            data.add(enhanceEquip[i]);
        }
        equip.setItems(data);

        // Create action event
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                equipStore.setText(equip.getValue());
                enhanceData[0] = equipStore.getText();
            }
        };
        equip.setOnAction(event);
    }

    public void addEnhanceType()
    {
        ObservableList<String> data = FXCollections.observableArrayList();
        for (int i = 0; i< enhanceType.length; i++)
        {
            data.add(enhanceType[i]);
        }
        type.setItems(data);

        // Create action event
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                typeStore.setText(type.getValue());
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

        // Create action event
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                levelStore.setText(level.getValue());
            }
        };
        level.setOnAction(event);
    }

    public void getEnhanceData()
    {
        enhanceData[0] = equipStore.getText();
        enhanceData[1] = typeStore.getText();
        enhanceData[2] = levelStore.getText();
    }

    public void loadOption()
    {
        switch (functionOption)
        {
            case "Enhancement":
            {
                enhancePane.setVisible(true);
                addEnhanceEquip();
                addEnhanceType();
                addEnhanceLevel();
                break;
            }
            case "Fairy Sprouting":
            {
                enhancePane.setVisible(false);
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
