package main.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.util.Duration;
import main.module.BossTimerModule;

public class BossTimerController implements Initializable
{
    long nextBossTime_hour, nextBossTime_minute, nextBossTime_second, followBossTime_hour, followBossTime_minute, followBossTime_second;
    String nextBossName;
    BossTimerModule bossTimerModule = new BossTimerModule();

    @FXML
    private ImageView nextBoss1;
    @FXML
    private ImageView nextBoss2;
    @FXML
    private ImageView followBoss1;
    @FXML
    private ImageView followBoss2;
    @FXML
    private Label nextBossName1;
    @FXML
    private Label nextBossName2;
    @FXML
    private Label followBossName1;
    @FXML
    private Label followBossName2;
    @FXML
    private Label nextBossTime;
    @FXML
    private Label followBossTime;

    Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(1),
                    e -> {
                        oneSecondPass();
                        bossDisplay();
                    })
    );

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        bossTimerModule.getData();
        timeCalculator();

        /*String followBossName = bossTimerModule.getFollowBoss();*/
    }

    public void setNextBossName()
    {
        int count = 1;
        for (int i=0;i<nextBossName.length()-1;i++)
        {
            if(nextBossName.charAt(i) == ',')
            {
                count++;
            }
        }
        if (count==1)
        {
            nextBossName1.setText(nextBossName);
            nextBossName2.setText("");
        }
        else
        {
            String[] arr = nextBossName.split(",");
            nextBossName1.setText(arr[0]);
            nextBossName2.setText(arr[1]);
        }
    }

    public void timeCalculator()
    {
        long nextBossTime_temp = bossTimerModule.getNextBossTime();
        nextBossTime_hour = nextBossTime_temp / 3600;
        nextBossTime_minute = (nextBossTime_temp % 3600) / 60;
        nextBossTime_second = nextBossTime_temp - nextBossTime_hour * 3600 - nextBossTime_minute * 60;

        nextBossName = bossTimerModule.getNextBoss();

       /* long followBossTime_temp = bossTimerModule.getFollowBossTime();
        followBossTime_hour = followBossTime_temp / 3600;
        followBossTime_minute = (followBossTime_temp % 3600) / 60;
        followBossTime_second = followBossTime_temp - followBossTime_hour * 3600 - followBossTime_minute * 60;*/
    }

    public void oneSecondPass()
    {
        nextBossTime_second--;
        if (nextBossTime_second < 0)
        {
            nextBossTime_minute--;
            if (nextBossTime_minute < 0)
            {
                if (nextBossTime_hour <= 0)
                    timeCalculator();
            }
            else
                nextBossTime_second = 59;

            if (nextBossTime_minute < 0)
            {
                nextBossTime_hour--;
                nextBossTime_minute = 59;
            }
        }

        followBossTime_second--;
        if (followBossTime_second < 0)
        {
            followBossTime_minute--;
            if (followBossTime_minute < 0)
            {
                if (followBossTime_hour <= 0)
                    timeCalculator();
            }
            else
                followBossTime_second = 59;

            if (followBossTime_minute < 0)
            {
                followBossTime_hour--;
                followBossTime_minute = 59;
            }
        }
    }

    public void bossDisplay()
    {
        nextBossTime.setText(nextBossTime_hour + ":" + nextBossTime_minute + ":" + nextBossTime_second);
        setNextBossName();
        /*followBossTime.setText(followBossTime_hour + ":" + followBossTime_minute + ":" + followBossTime_second);*/
    }
}
