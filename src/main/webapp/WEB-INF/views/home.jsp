<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Home - Inventory System</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Segoe UI', sans-serif;
            background-image: url('https://images.ctfassets.net/00voh0j35590/a5HhCPfrJL8ze5FQ7ahD3/c3b1d7eedaf3a67411ae05b11d13d9ec/retail-blog-header-13.jpg');
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            min-height: 100vh;
            color: white;
            display: flex;
            flex-direction: column;
            align-items: center;
            overflow-y: auto;
        }

        header {
            background-color: rgba(0, 0, 0, 0.6);
            width: 100%;
            padding: 20px 0;
            text-align: center;
        }

        header h1 {
            margin: 0;
            font-size: 40px;
            text-shadow: 2px 2px 6px #000;
        }

        .nav-buttons {
            margin: 30px 0;
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 20px;
        }

        .nav-buttons a {
            text-decoration: none;
            background-color: rgba(255,255,255,0.9);
            color: #2c3e50;
            padding: 15px 30px;
            border-radius: 10px;
            font-size: 18px;
            font-weight: bold;
            transition: all 0.3s ease;
        }

        .nav-buttons a:hover {
            background-color: #2980b9;
            color: white;
        }

        .dashboard {
            background-color: rgba(0,0,0,0.6);
            padding: 30px;
            border-radius: 25px;
            max-width: 1000px;
            color: #f1f1f1;
            margin-bottom: 50px;
            box-shadow: 0 0 10px rgba(0,0,0,0.3);
        }

        .dashboard h2 {
            margin-bottom: 10px;
        }

        .dashboard ul {
            list-style: none;
            padding-left: 0;
        }

        .dashboard li {
            margin: 6px 0;
        }

       
    </style>
</head>
<body>
    <header>
        <h1>Inventory Management System</h1>
    </header>

    <div class="nav-buttons">
        <a href="/add">➕ Add Item</a>
        <a href="/inventory">📋 View Inventory</a>
        <a href="/logout">👤 Logout</a>
    </div>

    <div class="dashboard">
        <h2>📊 Inventory Summary</h2>
        <ul>
            <li>Total Items: <strong>${totalItems}</strong></li>
            <li>Total Quantity: <strong>${totalQuantity}</strong></li>
            <li>Total Value: <strong>₹${totalValue}</strong></li>
        </ul>

        <h2>🆕 Recently Added Items</h2>
        <ul>
            <c:forEach var="item" items="${recentItems}">
                <li>${item.name} - ${item.category} (${item.quantity})</li>
            </c:forEach>
        </ul>
    </div>

   
</body>
</html>
