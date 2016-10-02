package com.qbryx.service;

import com.qbryx.dao.UserDao;
import com.qbryx.dao.UserDaoImpl;
import com.qbryx.domain.User;

public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	
	public UserServiceImpl(){
		this.userDao = new UserDaoImpl();
	}

	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return userDao.getUser(username);
	}

}
