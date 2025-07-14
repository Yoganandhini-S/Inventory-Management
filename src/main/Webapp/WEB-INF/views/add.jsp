<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Inventory Item</title>
    <style>
        body {
            font-family: Arial;
            background: url('https://www.eazystock.com/wp-content/uploads/2022/11/Shopping-trolley-on-red-background-scaled.jpeg') no-repeat center center fixed;
            background-size: cover;
            padding: 40px;
        }
        form {
            width: 400px;
            margin: auto;
            background: white;
            padding: 20px;
            box-shadow: 0 0 10px #aaa;
            border-radius: 8px;
        }
        input, select {
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
        a.btn {
            display: inline-block;
            padding: 10px 15px;
            background: #3498db;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }
        .top-bar {
            text-align: center;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

<div class="top-bar">
    <a href="/inventory" class="btn">View Items</a>
    <a href="/logout" class="btn" style="background: #e74c3c;">Logout</a>
</div>

<h2 align="center">Add Inventory Item</h2>

<form method="post" action="/save">
    
    <label>Name:</label>
    <input type="text" name="name" required>

    <label>Category:</label>
    <input type="text" name="category" required>

    <label>Quantity:</label>
    <input type="number" name="quantity" required>

    <label>Price:</label>
    <input type="number" step="0.01" name="price" required>

    <input type="submit" value="Add Item">
</form>

</body>
</html>
