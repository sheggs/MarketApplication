import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class test2 {
	private Login login = null;
	public test2(Login login) {
		this.login = login;
	}
	/**
	 * 
	 * @return ArrayList of products that have been purchased by the user
	 */
	public ArrayList<Products> getProductsPurchased(){
		// Initialising the products field;
		ArrayList<Products> purchasedProducts = null;
		// Querying for all the products that have been purchased by the user.
		ResultSet query = DatabaseHandlerHSQL.getDatabase().Query("SELECT * FROM products WHERE BuyerID = '"+login.getUser().getUserID()+"'");
		try {
			purchasedProducts = new ArrayList<Products>();
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
				ArrayList<Products> awaitingProducts = null;
				// Querying for all the products that have been purchased by the user.
				ResultSet query = DatabaseHandlerHSQL.getDatabase().Query("SELECT * FROM products WHERE SellerID = '"+login.getUser().getUserID()+"' AND approval = 0");
				try {
					awaitingProducts = new ArrayList<Products>();
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
				ArrayList<Products> productsOnMarket = null;
				// Querying for all the products that have been purchased by the user.
				ResultSet query = DatabaseHandlerHSQL.getDatabase().Query("SELECT * FROM products WHERE SellerID = '"+login.getUser().getUserID()+"' AND approval = 1");
				try {
					productsOnMarket = new ArrayList<Products>();
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
	
	public static void main(String[] args) {
		ArrayList<Products> p = new test2(new Login("password","password")).getProductsPurchased();
		System.out.println(p.get(0).getName());
		System.out.println("hi");
		
	}
}
