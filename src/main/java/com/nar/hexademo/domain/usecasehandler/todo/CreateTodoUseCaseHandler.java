package com.nar.hexademo.domain.usecasehandler.todo;

import com.nar.hexademo.domain.DomainComponent;
import com.nar.hexademo.domain.aggregate.todo.TodoAggregate;
import com.nar.hexademo.domain.event.todo.CreateTodoEvent;
import com.nar.hexademo.domain.port.todo.TodoEventPort;
import com.nar.hexademo.domain.port.todo.TodoPort;
import com.nar.hexademo.domain.usecase.todo.CreateTodoUseCase;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class CreateTodoUseCaseHandler {
    private final TodoPort todoPort;
    private final TodoEventPort todoEventPort;

    public TodoAggregate handle(CreateTodoUseCase useCase) {
        //validate(useCase);
        TodoAggregate todoAggregate = todoPort.createTodo(useCase);
        todoEventPort.publish(CreateTodoEvent.from(todoAggregate));

        return todoAggregate;
    }
}
