package com.example.demo.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.beans.ConnectionVo;
import com.example.demo.manager.ConnectionManager;
import com.example.demo.manager.ExceedConnectons;
import com.example.demo.manager.Executeservice;
import com.example.demo.manager.Schedulerservice;

@RestController
@CrossOrigin
//@RequestMapping(value = "/rest/connection")
public class ConnectionController {

	@Autowired
	private ConnectionManager connectionManager;

	@Autowired
	Schedulerservice scheduler;

	@Autowired
	Executeservice execution;
	
	@Autowired
	ExceedConnectons exceed;

	
	
	@RequestMapping(path = "/start")
	public void startapi() throws IOException, InterruptedException {
		scheduler.setflag(true);

	}

	@RequestMapping(value = "/getconnectionDetails1", method = RequestMethod.GET, produces = "application/json")
	public List<ConnectionVo> getconnectiondetails() throws SQLException {
		return connectionManager.getconnectionnumber();

	}
	
	@RequestMapping(value = "/deleteschemas", method = RequestMethod.GET)
	public void deleteschemas() throws SQLException {
		 connectionManager.deleteSchemas();

	}

	// @RequestMapping(value = "/connectionData", method = RequestMethod.GET,
	// produces = "application/json")
	 @Scheduled(cron="${cronexecute}")
	//@Scheduled(cron = "0 0 12 1/2 * ?")
	public void getConnectionsDaily() throws SQLException {

		execution.executeservice();

	}
	 
	 @Scheduled(cron="${cronexceed}")
	 public void getexceedConnections() throws SQLException {
		 exceed.executeservice();
	 }

	@RequestMapping(value = "/postgresconnection", method = RequestMethod.GET, produces = "application/json")
	public String postgres() {
		scheduler.connect();
		return "Success";

	}

	@RequestMapping(value = "/getconnectionDetails", method = RequestMethod.POST, produces = "application/json")
	public ConnectionVo getconnectiondetails(@RequestBody String connecttype) throws SQLException {
		HashMap<String, ConnectionVo> connectionnumber= connectionManager.connectionnumber(connecttype);
		//List<ConnectionVo>  connlist = new ArrayList<>();
		
		if(connecttype.equalsIgnoreCase("QA"))
		{ConnectionVo connobjqa= connectionnumber.get("QA");
		return connobjqa;
		}else if(connecttype.equalsIgnoreCase("DEV"))
		{
			ConnectionVo connobjdev= connectionnumber.get("DEV");
		    return connobjdev;
		}else {
			ConnectionVo connobjdemo= connectionnumber.get("DEMO");
			return connobjdemo;
		}
		 //connlist.add(connobjdev);
//		}else {
//			ConnectionVo connobjqa= connectionnumber.get("qa");
//			connlist.add(connobjqa);
//			ConnectionVo connobjdev= connectionnumber.get("dev");
//			connlist.add(connobjdev);
//		}
		//return connlist;
		

	}

	@RequestMapping(value = "/getconnectionDetails4", method = RequestMethod.GET,
	 produces = "application/json")
	//@Scheduled(cron = "0 32 12 * * ?")
	public HashMap<String, ConnectionVo> getconnectiondetails12() throws SQLException {
		return connectionManager.connectionnumber("");

	}

}
