package com.au.springjtableintegration.business.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "expenseSubcategory")
public class ExpenseSubCategory implements Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Integer id;
	@Column(name = "expenseSubcategoryName")
	private String expenseSubcategoryName;
	@ManyToOne
	private ExpenseCategory expenseCategory;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getExpenseSubcategoryName() {
		return expenseSubcategoryName;
	}

	public void setExpenseSubcategoryName(String expenseSubcategoryName) {
		this.expenseSubcategoryName = expenseSubcategoryName;
	}

	public ExpenseCategory getExpenseCategory() {
		return expenseCategory;
	}

	public void setExpenseCategory(ExpenseCategory expenseCategory) {
		this.expenseCategory = expenseCategory;
	}
}
