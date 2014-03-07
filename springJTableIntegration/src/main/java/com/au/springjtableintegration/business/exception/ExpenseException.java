/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.au.springjtableintegration.business.exception;

/**
 * 
 * @author Sanath Kodikara
 */
public class ExpenseException extends Exception {

	public ExpenseException() {
		super("Expense exception occured due to business logic failure");
	}

	public ExpenseException(String message) {
		super(message);
	}

	public ExpenseException(String message, Exception exception) {
		super(message, exception);
	}
}
