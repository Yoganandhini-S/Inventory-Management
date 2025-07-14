<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
    <style>
        body {
            font-family: Arial;
            background: url('https://theramreview.com/wp-content/uploads/2022/03/AdobeStock_482132909-1080x675.jpeg') no-repeat center center fixed;
            background-size: cover;
            padding: 40px;
        }
        form {
            width: 400px;
            margin: auto;
            background: white;
            padding: 50px;
            box-shadow: 0 0 10px #aaa;
            border-radius: 10px;
        }
        input {
            width: 100%;
            padding: 10px;
            margin-top: 10px;
            margin-bottom: 20px;
        }
        input[type=submit] {
            background: #2ecc71;
            color: white;
            border: none;
            cursor: pointer;
        }
        .link {
            text-align: center;
        }
    </style>
    <script>
    <% if ("true".equals(request.getParameter("success"))) { %>
        alert("Registration successful! You can now log in.");
    <% } %>

    <% if ("exists".equals(request.getParameter("error"))) { %>
        alert("Username already exists. Please try a different one.");
    <% } %>
</script>
    
</head>
<body>

<h2 align="center">Create Account</h2>

<form method="post" action="/signup">
    <label>User name:</label>
    <input type="text" name="username" required>

    <label>Password:</label>
    <input type="password" name="password" required>

    <input type="submit" value="Sign Up">
</form>

<div class="link">
    <a href="/">Already have an account? Login</a>
</div>

</body>
</html>

