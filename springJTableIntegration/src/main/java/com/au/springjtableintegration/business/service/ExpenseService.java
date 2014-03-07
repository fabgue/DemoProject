package com.au.springjtableintegration.business.service;

import com.au.springjtableintegration.business.exception.ExpenseException;
import com.au.springjtableintegration.business.model.Expense;
import com.au.springjtableintegration.web.dto.datatable.bean.JsonJTableExpenseBean;
import com.au.springjtableintegration.web.dto.datatable.bean.JsonJTableExpenseOptionsBean;
import java.util.List;

public interface ExpenseService {
	/**
	 * Get Expense based on its primary key
	 * 
	 * @param id
	 * @return Expense object
	 */
	public Expense getExpense(String id) throws ExpenseException;

	/**
	 * Add an expense
	 * 
	 * @param expenseBean
	 * @throws Exception
	 */
	public void addExpense(JsonJTableExpenseBean expenseBean)
			throws ExpenseException;

	/**
	 * List expenses based on a range
	 * 
	 * @param startIndex
	 * @param pageSize
	 * @return List of <JsonJTableExpenseBean>
	 */
	public List<JsonJTableExpenseBean> listExpenses(int startIndex, int pageSize)
			throws ExpenseException;

	/**
	 * Get the count of expenses
	 * 
	 * @return integer
	 */
	public int getExpenseCount() throws ExpenseException;

	/**
	 * remove a particular expense
	 * 
	 * @param id
	 */
	public void removeExpense(Integer id) throws ExpenseException;

	/**
	 * Update en expense record
	 * 
	 * @param expenseBean
	 * @throws Exception
	 */
	public void updateExpense(JsonJTableExpenseBean expenseBean)
			throws ExpenseException;

	/**
	 * List expense categories
	 * 
	 * @return
	 */
	public List<JsonJTableExpenseOptionsBean> listExpenseCategories()
			throws ExpenseException;

	/**
	 * List of all expense sub categories
	 * 
	 * @return List of <JsonJTableExpenseOptionsBean>
	 */
	public List<JsonJTableExpenseOptionsBean> listExpenseSubCategories()
			throws ExpenseException;

	/**
	 * List of expense sub categories based on category
	 * 
	 * @param categoryId
	 * @return List of <JsonJTableExpenseOptionsBean>
	 */
	public List<JsonJTableExpenseOptionsBean> getSubCategoriesForCategory(
			String categoryId) throws ExpenseException;
}
