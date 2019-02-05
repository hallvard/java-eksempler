package javafx.shopping.v0;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	
	private List<Item> cart = new ArrayList<>();
	
	public void addOneToCart(Item item) {
		Item cartItem = findItem(item);
		if (cartItem == null) {
			cart.add(item);
			item.setQuantity(1);
		} else {
			var newQty = cartItem.getQuanity() + 1;
			item.setQuantity(newQty);
		}
	}
	
	public void removeFromCart(Item item) {
		if (findItem(item) == null) {
			throw new IllegalArgumentException(item.getName() + " not in cart");
		} else {
			item.setQuantity(0);
			cart.remove(item);
		}
	}
	
	public Item findItem(Item item) {
		for (Item cartItem : cart) {
			if (item.getName().equals(cartItem.getName())) {
				return cartItem;
			}
		}
		return null;
	}
	
	public double getTotal() {
		var tot = 0;
		for (Item cartItem : cart) {
			tot += cartItem.getTotalPrice();
		}
		return tot;
	}

}
