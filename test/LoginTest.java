import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;
/**
 * 
 * Ensure you have not modified anything with the default email@email.com account otherwise tests fail.
 *
 */
class LoginTest {

	@Test
	void testLoginCreation() {
		boolean exists = false;
		DatabaseHandlerHSQL db = DatabaseHandlerHSQL.getDatabase();
		// Creating dummy account
		db.createAccount("TestAccount", "test@test", "password");
		// Checking if account exists in database.
		Login login = new Login("test@test","password");
		if(login !=null) {
			exists = true;
		}
		
		assertEquals(true, exists);
		// Deleting account.
		ResultSet testingAccount1 = db.Query("DELETE FROM useraccount WHERE email = 'test@test'");

	}

}
