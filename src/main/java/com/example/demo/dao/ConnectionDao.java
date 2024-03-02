package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.beans.ConnectionVo;


@Repository
public interface ConnectionDao extends JpaRepository<ConnectionVo,Integer> {

}
