import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;

import org.junit.Test;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
/**
 * 
 * Ensure you have not modified anything with the default email@email.com account otherwise tests fail.
 *
 * Set running order by name since they must run in this specified order.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class YourAccountTest {
	@Test
	public void test1CreationDummyProducts() {
		Login login = new Login("email@email.com","Password");
		User user = login.getUser();
		boolean dummyProductExists = false;
		/** Querying for duplicate product **/
		ResultSet query = DatabaseHandlerHSQL.getDatabase().Query("SELECT * FROM products WHERE name = '0x129xsid90'");
		try {
			while(query.next()){
				/** Deleting duplicate Product **/
				new Products(query.getInt("product_id"),"0x129xsid90","RandomProduct Description", 22).deleteProduct();
			}
		} catch (SQLException e) {
		}
		Products.registerProduct(login,"0x129xsid90", 22,"RandomProduct Description");
	}
	@Test
	public void test2awaitingApprovalTest() {
		Login login = new Login("email@email.com","Password");
		int productid = -1;
		ResultSet query = DatabaseHandlerHSQL.getDatabase().Query("SELECT * FROM products WHERE name = '0x129xsid90'");
		try {
			if(query.next()) {
				productid = query.getInt("product_id");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(productid);
		Products product = new Products(productid,"0x129xsid90","RandomProduct Description",22);
		assertEquals(new YourAccount(login).getProductsAwaitingApproval().get(0), product);
	}
	@Test
	public void test3ProductInAccount() {
		Login login = new Login("email@email.com","Password");
		User user = login.getUser();
		YourAccount youraccount = new YourAccount(login);
		Products dummyProduct = youraccount.getProductsAwaitingApproval().get(0);
		assertEquals(dummyProduct.getName(),"0x129xsid90");
		assertEquals(dummyProduct.getPrice(),22,0);
		assertEquals(dummyProduct.getDescription(),"RandomProduct Description");
	}
	
	@Test
	public void test4purchaseDummyProductTest() {
		Login login = new Login("email@email.com","Password");
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
		product.purchaseProduct(login.getUser());
		assertEquals(new YourAccount(login).getProductsPurchased().get(0), product);
	}


}
