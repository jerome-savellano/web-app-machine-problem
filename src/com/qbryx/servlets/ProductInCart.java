package com.qbryx.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbryx.domain.CartProduct;
import com.qbryx.domain.Customer;
import com.qbryx.exception.InsufficientStockException;
import com.qbryx.util.Path;
import com.qbryx.util.ServiceFactory;

/**
 * Servlet implementation class ProductInCart
 */
public class ProductInCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductInCart() {
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
		CartProduct product = new CartProduct(
				ServiceFactory.productService().getProductByUpc(request.getParameter("upc")));
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		product.setQuantity(Integer.parseInt(request.getParameter("quantity")));

		String cartId = customer.getCartId();

		try {

			ServiceFactory.customerService().addProductInCart(product, cartId);
			response.sendRedirect(Path.CUSTOMER_ROOT_PATH + "success.jsp");
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
