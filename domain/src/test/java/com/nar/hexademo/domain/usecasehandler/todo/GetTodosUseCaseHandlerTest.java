package com.nar.hexademo.domain.usecasehandler.todo;

import com.nar.hexademo.domain.adapters.TodoRestFakeAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GetTodosUseCaseHandlerTest {
    private GetTodosUseCaseHandler useCaseHandler;

    @BeforeEach
    void setUp() {
        useCaseHandler = new GetTodosUseCaseHandler(
                new TodoRestFakeAdapter()
        );
    }

    @Test
    void handle_success() {
        var result = useCaseHandler.handle();

        assertThat(result).hasSize(3);
        assertThat(result.get(0).getTitle()).isEqualTo("test-title-1");
    }
}