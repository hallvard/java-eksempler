package javafx.shopping.v0;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Shopping {

	private List<Item> availableItems;
	private Cart cart;

	public Shopping() {
		this.cart = new Cart();
		this.availableItems = new ArrayList<Item>(Arrays.asList(
				new Item("Banan",7),
				new Item("Melk", 20),
				new Item("Sukker", 30),
				new Item("Loff", 15),
				new Item("Juice", 25),
				new Item("Laks", 50)
				));
	}
	
	public double getCartTotal() {
		return this.cart.getTotal();
	}
	
	public List<Item> getAvailableItems() {
		return availableItems;
	}
	
	public List<Item> getCart() {
		return this.cart.getCart();
	}
	
	public void addToCart(Item item) {
		this.cart.addToCart(item);
	}
	
	public void removeFromCart(Item item) {
		this.cart.removeFromCart(item);
	}
	
	public void addAvailableItem(Item item) {
		availableItems.add(item);
	}
	
}
