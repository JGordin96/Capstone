package com.techelevator;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ObjectTest {
	
	Candy someCandy = new Candy();
	
	@Before
	public void setup() {
		someCandy = new Candy(); 
	}
	
	@Test
	public void test_Get_And_Set_Product_Name() {
		someCandy.setName("My candy");
		assertEquals("My candy", someCandy.getName());
		
	}
	@Test
	public void test_Get_And_Set_Product_Price() {
		someCandy.setPrice(BigDecimal.valueOf(10.00));
		assertEquals(BigDecimal.valueOf(10.00), someCandy.getPrice());

	
	}

}
