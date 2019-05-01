import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Admin extends User{
	private int adminID = 0;
	int privilidgeLevel = 0;
	public Admin(int userID, String username, String email, Date date) {
		super(userID, username, email, date);
		try {
			ResultSet query = DatabaseHandlerHSQL.getDatabase().Query("SELECT * FROM admin WHERE user_id = '" + getUserID() + "'");
			if(query.next()) {
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
	 * @return The admins privlidge level
	 */
	public int getPrivilidgeLevel() {
		return this.privilidgeLevel;
	}
	


}
