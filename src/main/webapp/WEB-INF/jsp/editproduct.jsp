<!DOCTYPE html>

<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html lang="en">
<head>
<style>
.error {
	color: #ff0000;
}
</style>
</head>

<body>
    <h2>Product Edit</h2>

    <spring:url value="/product/edit/${productData.id}" var="editProductUrl" />
    <spring:url value="/product/list" var="listProductUrl" />
    <form:form action="${editProductUrl}" method="POST" modelAttribute="productData">
        <table>
            <form:input type="hidden" path="id"/>
            <spring:bind path="name">
                <tr>
                    <td>Name :</td>
                    <td><form:input path="name" type="text" id="name" placeholder="Name" /></td>
                    <td><form:errors path="name" cssClass="error" /></td>
                </tr>
            </spring:bind>
            <spring:bind path="description">
                <tr>
                    <td>Description :</td>
                    <td><form:input path="description" type="text" id="description" placeholder="Description" /></td>
                    <td><form:errors path="description" cssClass="error" /></td>
                </tr>
            </spring:bind>
            <c:forEach items="${productData.prices}" varStatus="vs">
                <tr>
                    <spring:bind path="prices[${vs.index}].currency">
                            <td>Currency Code :</td>
                            <td><form:input path="prices[${vs.index}].currency" id="pricecurrency"/></td>
                            <td><form:errors path="prices[${vs.index}].currency" cssClass="error" /></td>
                    </spring:bind>
                    <spring:bind path="prices[${vs.index}].amount">
                            <td>Amount :</td>
                            <td><form:input path="prices[${vs.index}].amount" id="priceamount"/>
                            <form:errors path="prices[${vs.index}].amount" cssClass="error" /></td>
                    </spring:bind>
                </tr>
            </c:forEach>

            <td colspan="2"><input type="submit" /></td>
            <td colspan="2"><button type="cancel" onclick="location.href='${listProductUrl}'">Cancel</button></td>
        </table>
    </form:form>
</body>
</html>