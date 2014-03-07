package com.au.springjtableintegration.business.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "expenseCategory")
public class ExpenseCategory implements Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Integer id;
	@Column(name = "expenseCategoryName")
	private String expenseCategoryName;
	@OneToMany(mappedBy = "expenseCategory")
	private List<ExpenseSubCategory> expenseSubCategories;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getExpenseCategoryName() {
		return expenseCategoryName;
	}

	public void setExpenseCategoryName(String expenseCategoryName) {
		this.expenseCategoryName = expenseCategoryName;
	}

	public List<ExpenseSubCategory> getExpenseSubCategories() {
		return expenseSubCategories;
	}

	public void setExpenseSubCategories(
			List<ExpenseSubCategory> expenseSubCategories) {
		this.expenseSubCategories = expenseSubCategories;
	}
}
