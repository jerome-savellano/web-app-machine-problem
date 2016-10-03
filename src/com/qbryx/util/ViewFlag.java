package com.qbryx.util;

import javax.servlet.http.HttpServletRequest;

public class ViewFlag {

	public static int setFlag(HttpServletRequest request, int val){
		
		int viewFlag = (int) request.getSession().getAttribute("viewFlag");
		viewFlag = val;
		
		return viewFlag;
	}
}
