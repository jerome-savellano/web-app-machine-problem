package com.qbryx.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qbryx.domain.Category;
import com.qbryx.domain.Product;
import com.qbryx.managers.ConnectionManager;

public class ProductDaoImpl implements ProductDao {
	
	private static final String GET_ALL_PRODUCTS = "";
	private static final String GET_PRODUCTS_BY_CATEGORY = "select product.upc, product.name from product inner join category on product.category_id = category.category_id where category.name = ?";
	private static final String GET_PRODUCT_BY_UPC = "select upc, name, category_id, description, price from product where upc = ?";
	private static final String GET_PRODUCT_QUANTITY_ON_HAND = "select stock from product_inventory where upc = ?";
	
	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductsByCategory(String categoryName) {
		// TODO Auto-generated method stub
		List<Product> products = new ArrayList<>();
			
		if(ConnectionManager.getConnection() != null){
			PreparedStatement stmt;
			
			try {
				stmt = ConnectionManager.prepareStatement(GET_PRODUCTS_BY_CATEGORY);
				stmt.setString(1, categoryName);
				
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()){
					Product product = new Product();
					
					product.setName(rs.getString("name"));
					product.setUpc(rs.getString("upc"));
					
					products.add(product);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		ConnectionManager.close();
		return products;
	}

	@Override
	public Product getProductByUpc(String upc) {
		// TODO Auto-generated method stub
		Product product = null;
		
		if(ConnectionManager.getConnection() != null){
			PreparedStatement stmt;
			
			
			try {
				stmt = ConnectionManager.prepareStatement(GET_PRODUCT_BY_UPC);
				stmt.setString(1,  upc);
				
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next()){
					product = new Product();
					product.setUpc(rs.getString("upc"));
					product.setName(rs.getString("name"));
					product.setCategory(new Category(rs.getString("category_id"), rs.getString("name")));
					product.setDescription(rs.getString("description"));
					product.setPrice(rs.getBigDecimal("price"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		ConnectionManager.closeConnection();
		return product;
	}

	@Override
	public int getStock(String upc) {
		// TODO Auto-generated method stub
		int stock = 0;
		
		if(ConnectionManager.getConnection() != null){
			PreparedStatement stmt;
			
			
			try {
				stmt = ConnectionManager.prepareStatement(GET_PRODUCT_QUANTITY_ON_HAND);
				stmt.setString(1, upc);
				
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next()){
					stock = rs.getInt("stock");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return stock;
	}

}
