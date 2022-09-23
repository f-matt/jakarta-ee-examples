package com.github.fmatt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.DualListModel;

@Named
@ViewScoped
public class IndexController implements Serializable {

	private Logger logger = Logger.getAnonymousLogger();

	private List<MyEntity> source;

	private List<MyEntity> target;
	
	private DualListModel<MyEntity> listModel;
	
	private String selected = "";
	
	@PostConstruct
	public void init() {
		source = Arrays.asList(new MyEntity(1, "Entity A"), new MyEntity(2, "Entity B"), new MyEntity(3, "Entity C"));
		target = new ArrayList<>();
		
		listModel = new DualListModel<MyEntity>(source, target);
	}
	
	public void send() {
		logger.info(listModel.getSource().toString());
		logger.info(listModel.getTarget().toString());

		selected = "";
		
		for (MyEntity entity : listModel.getTarget())
			selected += (entity.toString() + " "); 
	}

	public DualListModel<MyEntity> getListModel() {
		return listModel;
	}

	public void setListModel(DualListModel<MyEntity> listModel) {
		this.listModel = listModel;
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

}
