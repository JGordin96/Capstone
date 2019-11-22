package com.techelevator;

import java.math.BigDecimal;

public interface Snacks {
	
	public String getName();
	
	public void setName(String name);
	
	public String getProductCode();
	
	public void setProductCode(String code);
	
	public BigDecimal getPrice();
	
	public void setPrice(BigDecimal price);
	
	public Integer getQuantity();
	
	public String getSoundMsg();	
	
	public void purchase();
	
	public String purchaseWithMessage(String getName);

	
	
}

