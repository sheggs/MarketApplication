import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;

public class Login{
	
	private int userID = 0;
	private String email = null;
	private String password = null;
	private DatabaseHandlerHSQL db = DatabaseHandlerHSQL.getDatabase();
	private User user = null;
	public Login(String email, String password) {
		this.email = email;
		this.password = password;
		ResultSet results = db.Query("SELECT * FROM useraccount WHERE email = '" + this.email + "' AND password = '" + this.password + "'");
		try {
			if(results.next()) {
				boolean isAdmin = false;
				this.userID = results.getInt("user_id");
				System.out.println(this.userID);
				if(results.getInt("admin") == 1) {
					isAdmin = true;
				}
				this.user = new User(results.getInt("user_id"), results.getString("username"),results.getString("email") ,results.getDate("date_created"),isAdmin);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public User getUser() {
		return this.user;
	}
	public String getEmail() {
		return this.email;
	}
	public int getUserID() {
		return this.userID;
	}
	
	public boolean checkCredentials() {
		ResultSet results = db.Query("SELECT * FROM useraccount WHERE email = '" + this.email + "' AND password = '" + this.password + "'");
		try {
			if(results.next()) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public static void main(String[] args) {
		Login a = new Login("Password","Password");
		System.out.println(a.checkCredentials());
	}
	

}
