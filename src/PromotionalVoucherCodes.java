import java.sql.ResultSet;
import java.sql.SQLException;

public class PromotionalVoucherCodes {
	public static String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890@-/";
	DatabaseHandlerHSQL db = DatabaseHandlerHSQL.getDatabase();
	
	public PromotionalVoucherCodes() {
	
	}
	
	public String generateRandomCode() {
		int numberRequired = 10;
		String voucherCode = "";
		for(int j = 0;j<10;j++) {
			voucherCode += Character.toString(base.charAt((int) (Math.random()*base.length())));

		}
		return voucherCode;
	}
	/**
	 * 
	 * @param login The login object of the user
	 * @param code The top up code.
	 * @return If the application of the code has been successful
	 */
	public boolean applyTopUpCode(Login login,String code) {
		boolean successfulTopUp = false;
		/** Checking if code exists AND if the code has not been used**/
		if(checkIfCodeExists(code,CodeType.TOPUP_CODE) && !checkIfCodeUsed(code,CodeType.TOPUP_CODE)) {
			/** Update uses current balance with the value of the voucher **/
			login.getUser().updateBalance(getCodeValue(code,CodeType.TOPUP_CODE));
			/** Sets the used integer to 1 to show that the code has been used by a user already **/
			DatabaseHandlerHSQL.getDatabase().executeQuery("UPDATE topupvoucher SET used = 1 WHERE code = '"+code+"'");
			successfulTopUp = true;
		}
		return successfulTopUp;
	}
	/**
	 * 
	 * @param login The login object of the user
	 * @param code The top up code.
	 * @return The promotional percentage
	 */
	public double applyPromoCode(String code) {
		double promoPercentage = 0.0;
		/** Checking if code exists AND if the code has not been used**/
		if(checkIfCodeExists(code,CodeType.PROMOTIONAL_CODE) && !checkIfCodeUsed(code,CodeType.PROMOTIONAL_CODE)) {
			/** Returning the promotional value of the code **/
			promoPercentage = getCodeValue(code, CodeType.PROMOTIONAL_CODE);
			/** Sets the used integer to 1 to show that the code has been used by a user already **/
			DatabaseHandlerHSQL.getDatabase().executeQuery("UPDATE "+CodeType.PROMOTIONAL_CODE.getTableName()+" SET used = 1 WHERE code = '"+code+"'");
		}
		return promoPercentage;
	}
	
	
	/**
	 * 
	 * @param code The top up code.
	 * @return a boolean value if the top up voucher has been used
	 */
	public boolean checkIfCodeUsed(String code,CodeType type) {
		
		boolean used = false;
		try {
			// Querying if the code is used
			ResultSet checkCode = db.Query("SELECT * FROM " +type.getTableName()+" WHERE code = '"+code+"' AND used = 1");
			if(checkCode.next()) {
				// set used to true if the code has been used
				used = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return used;
	}
	public double getCodeValue(String code,CodeType type) {
		double amount = 0;
		try {
			ResultSet query = DatabaseHandlerHSQL.getDatabase().Query("SELECT * FROM "+type.getTableName()+" WHERE code = '"+code + "'");
			if (query.next()) {
				amount = query.getDouble("value");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return amount;
	}
	public String generateCode(double value,int admin_id,CodeType type) {
		String code = generateRandomCode();
		if(!checkIfCodeExists(code,type)) {
			db.executeQuery("INSERT INTO "+type.getTableName()+" (code,value,admin_id,used) VALUES ('"+code+"','" + value + "','" + admin_id +"',0)");
		}else {
			code = null;
		}
		return code;
	}
	public boolean checkIfCodeExists(String code,CodeType type) {
		boolean exists = false;
		ResultSet checkCode = db.Query("SELECT * FROM "+type.getTableName()+" WHERE code = '"+code+"'");
		try {
			if(checkCode.next()) {
				exists = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return exists;
	}
//	public static void main(String[] args) {
//		System.out.println(new PromotionalVoucherCodes().generateRandomCode());
//	}

}
