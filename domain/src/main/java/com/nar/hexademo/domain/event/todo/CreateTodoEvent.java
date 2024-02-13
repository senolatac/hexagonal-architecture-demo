package com.nar.hexademo.domain.event.todo;

import com.nar.hexademo.domain.aggregate.todo.TodoAggregate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateTodoEvent {
    private Long id;
    private String title;

    public static CreateTodoEvent from(TodoAggregate aggregate) {
        return CreateTodoEvent.builder()
                .id(aggregate.getId())
                .title(aggregate.getTitle())
                .build();
    }
}
