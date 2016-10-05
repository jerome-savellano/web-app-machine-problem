package com.qbryx.service;

import java.util.ArrayList;
import java.util.List;

import com.qbryx.dao.CartDao;
import com.qbryx.dao.CartDaoImpl;
import com.qbryx.dao.ProductDao;
import com.qbryx.dao.ProductDaoImpl;
import com.qbryx.dao.UserDao;
import com.qbryx.dao.UserDaoImpl;
import com.qbryx.domain.Cart;
import com.qbryx.domain.CartProduct;
import com.qbryx.domain.InventoryProduct;
import com.qbryx.exception.InsufficientStockException;

public class CustomerServiceImpl implements CustomerService {

	private UserDao userDao;
	private CartDao cartDao;
	private ProductDao productDao;

	public CustomerServiceImpl() {
		this.userDao = new UserDaoImpl();
		this.cartDao = new CartDaoImpl();
		this.productDao = new ProductDaoImpl();
	}

	@Override
	public String getCartId(int userId) {
		return userDao.getCartId(userId);
	}

	@Override
	public void addProductInCart(CartProduct cartProduct, String cartId) throws InsufficientStockException {
		int stockOnHand = productDao.getStock(cartProduct.getUpc());
		CartProduct product = cartDao.checkProductInCart(cartId, cartProduct.getUpc());

		if (product != null) {
			if ((product.getQuantity() + cartProduct.getQuantity()) <= stockOnHand) {
				product.setQuantity(product.getQuantity() + cartProduct.getQuantity());

				cartDao.updateProductQuantityInCart(product);
			}else{
				
				throw new InsufficientStockException();
			}
		} else {
			if (cartProduct.getQuantity() <= stockOnHand) {
				
				cartDao.addProductInCart(cartProduct, cartId);
			}else{
				
				throw new InsufficientStockException();
			}
		}
	}

	@Override
	public List<CartProduct> getProductsInCart(String cartId) {
		return cartDao.getProductsInCart(cartId);
	}

	@Override
	public void removeProductInCart(String cartId, String upc){
		cartDao.removeProductInCart(cartId, upc);
	}

	@Override
	public void updateProductInCart(String cartId) {
		cartDao.updateProductStatusInCart(cartId);
	}

	@Override
	public int getQuantityOfProductInCart(String cartId, String upc) {
		return cartDao.getQuantity(cartId, upc);
	}

	@Override
	public List<CartProduct> checkout(String cartId) throws InsufficientStockException{
		List<CartProduct> invalidProduct = new ArrayList<>();

		List<CartProduct> cartProducts = getProductsInCart(cartId);

		for (CartProduct cartProduct : cartProducts) {
			InventoryProduct inventoryProduct = new InventoryProduct(cartProduct.getUpc(),
					productDao.getStock(cartProduct.getUpc()));

			if (inventoryProduct.getStock() >= cartProduct.getQuantity()) {
				int newStock = inventoryProduct.getStock() - cartProduct.getQuantity();

				inventoryProduct.setStock(newStock);
				productDao.updateProductStock(inventoryProduct);
				cartDao.updateProductStatusInCart(cartId);
			} else {
				invalidProduct.add(cartProduct);
			}
		}

		return invalidProduct;
	}

	@Override
	public Cart getCart(String cartId) {
		// TODO Auto-generated method stub
		List<CartProduct> cartProducts = getProductsInCart(cartId);
		
		Cart cart = new Cart(cartId, cartProducts);
		
		return cart;
	}
}
