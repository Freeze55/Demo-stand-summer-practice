package ru.sgu.practice.demostand.ui.views;

import com.vaadin.addon.charts.Chart;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.datefield.DateTimeResolution;
import com.vaadin.ui.*;
import ru.sgu.practice.demostand.ui.ImageCharts;
import ru.sgu.practice.demostand.ui.MainUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


/**
 * Created by --- on 10.07.2017.
 */
public class ContentView  extends VerticalLayout implements View {
    public static final String NAME = "hello";

    public ContentView(){
        String basepath = VaadinService.getCurrent()
                .getBaseDirectory().getAbsolutePath();
        Page.getCurrent().setTitle("Не главная страница");
        Page.Styles styles = Page.getCurrent().getStyles();
        HorizontalLayout layoutRoot = new HorizontalLayout();
        VerticalLayout layout = new VerticalLayout();
        HorizontalLayout layoutUp = new HorizontalLayout();
        HorizontalLayout horLay = new HorizontalLayout();
        HorizontalLayout layoutData = new HorizontalLayout();
        VerticalLayout vertical0 = new VerticalLayout();
        VerticalLayout vertical1 = new VerticalLayout();

        VerticalLayout verticalLayout = new VerticalLayout();

        InlineDateTimeField dateTimeField = new InlineDateTimeField();
        dateTimeField.setValue(LocalDateTime.now(ZoneId.systemDefault()));


        int temp;
        try {
            RandomAccessFile var = new RandomAccessFile("src\\main\\webapp\\resources\\var.kotel", "rw");
            var.readInt();
            var.readDouble();
            temp = (int)(var.readDouble() * 100);
            var.close();
        } catch (FileNotFoundException e) {
            temp = 0;
        } catch (IOException e) {
            temp = 0;
        }

        int imFour = temp%10;
        temp/=10;
        int imThree = temp%10;
        temp/=10;
        int imTwo = temp%10;
        temp/=10;
        int imOne = temp%10;

        Image one = new Image(null, new FileResource(new File(basepath + "\\resources\\image\\" + imOne + ".png")));
        Image two = new Image(null, new FileResource(new File(basepath + "\\resources\\image\\" + imTwo + ".png")));
        Image three = new Image(null, new FileResource(new File(basepath + "\\resources\\image\\" + imThree + ".png")));
        Image four = new Image(null, new FileResource(new File(basepath + "\\resources\\image\\" + imFour + ".png")));
        Image space = new Image(null, new FileResource(new File(basepath + "\\resources\\image\\space.png")));
        Image space1 = new Image(null, new FileResource(new File(basepath + "\\resources\\image\\space.png")));
        Image point = new Image(null, new FileResource(new File(basepath + "\\resources\\image\\point.png")));
        Image grad = new Image(null, new FileResource(new File(basepath + "\\resources\\image\\gradus.png")));
        one.setHeight("90");
        two.setHeight("90");
        three.setHeight("90");
        four.setHeight("90");
        space.setHeight("90");
        space1.setHeight("90");
        point.setHeight("90");
        grad.setHeight("90");

        horLay.addComponents(one, space, two, point, three, space1, four, grad);
        horLay.setSpacing(false);
        styles.add(".forTemperature {border: groove 10px black; border-radius: 15px; margin-right: 100px;}");
        horLay.addStyleName("forTemperature");

        TextField input = new TextField();
        input.setCaption("Введите новое значение температуры:");


        Button button = new Button("Отправить");
        input.addShortcutListener(new ShortcutListener("Execute",
                ShortcutAction.KeyCode.ENTER, null) {
            @Override
            public void handleAction(Object sender, Object target) {
                button.click();
            }
        });

        CheckBox checkBox = new CheckBox("Установить на определенную дату", false);


        layoutData.addComponents(input, button);

        vertical0.addComponents(horLay, verticalLayout);
        vertical0.setComponentAlignment(horLay, Alignment.MIDDLE_CENTER);
        vertical0.setComponentAlignment(verticalLayout, Alignment.MIDDLE_CENTER);
        VerticalLayout calendar = new VerticalLayout();
        calendar.addComponent(dateTimeField);
        vertical1.addComponents(layoutData, checkBox, calendar);
        vertical1.setComponentAlignment(calendar, Alignment.BOTTOM_CENTER);
        layoutUp.addComponents(vertical0, vertical1);
        layoutData.setComponentAlignment(button, Alignment.BOTTOM_LEFT);
        styles.add(".forButton {margin-left: -50px;}");
        button.addStyleName("forButton");
        styles.add(".forCalendar {margin-top: 50px;}");
        calendar.addStyleName("forCalendar");
        styles.add(".forVertical0 {margin-right: 120px;}");
        vertical0.addStyleName("forVertical0");

        layout.addComponents(layoutUp);

        styles.add(".forAction{background: #fafafa url(/resources/shadow0.png) no-repeat 100% 100%; padding: 10px; width: 300px; font-size: 13px; font-style: italic;}");
        styles.add(".forP{margin:-15px -15px;}");
        styles.add(".forInstall{font-size:17px; text-align:center; margin-top: -25px;}");
        String install;
        try {
            RandomAccessFile var = new RandomAccessFile("src\\main\\webapp\\resources\\var.kotel", "rw");
            var.readInt();
            install = Double.toString(var.readDouble()) + "°C";
            var.close();
        } catch (Exception e) {
            install = "ошибка";
        }
        Label labelNow = new Label("<p>Установлено: " + install +"</p>");
        labelNow.setContentMode(ContentMode.HTML);
        labelNow.setWidth("250px");
        labelNow.addStyleName("forP");
        labelNow.addStyleName("forInstall");
        verticalLayout.addComponent(labelNow);

        try {
            RandomAccessFile lab = new RandomAccessFile("src\\main\\webapp\\resources\\labels.kotel", "rw");
            int n = lab.readInt();
            for (int i = 0; i < n; i++){
                LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(lab.readLong(), 0, ZoneOffset.UTC);
                Label label = new Label("<p>" + localDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) +"<br>" + lab.readUTF() + "</p>");
                label.setContentMode(ContentMode.HTML);
                label.setWidth("250px");
                label.addStyleName("forP");
                verticalLayout.addComponent(label);
            }
            lab.close();
        } catch (Exception e) {
        }
        verticalLayout.addStyleName("forAction");

