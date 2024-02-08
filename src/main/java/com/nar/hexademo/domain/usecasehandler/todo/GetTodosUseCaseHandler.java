package com.nar.hexademo.domain.usecasehandler.todo;

import com.nar.hexademo.domain.DomainComponent;
import com.nar.hexademo.domain.aggregate.todo.TodoAggregate;
import com.nar.hexademo.domain.port.todo.TodoPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainComponent
@RequiredArgsConstructor
public class GetTodosUseCaseHandler {
    private final TodoPort todoPort;

    public List<TodoAggregate> handle() {
        //validate(useCase);

        return todoPort.getAllTodos();
    }
}
