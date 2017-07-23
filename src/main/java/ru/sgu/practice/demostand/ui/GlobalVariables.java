package ru.sgu.practice.demostand.ui;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

/**
 * Created by --- on 10.07.2017.
 */
public class GlobalVariables {

    public static void setVal(){

        try {
           /* RandomAccessFile var = new RandomAccessFile("src\\main\\webapp\\resources\\var.kotel", "rw");
            RandomAccessFile date = new RandomAccessFile("src\\main\\webapp\\resources\\date.kotel", "rw");
            RandomAccessFile home = new RandomAccessFile("src\\main\\webapp\\resources\\home.kotel", "rw");
            RandomAccessFile out = new RandomAccessFile("src\\main\\webapp\\resources\\outdoors.kotel", "rw");
            RandomAccessFile kotel = new RandomAccessFile("src\\main\\webapp\\resources\\kotel.kotel", "rw");
            var.setLength(0);
            date.setLength(0);
            home.setLength(0);
            out.setLength(0);
            kotel.setLength(0);

            var.writeInt(1);            //j
            var.writeDouble(17.09);     //установлено
            var.writeDouble(11.18);     //в доме сейчас
            date.writeLong(LocalDateTime.now(ZoneId.systemDefault()).toInstant(ZoneOffset.UTC).getEpochSecond());
            home.writeDouble(11.18);
            out.writeDouble(33.95);
            kotel.writeDouble(71.17);

            var.close();
            date.close();
            home.close();
            out.close();
            kotel.close();*/
            RandomAccessFile lab = new RandomAccessFile("src\\main\\webapp\\resources\\labels.kotel", "rw");
            lab.writeInt(2);
            lab.writeLong(LocalDateTime.now(ZoneId.systemDefault()).toInstant(ZoneOffset.UTC).getEpochSecond());
            lab.writeUTF("Температура была изменена с 15 на 35 градусов");
            lab.writeLong(LocalDateTime.now(ZoneId.systemDefault()).toInstant(ZoneOffset.UTC).getEpochSecond());
            lab.writeUTF("Температура была изменена с 35.12 на 23.22 градусов");
            lab.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
