<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head></head>

<body>
	<h1>${message}</h1>
	<h2>Foo Values</h2>
	<c:forEach items="${data}" var="foo">
		${foo.id}
	</c:forEach>
</body>
</html>