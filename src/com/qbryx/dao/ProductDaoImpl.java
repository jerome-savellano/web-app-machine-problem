package com.qbryx.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qbryx.domain.Category;
import com.qbryx.domain.InventoryProduct;
import com.qbryx.domain.Product;
import com.qbryx.managers.ConnectionManager;

public class ProductDaoImpl implements ProductDao {
	
	private static final String GET_ALL_PRODUCTS = "";
	private static final String GET_PRODUCTS_BY_CATEGORY = "select product.upc, product.name from product inner join category on product.category_id = category.category_id where category.name = ?";
	private static final String GET_PRODUCT_BY_UPC_P = "select upc, name, category_id, description, price from product where upc = ?";
	private static final String GET_PRODUCT_BY_UPC_M = "select p.name, .p.price, p.upc, c.category_id, c.name, p.description, pi.stock from product p inner join category c on p.category_id = c.category_id inner join product_inventory pi on p.upc = pi.upc  where p.upc = ?";
	private static final String GET_PRODUCT_QUANTITY_ON_HAND = "select stock from product_inventory where upc = ?";
	private static final String ADD_PRODUCT = "insert into product (category_id, upc, name, description, price) values (?,?,?,?,?);";
	private static final String ADD_PRODUCT_STOCK = "insert into product_inventory (upc, stock) values (?,?);";
	
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
	public Product getProductByUpcCust(String upc) {
		// TODO Auto-generated method stub
		Product product = null;
		
		if(ConnectionManager.getConnection() != null){
			PreparedStatement stmt;
			
			
			try {
				stmt = ConnectionManager.prepareStatement(GET_PRODUCT_BY_UPC_P);
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
		
		ConnectionManager.close();
		return stock;
	}

	@Override
	public Product getProductByUpcMan(String upc) {
		// TODO Auto-generated method stub
		InventoryProduct product = null;
		
		if(ConnectionManager.getConnection() != null){
			PreparedStatement stmt;
			
			try {
				stmt = ConnectionManager.prepareStatement(GET_PRODUCT_BY_UPC_M);
				stmt.setString(1, upc);
				
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next()){
					Product mProduct = new Product();
					mProduct.setName(rs.getString("name"));
					mProduct.setPrice(rs.getBigDecimal("price"));
					mProduct.setUpc(rs.getString("upc"));
					mProduct.setCategory(new Category(rs.getString("category_id"), rs.getString("name")));
					mProduct.setDescription(rs.getString("description"));
					
					product = new InventoryProduct(mProduct);
					product.setStock(rs.getInt("stock"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		ConnectionManager.close();
		return product;
	}

	@Override
	public boolean addProduct(Product product) {
		// TODO Auto-generated method stub
		if(ConnectionManager.getConnection() != null){
			PreparedStatement stmt;
			
			try {
				stmt = ConnectionManager.prepareStatement(ADD_PRODUCT);
				stmt.setString(1, product.getCategory().getCategoryId());
				stmt.setString(2, product.getUpc());
				stmt.setString(3, product.getName());
				stmt.setString(4, product.getDescription());
				stmt.setBigDecimal(5, product.getPrice());
				
				stmt.executeUpdate();
				
				ConnectionManager.close();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		
		ConnectionManager.close();
		return false;
	}

	@Override
	public boolean addProductStock(InventoryProduct inventoryProduct) {
		// TODO Auto-generated method stub
		if(ConnectionManager.getConnection() != null){
			PreparedStatement stmt;
			
			try {
				stmt = ConnectionManager.prepareStatement(ADD_PRODUCT_STOCK);
				stmt.setString(1, inventoryProduct.getUpc());
				stmt.setInt(2, inventoryProduct.getStock());
				
				stmt.executeUpdate();
				
				ConnectionManager.close();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		ConnectionManager.close();
		return false;
	}

}
