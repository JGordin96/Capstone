package com.techelevator;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class MakeChangeTest {
	ReturnChange amountToReturn = new ReturnChange();
	
	@Test
	public void amount_To_Return_8Dollars_50_Cents() {
		String expectedResult = "Quarters: 34\n" + 
				"Dimes: 0\n" + 
				"Nickles: 0\n" + "Pennies: 0\n" ;
		String actualResult = ReturnChange.returnChange(BigDecimal.valueOf(8.50));
		
		assertEquals(expectedResult, actualResult);
	}
	@Test
	public void amount_To_Return_3Dollars_55_Cents() { //only returns 3.54because of rounding
		String expectedResult = "Quarters: 14\n" + 
				"Dimes: 0\n" + 
				"Nickles: 0\n" + "Pennies: 4\n" ;
		String actualResult = ReturnChange.returnChange(BigDecimal.valueOf(3.54));
		
		assertEquals(expectedResult, actualResult);
	}
	@Test
	public void amount_To_Return_4Dollars_15_Cents() {
		String expectedResult = "Quarters: 16\n" + 
				"Dimes: 1\n" + 
				"Nickles: 1\n" + "Pennies: 0\n" ;
		String actualResult = ReturnChange.returnChange(BigDecimal.valueOf(4.15));
		
		assertEquals(expectedResult, actualResult);
	}
	@Test
	public void amount_To_Return_Zero() { //only returns 19.54because of rounding
		String expectedResult = "Quarters: 0\n" + 
				"Dimes: 0\n" + 
				"Nickles: 0\n" + "Pennies: 0\n" ;
		String actualResult = ReturnChange.returnChange(BigDecimal.valueOf(0.0));
		
		assertEquals(expectedResult, actualResult);
	}
}
