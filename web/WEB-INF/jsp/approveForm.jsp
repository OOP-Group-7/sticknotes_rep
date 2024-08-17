<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome To SNMS</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
        }
        .container {
            padding: 20px;
            margin: 20px auto;
            width: 300px;
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        label {
            margin-top: 10px;
            display: block;
            color: #333;
        }
        input[type="text"], input[type="email"], input[type="password"] {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }
        .button-container {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
        }
        input[type="submit"], input[type="button"] {
            width: 48%;
            padding: 10px;
            background-color: #007bff;
            border: none;
            border-radius: 3px;
            color: white;
            cursor: pointer;
            font-size: 16px;
            text-align: center;
        }
        input[type="submit"]:hover, input[type="button"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp" />
<jsp:include page="aside.jsp" />
<main>
    <h2>Admin Dashboard</h2>

    <div class="container">
        <h2>Approve Normal User</h2>
        
        <form action="approveForm.htm" method="post">
            <input type="hidden" id="userid" name="userid" value="${userData[0].userid}" required>
            
            <label for="email">Email</label>
            <input type="email" id="email" name="email" value="${userData[0].email}" required>
            
            <label for="username">Username</label>
            <input type="text" id="username" name="username" required>
            
            <label for="password">Password</label>
            <input type="password" id="password" name="password" required>
            
            <label for="status">Status</label>
            
            <input type="text" id="status" name="status" value="${userData[0].status}" required>
            
            <div class="button-container">
                <input type="submit" value="Approve">
                <input type="button" value="Back" onclick="window.history.back()">
            </div>
        </form>
    </div>
</main>
<jsp:include page="footer.jsp" />
</body>
</html>
