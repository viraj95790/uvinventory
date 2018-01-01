package com.inventorymanagement.main.eu.bi;

import java.util.ArrayList;

import com.inventorymanagement.main.eu.entity.Color;

public interface ColorDAO {

	boolean checkColorExist(String colorName);

	int saveColor(Color color);

	ArrayList<Color> getColorList();

	Color getColor(int selectedid);

	int updateColor(Color color);

	int deleteColor(int selectedid);
	

}
