package main.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
/*import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;*/

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.image.ImageView;
import javafx.util.Duration;
import main.module.BossTimerModule;

public class BossTimerController implements Initializable
{
    long nextBossTime_hour, nextBossTime_minute, nextBossTime_second, followBossTime_hour, followBossTime_minute, followBossTime_second;
    String nextBossName,followBossName;
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
            nextBossName1.setLayoutX(363);
            nextBossName1.setText(nextBossName);
            nextBossName2.setText("");

            File filepath = new File("src/main/img/worldboss/" + nextBossName + ".png");
            Image image = new Image(filepath.toURI().toString());
            nextBoss1.setLayoutX(363);
            nextBoss1.setImage(image);
            nextBoss2.setImage(null);
        }
        else
        {
            nextBossName1.setLayoutX(277);
            nextBoss1.setLayoutX(277);
            String[] arr = nextBossName.split(",");
            nextBossName1.setText(arr[0]);
            File filepath1 = new File("src/main/img/worldboss/" + arr[0] + ".png");
            Image image1 = new Image(filepath1.toURI().toString());
            nextBoss1.setImage(image1);

            nextBossName2.setText(arr[1]);
            File filepath2 = new File("src/main/img/worldboss/" + arr[1] + ".png");
            Image image2 = new Image(filepath2.toURI().toString());
            nextBoss2.setImage(image2);
        }
    }

    public void setFollowBossName()
    {
        int count = 1;
        for (int i=0;i<followBossName.length()-1;i++)
        {
            if(followBossName.charAt(i) == ',')
            {
                count++;
            }
        }

        if (count==1)
        {
            followBossName1.setLayoutX(942);
            followBossName1.setText(followBossName);
            followBossName2.setText("");

            File filepath = new File("src/main/img/worldboss/" + followBossName + ".png");
            Image image = new Image(filepath.toURI().toString());
            followBoss1.setLayoutX(942);
            followBoss1.setImage(image);
            followBoss2.setImage(null);
        }
        else
        {
            followBossName1.setLayoutX(852);
            followBoss1.setLayoutX(852);
            String[] arr = followBossName.split(",");
            followBossName1.setText(arr[0]);
            File filepath1 = new File("src/main/img/worldboss/" + arr[0] + ".png");
            Image image1 = new Image(filepath1.toURI().toString());
            followBoss1.setImage(image1);

            followBossName2.setText(arr[1]);
            File filepath2 = new File("src/main/img/worldboss/" + arr[1] + ".png");
            Image image2 = new Image(filepath2.toURI().toString());
            followBoss2.setImage(image2);
        }
    }

    public void timeCalculator()
    {
        long nextBossTime_temp = bossTimerModule.getNextBossTime();
        nextBossTime_hour = nextBossTime_temp / 3600;
        nextBossTime_minute = (nextBossTime_temp % 3600) / 60;
        nextBossTime_second = nextBossTime_temp - nextBossTime_hour * 3600 - nextBossTime_minute * 60;

        nextBossName = bossTimerModule.getNextBoss();
        followBossName = bossTimerModule.getFollowBoss();

        long followBossTime_temp = bossTimerModule.followTime;
        followBossTime_hour = followBossTime_temp / 3600;
        followBossTime_minute = (followBossTime_temp % 3600) / 60;
        followBossTime_second = followBossTime_temp - followBossTime_hour * 3600 - followBossTime_minute * 60;
    }

    /*public void soundManager(String time)
    {
        String filepath = "src/main/sound/worldboss/female_"+ time +".mp3";
        Media media = new Media(new File(filepath).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
    }*/

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
        followBossTime.setText(followBossTime_hour + ":" + followBossTime_minute + ":" + followBossTime_second);
        setFollowBossName();
    }
}
