package com.nar.hexademo.domain.port.todo;

import com.nar.hexademo.domain.event.todo.CreateTodoEvent;

public interface TodoEventPort {
    void publish(CreateTodoEvent event);
}
