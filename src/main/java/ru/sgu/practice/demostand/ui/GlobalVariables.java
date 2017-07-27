package ru.sgu.practice.demostand.ui;


import java.io.File;
import java.io.RandomAccessFile;

/**
 * Created by --- on 10.07.2017.
 */
public class GlobalVariables {
    static File var = new File("src\\main\\webapp\\resources\\var.kotel");
    static File date = new File("src\\main\\webapp\\resources\\date.kotel");
    static File home = new File("src\\main\\webapp\\resources\\home.kotel");
    static File outdoors = new File("src\\main\\webapp\\resources\\outdoors.kotel");
    static File kotel = new File("src\\main\\webapp\\resources\\kotel.kotel");
    static File labels = new File("src\\main\\webapp\\resources\\labels.kotel");

    private static void updateFiles(){
        try {
            var.delete();
            var.createNewFile();
            RandomAccessFile rand = new RandomAccessFile("src\\main\\webapp\\resources\\var.kotel", "rw");
            rand.writeInt(0);
            rand.writeDouble(0);
            rand.writeDouble(0);
            rand.close();
            date.delete();
            date.createNewFile();
            home.delete();
            home.createNewFile();
            outdoors.delete();
            outdoors.createNewFile();
            kotel.delete();
            kotel.createNewFile();
        }catch (Exception e){ }
    }

    public static void checkingFiles(){
        try {
            if (!var.exists() || !date.exists() || !home.exists() || !outdoors.exists() || !kotel.exists()) {
                updateFiles();
            }

            if (!labels.exists()) {
                labels.createNewFile();
                RandomAccessFile rand = new RandomAccessFile("src\\main\\webapp\\resources\\labels.kotel", "rw");
                rand.writeInt(0);
                rand.close();
            }
        } catch (Exception e){ }
    }
}
