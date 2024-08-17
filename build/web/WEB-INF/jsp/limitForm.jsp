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

    <div class="container">
        <h2>Set Limit of StickyNotes</h2>
        
        <form action="limitForm.htm" method="post">
            
            <input type="hidden" id="userId" name="userid" value="${limitation[0].userid}" required>
            <label for="limit">Sticky Note Limit:</label>
            <input type="number" id="limit" name="note_limit" value="${limitation[0].note_limit}" required>

    <input type="submit" value="Set Limit">
        </form>
        <form action="approveUserServlet" method="get">
            <input type="button" value="Back" onclick="window.history.back()">
        </form>
        
    </div>
   
    </main>
<jsp:include page="footer.jsp" />
</body>
</html>
