import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class YourAccount {
	/** Initialising the fields **/
	private Login login = null;
	public YourAccount(Login login) {
		this.login = login;
	}
	/**
	 * 
	 * @return ArrayList of products that have been purchased by the user
	 */
	public ArrayList<Products> getProductsPurchased(){
		// Initialising the products field;
		ArrayList<Products> purchasedProducts = new ArrayList<Products>();

		// Querying for all the products that have been purchased by the user.
		ResultSet query = DatabaseHandlerHSQL.getDatabase().Query("SELECT * FROM products WHERE BuyerID = '"+login.getUser().getUserID()+"'");
		try {
			// Looping through every product the user has purchased;
			while(query.next()) {
				// Adding the product into the ArrayList.
				purchasedProducts.add(new Products(query.getInt(1),query.getString(2),query.getString(5),query.getDouble(3)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return purchasedProducts;
	}
	
	public ArrayList<Products> getProductsAwaitingApproval(){
		// Initialising the products field;
				ArrayList<Products> awaitingProducts = new ArrayList<Products>();
				// Querying for all the products that have been purchased by the user.
				ResultSet query = DatabaseHandlerHSQL.getDatabase().Query("SELECT * FROM products WHERE SellerID = '"+login.getUser().getUserID()+"' AND BUYERID IS NULL AND approval = 0");
				try {
					
					// Looping through every product the user has purchased;
					while(query.next()) {
						// Adding the product into the ArrayList.
						awaitingProducts.add(new Products(query.getInt(1),query.getString(2),query.getString(5),query.getDouble(3)));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return awaitingProducts;
	}
	public ArrayList<Products> getProductsOnMarket(){
		// Initialising the products field;
				ArrayList<Products> productsOnMarket = new ArrayList<Products>();
				// Querying for all the products that have been purchased by the user.
				ResultSet query = DatabaseHandlerHSQL.getDatabase().Query("SELECT * FROM products WHERE SellerID = '"+login.getUser().getUserID()+"' AND BUYERID IS NULL AND approval = 0");
				try {
					
					// Looping through every product the user has purchased;
					while(query.next()) {
						// Adding the product into the ArrayList.
						productsOnMarket.add(new Products(query.getInt(1),query.getString(2),query.getString(5),query.getDouble(3)));
					}
				} catch (SQLException e) {	
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return productsOnMarket;
	}
	public ArrayList<Products> getProductsSold(){
		// Initialising the products field;
				ArrayList<Products> productsSold = new ArrayList<Products>();
				// Querying for all the products that have been purchased by the user.
				ResultSet query = DatabaseHandlerHSQL.getDatabase().Query("SELECT * FROM products WHERE SellerID = '"+login.getUser().getUserID()+"' AND BUYERID IS NOT NULL AND approval = 0");
				try {
					
					// Looping through every product the user has purchased;
					while(query.next()) {
						// Adding the product into the ArrayList.
						productsSold.add(new Products(query.getInt(1),query.getString(2),query.getString(5),query.getDouble(3)));
					}
				} catch (SQLException e) {	
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return productsSold;
	}


}
