package com.example.demo.manager;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.example.demo.beans.ConnectionVo;

public interface ConnectionManager {
	
	
public ConnectionVo connectionnumber();

public HashMap<String,ConnectionVo> connectionnumber(String connecttype) throws SQLException;

public ConnectionVo devconn() throws SQLException;

public ConnectionVo qaconn() throws SQLException;

public ConnectionVo democonn() throws SQLException;

public List<ConnectionVo> getconnectionnumber() throws SQLException;

public void deleteSchemas() throws SQLException;
}
