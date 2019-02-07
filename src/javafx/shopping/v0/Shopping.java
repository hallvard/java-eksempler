package javafx.shopping.v0;

import java.util.List;

public class Shopping {

	
	private Cart cart = new Cart();
	private List<Item> availableItems = List.of(
			new Item("Banan",7),
			new Item("Melk", 20),
			new Item("Sukker", 30),
			new Item("Loff", 15),
			
			new Item("Juice", 25),
			new Item("Laks", 50),
			new Item("Eple",7),
			new Item("Brus", 20),
			
			new Item("BigJava", 800),
			new Item("Appelsin", 7),
			new Item("Kaffe", 20),
			new Item("Kjeks", 15),
			
			new Item("Biff", 40),
			new Item("Torsk", 40),
			new Item("Ris", 15),
			new Item("Pesto",20)
			);
	
	public double getCartTotal() {
		return this.cart.getTotal();
	}
	
	public Item getAvailableItem(int index) {
		if (index >= availableItems.size()) throw new IndexOutOfBoundsException("Index: " + index + ", size of availableItems: " + availableItems.size());
		return availableItems.get(index);
	}
	
	public int getAvailableItemsSize() {
		return this.availableItems.size();
	}
	
	public void addToCart(Item item) {
		this.cart.addNToCart(item, 1);
	}
	
	public void removeFromCart(Item item) {
		this.cart.removeAllFromCart(item);
	}
	
}
