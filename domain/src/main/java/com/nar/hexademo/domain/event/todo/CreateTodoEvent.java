package com.nar.hexademo.domain.event.todo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CreateTodoEvent {
    private Long id;
    private String title;
}
