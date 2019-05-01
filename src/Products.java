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
	 * @return a boolean value wheather the product exists.
	 */
	private boolean validateProduct(int id,String name, String description, double price) {
		boolean valid = false;
		ResultSet query = DatabaseHandlerHSQL.getDatabase().Query("SELECT * FROM products WHERE id = '"+id+"' AND name = '"+name+"' AND description = '"+description+"' AND price = '"+price+"'");
		try {
			valid = query.next();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return valid;
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
	public static ArrayList<Products> getProducts() {
		ArrayList<Products> productList = new ArrayList<Products>();
		try {
			DatabaseHandlerHSQL db =  DatabaseHandlerHSQL.getDatabase();
			ResultSet query = db.Query("SELECT * FROM products WHERE approval = 0 AND BuyerID IS NULL");
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
	 * @param id The product id
	 * @return An array of the user ID and the username.
	 */
	public String[] getSeller() {
		System.out.println("hi");
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
