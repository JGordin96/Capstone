package com.techelevator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ReturnChange {
	public static String returnChange(BigDecimal amountToReturn) {
	
    Double changingDouble = amountToReturn.doubleValue();
    int nickels = 0;
    int dimes= 0;
    int quarters= 0;
    int pennies = 0;

    while(changingDouble>=0.25) {
        quarters++;
        changingDouble -= 0.25;
    }
    while(changingDouble>=0.10) {
        dimes++;
        changingDouble -= 0.10;
    }
    while(changingDouble>=0.05) {
        nickels++;
        changingDouble -= 0.05;
    }
    while(changingDouble>=0.01) {
        pennies++;
        changingDouble -= 0.01;
    }

    System.out.println("Quarters: " + quarters + "\nDimes: " + dimes + "\nNickles: " + nickels + "\nPennies: " + pennies);
	return "Quarters: " + quarters + "\nDimes: " + dimes + "\nNickles: " + nickels + "\nPennies: " + pennies + "\n"; 
			


}
}
