<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>User Dashboard</title>
   
</head>
<body>
    
<div class="dashboard">
    <center>
        <h3>Update your Sticky Notes</h3>

    <div class="sticky-note-editor" id="stickyNoteEditor">
        <h3>Create/Edit Sticky Note</h3>
        
        <form action="editNote.htm" method="POST">
            <input type="hidden" id="id" name="id" value="${noteContent[0].id}" required>
            <label for="noteTitle">Title</label>
            <input type="text" id="noteTitle" name="title" value="${noteContent[0].title}" required>

            <label for="noteContent">Content</label>
            <textarea id="noteContent" name="content" value="${noteContent[0].content}" rows="4" required></textarea>

            <button type="submit">Save</button>
        </form>
   </div>
<br><br><a href="viewNote.htm">Back</a>

</center>
</body>
</html>
