package com.nar.hexademo.domain.port.todo;

import com.nar.hexademo.domain.aggregate.todo.TodoAggregate;
import com.nar.hexademo.domain.usecase.todo.CreateTodoUseCase;

public interface TodoDataPort {
    TodoAggregate save(CreateTodoUseCase useCase);
}
