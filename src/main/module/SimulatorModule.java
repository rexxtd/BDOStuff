package main.module;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class SimulatorModule
{
    double baseRate, totalRate;
    int softCap;

    public void searchCsvLine(int searchColumnIndex, String searchString, String type, String equip) throws IOException
    {
        //read data from file
        String resultRow = null;
        BufferedReader br = new BufferedReader(new FileReader("src/main/data/" + type + "_" + equip + ".csv"));
        String line;
        while ( (line = br.readLine()) != null )
        {
            String[] values = line.split(",");
            if(values[searchColumnIndex].equals(searchString))
            {
                resultRow = line;
                break;
            }
        }
        br.close();
        //get the rate
        String[] split = resultRow.split(",");
        baseRate = Double.parseDouble(split[1]);
        softCap = Integer.parseInt(split[2]);
    }

    public double calculatePercent(int fs)
    {
        if (fs < softCap)
        {
            totalRate = baseRate * (1 + 0.1 * fs);
        }
        else
        {
            totalRate = baseRate * (1 + 0.1 * softCap) + (baseRate * (1 + (0.02 * (fs- softCap))));
        }

        if (totalRate >= 90)
            totalRate = 90;

        return totalRate;
    }

    public double addedRate()
    {
        return (totalRate - baseRate);
    }

    public boolean getEnhanceResult()
    {
        {
            int max = 100;
            int min = 0;
            Random random = new Random();
            double randomResult = min + (max - min) * random.nextDouble();

            if (randomResult <= totalRate)
                return true;
            else
                return false;
        }
    }
}