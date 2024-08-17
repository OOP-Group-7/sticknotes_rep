<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 20px;
        }
        main {
            width: 80%;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        h2 {
            color: #333;
            text-align: center;
        }
        .dashboard-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        .dashboard-table th, .dashboard-table td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ddd;
        }
        .dashboard-table th {
            background-color: #007bff;
            color: white;
        }
        .dashboard-table tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        .actions {
            text-align: center;
        }
        .actions a.approve {
            display: inline-block;
            padding: 6px 12px;
            background-color: #28a745;
            color: white;
            text-decoration: none;
            border-radius: 3px;
            font-size: 14px;
        }
        .actions a.approve:hover {
            background-color: #218838;
        }
        input[type="button"] {
            display: block;
            margin: 20px auto;
            padding: 10px 20px;
            background-color: #007bff;
            border: none;
            border-radius: 3px;
            color: white;
            cursor: pointer;
            font-size: 16px;
        }
        input[type="button"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp" />
<jsp:include page="aside.jsp" />
<main>
    <h2>Admin Dashboard</h2>

    <table class="dashboard-table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Names</th>
                <th>Email</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${userList}" var="list">
                <tr>
                    <td>${list.userid}</td>
                    <td>${list.Names}</td>
                    <td>${list.email}</td>
                    <td>${list.status}</td>
                    <td class="actions">
                        <a href="approveForm.htm?id=${list.userid}" class="approve">Approve</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <form action="manageUser.htm" method="get">
        <input type="button" value="Go Back" onclick="window.history.back()">
    </form>
</main>
<jsp:include page="footer.jsp" />
</body>
</html>
