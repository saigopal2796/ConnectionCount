package com.example.demo.dao2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.beans.ConnectionVo;


@Repository
public interface ConnectiondemoDao extends JpaRepository<ConnectionVo,Integer> {

}