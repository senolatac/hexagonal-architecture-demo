package com.nar.hexademo.domain.adapters;

import com.nar.hexademo.domain.aggregate.todo.TodoAggregate;
import com.nar.hexademo.domain.port.todo.TodoDataPort;
import com.nar.hexademo.domain.usecase.todo.CreateTodoUseCase;

public class TodoDataFakeAdapter implements TodoDataPort {
    @Override
    public TodoAggregate save(CreateTodoUseCase useCase) {
        return TodoAggregate.builder()
                .id(1L)
                .userId(useCase.getUserId())
                .title(useCase.getTitle())
                .completed(useCase.getCompleted())
                .build();
    }
}
