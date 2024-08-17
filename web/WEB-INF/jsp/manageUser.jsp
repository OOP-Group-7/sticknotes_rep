<!-- manageUsers.jsp -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Users</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            text-align: center;
        }
        main {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            width: 60%;
            margin: 50px auto;
            text-align: left;
        }
        h2 {
            color: #333333;
            margin-top: 0;
        }
        .link-button {
            display: inline-block;
            background-color: #007bff;
            color: #ffffff;
            padding: 10px 20px;
            border-radius: 4px;
            text-decoration: none;
            font-size: 16px;
            margin: 10px;
        }
        .link-button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <jsp:include page="header.jsp" />
    <jsp:include page="aside.jsp" />

    <main>
        <h2>Manage Users</h2>
        <a href="viewUserRegistered.htm" class="link-button">View Registered Users</a>
        <a href="approveUserServlet" class="link-button">View User Accounts</a>
    </main>

    <jsp:include page="footer.jsp" />
</body>
</html>
