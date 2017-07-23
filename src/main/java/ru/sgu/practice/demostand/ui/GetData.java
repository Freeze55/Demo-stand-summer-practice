package ru.sgu.practice.demostand.ui;

import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

/**
 * Created by --- on 10.07.2017.
 */
@WebServlet(urlPatterns = {"/dataInput"})
@Component
public class GetData extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            RandomAccessFile var = new RandomAccessFile("src\\main\\webapp\\resources\\var.kotel", "rw");
            File temp = new File("src\\main\\webapp\\resources\\temp.kotel");
            temp.createNewFile();
            RandomAccessFile rand = new RandomAccessFile("src\\main\\webapp\\resources\\temp.kotel", "rw");
            RandomAccessFile date = new RandomAccessFile("src\\main\\webapp\\resources\\date.kotel", "rw");
            RandomAccessFile home = new RandomAccessFile("src\\main\\webapp\\resources\\home.kotel", "rw");
            RandomAccessFile out = new RandomAccessFile("src\\main\\webapp\\resources\\outdoors.kotel", "rw");
            RandomAccessFile kotel = new RandomAccessFile("src\\main\\webapp\\resources\\kotel.kotel", "rw");

            Instant instant = LocalDateTime.now(ZoneId.systemDefault()).toInstant(ZoneOffset.UTC);
            int n = var.readInt();
            if (n < 2016) {
                date.seek(date.length());
                home.seek(home.length());
                out.seek(out.length());
                kotel.seek(kotel.length());

                rand.writeInt(n + 1);
                rand.writeDouble(Double.parseDouble(req.getParameter("temp")));
                rand.writeDouble(Double.parseDouble(req.getParameter("valueHome")));

                date.writeLong(instant.getEpochSecond());
                home.writeDouble(Double.parseDouble(req.getParameter("valueHome")));
                out.writeDouble(Double.parseDouble(req.getParameter("valueOut")));
                kotel.writeDouble(Double.parseDouble(req.getParameter("valueKotel")));

                rand.close();
                var.close();
                date.close();
                home.close();
                out.close();
                kotel.close();

            }
            else{
                File datetemp = new File("src\\main\\webapp\\resources\\datetemp.kotel");
                temp.createNewFile();
                File hometemp = new File("src\\main\\webapp\\resources\\hometemp.kotel");
                temp.createNewFile();
                File outtemp = new File("src\\main\\webapp\\resources\\outtemp.kotel");
                temp.createNewFile();
                File koteltemp = new File("src\\main\\webapp\\resources\\koteltemp.kotel");
                temp.createNewFile();
                RandomAccessFile daterand = new RandomAccessFile("src\\main\\webapp\\resources\\datetemp.kotel", "rw");
                RandomAccessFile homerand = new RandomAccessFile("src\\main\\webapp\\resources\\hometemp.kotel", "rw");
                RandomAccessFile outrand = new RandomAccessFile("src\\main\\webapp\\resources\\outtemp.kotel", "rw");
                RandomAccessFile kotelrand = new RandomAccessFile("src\\main\\webapp\\resources\\koteltemp.kotel", "rw");
                rand.writeInt(n);
                rand.writeDouble(Double.parseDouble(req.getParameter("temp")));
                rand.writeDouble(Double.parseDouble(req.getParameter("valueHome")));
                date.readLong();
                home.readDouble();
                out.readDouble();
                kotel.readDouble();
                for (int i = 0; i < 2015; i++){
                    daterand.writeLong(date.readLong());
                    homerand.writeDouble(home.readDouble());
                    outrand.writeDouble(out.readDouble());
                    kotelrand.writeDouble(kotel.readDouble());
                }
                daterand.writeLong(instant.getEpochSecond());
                homerand.writeDouble(Double.parseDouble(req.getParameter("valueHome")));
                outrand.writeDouble(Double.parseDouble(req.getParameter("valueOut")));
                kotelrand.writeDouble(Double.parseDouble(req.getParameter("valueKotel")));

                rand.close();
                var.close();
                daterand.close();
                date.close();
                homerand.close();
                home.close();
                outrand.close();
                out.close();
                kotelrand.close();
                kotel.close();

                File d = new File("src\\main\\webapp\\resources\\date.kotel");
                d.delete();
                datetemp.renameTo(new File("src\\main\\webapp\\resources\\date.kotel"));
                File h = new File("src\\main\\webapp\\resources\\home.kotel");
                h.delete();
                hometemp.renameTo(new File("src\\main\\webapp\\resources\\home.kotel"));
                File o = new File("src\\main\\webapp\\resources\\outdoors.kotel");
                o.delete();
                outtemp.renameTo(new File("src\\main\\webapp\\resources\\outdoors.kotel"));
                File k = new File("src\\main\\webapp\\resources\\kotel.kotel");
                k.delete();
                koteltemp.renameTo(new File("src\\main\\webapp\\resources\\kotel.kotel"));
            }

            File v = new File("src\\main\\webapp\\resources\\var.kotel");
            v.delete();
            temp.renameTo(new File("src\\main\\webapp\\resources\\var.kotel"));

        }catch (Exception e){}

    }
}
