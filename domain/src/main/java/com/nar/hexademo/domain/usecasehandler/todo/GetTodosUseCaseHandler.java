package com.nar.hexademo.domain.usecasehandler.todo;

import com.nar.hexademo.domain.common.DomainComponent;
import com.nar.hexademo.domain.port.todo.TodoRestPort;
import com.nar.hexademo.domain.aggregate.todo.TodoAggregate;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainComponent
@RequiredArgsConstructor
public class GetTodosUseCaseHandler {
    private final TodoRestPort todoRestPort;

    public List<TodoAggregate> handle() {
        //validate(useCase);

        return todoRestPort.getAllTodos();
    }
}
