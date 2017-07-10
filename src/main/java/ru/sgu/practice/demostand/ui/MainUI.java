package ru.sgu.practice.demostand.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import ru.sgu.practice.demostand.ui.authentication.Authentication;
import ru.sgu.practice.demostand.ui.views.AuthenticationView;

/**
 * Created by admin on 10.07.2017.
 */
@SpringUI
@Theme("valo")
@Title("Главная страница")
public class MainUI extends UI {


    Navigator navigator;
    public static Authentication AUTH;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        AUTH = new Authentication();
        navigator = new Navigator(this, this);

        navigator.addView(AuthenticationView.NAME, AuthenticationView.class);






    }
}
