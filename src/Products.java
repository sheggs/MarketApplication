import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Products {
	private int id;
	private String name;
	private String description;
	private double price;
	public Products(int id,String name, String description, double price) {
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
	public double getPrice() {
		return this.price;
	}
	public String getDescription() {
		return this.description;
	}
	public String getName() {
		return this.name;
	}
	public int getId() {
		return this.id;
	}
	
	public static boolean registerProduct(Login login,String name, int price,String description) {
		boolean successful = false;
		DatabaseHandlerHSQL db = DatabaseHandlerHSQL.getDatabase();
		PreparedStatement registerProductStament;
		try {
			db.reestablishConnection();
			registerProductStament = db.getCon().prepareStatement("INSERT INTO products (name,price,approval,description,SellerID) VALUES (?,?,?,?,?)");
			registerProductStament.setString(1,name);
			registerProductStament.setInt(2, price);
			registerProductStament.setInt(3, 0);
			registerProductStament.setString(4, description);
			registerProductStament.setInt(5, login.getUserID());
			registerProductStament.executeUpdate();
			successful= true;
			db.endConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return successful;
		
	}
	public boolean removeProduct() {
		boolean successfulRemoval = false;
		DatabaseHandlerHSQL db =  DatabaseHandlerHSQL.getDatabase();
		if(db.executeQuery("DELETE FROM products WHERE product_id = '"+ this.id + "'")) {
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
			/** QUERYING the database to get all the procuts that have been approved and not purchased **/
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
	public boolean approveProduct() {
		boolean approved = false;
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
		String[] sellerID = new String[2];
		try {
			DatabaseHandlerHSQL db =  DatabaseHandlerHSQL.getDatabase();
			ResultSet query = db.Query("SELECT * FROM products JOIN useraccount ON products.SellerID = useraccount.user_id WHERE products.product_id = '"+this.id+"'");
			while(query.next()) {
				sellerID[0] = (query.getString("user_id"));
				sellerID[1] = (query.getString("username"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sellerID;
	}
	public void deleteProduct() {
		 DatabaseHandlerHSQL.getDatabase().executeQuery("DELETE FROM products WHERE product_id = '" + getId() +"'");
	}
	@Override
	public boolean equals(Object o) {
		if(o == null) {
			throw new IllegalArgumentException("Cannot be null");
		}
		boolean isEqual = false;
		if(this.id ==((Products)o).getId()) {
			isEqual = true;
		}
		return isEqual;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ("ID:" + this.getId() + " Name: " + this.getName() + " Description: "+this.getDescription() + " Price £:" + this.getPrice());
	}
	
//	public static void main(String[] args) {
//		for(Products p : Products.getProducts()) {
//			p.getSeller();
//		}
//	}

}
