package ru.sgu.practice.demostand.ui;

import java.net.ServerSocket;
import java.net.Socket;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

/**
 * Created by --- on 26.07.2017.
 */
public class SetTime implements Runnable {
    static private ServerSocket server;
    static private Socket connection;

    @Override
    public void run() {
        try {
            server = new ServerSocket(8082, 10);
            while (true) {
                connection = server.accept();
                Instant instant = LocalDateTime.now(ZoneId.systemDefault()).toInstant(ZoneOffset.UTC);
                connection.getOutputStream().write(Long.toString(instant.getEpochSecond()).getBytes());
            }
        } catch (Exception e) {}
    }
}
