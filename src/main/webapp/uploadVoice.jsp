<%@page import="Utils.UploadVoice"%>
<%@ page import="Utils.UploadImage" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>录音上传上传</title>
</head>
<body>
<%
    String imgEncodedStr = request.getParameter("voice");
    String fileName = request.getParameter("filename");
    System.out.println("Filename: "+ fileName);
    if(imgEncodedStr != null){
        UploadImage.convertStringtoImage(imgEncodedStr, fileName);
        out.print("Image upload complete, Please check your directory");
    } else{
        out.print("Image is empty");
    }
%>
</body>
</html>