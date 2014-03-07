package com.au.springjtableintegration.dataaccess.dao;

import com.au.springjtableintegration.business.model.Expense;
import com.au.springjtableintegration.business.model.ExpenseCategory;
import com.au.springjtableintegration.business.model.ExpenseSubCategory;
import java.util.List;

public interface ExpenseDao {
	/**
	 * Add an expense
	 * 
	 * @param expense
	 */
	public void addExpense(Expense expense);

	/**
	 * List all the expenses
	 * 
	 * @param startIndex
	 * @param pageSize
	 * @return List of Expenses
	 */
	public List<Expense> listExpenses(int startIndex, int pageSize);

	/**
	 * Get the expense count
	 * 
	 * @return int
	 */
	public int getExpenseCount();

	/**
	 * Remove an expense with given id
	 * 
	 * @param id
	 */
	public void removeExpense(Integer id);

	/**
	 * update expense
	 * 
	 * @param expense
	 */
	public void updateExpense(Expense expense);

	/**
	 * List all the expense categories
	 * 
	 * @return
	 */
	public List<ExpenseCategory> listExpenseCategories();

	/**
	 * Get an expense category based on its id
	 * 
	 * @param category
	 * @return
	 */
	public ExpenseCategory getExpenseCategory(Integer category);

	/**
	 * Get expense based on its id
	 * 
	 * @param id
	 * @return
	 */
	public Expense getExpense(Integer id);

	/**
	 * Get an expense sub category based on its id
	 * 
	 * @param subcategory
	 * @return
	 */
	public ExpenseSubCategory getExpenseSubCategory(Integer subcategory);

	/**
	 * List all the expense sub categories
	 * 
	 * @return
	 */
	public List<ExpenseSubCategory> listExpenseSubCategories();

	/**
	 * Get a list of expense sub categories for a given category id
	 * 
	 * @param categoryId
	 * @return
	 */
	public List<ExpenseSubCategory> getSubCategoriesForCategory(
			String categoryId);
}
