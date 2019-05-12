import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManageUsers {

	public ManageUsers() {
	}
	/**
	 * 
	 * @return An array list of every single user that is not an administrator.
	 */
	public static ArrayList<User> getTotalUsers(){
		/** Initialising variables. There is a tempory list of users to avoid Concurrency exceptions **/
		ArrayList<User> listOfAllUsers = new ArrayList<User>();
		ArrayList<Admin> listOfAdmins = getTotalAdmins();
		ArrayList<User> tempUserList =  new ArrayList<User>();
		try {
			/** Querying for all user accounts **/
			ResultSet query = DatabaseHandlerHSQL.getDatabase().Query("SELECT * FROM useraccount");
			while(query.next()) {
				/** adding user accounts to array list**/
				listOfAllUsers.add(new User(query.getInt("user_id"), query.getString("username"), query.getString("email"), query.getDate("date_created")));
			}
			/** Copying user list to a temporary list **/
			tempUserList.addAll(listOfAllUsers);
	
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/** Two for loops to remove all administartors in the user array **/
		for(User user : listOfAllUsers) {
			for(Admin admin : listOfAdmins) {
				if(user.getUserID() == admin.getUserID()) {
					tempUserList.remove(user);
				}
			}
		}
		listOfAllUsers = tempUserList;
		return listOfAllUsers;
	}
	/**
	 * 
	 * @return List of admins
	 */
	public static ArrayList<Admin> getTotalAdmins(){
		/** Initialising variables. There is a tempory list of users to avoid Concurrency exceptions **/

		ArrayList<Admin> listOfAdmins = new ArrayList<Admin>();
		try {
			/** Querying for all admin account details in both useraccount table and admin table **/
			ResultSet query = DatabaseHandlerHSQL.getDatabase().Query("SELECT * FROM useraccount JOIN admin ON useraccount.user_id = admin.user_id");
			while(query.next()) {
				// Adding each admin to array list.
				listOfAdmins.add(new Admin(query.getInt("user_id"), query.getString("username"), query.getString("email"), query.getDate("date_created")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listOfAdmins;
	}
	
	/**
	 * 
	 * @param futureName The name you want to override the user's name
	 * @param user The user who you are targeting.
	 */
	public void changeUsername(String futureName, User user) {
		// Executing a query to change the username.
		DatabaseHandlerHSQL.getDatabase().executeQuery("UPDATE useraccount SET username = '"+futureName +"' WHERE user_id = '"+user.getUserID()+"'");
	}
	/**
	 * 
	 * @param futureEmail The email you want to override the user's email
	 * @param user The user who you are targeting.
	 */
	public void changeEmail(String futureEmail, User user) {
		// Executing a query to change the email.
		DatabaseHandlerHSQL.getDatabase().executeQuery("UPDATE useraccount SET email = '"+futureEmail +"' WHERE user_id = '"+user.getUserID()+"'");
	}
	
	/**
	 * 
	 * @param amount The amount you want to set the users wallet.
	 * @param user The user you are targetting.
	 */
	public void setBalance(String amount, User user) {
		user.updateBalance(-user.getCurrentBalance() + Double.parseDouble(amount));
	}
	


}
