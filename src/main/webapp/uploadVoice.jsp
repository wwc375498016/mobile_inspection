<%@page import="Utils.UploadVoice"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>录音上传上传</title>
</head>
<body>
<%
    String VoiceEncodedStr = request.getParameter("voice");
    String projectName = request.getParameter("projectName");
    String date = request.getParameter("date");
    String time = request.getParameter("time");
    String address = request.getParameter("address");

    out.println("Filename: "+ projectName+" "+address+" "+date+" "+time);
    if(VoiceEncodedStr != null){
        UploadVoice.convertStringtoImage(VoiceEncodedStr, projectName,date,time,address);
        out.print("Voice upload complete, Please check your directory");
    } else{
        out.print("Voice is empty");
    }
%>
</body>
</html>