<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .note-form {
            margin-bottom: 20px;
        }
        .sticky-notes {
            display: flex;
            flex-wrap: wrap;
        }
        .sticky-note {
            background-color: #ffeb3b;
            padding: 10px;
            margin: 10px;
            border-radius: 5px;
            width: 200px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            position: relative;
        }
        .sticky-note button {
            position: absolute;
            top: 5px;
            right: 5px;
            background-color: #f44336;
            color: white;
            border: none;
            border-radius: 50%;
            width: 20px;
            height: 20px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <h2>User Dashboard</h2>
    
    <div class="note-form">
        <form action="addNote" method="post">
            <input type="text" name="note" placeholder="Enter a new sticky note" required>
            <button type="submit">Add Note</button>
        </form>
    </div>

    <div class="sticky-notes">
        <c:forEach var="note" items="${stickyNotes}" varStatus="status">
            <div class="sticky-note">
                <p>${note}</p>
                <form action="deleteNote" method="post">
                    <input type="hidden" name="index" value="${status.index}">
                    <button type="submit">&times;</button>
                </form>
            </div>
        </c:forEach>
    </div>
</body>
</html>
