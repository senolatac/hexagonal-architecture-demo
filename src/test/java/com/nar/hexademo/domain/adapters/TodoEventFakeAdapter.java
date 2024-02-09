package com.nar.hexademo.domain.adapters;

import com.nar.hexademo.domain.event.todo.CreateTodoEvent;
import com.nar.hexademo.domain.port.todo.TodoEventPort;

public class TodoEventFakeAdapter implements TodoEventPort {
    @Override
    public void publish(CreateTodoEvent event) {
        //no need an extra action.
    }
}
