<%@page import="Utils.UploadVoice"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>录音上传上传</title>
</head>
<body>
<%
    String VoiceEncodedStr = request.getParameter("voice");
    String fileName = request.getParameter("filename");
    System.out.println("Filename: "+ fileName);
    if(VoiceEncodedStr != null){
        UploadVoice.convertStringtoImage(VoiceEncodedStr, fileName);
        out.print("Voice upload complete, Please check your directory");
    } else{
        out.print("Voice is empty");
    }
%>
</body>
</html>