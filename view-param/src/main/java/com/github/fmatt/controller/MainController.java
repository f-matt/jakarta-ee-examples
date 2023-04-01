package com.github.fmatt.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class MainController {

	private Integer num;

	public void onLoad() {
		if (num == null)
			num = 0;
		else
			num *= 2;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}
