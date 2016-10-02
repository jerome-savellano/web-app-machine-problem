package com.qbryx.util;

import com.qbryx.dao.CartDao;
import com.qbryx.dao.CartDaoImpl;
import com.qbryx.dao.CategoryDao;
import com.qbryx.dao.CategoryDaoImpl;
import com.qbryx.dao.ProductDao;
import com.qbryx.dao.ProductDaoImpl;
import com.qbryx.dao.UserDao;
import com.qbryx.dao.UserDaoImpl;

public class DaoFactory {

	public static CartDao cartDao(){
		return new CartDaoImpl();
	}
	
	public static CategoryDao categoryDao(){
		return new CategoryDaoImpl();
	}
	
	public static ProductDao productDao(){
		return new ProductDaoImpl();
	}
	
	public static UserDao userDao(){
		return new UserDaoImpl();
	}
}
