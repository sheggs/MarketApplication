import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Products {
	private int id;
	private String name;
	private String description;
	private double price;
	public Products(int id,String name, String description, double price) {
		this.name = name;
		this.id = id;
		this.price = price;
		this.description = description;
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
