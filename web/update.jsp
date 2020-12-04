<%-- 
    Document   : update
    Created on : Oct 13, 2020, 1:40:11 PM
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
        <h1>Update</h1>
        <form action="Update" method="POST" enctype="multipart/form-data">
            <input type="text" name="txtIdCake" value="${sessionScope.CAKES.cakeId} ${requestScope.CI}" readonly="true"/><br/>
            <input type="text" name="txCakeName" placeholder="Cake Name" value="${requestScope.CN}" required=""/><br/>
            <input type="number" name="txCakePrice" placeholder="Price" vale=""${requestScope.CP}" required=""/><br/>
            <font style="color: tomato">${requestScope.INVALID}</font><br/>
            <input type="text" name="txCakeDes" placeholder="Cake Description" value="${requestScope.CD}" required=""/><br/>
            <input type="number" name="txCakeQuantity" placeholder="Quantity" required=""/><br/>
            <font style="color: tomato">${requestScope.INVALID1}</font><br/>
            <label>
                <input type="file" name="txFile" style="display: none"/>
                Upload Image
            </label><br/>
            <input type="date" name="txCreateDate" placeholder="Create Date" required=""/><br/>
            <input type="date" name="txExpireDate" placeholder="Expire Date" required=""/><br/>
            <font style="color: tomato">${requestScope.INVALID2}</font><br/>
            <select name="catego">
                <c:forEach var="dto" items="${sessionScope.CATEGORY}">
                    <option value ="${dto.idCakeCategory}">${dto.categoryName}</option>
                </c:forEach>
            </select><br/>
            <select name="status">
                <option value ="ACTIVE">ACTIVE</option>
                <option value ="SOLDOUT">SOLDOUT</option>
            </select><br/>
            <input type="hidden" name="getrole" value="${sessionScope.IDU}"/>
            <button type="submit" value="Updatingg" name="acion">Update</button>
        </form>
    </body>
</html>
