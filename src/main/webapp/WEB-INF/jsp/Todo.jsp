<%@ include file="Common/header.jspf" %>
<%@ include file="Common/navigation.jspf" %>
<h1 style="text-align:center" class="h2 my-5">Welcome to Todo List</h1>
<div style="display:flex; justify-content:center; width:100%">
	<form:form method="POST" modelAttribute="todo">
	<div class="input-group">
	<!--<form:input type="text" class="form-control" path="user"  placeholder="Todo Name"/>-->
	<form:input type="text" class="form-control" path="desc" placeholder="Todo Desc"/>
	<form:input type="text" class="form-control" path="targetDate" placeholder="Target Date" autocomplete="off"/>
	
	<!--<Select path="isDone" class="form-control">
	<option value="">Select Option</option>
	<option value="false">In Progress</option>
	<option value="true">Done</option>
	</Select> -->
	<input type="submit" class="btn btn-primary" value="save" />
	</div>
	<form:errors path="desc" cssClass="text-danger" />
	<form:errors path="targetDate" cssClass="text-danger" />
	</form:form>
</div>
<h1 style="text-align:center" class="h2 my-5">Your Todos</h1>
<div style="display:flex; justify-content:center; width:100%">
<table class="table w-75 table-hover">
<thead>
<tr>
	<th>Description</th>
	<th>Target Done</th>
	<th>Is it Done?</th>
	<th>Delete</th>
</tr>
</thead>
<tbody>
<c:forEach items="${ todos }" var="todo">
<tr>
<td class="center">${todo.desc}</td>
<td><fmt:formatDate pattern="dd/MM/yyyy" value="${todo.targetDate}" /></td>
<td>${todo.done}</td>
<td><a class="btn btn-danger" href="/delete-todo?id=${todo.id}">Delete</a></td>
</tr>
</c:forEach>
</tbody>
</table>
</div>
<%@ include file="Common/footer.jspf" %>