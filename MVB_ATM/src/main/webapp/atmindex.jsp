<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>ATM Menu</title>
  <style>
    * {
      box-sizing: border-box;
      font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
    }

    body {
      margin: 0;
      background-color: #002b45;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      color: #ffffff;
    }

    .atm-screen {
      background-color: #f7f9fc;
      width: 600px;
      padding: 40px;
      border-radius: 20px;
      box-shadow: 0 0 40px rgba(0, 0, 0, 0.6);
      text-align: center;
    }

    .atm-screen h1 {
      margin-bottom: 20px;
      font-size: 28px;
      color: #002b45;
    }
    
      .atm-screen h1 span {
      font-family: 'MV Boli', cursive;
      color: deepskyblue;
      font-size: 40px;
    }

    .button-grid {
      display: grid;
      grid-template-columns: 1fr 1fr;
      gap: 20px;
      margin-bottom: 30px;
    }

    .atm-button {
      display: inline-block;
      width: 100%;
      padding: 25px 0;
      font-size: 20px;
      border: none;
      border-radius: 12px;
      background: linear-gradient(135deg, #0078d7, #005fa3);
      color: white;
      text-decoration: none;
      text-align: center;
      transition: transform 0.2s, background 0.3s;
    }

    .atm-button:hover {
      transform: scale(1.03);
      background: linear-gradient(135deg, #005fa3, #004080);
    }

    .logout-wrapper {
      width: 48%; 
      margin: 0 auto;
    }

    .atm-button.logout {
      background: linear-gradient(135deg, #d70000, #a30000);
    }

    .atm-button.logout:hover {
      background: linear-gradient(135deg, #a30000, #800000);
    }

    .footer {
      margin-top: 40px;
      font-size: 14px;
      color: #777;
    }
  </style>
</head>
<body>
  <div class="atm-screen">
  <h1>Welcome <span><%= session.getAttribute("username") %></span></h1>
    <h1>Select Your Transaction</h1>

    <div class="button-grid">
      <a class="atm-button" href="withdraw.jsp">Withdraw</a>
      <a class="atm-button" href="deposit.jsp">Deposit</a>
      <a class="atm-button" href="balanceenquiry">Balance Enquiry</a>
      <a class="atm-button" href="changepin.jsp">Change PIN</a>
    </div>

    <div class="logout-wrapper">
  <a class="atm-button logout" href="userauthentication.jsp">Logout</a>
</div>


    <div class="footer">
      © 2025 SecureATM Systems. All Rights Reserved.
    </div>
  </div>
</body>
</html>
