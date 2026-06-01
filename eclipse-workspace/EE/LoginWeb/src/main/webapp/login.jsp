<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="bootstrap.min.css"/>
    <!-- Load Poppins for improved typography (used by motion-theme.css) -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600;700&display=swap" rel="stylesheet">
<link rel="stylesheet" href="css/motion-theme.css"/>
<!-- Avoid forcing a transparent body background so the theme background/colors remain visible -->
<style>
  .login-wrap{ max-width:420px; margin:60px auto; }
</style>
</head>
<body>
  <div class="login-wrap ms-container">
    <div class="ms-card ms-appear">
      <h3>Login</h3>
      <form action="Log" method="post" class="ms-form">
        <div class="form-group">
          <label>Username</label>
          <input type="text" name="uname" class="form-control" required />
        </div>
        <div class="form-group">
          <label>Password</label>
          <input type="password" name="pwd" class="form-control" required />
        </div>
        <div style="display:flex;gap:10px;">
          <input type="submit" value="Login" class="ms-btn" />
          <a href="index.html" class="ms-btn secondary">Back</a>
        </div>
      </form>
    </div>
  </div>
  <script src="jquery.min.js"></script>
  <script src="bootstrap.min.js"></script>
  <script src="js/theme-toggle.js"></script>
  <script src="js/motion.js"></script>
</body>
</html>