        dateTimeField.setLocale(Locale.getDefault());
        dateTimeField.setResolution(DateTimeResolution.MINUTE);
        dateTimeField.setVisible(false);
        dateTimeField.setCaption("Выберите дату и время");

        GridLayout layoutCharts = new GridLayout(3, 2);

        Chart chartHome = new ImageCharts().getChart("В доме, °C", 0);
        Chart chartOutdoors = new ImageCharts().getChart("На улице, °C", 1);
        Chart chartKotel = new ImageCharts().getChart("Вода в котле, °C", 2);
        Button buttonHome = new Button("Увеличить");
        Button buttonOutdoors = new Button("Увеличить");
        Button buttonKotel = new Button("Увеличить");

        layoutCharts.addComponents(chartHome, chartOutdoors, chartKotel, buttonHome, buttonOutdoors, buttonKotel);
        layoutCharts.setComponentAlignment(buttonHome, Alignment.TOP_CENTER);
        layoutCharts.setComponentAlignment(buttonOutdoors, Alignment.TOP_CENTER);
        layoutCharts.setComponentAlignment(buttonKotel, Alignment.TOP_CENTER);

        layout.addComponent(layoutCharts);
        styles.add(".forChart {margin: 50px 0px 0px;}");
        layoutCharts.addStyleName("forChart");


        layout.setComponentAlignment(layoutUp, Alignment.MIDDLE_CENTER);
        layout.setComponentAlignment(layoutCharts, Alignment.MIDDLE_CENTER);

        styles.add(".forContent {background: #ffffff; margin: 10px; border: outset 8px; border-radius: 6px}");
        layout.addStyleName("forContent");
        layout.setWidth("1600");

        Image kot0 = new Image(null, new FileResource(new File(basepath + "\\resources\\kot0.png")));
        Image kot1 = new Image(null, new FileResource(new File(basepath + "\\resources\\kot1.png")));
        kot0.setWidth("100");
        kot1.setWidth("100");
        layoutRoot.addComponents(kot0, layout, kot1);
        layoutRoot.setComponentAlignment(kot0, Alignment.MIDDLE_LEFT);
        layoutRoot.setComponentAlignment(layout, Alignment.MIDDLE_CENTER);
        layoutRoot.setComponentAlignment(kot1, Alignment.MIDDLE_RIGHT);
        addComponent(layoutRoot);
        setComponentAlignment(layoutRoot, Alignment.MIDDLE_CENTER);

