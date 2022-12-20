<html>

<head>
<title>First Web Application</title>
<link th:href="/webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">

<style type="text/css">
	.login-form {
		width: 340px;
    	margin: 50px auto;
	}
    .login-form form {
    	margin-bottom: 15px;
        background: #f7f7f7;
        box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
        padding: 30px;
    }
    .login-form h2 {
        margin: 0 0 15px;
    }
    .form-control, .btn {
        min-height: 38px;
        border-radius: 2px;
    }
    .btn {
        font-size: 15px;
        font-weight: bold;
    }
</style>
</head>

<body>
	<div class="login-form">
		<font color="red">${errorMessage}</font>		
		<form method="post">
		 	<h2 class="text-center">Log in</h2>       
       		 <div class="form-group">
       			 <input type="text" class="form-control" placeholder="Username" required="required" name="name">
       		 </div><br>
       		 <div class="form-group">
            	<input type="password" class="form-control" placeholder="Password" required="required" name="password">
       		 </div><br>
       		  <div class="form-group">
            	<button type="submit" class="btn btn-primary btn-block">Log in</button>
        	</div>

		</form>
	</div>
</body>
       
</html>