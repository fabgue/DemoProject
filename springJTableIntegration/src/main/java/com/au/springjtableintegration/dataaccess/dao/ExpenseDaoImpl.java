package com.au.springjtableintegration.dataaccess.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.au.springjtableintegration.business.model.Expense;
import com.au.springjtableintegration.business.model.ExpenseCategory;
import com.au.springjtableintegration.business.model.ExpenseSubCategory;

@Repository
public class ExpenseDaoImpl implements ExpenseDao {

	@Autowired
	protected SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addExpense(Expense expense) {
		sessionFactory.getCurrentSession().save(expense);
	}

	public void updateExpense(Expense expense) {
		sessionFactory.getCurrentSession().merge(expense);
	}

	public List<Expense> listExpenses(int startIndex, int pageSize) {
		return sessionFactory.getCurrentSession()
				.createQuery("from Expense order by date DESC")
				.setFirstResult(startIndex).setMaxResults(pageSize).list();
	}

	public int getExpenseCount() {
		int count = 0;
		try {
			count = ((Long) sessionFactory.getCurrentSession()
					.createQuery("select count(*) from Expense").uniqueResult())
					.intValue();
		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
		}
		return count;
	}

	public Expense getExpense(Integer id) {
		Expense expense = (Expense) sessionFactory.getCurrentSession().load(
				Expense.class, id);
		if (expense != null) {
			return expense;
		} else {
			return null;
		}
	}

	public void removeExpense(Integer id) {
		Expense expense = (Expense) sessionFactory.getCurrentSession().load(
				Expense.class, id);
		if (null != expense) {
			sessionFactory.getCurrentSession().delete(expense);
		}
	}

	public List<ExpenseCategory> listExpenseCategories() {
		return sessionFactory.getCurrentSession()
				.createQuery("from ExpenseCategory").list();
	}

	public ExpenseCategory getExpenseCategory(Integer category) {
		ExpenseCategory expenseCategory = (ExpenseCategory) sessionFactory
				.getCurrentSession().load(ExpenseCategory.class, category);
		return expenseCategory;
	}

	public ExpenseSubCategory getExpenseSubCategory(Integer subcategory) {
		ExpenseSubCategory expensesubCategory = (ExpenseSubCategory) sessionFactory
				.getCurrentSession()
				.load(ExpenseSubCategory.class, subcategory);
		return expensesubCategory;
	}

	public List<ExpenseSubCategory> listExpenseSubCategories() {
		return sessionFactory.getCurrentSession()
				.createQuery("from ExpenseSubCategory").list();
	}

	public List<ExpenseSubCategory> getSubCategoriesForCategory(
			String categoryId) {
		return sessionFactory
				.getCurrentSession()
				.createQuery(
						"from ExpenseSubCategory esc where esc.expenseCategory.id=:categoryId")
				.setParameter("categoryId", new Integer(categoryId)).list();
	}
}
