package com.example.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todo.entity.TodoShare;
import com.example.todo.entity.User;

@Repository
public interface SharedTodoRepository extends JpaRepository<TodoShare, Integer> {
	List<TodoShare> findBySharedBy(User user);
}
