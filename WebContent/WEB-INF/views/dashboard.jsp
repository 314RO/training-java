<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="WebUIMain"> Application - Computer
				Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">
				<c:out value="${count}" />
				ordinateurs dans la bdd et
				<c:out value="${pageNbr}" />
				pages et on est sur la page
				<c:out value="${page}" />

			</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="Search name" /> <input
							type="submit" id="searchsubmit" value="Filter by name"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="addComputer">Add
						Computer</a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();">Edit</a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="#" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<c:choose>
				<c:when test="${Erreur==1}">Tu me demandes de la merde</c:when>
				<c:otherwise>
					<table class="table table-striped table-bordered">
						<thead>
							<tr>
								<!-- Variable declarations for passing labels as parameters -->
								<!-- Table header for Computer Name -->

								<th class="editMode" style="width: 60px; height: 22px;"><input
									type="checkbox" id="selectall" /> <span
									style="vertical-align: top;"> - <a href="#"
										id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
											class="fa fa-trash-o fa-lg"></i>
									</a>
								</span></th>
								<th>Computer name</th>
								<th>Introduced date</th>
								<!-- Table header for Discontinued Date -->
								<th>Discontinued date</th>
								<!-- Table header for Company -->
								<th>Company</th>

							</tr>
						</thead>
						<!-- Browse attribute computers -->
						<tbody id="results">
							<c:forEach items="${computerList}" var="computerDTO">
								<tr>
									<td class="editMode"><input type="checkbox" name="cb"
										class="cb" value="0"></td>
									<td><a href="editComputer?id=${computerDTO.id}" onclick="">
											<c:out value="${computerDTO.name}" />
									</a></td>
									<td><c:out value="${computerDTO.introduced}" /></td>
									<td><c:out value="${computerDTO.discontinued}" /></td>
									<td><c:out value="${computerDTO.companyName}" /></td>

								</tr>
							</c:forEach>

						</tbody>
					</table>
				</c:otherwise>
			</c:choose>



		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination">
				<c:if test="${page gt 1}">

					<li><a href="?page=${page-1 }" aria-label="Previous"> <span
							aria-hidden="true">&laquo;</span>
					</a></li>
				</c:if>

				<c:forEach var="i" begin="1" end="${pageNbr}">
					<c:choose>
						<c:when test="${page <= 3}">
							<c:if test="${i <= 5}">
								<li><a href="?page=${i}">${i}</a></li>

							</c:if>

						</c:when>
						<c:when test="${page >= pageNbr-3}">
							<c:if test="${i >= pageNbr-4}">
								<li><a href="?page=${i}">${i}</a></li>

							</c:if>
						</c:when>
						<c:otherwise>
							<c:if test="${i>= page-2 && i <= page +2}">
								<li><a href="?page=${i}">${i}</a></li>

							</c:if>
						</c:otherwise>
					</c:choose>

				</c:forEach>
				<c:if test="${page lt pageNbr}">
					<li><a href="?page=${page+1 }" aria-label="Next"> <span
							aria-hidden="true">&raquo;</span>
					</a></li>
				</c:if>
			</ul>

			<div class="btn-group btn-group-sm pull-right" role="group">
				<a href="?itemPerPage=10" class="btn btn-default" type="submit"
					onclick="location.reload()">10</a> <a href="?itemPerPage=50"
					class="btn btn-default" type="submit" onclick="location.reload()">50</a>
				<a href="?itemPerPage=100" class="btn btn-default" type="submit"
					onclick="location.reload()">100</a>
			</div>
		</div>
	</footer>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/dashboard.js"></script>

</body>
</html>