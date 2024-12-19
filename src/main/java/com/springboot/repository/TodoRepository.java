package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
