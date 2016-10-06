package com.qbryx.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbryx.domain.InventoryProduct;
import com.qbryx.managers.RequestDispatcherManager;
import com.qbryx.util.Path;
import com.qbryx.util.ServiceFactory;
import com.qbryx.util.ViewFlag;

/**
 * Servlet implementation class ViewProductToUpdateServlet
 */
public class ViewProductToUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewProductToUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String upc = request.getParameter("upc");
		
		InventoryProduct product = (InventoryProduct) ServiceFactory.managerService().getProductByUpc(upc);

		request.setAttribute("product", product);
		RequestDispatcherManager.dispatch(this, Path.MANAGEMENET_ROOT_PATH + "update_product.jsp", request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String category = request.getParameter("category");
		request.setAttribute("viewFlag", ViewFlag.setFlag(request, 2));
		request.setAttribute("categories", ServiceFactory.productService().getCategories());
		
		if(category != null){
			request.setAttribute("productsByCategory",
					ServiceFactory.productService().getProductsByCategory(category));
			request.setAttribute("categorySelected", true);
			RequestDispatcherManager.dispatch(this, Path.MANAGEMENET_ROOT_PATH + "management_home.jsp", request, response);
		}else{
			request.setAttribute("invalidCategorySelected", true);
			RequestDispatcherManager.dispatch(this, Path.MANAGEMENET_ROOT_PATH + "management_home.jsp", request, response);
		}
	}
}
