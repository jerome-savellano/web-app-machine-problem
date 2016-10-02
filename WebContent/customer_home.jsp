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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome!</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-8">
				<form action="logout">
					<h1 class="page-header">
						Hi, ${customer.getUsername()}! <input type="submit"
							class="btn btn-warning btn-xs" value="Logout">

					</h1>
				</form>
				<c:choose>
					<c:when test="${empty productsInCart}">
						<h1 class="text-center text-muted">Your cart is empty. Start
							shopping!</h1>
					</c:when>
					<c:otherwise>
						<h3 class="text-success">Products in cart</h3>
						<form class="form-inline" action="checkout">
							<div class="form-group">
								<input type="hidden" name="cartId"
									value="${customer.getCartId()}"> <label for="email"><h4>Total
										Amount:</h4></label> <input type="text" class="form-control"
									name="totalAmount" value="&#8369; ${totalAmount}" disabled>
							</div>
							<button type="submit" class="btn btn-primary">Checkout</button>
						</form>
						<form action="removeProduct" method="post">
							<table class="table">
								<thead>
									<tr>
										<th>Name</th>
										<th>Quantity</th>
										<th>Amount</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${productsInCart}" var="product"
										varStatus="status">
										<tr class="active">
											<input type="hidden" class="form-control" name="upc"
												value="${product.getUpc()}">
											<td>${product.getName()}</td>
											<td>${product.getQuantity()}</td>
											<td style="color: green;">&#8369; ${product.getTotal()}</td>
											<td><input type="submit" class="btn btn-warning btn-xs"
												value="Remove from cart"></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</form>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="col-md-4">
				<h1 class="page-header">Browse products</h1>
				<c:if test="${invalidCategorySelected}">
					<div class="alert alert-danger">
						<strong>Oops!</strong> Please select a category.
					</div>
				</c:if>
				<form method="post" action="customer" class="form-inline">
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
									href="processProduct?upc=${item.getUpc()}&category=${category}"
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