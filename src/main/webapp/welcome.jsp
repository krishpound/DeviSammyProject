<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">


  <head>
    <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
    <link href="css/fashiontrack.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" language="javascript" src="jscript/searchTable.js"></script>
    <title>Fashion Engine</title>
  </head>

  <body>
    <h1>Fashion Track Project</h1>
	<table>
    	<tr><td>Click one of these links:</td></tr>
   		<!--<tr><td><a href='/hello'>Local Inventory</a></td></tr>-->
   		<tr><td><a href='/vision'>Analyze a Cache of Images</a></td></tr>
      	<tr><td><a href='/krish'>Search Image Database</a></td></tr>
      	<tr><td><a href='/findCopies'>Search For Similar Images</a></td></tr>
	</table>
   
   	<c:if test="${requestScope.localTableList != null}">
    	<h1>Image Database on Local</h1>
    	<c:forEach items="${requestScope.localTableList}" var="local"> 
    		<br>${local}
    	</c:forEach>
    </c:if>
    
    
    <c:if test="${requestScope.cloudTableList != null}">
    	<h2>Image Database on Cloud</h2>
    	<input type="text" id="imageInput" onkeyup="imageFunction()" placeholder="Search Image Name ..">
    	<input type="text" id="labelInput" onkeyup="labelFunction()" placeholder="Search Google Vision Labels ..">
    	<table id="myTable">
    	<th>Path</th><th>Image File</th><th>Google Vision Label</th><th>Probability</th><th>Image Created</th><th>Size</th><th>Labels</th>
    	<c:forEach items="${requestScope.cloudTableList}" var="cloud"> 
    		<tr><td>${cloud.imgpath}</td><td><a href="${cloud.imgpath}/${cloud.imgname}">${cloud.imgname}</a></td><td>${cloud.label}</td><td>${cloud.score}</td><td>${cloud.imgmodified}</td><td>${cloud.imgsize}</td><td>${cloud.labelcount}</td></tr>
    	</c:forEach>
    	</table> 
    </c:if>
    
    <c:if test="${requestScope.sectionList != null}">
    	<c:forEach items="${requestScope.sectionList}" var="section"> 
    		<h2>Vision Labels for Image: ${section}</h2>
    		<img src="${section}"/>
    		<table class="visionData">
    	  	<th class="visionData">LABEL</th><th class="visionData">SCORE</th>
    		<c:forEach items="${requestScope.visionLabelList}" var="cloud"> 
    			<c:if test="${cloud.imgid == section}">
    				<tr><td class="visionData1">${cloud.label}</td><td class="visionData2">${cloud.score}</td></tr>
    			</c:if>
    	</c:forEach>
    	</table>
    	</c:forEach>
    </c:if>
  </body>
</html>