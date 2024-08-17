<!-- registerSuccess.jsp -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Successful</title>
    <script>
        function showAlert() {
            alert("You have registered successfully!");
        }
    </script>
     <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            text-align: center; /* Center align the content */
        }
        header {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            width: 100%;
                 /* Center the header horizontally and add margin on top */
        }
        p {
            color: #333333;
            margin: 0;
        }
        </style>
</head>
<body onload="showAlert()">
    <h1>Registration Successful</h1>
    <p>You have been successfully registered. <a href="index.htm">Return to Home</a></p>
</body>
</html>
