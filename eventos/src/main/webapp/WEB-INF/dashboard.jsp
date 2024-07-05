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
<title>Home</title>
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/css/main.css" />
</head>
<body>

	<div class="container">
		<h1>
			Bienvenid@
			<c:out value="${usuario.userName }"></c:out>
		</h1>
		<br> <a href="/logout"> LOG OUT</a>



		<form:form action="/crearevento" method="post"
			modelAttribute="nuevoevento">
			<form:input path="host" type="hidden"  value="${usuario.id}"/>
			
			<div class="mb-3">
				<form:label path="nombreEvento" for="exampleInputEmail1"
					class="form-label">
						Nombre: </form:label>
				<form:errors class="text-danger" path="nombreEvento" />
				<form:input path="nombreEvento" type="text" class="form-control"
					id="exampleInputEmail1" aria-describedby="emailHelp" />
			</div>
			<div class="mb-3">
				<form:label path="fechaEvento" for="exampleInputEmail1"
					class="form-label">
						Date: </form:label>
				<form:errors class="text-danger" path="fechaEvento" />
				<form:input path="fechaEvento" type="date" class="form-control"
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
			<button type="submit" class="btn btn-primary">Crear Evento</button>
		</form:form>

	</div>






</body>
</html>