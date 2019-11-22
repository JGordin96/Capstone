package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Inventory {

	public static Map<String, Snacks> inventoryCreation() throws FileNotFoundException {
	
	
	File vendingFile = new File("vendingmachine.csv");
	
	Scanner vendingScanner = new Scanner(vendingFile);
	
	Map<String, Snacks> snackInventory = new LinkedHashMap<String, Snacks>();
	
	while (vendingScanner.hasNextLine()) {
		
		String line = vendingScanner.nextLine();
		
		String[] snackArray = line.split("\\|");
		
		double tempPrice = Double.parseDouble(snackArray[2]);
		
		BigDecimal productPriceBigDec = BigDecimal.valueOf(tempPrice);
		
		NumberFormat cf = NumberFormat.getCurrencyInstance();
		
		
		
		switch (snackArray[0].charAt(0)) {
			
		case 'A':
			Chip chips = new Chip();
			chips.setName(snackArray[1]);
			chips.setProductCode(snackArray[0]);
			chips.setPrice(productPriceBigDec);
			snackInventory.put(snackArray[0], chips);	
			break;
		
		
		case 'B':
			Candy candy = new Candy();
			candy.setName(snackArray[1]);
			candy.setProductCode(snackArray[0]);
			candy.setPrice(productPriceBigDec);
			snackInventory.put(snackArray[0], candy);	
			break;
		
		case 'C':
			Drink drinks = new Drink();
			drinks.setName(snackArray[1]);
			drinks.setProductCode(snackArray[0]);
			drinks.setPrice(productPriceBigDec);
			snackInventory.put(snackArray[0], drinks);			
			break;
		
		case 'D':
			Gum gum = new Gum();
			gum.setName(snackArray[1]);
			gum.setProductCode(snackArray[0]);
			gum.setPrice(productPriceBigDec);
			snackInventory.put(snackArray[0], gum);	
			break;
		}
		
	}
return snackInventory;
}
}