package com.qbryx.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbryx.domain.Customer;
import com.qbryx.managers.RequestDispatcherManager;
import com.qbryx.util.ServiceFactory;

/**
 * Servlet implementation class RemoveFromCartServlet
 */
public class RemoveFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveFromCartServlet() {
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
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		
		String cartId = customer.getCartId();
		String upc = request.getParameter("upc");

		boolean success = ServiceFactory.customerService().removeProductInCart(cartId, upc);
		
		if(success){
			response.sendRedirect("customer");
		}
	}

}
