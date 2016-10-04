package com.qbryx.servlets;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbryx.domain.Category;
import com.qbryx.domain.InventoryProduct;
import com.qbryx.domain.Product;
import com.qbryx.managers.RequestDispatcherManager;
import com.qbryx.util.ServiceFactory;
import com.qbryx.util.ViewFlag;

/**
 * Servlet implementation class CreateProductServlet
 */
public class CreateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateProductServlet() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String upc = request.getParameter("upc");
		Category category = ServiceFactory.productService().getCategory(request.getParameter("category"));
		String description = request.getParameter("description");
		String price = request.getParameter("price");
		String stock = request.getParameter("stock");

		Product product = new Product(upc, category, name, description, new BigDecimal(price));
		InventoryProduct inventoryProduct = new InventoryProduct(product, Integer.parseInt(stock));

		ServiceFactory.managerService().addProduct(inventoryProduct);

		request.setAttribute("categories", ServiceFactory.productService().getCategories());
		request.setAttribute("productCreated", true);
		request.setAttribute("viewFlag", ViewFlag.setFlag(request, 3));
		RequestDispatcherManager.dispatch(this, "/management_home.jsp", request, response);

	}

}
