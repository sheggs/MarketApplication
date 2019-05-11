
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author Helen Treharne
 */
@RunWith(Suite.class)
@SuiteClasses({ AdminTest.class,LoginTest.class,ProductsTest.class,UserTest.class,YourAccountTest.class})
public class AllTests {
}
