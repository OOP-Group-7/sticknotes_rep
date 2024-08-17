<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error Page</title>
    <style>
        .error-container {
            padding: 20px;
            margin: 20px;
            border: 1px solid #e74c3c;
            background-color: #f8d7da;
            color: #721c24;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <!-- Display alert message if present -->
        <c:if test="${not empty message}">
            <script>
                alert("${message}");
            </script>
        </c:if>

        <h2>Error</h2>
        <p><c:out value="${message}" /></p>
    </div>
</body>
</html>
