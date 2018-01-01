package com.inventorymanagement.main.web.form;

import java.util.ArrayList;

import com.inventorymanagement.main.eu.entity.Color;

public class ColorForm {
	
	private int id;
	
	private String colorName;
	
	private String description;
	
	private ArrayList<Color>colorList; 

	public ArrayList<Color> getColorList() {
		return colorList;
	}

	public void setColorList(ArrayList<Color> colorList) {
		this.colorList = colorList;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
