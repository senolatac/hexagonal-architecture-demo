package com.nar.hexademo.domain.adapters;

import com.nar.hexademo.domain.aggregate.todo.TodoAggregate;
import com.nar.hexademo.domain.port.todo.TodoRestPort;
import com.nar.hexademo.domain.usecase.todo.CreateTodoUseCase;

import java.util.List;

public class TodoRestFakeAdapter implements TodoRestPort {
    @Override
    public TodoAggregate createTodo(CreateTodoUseCase useCase) {
        return TodoAggregate.builder()
                .id(1L)
                .title(useCase.getTitle())
                .userId(useCase.getUserId())
                .completed(useCase.getCompleted())
                .build();
    }

    @Override
    public List<TodoAggregate> getAllTodos() {
        return List.of(
                createAggregate(1L),
                createAggregate(2L),
                createAggregate(3L)
        );
    }

    private TodoAggregate createAggregate(Long id) {
        return TodoAggregate.builder()
                .id(id)
                .title("test-title-" + id)
                .userId(id)
                .completed(true)
                .build();
    }
}
