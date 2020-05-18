<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.Date"%>
<%@page import="Chart.GetRequestedData"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.SortedSet"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GES and WEB Ticket Stat</title>

<script type="text/javascript" src="http://jqueryui.com/latest/jquery-1.3.2.js"></script>
<script type="text/javascript">
function createGraph(){
		$('#errorFont').remove();
	var w = document.requestGraph.year.selectedIndex;
	var year = document.requestGraph.year.options[w].text;
	if(year=='-Select Year-'){
		$('#error').append('<font id=errorFont color=red>Please select valid year</font>');
		//return;
	}
	var url = "http://chart.apis.google.com/chart?cht=bvg&chs=520x400&chco=FF0000,00FF00,0000FF,000000,000FFF&chxt=x,y,r&chxl= 0:|Jan|Feb|March|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec|1:|0|50|100|150|200|2:|0.0|10.0|20.0|30.0&chbh=3,3,10&chbl=NewThisMonth|Resolved|Yield|Median|SPAN&chdlp=b&chd=t:0,0,2,0,0,0,0,0,0,0,0,0|0,0,3,0,0,0,0,0,0,0,0,0|0,0,4.0,0,0,0,0,0,0,0,0,0|0,0,5.0,0,0,0,0,0,0,0,0,0|0,0,6.0,0,0,0,0,0,0,0,0,0";
	$("#graph").load("Graph.jsp?year="+year);
	//window.open(url);
	//$("graph").append('<img align="middle" src='+url+'>');
	//$("#graph").load("http://chart.apis.google.com/chart?cht=bvg&chs=520x400&chco=FF0000,00FF00,0000FF,000000,000FFF&chxt=x,y,r&chxl= 0:|Jan|Feb|March|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec|1:|0|50|100|150|200|2:|0.0|10.0|20.0|30.0&chbh=3,3,10&chbl=NewThisMonth|Resolved|Yield|Median|SPAN&chdlp=b&chd=t:0,0,2,0,0,0,0,0,0,0,0,0|0,0,3,0,0,0,0,0,0,0,0,0|0,0,4.0,0,0,0,0,0,0,0,0,0|0,0,5.0,0,0,0,0,0,0,0,0,0|0,0,6.0,0,0,0,0,0,0,0,0,0");
}


</script>

</head>
<body>
<br><br>
<div align="center" id="chartTitle">
<font size="4" color="blue">Select year to get graph</font>
</div>
<br>
<form action="" name="requestGraph" id="requestGraph">
<div id="optionSelect">
<table align="center">
<tr>
<td>Select Year:</td>
<%GetRequestedData data = new GetRequestedData();
SortedSet<String> allYear = data.getAllValidYear(); %>
<%if(allYear==null || allYear.size()==0){ %>
<td>
<font color="red">No Record Present. First Create Record.</font>
</td>

<%}else{ %>
<td>
<select name="year" onchange="createGraph();">
<option selected="selected">-Select Year-</option>

<%
for(String year:allYear){ %>
<option value=<%=year%>>
<%=year %>
</option>
<%} %>


</select>

</td>


<%} %>
<td><div id="error"></div></td>
</tr>
</table>


</div>



</form>

<br><br><br>
<div align="center" id="graph"></div>
<br>

<font color="green">Click Link below to create new record.</font>
<br>

<a href="CreateChart.jsp">Create Entry</a>
</body>
</html>