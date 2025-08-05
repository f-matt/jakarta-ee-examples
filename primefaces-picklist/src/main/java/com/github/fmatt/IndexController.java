package com.github.fmatt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.model.DualListModel;

@Named
@ViewScoped
public class IndexController implements Serializable {

	private final Logger logger = Logger.getAnonymousLogger();

	private List<MyEntity> source;

	private List<MyEntity> target;
	
	private DualListModel<MyEntity> listModel;

    private String available = "";
	
	private String selected = "";
	
	@PostConstruct
	public void init() {
		source = Arrays.asList(new MyEntity(1, "Entity A"), new MyEntity(2, "Entity B"),
				new MyEntity(3, "Entity C"));
		target = new ArrayList<>();
		
		listModel = new DualListModel<>(new ArrayList<>(source), new ArrayList<>(target));
	}
	
	public void send() {
        StringBuilder builder = new StringBuilder();
		for (MyEntity entity : listModel.getSource())
            builder.append(entity.toString());
        available = builder.toString();

        builder = new StringBuilder();
        for (MyEntity entity : listModel.getTarget())
            builder.append(entity.toString());
        selected = builder.toString();

        logger.severe("Available: " + available);
        logger.severe("Selected: " + selected);
    }

	public DualListModel<MyEntity> getListModel() {
		return listModel;
	}

	public void setListModel(DualListModel<MyEntity> listModel) {
		this.listModel = listModel;
	}

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

    public List<MyEntity> getSource() {
        return source;
    }

    public void setSource(List<MyEntity> source) {
        this.source = source;
    }

    public List<MyEntity> getTarget() {
        return target;
    }

    public void setTarget(List<MyEntity> target) {
        this.target = target;
    }



}
