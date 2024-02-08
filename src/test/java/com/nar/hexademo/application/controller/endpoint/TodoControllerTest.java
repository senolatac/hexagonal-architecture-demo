package com.nar.hexademo.application.controller.endpoint;

import com.nar.hexademo.domain.aggregate.todo.TodoAggregate;
import com.nar.hexademo.domain.usecasehandler.todo.CreateTodoUseCaseHandler;
import com.nar.hexademo.domain.usecasehandler.todo.GetTodosUseCaseHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TodoController.class)
class TodoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetTodosUseCaseHandler getTodosUseCaseHandler;

    @MockBean
    private CreateTodoUseCaseHandler createTodoUseCaseHandler;

    private static final Long TODO_ID_1 = 1L;

    @Test
    void getAllTodos() throws Exception {
        List<TodoAggregate> results = List.of(TodoAggregate.builder()
                .id(TODO_ID_1)
                .build());

        when(getTodosUseCaseHandler.handle()).thenReturn(results);

        mockMvc.perform(get("/api/v1/todos/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.[0].id", is(TODO_ID_1.intValue())))
                .andExpect(jsonPath("$", hasSize(1)));

        verify(getTodosUseCaseHandler).handle();
    }

    @Test
    void createTodo() {
    }
}