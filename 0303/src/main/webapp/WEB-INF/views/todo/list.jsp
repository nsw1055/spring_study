<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	${list }
<ul>
<c:forEach items="${list}" var="todoDTO">
<li>${todoDTO }</li>
</c:forEach>
</ul>

<script>
const msg = '${msg}'

if(msg === 'success'){
	alert("성공")
}
</script>
</body>
</html>