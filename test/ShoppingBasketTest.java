import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class ShoppingBasketTest {
	ShoppingBasket newBasket = new ShoppingBasket();
	Products dummyProduct = null;

	@Test
	public void testShoppingBasket() {
		// Creating the user account. Database handeler prevents any duplications!
		DatabaseHandlerHSQL.getDatabase().createAccount("Username", "email@email.com", "Password");
		// Registering the product.
		Products.registerProduct(new Login("email@email.com","Password"), "xT3aRsd2asdq",20.30,"yTsaSqusjam");
		
		try {
			// Querying for the product id
			ResultSet query = DatabaseHandlerHSQL.getDatabase().Query("SELECT * FROM products WHERE name = 'xT3aRsd2asdq'");
			while(query.next()) {
				// Creating the dummy product
				dummyProduct = new Products(query.getInt("product_id"),"xT3aRsd2asdq",  "yTsaSqusjam",20.30);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Checking if you can only add the same product once 
		assertEquals(true,newBasket.addItem(dummyProduct));
		assertEquals(false,newBasket.addItem(dummyProduct));
		// Checking if the product is in the basket
		assertEquals(dummyProduct, newBasket.getBasket().get(0));
		// Checking if the total price works
		assertEquals(20.30, newBasket.getTotalPrice(),0);

	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testInvalidProduct() {
		newBasket.remoteItem(null);
		newBasket.addItem(null);
	}

}
