package com.example.demo.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ConnectionVo {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private Integer active;

	private Integer idle;
	
	private Integer total;
	
	private String connectionserver;

	

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Integer getIdle() {
		return idle;
	}

	public void setIdle(Integer idle) {
		this.idle = idle;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getConnectionserver() {
		return connectionserver;
	}

	public void setConnectionserver(String connectionserver) {
		this.connectionserver = connectionserver;
	}
	
	
	
	

}
