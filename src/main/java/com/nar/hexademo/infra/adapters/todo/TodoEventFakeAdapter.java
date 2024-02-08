package com.nar.hexademo.infra.adapters.todo;

import com.nar.hexademo.domain.event.todo.CreateTodoEvent;
import com.nar.hexademo.domain.port.todo.TodoEventPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TodoEventFakeAdapter implements TodoEventPort {

    @Override
    public void publish(CreateTodoEvent event) {
        log.info("Sent event: {}", event);
    }
}
