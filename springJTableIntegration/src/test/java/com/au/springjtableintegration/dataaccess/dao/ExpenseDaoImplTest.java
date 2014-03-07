/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.au.springjtableintegration.dataaccess.dao;

import com.au.springjtableintegration.business.model.Expense;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author Sanath Kodikara
 */
public class ExpenseDaoImplTest {
    
    private ExpenseDaoImpl objectUnderTest;
    
    private SessionFactory mockedSessionFactory;
    
    private Session mockedSession; 
    
    private Transaction mockedTransaction;
    
    @Before
    public void beforeTestSetup() {
        try {
            objectUnderTest = new ExpenseDaoImpl();
            mockedSessionFactory = Mockito.mock(SessionFactory.class);
            mockedSession = Mockito.mock(Session.class);
            mockedTransaction = Mockito.mock(Transaction.class);
            //objectUnderTest.sessionFactory = mockedSessionFactory;
            Mockito.when(mockedSessionFactory.openSession()).thenReturn(mockedSession);
            Mockito.when(mockedSession.beginTransaction()).thenReturn(mockedTransaction);
            objectUnderTest.sessionFactory = mockedSessionFactory;
        } catch (Exception ex) {
            Assert.fail("Exception NOT expected\n" + ex.getMessage());
        }
    }
    
    @Test
    public void testGetExpenseCount(){         
        try {
            int actualCount = objectUnderTest.getExpenseCount();
            Assert.assertEquals(0, actualCount);
        } catch (Exception e) {
            Assert.fail("Exception NOT expected\n" + e.getMessage());
        }
    }
}
