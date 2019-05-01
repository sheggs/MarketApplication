import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
/**
 * 
 * Ensure you have not modified anything with the default email@email.com account otherwise tests fail.
 *
 */
class UserTest {

	@Test
	void testUserCreation() {
		Login login = new Login("email@email.com","Password");
		User user = login.getUser();
		assertEquals(user.getUserID(),1);
		assertEquals(user.getEmail(),"email@email.com");
		assertEquals(user.getUsername(),"Username");
		assertEquals(user.isBanned(), false);
	}
	void testAccountBalanceUpdate() {
		Login login = new Login("email@email.com","Password");
		User user = login.getUser();
		double futureBalance = user.getCurrentBalance() + 100;
		user.updateBalance(100);
		assertEquals(user.getCurrentBalance(), futureBalance);
	}

}
