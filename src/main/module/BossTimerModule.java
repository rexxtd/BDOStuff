package main.module;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class BossTimerModule
{
    int time_row,day_column;
    public long followTime;
    String[] timer = {"00:30:00","10:00:00","14:00:00","15:00:00","19:00:00","23:00:00"};
    String[][] bossData = new String[6][7];

    public void getData()
    {
        bossData[0][0] = "Kutum";         bossData[0][1] = "Nouver";       bossData[0][2] = "Kzarka,Offin";  bossData[0][3] = "Kutum";         bossData[0][4] = "Nouver";        bossData[0][5] = "Karanda";       bossData[0][6] = "Kzarka";
        bossData[1][0] = "Kzarka,Nouver"; bossData[1][1] = "Kutum,Karanda";bossData[1][2] = "Kutum,Nouver";  bossData[1][3] = "Kzarka,Karanda";bossData[1][4] = "Kzarka,Kutum";  bossData[1][5] = "Kzarka,Kutum";  bossData[1][6] = "Nouver,Karanda";
        bossData[2][0] = "Kutum,Nouver";  bossData[2][1] = "Kzarka,Kutum"; bossData[2][2] = "Kzarka,Karanda";bossData[2][3] = "Kutum,Nouver";  bossData[2][4] = "Kzarka,Karanda";bossData[2][5] = "Nouver,Karanda";bossData[2][6] = "Kutum,Karanda";
        bossData[3][0] = "-";             bossData[3][1] = "-";            bossData[3][2] = "-";             bossData[3][3] = "-";             bossData[3][4] = "-";             bossData[3][5] = "Garmoth";       bossData[3][6] = "Vell";
        bossData[4][0] = "Kzarka,Karanda";bossData[4][1] = "Quint,Muraka"; bossData[4][2] = "Kutum,Nouver";  bossData[4][3] = "Nouver,Karanda";bossData[4][4] = "Kutum,Nouver";  bossData[4][5] = "Quint,Muraka";  bossData[4][6] = "Kzarka,Karanda";
        bossData[5][0] = "Offin";         bossData[5][1] = "Garmoth";      bossData[5][2] = "Vell";          bossData[5][3] = "Garmoth";       bossData[5][4] = "Offin";         bossData[5][5] = "-";             bossData[5][6] = "Kutum,Nouver";
    }

    public String getNextBoss()
    {
        getNextBossTime();
        if (bossData[time_row][day_column] == "-")
            time_row++;
        return bossData[time_row][day_column];
    }

    public String getFollowBoss()
    {
        int follow_row = 0,follow_column = 0;
        if (time_row == 5)
        {
            if (day_column == 6)
            {
                follow_column = 0;
                follow_row = 0;
            }
            else
            {
                follow_column = day_column + 1;
                follow_row = 0;
            }
        }
        else
        {
            follow_row = time_row + 1;
            follow_column = day_column;
            if (bossData[follow_row][follow_column] == "-")
                if (follow_row == 5)
                {
                    follow_row = 0;
                    follow_column ++;
                }
                else
                    follow_row++;
        }

        return bossData[follow_row][follow_column];
    }

    public void getBossDate()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE");
        Date d = new Date();
        String dayOfTheWeek = sdf.format(d);
        switch (dayOfTheWeek)
        {
            case "Mon":
                day_column = 0;
                break;
            case "Tue":
                day_column = 1;
                break;
            case "Wed":
                day_column = 2;
                break;
            case "Thu":
                day_column = 3;
                break;
            case "Fri":
                day_column = 4;
                break;
            case "Sat":
                day_column = 5;
                break;
            case "Sun":
                day_column = 6;
                break;
            default:
                System.out.println("An error has occurred, please contact admin!");
        }
    }

    public long getNextBossTime()
    {
        getBossDate();
        LocalTime localTime = LocalTime.now();
        boolean check = false;
        long time = 0;

        LocalTime finalTime = LocalTime.parse("23:00:00");
        LocalTime secondFinalTime = LocalTime.parse("19:00:00");

        {
            while (!check)
            {
                if (day_column == 5)
                {
                    if (localTime.compareTo(finalTime) >= 0)
                    {
                        day_column++;
                        time = 86400 - LocalTime.parse(timer[0]).until(localTime, ChronoUnit.SECONDS);
                        time_row = 0;

                        followTime = 86400 - LocalTime.parse(timer[1]).until(localTime, ChronoUnit.SECONDS);

                        break;
                    }
                    else
                    {
                        if (localTime.compareTo(secondFinalTime)>0)
                        {
                            day_column++;
                            time = 86400 - LocalTime.parse(timer[0]).until(localTime, ChronoUnit.SECONDS);
                            time_row = 0;

                            followTime = 86400 - LocalTime.parse(timer[1]).until(localTime, ChronoUnit.SECONDS);

                            break;
                        }
                    }
                }

                if (day_column==6)
                {
                    if (localTime.compareTo(finalTime) >= 0)
                    {
                        day_column = 0;
                        time = 86400 - LocalTime.parse(timer[0]).until(localTime, ChronoUnit.SECONDS);
                        time_row = 0;

                        followTime = 86400 - LocalTime.parse(timer[1]).until(localTime, ChronoUnit.SECONDS);

                        break;
                    }
                }

                for (int i=0;i<timer.length; i++)
                {
                    LocalTime timeFixed = LocalTime.parse(timer[i]);
                    if (timeFixed.compareTo(localTime) > 0)
                    {
                        if(bossData[i][day_column] != "-")
                        {
                            time = localTime.until(timeFixed, ChronoUnit.SECONDS);
                            time_row = i;

                            LocalTime followTimeFixed;
                            if (time_row == 5)
                            {
                                followTimeFixed = LocalTime.parse(timer[0]);
                                followTime = 86400 - followTimeFixed.until(localTime, ChronoUnit.SECONDS);
                            }
                            else
                            {
                                if (bossData[i+1][day_column] != "-")
                                {
                                    followTimeFixed = LocalTime.parse(timer[i + 1]);
                                    followTime = localTime.until(followTimeFixed, ChronoUnit.SECONDS);
                                }
                                else
                                {
                                    if (day_column == 5)
                                    {
                                        followTimeFixed = LocalTime.parse(timer[0]);
                                        followTime = 86400 - followTimeFixed.until(localTime, ChronoUnit.SECONDS);
                                    }
                                    else
                                    {
                                        followTimeFixed = LocalTime.parse(timer[(i + 1) + 1]);
                                        followTime = localTime.until(followTimeFixed, ChronoUnit.SECONDS);
                                    }
                                }
                            }

                            check = true;
                            break;
                        }
                    }
                    else
                    {
                        if (i == 5)
                        {
                            time = 86400 - LocalTime.parse(timer[0]).until(localTime, ChronoUnit.SECONDS);
                            day_column++;

                            followTime = 86400 - LocalTime.parse(timer[1]).until(localTime, ChronoUnit.SECONDS);

                            check = true;
                            break;
                        }
                    }
                }
            }
        }
        return time;
    }
}
