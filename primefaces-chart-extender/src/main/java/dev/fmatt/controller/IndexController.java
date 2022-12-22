package dev.fmatt.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.data.NumericPoint;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.scatter.ScatterChartModel;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named
@ViewScoped
public class IndexController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ScatterChartModel scatterModel;
	
	@PostConstruct
	public void init() {
		scatterModel = new ScatterChartModel();
        ChartData data = new ChartData();
        
        LineChartDataSet dataSet = new LineChartDataSet();
        List<Object> values = new ArrayList<>();
        
        values.add(new NumericPoint(1, 65));
        values.add(new NumericPoint(2, 59));
        values.add(new NumericPoint(3, 80));
        values.add(new NumericPoint(4, 81));
        values.add(new NumericPoint(5, 56));
        values.add(new NumericPoint(6, 55));
        values.add(new NumericPoint(7, 40));
        dataSet.setData(values);
        dataSet.setFill(false);
        dataSet.setLabel("My Dataset");
        dataSet.setBorderColor("rgb(75, 192, 192)");
        data.addChartDataSet(dataSet);

        List<String> labels = new ArrayList<>();
        labels.add("AA");
        labels.add("BB");
        labels.add("CC");
        labels.add("DD");
        labels.add("EE");
        labels.add("FF");
        labels.add("GG");
        data.setLabels(labels);

        //Options
        LineChartOptions options = new LineChartOptions();
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Line Chart");
        options.setTitle(title);
       
        scatterModel.setOptions(options);
        scatterModel.setData(data);
        scatterModel.setExtender("chartExtender");
	}

	/*
	 * Auto-generated
	 */
	public ScatterChartModel getScatterModel() {
		return scatterModel;
	}

	public void setScatterModel(ScatterChartModel scatterModel) {
		this.scatterModel = scatterModel;
	}

}
