/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.au.springjtableintegration.business.service;

import com.au.springjtableintegration.business.model.Expense;
import com.au.springjtableintegration.dataaccess.dao.ExpenseDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author Sanath Kodikara
 */
public class ExpenseServiceImplTest {
    private ExpenseServiceImpl objectUnderTest;
    
    private ExpenseDao mockedExpenseDao;
    
    @Before
    public void beforeTestSetup() {
        try {
            mockedExpenseDao = Mockito.mock(ExpenseDao.class);
            objectUnderTest = new ExpenseServiceImpl(mockedExpenseDao);
        } catch (Exception ex) {
            Assert.fail("Exception NOT expected\n" + ex.getMessage());
        }
    }
    
    @Test
    public void testGetExpense(){
        try {
            Expense expense = objectUnderTest.getExpense("1");
        } catch (Exception e) {
            Assert.fail("Exception NOT expected\n" + e.getMessage());
        }
    }
}
