import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Admin extends User{
	/** Initialising the variables **/
	private int adminID = 0;
	int privilidgeLevel = 0;
	/**
	 * 
	 * @param userID The user id of the admin.
	 * @param username The username of the admin
	 * @param email The email of the admin
	 * @param date The date the account was created
	 */
	public Admin(int userID, String username, String email, Date date) {
		/** The parameters are used to complete the User object since the Admin object inherites the user object **/
		super(userID, username, email, date);
		try {
			/** Querying for the admin id and privilege level **/
			ResultSet query = DatabaseHandlerHSQL.getDatabase().Query("SELECT * FROM admin WHERE user_id = '" + getUserID() + "'");
			if(query.next()) {
				/** Storing the admin ID and privilege levels in variables */
				this.adminID = query.getInt("admin_id");
				this.privilidgeLevel = query.getInt("privilidge_level");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 
	 * @return get the admins ID.
	 */
	public int getAdminID() {
		return this.adminID;
	}
	/**
	 * 
	 * @return The admins privilege level
	 */
	public int getPrivilidgeLevel() {
		return this.privilidgeLevel;
	}
	


}
