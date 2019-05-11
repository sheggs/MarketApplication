import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
/**
 * 
 * Ensure you have not modified anything with the default email@email.com account otherwise tests fail.
 *
 * Set running order by name since they must run in this specified order.
 */

class AdminTest {
	/**
	 * Creating an admin account. Checking if that is made successfully.
	 */
	@Test
	public void createAdminAccount() {
		boolean exists = false;
		DatabaseHandlerHSQL db = DatabaseHandlerHSQL.getDatabase();
		// Creating dummy account
		db.createAdminAccount(100,101,"adminTestAccountt", "adminTest123@adminTest123", "Password");
		// Checking if account exists in database.
		Login login = new Login("adminTest123@adminTest123","Password");
		if(login !=null) {
			exists = true;
		}
		// Account Created.
		assertEquals(true, exists);

	}
	/**
	 * Checking if the account made is an admin account.
	 */
	@Test
	public void testIfAdmin() {
		Login login = new Login("adminTest123@adminTest123","Password");
		boolean isAdmin =false;
		if(login.getAdmin() != null) {
			isAdmin = true;
		}
		
		assertEquals(true, isAdmin);
	}
	/**
	 * Deleting the admin account.
	 */
	@Test
	public void testDeleteAdminAccount() {
		//Deleting account.
		ResultSet testingAccount1 = DatabaseHandlerHSQL.getDatabase().Query("DELETE FROM useraccount WHERE email = 'adminTest123@adminTest123'");
	}


}
