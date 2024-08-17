<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>User Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 20px;
        }
        .dashboard {
            width: 80%;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 5px;
            text-align: center;
        }
        h3 {
            color: #333;
        }
        button {
            padding: 10px 20px;
            margin: 10px;
            background-color: #007bff;
            border: none;
            border-radius: 3px;
            color: white;
            cursor: pointer;
            font-size: 16px;
        }
        button a {
            color: white;
            text-decoration: none;
        }
        button:hover {
            background-color: #0056b3;
        }
        table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        form {
            display: inline-block;
        }
        a {
            color: #007bff;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="dashboard">
    <center>
        <h3><b>Manage Your Sticky Notes</b></h3>
        
        <button id="newNoteBtn">New Sticky Note</button> 
        <button id="viewNote"><a href="viewNote.htm">View your Sticky Note</a></button>
        <button id="logout"><a href="logout.htm">Logout</a></button>

<%
session = request.getSession(false);
Integer userId = (Integer) session.getAttribute("userId");
%>
    <div>
        <table border="1" cellpadding="10" cellspacing="0">
            <thead>
                <tr>
                    <th>Note ID</th>
                    <th>Title</th>
                    <th>Content</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="note" items="${noteList}">
                    <tr>
                        <td>${note.id}</td>
                        <td>${note.title}</td>
                        <td>${note.content}</td>
                        <td>
                            <form action="editNote.htm" method="get" style="display:inline;">
                                <input type="hidden" name="id" value="${note.id}" />
                                <button type="submit">Edit</button>
                            </form>
                            <form action="deleteNote.htm" method="get" style="display:inline;">
                                <input type="hidden" name="id" value="${note.id}" />
                                <button type="submit" onclick="return confirm('Are you sure you want to delete this note?');">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <br><br><a href="dashboard.htm">Back</a>
    <script>
        document.getElementById('newNoteBtn').addEventListener('click', function () {
            document.getElementById('stickyNoteEditor').style.display = 'block';
        });
    </script>
    </center>
</div>

</body>
</html>
