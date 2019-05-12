import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;

public class Login{
	/** Initialising the variables **/
	private int userID = 0;
	private String email = null;
	private String password = null;
	private DatabaseHandlerHSQL db = DatabaseHandlerHSQL.getDatabase();
	private User user = null;
	private Admin admin = null;
	/**
	 * 
	 * @param email The email of the user.
	 * @param password The password of the user.
	 */
	public Login(String email, String password) {
		/** Storing the data inside the fields **/
		this.email = email;
		this.password = password;
		/** Querying database for the user ID. **/
		ResultSet results = db.Query("SELECT * FROM useraccount WHERE email = '" + this.email + "' AND password = '" + this.password + "'");
		try {
			if(results.next()) {
				/** Getting the user ID and creating a user object **/
				this.userID = results.getInt("user_id");
				this.user = new User(results.getInt("user_id"), results.getString("username"),results.getString("email") ,results.getDate("date_created"));
				/** Checking if the user is an admin and creating an admin object if the user is an admin **/
				if(isAdmin()) {
					this.admin = new Admin(results.getInt("user_id"), results.getString("username"),results.getString("email") ,results.getDate("date_created"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 
	 * @return The admin object.
	 */
	public Admin getAdmin() {
		return this.admin;
	}
	/**
	 * 
	 * @return A boolean value whether the user is an admin
	 */
	public boolean isAdmin() {
		boolean isAdmin = false;
		/** Querying for the user in the admin table **/
		ResultSet query = db.Query("SELECT * FROM admin WHERE user_id = '"+this.userID+"'");
		try {
			/** If the query exists the user is an admin **/
			if(query.next()) {
				isAdmin = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isAdmin;
	}
	
	/**
	 * 
	 * @return The user object.
	 */
	public User getUser() {
		return this.user;
	}
	/**
	 * 
	 * @return The email object.
	 */
	public String getEmail() {
		return this.email;
	}
	/**
	 * 
	 * @return The user ID
	 */
	public int getUserID() {
		return this.userID;
	}
	
	/**
	 * 
	 * @return A boolean value whether this user exists.
	 */
	public boolean checkCredentials() {
		boolean validCredentials = false;
		/** Querying for both the email and password **/
		ResultSet results = db.Query("SELECT * FROM useraccount WHERE email = '" + this.email + "' AND password = '" + this.password + "'");
		try {
			/** If the query exists return true. **/
			if(results.next()) {
				validCredentials = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return validCredentials;
	}

	

}
