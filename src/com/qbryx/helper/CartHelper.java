package com.qbryx.helper;

import javax.servlet.http.HttpServletRequest;

import com.qbryx.domain.Cart;
import com.qbryx.util.ServiceFactory;

public class CartHelper {
	
	public void populateCartInLayout(String cartId, HttpServletRequest request){
		Cart cart = ServiceFactory.customerService().getCart(cartId);
		
		request.setAttribute("productsInCart", cart.getCartProducts());
		request.setAttribute("totalAmount", cart.getTotal());
	}
}
