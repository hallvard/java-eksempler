package javafx.shopping.v1;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	
	private List<Item> cart = new ArrayList<>();
	
	public Item getItem(int index) {
		if (index >= cart.size()) return null;
		return cart.get(index);
	}
	
	public void addNToCart(Item item, int n) {
		Item cartItem = findItem(item);
		if (cartItem == null) {
			cart.add(item);
			item.setQuantity(n);
		} else {
			int newQty = cartItem.getQuanity() + n;
			cartItem.setQuantity(newQty);
		}
	}
	
	public void removeAllFromCart(Item item) {
		Item cartItem = findItem(item);
		if (findItem(cartItem) == null) {
			throw new IllegalArgumentException(item.getName() + " not in cart");
		} else {
			cartItem.setQuantity(0);
			cart.remove(cartItem);
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
