<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Change PIN</title>
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

    .atm-screen {
      background-color: #f7f9fc;
      width: 500px;
      padding: 40px;
      border-radius: 20px;
      box-shadow: 0 0 40px rgba(0, 0, 0, 0.6);
      text-align: center;
    }

    h1 {
      color: #002b45;
      margin-bottom: 30px;
    }

    input[type="password"] {
      width: 100%;
      padding: 15px;
      margin-bottom: 20px;
      border-radius: 10px;
      border: 1px solid #ccc;
      font-size: 18px;
    }

    .atm-button {
      width: 100%;
      padding: 18px;
      font-size: 18px;
      border: none;
      border-radius: 12px;
      background: linear-gradient(135deg, #0078d7, #005fa3);
      color: white;
      cursor: pointer;
      transition: 0.3s;
    }

    .atm-button:hover {
      background: linear-gradient(135deg, #005fa3, #004080);
    }
  </style>
</head>
<body>
  <div class="atm-screen">
    <h1>Change PIN</h1>
    <form action="pinchange" method="post">
      <input type="password" name="oldpin" placeholder="Old PIN" required />
      <input type="password" name="newpin" placeholder="New PIN" required />
      <button type="submit" class="atm-button">Change PIN</button>
    </form>
  </div>
</body>
</html>
