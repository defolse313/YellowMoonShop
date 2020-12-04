<%-- 
    Document   : addCake
    Created on : Oct 12, 2020, 11:52:18 PM
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
        <h1>Add new Cake</h1>
        <form action="AddCake" method="POST" enctype="multipart/form-data">
            <input type="text" name="txtCakeId" placeholder="Cake Id" value="${requestScope.CI}" required=""/><br/>
            <input type="text" name="txtCakeName" placeholder="Cake Name" value="${requestScope.CN}" required=""/><br/>
            <input type="number" name="txtCakePrice" placeholder="Price" vale=""${requestScope.CP}" required=""/><br/>
             <font style="color: tomato">${requestScope.INVALID}</font><br/>
            <input type="text" name="txtCakeDes" placeholder="Cake Description" value="${requestScope.CD}" required=""/><br/>
            <input type="number" name="txtCakeQuantity" placeholder="Quantity" required=""/><br/>
            <font style="color: tomato">${requestScope.INVALID1}</font><br/>
            <label>
                <input type="file" name="txtFile" style="display: none"/>
                Upload Image
            </label><br/>
            <input type="date" name="txtExpireDate" placeholder="Expire Date" required=""/><br/>
            <select name="cate">
                    <c:forEach var="dto" items="${sessionScope.CATEGORY}">
                        <option value ="${dto.idCakeCategory}">${dto.categoryName}</option>
                    </c:forEach>
            </select>
<!--            <input type="text" name="txtCategoryId" placeholder="Category Id" required=""/><br/>-->
            <button type="submit" value="Addd" name="acion">Create</button>
        </form>

    </body>
</html>
