<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Помилка</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">
    <div class="alert alert-danger">
        <h3>Помилка!</h3>
        <p>${error != null ? error : 'Невідома помилка'}</p>
        <a href="${pageContext.request.contextPath}/planes" class="btn btn-primary">На головну</a>
    </div>
</body>
</html>