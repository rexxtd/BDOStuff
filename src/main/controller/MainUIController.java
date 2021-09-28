package main.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import main.module.MainUIModule;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainUIController implements Initializable
{
    public MainUIModule mainUIModule = new MainUIModule();
    @FXML
    private AnchorPane slider;
    @FXML
    private ImageView Exit;
    @FXML
    private Label nightTime;
    @FXML
    private Label dailyTime;
    @FXML
    private Label impTime;
    @FXML
    private BorderPane mainPane;

    long night_hour,night_minute,night_second,daily_hour,daily_minute,daily_second,imp_hour,imp_minute,imp_second;

    Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(1),
                    e -> {
                        oneSecondPass();
                        timeDisplay();
                    })
    );

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Exit.setOnMouseClicked(event -> {
            System.exit(0);
        });

        //display default content for the first time
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("Home");
        mainPane.setCenter(view);

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        timeCalculator();
    }

    public void timeCalculator()
    {
        long night_temp = mainUIModule.getNightTime();
        night_hour = night_temp / 3600;
        night_minute = (night_temp % 3600) / 60;
        night_second = night_temp - night_hour * 3600 - night_minute * 60;

        long daily_temp = mainUIModule.getDailyTime();
        daily_hour = daily_temp / 3600;
        daily_minute = (daily_temp % 3600) / 60;
        daily_second = daily_temp - daily_hour * 3600 - daily_minute * 60;

        long imp_temp = mainUIModule.getImperialTime();
        imp_hour = imp_temp / 3600;
        imp_minute = (imp_temp % 3600) / 60;
        imp_second = imp_temp - imp_hour * 3600 - imp_minute * 60;
    }

    public void oneSecondPass()
    {
        night_second--;
        if (night_second < 0)
        {
            night_minute--;
            if (night_minute < 0)
            {
                if (night_hour <= 0)
                    timeCalculator();
            }
            else
                night_second = 59;

            if (night_minute < 0)
            {
                night_hour--;
                night_minute = 59;
            }
        }

        daily_second--;
        if (daily_second < 0)
        {
            daily_minute--;
            if (daily_minute < 0)
            {
                if (daily_hour <= 0)
                    timeCalculator();
            }
            else
                daily_second = 59;
            if (daily_minute < 0)
            {
                daily_hour--;
                daily_minute = 59;
            }
        }

        imp_second--;
        if (imp_second < 0)
        {
            imp_minute--;
            if (imp_minute < 0)
            {
                if (imp_hour <= 0)
                    timeCalculator();
            }
            else
                imp_second = 59;
            if (imp_minute < 0)
            {
                imp_hour--;
                imp_minute = 59;
            }
        }
    }

    public void timeDisplay()
    {
        if (night_hour <= 0)
            nightTime.setText(night_minute + "m " + night_second + "s");
        else
            nightTime.setText(night_hour + "h " + night_minute + "m " + night_second + "s");

        if (daily_hour <= 0)
            dailyTime.setText(daily_minute + "m " + daily_second + "s");
        else
            dailyTime.setText(daily_hour + "h " + daily_minute + "m " + daily_second + "s");

        if (imp_hour <= 0)
            impTime.setText(imp_minute + "m " + imp_second + "s");
        else
            impTime.setText(imp_hour + "h " + imp_minute + "m " + imp_second + "s");
    }

    //display Home tab inside homepage when click on booking button
    @FXML
    private void loadHome(ActionEvent event) throws IOException
    {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("Home");
        mainPane.setCenter(view);
    }

    //display Date/Time tab inside homepage when click on booking button
    @FXML
    private void loadDateTime(ActionEvent event) throws IOException
    {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("DateTime");
        mainPane.setCenter(view);
    }
}
