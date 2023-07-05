<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.io.*"%>
<%@ page import="com.egov.namul.util.Common" %>
<%@ page session="true" %>
<% Common common = new Common(); %>
<% String thisPath = "/"+request.getRequestURI().split("jsp/")[1].split(".jsp")[0];%>
<% String cssPath = request.getRequestURI().split("jsp/")[1].split(".jsp")[0].replace("/", "");%>
<%
	session = request.getSession();
	String username = (String)session.getAttribute("username");
	String usertypecode = (String)session.getAttribute("usertypecode");
	String workid = request.getParameter("work"); 
	String per_name = request.getParameter("per_name"); 
	String per_seq = request.getParameter("per_seq"); 
	String per_id = request.getParameter("per_id"); 

	String per_organize = request.getParameter("per_organize"); 
	String per_team = request.getParameter("per_team"); 
	String at_seq = request.getParameter("at_seq"); 
	String pos_seq = request.getParameter("pos_seq"); 
	String jg_seq = request.getParameter("jg_seq"); 
	
%>
<!DOCTYPE html>
<html lang="UTF-8">
<c:set var="now" value="<% common.now(); %>" />
<c:set var="week" value="<% common.week(); %>" />
<c:set var="min5" value="<% common.min5(); %>" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="resourcePath" value="${contextPath}/resource" />
<c:set var="cssfile" value="${resourcePath}/css"/>
<c:set var="jspPath" value="${contextPath}/WEB-INF/jsp"/>
<c:set var="jsonPath" value="${jspPath}/json"/>
<c:set var="thisPath" value="<%=thisPath %>" />
<c:set var="cssPath" value="<%=cssPath %>" />
<c:set var="username" value="${username}"/>
<c:set var="usertypecode" value="${usertypecode}"/>