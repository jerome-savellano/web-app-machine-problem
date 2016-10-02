package com.qbryx.service;

import java.util.List;

import com.qbryx.domain.Cart;
import com.qbryx.domain.CartProduct;

public interface CustomerService {

	List<CartProduct> getProductsInCart(String cartId);
	String getCartId(int userId);
	int getQuantityOfProductInCart(String cartId, String upc);
	
	boolean addProductInCart(CartProduct cartProduct, String cartId);
	boolean removeProductInCart(String cartId, String upc);
	boolean updateProductInCart(String cartId);
}
