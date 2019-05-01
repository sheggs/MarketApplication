import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class User {
	private String username;
	private int userID;
	private String email;
	private Date date;
	private boolean admin;
	private ShoppingBasket basket;
	public User(int userID, String username, String email, Date date) {
		this.username = username;
		this.userID = userID;
		this.email = email;
		this.date = date;
		this.admin = admin;
		this.basket = new ShoppingBasket();
	}
	public Date getDate() {
		return this.date;
	}
	public ShoppingBasket getBasket() {
		return this.basket;
	}
	public boolean isAdmin() {
		return this.admin;
	}
	public String getEmail() {
		return this.email;
	}
	public int getUserID() {
		return this.userID;
	}
	public String getUsername() {
		return this.username;
	}
	
	public boolean isBanned() {
		DatabaseHandlerHSQL db = DatabaseHandlerHSQL.getDatabase();
		ResultSet results = db.Query("SELECT * FROM useraccount WHERE user_id = '" + this.userID +"'");
		boolean banned = false;
		try {
			while(results.next()) {
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
	
	public double getCurrentBalance() {
		DatabaseHandlerHSQL db = DatabaseHandlerHSQL.getDatabase();
		ResultSet results = db.Query("SELECT * FROM useraccount WHERE user_id = '" + this.userID +"'");
		double balance = 0;
		try {
			while(results.next()) {
				balance =  results.getDouble("balance");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			balance = 0;
		}
		return balance;
	}
	public boolean updateBalance(double amount) {
		DatabaseHandlerHSQL db = DatabaseHandlerHSQL.getDatabase();
		if((getCurrentBalance() + amount) < 0) {
			return false;
		}else {
			ResultSet results = db.Query("UPDATE useraccount SET balance = '" + (getCurrentBalance() + amount) + "' WHERE user_id = '" + getUserID() + "'");
			return true;
		}
	}
	public boolean purchaseBasket(String code) {
		double totalPrice = 0;
		boolean successful = false;
		for(Products p : getBasket().getBasket()) {
			totalPrice += p.getPrice();
		}
		if(totalPrice < getCurrentBalance()) {
			for(Products p : getBasket().getBasket()) {
				DatabaseHandlerHSQL.getDatabase().Query("UPDATE products SET BuyerID = '"+ getUserID() +"' WHERE product_id = '"+p.getId()+"'");
	
			}
			double discount = new PromotionalVoucherCodes().applyPromoCode(code);
			double percentageOfCost = (100 - discount)/100;
			updateBalance(-(totalPrice*percentageOfCost));
			getBasket().dropBasket();
			successful = true;
		}
		return successful;
	}
	
//	public static void main(String[] args) {
//		Login login = new Login("Password","Password");
//		User user = login.getUser();
//		System.out.println(user.updateBalance(100));
//
//		System.out.println(user.getCurrentBalance());
//	}
	

}
