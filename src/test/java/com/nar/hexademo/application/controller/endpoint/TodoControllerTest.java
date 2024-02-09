package com.nar.hexademo.application.controller.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nar.hexademo.application.controller.transfer.todo.CreateTodoRequestDto;
import com.nar.hexademo.domain.aggregate.todo.TodoAggregate;
import com.nar.hexademo.domain.usecase.todo.CreateTodoUseCase;
import com.nar.hexademo.domain.usecasehandler.todo.CreateTodoUseCaseHandler;
import com.nar.hexademo.domain.usecasehandler.todo.GetTodosUseCaseHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureDataJpa
@WebMvcTest(controllers = TodoController.class)
class TodoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GetTodosUseCaseHandler getTodosUseCaseHandler;

    @MockBean
    private CreateTodoUseCaseHandler createTodoUseCaseHandler;

    private static final Long TODO_ID_1 = 1L;

    @Test
    void getAllTodos_success() throws Exception {
        List<TodoAggregate> results = List.of(createAggregate());

        when(getTodosUseCaseHandler.handle()).thenReturn(results);

        mockMvc.perform(get("/api/v1/todos/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.[0].id", is(TODO_ID_1.intValue())))
                .andExpect(jsonPath("$", hasSize(1)));

        verify(getTodosUseCaseHandler).handle();
    }

    @Test
    void createTodo_success() throws Exception {
        CreateTodoRequestDto requestDto = CreateTodoRequestDto.builder()
                .title("test")
                .completed(true)
                .userId(1L)
                .build();
        TodoAggregate result = createAggregate();

        when(createTodoUseCaseHandler.handle(any(CreateTodoUseCase.class))).thenReturn(result);

        mockMvc.perform(post("/api/v1/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.id", is(TODO_ID_1.intValue())));

        verify(createTodoUseCaseHandler).handle(any());
    }

    @Test
    void createTodo_notValidRequest() throws Exception {
        CreateTodoRequestDto requestDto = CreateTodoRequestDto.builder()
                .title("test")
                .completed(true)
                .build();

        mockMvc.perform(post("/api/v1/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().is4xxClientError());
    }

    private TodoAggregate createAggregate() {
        return TodoAggregate.builder()
                .id(TODO_ID_1)
                .build();
    }
}