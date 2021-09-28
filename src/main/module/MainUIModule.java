package main.module;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class MainUIModule
{
    String[] nightTime = new String[]{"02:40:00", "06:40:00", "10:40:00", "14:40:00", "18:40:00", "22:40:00"};
    String[] imperialTime = new String[]{"01:00:00", "04:00:00", "07:00:00", "10:00:00", "13:00:00", "16:00:00", "19:00:00", "22:00:00"};

    public long getNightTime()
    {
        int i = 1;
        boolean check = false;
        long time = 0;
        while(!check && i<= nightTime.length)
        {
            LocalTime localTime = LocalTime.now();
            LocalTime timeFixed = LocalTime.parse(nightTime[i]);
            if (timeFixed.compareTo(localTime) > 0)
            {
                time = localTime.until(timeFixed, ChronoUnit.SECONDS);
                check = true;
            }
            else
                i++;
        }
        return time;
    }

    public long getDailyTime()
    {
        LocalTime localTime = LocalTime.now();
        long time = 0;
        LocalTime timeFixed = LocalTime.parse("05:00:00");
        if (timeFixed.compareTo(localTime) > 0)
            time = localTime.until(timeFixed, ChronoUnit.SECONDS);
        else
        {
            time = 86400 - timeFixed.until(localTime, ChronoUnit.SECONDS);
        }
        return time;
    }

    public long getImperialTime()
    {
        LocalTime localTime = LocalTime.now();
        int i = 1;
        boolean check = false;
        long time = 0;
        while(!check && i<= imperialTime.length)
        {
            LocalTime timeFixed = LocalTime.parse(imperialTime[i]);
            if (timeFixed.compareTo(localTime) > 0)
            {
                time = localTime.until(timeFixed, ChronoUnit.SECONDS);
                check = true;
            }
            else
                i++;
        }
        return time;
    }
}
