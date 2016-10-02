package com.qbryx.dao;

import java.util.List;

import com.qbryx.domain.Cart;
import com.qbryx.domain.CartProduct;

public interface CartDao {
	
	CartProduct checkProductInCart(String cartId, String upc);
	List<CartProduct> getProductsInCart(String cartId);
	int getQuantity(String cartId, String upc);
	
	boolean addProductInCart(CartProduct product, String cartId);
	boolean removeProductInCart(String cartId, String upc);
	boolean updateProductStatusInCart(String cartId);
	boolean updateProductQuantityInCart(CartProduct cartProduct);
}
