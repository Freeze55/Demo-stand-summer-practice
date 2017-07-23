package ru.sgu.practice.demostand.ui.communication;


import com.sun.imageio.plugins.common.InputStreamAdapter;

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

    public int communicate(String value, Boolean flag, long seconds) {
        try {
            Socket connection = new Socket(InetAddress.getByName("0.0.0.0"), 5678);
            //ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
            //ObjectInputStream input = new ObjectInputStream(connection.getInputStream());
            OutputStream outputStream = connection.getOutputStream();
            InputStream inputStream = connection.getInputStream();

            if (flag == true) {
                //output.writeObject(2);
                //output.writeObject(value);
                //output.writeObject(seconds);

                outputStream.write(2);
                outputStream.write(value.getBytes());
                outputStream.write(Long.toString(seconds).getBytes());

            } else {
               // output.writeObject(1);
               // output.writeObject(value);
                outputStream.write(1);
                outputStream.write(value.getBytes());

            }
           // output.flush();
            outputStream.flush();
            //int answer = Integer.parseInt(input.readObject().toString());
            int answer = inputStream.read();
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
                    rand.writeDouble(Double.parseDouble(value));
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
                    string = "На " + localDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) + "была установлена температура " + value + "°C";
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