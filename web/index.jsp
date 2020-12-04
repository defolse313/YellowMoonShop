<%-- 
    Document   : index
    Created on : Oct 12, 2020, 11:25:07 AM
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
        <h1>Yellow Moon Shop</h1>
        <form action="Search" method="POST">
            <input type="text" name="txtSearch" placeholder="Type something..."/>
            <button type="submit" value="Searching" name="action">Search</button>
        </form>
        <c:if test="${sessionScope.ROLE == null}">
            Use our full functions? <a href="login.jsp">Login</a>
        </c:if>
        <c:if test="${sessionScope.ROLE != null}">
            Welcome, ${sessionScope.USERNAME}
            <form action="Logout" method="POST">
                Not ${sessionScope.USERNAME}? <button type="submit" value="Logingout" name="action">Log Out</button>
            </form>
        </c:if>
        <c:if test="${sessionScope.ROLE eq 'admin'}">
            <button><a href="addCake.jsp">Add Cake</a></button>
        </c:if>
        <c:if test="${requestScope.CATE != null}">
            <c:if test="${not empty requestScope.CATE}" var="checkList">
                <c:forEach var="dto" items="${requestScope.CATE}" varStatus="counter">
                    <form action="Search" method="POST">
                        <input type="hidden" name="Category" value="${dto.idCakeCategory}"/>
                        <button type="submit" value="Searchingg" name="action">
                            <div>
                                <div>${dto.categoryName}</div>
                            </div>
                        </button>
                    </form>
                </c:forEach>
            </c:if>
        </c:if>
        <form action="Search" method="POST">
            <input type="number" name="txtLowerRange" placeholder="Lower money range..." required=""/>
            <input type="number" name="txtUpperRange" placeholder="Upper money range..." required=""/>
            <button type="submit" value="Ranging" name="action">Find</button>
        </form>
        <c:if test="${sessionScope.ROLE ne 'admin'}">
            <a href="viewcart.jsp">View Your Cart</a>
        </c:if>
        <c:if test="${sessionScope.ROLE eq 'admin'}">
            <br/>
        </c:if>
        <c:if test="${requestScope.INFO != null}">
            <c:if test="${not empty requestScope.INFO}" var="checkList">
                <c:forEach var="dto" items="${requestScope.INFO}" varStatus="counter">
                    <form action="DetailsDisplay" method="POST">
                        <input type="hidden" name="cake" value="${dto.cakeId}"/>
                        <button type="submit" value="Details" name="action">
                            <div>
                                <div>Name: ${dto.cakeName}</div>
                                <div>Price: ${dto.cakePrice}</div>
                                <div>Type: ${dto.categoryId.categoryName}</div>
                            </div>
                            <div><img src="images/${dto.cakeImage}"></div>
                        </button>
                    </form>
                    <c:if test="${sessionScope.ROLE ne 'admin'}">
                        <form action="AddToCart" method="POST">
                            <input type="hidden" name="cakeid" value="${dto.cakeId}"/>
                            <input type="hidden" name="cakename" value="${dto.cakeName}"/>
                            <button type="submit" name="action">Add To Cart</button>
                        </form>
                    </c:if>
                </c:forEach>
            </c:if>
            <c:if test="${!checkList}">
                No record found
            </c:if>
        </c:if>

        <div>
            <c:forEach begin="1" end="${requestScope.CAKE_COUNT}" var="page" varStatus="counter">
                <c:url value="Search" var="pageNum">
                    <c:param name="pageIDPaging" value="${counter.count}"/>
                    <c:param name="txtSearch" value="${param.txtSearch}"/>
                    <c:param name="txtLowerRange" value="${param.txtLowerRange}"/>
                    <c:param name="txtUpperRange" value="${param.txtUpperRange}"/>
                </c:url>
                <a href="${pageScope.pageNum}">
                    <span class="page">${page}</span></a>
                </c:forEach>
        </div>
    </body>
</html>
