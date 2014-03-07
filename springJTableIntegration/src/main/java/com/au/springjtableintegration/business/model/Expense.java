package com.au.springjtableintegration.business.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "expense")
public class Expense implements Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Integer id;
	@Column(name = "date")
	private String date;
	@Column(name = "amount")
	private double amount;
	@OneToOne
	private ExpenseCategory expenseCategory;
	@OneToOne
	private ExpenseSubCategory expenseSubCategory;
	@Column(name = "description")
	private String description;
	@Column(name = "taxable")
	private boolean taxable;
	@Column(name = "attachment")
	private String attachment;

	public Expense() {

	}

	public Expense(Integer id, String date, double amount,
			ExpenseCategory expenseCategory,
			ExpenseSubCategory expenseSubCategory, String description,
			boolean taxable, String attachment) {
		this.id = id;
		this.date = date;
		this.amount = amount;
		this.expenseCategory = expenseCategory;
		this.expenseSubCategory = expenseSubCategory;
		this.description = description;
		this.taxable = taxable;
		this.attachment = attachment;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public ExpenseCategory getExpenseCategory() {
		return expenseCategory;
	}

	public void setExpenseCategory(ExpenseCategory expenseCategory) {
		this.expenseCategory = expenseCategory;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isTaxable() {
		return taxable;
	}

	public void setTaxable(boolean taxable) {
		this.taxable = taxable;
	}

	public ExpenseSubCategory getExpenseSubCategory() {
		return expenseSubCategory;
	}

	public void setExpenseSubCategory(ExpenseSubCategory expenseSubCategory) {
		this.expenseSubCategory = expenseSubCategory;
	}
}
