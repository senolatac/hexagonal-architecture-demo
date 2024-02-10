package com.nar.hexademo.domain.usecase.todo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateTodoUseCase {
    private Long userId;
    private String title;
    private Boolean completed;
}
