package ru.sgu.practice.demostand.ui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by admin on 10.07.2017.
 */
public class TestView extends VerticalLayout implements View {
    public static final String NAME = "TEST";

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        TextField textField = new TextField("all OK");
        addComponent(textField);
    }
}
