<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customers list</title>

<link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">

</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>

	<div id="cotainer">
		<div id="content">

			<input type="button" value="Add Customer"
				onclick="window.location.href='showFormForAdd';return false;"
				class="add.button" />


			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				<!--  loop over and print our customers -->

				<c:forEach var="tempCustomer" items="${customers}">

					<!-- construct an "update" link with customerid -->
					<c:url var="updateLink" value="/customer/showFormForUpdate">
						<c:param name="customerId" value="${tempCustomer.id}" />
					</c:url>

					<!-- construct an "delete" link with customerid -->
					<c:url var="deleteLink" value="/customer/Delete">
						<c:param name="customerId" value="${tempCustomer.id}" />
					</c:url>
					<tr>
						<td>${tempCustomer.firstName}</td>
						<td>${tempCustomer.lastName}</td>
						<td>${tempCustomer.email}</td>
						<td>
							<!-- display the update link --> <a href="${updateLink}">Update</a>
							|
							<a href="${deleteLink}"
								onclick="if(!(confirm('Are sure you want to delete this customer'))) return false">Delete</a>
						</td>
						
					</tr>

				</c:forEach>
			</table>
		</div>
	</div>

</body>
</html>