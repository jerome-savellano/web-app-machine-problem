package com.qbryx.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbryx.domain.Customer;
import com.qbryx.exception.InsufficientStockException;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Customer customer = (Customer) request.getSession().getAttribute("customer");

		String cartId = customer.getCartId();
		String upc = request.getParameter("upc");

		try {

			ServiceFactory.customerService().checkout(cartId);
			request.setAttribute("checkoutSuccess", true);
			request.setAttribute("categories", ServiceFactory.productService().getCategories());
			request.setAttribute("product", ServiceFactory.productService().getProductByUpc(upc));
			request.setAttribute("quantity",
					ServiceFactory.customerService().getQuantityOfProductInCart(customer.getCartId(), upc));
			RequestDispatcherManager.dispatch(this, "/customer", request, response);
		} catch (InsufficientStockException e) {
			response.sendRedirect("insufficient_stock.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
