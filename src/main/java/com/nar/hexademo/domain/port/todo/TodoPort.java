package com.nar.hexademo.domain.port.todo;

import com.nar.hexademo.domain.aggregate.todo.TodoAggregate;
import com.nar.hexademo.domain.usecase.todo.CreateTodoUseCase;

import java.util.List;

public interface TodoPort {
    TodoAggregate createTodo(CreateTodoUseCase useCase);
    List<TodoAggregate> getAllTodos();
}