        button.addClickListener( e -> {
            boolean test, testValue;
            String s = "", s1 = "";
            try{
            Double.parseDouble(input.getValue());
            testValue = true;
            }catch (NumberFormatException e1){
                testValue = false;
            }
            double value = Double.parseDouble(input.getValue());
            if (10 > value || value > 50) {
                s = "Введено неправильное значение. Температура должна быть между 10°C и 50°C.";
                s1 = "160";
                test = false;
            } else {
                if (checkBox.getValue() && dateTimeField.getValue().toEpochSecond(ZoneOffset.UTC) < LocalDateTime.now(ZoneId.systemDefault()).toEpochSecond(ZoneOffset.UTC)){
                    s = "Выбранная дата меньше текущей. Выберите другую дату.";
                    s1 = "160";
                    test = false;
                } else {
                    if (testValue) {
                        if (MainUI.COMMUNIC.communicate(value, checkBox.getValue(), dateTimeField.getValue().toEpochSecond(ZoneOffset.UTC)) == 0) {
                            MainUI.getCurrent().getNavigator().navigateTo(ContentView.NAME);
                            test = true;
                        } else {
                            s = "Нет соединения с сервером, повторите попытку позже.";
                            s1 = "140";
                            test = false;
                        }
                    } else {
                        s = "Неверный формат данных. В поле должно быть записано число, целая часть отделяется от дробной точкой.";
                        s1 = "190";
                        test = false;
                    }
                }
            }
            if (!test){
                newWindow(s, s1);
            }
        });

        buttonHome.addClickListener(e->{
            Window window = new Window();
            VerticalLayout verticalChart1 = new VerticalLayout();
            Chart chartWindow = new Chart();
            chartWindow.setData(chartHome.getData());
            chartWindow.setConfiguration(chartHome.getConfiguration());
            chartWindow.setHeight("600px");
            chartWindow.setWidth("100%");
            verticalChart1.addComponent(chartWindow);
            window.setContent(verticalChart1);
            window.setModal(true);
            window.setResizable(false);
            window.setWidth("95%");
            window.center();
            this.getUI().addWindow(window);
        });
        buttonOutdoors.addClickListener(e->{
            Window window = new Window();
            VerticalLayout verticalChart1 = new VerticalLayout();
            Chart chartWindow = new Chart();
            chartWindow.setData(chartOutdoors.getData());
            chartWindow.setConfiguration(chartOutdoors.getConfiguration());
            chartWindow.setHeight("600px");
            chartWindow.setWidth("100%");
            verticalChart1.addComponent(chartWindow);
            window.setContent(verticalChart1);
            window.setModal(true);
            window.setResizable(false);
            window.setWidth("95%");
            window.center();
            this.getUI().addWindow(window);
        });
        buttonKotel.addClickListener(e->{
            Window window = new Window();
            VerticalLayout verticalChart1 = new VerticalLayout();
            Chart chartWindow = new Chart();
            chartWindow.setData(chartKotel.getData());
            chartWindow.setConfiguration(chartKotel.getConfiguration());
            chartWindow.setHeight("600px");
            chartWindow.setWidth("100%");
            verticalChart1.addComponent(chartWindow);
            window.setContent(verticalChart1);
            window.setModal(true);
            window.setResizable(false);
            window.setWidth("95%");
            window.center();
            this.getUI().addWindow(window);
        });

        checkBox.addValueChangeListener(e -> {
            if(e.getValue()){
                dateTimeField.setValue(LocalDateTime.now(ZoneId.systemDefault()));
                dateTimeField.setVisible(true);
            }
            else {
                dateTimeField.setVisible(false);
            }
        });

    }


    private void newWindow(String name, String height){
        final Window window = new Window();
        Panel panel = new Panel("Внимание!");
        panel.setSizeFull();

        VerticalLayout vertical = new VerticalLayout();

        Label label = new Label(name);
        label.setWidth("425");
        vertical.addComponent(label);
        Button buttonWindow = new Button("OK", event -> window.close());
        vertical.addComponent(buttonWindow);
        vertical.setComponentAlignment(buttonWindow, Alignment.MIDDLE_RIGHT);
        panel.setContent(vertical);
        window.setContent(panel);
        window.setWidth("450");
        window.setHeight(height);
        window.setClosable(false);
        window.setResizable(false);
        window.setModal(true);
        window.center();
        this.getUI().getUI().addWindow(window);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
