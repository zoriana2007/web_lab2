<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Управління літаками</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">

    <h1>Управління літаками</h1>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <a href="${pageContext.request.contextPath}/plane" class="btn btn-success mb-3">+ Новий літак</a>

    <table class="table table-bordered table-striped">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Модель</th>
                <th>Виробник</th>
                <th>Авіакомпанія</th>
                <th>Місткість баку</th>
                <th>Макс. швидкість</th>
                <th>Дії</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${planes}" var="plane">
                <tr>
                    <td>${plane.id}</td>
                    <td>${plane.model}</td>
                    <td>${plane.manufacturer != null ? plane.manufacturer.name : '-'}</td>
                    <td>${plane.aviacompany != null ? plane.aviacompany.name : '-'}</td>
                    <td>${plane.fuelCapacity}</td>
                    <td>${plane.maxSpeed}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/plane?id=${plane.id}" class="btn btn-sm btn-warning">Редагувати</a>
                        <form action="${pageContext.request.contextPath}/planes" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="${plane.id}">
                            <button type="submit" class="btn btn-sm btn-danger" onclick="return confirm('Видалити?')">Видалити</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>