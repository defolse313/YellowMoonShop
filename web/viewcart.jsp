<%-- 
    Document   : viewcart
    Created on : Oct 14, 2020, 11:44:35 AM
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
        <h1>YOUR CART</h1>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${not empty cart.items}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Cake Name</th>
                        <th>Amount</th>
                        <th>Price</th>
                        <th>Total</th>
                        <th>Remove item(s)</th>
                    </tr>
                </thead>
                <tbody>
                <form id="del" action="RemoveCart" method="POST">
                    <c:forEach var="g" items="${cart.items}" varStatus="counter">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${g.key.cakeName}
                            </td>
                            <td>
                                <input type="number" name="txtQuantityOrder" value="${g.value}"/>
                            </td>
                            <td>
                                ${g.key.cakePrice}
                            </td>
                            <td>
                                ${g.key.cakePrice * g.value}
                            </td>
                            <td>
                                <input type="checkbox" name="chkitem" value="${g.key.cakeId}"/>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="4">
                            <button type="button" name="RemoveCart" onclick="checkDel()">Remove</button>
                        </td>
                        <td colspan="1">
                            ${sessionScope.TOTAL}
                        </td>
                    </tr>
                </form>
                <form action="Search" method="POST">
                    <input type="submit" value="Continue Shopping"/>
                </form> 
            </tbody>
        </table>
        <c:if test="${sessionScope.ROLE ne 'customer'}">
            <form action="AddOrder" method="POST">
                <input type="text" name="txtName" placeholder="Your name" required=""/>
                <input type="number" name="txtPhone" placeholder="Phone" required=""/>
                <input type="text" name="txtAddress" placeholder="Address" required=""/>
                <button type="submit">Confirm</button>
            </form>

        </c:if>
        <c:if test="${sessionScope.ROLE eq 'customer'}">
            <form action="AddOrder" method="POST">
                <button type="submit">Confirm</button>
            </form>
        </c:if>
    </c:if>
    <c:if test="${empty cart.items}">
        You have not added anything to your cart
        <form action="Search" method="POST">
            <input type="submit" value="Continue Shopping"/>
        </form> 
    </c:if>
    <script>
        function checkDel() {
            let confirmed = confirm("Do you really want to remove this cake from your cart");
            if (confirmed) {
                document.getElementById("del").submit();
            }
        }
    </script>
</body>
</html>
