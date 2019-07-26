<%--
  Created by IntelliJ IDEA.
  User: 10534
  Date: 2019/7/25
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@page import="Utils.UploadSignature"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>签名上传</title>
</head>
<body>
<%
    String signatureEncodedStr = request.getParameter("image");
    String checkName = request.getParameter("checkName");
    String signatureName = request.getParameter("signatureName");
    String date = request.getParameter("date");

    out.print("Filename: "+ signatureName);
    if(signatureEncodedStr != null){
        UploadSignature.convertStringtoImage(signatureEncodedStr, checkName, signatureName, date);
        out.print("Signature upload complete, Please check your directory");
    } else{
        out.print("Signature is empty");
    }
%>
</body>
</html>
