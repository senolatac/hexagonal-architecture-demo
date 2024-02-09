package com.nar.hexademo.domain.usecasehandler.todo;

import com.nar.hexademo.domain.common.DomainComponent;
import com.nar.hexademo.domain.aggregate.todo.TodoAggregate;
import com.nar.hexademo.domain.event.todo.CreateTodoEvent;
import com.nar.hexademo.domain.port.todo.TodoDataPort;
import com.nar.hexademo.domain.port.todo.TodoEventPort;
import com.nar.hexademo.domain.port.todo.TodoRestPort;
import com.nar.hexademo.domain.usecase.todo.CreateTodoUseCase;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class CreateTodoUseCaseHandler {
    private final TodoRestPort todoRestPort;
    private final TodoDataPort todoDataPort;
    private final TodoEventPort todoEventPort;

    public TodoAggregate handle(CreateTodoUseCase useCase) {
        validate(useCase);
        TodoAggregate todoAggregate = todoRestPort.createTodo(useCase);
        todoDataPort.save(useCase);
        todoEventPort.publish(CreateTodoEvent.from(todoAggregate));

        return todoAggregate;
    }

    private void validate(CreateTodoUseCase useCase) {
        //implement custom validations.
    }
}
