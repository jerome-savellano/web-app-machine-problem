package com.qbryx.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbryx.domain.Customer;
import com.qbryx.domain.User;
import com.qbryx.managers.RequestDispatcherManager;
import com.qbryx.util.ServiceFactory;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		boolean userDoesNotExist = false;
		
		User user = ServiceFactory.userService().getUser(username);
		
		if(user != null && user.getPassword().equals(password)){
			if(user.getUser_type() == 1){
				Customer customer = new Customer(user);
				customer.setCartId(ServiceFactory.customerService().getCartId(customer.getUserId()));

				request.getSession().setAttribute("customer", customer);
				response.sendRedirect("customer");
			}else{
				
			}
		}else{
			userDoesNotExist = true;
			
			request.setAttribute("userDoesNotExist", userDoesNotExist);
			request.setAttribute("username", username);
			RequestDispatcherManager.dispatch(this, "/login.jsp", request, response);
		}
	}

}
