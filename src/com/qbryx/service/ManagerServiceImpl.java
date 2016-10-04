package com.qbryx.service;

import com.qbryx.dao.ProductDao;
import com.qbryx.dao.ProductDaoImpl;
import com.qbryx.domain.InventoryProduct;
import com.qbryx.domain.Product;

public class ManagerServiceImpl implements ManagerService {

	private ProductDao productDao;
	
	public ManagerServiceImpl(){
		this.productDao = new ProductDaoImpl();
	}
	
	@Override
	public Product getProductByUpc(String upc) {
		return productDao.getInventoryProductByUpc(upc);
	}

	@Override
	public void addProduct(InventoryProduct inventoryProduct) {
		productDao.addProduct(inventoryProduct); 
		productDao.addProductStock(inventoryProduct);
	}

	@Override
	public void updateProduct(InventoryProduct inventoryProduct) {
		productDao.updateProduct(inventoryProduct);
		productDao.updateProductStock(inventoryProduct);
	}
}
