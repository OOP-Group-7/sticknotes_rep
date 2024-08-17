<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>welcome to SNMS</title>
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
        .sticky-note-editor {
            display: none;
            margin-top: 20px;
            padding: 20px;
            background-color: #f2f2f2;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
            color: #333;
        }
        input[type="text"],
        textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }
        textarea {
            resize: none;
        }
    </style>
</head>
<body>
    
<div class="dashboard">
    <center>
        <h3><b>Add New Sticky Notes</b></h3>
        
        <button id="newNoteBtn">New Sticky Note</button> 
        <button id="viewNote"><a href="viewNote.htm">View your Sticky Note</a></button>
        <button id="logout"><a href="logout.htm">Logout</a></button>
       
<%
session = request.getSession(false);
Integer userId = (Integer) session.getAttribute("userId");
%>
    <div class="sticky-note-editor" id="stickyNoteEditor">
        <h3><b><u>Create Sticky Note</u></b></h3>
        
        <form action="dashboard.htm" method="POST">
            <input type="hidden" id="userid" name="userid" value="${userId}" required>
            <label for="noteTitle">Title</label>
            <input type="text" id="noteTitle" name="title" required>

            <label for="noteContent">Content</label>
            <textarea id="noteContent" name="content" rows="4" required></textarea>

            <button type="submit">Save</button>
        </form>
   </div>

<script>
    document.getElementById('newNoteBtn').addEventListener('click', function () {
        document.getElementById('stickyNoteEditor').style.display = 'block';
    });
</script>
</center>
</div>

</body>
</html>
