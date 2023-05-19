<%--
  Created by IntelliJ IDEA.
  User: whdgh
  Date: 2023-05-19
  Time: 오후 6:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload</title>
</head>
<body>
<h1>Upload !!</h1>
<form action="/upload" method="post" enctype="multipart/form-data">
  <input type="file" name="file"/>
  <input type="submit" />
</form>
<img src="${url}">
</body>
</html>
