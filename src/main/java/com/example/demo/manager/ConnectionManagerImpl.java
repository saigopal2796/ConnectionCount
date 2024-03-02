package com.example.demo.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.beans.ConnectionVo;
import com.example.demo.config.DB1_DataSource;
import com.example.demo.config.DB2_DataSource;
import com.example.demo.config.DB3_DataSource;
import com.example.demo.dao.ConnectionDao;

@Service
public class ConnectionManagerImpl implements ConnectionManager {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	private ConnectionDao connectionDao;

	@Autowired
	private DB1_DataSource dataSource1;

	@Autowired
	private DB2_DataSource dataSource2;
	
	@Autowired
	private DB3_DataSource dataSource3;

	@Override
	public ConnectionVo connectionnumber() {

		EntityManager em = entityManagerFactory.createEntityManager();
		String str = "SELECT count(*) FROM pg_stat_activity";
		String str1 = "SELECT count(*) FROM pg_stat_activity where state='active'";
		String str2 = "SELECT count(*) FROM pg_stat_activity where state='idle'";
		em.getTransaction().begin();
		Query q1 = em.createNativeQuery(str);
		int total = ((Number) q1.getSingleResult()).intValue();
		ConnectionVo connectionvo = new ConnectionVo();
		connectionvo.setTotal(total);
		Query q2 = em.createNativeQuery(str1);
		int active = ((Number) q2.getSingleResult()).intValue();
		connectionvo.setActive(active);
		Query q3 = em.createNativeQuery(str2);
		int idle = ((Number) q3.getSingleResult()).intValue();
		connectionvo.setIdle(idle);
		em.getTransaction().commit();
		em.close();
		System.out.println(connectionvo);
		return connectionvo;
	}

	@Override
	public HashMap<String, ConnectionVo> connectionnumber(String connecttype) throws SQLException {

		List<ConnectionVo> connlist = new ArrayList<>();
		HashMap<String, ConnectionVo> env = new HashMap<>();
		connlist.add(devconn());
		connlist.add(qaconn());
		connlist.add(democonn());
		if (connecttype.equalsIgnoreCase("QA")) {
			connlist.get(1).setConnectionserver(connecttype);
			env.put("QA", connlist.get(1));
		} else if (connecttype.equalsIgnoreCase("DEV")) {
			connlist.get(0).setConnectionserver(connecttype);
			env.put("DEV", connlist.get(0));
		} else if(connecttype.equalsIgnoreCase("DEMO")){
			connlist.get(2).setConnectionserver(connecttype);
			env.put("DEMO", connlist.get(2));
		}
		else {
			connlist.get(1).setConnectionserver("QA");
			connlist.get(0).setConnectionserver("DEV");
			connlist.get(2).setConnectionserver("DEMO");
			env.put("QA", connlist.get(1));
			env.put("DEV", connlist.get(0));
			env.put("DEMO", connlist.get(2));
		}
		return env;

	}
	
	

	@Override
	public ConnectionVo devconn() throws SQLException {
		Connection conn = dataSource1.db1Datasource().getConnection();
		String str = "SELECT count(*),(SELECT COUNT(*) FROM pg_stat_activity WHERE state='idle'),(SELECT COUNT(*) FROM pg_stat_activity WHERE state='active') FROM pg_stat_activity";
		PreparedStatement pstmt = conn.prepareStatement(str);
		ResultSet result = pstmt.executeQuery();
		int total = 0;
		int idle = 0;
		int active = 0;
		while (result.next()) {
			total = result.getInt(1);
			idle = result.getInt(2);
			active = result.getInt(3);
			System.out.println("dev "  +total);
		}
		ConnectionVo connectionvo = new ConnectionVo();
		connectionvo.setTotal(total);
		connectionvo.setIdle(idle);
		connectionvo.setActive(active);
		conn.close();
		return connectionvo;

	}
	
	@Override
	public void deleteSchemas() throws SQLException {
		//dev 
		Connection conn = dataSource1.db1Datasource().getConnection();
		//qa 
		//Connection conn = dataSource2.db2Datasource().getConnection();
		//demo 
		//Connection conn = dataSource3.db3Datasource().getConnection();
		List<String> schemaList = new ArrayList<String>();
		String str = "SELECT tenant_id from tenant_master where active = false";
		PreparedStatement pstmt = conn.prepareStatement(str);
		ResultSet result = pstmt.executeQuery();
		while (result.next()) {
			String schema=  result.getString(1);
			schemaList.add(schema);
		}
		
		schemaList.forEach(x-> {
			System.out.println(x);
			String str1 = "DROP SCHEMA IF EXISTS " + '"' + x + '"' + " CASCADE";
			try {
				PreparedStatement pstmt1 = conn.prepareStatement(str1);
				int y = pstmt1.executeUpdate();
				System.out.println(y);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
		conn.close();
		
	}

	@Override
	public ConnectionVo qaconn() throws SQLException {
		Connection conn = dataSource2.db2Datasource().getConnection();
		String str = "SELECT count(*),(SELECT COUNT(*) FROM pg_stat_activity WHERE state='idle'),(SELECT COUNT(*) FROM pg_stat_activity WHERE state='active') FROM pg_stat_activity";
		PreparedStatement pstmt = conn.prepareStatement(str);
		ResultSet result = pstmt.executeQuery();
		int total = 0;
		int idle = 0;
		int active = 0;
		while (result.next()) {
			total = result.getInt(1);
			idle = result.getInt(2);
			active = result.getInt(3);
			System.out.println("qa "  +total);
		}
		ConnectionVo connectionvo = new ConnectionVo();
		connectionvo.setTotal(total);
		connectionvo.setIdle(idle);
		connectionvo.setActive(active);
		conn.close();
		return connectionvo;
	}

	@Override
	public ConnectionVo democonn() throws SQLException {
		Connection conn = dataSource3.db3Datasource().getConnection();
		String str = "SELECT count(*),(SELECT COUNT(*) FROM pg_stat_activity WHERE state='idle'),(SELECT COUNT(*) FROM pg_stat_activity WHERE state='active') FROM pg_stat_activity";
		PreparedStatement pstmt = conn.prepareStatement(str);
		ResultSet result = pstmt.executeQuery();
		int total = 0;
		int idle = 0;
		int active = 0;
		while (result.next()) {
			total = result.getInt(1);
			idle = result.getInt(2);
			active = result.getInt(3);
			System.out.println("demo "  +total);
		}
		ConnectionVo connectionvo = new ConnectionVo();
		connectionvo.setTotal(total);
		connectionvo.setIdle(idle);
		connectionvo.setActive(active);
		conn.close();
		return connectionvo;
	}

	@Override
	public List<ConnectionVo> getconnectionnumber() throws SQLException {
		List<ConnectionVo> connlist = new ArrayList<>();
		HashMap<String, ConnectionVo> env = new HashMap<>();
		connlist.add(devconn());
		connlist.add(qaconn());
		connlist.add(democonn());
		connlist.get(1).setConnectionserver("QA");
		connlist.get(0).setConnectionserver("DEV");
		connlist.get(2).setConnectionserver("DEMO");
		return connlist;
	}

}
