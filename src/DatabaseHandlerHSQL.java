import java.sql.*; 
import java.util.*; 
public class DatabaseHandlerHSQL {
	/**
	 * Initalising fields with connection details.
	 */
	private String url = "jdbc:hsqldb:file:db_data/marketplace;create=true;shutdown=true";
	private String username = "root";
	private String password = "";
	private Statement statement = null;
	private static DatabaseHandlerHSQL db = null;
	Connection con;
	public DatabaseHandlerHSQL() {
		reestablishConnection();
		setUpUserTables();
		setUpEmailTable();
		setUpProductTables();
		setUpAdminTables();
		setUpTopUpTable();
		setUpPromotionalTable();
		
	}
	public static DatabaseHandlerHSQL getDatabase() {
		if(db == null) {
			db = new DatabaseHandlerHSQL();
			return db;
		}else {
			return db;
		}
	}
	public void reestablishConnection() {
		try {
			if((con == null) || (con.isClosed())){
				this.con = DriverManager.getConnection( this.url,"SA", "");
				if (this.statement == null || this.statement.isClosed()) {
					this.statement = con.createStatement();
					this.statement.execute("SET FILES WRITE DELAY FALSE");
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}

	}
	
	public void endConnection() {
		try {
			if (this.con != null) {
				this.con.close();
			}
			if (this.statement != null) {
				this.statement.close();
			}
			
		} catch (Exception e) {
		}
	}

	public Statement getStatement() {
		return this.statement;
	}
	
	public boolean executeQuery(String sql) {
		reestablishConnection();
		boolean succesfulExecution = false;
		DatabaseHandlerHSQL db =  DatabaseHandlerHSQL.getDatabase();
		try {
			db.getStatement().execute(sql);
			succesfulExecution = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			succesfulExecution = false;
		}
		endConnection();
		return succesfulExecution;
	}
	
	/**
	 * 
	 * @param username Username of user
	 * @param email Email of user
	 * @param password The password of the account
	 * @return A boolean whether account has been created.
	 */
	public boolean createAccount(String username, String email, String password){
		boolean successfulCreation = false;

		try {
			 successfulCreation = false;
			if(checkExistanceInDB("useraccount","username",username) || checkExistanceInDB("useraccount","email",email)){
				successfulCreation= false;
			}else {
				reestablishConnection();

				// Creating account since the user does not exist
				this.statement.executeUpdate("INSERT INTO useraccount (admin,username,email,password,date_created,isBanned,balance) VALUES (0,'"+ username+"','"+email+"','"+password+"',CURRENT_TIMESTAMP,0,0)");
				successfulCreation= true;
			}
			endConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// Printing error and returning false since an error has occured.
			e.printStackTrace();
			successfulCreation= false;
		}

		


		// Returning if the user has been created
		return successfulCreation;

	}
	/**
	 * 
	 * @param tableName Then name of the table
	 * @param column The column you are checking
	 * @param data The data that is meant to be in that column
	 * @return A boolean value whether the data exists in that specified column and table.
	 */
	public boolean checkExistanceInDB(String tableName,String column, String data) {
		Boolean exists = false;

		try {
			reestablishConnection();

			// Getting the results of the query.
			ResultSet gettingUserID = this.statement.executeQuery("SELECT * FROM "+tableName+" WHERE "+column+" = '"+data+"'");

			// Moving the cursor the next position
			if(gettingUserID.next()) {
				// Returning true since this exists
				exists =  true;
			}else {
				// Return false since this does not exist
				exists = false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			exists = false;
		}
		
		return exists;

	}
	public Connection getCon() {
		return con;
	}
	/**
	 * 
	 * @param privilidgeLevel The privilidge level the new admin should have.
	 * @param yourPrivlidgeLevel The privilidge level of the current admin
	 * @param name The name of the account
	 * @param email The email of the account
	 * @param password The password of the account
	 * @return A boolean value whether the account has been created
	 */
	public boolean createAdminAccount(int privilidgeLevel,int yourPrivlidgeLevel,String name, String email, String password){
		boolean successful = false;
		try {
			/** Checking if the credentials are in use **/
			if(!(checkExistanceInDB("useraccount","username",username) || checkExistanceInDB("useraccount","email",email))){
				/** Checking the current admins privilege level is greater **/
				if(privilidgeLevel < yourPrivlidgeLevel) {
					reestablishConnection();
					/** Inserting account into database **/
					this.statement.executeUpdate("INSERT INTO useraccount (admin,username,email,password,date_created,isBanned) VALUES (1,'"+ name+"','"+email+"','"+password+"',CURRENT_TIMESTAMP,0)");
					int userId;
					/** Getting the userid **/
					ResultSet gettingUserID = this.statement.executeQuery("SELECT user_id FROM useraccount WHERE email = '"+email+"'");
					gettingUserID.next();
					userId = Integer.parseInt(gettingUserID.getString("user_id"));
					/** Adding the admin into the admin table **/
					PreparedStatement preparedStatement = this.con.prepareStatement("INSERT INTO admin (privilidge_level,user_id) VALUES (?,?)");
					preparedStatement.setInt(1,privilidgeLevel);
					preparedStatement.setInt(2, userId);
					preparedStatement.executeUpdate();
		     		endConnection();

		     		successful =  true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			successful =  false;
		}
		return successful;
	}
	/**
	 * 
	 * @param user The user that you want to upgrade
	 * @param admin The admin that is trying to upgrade the user
	 * @param privilidgeLevel the privilidge level you want the user to become.
	 * @return A boolean value whether the operation has been successful.
	 */
	public boolean upgradeAccount(User user,Admin admin,int privilidgeLevel) {
		boolean successful = false;
		/** Checking if the admins privlidlge level is greater than the user you want to upgrade **/
		if(privilidgeLevel < admin.getPrivilidgeLevel()) {
			try {
				reestablishConnection();
				/** Inserting the user into the admin table aswell **/
				PreparedStatement preparedStatement = this.con.prepareStatement("INSERT INTO admin (privilidge_level,user_id) VALUES (?,?)");
				preparedStatement.setInt(1,privilidgeLevel);
				preparedStatement.setInt(2, user.getUserID());
				preparedStatement.executeUpdate();
	     		endConnection();
	     		successful = true;
			} catch (SQLException e) {
			}
		}
		return successful;
	}
	/**
	 * 
	 * @param admin The admin that is being demoted
	 * @return a boolean value whether the admin has been demoted
	 */
	public boolean demoteAdmin(Admin admin) {
		boolean demoted = false;
		/** DELETING the admin from the admin table **/
		if(executeQuery("DELETE FROM admin WHERE admin_id = '"+admin.getAdminID()+"'")) {
			demoted = true;
		}
		return demoted;
	}
	private void setUpUserTables() {
		reestablishConnection();

		try {
			this.statement.executeUpdate("CREATE TABLE IF NOT EXISTS useraccount (user_id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), balance DOUBLE,admin SMALLINT,username VARCHAR(16),email VARCHAR(255),password VARCHAR(32),date_created TIMESTAMP,isBanned SMALLINT,PRIMARY KEY (user_id))");
			endConnection();
	    	createAccount("Username", "email@email.com", "Password");

		} catch (SQLException e) {
			if (!e.getSQLState().equals("X0Y32") ){
				e.printStackTrace();
			}
		}
	}
	private void setUpProductTables() {
		reestablishConnection();
		try {

			this.statement.executeUpdate(
					"CREATE TABLE IF NOT EXISTS products (product_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
					+ "name VARCHAR(255),"
					+ "price DOUBLE,"
					+ "approval SMALLINT,"
					+ "description VARCHAR(255),"
					+ "SellerID INTEGER NOT NULL references useraccount(user_id) ON DELETE CASCADE,"
					+ "BuyerID INTEGER references useraccount(user_id),"
					+ "PRIMARY KEY (product_id))");
     		endConnection();


		} catch (SQLException e) {
			if (!e.getSQLState().equals("X0Y32") ){
				e.printStackTrace();
			}
		}
	}
	private void setUpAdminTables() {
		reestablishConnection();
		try {
			this.statement.executeUpdate("CREATE TABLE IF NOT EXISTS Admin (admin_id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
					+ "privilidge_level INT,"
					+ "user_id INT REFERENCES useraccount (user_id) ON DELETE CASCADE,"
					+ "PRIMARY KEY (admin_id))");
     		endConnection();
     		createAdminAccount(300,2000,"Admin", "admin@admin.com", "Password");

		} catch (SQLException e) {
			if (!e.getSQLState().equals("X0Y32") ){
				e.printStackTrace();
			}
		}
	}
	/**
	 * Setting up table to store top up codes.
	 */
	private void setUpTopUpTable() {
		// Resetting database connection
		reestablishConnection();
		try {
			// Creating the table.
			this.statement.executeUpdate("CREATE TABLE IF NOT EXISTS topupvoucher (voucher_id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
					+ "code VARCHAR(20),"
					+ "value DOUBLE,"
					+ "used SMALLINT,"
					+ "admin_id INT REFERENCES admin (admin_id) ON DELETE CASCADE,"
					+ "PRIMARY KEY (voucher_id))");

			/** Dropping the database connection**/
     		endConnection();

		} catch (SQLException e) {
			if (!e.getSQLState().equals("X0Y32") ){
				e.printStackTrace();
			}
		}
	}
	/**
	 * Setting up table to store top up codes.
	 */
	private void setUpPromotionalTable() {
		// Resetting database connection
		reestablishConnection();
		try {
			// Creating the table.
			this.statement.executeUpdate("CREATE TABLE IF NOT EXISTS promotionalcode (promotional_id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
					+ "code VARCHAR(20),"
					+ "value DOUBLE,"
					+ "used SMALLINT,"
					+ "admin_id INT REFERENCES admin (admin_id) ON DELETE CASCADE,"
					+ "PRIMARY KEY (promotional_id))");

			/** Dropping the database connection**/
     		endConnection();

		} catch (SQLException e) {
			if (!e.getSQLState().equals("X0Y32") ){
				e.printStackTrace();
			}
		}
	}
	
	private void setUpEmailTable() {
		reestablishConnection();
		try {
			this.statement.executeUpdate("CREATE TABLE IF NOT EXISTS emails (email_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
					+ "email_text VARCHAR(255),"
					+ "user_id INT NOT NULL REFERENCES useraccount (user_id) ON DELETE CASCADE,"
					+ "everyone SMALLINT,"
					+ "PRIMARY KEY (email_id))");
     		endConnection();


		} catch (SQLException e) {
			if (!e.getSQLState().equals("X0Y32") ){
				e.printStackTrace();
			}
		}
	}
	
	public ResultSet Query(String sql) {
		ResultSet results = null;
		try {
			reestablishConnection();
			results = this.statement.executeQuery(sql);
	 		endConnection();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

 		return results;

	}
	public static void main(String[] args) {
		DatabaseHandlerHSQL t = new DatabaseHandlerHSQL();
//   	t.createAdminAccount(300,2000,"Gotcha", "admin@admin.com", "Password");
//    	t.createAccount("Username", "Password", "Password");
    	
//		try {
//			t.reestablishConnection();
//			ResultSet gettingUserID = t.getCon().createStatement().executeQuery("SELECT * FROM useraccount JOIN admin ON useraccount.user_id = admin.user_id");
//			while(gettingUserID.next() != false) {
//				System.out.println(gettingUserID.getInt(1));
//				//System.out.println(gettingUserID.getString(3));
//
//			}
//			t.endConnection();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		
	}


}
