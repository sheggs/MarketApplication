import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;
/**
 * 
 * Ensure you have not modified anything with the default email@email.com account otherwise tests fail.
 *
 */
class YourAccountTest {
	@Test
	void CreationDummyProducts() {
		Login login = new Login("email@email.com","Password");
		User user = login.getUser();
		Products.registerProduct(login,"0x129xsid90", 22,"RandomProduct Description");
	}
	@Test
	void testProductInAccount() {
		Login login = new Login("email@email.com","Password");
		User user = login.getUser();
		YourAccount youraccount = new YourAccount(login);
		Products dummyProduct = youraccount.getProductsAwaitingApproval().get(0);
		assertEquals(dummyProduct.getName(),"Another Prod");
		assertEquals(dummyProduct.getPrice(),22);
		assertEquals(dummyProduct.getDescription(),"Some product for testing");
	}
	@Test
	void purchaseDummyProduct() {
		int productid = -1;
		ResultSet query = DatabaseHandlerHSQL.getDatabase().Query("SELECT * FROM products WHERE name = '0x129xsid90'");
		try {
			query.next();
			productid = query.getInt("product_id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Products product = new Products(productid,"0x129xsid90","RandomProduct Description",22);
		
	}

}
