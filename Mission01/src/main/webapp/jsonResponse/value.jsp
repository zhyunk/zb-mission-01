<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %><%@ page import="com.google.gson.JsonObject" %><% JsonObject obj = (JsonObject) request.getAttribute("response"); %><%= obj.get("a") %>