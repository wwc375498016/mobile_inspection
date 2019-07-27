<%--
  Created by IntelliJ IDEA.
  User: 10534
  Date: 2019/7/26
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@page import="Utils.UploadCheckPhoto"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图片上传</title>
</head>
<body>
<%
    String imgEncodedStr = request.getParameter("image");
    String projectName = request.getParameter("projectName");
    String date = request.getParameter("date");
    String address = request.getParameter("address");
    String photoName = request.getParameter("photoName");
    out.println("Filename: "+ projectName+" "+address+" "+date+" "+photoName);
    if(imgEncodedStr != null){
        UploadCheckPhoto.convertStringtoImage(imgEncodedStr, projectName,date,address,photoName);
        out.print("Image upload complete, Please check your directory");
    } else{
        out.print("Image is empty");
    }
%>
</body>
</html>
