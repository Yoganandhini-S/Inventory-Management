<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Inventory</title>
    <style>
        body {
            font-family: Arial;
            background: url('https://www.extensiv.com/hubfs/ecommerce-shopping-cart-with-money-1.png')no-repeat center center fixed;
            background-size:cover;
            padding: 30px;
        }
        h1 { text-align: center; }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background: white;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        th, td {
            padding: 12px;
            border: 1px solid #ccc;
        }
        th { background-color: #f9f9f9; }
        a.btn, button {
            padding: 6px 12px;
            margin: 2px;
            border: none;
            background: #3498db;
            color: white;
            cursor: pointer;
            text-decoration: none;
            border-radius: 5px;
        }
        a.logout {
            float: right;
            margin-bottom: 10px;
            background: #e74c3c;
        }
    </style>
</head>
<body>
    <h1>Inventory Management</h1>
    <a href="/add" class="btn" style="background: #2ecc71;">Add Item</a>
    <a href="/logout" class="btn logout">Logout</a>
    <table>
        <thead>
            <tr>
                  <th>S.NO</th><th>Name</th><th>Category</th><th>Quantity</th><th>Price</th><th>Action</th>
            </tr>
        </thead>
        <tbody>
           <c:forEach var="item" items="${items}" varStatus="status">
    <tr>
        <td>${status.index + 1}</td> 
        <td>${item.name}</td>
        <td>${item.category}</td>
        <td>${item.quantity}</td>
        <td>${item.price}</td>
        <td>
            <a href="/update?id=${item.id}" class="btn">Update</a>
            <a href="/delete?id=${item.id}" class="btn" style="background: #e74c3c;">Delete</a>
        </td>
    </tr>
</c:forEach>
           
        </tbody>
    </table>
</body>
</html>
