package Chart;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;


public class CreateChartXML {
	private static final String fileName="chartData.xml";
	private SAXBuilder builder = new SAXBuilder();
	private Document doc=null;
	private boolean isFirstRecord=false;
	private boolean isRecordPresent=false;
	public void createChartXML(ChartObject chartobj){
		if(isFileExist(fileName)){
			System.out.println("File Exist and path is "+new File(fileName).getAbsolutePath());
			addRecord(chartobj);
		}else{
			File file  = new File(fileName);
			try {
				file.createNewFile();
				this.isFirstRecord=true;
				addRecord(chartobj);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	private boolean isFileExist(String fileName){
		return (new File(fileName)).exists();
	}
	private void addRecord(ChartObject chartobj){
		String uniqueKey = Integer.toString(chartobj.getYear())+chartobj.getMonth();
		try {
			if(new File(fileName).length()==0){
				this.isFirstRecord=true;
			}
			if(!this.isFirstRecord)
			this.doc=this.builder.build(fileName);
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(this.isFirstRecord){
			addNewRecord(chartobj,uniqueKey);
		}
		else{
		
		if(isRecordAlreadyPresent(uniqueKey)){
			System.out.println("This record is already present");
			updateExistingRecord(chartobj,uniqueKey);
		}else
			addNewRecord(chartobj,uniqueKey);
		}
	}
	

	
	private void addNewRecord(ChartObject chartObject,String uniqueKey){
		Element root = null;
		if(this.doc==null){
			root = new Element("chart");
			//Element rootData = new Element("rootData");
			//root.addContent(rootData);
		}
		else{
			root = this.doc.getRootElement();
		}
			Element chartData = new Element("chartData");
			root.addContent(chartData);
			Element unique = new Element("uniqueKey");
			unique.setText(uniqueKey);
			
			Element year = new Element("year");
			Element month = new Element("month");
			Element level = new Element("level");
			Element newDefects = new Element("newDefect");
			Element issueResolved = new Element("numberOfIssueResolved");
			Element yield = new Element("yield");
			Element median = new Element("median");
			Element span = new Element("span");
			year.setText(Integer.toString(chartObject.getYear()));
			month.setText(chartObject.getMonth());
			level.setText(Integer.toString(chartObject.getLevel()));
			newDefects.setText(Integer.toString(chartObject.getNewDefects()));
			issueResolved.setText(Integer.toString(chartObject.getIssueResolve()));
			yield.setText(Double.toString(chartObject.getYield()));
			median.setText(Double.toString(chartObject.getMedian()));
			span.setText(Double.toString(chartObject.getSpan()));
			chartData.addContent(unique).addContent(year).addContent(month).addContent(level).addContent(newDefects).addContent(issueResolved).addContent(yield).addContent(median).addContent(span);
			
			
			XMLOutputter output = new XMLOutputter();
			Format newFormat = output.getFormat();
			newFormat.setIndent("\t");
			newFormat.setLineSeparator("\n");
			output.setFormat(newFormat);
			try {
				FileWriter fw = new FileWriter(fileName,false);
				if(this.doc==null)					
				output.output(new Document(root), fw);
				else
				output.output(this.doc.getRootElement(), fw);
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
		
		
	}
	private void updateExistingRecord(ChartObject chartObject,String uniqueKey){
		Element root = this.doc.getRootElement();
		List chart = this.doc.getRootElement().getChildren();
		Iterator i = chart.iterator();
		while(i.hasNext()){
			Element chartData = (Element)i.next();
			
			
			if(chartData.getChild("uniqueKey").getText().equals(uniqueKey)){
				chartData.getChild("year").setText(Integer.toString(chartObject.getYear()));
				chartData.getChild("month").setText(chartObject.getMonth());
				chartData.getChild("level").setText(Integer.toString(chartObject.getLevel()));
				chartData.getChild("newDefect").setText(Integer.toString(chartObject.getNewDefects()));
				chartData.getChild("numberOfIssueResolved").setText(Integer.toString(chartObject.getIssueResolve()));
				chartData.getChild("yield").setText(Double.toString(chartObject.getYield()));
				chartData.getChild("median").setText(Double.toString(chartObject.getMedian()));
				chartData.getChild("span").setText(Double.toString(chartObject.getSpan()));
				break;
			}
		}
		
	}
	private boolean isRecordAlreadyPresent(String uniqueKey){	
	
		if(!isFirstRecord){
		List chart = this.doc.getRootElement().getChildren();
		//List chart = root.getChildren("chart");
		Iterator i = chart.iterator();
		while(i.hasNext()){
			Element chartData = (Element)i.next();
			if(chartData.getChildText("uniqueKey").equals(uniqueKey)){				
				this.isRecordPresent=true;
				break;	
			
		}
		}
		}
		return this.isRecordPresent;
	
		
	}
	
	public static String getFileName(){
		return fileName;
	}
	
	
}
