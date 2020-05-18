package Chart;

public class ChartObject {
	private int year;
	private String month;
	private int level;
	private int newDefects;
	private int numberOfIssueResolve;
	private Double yield;
	private Double median;
	private Double span;
	public int getYear(){
		return this.year;
	}
	public void setYear(int year){
		this.year=year;
	}
	public String getMonth(){
		return this.month;
	}
	public void setMonth(String month){
		this.month=month;
	}
	public int getLevel(){
		return this.level;
	}
	public void setLevel(int level){
		this.level=level;
	}
	public int getNewDefects(){
		return this.newDefects;
	}
	public void setNewDefect(int newDefects){
		this.newDefects=newDefects;
	}
	
	public int getIssueResolve(){
		return this.numberOfIssueResolve;
	}
	public void setIssueResolve(int numberOfIssueResolve){
		this.numberOfIssueResolve=numberOfIssueResolve;
	}
	public Double getYield(){
		return this.yield;
	}
	public void setYield(Double yield){
		this.yield=yield;
	}
	public Double getMedian(){
		return this.median;
	}
	public void setMedian(Double median){
		this.median=median;
	}
	public Double getSpan(){
		return this.span;
	}
	public void setSpan(Double span){
		this.span=span;
	}
}
