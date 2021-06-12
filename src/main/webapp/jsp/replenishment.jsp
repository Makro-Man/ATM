<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Add balance</title>

    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<div class="container">


    <!-- Sidebar -->
    <div class="w3-sidebar w3-light-grey w3-bar-block" style="width: 10%">
        <h3 class="w3-bar-item">Menu</h3>
        <a href="/home" class="w3-bar-item w3-button">Home</a> <a
            href="/create-card" class="w3-bar-item w3-button">Add Card</a>
        <a href="/cards" class="w3-bar-item w3-button">Cards</a>
        <a href="/replenishment/to/the/user" class="w3-bar-item w3-button">Replenishment to the user</a>
    </div>


    <!-- Page Content -->
    <div style="margin-left: 10%">
        <div class="w3-container w3-teal">
            <h1>Create new Card</h1>
        </div>
        <div class="w3-container">
            <c:if test="${pageContext.request.userPrincipal.name != null}">
            <form id="logoutForm" method="POST" action="${contextPath}/logout">
                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}" />
            </form>
            <h2>
                Welcome ${pageContext.request.userPrincipal.name} | <a
                    onclick="document.forms['logoutForm'].submit()">Logout</a>
            </h2>
            </c:if>

                <form:form method="POST" action="${contextPath}/replenishment" modelAttribute="replenishmentForm">
                        <form:select name = "category" path="number">
                                <form:option value="${card.number}">${card.number}</form:option>
                        </form:select>
                    <table>
                    <tr>
                        <td><form:label path="balance">Enter amount</form:label></td>
                        <td><form:input path="balance" /></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Submit" /></td>
                    </tr>
                </table>
                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}" />
                </form:form>
        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>


