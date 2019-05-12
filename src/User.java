import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class User {
	/**
	 * Initalising variables
	 */
	private String username;
	private int userID;
	private String email;
	private Date date;
	private boolean admin;
	private ShoppingBasket basket;
	/**
	 * 
	 * @param userID The users id
	 * @param username The username.
	 * @param email The email of the user.
	 * @param date The date of creation.
	 */
	public User(int userID, String username, String email, Date date) {
		this.username = username;
		this.userID = userID;
		this.email = email;
		this.date = date;
		this.admin = admin;
		this.basket = new ShoppingBasket();
	}
	/**
	 * 
	 * @return The date the account was created
	 */
	public Date getDate() {
		return this.date;
	}
	/**
	 * 
	 * @return The shopping basket of the user.
	 */
	public ShoppingBasket getBasket() {
		return this.basket;
	}
	/**
	 * 
	 * @return a boolean value whether the user is banned.
	 */
	public boolean isAdmin() {
		return this.admin;
	}
	/**
	 * 
	 * @return The email of the user.
	 */
	public String getEmail() {
		return this.email;
	}
	/**
	 * 
	 * @return the user's id
	 */
	public int getUserID() {
		return this.userID;
	}
	/**
	 * 
	 * @return the user's username.
	 */
	public String getUsername() {
		return this.username;
	}
	
	/**
	 * 
	 * @return A boolean value whether the ban has been successful
	 */
	public boolean banUser() {
		boolean banned = false;
		/** Updating table to ban the user **/
		if(DatabaseHandlerHSQL.getDatabase().executeQuery("UPDATE useraccount SET isBanned = 1 WHERE user_id = '"+getUserID()+"'")) {
			banned = true;
		}
		return banned;
	}
	/**
	 * 
	 * @return A boolean value whether the unban has been successful
	 */
	public boolean unBanUser() {
		boolean unbanned = false;
		/** Updating table to unban the user **/
		if(DatabaseHandlerHSQL.getDatabase().executeQuery("UPDATE useraccount SET isBanned = 0 WHERE user_id = '"+getUserID()+"'")) {
			unbanned = true;
		}
		return unbanned;
	}
	/**
	 * 
	 * @return The users password
	 */
	public String getPassword() {
		String password ="";
		/** querying for the password **/
		ResultSet queryPassword = DatabaseHandlerHSQL.getDatabase().Query("SELECT password FROM useraccount WHERE user_id = '" + this.userID +"'");
		try {
			/** Getting the first row and extracting the password **/
			if(queryPassword.next()) {
				password = queryPassword.getString("password");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return password;
	}
	/**
	 * 
	 * @return A boolean value whether the user is banned
	 */
	public boolean isBanned() {
		/** Querying the database for the user **/
		DatabaseHandlerHSQL db = DatabaseHandlerHSQL.getDatabase();
		ResultSet results = db.Query("SELECT * FROM useraccount WHERE user_id = '" + this.userID +"'");
		boolean banned = false;
		try {
			while(results.next()) {
				// Checking if the user is banned. If so set the banned variable to ture
				if(results.getInt("isBanned") == 1){
					banned = true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return banned;
	}
	
	/**
	 * 
	 * @return The current balance of the user.
	 */
	public double getCurrentBalance() {
		/** Querying the user in the database **/
		DatabaseHandlerHSQL db = DatabaseHandlerHSQL.getDatabase();
		ResultSet results = db.Query("SELECT * FROM useraccount WHERE user_id = '" + this.userID +"'");
		/** Initialising the balance variable **/
		double balance = 0;
		try {
			while(results.next()) {
				/** Setting the balance variable to the balance of the user **/
				balance =  results.getDouble("balance");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return balance;
	}
	
	/**
	 * 
	 * @param amount The amount you want to increase or decrease the users balance.
	 * @return A boolean value whether this has been successful.
	 */
	public boolean updateBalance(double amount) {
		DatabaseHandlerHSQL db = DatabaseHandlerHSQL.getDatabase();
		/** Checking if this makes the users balance below 0 **/
		if((getCurrentBalance() + amount) < 0) {
			return false;
		}else {
			/** Updating the users balance **/
			ResultSet results = db.Query("UPDATE useraccount SET balance = '" + (getCurrentBalance() + amount) + "' WHERE user_id = '" + getUserID() + "'");
			return true;
		}
	}
	/**
	 * 
	 * @param code promotional code
	 * @return A boolean value whether the product has been purchased
	 */
	public boolean purchaseBasket(String code) {
		double totalPrice = 0;
		boolean successful = false;
		double discount = new PromotionalVoucherCodes().applyPromoCode(code);
		double percentageOfCost = (100 - discount)/100;


		/** Looping through the basket to obtain the total price of the basket **/
		for(Products p : getBasket().getBasket()) {
			totalPrice += p.getPrice();
		}
		/** Ensuring the users balance is greater than the total price **/
		totalPrice *= percentageOfCost;
		if(totalPrice < getCurrentBalance()) {
			System.out.println(1);
			/** Looping through the basket and setting every product's BuyerID to the user's **/
			for(Products p : getBasket().getBasket()) {
				p.purchaseProduct(this);
			}
			/** Setting up the discount price **/
			/** Updating the account balance**/
			updateBalance(-(totalPrice));
			/** Emptying the basket **/
			getBasket().dropBasket();
			successful = true;
		}
		return successful;
	}
	
	

}
