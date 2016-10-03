 package com.qbryx.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbryx.domain.CartProduct;
import com.qbryx.managers.RequestDispatcherManager;
import com.qbryx.util.ServiceFactory;

/**
 * Servlet implementation class CheckOutServlet
 */
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckOutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cartId = request.getParameter("cartId");
		List<String> invalidProducts = ServiceFactory.customerService().checkout(cartId);
		
		if(invalidProducts.isEmpty()){
			request.setAttribute("checkoutSuccess", true);
			request.setAttribute("categories", ServiceFactory.productService().getCategories());
			RequestDispatcherManager.dispatch(this, "/customer_home.jsp", request, response);
		}else{
			response.sendRedirect("insufficient_stock.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
