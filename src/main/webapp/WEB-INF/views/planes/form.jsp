<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>${plane == null || plane.id == 0 ? 'Створення' : 'Редагування'} літака</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">

<h1>${plane == null || plane.id == 0 ? 'Створення літака' : 'Редагування літака'}</h1>

<c:if test="${not empty error}">
    <div class="alert alert-danger">${error}</div>
</c:if>

<form action="${pageContext.request.contextPath}/plane" method="post">

    <c:if test="${plane != null && plane.id != 0}">
        <input type="hidden" name="id" value="${plane.id}">
    </c:if>

    <div class="mb-3">
        <label class="form-label">Модель літака: <span class="text-danger">*</span></label>
        <input type="text" name="model" class="form-control" value="${plane.model}" style="width: 300px;">
    </div>

    <div class="mb-3">
        <label class="form-label">Авіакомпанія: <span class="text-danger">*</span></label>
        <select name="aviacompanyId" class="form-select" style="width: 300px;">
            <option value="">-- Оберіть авіакомпанію --</option>
            <c:forEach items="${aviacompanies}" var="company">
                <option value="${company.id}" ${plane.aviacompany != null && plane.aviacompany.id == company.id ? 'selected' : ''}>
                    ${company.name}
                </option>
            </c:forEach>
        </select>
    </div>

    <div class="mb-3">
        <label class="form-label">Виробник: <span class="text-danger">*</span></label>
        <select name="manufacturerId" class="form-select" style="width: 300px;">
            <option value="">-- Оберіть виробника --</option>
            <c:forEach items="${manufacturers}" var="man">
                <option value="${man.id}" ${plane.manufacturer != null && plane.manufacturer.id == man.id ? 'selected' : ''}>
                    ${man.name}
                </option>
            </c:forEach>
        </select>
    </div>

    <div class="mb-3">
        <label class="form-label">Місткість баку (л): <span class="text-danger">*</span></label>
        <input type="number" step="1" name="fuelCapacity" class="form-control" value="${plane.fuelCapacity}" style="width: 300px;">
    </div>

    <div class="mb-3">
        <label class="form-label">Макс. швидкість (км/год): <span class="text-danger">*</span></label>
        <input type="number" name="maxSpeed" class="form-control" value="${plane.maxSpeed}" style="width: 300px;">
    </div>

    <button type="submit" class="btn btn-primary">Зберегти</button>
    <a href="${pageContext.request.contextPath}/planes" class="btn btn-secondary">Назад</a>
</form>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>