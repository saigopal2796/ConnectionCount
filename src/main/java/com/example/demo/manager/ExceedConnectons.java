package com.example.demo.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.example.demo.beans.ConnectionVo;

@Service
public class ExceedConnectons {

	private static final Logger log = LoggerFactory.getLogger(Executeservice.class);

	@Autowired
	private ConnectionManager connectionManager;

	@Autowired
	private TemplateBuilder builder;

	@Autowired
	private Sendemailservice sendmail;

	@Autowired
	private Environment env;
	
	@Value("${percentage}")
	float percentage;
	
    public void executeservice() throws SQLException {

		String[] server = { "QA", "DEV","DEMO" };
		List<ConnectionVo> connectionvo = new ArrayList<ConnectionVo>();
		Date date = new Date();

		for (int i = 0; i < server.length; i++) {

			ConnectionVo connection = new ConnectionVo();
			connection.setConnectionserver(server[i]);
			log.info("\n\n**************  Getting connection details **************\n\n");
			// ConnectionVo connections = connectionManager.connectionnumber();
			HashMap<String, ConnectionVo> connectionsData = connectionManager.connectionnumber(server[i]);

			connection.setActive(connectionsData.get(server[i]).getActive());
			connection.setIdle(connectionsData.get(server[i]).getIdle());
			connection.setTotal(connectionsData.get(server[i]).getTotal());

			connectionvo.add(connection);

		}

		/*
		 * String body = builder.template(connectionvo);
		 * 
		 * System.out.println("message " + body);
		 * 
		 * log.info("\n\n**************  Sending Mails **************\n\n"); try {
		 * sendmail.sendMail(env.getProperty("mailto"), env.getProperty("mailsubject") +
		 * " " + date, env.getProperty("mailfrom"), body); } catch (MessagingException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */

		
		  if((connectionvo.get(0).getActive() > (percentage*connectionvo.get(0).getTotal())) || (connectionvo.get(1).getActive() > (percentage*connectionvo.get(1).getTotal())) || (connectionvo.get(2).getActive() > (percentage*connectionvo.get(2).getTotal()))) {
		   log.info("\n\n**************  Building template **************\n\n");
		  String body = builder.template(connectionvo);
		  System.out.println("message " + body);
		  
		  
		  log.info("\n\n**************  Sending Mails **************\n\n"); 
		  try {
			  //List<String> list = new ArrayList<String>(Arrays.asList(env.getProperty("mailto").split(" , ")));
	        // for(String l: list) {
		  sendmail.sendMail(env.getProperty("mailto"), env.getProperty("mailexceed"), env.getProperty("mailfrom"), body); 
	             //  }
		  } catch (MessagingException e) {
		   // TODO Auto-generated catch block
			  e.printStackTrace(); 
			  }
		  }
		  
		   else {
			   System.out.println("ended");
			   }
		 

	}

}
