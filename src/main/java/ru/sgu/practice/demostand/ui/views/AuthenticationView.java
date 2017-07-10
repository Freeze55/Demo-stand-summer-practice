package ru.sgu.practice.demostand.ui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import ru.sgu.practice.demostand.ui.MainUI;

/**
 * Created by admin on 10.07.2017.
 */
public class AuthenticationView  extends VerticalLayout implements View{
    private static final long serialVersionUID = 1L;
    public static final String NAME = "";

    public AuthenticationView(){
        Panel panel = new Panel("Вход");
        panel.setSizeUndefined();
        addComponent(panel);


        FormLayout content = new FormLayout();
        TextField username = new TextField("Логин");
        content.addComponent(username);
        PasswordField password = new PasswordField("Пароль");
        content.addComponent(password);

        Button send = new Button("Войти");
        send.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if(MainUI.AUTH.authenticate(username.getValue(), password.getValue())){
                    VaadinSession.getCurrent().setAttribute("user", username.getValue());

                    getUI().getNavigator().addView(TestView.NAME,TestView.class);
                    getUI().getNavigator().navigateTo(TestView.NAME);
                }else{
                    Notification.show("Неправильный логин/пароль!", Notification.Type.ERROR_MESSAGE);
                }
            }
        });

        content.addComponent(send);
        content.setSizeUndefined();
        content.setMargin(true);
        panel.setContent(content);
        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
