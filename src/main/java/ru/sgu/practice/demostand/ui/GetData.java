package ru.sgu.practice.demostand.ui;

import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Enumeration;

/**
 * Created by --- on 10.07.2017.
 */
@WebServlet(urlPatterns = {"/dataInput"})
@Component
public class GetData extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Enumeration en = req.getParameterNames();
        String name = (String)en.nextElement();
        String value = req.getParameter(name);
        Instant instant = Instant.now();
        LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        GlobalVariables.inst[GlobalVariables.j] = ldt.toInstant(ZoneOffset.UTC);
        GlobalVariables.values[GlobalVariables.j] = Double.parseDouble(value);
        GlobalVariables.j++;

    }
}
