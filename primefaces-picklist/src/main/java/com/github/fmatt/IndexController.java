package com.github.fmatt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.DualListModel;

@Named
@ViewScoped
public class IndexController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private DualListModel<MyEntity> listModel;
	
	private String selected = "";
	
	@PostConstruct
	public void init() {
		List<MyEntity> source = Arrays.asList(new MyEntity(1, "Entity A"), new MyEntity(2, "Entity B"), new MyEntity(3, "Entity C"));
		
		listModel = new DualListModel<MyEntity>(source, new ArrayList<>());
	}
	
	public void send() {
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
