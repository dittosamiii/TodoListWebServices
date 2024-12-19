package com.springboot.service;

import java.util.List;

import com.springboot.dto.TodoDto;

public interface TodoService {
	TodoDto addTodo(TodoDto todoDto);

	TodoDto getTodo(Long id);

	List<TodoDto> getAllTodos();

	TodoDto updateTodo(TodoDto todoDto, Long id);

	void deleteTodo(Long id);

	TodoDto completeTodo(Long id);

	TodoDto inCompleteTodo(Long id);
}
