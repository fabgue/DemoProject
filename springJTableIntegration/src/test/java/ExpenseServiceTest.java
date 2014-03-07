
import com.au.springjtableintegration.business.exception.ExpenseException;
import com.au.springjtableintegration.business.service.ExpenseService;
import com.au.springjtableintegration.web.dto.datatable.bean.JsonJTableExpenseBean;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sanath Kodikara
 */
public class ExpenseServiceTest extends AbstractServiceTest{
    @Autowired 
    ExpenseService expenseService;  
  
    @Test  
    public void testFindAllAccounts() {  
        List<JsonJTableExpenseBean> allExpenses;
        try {
            allExpenses = expenseService.listExpenses(0, 1);
            Assert.assertEquals("Check DB is empty first", 1, allExpenses.size());  
            JsonJTableExpenseBean a = new JsonJTableExpenseBean();  
            a.setAmount("100"); 
            a.setCategoryId("1");
            a.setDate("2013-03-22");
            a.setDescription("xxx");
            a.setSubcategoryId("1");
            //expenseService.addExpense(a);
            //allExpenses = expenseService.listExpenses(0, 10); 
            //Assert.assertEquals("check Account has been created", 1, allExpenses.size());
        } catch (ExpenseException ex) {
            Logger.getLogger(ExpenseServiceTest.class.getName()).log(Level.SEVERE, null, ex.getMessage());
            Assert.assertEquals(ExpenseException.class,ex);
        } catch(Exception e){
            
        } 
          
    }  
}
