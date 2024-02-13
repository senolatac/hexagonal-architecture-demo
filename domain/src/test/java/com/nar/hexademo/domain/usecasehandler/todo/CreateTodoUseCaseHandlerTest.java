package com.nar.hexademo.domain.usecasehandler.todo;

import com.nar.hexademo.domain.adapters.TodoDataFakeAdapter;
import com.nar.hexademo.domain.adapters.TodoEventFakeAdapter;
import com.nar.hexademo.domain.adapters.TodoRestFakeAdapter;
import com.nar.hexademo.domain.aggregate.todo.TodoAggregate;
import com.nar.hexademo.domain.event.todo.CreateTodoEvent;
import com.nar.hexademo.domain.usecase.todo.CreateTodoUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreateTodoUseCaseHandlerTest {

    private CreateTodoUseCaseHandler useCaseHandler;

    @Spy
    private TodoEventFakeAdapter todoEventFakeAdapter = new TodoEventFakeAdapter();

    @Captor
    private ArgumentCaptor<CreateTodoEvent> eventArgumentCaptor;

    @BeforeEach
    void setUp() {
        useCaseHandler = new CreateTodoUseCaseHandler(
                new TodoRestFakeAdapter(),
                new TodoDataFakeAdapter(),
                todoEventFakeAdapter
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
        assertThat(aggregate.toString()).isNotBlank();
        verify(todoEventFakeAdapter).publish(eventArgumentCaptor.capture());
        assertThat(eventArgumentCaptor.getValue()).isNotNull()
                .returns(1L, CreateTodoEvent::getId)
                .returns("new-title", CreateTodoEvent::getTitle);
        assertThat(eventArgumentCaptor.getValue().toString()).isNotBlank();
    }
}