<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>GrowingITSkill</title>

<!-- Bootstrap -->
<link
	href="resources/login/vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link
	href="resources/login/vendors/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- Animate.css -->
<!-- <link href="https://colorlib.com/polygon/gentelella/css/animate.min.css" rel="stylesheet"> -->
<link href="resources/login/vendors/animate.css/animate.min.css"
	rel="stylesheet">

<!-- Custom Theme Style -->
<link href="resources/login/build/css/custom.min.css" rel="stylesheet">
</head>

<body class="login">
	<div>
		<a class="hiddenanchor" id="signup"></a> <a class="hiddenanchor"
			id="signin"></a>

		<div class="login_wrapper">
			<div class="animate form login_form">
				<section class="login_content">
					<sf:form method="post">
						<h1>Login</h1>
						<div>
							<input type="text" name="username" class="form-control"
								placeholder="Username" required="" />
						</div>
						<div>
							<input type="password" name="password" class="form-control"
								placeholder="Password" required="" />
						</div>
						<div>
							<input type="submit" value="Go! Login" />
						</div>

						<div class="clearfix"></div>

						<div class="separator">

							<div class="clearfix"></div>
							<br />

							<div>
								<p>
									<a href="/"><img src="/resources/images/logo.png"
										alt="Logo Not Found" /></a>
								</p>
								<p>
									&copy; <a href="/">www.growingitskill.com</a> All Rights
									Reserved.
								</p>
								<p>
									Gentelella - Bootstrap Admin Template by <a
										href="https://colorlib.com">Colorlib</a>
								</p>
							</div>
						</div>
					</sf:form>
				</section>
			</div>

			<div id="register" class="animate form registration_form">
				<section class="login_content">
					<form>
						<h1>Create Account</h1>
						<div>
							<input type="text" class="form-control" placeholder="Username"
								required="" />
						</div>
						<div>
							<input type="email" class="form-control" placeholder="Email"
								required="" />
						</div>
						<div>
							<input type="password" class="form-control"
								placeholder="Password" required="" />
						</div>
						<div>
							<a class="btn btn-default submit" href="index.html">Submit</a>
						</div>

						<div class="clearfix"></div>

						<div class="separator">
							<p class="change_link">
								Already a member ? <a href="#signin" class="to_register">
									Log in </a>
							</p>

							<div class="clearfix"></div>
							<br />

							<div>
								<h1>
									<i class="fa fa-paw"></i> GrowingITSkill
								</h1>
								&copy; <a href="/">www.growingitskill.com</a> All Rights
								Reserved.
								<p>Gentelella Alela! is a Bootstrap 3 template.</p>
							</div>
						</div>
					</form>
				</section>
			</div>
		</div>
	</div>
</body>
</html>