package ru.sgu.practice.demostand.ui;

/**
 * Created by --- on 10.07.2017.
 */

import java.io.RandomAccessFile;
import java.time.Instant;
import java.util.Locale;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.*;


public class ImageCharts {
    public Chart getChart(String title, int number) {

        Chart chart = new Chart();
        chart.setHeight("500px");
        chart.setWidth("500px");

        Configuration configuration = chart.getConfiguration();
        configuration.getChart().setType(ChartType.SPLINE);

        configuration.getTooltip().setFormatter("'<br/>\'+ Highcharts.dateFormat('%e.%m %H:%M', this.x) +': <b>'+ this.y +'°C</b>'");

        configuration.getTitle().setText(title);

        configuration.getxAxis().setType(AxisType.DATETIME);
        configuration.getxAxis().setDateTimeLabelFormats(new DateTimeLabelFormats("%d.%m", "%b"));
        configuration.getxAxis().getDateTimeLabelFormats().setDay("%d.%m %H:%M");
        configuration.getxAxis().getDateTimeLabelFormats().setMinute("%d.%m %H:%M");
        configuration.getxAxis().getDateTimeLabelFormats().setHour("%d.%m %H:%M");
        configuration.getxAxis().setTickPixelInterval(60);

        YAxis yAxis = configuration.getyAxis();
        yAxis.setTitle("");
        yAxis.setMin(0);

        Legend legend = new Legend();
        legend.setEnabled(false);
        configuration.setLegend(legend);
        Marker marker = new Marker();
        marker.setEnabled(false);

        DataSeries ls = new DataSeries();
        ls.setPlotOptions(new PlotOptionsSpline());

        Object[][] data1;

        switch (number){
            case 0:
               data1  = getData0();
               break;
            case 1:
                data1  = getData1();
                break;
            case 2:
                data1  = getData2();
                break;
            default:
                data1 = getData0();
                break;
        }

        for (int i = 0; i < data1.length; i++) {
            Object[] ds = data1[i];
            DataSeriesItem item = new DataSeriesItem((Instant) ds[0],
                    (Double) ds[1]);
            item.setMarker(marker);
            ls.add(item);
        }

        configuration.addSeries(ls);


        chart.drawChart(configuration);
        return chart;
    }

    private Object[][] getData0() {
        Object [][]objects;
        try {
            RandomAccessFile var = new RandomAccessFile("src\\main\\webapp\\resources\\var.kotel", "r");
            RandomAccessFile date = new RandomAccessFile("src\\main\\webapp\\resources\\date.kotel", "r");
            RandomAccessFile home = new RandomAccessFile("src\\main\\webapp\\resources\\home.kotel", "r");
            int n = var.readInt();
            objects = new Object[n][2];
            for (int i = 0; i < n; i++){
                objects[i][0] = Instant.ofEpochSecond(date.readLong());
                objects[i][1] = home.readDouble();
            }

            var.close();
            date.close();
            home.close();
        } catch (Exception e) {
            objects = new Object[0][0];
        }
        return objects;
    }

    private Object[][] getData1() {
        Object [][]objects;
        try {
            RandomAccessFile var = new RandomAccessFile("src\\main\\webapp\\resources\\var.kotel", "r");
            RandomAccessFile date = new RandomAccessFile("src\\main\\webapp\\resources\\date.kotel", "r");
            RandomAccessFile out = new RandomAccessFile("src\\main\\webapp\\resources\\outdoors.kotel", "r");
            int n = var.readInt();
            objects = new Object[n][2];
            for (int i = 0; i < n; i++){
                objects[i][0] = Instant.ofEpochSecond(date.readLong());
                objects[i][1] = out.readDouble();
            }

            var.close();
            date.close();
            out.close();
        } catch (Exception e) {
            objects = new Object[0][0];
        }
        return objects;
    }

    private Object[][] getData2() {
        Object [][]objects;
        try {
            RandomAccessFile var = new RandomAccessFile("src\\main\\webapp\\resources\\var.kotel", "r");
            RandomAccessFile date = new RandomAccessFile("src\\main\\webapp\\resources\\date.kotel", "r");
            RandomAccessFile kotel = new RandomAccessFile("src\\main\\webapp\\resources\\kotel.kotel", "r");
            int n = var.readInt();
            objects = new Object[n][2];
            for (int i = 0; i < n; i++){
                objects[i][0] = Instant.ofEpochSecond(date.readLong());
                objects[i][1] = kotel.readDouble();
            }

            var.close();
            date.close();
            kotel.close();
        } catch (Exception e) {
            objects = new Object[0][0];
        }
        return objects;
    }
}
