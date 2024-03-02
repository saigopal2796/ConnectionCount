package com.example.demo.manager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.example.demo.beans.ConnectionVo;

@Component
public class TemplateBuilder {

	private Environment env;
	@Value("${messageheader}")
	String header;
	@Value("${messagefooter}")
	String footer;

	public String template(List<ConnectionVo> report)
	{
		//System.out.println(reportdata);
		/*
		 * for (Reportdata num : report){
		 * System.out.println("output testing output testing" + num.getServicename()); }
		 */
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String date = sdf.format(new Date());
		String message =null;
		 message="<html><style> " + 
				"#customers { " + 
				"  font-family: \"Trebuchet MS\", Arial, Helvetica, sans-serif; " + 
				"  border-collapse: collapse; " + 
				"  width: 100%; " + 
				"} " + 
				" " + 
				"#customers td, #customers th { " + 
				"  border: 1px solid #ddd; " + 
				"  padding: 8px; " + 
				"} " + 
				" " + 
				"#customers tr:nth-child(even){background-color: #f2f2f2;} " + 
				" " + 
				"#customers tr:hover {background-color: #ddd;} " + 
				" " + 
				"#customers th { " + 
				"  padding-top: 12px; " + 
				"  padding-bottom: 12px; " + 
				"  text-align: left; " + 
				"  background-color: #4CAF50; " + 
				"  color: white; " + 
				"} " + 
				"</style><body>"
				+ header+date+"<br><br>"+"<table id='customers'><tr>" + 
				"<th>Environment</th>"+ 
				"<th>Total</th>"+ 
				"<th>Active</th>"+ 
				"<th>Idle</th>"+ 
				//"<th>Test Runs</th>"+ 
				"</tr>";
		 for (ConnectionVo num : report){ 
		 message =	message+ "<tr><td>"+num.getConnectionserver()+"</td><td>"+num.getTotal()+"</td><td>"+num.getActive()+"</td><td>"+num.getIdle()+"</td></tr>";
				
		 }
				message=message
						
						+"</table><br>"+footer+""
						+ "<body></html>";
			String data=message.replaceAll("null", "-");
			System.out.println(data);
			return data;
			 
		//return null;
			 
	}

}
