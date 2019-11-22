package com.techelevator;

import java.math.BigDecimal;

public class Drink implements Snacks {

	private String productName;
	private String productCode;
	private BigDecimal productPrice;
	private int quantity = 5;
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return productName;
	}
	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.productName = name;
	}
	@Override
	public String getProductCode() {
		// TODO Auto-generated method stub
		return productCode;
	}
	@Override
	public void setProductCode(String code) {
		// TODO Auto-generated method stub
		this.productCode = code;
	}
	@Override
	public BigDecimal getPrice() {
		// TODO Auto-generated method stub
		return productPrice;
	}
	@Override
	public void setPrice(BigDecimal price) {
		// TODO Auto-generated method stub
		this.productPrice = price;
	}
	@Override
	public Integer getQuantity() {
		// TODO Auto-generated method stub
		return quantity;
	}
	@Override
	public String getSoundMsg() {
		// TODO Auto-generated method stub
		return "Glug Glug, Yum!";
	}
	@Override
	public void purchase() {
		// TODO Auto-generated method stub
		if (this.quantity > 0) {
			this.quantity -=1;
		}
	}
	@Override
	public String purchaseWithMessage(String getName) {

String message = "";
if (this.quantity == 0) {
	message += "This item is sold out.";
	
} else {
	message += "You selected " + getName;
	this.quantity -=1;
}
		return message;
	}
	

}
