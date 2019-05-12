import java.sql.ResultSet;
import java.sql.SQLException;

public class PromotionalVoucherCodes {
	/**
	 * The base is a array of characters that will be used to generate the codes
	 */
	public static String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890@-/";
	DatabaseHandlerHSQL db = DatabaseHandlerHSQL.getDatabase();
	
	public PromotionalVoucherCodes() {
	
	}
	
	/**
	 * 
	 * @return A randomly generated code.
	 */
	public String generateRandomCode() {
		/** Number of characters needed **/
		int numberRequired = 10;
		String voucherCode = "";
		for(int j = 0;j<10;j++) {
			/** Appending random characters **/
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
	
	/**
	 * 
	 * @param code The randomly generated code.
	 * @param type The type of code.
	 * @return A double value of the code's value;.
	 */
	public double getCodeValue(String code,CodeType type) {
		double amount = 0;
		try {
			/** Querying for the code **/
			ResultSet query = DatabaseHandlerHSQL.getDatabase().Query("SELECT * FROM "+type.getTableName()+" WHERE code = '"+code + "'");
			/** Checking if the query exists **/
			if (query.next()) {
				/** Getting the code's value **/
				amount = query.getDouble("value");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return amount;
	}
	
	/**
	 * 
	 * @param value The value of the code.
	 * @param admin_id The admin's id.
	 * @param type The type of code.
	 * @return The code that is generated.
	 */
	public String generateCode(double value,int admin_id,CodeType type) {
		/** Generated code **/
		String code = generateRandomCode();
		/** Checking if the code already exists **/
		if(!checkIfCodeExists(code,type)) {
			/** Inserting the query into the database **/
			db.executeQuery("INSERT INTO "+type.getTableName()+" (code,value,admin_id,used) VALUES ('"+code+"','" + value + "','" + admin_id +"',0)");
		}else {
			code = null;
		}
		return code;
	}
	/**
	 * 
	 * @param code The code you want to check
	 * @param type The type of code
	 * @return A boolean value whether the code exists
	 */
	public boolean checkIfCodeExists(String code,CodeType type) {
		boolean exists = false;
		/** Finding the code in the database. **/
		ResultSet checkCode = db.Query("SELECT * FROM "+type.getTableName()+" WHERE code = '"+code+"'");
		try {
			if(checkCode.next()) {
				/** Found the code so set the return value to true **/
				exists = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return exists;
	}

}
