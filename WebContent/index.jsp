<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
<link rel="stylesheet" type="text/css" href="${cp }/css/common.css">
</head>
<body>
<div id="wrap">
<div id="top">
	<div>
		<jsp:include page="${top }"/>
	</div>	
</div>
<div id="main">
	<div>
		<jsp:include page="${content }"/>
	</div>
</div>
</div>
</body>
</html>










