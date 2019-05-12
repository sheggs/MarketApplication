import java.util.ArrayList;

public class ShoppingBasket {
	/**  Initialising the variables **/
	ArrayList<Products> basket = new ArrayList<Products>();
	public ShoppingBasket() {	
	}
	/**
	 *  Clearing the basket
	 */
	public void dropBasket() {
		this.basket.clear();
	}
	/**
	 * 
	 * @param product The product you want to add into the database
	 * @return A boolean value whether this has been successful.
	 */
	public boolean addItem(Products product) {
		boolean success = false;
		/** Checking if the product is initialised properly**/
		if(product == null) {
			throw new IllegalArgumentException("Product cannot be unrefrenced");
		}else {
			/** Checking if this is not a duplicate **/
			if(!this.basket.contains(product)) {
				/** Adding product into the basket **/
				this.basket.add(product);
				success = true;
			}
		}
		return success;
	}
	
	/**
	 * 
	 * @param product The product you want to remove.
	 */
	public void remoteItem(Products product) {
		/** Checking if the product is initialised properly**/
		if(product == null) {
			throw new IllegalArgumentException("Product cannot be unrefrenced");
		}
		/** Looping through the products **/
		for(Products p : this.basket) {
			/** Checking if the product is equal to the product in the array list. **/
			if(p.equals(product)) {
				/** Removing the product **/
				this.basket.remove(p);
			}
		}
	}
	/**
	 * 
	 * @return The list of products
	 */
	public ArrayList<Products> getBasket() {
		return this.basket;
	}
	/**
	 * 
	 * @return The total price of the products in the basket.
	 */
	public double getTotalPrice() {
		double totalPrice = 0;
		for(Products p : getBasket()) {
			totalPrice += p.getPrice();
		}
		return totalPrice;
	}
	

}
