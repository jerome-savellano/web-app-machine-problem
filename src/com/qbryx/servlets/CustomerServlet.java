package com.qbryx.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbryx.domain.Cart;
import com.qbryx.domain.Customer;
import com.qbryx.managers.RequestDispatcherManager;
import com.qbryx.util.ServiceFactory;

/**
 * Servlet implementation class CustomerServlet
 */
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		Cart cart = new Cart(customer.getCartId(), ServiceFactory.customerService().getProductsInCart(customer.getCartId()));
		
		request.setAttribute("categories", ServiceFactory.productService().getCategories());
		request.setAttribute("productsInCart", cart.getCartProducts());
		request.setAttribute("totalAmount", cart.getTotal());
		RequestDispatcherManager.dispatch(this, "/customer_home.jsp", request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		Cart cart = new Cart(customer.getCartId(), ServiceFactory.customerService().getProductsInCart(customer.getCartId()));
		String category = request.getParameter("category");
		boolean invalidCategorySelected = false;
		boolean categorySelected = true;
		
		if(category != null){
			categorySelected = true;
			
			request.setAttribute("categorySelected", categorySelected);
			request.setAttribute("category", category);
			request.setAttribute("products", ServiceFactory.productService().getProductsByCategory(category));
			request.setAttribute("categories", ServiceFactory.productService().getCategories());
			request.setAttribute("productsInCart", cart.getCartProducts());
			request.setAttribute("totalAmount", cart.getTotal());
			RequestDispatcherManager.dispatch(this, "/customer_home.jsp", request, response);
		}else{
			invalidCategorySelected = true;
			
			request.setAttribute("invalidCategorySelected", invalidCategorySelected);
			doGet(request, response);
		}
	}
}
