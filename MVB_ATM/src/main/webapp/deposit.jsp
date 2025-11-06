<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Deposit</title>
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

    input[type="number"] {
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
    <h1>Deposit Amount</h1>
    <form action="atmdeposit" method="post">
      <input type="number" name="amount" placeholder="Enter amount" required />
      <button type="submit" class="atm-button">Deposit</button>
    </form>
  </div>
</body>
</html>
