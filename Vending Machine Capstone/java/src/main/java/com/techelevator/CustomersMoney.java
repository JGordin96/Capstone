package com.techelevator;

import java.math.BigDecimal;

public class CustomersMoney {

	private BigDecimal currentMoney = BigDecimal.valueOf(0.00);

	public BigDecimal getCurrentMoney() {
		return currentMoney;
	}

	public void addToCurrent(BigDecimal addMoney) {
		this.currentMoney = this.currentMoney.add(addMoney);
	}

	public void subtractFromCurrent(BigDecimal subtractAmount) {
		if (subtractAmount.compareTo(this.currentMoney) <= 0) {
			this.currentMoney = this.currentMoney.subtract(subtractAmount);
		}
	}
}
