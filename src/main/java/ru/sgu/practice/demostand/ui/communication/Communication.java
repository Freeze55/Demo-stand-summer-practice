package ru.sgu.practice.demostand.ui.communication;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * Created by --- on 12.07.2017.
 */

public class Communication {

    public int communicate(double value, Boolean flag, long seconds) {
        try {
            Socket connection = new Socket(InetAddress.getByName("0.0.0.0"), 5678);
            OutputStream outputStream = connection.getOutputStream();
            InputStream inputStream = connection.getInputStream();

            if (flag == true) {
                outputStream.write(Byte.parseByte("2"));
                int x = (int)(value*100);
                outputStream.write(Byte.parseByte(Integer.toString(x/100)));
                outputStream.write(Byte.parseByte(Integer.toString(x%100)));
                outputStream.write(Long.toString(seconds).getBytes());

            } else {
                outputStream.write(Byte.parseByte("1"));
                int x = (int)(value*100);
                outputStream.write(Byte.parseByte(Integer.toString(x/100)));
                outputStream.write(Byte.parseByte(Integer.toString(x%100)));

            }
            outputStream.flush();
            int answer = inputStream.read();
            answer -= 48;
            connection.close();
            String string;
            double after;
            if (answer == 0) {
                if (flag == false) {
                    File temp = new File("src\\main\\webapp\\resources\\temp.kotel");
                    temp.createNewFile();
                    RandomAccessFile rand = new RandomAccessFile("src\\main\\webapp\\resources\\temp.kotel", "rw");
                    RandomAccessFile var = new RandomAccessFile("src\\main\\webapp\\resources\\var.kotel", "r");
                    rand.writeInt(var.readInt());
                    rand.writeDouble(value);
                    after = var.readDouble();
                    rand.writeDouble(var.readDouble());
                    var.close();
                    rand.close();
                    File v = new File("src\\main\\webapp\\resources\\var.kotel");
                    v.delete();
                    temp.renameTo(new File("src\\main\\webapp\\resources\\var.kotel"));
                    string = "Температура была изменена с " + after + "°C на " + value + "°C";
                }
                else {
                    LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(seconds, 0, ZoneOffset.UTC);
                    string = "На " + localDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) + " была установлена температура " + value + "°C";
                }
                File temp = new File("src\\main\\webapp\\resources\\temp.kotel");
                temp.createNewFile();
                RandomAccessFile rand = new RandomAccessFile("src\\main\\webapp\\resources\\temp.kotel", "rw");
                RandomAccessFile lab = new RandomAccessFile("src\\main\\webapp\\resources\\labels.kotel", "rw");
                int n = lab.readInt();
                if (n < 5){
                    rand.writeInt(n + 1);
                    rand.writeLong(LocalDateTime.now(ZoneId.systemDefault()).toInstant(ZoneOffset.UTC).getEpochSecond());
                    rand.writeUTF(string);
                    for(int i = 0; i < n; i++){
                        rand.writeLong(lab.readLong());
                        rand.writeUTF(lab.readUTF());
                    }
                }
                else {
                    rand.writeInt(n);
                    rand.writeLong(LocalDateTime.now(ZoneId.systemDefault()).toInstant(ZoneOffset.UTC).getEpochSecond());
                    rand.writeUTF(string);
                    for (int i = 0; i < 4; i++){
                        rand.writeLong(lab.readLong());
                        rand.writeUTF(lab.readUTF());
                    }
                }
                rand.close();
                lab.close();
                File l = new File("src\\main\\webapp\\resources\\labels.kotel");
                l.delete();
                temp.renameTo(new File("src\\main\\webapp\\resources\\labels.kotel"));

                return 0;
            } else {
                return 1;
            }
        } catch (Exception e) {
            return 1;
        }
    }

}