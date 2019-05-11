import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;

import org.junit.Test;


/**
 * 
 * Ensure you have not modified anything with the default email@email.com account otherwise tests fail.
 *
 */
public class ProductsTest {

	@Test
	void testProductsCreation() {
		// Creating the user account. Database handeler prevents any duplications!
		DatabaseHandlerHSQL.getDatabase().createAccount("Username", "email@email.com", "Password");
		Products.registerProduct(new Login("email@email.com","Password"), "Chicken", 20.30, "Succulent chicken! Very good quality!");

	}

}
