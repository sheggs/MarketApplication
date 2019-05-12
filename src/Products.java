import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Products {
	/** Initialising the fields **/
	private int id = 0;
	private String name = null;
	private String description = null;
	private double price = 0;
	
	/**
	 * 
	 * @param id The id of the product
	 * @param name The name of the product
	 * @param description The description of the product.
	 * @param price The price of the product.
	 */
	public Products(int id,String name, String description, double price) {
		/** Checking if the product exists in the database **/
		if(validateProduct( id, name,  description, price)){
			this.name = name;
			this.id = id;
			this.price = price;
			this.description = description;
		 }else {
			 throw new IllegalArgumentException("Product does not exist");
		 }
	}
	/**
	 * 
	 * @param id Product id
	 * @param name Product name
	 * @param description Product descripitoin
	 * @param price Product Price
	 * @return a boolean value whether the product exists.
	 */
	private boolean validateProduct(int id,String name, String description, double price) {
		boolean valid = false;
		// Checking if all the fields exist in database
		ResultSet query = DatabaseHandlerHSQL.getDatabase().Query("SELECT * FROM products WHERE product_id = '"+id+"' AND name = '"+name+"' AND description = '"+description+"' AND price = '"+price+"'");
		try {
			// If the row exists the product is valid
			valid = query.next();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return valid;
	}
	/**
	 * 
	 * @param login The user that is purchasing the product.
	 */
	public void purchaseProduct(User user) {
		/** Setting the product as purchased **/
		DatabaseHandlerHSQL.getDatabase().Query("UPDATE products SET BuyerID = '"+ user.getUserID() +"' WHERE product_id = '"+getId()+"'");
	}
	/**
	 * 
	 * @return A boolean value whether the product has been purchased.
	 */
	public boolean productPurchased() {
		boolean exists = false;
		/** Querying database to see if this product has been purchased **/
		ResultSet query = DatabaseHandlerHSQL.getDatabase().Query("SELECT * FROM products WHERE product_id = '"+id+"' AND BuyerID IS NULL");
		try {
			/** This query has a row so this product has been purchased. **/
			exists = query.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exists;
	}
	
	/**
	 * 
	 * @return The price of the product.
	 */
	public double getPrice() {
		return this.price;
	}
	/**
	 * 
	 * @return The description of the product.
	 */
	public String getDescription() {
		return this.description;
	}
	/**
	 * 
	 * @return The name of the product
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * 
	 * @return The id of the product.
	 */
	public int getId() {
		return this.id;
	}
	/**
	 * 
	 * @param login The login details of the user that wants to register a product.
	 * @param name The name of the product
	 * @param price The price of the product
	 * @param description The description of the product.
	 * @return A boolean value whether this has been successful.
	 */
	public static boolean registerProduct(Login login,String name, double price,String description) {
		boolean successful = false;
		/** Getting the database **/
		DatabaseHandlerHSQL db = DatabaseHandlerHSQL.getDatabase();
		/** Initialising the statement variable **/
		PreparedStatement registerProductStament;
		try {
			/** Restarting connection **/
			db.reestablishConnection();
			/** Setting up the prepared statement **/
			registerProductStament = db.getCon().prepareStatement("INSERT INTO products (name,price,approval,description,SellerID) VALUES (?,?,?,?,?)");
			/** Filling out each variable inside the prepared statement **/
			registerProductStament.setString(1,name);
			registerProductStament.setDouble(2, price);
			registerProductStament.setInt(3, 0);
			registerProductStament.setString(4, description);
			registerProductStament.setInt(5, login.getUserID());
			/** Execute the updated **/
			registerProductStament.executeUpdate();
			/** Set the boolean value to true if there is no SQLException **/
			successful= true;
			/** End the connection to the database **/
			db.endConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return successful;
		
	}
	/**
	 * 
	 * @return A boolean value whether product has been deleted.
	 */
	public boolean removeProduct() {
		boolean successfulRemoval = false;
		/** Getting the database **/
		DatabaseHandlerHSQL db =  DatabaseHandlerHSQL.getDatabase();
		/** Executing query to delete the product **/
		if(db.executeQuery("DELETE FROM products WHERE product_id = '"+ this.id + "'")) {
			/** Setting return value to true as the query has been successful **/
			successfulRemoval = true;
		}
		return successfulRemoval;

	}
	/**
	 * 
	 * @return An arraylist of all the products that have been approved
	 */
	public static ArrayList<Products> getProducts() {
		ArrayList<Products> productList = new ArrayList<Products>();
		try {
			/** QUERYING the database to get all the products that have been approved and not purchased **/
			DatabaseHandlerHSQL db =  DatabaseHandlerHSQL.getDatabase();
			ResultSet query = db.Query("SELECT * FROM products WHERE approval = 1 AND BuyerID IS NULL");
			/** Looping through each row to get every product **/
			while(query.next()) {
				productList.add(new Products(query.getInt(1),query.getString(2),query.getString(5),query.getDouble(3)));
			}
			return productList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 
	 * @return An arraylist of all the products that have not been approved
	 */
	public static ArrayList<Products> getUnApprovedProducts() {
		ArrayList<Products> productList = new ArrayList<Products>();
		try {
			/** QUERYING the database to get all the procuts that have been not been approved and not purchased **/
			DatabaseHandlerHSQL db =  DatabaseHandlerHSQL.getDatabase();
			ResultSet query = db.Query("SELECT * FROM products WHERE approval = 0 AND BuyerID IS NULL");
			/** Looping through each row to get every product **/
			while(query.next()) {
				productList.add(new Products(query.getInt(1),query.getString(2),query.getString(5),query.getDouble(3)));
			}
			return productList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 
	 * @return A boolean value whether the product has been approved.
	 */
	public boolean approveProduct() {
		boolean approved = false;
		/** Updating the approval variable to true **/
		 if(DatabaseHandlerHSQL.getDatabase().executeQuery("UPDATE products SET approval = 1 WHERE product_id = '"+this.id+"'")) {
			 approved = true;
		 }
		 return approved;
	}

	/**
	 * 
	 * @param id The product id
	 * @return An array of the user ID and the username.
	 */
	public String[] getSeller() {
		/** Initialising the array **/
		String[] sellerID = new String[2];
		try {
			/** Querying the database for the product **/
			DatabaseHandlerHSQL db =  DatabaseHandlerHSQL.getDatabase();
			ResultSet query = db.Query("SELECT * FROM products JOIN useraccount ON products.SellerID = useraccount.user_id WHERE products.product_id = '"+this.id+"'");
			while(query.next()) {
				/** Storing the ID and name of the seller **/
				sellerID[0] = (query.getString("user_id"));
				sellerID[1] = (query.getString("username"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sellerID;
	}
	/** 
	 * A function to delete the product
	 */
	public void deleteProduct() {
		/** Deleting the product **/
		 DatabaseHandlerHSQL.getDatabase().executeQuery("DELETE FROM products WHERE product_id = '" + getId() +"'");
	}
	/** Overriding the equals function to compare using the product id **/
	@Override
	public boolean equals(Object o) {
		// If the object is null an exception is run.
		if(o == null) {
			throw new IllegalArgumentException("Cannot be null");
		}
		boolean isEqual = false;
		/** Checking if both id values are the same **/
		if(this.id ==((Products)o).getId()) {
			/** True is returned as both objects refrence the same product **/
			isEqual = true;
		}
		return isEqual;
	}
	/** Overriding the toString function **/
	@Override
	public String toString() {
		return ("ID:" + this.getId() + " Name: " + this.getName() + " Description: "+this.getDescription() + " Price £:" + this.getPrice());
	}
	
//	public static void main(String[] args) {
//		for(Products p : Products.getProducts()) {
//			p.getSeller();
//		}
//	}

}
