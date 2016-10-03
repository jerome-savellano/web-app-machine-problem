package com.qbryx.service;

import com.qbryx.domain.InventoryProduct;
import com.qbryx.domain.Product;

public interface ManagerService {
	
	Product getProductByUpc(String upc);
	
	boolean addProduct(Product product, InventoryProduct inventoryProduct);
	boolean updateProduct(Product product, InventoryProduct inventoryProduct);
}
