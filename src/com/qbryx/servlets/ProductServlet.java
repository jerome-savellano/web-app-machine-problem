package com.qbryx.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbryx.domain.Customer;
import com.qbryx.helper.CartHelper;
import com.qbryx.managers.RequestDispatcherManager;
import com.qbryx.util.Path;
import com.qbryx.util.ServiceFactory;

/**
 * Servlet implementation class ProductServlet
 */
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Customer customer = (Customer) request.getSession().getAttribute("customer");
		String upc = request.getParameter("upc");
		String category = request.getParameter("category");

		CartHelper cartHelper = new CartHelper();

		cartHelper.populateCartInLayout(customer.getCartId(), request);
		request.setAttribute("product", ServiceFactory.productService().getProductByUpc(upc));
		request.setAttribute("category", category);
		request.setAttribute("quantity",
				ServiceFactory.customerService().getQuantityOfProductInCart(customer.getCartId(), upc));
		RequestDispatcherManager.dispatch(this, Path.CUSTOMER_ROOT_PATH + "product.jsp", request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
