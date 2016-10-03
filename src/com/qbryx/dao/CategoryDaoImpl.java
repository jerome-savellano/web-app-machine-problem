package com.qbryx.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qbryx.domain.Category;
import com.qbryx.managers.ConnectionManager;

public class CategoryDaoImpl implements CategoryDao {
	
	private static final String GET_CATEGORIES = "select category_id, name from category";
	private static final String GET_CATEGORY = "select category_id, name from category where name = ?";

	@Override
	public List<Category> getCategories() {
		// TODO Auto-generated method stub
		List<Category> categories = new ArrayList<>();
		
		if(ConnectionManager.getConnection() != null){
			PreparedStatement stmt;
				
			try {
				stmt = ConnectionManager.prepareStatement(GET_CATEGORIES);
				
				ResultSet rs = stmt.executeQuery();
				while(rs.next()){
					Category category = new Category(rs.getString("category_id"), rs.getString("name"));
					categories.add(category);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		ConnectionManager.close();
		return categories;
	}

	@Override
	public Category getCategory(String categoryName) {
		// TODO Auto-generated method stub
		Category category = null;
		
		if(ConnectionManager.getConnection() != null){
			PreparedStatement stmt;
				
			try {
				stmt = ConnectionManager.prepareStatement(GET_CATEGORY);
				stmt.setString(1, categoryName);
				
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next()){
					category = new Category(rs.getString("category_id"), rs.getString("name"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		ConnectionManager.close();
		return category;
	}
}
