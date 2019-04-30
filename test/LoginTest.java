import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class LoginTest {

	@Test
	void testLoginCreation() {
		DatabaseHandlerHSQL db = DatabaseHandlerHSQL.getDatabase();
		db.createAccount("TestAccount", "test@test", "password");
		
		ResultSet testingAccount = db.Query("SELECT * FROM useraccount WHERE email = 'test@test' AND password = 'password'");
		boolean Result = false;
		try {
			 Result = testingAccount.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(true, Result);
		ResultSet testingAccount1 = db.Query("DELETE FROM useraccount WHERE email = 'test@test'");

	}

}
