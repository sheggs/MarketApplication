import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;

public class Login{
	
	private int userID = 0;
	private String email = null;
	private String password = null;
	private DatabaseHandlerHSQL db = DatabaseHandlerHSQL.getDatabase();
	private User user = null;
	private Admin admin = null;
	public Login(String email, String password) {
		this.email = email;
		this.password = password;
		ResultSet results = db.Query("SELECT * FROM useraccount WHERE email = '" + this.email + "' AND password = '" + this.password + "'");
		try {
			if(results.next()) {
				this.userID = results.getInt("user_id");
				this.user = new User(results.getInt("user_id"), results.getString("username"),results.getString("email") ,results.getDate("date_created"));
				if(isAdmin()) {
					this.admin = new Admin(results.getInt("user_id"), results.getString("username"),results.getString("email") ,results.getDate("date_created"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public Admin getAdmin() {
		return this.admin;
	}
	public boolean isAdmin() {
		boolean isAdmin = false;
		ResultSet query = db.Query("SELECT * FROM admin WHERE user_id = '"+this.userID+"'");
		try {
			if(query.next()) {
				isAdmin = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isAdmin;
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
//	public static void main(String[] args) {
//		Login a = new Login("admin@admin.com","Password");
//		System.out.println(a.isAdmin());
//	}
	

}
