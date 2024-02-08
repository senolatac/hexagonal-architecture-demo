package com.nar.hexademo.infra.adapters.todo;

import com.nar.hexademo.TestUtils;
import com.nar.hexademo.infra.configuration.RestConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestToUriTemplate;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(components = {RestConfiguration.class, TodoAdapter.class})
class TodoAdapterTest {

    @Autowired
    private TodoAdapter todoAdapter;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer server;

    @BeforeEach
    public void setUp() {
        server = MockRestServiceServer.bindTo(restTemplate).build();
    }

    @Test
    void createTodo() {
    }

    @Test
    void getAllTodos() throws Exception {
        String content = TestUtils.getResourceContent("json/todo-list-result.json");

        server.expect(requestToUriTemplate(TodoAdapter.TODOS_URL))
                .andRespond(withSuccess(content, MediaType.APPLICATION_JSON));

        var result = todoAdapter.getAllTodos();

        server.verify();
        assertThat(result).hasSize(3);
    }
}