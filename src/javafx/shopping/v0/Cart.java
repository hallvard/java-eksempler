package javafx.shopping.v0;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	
	private List<Item> cart = new ArrayList<>();
	
	public List<Item> getCart() {
		return cart;
	}
	
	public void addToCart(Item item) {
		Item cartItem = getItem(item);
		if (cartItem == null) {
			cart.add(item);
		} else {
			var newQty = cartItem.getQuanity() + 1;
			cartItem.setQuantity(newQty);
		}
	}
	
	public Item getItem(Item item) {
		for (Item cartItem : cart) {
			if (item.getName().equals(cartItem.getName())) {
				return cartItem;
			}
		}
		return null;
	}

	public void removeFromCart(Item item) {
		if (getItem(item) == null) {
			throw new IllegalArgumentException(item.getName() + " not in cart");
		} else {
			cart.remove(item);
		}
	}
	
	public double getTotal() {
		var tot = 0;
		for (Item cartItem : cart) {
			tot += cartItem.getTotalPrice();
		}
		return tot;
	}

}
