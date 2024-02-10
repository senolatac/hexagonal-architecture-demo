package com.nar.hexademo.domain.usecasehandler.todo;

import com.nar.hexademo.domain.adapters.TodoDataFakeAdapter;
import com.nar.hexademo.domain.adapters.TodoEventFakeAdapter;
import com.nar.hexademo.domain.adapters.TodoRestFakeAdapter;
import com.nar.hexademo.domain.aggregate.todo.TodoAggregate;
import com.nar.hexademo.domain.usecase.todo.CreateTodoUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CreateTodoUseCaseHandlerTest {

    CreateTodoUseCaseHandler useCaseHandler;

    @BeforeEach
    void setUp() {
        useCaseHandler = new CreateTodoUseCaseHandler(
                new TodoRestFakeAdapter(),
                new TodoDataFakeAdapter(),
                new TodoEventFakeAdapter()
        );
    }

    @Test
    void handle_success() {
        CreateTodoUseCase useCase = CreateTodoUseCase.builder()
                .title("new-title")
                .userId(1L)
                .completed(true)
                .build();

        var aggregate = useCaseHandler.handle(useCase);

        assertThat(aggregate).isNotNull()
                .returns(1L, TodoAggregate::getId)
                .returns("new-title", TodoAggregate::getTitle)
                .returns(1L, TodoAggregate::getUserId)
                .returns(true, TodoAggregate::getCompleted);
    }
}