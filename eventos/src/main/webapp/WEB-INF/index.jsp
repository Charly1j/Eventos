<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Libreria JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Libreria PARA FORMULARIOS - DATA BINDING -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page isErrorPage="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login y Registro</title>
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/css/main.css" />
</head>
<body>

	<div class="container">

		<div class="row pt-5">

			<div class="col-6">
				<form:form action="/register" method="post"
					modelAttribute="registro">
					<div class="mb-3">
						<form:label path="userName" for="exampleInputEmail1"
							class="form-label">
						Username: </form:label>
						<form:errors class="text-danger" path="userName" />
						<form:input path="userName" type="text" class="form-control"
							id="exampleInputEmail1" aria-describedby="emailHelp" />
					</div>
					<div class="mb-3">
						<form:label path="email" for="exampleInputEmail1"
							class="form-label">
						Email: </form:label>
						<form:errors class="text-danger" path="email" />
						<form:input path="email" type="email" class="form-control"
							id="exampleInputEmail1" aria-describedby="emailHelp" />
					</div>
					<div class=" row mb-3">
				<div class="col-8">
					<form:label path="locacion" for="exampleInputPassword1"
						class="form-label">
						Location: </form:label>
					<form:errors class="text-danger" path="locacion" />
					<form:input path="locacion" type="text" class="form-control"
						id="exampleInputEmail1" aria-describedby="emailHelp" />
				</div>
				<div class="col-4">
					<form:select class="form-label" path="provincia">
						<c:forEach items="${provincias}" var="prov">
							<form:option label="${prov}" value="${prov}" />
						</c:forEach>
					</form:select>

				</div>

			</div>
					<div class="mb-3">
						<form:label path="password" for="exampleInputPassword1"
							class="form-label">
						Password: </form:label>
						<form:errors class="text-danger" path="password" />
						<form:input path="password" type="password" class="form-control"
							id="exampleInputEmail1" aria-describedby="emailHelp" />
					</div>
					<div class="mb-3">
						<form:label path="passwordConfirmation"
							for="exampleInputPassword1" class="form-label">
						Confirm Password: </form:label>
						<form:errors class="text-danger" path="passwordConfirmation" />
						<form:input path="passwordConfirmation" type="password"
							class="form-control" id="exampleInputEmail1"
							aria-describedby="emailHelp" />
					</div>
					<button type="submit" class="btn btn-primary">Registro</button>

					<div>
						<p class="text-success">
							<c:out  value="${gracias}"></c:out>
						</p>
					</div>

				</form:form>

			</div>
			<div class="col-6">
				<form:form action="/login" method="post" modelAttribute="login">
					<div class="mb-3">
						<form:label path="email" for="exampleInputEmail1"
							class="form-label">
						Email: </form:label>
						<form:errors class="text-danger" path="email" />
						<form:input path="email" type="email" class="form-control"
							id="exampleInputEmail1" aria-describedby="emailHelp" />
					</div>
					<div class="mb-3">
						<form:label path="password" for="exampleInputPassword1"
							class="form-label">
						Password: </form:label>
						<form:errors class="text-danger" path="password" />
						<form:input path="password" type="password" class="form-control"
							id="exampleInputEmail1" aria-describedby="emailHelp" />
					</div>
					<button type="submit" class="btn btn-primary">Login</button>
				</form:form>
			</div>

		</div>


	</div>


</body>
</html>