import java.util.ArrayList;

public class ShoppingBasket {
	ArrayList<Products> basket = new ArrayList<Products>();
	public ShoppingBasket() {	
	}
	public void dropBasket() {
		this.basket.clear();
	}
	public boolean addItem(Products product) {
		boolean success = false;
		if(product == null) {
			throw new IllegalArgumentException("Product cannot be unrefrenced");
		}else {
			if(!this.basket.contains(product)) {
				this.basket.add(product);
				success = true;
				System.out.println(product.getName());
			}
		}
		return success;
	}
	
	public void remoteItem(Products product) {
		if(product == null) {
			throw new IllegalArgumentException("Product cannot be unrefrenced");
		}
		for(Products p : this.basket) {
			if(p.equals(product)) {
				this.basket.remove(p);
			}
		}
	}
	public ArrayList<Products> getBasket() {
		return this.basket;
	}
	public double getTotalPrice() {
		double totalPrice = 0;
		for(Products p : getBasket()) {
			totalPrice += p.getPrice();
		}
		return totalPrice;
	}
	

}
