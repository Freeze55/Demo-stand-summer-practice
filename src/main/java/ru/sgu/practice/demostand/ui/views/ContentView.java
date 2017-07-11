package ru.sgu.practice.demostand.ui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import ru.sgu.practice.demostand.ui.GlobalVariables;
import ru.sgu.practice.demostand.ui.ImageCharts;

import java.io.File;

/**
 * Created by --- on 10.07.2017.
 */
public class ContentView  extends VerticalLayout implements View {
    public static final String NAME = "hello";
    public ContentView(){
        String basepath = VaadinService.getCurrent()
                .getBaseDirectory().getAbsolutePath();
        final VerticalLayout layout = new VerticalLayout();
        final HorizontalLayout layoutTemp = new HorizontalLayout();

        final HorizontalLayout layoutCharts = new HorizontalLayout();
        int temp = (int)(GlobalVariables.temperature*100);
        int imFour = temp%10;
        temp/=10;
        int imThree = temp%10;
        temp/=10;
        int imTwo = temp%10;
        temp/=10;
        int imOne = temp%10;



        final Image one = new Image(null, new FileResource(new File(basepath + "\\resources\\image\\" + imOne + ".png")));
        final Image two = new Image(null, new FileResource(new File(basepath + "\\resources\\image\\" + imTwo + ".png")));
        final Image three = new Image(null, new FileResource(new File(basepath + "\\resources\\image\\" + imThree + ".png")));
        final Image four = new Image(null, new FileResource(new File(basepath + "\\resources\\image\\" + imFour + ".png")));
        final Image space = new Image(null, new FileResource(new File(basepath + "\\resources\\image\\space.png")));
        final Image space1 = new Image(null, new FileResource(new File(basepath + "\\resources\\image\\space.png")));
        final Image point = new Image(null, new FileResource(new File(basepath + "\\resources\\image\\point.png")));
        final Image grad = new Image(null, new FileResource(new File(basepath + "\\resources\\image\\gradus.png")));
        one.setHeight("90");
        two.setHeight("90");
        three.setHeight("90");
        four.setHeight("90");
        space.setHeight("90");
        space1.setHeight("90");
        point.setHeight("90");
        grad.setHeight("90");
        final HorizontalLayout horLay = new HorizontalLayout();
        horLay.addComponents(one, space, two, point, three, space1, four, grad);
        horLay.setSpacing(false);

        final Page.Styles styles = Page.getCurrent().getStyles();
        styles.add(".forTemperature {border: groove 10px black; border-radius: 15px}");
        horLay.addStyleName("forTemperature");

        final TextField name = new TextField();
        name.setCaption("Введите новое значение температуры:");

        Button button = new Button("Отправить");
        button.addClickListener( e -> {
            layout.addComponent(new Label("Температура " + name.getValue()
                    + " отправлена на сервер"));
        });
        layoutCharts.setSpacing(false);
        layoutTemp.addComponents(horLay, name);
        layout.addComponents(layoutTemp);

        ImageCharts chart = new ImageCharts();
        layout.addComponent(chart.getChart("In home", "Date", "temperature", basepath + "\\resources\\data.kotel"));
        addComponent(layout);

    }




    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
