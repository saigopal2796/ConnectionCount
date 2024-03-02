package com.example.demo.manager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Schedulerservice {

	

	@Autowired
	Executeservice executeservice;

	private Boolean flag = true;

	public void setflag(Boolean data) {
		this.flag = data;
	}

	public Boolean getflag() {
		return this.flag;
	}

	//@Scheduled(cron = "*/1 * * * * ?")
	//@Scheduled(fixedDelay = (60000), initialDelay = 10000)
	public void scheduler() throws IOException, InterruptedException, SQLException {
		if (this.getflag()) {
			System.out.println("->Process Started");
			// connectionManager.connectionnumber();
			executeservice.executeservice();

		} else {
			System.out.println("->Not Started ");
		}
	}
	
	
	private final String url = "jdbc:postgresql://localhost/postgres";
    private final String user = "postgres";
    private final String password = "postgres";

    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     */
    public Connection connect() {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
            stmt = conn.createStatement();
            String str="SELECT count(*) FROM pg_stat_activity";
             ResultSet ret = stmt.executeQuery(str);
            System.out.println("Return value is : " + ret.getInt(0) );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

}
