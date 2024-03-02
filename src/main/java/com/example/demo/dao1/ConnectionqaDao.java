package com.example.demo.dao1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.beans.ConnectionVo;


@Repository
public interface ConnectionqaDao extends JpaRepository<ConnectionVo,Integer> {

}