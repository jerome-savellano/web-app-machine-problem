package com.qbryx.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbryx.domain.Cart;
import com.qbryx.domain.CartProduct;
import com.qbryx.domain.Customer;
import com.qbryx.managers.RequestDispatcherManager;
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
		// TODO Auto-generated method stub
		String upc = request.getParameter("upc");
		String category = request.getParameter("category");

		request.setAttribute("product", ServiceFactory.productService().getProductByUpc(upc));
		request.setAttribute("category", category);
		request.setAttribute("quantity", ServiceFactory.customerService()
				.getQuantityOfProductInCart(Customer.customer(request).getCartId(), upc));
		RequestDispatcherManager.dispatch(this, "/product.jsp", request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		CartProduct product = new CartProduct(
				ServiceFactory.productService().getProductByUpc(request.getParameter("upc")));
		product.setQuantity(Integer.parseInt(request.getParameter("quantity")));

		String cartId = Customer.customer(request).getCartId();
		boolean addProductToCart = ServiceFactory.customerService().addProductInCart(product, cartId);

		if (addProductToCart) {
			response.sendRedirect("success.jsp");
		} else {
			response.sendRedirect("insufficientstock.jsp");
		}
	}

}
