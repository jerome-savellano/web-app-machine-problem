package com.qbryx.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbryx.managers.RequestDispatcherManager;
import com.qbryx.util.Path;
import com.qbryx.util.ServiceFactory;

/**
 * Servlet implementation class ManagementServlet
 */
public class ManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int viewFlag = (int) request.getSession().getAttribute("viewFlag");
		viewFlag = 1;
		
		request.setAttribute("viewFlag", viewFlag);
		request.setAttribute("categories", ServiceFactory.productService().getCategories());
		RequestDispatcherManager.dispatch(this, Path.MANAGEMENET_ROOT_PATH + "management_home.jsp", request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
