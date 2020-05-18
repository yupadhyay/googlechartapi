<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" href="http://jqueryui.com/latest/themes/base/ui.all.css" rel="stylesheet" />
  <script type="text/javascript" src="http://jqueryui.com/latest/jquery-1.3.2.js"></script>
  <script type="text/javascript" src="http://jqueryui.com/latest/ui/ui.core.js"></script>
  <script type="text/javascript" src="http://jqueryui.com/latest/ui/ui.datepicker.js"></script>
   <script type="text/javascript" src="http://dev.jquery.com/view/trunk/plugins/validate/jquery.validate.js"></script>

<script type="text/javascript">

$(document).ready(function(){
	$("#recordCreate").validate();
});


function test(){

	$("#recordCreate").validate({
		  rules: {
		newDefects: {
				digit: true		 
	  	},
	  	resolved: {
	  		digit: true		 
  	},
  	yield: {
  		number: true		 
	},
	median: {
		number: true		 
	},
	span: {
		number: true		 
	}	  	
	}
	
  });
}

$(function()
{
	$('.date-pick').datepicker();
});

</script>

<style type="text/css">
* { font-family: Verdana; font-size: 96%; }
label { width: 10em; float: left; }
label.error { float: none; color: red; padding-left: .5em; vertical-align: top; }
p { clear: both; }
.submit { margin-left: 12em; }
em { font-weight: bold; padding-right: 1em; vertical-align: top; }
</style>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Entry</title>
</head>
<%
String statusMsg="";
if(session.getAttribute("status")!=null){
if(session.getAttribute("status").equals("error")){
statusMsg = 
"<font color=red style=bold>"+	
"There is some problem creating your record. Please try again later.Contact support for more detail"
+"</font>";
}
if(session.getAttribute("status").equals("success")){
statusMsg=
	"<font color=green style=bold>"+	
	"Your record created success fully.Use form below to add more record.<BR>"+
	"</font>";
}
}
	%>


<body>

<font></font>
<br><br>
<div align="center">
<font size="4" color="blue">Create Record</font>
</div>
<div id="status" align="center">
<%=statusMsg %>
</div>
<br>
<form action="/Chart/CreateChart" method="post" id="recordCreate" name="recordCreate">
<div id="chartForm">
<table align="center">
<tr>

<td>Please Select Date:</td>
<td><input type="text" id="date" name="date" class="date-pick required"> <font color="red">*</font> </td>
</tr>
<tr>
<td>Please Select Level:</td>
<td>
<select name="level">
<option value="1">Level 1</option>
<option value="2">Level 2</option>
<option value="3">Level 3</option>
</select>
</td>
</tr>
<tr>
<td>New Defect for this Month:</td>
<td><input type="text" name="newDefects" class="digits"></td>
</tr>
<tr>
<tr>
<td>Number of Defect Resolved:</td>
<td><input type="text" name="resolved" class="digits"></td>
</tr>
<tr>
<td>Yield</td>
<td><input type="text" name="yield" class="number"></td>
</tr>
<tr>
<td>Median Resolution Time</td>
<td><input type="text" name="median" class="number"></td>
</tr>
<tr>
<td>Span:</td>
<td><input type="text" name="span" class="number"></td>
</tr>
<tr></tr>
<tr><td></td>
<td><input type="submit" value="Create Entry"></td>
</tr>
<tr></tr>
<tr><td><a href ="RequestStatistics.jsp"> < go to Home Page </a> </td></tr>
</table>
</div>
</form>
</body>
</html>