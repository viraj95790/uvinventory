package com.inventorymanagement.main.eu.entity;

public class Color {
	
	private int id;
	
	private int colorId;
	
	private String colorName;
	
	private String description;
	
	public Color(){
		
	}
	
	public Color(int colorID,String colorName){
		this.colorId = colorID;
		this.colorName = colorName;
	}

	

	public int getColorId() {
		return colorId;
	}



	public void setColorId(int colorId) {
		this.colorId = colorId;
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
