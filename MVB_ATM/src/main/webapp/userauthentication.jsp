<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>ATM Login</title>
  <style>
    body {
      margin: 0;
      background-color: #002b45;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
    }

    .login-container {
      background-color: #f7f9fc;
      padding: 40px;
      border-radius: 20px;
      box-shadow: 0 0 40px rgba(0, 0, 0, 0.6);
      width: 400px;
      text-align: center;
    }

    h1 {
      color: #002b45;
      margin-bottom: 30px;
    }

    input[type="text"], input[type="password"] {
      width: 100%;
      padding: 14px;
      margin: 10px 0;
      border-radius: 10px;
      border: 1px solid #ccc;
      font-size: 16px;
    }

    button {
      background: linear-gradient(135deg, #0078d7, #005fa3);
      color: white;
      padding: 14px 30px;
      font-size: 18px;
      border: none;
      border-radius: 10px;
      cursor: pointer;
      margin-top: 20px;
      width: 100%;
    }

    button:hover {
      background: linear-gradient(135deg, #005fa3, #004080);
    }
  </style>
</head>
<body>
  <div class="login-container">
    <h1>Welcome to MVB ATM</h1>
    <form action="userauthentication" method="post">
      <input type="text" name="cardNumber" placeholder="Card Number" required />
      <input type="password" name="pin" placeholder="PIN" required />
      <button type="submit">Login</button>
    </form>
  </div>
</body>
</html>
