package com.qbryx.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbryx.domain.Product;
import com.qbryx.managers.RequestDispatcherManager;
import com.qbryx.util.Path;
import com.qbryx.util.ServiceFactory;
import com.qbryx.util.ViewFlag;

/**
 * Servlet implementation class SearchServlet
 */
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("management_home.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		boolean productNotFound = false;
		Product product = ServiceFactory.managerService().getProductByUpc(request.getParameter("upc"));
			
		request.setAttribute("categories", ServiceFactory.productService().getCategories());
		
		if(product == null){
			productNotFound = true;
		}
		
		request.setAttribute("viewFlag", ViewFlag.setFlag(request, 1));
		request.setAttribute("productNotFound", productNotFound);
		request.setAttribute("product", product);
		RequestDispatcherManager.dispatch(this, Path.MANAGEMENET_ROOT_PATH + "management_home.jsp", request, response);
	}

}
