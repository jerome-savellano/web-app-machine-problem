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
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome!</title>
</head>
<body>
	<div class="container">
		<form action="logout">
			<h2>
				Welcome, ${manager.getUsername()}! <input type="submit"
					class="btn btn-warning btn-xs" value="Logout">
			</h2>
		</form>

		<ul class="nav nav-tabs">
			<li><a data-toggle="tab" href="#menu1">Search
					product by UPC</a></li>
			<li><a data-toggle="tab" href="management_home.jsp#menu2">Update
					product by category</a></li>
			<li><a data-toggle="tab" href="#menu3">Add a product</a></li>
		</ul>

		<div class="tab-content">
			<div id="menu1" class="tab-pane fade">
				<div class="container-fluid"
					style="padding-left: 20%; padding-right: 20%; padding-top: 2%;">
					<div class="row">
						<div class="col-md-12">
							<form action="search" method="post">
								<div class="form-group">
									<div class="input-group">
										<input type="number" name="upc" class="form-control"
											placeholder="Enter product UPC..." required> <span
											class="input-group-btn">
											<button class="btn btn-primary" type="submit">Go!</button>
										</span>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				<c:choose>
					<c:when test="${empty product && !productNotFound}">
					</c:when>
					<c:when test="${productNotFound}">
						<div class="alert alert-warning">
							<strong>Product not found!</strong> No product was found with the
							UPC indicated. Please check the UPC carefully.
						</div>
					</c:when>
					<c:otherwise>
						<div class="container-fluid">
							<div class="row">
								<div class="col-md-12" style="text-align: center">
									<h2 class="page-header">${product.getName()}</h2>
									<dl>
										<dt>
											<h4>
												<strong>Product price</strong>
											</h4>
										</dt>
										<dd>
											<h4>
												<i><span style="color: green;">&#8369;
														${product.getPrice()}</span>
											</h4>
											</i>
										</dd>
										<dt>
											<h4>
												<strong>UPC</strong>
											</h4>
										</dt>
										<dd>
											<h4>
												<i>${product.getUpc()}</i>
											</h4>
										</dd>
										<dt>
											<h4>
												<strong>Category</strong>
											</h4>
										</dt>
										<dd>
											<h4>
												<i>${category}</i>
											</h4>
										</dd>
										<dt>
											<h4>
												<strong>Description</strong>
											</h4>
										</dt>
										<dd>
											<h5>
												<i>${product.getDescription()}</i>
											</h5>
										</dd>
										<dt>
											<h4>
												<strong>Stock</strong>
											</h4>
										</dt>
										<dd>
											<h4>
												<i>${product.getStock()}</i>
											</h4>
										</dd>
									</dl>
								</div>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
			<div id="menu2" class="tab-pane fade">
				 <div class="container-fluid">
					<form method="post" action="management">
						<div class="row" style="padding: 2%;">
							<div class="col-md-8">
								<select class="form-control" name="category">
									<option selected disabled>SELECT CATEGORY</option>
									<c:forEach items="${categories}" var="item">
										<option>${item.getName()}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-md-4">
								<input type="submit" class="btn btn-info" value="VIEW CATEGORY">
							</div>
						</div>
					</form>
				</div>
			</div>
			<div id="menu3" class="tab-pane fade">
				<div class="container-fluid" style="padding: 2%;">
					<div class="row">
						<div class="col-md-12"
							style="padding-left: 15%; padding-right: 15%;">
							<form action="createProduct" method="post">
								<div class="form-group">
									<label for="Name">Name </label> <input type="text" name="name"
										class="form-control" required />
								</div>
								<div class="form-group">
									<label for="UPC">UPC </label> <input type="number" name="upc"
										class="form-control" required />
								</div>
								<div class="form-group">
									<div class="form-group">
										<label for="category">Category</label> <select
											class="form-control" name="category" required>
											<option selected disabled value="">SELECT CATEGORY</option>
											<c:forEach items="${categories}" var="item">
												<option>${item.getName()}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="description">Description</label>
									<textarea class="form-control" rows="5" name="description"></textarea>
								</div>
								<div class="form-group">
									<label for="price">Price </label> <input type="number"
										name="price" class="form-control" required />
								</div>
								<div class="form-group">
									<label for="stock">Stock </label> <input type="number"
										name="stock" class="form-control" required />
								</div>
								<button type="submit" class="btn btn-primary">Add
									product</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>