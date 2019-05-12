import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;


/**
 * 
 * Ensure you have not modified anything with the default email@email.com account otherwise tests fail.
 *
 */
public class ProductsTest {
	
	@Test
	public void testProductsCreation() {
		// Creating the user account. Database handeler prevents any duplications!
		DatabaseHandlerHSQL.getDatabase().createAccount("Username", "email@email.com", "Password");
		// Registering the product.
		Products.registerProduct(new Login("email@email.com","Password"), "xT3asd2asdq",20.30,"yTaSqusjam");
		Products dummyProduct = null;
		
		try {
			// Querying for the product id
			ResultSet query = DatabaseHandlerHSQL.getDatabase().Query("SELECT * FROM products WHERE name = 'xT3asd2asdq'");
			while(query.next()) {
				// Creating the dummy product
				dummyProduct = new Products(query.getInt("product_id"),"xT3asd2asdq",  "yTaSqusjam",20.30);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean doesProductExist = false;
		// Looping through every product that is not approved in the database.
		for(Products p : Products.getUnApprovedProducts()) {
			// Checking if the dummy object exists
			if(p.equals(dummyProduct)) {
				doesProductExist = true;
			}
		}
		// Asserts that the product exists
		assertEquals(true, doesProductExist);

	}
	
	@Test
	public void testProductObject() {
		Products dummyProduct = null;
		try {
			// Querying for the product id
			ResultSet query = DatabaseHandlerHSQL.getDatabase().Query("SELECT * FROM products WHERE name = 'xT3asd2asdq'");
			while(query.next()) {
				// Creating the dummy product
				dummyProduct = new Products(query.getInt("product_id"),"xT3asd2asdq",  "yTaSqusjam",20.30);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Checking if the getters and setters work properly.
		assertEquals(dummyProduct.getName(), "xT3asd2asdq");
		assertEquals(dummyProduct.getDescription(), "yTaSqusjam");
		assertEquals(dummyProduct.getPrice(), 20.30,0);

	}

}
