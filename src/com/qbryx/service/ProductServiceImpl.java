package com.qbryx.service;

import java.util.List;

import com.qbryx.dao.CategoryDao;
import com.qbryx.dao.CategoryDaoImpl;
import com.qbryx.dao.ProductDao;
import com.qbryx.dao.ProductDaoImpl;
import com.qbryx.domain.Category;
import com.qbryx.domain.Product;

public class ProductServiceImpl implements ProductService{
	
	private CategoryDao categoryDao;
	private ProductDao productDao;
	
	public ProductServiceImpl(){
		this.categoryDao = new CategoryDaoImpl();
		this.productDao = new ProductDaoImpl();
	}

	@Override
	public List<Category> getCategories() {
		// TODO Auto-generated method stub
		return categoryDao.getCategories();
	}

	@Override
	public List<Product> getProductsByCategory(String categoryName) {
		// TODO Auto-generated method stub
		return productDao.getProductsByCategory(categoryName);
	}

	@Override
	public Product getProductByUpc(String upc) {
		// TODO Auto-generated method stub
		return productDao.getProductByUpcCust(upc);
	}

	@Override
	public Category getCategory(String categoryName) {
		// TODO Auto-generated method stub
		return categoryDao.getCategory(categoryName);
	}

}
