import static org.junit.Assert.*;

import org.junit.Test;
import org.omg.PortableServer.POAManagerOperations;

public class PromotionalVoucherCodesTest {

	@Test
	public void testTopUpCodeCreation() {
		DatabaseHandlerHSQL db = DatabaseHandlerHSQL.getDatabase();
		// Creating dummy account
		db.createAdminAccount(100,101,"adminT", "adminTest123@adminTest123", "Password");
		Login login = new Login("adminTest123@adminTest123","Password");
		
		PromotionalVoucherCodes promoCodeObject = new PromotionalVoucherCodes();
		String code = promoCodeObject.generateCode(99, login.getAdmin().getAdminID(), CodeType.TOPUP_CODE);
		assertEquals(true, promoCodeObject.checkIfCodeExists(code, CodeType.TOPUP_CODE));
		assertEquals(99, promoCodeObject.getCodeValue(code, CodeType.TOPUP_CODE),0);
	}
	@Test
	public void testPromotionalCodeCreation() {
		DatabaseHandlerHSQL db = DatabaseHandlerHSQL.getDatabase();
		// Creating dummy account
		db.createAdminAccount(100,101,"adminT", "adminTest123@adminTest123", "Password");
		Login login = new Login("adminTest123@adminTest123","Password");
		
		PromotionalVoucherCodes promoCodeObject = new PromotionalVoucherCodes();
		String code = promoCodeObject.generateCode(22, login.getAdmin().getAdminID(), CodeType.PROMOTIONAL_CODE);
		assertEquals(true, promoCodeObject.checkIfCodeExists(code, CodeType.PROMOTIONAL_CODE));
		assertEquals(22, promoCodeObject.getCodeValue(code, CodeType.PROMOTIONAL_CODE),0);
		assertEquals(22, promoCodeObject.applyPromoCode(code),0);
	}

}
