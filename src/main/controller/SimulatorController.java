package main.controller;

import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import main.module.SimulatorModule;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SimulatorController implements Initializable
{
    String[] simulator = {"Enhancement", "Fairy Simulator", "Pet Exchange"};
    String[] enhanceEquip = {"Weapon", "Armor", "Accessory"};
    String[] enhanceType;
    String[] enhanceLevel;
    SimulatorModule simulatorModule = new SimulatorModule();
    String functionOption = "";
    int totalFailstack;

    boolean realBoxIsSelected, muteBoxIsSelected;
    boolean addFailstackIsClicked = false;
    boolean isHide = false;

    @FXML
    private ComboBox<String> function,equip,type,level;
    @FXML
    private Button appearBox, applyButton, hideShowButton;
    @FXML
    private ImageView realIcon, simulatorIcon, enhanceItem, enhanceMaterial;
    @FXML
    private Pane realBox, simulatorBox, equipPane, typePane, levelPane, enhanceUI, addFailstackBox;
    @FXML
    private Pane fairyPane;
    @FXML
    private Pane petPane;
    @FXML
    private CheckBox realCheckBox,muteBox;
    @FXML
    private AnchorPane enhancePane;
    @FXML
    private Label equipStore,levelStore,typeStore, successCount, failCount, failstackDisplay, totalAddedRateDisplay, totalPercentDisplay, itemLevelDisplay;
    @FXML
    private MediaView enhanceVid;
    @FXML
    private TextField failstackTextField;
    @FXML
    private ScrollPane displayResultBox;

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

        //realCheckBox listener
        realCheckBox.selectedProperty().addListener(
                (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) ->
                {
                    realBoxIsSelected = realCheckBox.isSelected();
                });

        //muteBox listener
        muteBox.selectedProperty().addListener(
                (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) ->
                {
                    muteBoxIsSelected = muteBox.isSelected();
                });
    }

    public void enhanceEffect(boolean check)
    {
        String filePath;
        if (check)
        {
            filePath = "src/main/eff/short_success.mp4";
        }
        else
        {
            filePath = "src/main/eff/short_fail.mp4";
        }
        File path = new File(filePath);
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(path.toURI().toString()));
        enhanceVid.setMediaPlayer(mediaPlayer);
        if(muteBoxIsSelected)
        {
            mediaPlayer.setVolume(0);
        }
        else
        {
            mediaPlayer.setVolume(1);
        }
        mediaPlayer.play();
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
                //reset other selection
                type.valueProperty().set(null);
                level.valueProperty().set(null);
                enhanceUI.setVisible(false);

                equipStore.setText(equip.getValue());
                typePane.setVisible(true);
                fadeIn.setNode(typePane);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.playFromStart();
                addEnhanceType();
            }
        };
        equip.setOnAction(event);
    }

    public void addEnhanceType()
    {
        //check if equip is weapon, armor or accessory
        if (equipStore.getText() == "Weapon")
        {
            String[] temp = {"Blue-Yellow", "Green", "Blackstar", "Tuvala"};
            enhanceType = temp;
        }
        else if (equipStore.getText() == "Armor")
        {
            String[] temp = {"Blue-Yellow", "Green", "Blackstar", "Fallen God's", "Tuvala"};
            enhanceType = temp;
        }
        else if (equipStore.getText() == "Accessory")
        {
            String[] temp = {"Green-Blue-Yellow", "Tuvala"};
            enhanceType = temp;
        }

        //generate to Type ComboBox
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
                enhanceUI.setVisible(false);

                typeStore.setText(type.getValue());
                levelPane.setVisible(true);
                fadeIn.setNode(levelPane);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.playFromStart();
                addEnhanceLevel();
            }
        };
        type.setOnAction(event);
    }

    public void addEnhanceLevel()
    {
        //check if equip or type have specific level enhance
        if (equipStore.getText() == "Armor" && typeStore.getText() == "Fallen God's")
        {
            String[] temp = {"0->PRI","PRI->DUO", "DUO->TRI", "TRI->TET", "TET->PEN"};
            enhanceLevel = temp;
        }

        else if (equipStore.getText() == "Accessory")
        {
            String[] temp = {"0->PRI", "PRI->DUO", "DUO->TRI", "TRI->TET", "TET->PEN"};
            enhanceLevel = temp;
        }

        else if (typeStore.getText() == "Tuvala")
        {
            String[] temp = {"15->PRI", "PRI->DUO", "DUO->TRI", "TRI->TET", "TET->PEN"};
            enhanceLevel = temp;
        }

        else
        {
            String[] temp = {"9->10", "10->11", "12->13", "13->14", "14->15", "15->PRI", "PRI->DUO", "DUO->TRI", "TRI->TET", "TET->PEN"};
            enhanceLevel = temp;
        }

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
                enhanceUI.setVisible(false);

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
    private void setEnhanceButton(ActionEvent event) throws IOException
    {
        if (simulatorModule.getEnhanceResult())
        {
            enhanceEffect(true);
            successCount.setText(String.valueOf(Integer.parseInt(successCount.getText()) + 1));
            if (realBoxIsSelected)
            {

            }
        }
        else
        {
            enhanceEffect(false);
            failCount.setText(String.valueOf(Integer.parseInt(failCount.getText()) + 1));
        }
    }

    @FXML
    private void setClearButton(ActionEvent event)
    {
        successCount.setText("0");
        failCount.setText("0");
    }

    @FXML
    private void setHideShowButton(ActionEvent event)
    {
        if (isHide)
        {
            hideShowButton.setText("HIDE");
            fadeIn.setNode(displayResultBox);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.playFromStart();
            isHide = false;
        }
        else
        {
            hideShowButton.setText("SHOW");
            fadeIn.setNode(displayResultBox);
            fadeIn.setFromValue(1.0);
            fadeIn.setToValue(0.0);
            fadeIn.playFromStart();
            isHide = true;
        }
    }

    @FXML
    private void setCorrectItemMaterial()
    {
        File materialPath = null;
        //set item image
        File itemPath = new File("src/main/img/data/enhanceItem/" + typeStore.getText() + "_" + equipStore.getText() + ".png");
        Image itemImage = new Image(itemPath.toURI().toString());
        enhanceItem.setImage(itemImage);
        //set material image
        if (equipStore.getText() != "Accessory")
        {
            if (typeStore.getText() == "Blackstar")
            {
                for (int i = 0; i < enhanceLevel.length; i++)
                {
                    if (levelStore.getText() == enhanceLevel[i])
                    {
                        if (i >= 5)
                        {
                            materialPath = new File("src/main/img/data/material/Blackstar.png");
                            break;
                        }
                        else
                        {
                            materialPath = new File("src/main/img/data/material/Concentrated Magical Black Stone_" + equipStore.getText() + ".png");
                            break;
                        }
                    }
                }
            }

            else if (typeStore.getText() == "Fallen God's")
            {
                materialPath = new File("src/main/img/data/material/Blackstar.png");
            }

            else if (typeStore.getText() == "Tuvala")
            {
                materialPath = new File("src/main/img/data/material/Tuvala.png");
            }

            else
            {
                for (int i = 0; i < enhanceLevel.length; i++)
                {
                    if (levelStore.getText() == enhanceLevel[i])
                    {
                        if (i >= 5)
                        {
                            materialPath = new File("src/main/img/data/material/Concentrated Magical Black Stone_" + equipStore.getText() + ".png");
                            break;
                        }
                        else
                        {
                            materialPath = new File("src/main/img/data/material/Blackstone_" + equipStore.getText() + ".png");
                            break;
                        }
                    }
                }
            }
        }

        else
        {
            materialPath = new File("src/main/img/data/enhanceItem/" + typeStore.getText() + "_" + equipStore.getText() + ".png");
        }
        Image materialImage = new Image(materialPath.toURI().toString());
        enhanceMaterial.setImage(materialImage);

        //set level of item
        String[] split = levelStore.getText().split("-");
        String currentLevel = split[0];
        String display;
        switch (currentLevel)
        {
            case "0":
            {
                display = "";
                break;
            }
            case "PRI":
            {
                display = "I";
                break;
            }
            case "DUO":
            {
                display = "II";
                break;
            }
            case "TRI":
            {
                display = "III";
                break;
            }
            case "TET":
            {
                display = "IV";
                break;
            }
            case "PEN":
            {
                display = "V";
                break;
            }
            default:
            {
                display = "+ " + currentLevel;
                break;
            }
        }
        itemLevelDisplay.setText(display);
    }

    @FXML
    private void setApplyButton(ActionEvent event) throws IOException
    {
        if (levelStore.getText() != "" && levelStore.getText() != null)
        {
            failstackDisplay.setText("+ 0");
            //call the data
            simulatorModule.searchCsvLine(0, levelStore.getText(), typeStore.getText(), equipStore.getText());
            //display basic UI with fade animation
            enhanceUI.setVisible(true);
            fadeIn.setNode(enhanceUI);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.playFromStart();
            totalPercentDisplay.setText(Math.floor(simulatorModule.calculatePercent(0) * 100) / 100 + "%");
            totalAddedRateDisplay.setText(Math.floor(simulatorModule.addedRate() * 100) / 100 + "%");
            setCorrectItemMaterial();
        }
        else
        {
            enhanceUI.setVisible(false);
            System.out.println("Please select lv");
        }
    }

    @FXML
    private void appearFailstackBox(ActionEvent event)
    {
        // force the field to be numeric only
        failstackTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    failstackTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        FadeTransition failstackFade = new FadeTransition(Duration.seconds(0.2));
        if (!addFailstackIsClicked)
        {
            addFailstackIsClicked = true;
            addFailstackBox.setVisible(true);
            failstackFade.setNode(addFailstackBox);
            failstackFade.setFromValue(0.0);
            failstackFade.setToValue(1.0);
            failstackFade.playFromStart();
        }
        else
        {
            addFailstackIsClicked = false;
            failstackFade.setNode(addFailstackBox);
            failstackFade.setFromValue(1.0);
            failstackFade.setToValue(0.0);
            failstackFade.playFromStart();
        }
    }

    @FXML
    private void setAddFailstackButton(ActionEvent event)
    {
        if ((failstackTextField.getText().isEmpty()) || (failstackTextField.getText() == ""))
        {
            totalFailstack = 0;
        }
        else
        {
            totalFailstack = Integer.parseInt(failstackTextField.getText());
        }

        //calculate percentage and display it
        totalPercentDisplay.setText(Math.floor(simulatorModule.calculatePercent(totalFailstack) * 100) / 100 + "%");
        totalAddedRateDisplay.setText(Math.floor(simulatorModule.addedRate() * 100) / 100 + "%");

        failstackDisplay.setText("+ " + totalFailstack);
        //fade transition and set the box fade out
        FadeTransition failstackFade = new FadeTransition(Duration.seconds(0.2));
        addFailstackIsClicked = false;
        failstackFade.setNode(addFailstackBox);
        failstackFade.setFromValue(1.0);
        failstackFade.setToValue(0.0);
        failstackFade.playFromStart();
        failstackTextField.clear();
    }

    public void loadOption()
    {
        switch (functionOption)
        {
            case "Enhancement":
            {
                enhancePane.setVisible(true);
                fairyPane.setVisible(false);
                petPane.setVisible(false);
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

                enhanceUI.setVisible(false);
                typePane.setVisible(false);
                levelPane.setVisible(false);
                applyButton.setVisible(false);

                addEnhanceEquip();
                break;
            }
            case "Fairy Simulator":
            {
                enhancePane.setVisible(false);
                fairyPane.setVisible(true);
                petPane.setVisible(false);
                fadeIn.setNode(fairyPane);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.playFromStart();
                break;
            }
            case "Pet Exchange":
            {
                enhancePane.setVisible(false);
                fairyPane.setVisible(false);
                petPane.setVisible(true);
                fadeIn.setNode(petPane);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.playFromStart();
                break;
            }

            default:
                break;
        }
    }
}