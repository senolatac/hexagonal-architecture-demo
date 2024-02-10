package com.nar.hexademo.infra.adapters.todo;

import com.nar.hexademo.infra.TestUtils;
import com.nar.hexademo.domain.aggregate.todo.TodoAggregate;
import com.nar.hexademo.domain.usecase.todo.CreateTodoUseCase;
import com.nar.hexademo.infra.configuration.RestConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@AutoConfigureDataJpa
@RestClientTest(components = {RestConfiguration.class, TodoRestAdapter.class})
class TodoRestAdapterTest {

    @Autowired
    private TodoRestAdapter todoAdapter;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer server;

    @BeforeEach
    public void setUp() {
        server = MockRestServiceServer.bindTo(restTemplate).build();
    }

    @Test
    void createTodo() throws Exception {
        String responseContent = TestUtils.getResourceContent("json/todo/create-todo-result.json");
        CreateTodoUseCase useCase = CreateTodoUseCase.builder()
                .title("test")
                .completed(true)
                .userId(1L)
                .build();

        server.expect(requestToUriTemplate(TodoRestAdapter.TODOS_URL))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(responseContent, MediaType.APPLICATION_JSON));

        var result = todoAdapter.createTodo(useCase);

        server.verify();
        assertThat(result).isNotNull()
                .returns(201L, TodoAggregate::getId);
    }

    @Test
    void getAllTodos() throws Exception {
        String content = TestUtils.getResourceContent("json/todo/todo-list-result.json");

        server.expect(requestToUriTemplate(TodoRestAdapter.TODOS_URL))
                .andRespond(withSuccess(content, MediaType.APPLICATION_JSON));

        var result = todoAdapter.getAllTodos();

        server.verify();
        assertThat(result).hasSize(3);
    }
}