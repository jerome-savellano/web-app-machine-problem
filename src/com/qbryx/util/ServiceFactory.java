package com.qbryx.util;

import com.qbryx.service.CustomerService;
import com.qbryx.service.CustomerServiceImpl;
import com.qbryx.service.ManagerService;
import com.qbryx.service.ManagerServiceImpl;
import com.qbryx.service.ProductService;
import com.qbryx.service.ProductServiceImpl;
import com.qbryx.service.UserService;
import com.qbryx.service.UserServiceImpl;

public class ServiceFactory {

	public static UserService userService(){
		return new UserServiceImpl();
	}
	
	public static ProductService productService(){
		return new ProductServiceImpl();
	}
	
	public static CustomerService customerService(){
		return new CustomerServiceImpl();
	}
	
	public static ManagerService managerService(){
		return new ManagerServiceImpl();
	}
}
