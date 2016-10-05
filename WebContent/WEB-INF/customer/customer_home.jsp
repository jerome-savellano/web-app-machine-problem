<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome!</title>
<script>
	window.setTimeout(function() {
		$(".alert-success").fadeTo(500, 0).slideUp(500, function() {
			$(this).remove();
		});
	}, 3000);
</script>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-8">
				<form action="${pageContext.request.contextPath}/logout">
					<h1 class="page-header">
						Hi,  ${customer.getUsername()}! <input type="submit"
							class="btn btn-warning btn-xs" value="Logout">
					</h1>
				</form>
				<jsp:include page="cart.jsp"></jsp:include>
			</div>
			<div class="col-md-4">
				<h1 class="page-header">Browse products</h1>
				<c:if test="${invalidCategorySelected}">
					<div class="alert alert-danger">
						<strong>Oops!</strong> Please select a category.
					</div>
				</c:if>
				<form method="post" action="${pageContext.request.contextPath}/customer/viewProduct" class="form-inline">
					<div class="form-group" style="padding: 0;">
						<select class="form-control" name="category">
							<option selected disabled>SELECT CATEGORY</option>
							<c:forEach items="${categories}" var="item">
								<option>${item.getName()}</option>
							</c:forEach>
						</select> <input type="submit" class="btn btn-info" value="VIEW CATEGORY">
					</div>
					<c:if test="${categorySelected}">
						<div class="list-group">
							<div class="page-header">
								<h1>${category}</h1>
							</div>
							<c:forEach items="${products}" var="item" varStatus="status">
								<a
									href="${pageContext.request.contextPath}/customer/processProduct?upc=${item.getUpc()}&category=${category}"
									class="list-group-item">${item.getName()}</a>
							</c:forEach>
						</div>
					</c:if>
				</form>
			</div>
		</div>
	</div>
</body>
</html>