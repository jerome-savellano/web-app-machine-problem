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
 * Servlet implementation class ViewProductServlet
 */
public class ViewProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewProductServlet() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		String category = request.getParameter("category");
		boolean invalidCategorySelected = false;
		boolean categorySelected = true;
		CartHelper cartHelper = new CartHelper();

		cartHelper.populateCartInLayout(customer.getCartId(), request);
		request.setAttribute("categories", ServiceFactory.productService().getCategories());

		if (category != null) {
			categorySelected = true;

			request.setAttribute("categorySelected", categorySelected);
			request.setAttribute("category", category);
			request.setAttribute("products", ServiceFactory.productService().getProductsByCategory(category));
			RequestDispatcherManager.dispatch(this, Path.CUSTOMER_ROOT_PATH + "customer_home.jsp", request, response);
		} else {
			invalidCategorySelected = true;

			request.setAttribute("invalidCategorySelected", invalidCategorySelected);
			RequestDispatcherManager.dispatch(this, Path.CUSTOMER_ROOT_PATH + "customer_home.jsp", request, response);
		}
	}

}
