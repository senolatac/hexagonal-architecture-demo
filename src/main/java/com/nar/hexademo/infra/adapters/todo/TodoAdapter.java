package com.nar.hexademo.infra.adapters.todo;

import com.nar.hexademo.domain.aggregate.todo.TodoAggregate;
import com.nar.hexademo.domain.port.todo.TodoPort;
import com.nar.hexademo.domain.usecase.todo.CreateTodoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoAdapter implements TodoPort {
    private final RestTemplate restTemplate;

    private static final String TODOS_URL = "https://jsonplaceholder.typicode.com/todos";

    @Override
    public TodoAggregate createTodo(CreateTodoUseCase useCase) {
        var response = restTemplate.postForEntity(TODOS_URL, useCase, TodoAggregate.class);

        if (response.getBody() == null) {
            throw new RuntimeException("Unexpected response..."); //specify Exception
        }
        return response.getBody();
    }

    @Override
    public List<TodoAggregate> getAllTodos() {
        ParameterizedTypeReference<List<TodoAggregate>> responseType = new ParameterizedTypeReference<>() {};
        var response = restTemplate.exchange(TODOS_URL, HttpMethod.GET, null, responseType);

        if (response.getBody() == null) {
            throw new RuntimeException("Unexpected response..."); //specify Exception
        }
        return response.getBody();
    }
}
