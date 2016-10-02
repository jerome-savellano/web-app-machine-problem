package com.qbryx.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qbryx.domain.User;
import com.qbryx.managers.ConnectionManager;

public class UserDaoImpl implements UserDao {
	
	private static final String GET_USER = "select id, user_type, username, password from user where username = ?";
	private static final String GET_CART_ID = "select cart_id from customer where user_id = ?";

	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		User user = null;
		
		if(ConnectionManager.getConnection() != null){
			PreparedStatement stmt;
				
			try {
				stmt = ConnectionManager.prepareStatement(GET_USER);
				stmt.setString(1, username);
				
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next()){
					user = new User(rs.getInt("id"), rs.getInt("user_type"), rs.getString("username"), rs.getString("password"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		ConnectionManager.close();
		return user;
	}

	@Override
	public String getCartId(int userId) {
		// TODO Auto-generated method stub
		String cartId = "";
		
		if(ConnectionManager.getConnection() != null){
			PreparedStatement stmt;
			
			
			try {
				stmt = ConnectionManager.prepareStatement(GET_CART_ID);
				stmt.setInt(1, userId);
				
				ResultSet rs = stmt.executeQuery();
				
				if(rs.next()){
					cartId = rs.getString("cart_id");
				}	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		ConnectionManager.close();
		return cartId;
	}

}
