<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">

<body>
    <h2>Product Details for productId : ${productId}</h2>
    <table>
        <tr>
            <th>Product</th>
            <th>Price</th>
        </tr>
        <c:if test="${not empty product.name}">
            <tr>
                <td>${product.name}</td>
                <td>${product.prices[currencylocale].amount}${currencylocale}</span></td>
             </tr>
         </c:if>
    </table>

    <a href="/">Go Home</a>
</body>
</html>