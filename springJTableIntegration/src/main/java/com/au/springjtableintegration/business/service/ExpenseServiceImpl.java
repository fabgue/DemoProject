package com.au.springjtableintegration.business.service;

import com.au.springjtableintegration.business.exception.ExpenseException;
import com.au.springjtableintegration.business.model.Expense;
import com.au.springjtableintegration.business.model.ExpenseCategory;
import com.au.springjtableintegration.business.model.ExpenseSubCategory;
import com.au.springjtableintegration.dataaccess.dao.ExpenseDao;
import com.au.springjtableintegration.web.dto.datatable.bean.JsonJTableExpenseBean;
import com.au.springjtableintegration.web.dto.datatable.bean.JsonJTableExpenseOptionsBean;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	private static final Logger LOG = LoggerFactory
			.getLogger(ExpenseServiceImpl.class);

	@Autowired
	private ExpenseDao expenseDao;

	public ExpenseServiceImpl() {
	}

	public ExpenseServiceImpl(ExpenseDao expenseDao) {
		this.expenseDao = expenseDao;
	}

	@Transactional(readOnly = true)
	public Expense getExpense(String id) throws ExpenseException {
		try {
			return expenseDao.getExpense(new Integer(id));
		} catch (Exception e) {
			LOG.error("Exception thrown while while getting expense with id - "
					+ id + " - " + e.getMessage());
			throw new ExpenseException(
					"Exception while getting expense with id - " + id + " - "
							+ e.getMessage());
		}
	}

	@Transactional
	public void addExpense(JsonJTableExpenseBean expenseBean)
			throws ExpenseException {
		try {
			LOG.info("adding expense ...........");
			Expense expense = new Expense();
			expense.setAmount(new Double(expenseBean.getAmount()));
			ExpenseCategory expenseCategory = expenseDao
					.getExpenseCategory(new Integer(expenseBean.getCategoryId()));
			ExpenseSubCategory expenseSubCategory = expenseDao
					.getExpenseSubCategory(new Integer(expenseBean
							.getSubcategoryId()));
			expense.setExpenseCategory(expenseCategory);
			expense.setExpenseSubCategory(expenseSubCategory);
			expense.setDate(expenseBean.getDate());
			expense.setDescription(expenseBean.getDescription());
			expense.setTaxable(true);
			expenseDao.addExpense(expense);
		} catch (Exception e) {
			LOG.error("Exception thrown while adding " + e.getMessage());
			throw new ExpenseException("Exception while adding record - "
					+ e.getMessage());
		}
	}

	@Transactional
	public void updateExpense(JsonJTableExpenseBean expenseBean)
			throws ExpenseException {
		try {
			LOG.info("updating expense with id -" + expenseBean.getExpenseId());
			Expense expense = expenseDao.getExpense(new Integer(expenseBean
					.getExpenseId()));
			expense.setAmount(new Double(expenseBean.getAmount()));
			ExpenseCategory expenseCategory = expenseDao
					.getExpenseCategory(new Integer(expenseBean.getCategoryId()));
			ExpenseSubCategory expenseSubCategory = expenseDao
					.getExpenseSubCategory(new Integer(expenseBean
							.getSubcategoryId()));
			LOG.info("expense category is - " + expenseBean.getCategoryId());
			LOG.info("expense sub-category is - "
					+ expenseBean.getSubcategoryId());
			expense.setExpenseCategory(expenseCategory);
			expense.setExpenseSubCategory(expenseSubCategory);
			expense.setDate(expenseBean.getDate());
			expense.setDescription(expenseBean.getDescription());
			expense.setTaxable(true);
			expenseDao.updateExpense(expense);
		} catch (Exception e) {
			LOG.error("Exception thrown while updating record" + e.getMessage());
			throw new ExpenseException("Exception while updating record - "
					+ e.getMessage());
		}
	}

	@Transactional(readOnly = true)
	public int getExpenseCount() throws ExpenseException {
		try {
			return expenseDao.getExpenseCount();
		} catch (Exception e) {
			LOG.error("Exception thrown while getting expense count "
					+ e.getMessage());
			throw new ExpenseException(
					"Exception thrown while getting expense count - "
							+ e.getMessage());
		}
	}

	@Transactional(readOnly = true)
	public List<JsonJTableExpenseBean> listExpenses(int startIndex, int pageSize)
			throws ExpenseException {
		List<JsonJTableExpenseBean> formattedExpenseList = new ArrayList<JsonJTableExpenseBean>();
		try {
			List<Expense> expenseList = expenseDao.listExpenses(startIndex,
					pageSize);
			for (Expense expense : expenseList) {
				JsonJTableExpenseBean expenseBean = new JsonJTableExpenseBean();
				expenseBean.setExpenseId(expense.getId().toString());
				expenseBean.setAmount(String.valueOf(expense.getAmount()));
				expenseBean.setCategoryId(expense.getExpenseCategory().getId()
						.toString());
				if (expense.getExpenseSubCategory() != null) {
					expenseBean.setSubcategoryId(expense
							.getExpenseSubCategory().getId().toString());
				} else {
					expenseBean.setSubcategoryId(expenseDao
							.getExpenseSubCategory(1).getId().toString());
				}
				expenseBean.setDate(expense.getDate());
				expenseBean.setDescription(expense.getDescription());
				expenseBean.setTaxable("true");
				formattedExpenseList.add(expenseBean);
			}
		} catch (Exception e) {
			LOG.error("Exception thrown while listing expenses from - "
					+ startIndex + " to " + pageSize + " - " + e.getMessage());
			throw new ExpenseException(
					"Exception while listing expenses from - " + startIndex
							+ " to " + pageSize + " - " + e.getMessage());
		}
		return formattedExpenseList;
	}

	@Transactional
	public void removeExpense(Integer id) throws ExpenseException {
		try {
			expenseDao.removeExpense(id);
		} catch (Exception e) {
			LOG.error("Exception thrown while removing expense"
					+ e.getMessage());
			throw new ExpenseException("Exception while removing expense "
					+ e.getMessage());
		}
	}

	@Transactional(readOnly = true)
	public List<JsonJTableExpenseOptionsBean> listExpenseCategories()
			throws ExpenseException {
		List<JsonJTableExpenseOptionsBean> lblList = new ArrayList<JsonJTableExpenseOptionsBean>();
		try {
			List<ExpenseCategory> expCategories = expenseDao
					.listExpenseCategories();
			for (ExpenseCategory expenseCategory : expCategories) {
				JsonJTableExpenseOptionsBean expenseOptionsBean = new JsonJTableExpenseOptionsBean(
						expenseCategory.getExpenseCategoryName(),
						expenseCategory.getId().toString());
				lblList.add(expenseOptionsBean);
			}
		} catch (Exception e) {
			LOG.error("Exception thrown while listing expense categories"
					+ e.getMessage());
			throw new ExpenseException(
					"Exception while listing expense categories "
							+ e.getMessage());
		}
		return lblList;
	}

	@Transactional(readOnly = true)
	public List<JsonJTableExpenseOptionsBean> listExpenseSubCategories()
			throws ExpenseException {
		List<JsonJTableExpenseOptionsBean> lblList = new ArrayList<JsonJTableExpenseOptionsBean>();
		try {
			List<ExpenseSubCategory> expSubCategories = expenseDao
					.listExpenseSubCategories();
			for (ExpenseSubCategory expenseSubCategory : expSubCategories) {
				JsonJTableExpenseOptionsBean expenseOptionsBean = new JsonJTableExpenseOptionsBean(
						expenseSubCategory.getExpenseSubcategoryName(),
						expenseSubCategory.getId().toString());
				lblList.add(expenseOptionsBean);
			}
		} catch (Exception e) {
			LOG.error("Exception thrown while listing all expense sub categories"
					+ e.getMessage());
			throw new ExpenseException(
					"Exception while listing all expense sub categories "
							+ e.getMessage());
		}
		return lblList;
	}

	@Transactional(readOnly = true)
	public List<JsonJTableExpenseOptionsBean> getSubCategoriesForCategory(
			String categoryId) throws ExpenseException {
		List<JsonJTableExpenseOptionsBean> lblList = new ArrayList<JsonJTableExpenseOptionsBean>();
		try {
			List<ExpenseSubCategory> expSubCategories = expenseDao
					.getSubCategoriesForCategory(categoryId);
			for (ExpenseSubCategory expenseSubCategory : expSubCategories) {
				JsonJTableExpenseOptionsBean expenseOptionsBean = new JsonJTableExpenseOptionsBean(
						expenseSubCategory.getExpenseSubcategoryName(),
						expenseSubCategory.getId().toString());
				lblList.add(expenseOptionsBean);
			}
		} catch (Exception e) {
			LOG.error("Exception thrown while listing sub categories for category"
					+ e.getMessage());
			throw new ExpenseException(
					"Exception while listing sub categories for category "
							+ e.getMessage());
		}
		return lblList;
	}
}
