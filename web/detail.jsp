<%-- 
    Document   : detail.jsp
    Created on : Oct 13, 2020, 1:02:26 PM
    Author     : hp
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div>
            <c:if test="${not empty requestScope.CAKE}">
                <div>${requestScope.CAKE.cakeId}</div>
                <div>${requestScope.CAKE.cakeName}</div>
                <div>${requestScope.CAKE.cakePrice}</div>
                <div>${requestScope.CAKE.cakeDescription}</div>
                <div>${requestScope.CAKE.cakeQuantity}</div>
                <div>${requestScope.CAKE.cakeCreateDate}</div>
                <div>${requestScope.CAKE.cakeExpiredDate}</div>
                <div><img src="images/${requestScope.CAKE.cakeImage}" height="30%" width="30%"/></div>
                <input type="hidden" name="cakeIdd" value="${requestScope.CAKE.cakeId}"/>
                <c:if test="${sessionScope.ROLE eq 'admin'}">
                    <input type="hidden" name="getrole" value="${sessionScope.USERNAME}"/>
                    <div><button type="submit" name="action"><a href="update.jsp">Update</a></button></div><br/>
                </c:if>
            </c:if>
                        <form action="Search" method="POST">
                            <button type="submit">Back to Home</button>
                        </form>
        </div>
    </body>
</html>
