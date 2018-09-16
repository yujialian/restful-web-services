package com.yujia.rest.webservices.restfulwebservices.supermarket;

import java.util.Date;
import java.util.Map;

public class Transaction {
	
		private String customerName;
		private Map<String, Integer> products;
		private Date transactionDate;
		private int transactionId;
		public Transaction() {
			
		}

		public Transaction(String customerName, Map<String, Integer> products, Date transactionDate, int transactionId) {
			super();
			this.customerName = customerName;
			this.products = products;
			this.transactionDate = transactionDate;
			this.transactionId = transactionId;
		}

		public void setTransactionDate(Date transactionDate) {
			this.transactionDate = transactionDate;
		}

		public String getCustomerName() {
			return customerName;
		}

		public int getTransactionId() {
			return transactionId;
		}

		public void setTransactionId(int transactionId) {
			this.transactionId = transactionId;
		}

		public Map<String, Integer> getProducts() {
			return products;
		}

		public Date getTransactionDate() {
			return transactionDate;
		}
		
}
