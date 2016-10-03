package com.qbryx.dao;

import java.util.List;

import com.qbryx.domain.InventoryProduct;
import com.qbryx.domain.Product;

public interface ProductDao {

	List<Product> getAllProducts();
	List<Product> getProductsByCategory(String categoryName);
	List<InventoryProduct> getProductInventory(String upc);
	Product getProductByUpcCust(String upc);
	Product getProductByUpcMan(String upc);
	int getStock(String cartId);
	
	boolean addProduct(Product product);
	boolean addProductStock(InventoryProduct inventoryProduct);
	boolean updateProduct(Product product);
	boolean updateProductStock(InventoryProduct inventoryProduct);
}
