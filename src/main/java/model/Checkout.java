package model;

import model.paymentclasses.PaymentStrategy;

//class that makes payments
public class Checkout {
	private PaymentStrategy paymentStrategy;
	
	public void makePayment(int amount) {
		paymentStrategy.makePayment(amount);
	}
	
	public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
		this.paymentStrategy=paymentStrategy;
	}
	
}
