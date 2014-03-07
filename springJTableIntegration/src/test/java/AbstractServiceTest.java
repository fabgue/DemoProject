
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sanath Kodikara
 */
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
@Transactional  
public abstract class AbstractServiceTest{
    
}
