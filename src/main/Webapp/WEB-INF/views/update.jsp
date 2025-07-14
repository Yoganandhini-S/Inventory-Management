<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Item</title>
    <style>
        body {
            font-family: Arial;
            background: url('https://img.lovepik.com/bg/20240104/How-Drones-Can-Revolutionize-Warehouse-Inventory-Management_2680977_wh1200.jpg')no-repeat center center fixed;
            background-size:cover;
            padding: 30px;
        }
        form {
            background: white;
            padding: 20px;
            margin: auto;
            width: 300px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        input, button {
            width: 100%;
            padding: 10px;
            margin-top: 10px;
        }
        h2 { text-align: center; }
    </style>
</head>
<body>
<h2>Update Inventory Item</h2>
<form method="post" action="/updateItem">
    <label>Id:</label>
    <input type="hidden" name="id" value="${item.id}" />
    
     <label>Name:</label>
    <input type="text" name="name" value="${item.name}" placeholder="Name" required/>
     <label>Category:</label>
    <input type="text" name="category" value="${item.category}" placeholder="Category" required/>
     <label>Quantity:</label>
    <input type="number" name="quantity" value="${item.quantity}" placeholder="Quantity" required/>
     <label>Price:</label>
    <input type="number" name="price" value="${item.price}" placeholder="Price" required/>
    <button type="submit">Update</button>
    
</form>
</body>
</html>

