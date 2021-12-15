package com.example.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.todo.entity.Connection;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection, Integer> {

	@Query(value = "select * from connection where connected_by = :first and connected_to = :second", nativeQuery = true)
	Connection findByConnectedByAndConnectedTo(@Param(value = "first") Integer first,@Param(value = "second") Integer second);
	
	List<Connection> findByConnectedBy(Integer userId);
    List<Connection> findByConnectedTo(Integer userId);
	
}
