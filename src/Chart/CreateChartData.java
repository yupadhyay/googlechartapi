package Chart;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateChartData extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession status = req.getSession();
		status.setAttribute("status", "");
		String date = req.getParameter("date");
		String[] dateSplit = date.split("\\/");
		int year  = Integer.parseInt(dateSplit[2]);
		String month = dateSplit[0];
		int level =Integer.parseInt(req.getParameter("level"));
		int resolved;
		if(req.getParameter("resolved")!=null && !req.getParameter("resolved").equals("")){
			resolved = Integer.parseInt(req.getParameter("resolved"));
		}else{
			resolved=0;
		}
		
		int newDefects=0;
		if(req.getParameter("newDefects")!=null && !req.getParameter("newDefects").equals("")){
			newDefects = Integer.parseInt(req.getParameter("newDefects"));
		}else{
			newDefects=0;
		}
		
		
		Double yield=0.0;
		if(req.getParameter("yield")!=null && !req.getParameter("yield").equals("") ){
			yield = Double.parseDouble(req.getParameter("yield"));			
		}
		else
			yield = 0.0;
		Double median=0.0;
		if(req.getParameter("median")!=null && !req.getParameter("median").equals("")){
			median = Double.parseDouble(req.getParameter("median"));			
		}
		else
			median = 0.0;
		Double span=0.0;
		if(req.getParameter("span")!=null && !req.getParameter("span").equals("")){
			span = Double.parseDouble(req.getParameter("span"));			
		}
		else
			span = 0.0;
		ChartObject chart = new ChartObject();
		chart.setYear(year);
		chart.setMonth(month);
		chart.setLevel(level);
		chart.setIssueResolve(resolved);
		chart.setYield(yield);
		chart.setMedian(median);
		chart.setSpan(span);
		chart.setNewDefect(newDefects);
		CreateChartXML chartXML  = new CreateChartXML();
		try{
		chartXML.createChartXML(chart);
		}catch(Exception e){
			status.setAttribute("status", "error");
			status.setAttribute("error", e.getMessage());
		}
		status.setAttribute("status", "success");
		resp.sendRedirect("/Chart/CreateChart.jsp");
		 
	}
}
