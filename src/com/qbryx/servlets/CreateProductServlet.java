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
import com.qbryx.util.Parser;
import com.qbryx.util.ServiceFactory;

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
		String name = request.getParameter("name");
		String upc = request.getParameter("upc");
		String category = request.getParameter("category");
		String description = request.getParameter("description");
		String price = request.getParameter("price");
		String stock = request.getParameter("stock");
		
		Product product = new Product();
		product.setName(name);
		product.setUpc(upc);
		product.setCategory(ServiceFactory.productService().getCategory(category));
		product.setDescription(description);
		product.setPrice(new BigDecimal(price));
		
		InventoryProduct inventoryProduct = new InventoryProduct(product);
		inventoryProduct.setStock(Integer.parseInt(stock));
		
		boolean productCreated = ServiceFactory.managerService().addProduct(product, inventoryProduct);
		
		if(productCreated){
			request.setAttribute("wowowee", true);
			RequestDispatcherManager.dispatch(this, "/management_home.jsp", request, response);
		}
	}

}
