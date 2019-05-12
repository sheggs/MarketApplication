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
			ResultSet query = DatabaseHandlerHSQL.getDatabase().Query("SELECT * FROM products WHERE name = 'xT3asd2asdq'");
			while(query.next()) {
				System.out.println(query.getInt("product_id"));
				System.out.println(query.getString("name"));
				System.out.println(query.getString("description"));
				System.out.println(query.getDouble("price"));


				dummyProduct = new Products(query.getInt("product_id"),"xT3asd2asdq",  "yTaSqusjam",20.30);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean doesProductExist = false;
		for(Products p : Products.getUnApprovedProducts()) {
			System.out.println(p.getName());
			if(p.equals(dummyProduct)) {
				System.out.println("ASDSA");
				doesProductExist = true;
			}
		}
		assertEquals(true, doesProductExist);

	}

}
