package main.controller;

import javafx.animation.FadeTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import main.FxmlLoader;
import main.module.SimulatorModule;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SimulatorController implements Initializable
{
    String[] simulator = {"Enhancement", "Fairy Sprouting", "Pet Exchange", "Horse Leveling"};
    String[] enhanceEquip = {"Weapon", "Armor", "Accessory"};
    String[] enhanceType = {"Blue/Yellow", "Green", "Blackstar", "Fallen God's", "Tuvala"};
    String[] enhanceLevel = {"9->10","10->11","12->13","13->14","14->15","15->PRI","PRI->DOU", "DOU->TRI", "TRI->TET", "TET->PEN"};
    SimulatorModule simulatorModule = new SimulatorModule();
    String functionOption = "";     //set it to default option

    boolean isSelected;

    @FXML
    private ComboBox<String> function,equip,type,level;
    @FXML
    private Button appearBox, applyButton;
    @FXML
    private ImageView realIcon, simulatorIcon, enhanceImg;
    @FXML
    private Pane realBox, simulatorBox, equipPane, typePane, levelPane;
    @FXML
    private CheckBox realCheckBox;
    @FXML
    private AnchorPane enhancePane;
    @FXML
    private Label equipStore,levelStore,typeStore;
    @FXML
    private MediaView enhanceVid;

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
        enhanceEffect();
    }

    public void enhanceEffect()
    {
        File path = new File("src/main/eff/1.mp4");
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(path.toURI().toString()));
        enhanceVid.setMediaPlayer(mediaPlayer);
        mediaPlayer.setAutoPlay(true);
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
        //equip.getSelectionModel().selectFirst();

        // Create action event
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                //reset other selection
                type.valueProperty().set(null);
                level.valueProperty().set(null);
                enhanceImg.setVisible(false);

                equipStore.setText(equip.getValue());
                typePane.setVisible(true);
                fadeIn.setNode(typePane);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.playFromStart();
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
                //reset other selection
                level.valueProperty().set(null);
                enhanceImg.setVisible(false);

                typeStore.setText(type.getValue());
                levelPane.setVisible(true);
                fadeIn.setNode(levelPane);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.playFromStart();
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
                enhanceImg.setVisible(false);

                levelStore.setText(level.getValue());
                applyButton.setVisible(true);
                fadeIn.setNode(applyButton);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.playFromStart();
            }
        };
        level.setOnAction(event);
    }

    @FXML
    private void setApplyButton(ActionEvent event) throws IOException
    {
        if (levelStore.getText() != "" && levelStore.getText() != null)
        {
            simulatorModule.getEnhanceSelection(equipStore.getText(),typeStore.getText(),levelStore.getText());
            enhanceImg.setVisible(true);
            fadeIn.setNode(enhanceImg);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.playFromStart();
        }
        else
        {
            enhanceImg.setVisible(false);
            System.out.println("Please select lv");
        }
    }

    public void loadOption()
    {
        switch (functionOption)
        {
            case "Enhancement":
            {
                enhancePane.setVisible(true);
                fadeIn.setNode(enhancePane);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.playFromStart();

                equipPane.setVisible(true);
                fadeIn.setNode(enhancePane);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.playFromStart();

                equip.getItems().clear();
                type.getItems().clear();
                level.getItems().clear();

                enhanceImg.setVisible(false);
                enhanceVid.setVisible(false);
                typePane.setVisible(false);
                levelPane.setVisible(false);
                applyButton.setVisible(false);

                addEnhanceEquip();
                addEnhanceType();
                addEnhanceLevel();
                break;
            }
            case "Fairy Sprouting":
            {
                enhancePane.setVisible(false);
                break;
            }
            case "Pet Exchange":
            {
                enhancePane.setVisible(false);
                break;
            }
            case "Horse Leveling":
            {

            }

            default:
            {
                System.out.println("Nothing selected");
                break;
            }
        }
    }
}
