package ru.sgu.practice.demostand.ui;

/**
 * Created by --- on 10.07.2017.
 */

import java.io.RandomAccessFile;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.AxisTitle;
import com.vaadin.addon.charts.model.AxisType;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.DateTimeLabelFormats;
import com.vaadin.addon.charts.model.PlotOptionsSpline;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.ui.Component;


public class ImageCharts {
    public Component getChart(String title, String axisX, String axisY, String data) {

        Chart chart = new Chart();
        chart.setHeight("450px");
        chart.setWidth("600px");

        Configuration configuration = chart.getConfiguration();
        configuration.getChart().setType(ChartType.SPLINE);

        configuration.getTitle().setText(title);

        configuration.getTooltip().setFormatter("");

        configuration.getxAxis().setType(AxisType.DATETIME);
        configuration.getxAxis().setDateTimeLabelFormats(
                new DateTimeLabelFormats("%e. %b", "%b"));

        YAxis yAxis = configuration.getyAxis();
        yAxis.setTitle(new AxisTitle(axisY));
        yAxis.setMin(0);

       // configuration
        //        .getTooltip()
        //        .setFormatter(
        //                "'<b>'+ this.series.name +'</b><br/>\'+ Highcharts.dateFormat('%e. %b', this.x) +': '+ this.y +' m'");

        DataSeries ls = new DataSeries();
        ls.setPlotOptions(new PlotOptionsSpline());
        ls.setName(axisX);

        Object[][] data1 = getData1(data);
        for (int i = 0; i < data1.length; i++) {
            Object[] ds = data1[i];
            DataSeriesItem item = new DataSeriesItem((Instant) ds[0],
                    (Double) ds[1]);
            ls.add(item);
        }

        DataSeriesItem dat = new DataSeriesItem();

        configuration.addSeries(ls);


        chart.drawChart(configuration);
        return chart;
    }

    private Object[][] getData1(String data) {
        Object [][]objects = new Object[GlobalVariables.j][2];
        try {
           // RandomAccessFile dat = new RandomAccessFile(data, "rw");
            //int length = GlobalVariables.j;
            for (int i = 0; i < GlobalVariables.j; i++) {
                objects[i][0] = GlobalVariables.inst[i];
                objects[i][1] = GlobalVariables.values[i];
            }
        }catch (Exception e){}
        return objects;
    }

    private final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy,MM,dd");

    /**
     * Helper method to convert Date string YYYY,MM,dd to Date
     *
     * @param dateString
     * @return
     */
    /**
     * Helper method to convert Date string YYYY,MM,dd to Date
     *
     * @param stringFormat
     * @return
     */
    private Instant d(String stringFormat) {
        LocalDateTime date;
        try {
            date = LocalDateTime.parse(stringFormat, df);
        } catch (DateTimeParseException e) {
            throw new RuntimeException(e);
        }
        return date.toInstant(ZoneOffset.UTC);
       // return date.atStartOfDay().toInstant(ZoneOffset.UTC);
    }


}
