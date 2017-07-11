package ru.sgu.practice.demostand.ui;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * Created by --- on 10.07.2017.
 */
public class GlobalVariables {
    public static double temperature;
    public static Instant [] inst = new Instant [2016];
    public static Date[]  dates = new Date[2016];
    public static double [] values = new double[2016];
    public static int j;




    public static void setVal(){
        double x = 11.18;

            Instant instant = Instant.now();
            LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            GlobalVariables.inst[0] = ldt.toInstant(ZoneOffset.UTC);
            GlobalVariables.dates[0] = new Date();
            GlobalVariables.values[0] = x;
            x++;
            GlobalVariables.j = 1;
            GlobalVariables.temperature = 17.09;
    }
}
