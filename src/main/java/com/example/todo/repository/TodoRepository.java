package com.example.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todo.entity.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {

	List<Todo> findByIsActive(Boolean isActive);
	List<Todo> findByUserId(Integer id);
}
