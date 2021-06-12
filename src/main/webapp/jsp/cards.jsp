<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Cards</title>
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
            <h1>Cards</h1>
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

            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Number</th>
                    <th>Balance</th>
                </tr>
                </thead>
                <tbody>

                    <tr>
                        <td>${card.number}</td>
                        <td>${card.balance}</td>
                    </tr>

                </tbody>
            </table>
            <a href="/replenishment" class="w3-bar-item w3-button">Add balance</a>
            <a href="/removal" class="w3-bar-item w3-button">Withdrawal of funds</a>

        </div>

    </div>


</div>
</body>
</html>