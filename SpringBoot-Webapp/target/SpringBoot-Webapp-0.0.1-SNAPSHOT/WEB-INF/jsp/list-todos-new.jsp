<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>

<head>
<title>Todo's for ${name}</title>
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
</head>

<body>
	<div class="container">
		<table class="table table-striped">
			<caption>Welcome ${name} !! , Your todos are:</caption>
			<thead>
				<tr>
					<th>Name</th>
					<th>Email</th>
					<th>Description</th>
					<th>Target Date</th>
					<th>Is it Done?</th>
					<th>End Date</th>
					<th><input type="checkbox" class="test" name="selectAll" id="selectAll" checked="checked">Select All</input></th>
					<th><a type="button" id="deleteAll" class="btn btn-warning" href="/delete-todo-new?id=0" hidden>Delete</a></th>					
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${todos}" var="todo">
					<tr>
						<td>${todo.name}</td>
						<td>${todo.email}</td>
						<td>${todo.desc}</td>
						<td><fmt:formatDate value="${todo.targetDate}" pattern="dd-MMM-yyyy"/></td>
						<td>${todo.done}</td>
						<td><fmt:formatDate value="${todo.endDate}" pattern="dd-MMM-yyyy"/></td>
						<td><a type="button" class="btn btn-success"
							href="/update-todo-new?id=${todo.id}">Update</a></td>
						<td><a type="button" class="btn btn-warning"
							href="/delete-todo-new?id=${todo.id}">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>
			<a class="button" href="/add-todo-new">Add a Todo</a>
			
		</div>
	</div>
	
	<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
	<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<script>
		$('.test').click(function () {
	    if($(".test:checked").length === 1 && $("#selectAll").is(":checked") == true){
	         $('#deleteAll').show();
	    } else {
	         $('#deleteAll').hide(); 
	    }
		});
</script>
</body>

</html>