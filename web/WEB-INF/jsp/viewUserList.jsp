<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
   
</head>
<body>
<jsp:include page="header.jsp" />
<jsp:include page="aside.jsp" />
<main>
    <h2>Admin Dashboard</h2>
    <%
    
    %>
    <center>
    <table class="dashboard-table" border="2">
        <thead>
            <tr>
                
                <th>Names</th>
                <th>Email</th>
                <th>username</th>
                <th>Password</th>
                <th>status</th>
                <th>Limit</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${secondList}" var="l">
            <tr>
                <td>${l.Names}</td>
                <td>${l.email}</td>
                <td>${l.username}</td>
                <td>${l.password}</td>
                <td>${l.status}</td>
                <td>${l.note_limit}</td>
                <td class="actions">
                    <a href="delete.htm?id=${l.userid}" class="approve">Delete</a>
                    <a href="limitForm.htm?id=${l.userid}" class="approve">Limit</a>
                     <a href="edit.htm?id=${l.userid}" class="approve">Edit</a>
                </td>
                    
                </td>
            </tr>
             </c:forEach>          <!-- Additional rows go here -->
        </tbody>
    </table></center><br>
    <form action="manageUser.htm" method="get">
            <input type="button" value="Go Back" onclick="window.history.back()">
        </form>
</main><br><br><br><br><br><br><br><br><br><br>
<jsp:include page="footer.jsp" />
</body>
</html>
