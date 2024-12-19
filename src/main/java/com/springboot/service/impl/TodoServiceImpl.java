package com.springboot.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.springboot.dto.TodoDto;
import com.springboot.entity.Todo;
import com.springboot.exception.ResourceNotFoundException;
import com.springboot.repository.TodoRepository;
import com.springboot.service.TodoService;

@Service
public class TodoServiceImpl implements TodoService {

	private TodoRepository todoRepository;
	private ModelMapper modelMapper;

	public TodoServiceImpl(TodoRepository todoRepository, ModelMapper modelMapper) {
		super();
		this.todoRepository = todoRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public TodoDto addTodo(TodoDto todoDto) {
		// Convert into todo
		Todo todo = modelMapper.map(todoDto, Todo.class);
		Todo savedTodo = todoRepository.save(todo);
		// convert into todoDto
		TodoDto savedTodoDto = modelMapper.map(savedTodo, TodoDto.class);

		return savedTodoDto;
	}

	@Override
	public TodoDto getTodo(Long id) {
		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found with ID: " + id));
		return modelMapper.map(todo, TodoDto.class);
	}

	@Override
	public List<TodoDto> getAllTodos() {
		List<Todo> todos = todoRepository.findAll();
		return todos.stream().map((todo) -> modelMapper.map(todo, TodoDto.class)).collect(Collectors.toList());
	}

	@Override
	public TodoDto updateTodo(TodoDto todoDto, Long id) {
		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found with ID: " + id));

		todo.setTitle(todoDto.getTitle());
		todo.setDescription(todoDto.getDescription());
		todo.setCompleted(todoDto.isCompleted());

		Todo updatedTodo = todoRepository.save(todo);

		return modelMapper.map(updatedTodo, TodoDto.class);

	}

	@Override
	public void deleteTodo(Long id) {
		todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found with ID: " + id));
		todoRepository.deleteById(id);
	}

	@Override
	public TodoDto completeTodo(Long id) {
		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found with ID: " + id));

		todo.setCompleted(true);
		Todo updatedTodo = todoRepository.save(todo);
		return modelMapper.map(updatedTodo, TodoDto.class);
	}

	@Override
	public TodoDto inCompleteTodo(Long id) {
		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found with ID: " + id));

		todo.setCompleted(false);
		Todo updatedTodo = todoRepository.save(todo);
		return modelMapper.map(updatedTodo, TodoDto.class);
	}

}
