/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.au.springjtableintegration.dataaccess.dao;

import com.au.springjtableintegration.business.model.Expense;
import com.au.springjtableintegration.business.model.ExpenseCategory;
import com.au.springjtableintegration.business.model.ExpenseSubCategory;
import java.io.File;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Sanath Kodikara
 */
public class EmployeeDaoIntegrationTest {
    private static Configuration config;
    private static SessionFactory factory;
    private static Session hibernateSession;
    private static Transaction transaction;
    private static ExpenseDaoImpl objectUnderTest;
     
     
    @BeforeClass
    public static void init() {
        config = new AnnotationConfiguration();
        config.configure(new File("src\\test\\resources\\hibernate.cfg.xml"));
        factory = config.buildSessionFactory();
        hibernateSession = factory.getCurrentSession();
        transaction = hibernateSession.beginTransaction();
        objectUnderTest = new ExpenseDaoImpl();
        objectUnderTest.setSessionFactory(factory);
    }
     
    @Test
    public void testInsertExpense(){
        Expense expense = new Expense(1,"2013-03-24",100.00,new ExpenseCategory(),new ExpenseSubCategory(),"test",true,"test attach");
        objectUnderTest.addExpense(expense);
        Expense returnedExp = objectUnderTest.getExpense(1);
        Assert.assertEquals("test", returnedExp.getDescription());
    }
    
//    @Test
//    public void testUpdateExpense(){
//        Expense expense2 = new Expense(3,"2013-03-24",100.00,new ExpenseCategory(),new ExpenseSubCategory(),"test",true,"test attach");
//        objectUnderTest.addExpense(expense2);
//        Expense returnedExp2 = objectUnderTest.getExpense(3);
//        returnedExp2.setDescription("updatedTest");
//        objectUnderTest.updateExpense(returnedExp2);
//        Assert.assertEquals("updatedTest", returnedExp2.getDescription());
//    }
}
