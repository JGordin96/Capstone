package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE,
			MAIN_MENU_OPTION_EXIT };
	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY,PURCHASE_MENU_OPTION_SELECT_PRODUCT,PURCHASE_MENU_FINISH_TRANSACTION};
	
	private static final String FEED_DOLLAR	= "Insert Dollar Bill";
	private static final String FEED_FIVE_DOLLAR = "Insert 5 Dollar Bill";
	private static final String FEED_TEN_DOLLAR	= "Insert 10 Dollar Bill";
	private static final String FEED_TWENTY_DOLLAR	= "Insert 20 Dollar Bill";
	private static final String FEED_HUNNED_DOLLAR	= "Insert 100 Dollar Bill (For The Rich And Swaggy)";
	private static final String[] FEED_MENU_OPTIONS = {FEED_DOLLAR,FEED_FIVE_DOLLAR,FEED_TEN_DOLLAR,FEED_TWENTY_DOLLAR,FEED_HUNNED_DOLLAR};
	private Menu menu;


	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}
	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a"); 
	Date date = new Date(); 
	public void run() throws IOException {
		NumberFormat cf = NumberFormat.getCurrencyInstance();
		Map<String, Snacks> menuOptions = new LinkedHashMap<String, Snacks>();
		Inventory inventory = new Inventory();
		List<String> returnSounds = new ArrayList<String>();
		List<String> purchaseReport = new ArrayList<String>(); 
		
		for (String eachSnack : Inventory.inventoryCreation().keySet()) {
			menuOptions.put(eachSnack, Inventory.inventoryCreation().get(eachSnack));
		}
		
		CustomersMoney customerBalance = new CustomersMoney();
		ReturnChange returnChange = new ReturnChange();
		
		while (true) {
			
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				run();
			}
			

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {

				for (String allSnacks : menuOptions.keySet()) {
					System.out.print("*" + menuOptions.get(allSnacks).getProductCode() + "*");
					System.out.print(" " + menuOptions.get(allSnacks).getName() + " ");
					System.out.print("- " + cf.format(menuOptions.get(allSnacks).getPrice()));
					System.out.println(" | " + menuOptions.get(allSnacks).getQuantity() + " available |");
				}
				
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				while (true) { 
					System.out.println("\nCurrent money provided: " + cf.format(customerBalance.getCurrentMoney()));
					
					String choice2 = (String)menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
					choice2 = choice2.trim();
					if(choice2.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
						String choice3 = (String)menu.getChoiceFromOptions(FEED_MENU_OPTIONS);
							BigDecimal amountToAdd = new BigDecimal(0);
							
							if(choice3.equals(FEED_DOLLAR)) {
								customerBalance.addToCurrent(BigDecimal.valueOf(1.00));
								amountToAdd = BigDecimal.valueOf(1.00);
							}
							if(choice3.equals(FEED_FIVE_DOLLAR)) {
								customerBalance.addToCurrent(BigDecimal.valueOf(5.00));
								amountToAdd = BigDecimal.valueOf(5.00);
							}
							if(choice3.equals(FEED_TEN_DOLLAR)) {
								customerBalance.addToCurrent(BigDecimal.valueOf(10.00));
								amountToAdd = BigDecimal.valueOf(10.00);
							}
							if(choice3.equals(FEED_TWENTY_DOLLAR)) {
								customerBalance.addToCurrent(BigDecimal.valueOf(20.00));
								amountToAdd = BigDecimal.valueOf(20.00);
							}
							if(choice3.equals(FEED_HUNNED_DOLLAR)) {
								customerBalance.addToCurrent(BigDecimal.valueOf(100.00));
								amountToAdd = BigDecimal.valueOf(100.00);
							}
							
							
							purchaseReport.add(dateFormat.format(date) + "  |  Feed Money:  " + amountToAdd + "  -  Current Money Inserted:  " + customerBalance.getCurrentMoney());	
						

					}
					else if(choice2.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)){
						Scanner pickItem = new Scanner (System.in);
						for (String allSnacks : menuOptions.keySet()) {
							System.out.print("*" + menuOptions.get(allSnacks).getProductCode() + "*");
							System.out.print(" " + menuOptions.get(allSnacks).getName() + " ");
							System.out.print("- " + cf.format(menuOptions.get(allSnacks).getPrice()));
							System.out.println(" | " + menuOptions.get(allSnacks).getQuantity() + " available |");
						}
						System.out.println("\nPlease enter the location of the product.");
						String userChoice = pickItem.nextLine();
						userChoice = userChoice.trim();
						userChoice = userChoice.toUpperCase();
						BigDecimal previousBalance = new BigDecimal(0);
						
						if(menuOptions.get(userChoice) == null) {
							System.out.println("Not a valid location, please try again.");
						}
						
						else if(menuOptions.get(userChoice).getPrice().compareTo(customerBalance.getCurrentMoney()) == 1) {
							System.out.println("Insufficient funds, please feed money to purchase.");
						}

						else {
							System.out.println(menuOptions.get(userChoice).purchaseWithMessage(menuOptions.get(userChoice).getName()));
							if (menuOptions.get(userChoice).getQuantity() >= 0) {
								customerBalance.subtractFromCurrent(menuOptions.get(userChoice).getPrice());
								returnSounds.add(menuOptions.get(userChoice).getSoundMsg());
								previousBalance = (customerBalance.getCurrentMoney().add(menuOptions.get(userChoice).getPrice())); 
								
								purchaseReport.add(dateFormat.format(date) + "  |  Item Name:  " + menuOptions.get(userChoice).getName() + "  -  Item Location: " + menuOptions.get(userChoice).getProductCode());
								purchaseReport.add("Previous Balance: $" + previousBalance + "  -  Current Balance: $" + customerBalance.getCurrentMoney());
							}
						}
						
						
					}
					else if (choice2.equals(PURCHASE_MENU_FINISH_TRANSACTION)) {
						purchaseReport.add(dateFormat.format(date) +"  |  Amount Of Change Returned: $" + customerBalance.getCurrentMoney());
						ReturnChange.returnChange(customerBalance.getCurrentMoney());
						customerBalance.subtractFromCurrent(customerBalance.getCurrentMoney());
						purchaseReport.add(dateFormat.format(date) +"  |  Balance After Change Returned: $" + customerBalance.getCurrentMoney());
						for (String sound : returnSounds) {
							System.out.println("\n" +sound);
						}
						
						File reportFile = new File ("SalesReport.txt");
						try (FileWriter writer = new FileWriter(reportFile.getAbsoluteFile(), true)) { 
							for (String eachReport : purchaseReport) {
								writer.write(eachReport + "\n");
								writer.flush();
							} 
							writer.flush();
						}
						break;
						
					}
					
					
				 
			}
		} 
	
		} 
	} 
	public static void main(String[] args) throws IOException {

		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();

	}
}
