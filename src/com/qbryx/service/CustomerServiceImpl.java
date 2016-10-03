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
import com.qbryx.util.ServiceFactory;

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
		// TODO Auto-generated method stub
		return userDao.getCartId(userId);
	}

	@Override
	public boolean addProductInCart(CartProduct cartProduct, String cartId) {
		// TODO Auto-generated method stub
		int stockOnHand = productDao.getStock(cartProduct.getUpc());

		if (cartDao.checkProductInCart(cartId, cartProduct.getUpc()) != null) {
			CartProduct product = cartDao.checkProductInCart(cartId, cartProduct.getUpc());

			if ((product.getQuantity() + cartProduct.getQuantity()) <= stockOnHand) {
				product.setQuantity(product.getQuantity() + cartProduct.getQuantity());

				return cartDao.updateProductQuantityInCart(product);
			}
		} else {
			if (cartProduct.getQuantity() < stockOnHand) {
				return cartDao.addProductInCart(cartProduct, cartId);
			}
		}

		return false;
	}

	@Override
	public List<CartProduct> getProductsInCart(String cartId) {
		// TODO Auto-generated method stub
		return cartDao.getProductsInCart(cartId);
	}

	@Override
	public boolean removeProductInCart(String cartId, String upc) {
		// TODO Auto-generated method stub
		return cartDao.removeProductInCart(cartId, upc);
	}

	@Override
	public boolean updateProductInCart(String cartId) {
		// TODO Auto-generated method stub
		return cartDao.updateProductStatusInCart(cartId);
	}

	@Override
	public int getQuantityOfProductInCart(String cartId, String upc) {
		// TODO Auto-generated method stub
		return cartDao.getQuantity(cartId, upc);
	}

	@Override
	public List<String> checkout(String cartId) {
		// TODO Auto-generated method stub
		List<String> invalidProduct = new ArrayList<>();

		List<CartProduct> cartProducts = getProductsInCart(cartId);

		for (CartProduct cartProduct : cartProducts) {
			InventoryProduct inventoryProduct = new InventoryProduct(cartProduct.getUpc(),
					productDao.getStock(cartProduct.getUpc()));

			if (inventoryProduct.getStock() > cartProduct.getQuantity()) {
				int newStock = inventoryProduct.getStock() - cartProduct.getQuantity();

				inventoryProduct.setStock(newStock);
				productDao.updateProductStock(inventoryProduct);
				cartDao.updateProductStatusInCart(cartId);
			} else {
				invalidProduct.add(cartProduct.getName());
			}
		}

		return invalidProduct;
	}

}
