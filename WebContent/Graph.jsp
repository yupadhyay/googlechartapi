<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="Chart.GetRequestedData"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String year = request.getParameter("year");
String googURL = "http://chart.apis.google.com/chart?";
String chartType = "cht=bvg";
String chartScaling = "chs=800x300";
String chartTitle = "chtt=GES+and+Web|Year+"+year+"&chts=FF0000,20";
String chartColor = "chco=FF0000,00FF00,0000FF,000000,000FFF";
String chartAxis = "chxt=x,y,r";
String xaxisRange = "0:|Jan|Feb|March|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec|";
String yaxysRange = "1:|0|50|100|150|200|";
String raxisRange = "2:|0.0|10.0|20.0|30.0";
String barDistance = "chbh=7,3,13";
String legend = "chdl=NewThisMonth|Resolved|Yield|Median|SPAN";
String legendPosition = "chdlp=b";
String ChartAxisValue = "chxl="+xaxisRange+yaxysRange+raxisRange;
String data = "chd=t:"+new GetRequestedData().getDataString(year).toString();
String finalURL = googURL+chartType+"&"+chartTitle+"&"+
chartScaling+"&"+legend+"&"+ legendPosition+"&"
+chartColor+"&"+chartAxis+"&"+ChartAxisValue+"&"+barDistance+"&"+data;
System.out.println(finalURL);
%>
<img alt="" width="1300" height="650" src=<%=finalURL%>>
</body>
</html>