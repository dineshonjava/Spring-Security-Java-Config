<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Registration for DOJ Classes for Spring Course!!!</h1>
<form action="/mvc/register" method="post">
	Name: <input type="text" name="username">
	Email: <input type="text" name="email">
	Mobile: <input type="text" name="mobile">
	<input type="submit" value="Submit">
</form>
<c:if test="${not empty employee}">
	<div>Name: ${employee.username} | Email: ${employee.email} | Mobile: ${employee.mobile}</div>
</c:if>
