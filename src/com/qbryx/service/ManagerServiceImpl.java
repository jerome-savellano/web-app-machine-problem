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
		// TODO Auto-generated method stub
		return productDao.getProductByUpcMan(upc);
	}

	@Override
	public boolean addProduct(Product product, InventoryProduct inventoryProduct) {
		// TODO Auto-generated method stub
		return productDao.addProduct(product) && productDao.addProductStock(inventoryProduct);
	}

	@Override
	public boolean updateProduct(Product product, InventoryProduct inventoryProduct) {
		// TODO Auto-generated method stub
		return productDao.updateProduct(product) && productDao.updateProductStock(inventoryProduct);
	}
}
