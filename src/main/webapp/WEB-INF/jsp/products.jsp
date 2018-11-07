<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html lang="en">

<body>
    <h2>Product List</h2>
    <table>
        <tr>
            <th>Product</th>
            <th>Price</th>
        </tr>
        <c:if test="${not empty products}">
            <c:forEach var="product" items="${products}">
                <tr>
                    <td>${product.name}</td>
                    <td>${product.prices[currencylocale].amount}${currencylocale}</span></td>
                    <td><a href="/product/edit/${product.id}">Edit</a></td>
                 </tr>
            </c:forEach>
        </c:if>
    </table>

    <h2> Session currency selector </h2>
    <form:form action="/product/modifycurrency" method="POST" modelAttribute="currencyData">
        <table>
            <tr>
               <td><form:label path = "currencyCode">CurrencyCode </form:label></td>
               <td>
                  <form:select path = "currencyCode" id="currencyCode">
                     <form:option value="NONE" label="--- Select ---" />
                     <c:forEach items="${currencyCodeList}" var="currency">
                               <form:option value="${currency}">${currency}</form:option>
                     </c:forEach>
                  </form:select>
               </td>
            </tr>
			<tr>
				<td colspan="2">
				    <input type = "submit" value = "Submit"/>
				</td>
			</tr>
        </table>
    </form:form>

</body>
</html>