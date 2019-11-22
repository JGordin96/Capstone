package com.techelevator;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CustomerMoneyTests {

CustomersMoney custMonTest = new CustomersMoney();
	@Test
	public void adding_10_And_10_And_5_And_5_Money_Should_Return_30() {
		custMonTest.addToCurrent(BigDecimal.valueOf(10.0));
		custMonTest.addToCurrent(BigDecimal.valueOf(10.0));
		custMonTest.addToCurrent(BigDecimal.valueOf(5.0));
		custMonTest.addToCurrent(BigDecimal.valueOf(5.0));
		assertEquals(BigDecimal.valueOf(30.0), custMonTest.getCurrentMoney());
	}
	
	@Test
	public void subtracting_Money_5_From_10_Should_Return_5() {
		custMonTest.addToCurrent(BigDecimal.valueOf(10.0));
		custMonTest.subtractFromCurrent((BigDecimal.valueOf(5.0)));
		assertEquals(BigDecimal.valueOf(5.0), custMonTest.getCurrentMoney());
	}
	
	@Test
	public void subtract_10_From_5_Should_Return_5() {
		custMonTest.addToCurrent(BigDecimal.valueOf(5.0));
		custMonTest.subtractFromCurrent((BigDecimal.valueOf(10.0)));
		assertEquals(BigDecimal.valueOf(5.0), custMonTest.getCurrentMoney());
	}
	@Test
	public void add_20_Add_5_Subtract_10_Should_Return_15() {
		custMonTest.addToCurrent(BigDecimal.valueOf(20.0));
		custMonTest.addToCurrent(BigDecimal.valueOf(5.0));
		custMonTest.subtractFromCurrent((BigDecimal.valueOf(10.0)));
		assertEquals(BigDecimal.valueOf(15.0), custMonTest.getCurrentMoney());
	}


}
