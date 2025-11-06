<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Balance Enquiry</title>
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

    .balance-amount {
      font-size: 28px;
      color: #004080;
      font-weight: bold;
      background-color: #e0ecf8;
      padding: 20px;
      border-radius: 12px;
      display: inline-block;
      margin-top: 20px;
    }
  </style>
</head>
<body>
  <div class="atm-screen">
    <h1>Available Balance</h1>
    <div class="balance-amount">
      ₹ <%= request.getAttribute("balance") != null ? request.getAttribute("balance") : "" %>
    </div>
  </div>
</body>
</html>
