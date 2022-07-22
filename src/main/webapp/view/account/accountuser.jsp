<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Thông Tin Cá Nhân</title>
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.1/dist/js/bootstrap.bundle.min.js"></script>
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet">
</head>
<style type="text/css">
@import
	url('http://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800;900&display=swap')
	;

* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: 'Poppins', sans-serif;
}

body {
	display: flex;
	margin-top: 20px;
	background: #f8f8f8;
	background: -webkit-linear-gradient(left, #25c481, #25b7c4);
	background: linear-gradient(to right, #25c481, #25b7c4);
	font-family: 'Roboto', sans-serif;
}

.button-85 {
	padding: 0.6em 2em;
	border: none;
	outline: none;
	color: rgb(255, 255, 255);
	background: #111;
	cursor: pointer;
	position: relative;
	z-index: 0;
	border-radius: 10px;
	user-select: none;
	-webkit-user-select: none;
	touch-action: manipulation;
}

.button-85:before {
	content: "";
	background: linear-gradient(45deg, #ff0000, #ff7300, #fffb00, #48ff00, #00ffd5,
		#002bff, #7a00ff, #ff00c8, #ff0000);
	position: absolute;
	top: -2px;
	left: -2px;
	background-size: 400%;
	z-index: -1;
	filter: blur(5px);
	-webkit-filter: blur(5px);
	width: calc(100% + 4px);
	height: calc(100% + 4px);
	animation: glowing-button-85 20s linear infinite;
	transition: opacity 0.3s ease-in-out;
	border-radius: 10px;
}

@
keyframes glowing-button-85 { 0% {
	background-position: 0 0;
}

50


%
{
background-position


:


400
%


0
;


}
100


%
{
background-position


:


0


0
;


}
}
.button-85:after {
	z-index: -1;
	content: "";
	position: absolute;
	width: 100%;
	height: 100%;
	background: #222;
	left: 0;
	top: 0;
	border-radius: 10px;
}

.button-62 {
	background: linear-gradient(to bottom right, #EF4765, #FF9A5A);
	border: 0;
	border-radius: 12px;
	color: #FFFFFF;
	cursor: pointer;
	display: inline-block;
	font-family: -apple-system, system-ui, "Segoe UI", Roboto, Helvetica,
		Arial, sans-serif;
	font-size: 16px;
	font-weight: 500;
	line-height: 2.5;
	outline: transparent;
	padding: 0 1rem;
	text-align: center;
	text-decoration: none;
	transition: box-shadow .2s ease-in-out;
	user-select: none;
	-webkit-user-select: none;
	touch-action: manipulation;
	white-space: nowrap;
}

.button-62:not([disabled]):focus {
	box-shadow: 0 0 .25rem rgba(0, 0, 0, 0.5), -.125rem -.125rem 1rem
		rgba(239, 71, 101, 0.5), .125rem .125rem 1rem rgba(255, 154, 90, 0.5);
}

.button-62:not([disabled]):hover {
	box-shadow: 0 0 .25rem rgba(0, 0, 0, 0.5), -.125rem -.125rem 1rem
		rgba(239, 71, 101, 0.5), .125rem .125rem 1rem rgba(255, 154, 90, 0.5);
}

#conten .card {
	background-image:
		url("https://scr.vn/wp-content/uploads/2020/07/white-background-%C4%91%E1%BA%B9p-1024x640.jpg");
	font-weight: 500;
}

.images {
	box-shadow: 0 0 10px #444;
}

#label {
	color: #ff1867;
	box-shadow: 0 0 10px #44;
	font-size: 19px;
}

#label label:hover {
	font-size: 19.5px;
	transition: 0.1s;
}

#label label::before {
	content: '';
	position: absolute;
	inset: 2px;
}
</style>
<body>
	<div class="container">
		<form:form action="/update/profile" modelAttribute="user"
			enctype="multipart/form-data">
			<div class="row flex-lg-nowrap">
				<div class="col">
					<div class="row">
						<div class="col mb-3" id="conten">
							<div class="card">
								<div class="card-body">
									<div class="e-profile">
										<div class="row">
											<div class="col-4">
												<div class="p-6 images">
													<img src="/webapp/images/${user.photo}" alt=""
														style="width: 240px" height="300px">
													<div class="form-group">
														<input type="file" name="photo" class="form-control-file"
															id="image" onchange="chooseFile(this)" />
													</div>
												</div>
											</div>
										</div>
										<ul class="nav nav-tabs">
											<li class="nav-item"><a href="" class="active nav-link">Settings</a></li>
										</ul>
										<div class="tab-content pt-3">
											<div class="tab-pane active">
												<div class="row" id="label">
													<div class="col">
														<div class="row">
															<div class="col">
																<div class="form-group">
																	<label id="fullenameID">Fullname</label>
																	<form:input class="form-control" type="text"
																		path="fullname" placeholder="fullname?" />
																</div>
															</div>
															<div class="col">
																<div class="form-group">
																	<label id="usernameID">Username</label>
																	<form:input class="form-control" type="text"
																		disabled="false" path="username" name="username"
																		placeholder="username?" />
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col">
																<div class="form-group">
																	<label id="emailID">Email</label>
																	<form:input class="form-control" type="email"
																		path="email" placeholder="Password?" />
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col mb-3">
																<div class="form-group">
																	<label>About</label>
																	<textarea class="form-control" rows="5"
																		placeholder="My Bio"></textarea>
																</div>
															</div>
														</div>
													</div>
												</div>
												<hr style="background: #27282c">
												<div class="row">
													<div class="col-12 col-sm-6 mb-3">
														<div class="mb-2">
															<b>Change Password</b>
														</div>
														<div class="row">
															<div class="col">
																<div class="form-group">
																	<label>Current Password</label> <input
																		class="form-control" type="password" name="passcu"
																		placeholder="Current Password?" />
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col">
																<div class="form-group">
																	<label>New Password</label> <input class="form-control"
																		type="password" name="password"
																		placeholder="New Password?" />
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col">
																<div class="form-group">
																	<label>Confirm <span class="d-none d-xl-inline">Password</span></label>
																	<input class="form-control" type="password"
																		placeholder="Confirm Password" name="xacnhanpas" />
																</div>
															</div>
														</div>
													</div>

												</div>
												<div class="row">
													<div class="col d-flex justify-content-end">
														<button class="button-62" role="button">Save
															Change</button>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-12 col-md-3 mb-3">
							<div class="card mb-3">
								<div class="card-body">
									<div class="px-xl-3">
										<button class="button-85" role="button">
											<i class="fa fa-sign-out"></i> <a href="/home/index">Trở Về</a>
										</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form:form>
	</div>
</body>
<script type="text/javascript">
	</html>
