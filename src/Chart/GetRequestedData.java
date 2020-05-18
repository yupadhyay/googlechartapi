package Chart;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class GetRequestedData {
	private SortedSet<String> allYear = new TreeSet<String>();	
	private String fileName =  CreateChartXML.getFileName();
	private Document doc = null;
	private SAXBuilder builder = new SAXBuilder();

	
	/**
	 * 
	 * For all Data
	 */
	//Formate is NewThisMonth,Resolved,Yield,Median,SPAN
	private String[] newDefects = new String[12];	
	private String[] resolved = new String[12];
	private String[] yield = new String[12];
	private String[] median = new String[12];
	private String[] span = new String[12];
	
	public GetRequestedData(){
		Arrays.fill(newDefects,"0");
		Arrays.fill(resolved,"0");
		Arrays.fill(yield,"0");
		Arrays.fill(median,"0");
		Arrays.fill(span,"0");
	}
	
	
	private ArrayList<ChartObject> getChartData(String year){
		
		
		ArrayList<ChartObject> chartList = new ArrayList<ChartObject>();
		if(!new File(fileName).exists()){
			System.out.println("File does not exist");
		}
		if(new File(fileName).length()==0){
			System.out.println("File is empty. Please add record first");
		}
		try {
			this.doc = this.builder.build(this.fileName);
			Element root = this.doc.getRootElement();
			List chart = root.getChildren();
			Iterator itr = chart.iterator();
			while(itr.hasNext()){
				Element element = (Element)itr.next();
				if(element.getChild("year").getText().equals(year)){
					ChartObject chartObject = new ChartObject();
					chartObject.setMonth(element.getChildText("month"));
					chartObject.setNewDefect(Integer.parseInt(element.getChildText("newDefect")));
					chartObject.setIssueResolve(Integer.parseInt(element.getChildText("numberOfIssueResolved")));
					chartObject.setMedian(Double.parseDouble(element.getChildText("median")));
					chartObject.setSpan(Double.parseDouble(element.getChildText("span")));
					chartObject.setYield(Double.parseDouble(element.getChildText("yield")));
					chartList.add(chartObject);
				}
			}
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return chartList;
	}
	
	public String getDataString(String year){
		ArrayList<ChartObject> chartList = getChartData(year);
		for(ChartObject chartObject:chartList){
			loadData(chartObject.getMonth(), chartObject);
		}
		System.out.println(getFinalString());
		return getFinalString();
	}
	
	
	public String testgetDataString(String year){
		String finalString="";
		//Formate is NewThisMonth,Resolved,Yield,Median,SPAN
		String jan = "0|0|0|0|0,";
		String feb = "0|0|0|0|0,";
		String march = "0|0|0|0|0,";
		String apr = "0|0|0|0|0,";
		String may = "0|0|0|0|0,";
		String june = "0|0|0|0|0,";
		String jul = "0|0|0|0|0,";
		String aug = "0|0|0|0|0,";
		String sep = "0|0|0|0|0,";
		String oct = "0|0|0|0|0,";
		String nov = "0|0|0|0|0,";
		String dec = "0|0|0|0|0";		
		ArrayList<ChartObject> chartList = getChartData(year);
		for(ChartObject chartObject:chartList){
		if(chartObject.getMonth().equals("01"))
			jan = getChangedString(chartObject);
		if(chartObject.getMonth().equals("02"))
			feb = getChangedString(chartObject);
		if(chartObject.getMonth().equals("03"))
			march = getChangedString(chartObject);
		if(chartObject.getMonth().equals("04"))
			apr = getChangedString(chartObject);
		if(chartObject.getMonth().equals("05"))
			may = getChangedString(chartObject);
		if(chartObject.getMonth().equals("06"))
			june = getChangedString(chartObject);
		if(chartObject.getMonth().equals("07"))
			jul = getChangedString(chartObject);
		if(chartObject.getMonth().equals("08"))
			aug = getChangedString(chartObject);
		if(chartObject.getMonth().equals("09"))
			sep = getChangedString(chartObject);
		if(chartObject.getMonth().equals("10"))
			oct = getChangedString(chartObject);
		if(chartObject.getMonth().equals("11"))
			nov = getChangedString(chartObject);
		if(chartObject.getMonth().equals("12"))
			dec = getChangedString(chartObject);	
			
		}
		finalString = jan+feb+march+apr+may+june+jul+aug+sep+oct+nov+dec;
		return finalString;
		
	}
	
	public SortedSet<String> getAllValidYear(){
		if(!new File(fileName).exists()){
			System.out.println("File does not exist");
			return null;
		}
		if(new File(fileName).length()==0){
			System.out.println("File is empty. Please add record first");
			return null;
		}
		try {
			this.doc = this.builder.build(this.fileName);
			Element root = this.doc.getRootElement();
			List chart = root.getChildren();
			Iterator itr = chart.iterator();
			while(itr.hasNext()){
				Element element = (Element)itr.next();	
				allYear.add(element.getChildText("year"));
				
			}
		}catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return this.allYear;
	}
	
	private String getChangedString(ChartObject chartObject){
		String finalString="";
		String newIssue = Integer.toString(chartObject.getNewDefects());
		String resolved = Integer.toString(chartObject.getIssueResolve());
		String yield = Double.toString(chartObject.getYield());
		String median = Double.toString(chartObject.getMedian());
		String span = Double.toString(chartObject.getSpan());
		finalString = newIssue+"|"+resolved+"|"+yield+"|"+median+"|"+span+",";
		return finalString;
	}
	
	private void loadData(String month,ChartObject chartObject){
		String newIssue = Integer.toString(chartObject.getNewDefects());
		String resolved = Integer.toString(chartObject.getIssueResolve());
		String yield = Double.toString(chartObject.getYield());
		String median = Double.toString(chartObject.getMedian());
		String span = Double.toString(chartObject.getSpan());
		int index = Integer.parseInt(month)-1; 
		this.newDefects[index]=newIssue;
		this.resolved[index]=resolved;
		this.yield[index]=yield;
		this.median[index]=median;
		this.span[index]=span;
		
	}
	
	private String getFinalString(){
		String finalString = "";
		String finalDefectString = getCommaSeperatedStrings(this.newDefects);
		String finalresolvedString = getCommaSeperatedStrings(this.resolved);
		String finalYieldString = getCommaSeperatedStrings(this.yield);
		String finalMedianString = getCommaSeperatedStrings(this.median);
		String finalSpanString = getCommaSeperatedStrings(this.span);
		finalString = finalDefectString+"|"+finalresolvedString+"|"+finalYieldString+"|"+finalMedianString+"|"+finalSpanString;
		return finalString;
	}
	
	private String getCommaSeperatedStrings(String[] input){
		return Arrays.toString(input).replace("[", "").replace("]", "").replace(" ", "");
	}
	
}
