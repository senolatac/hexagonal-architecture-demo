package com.nar.hexademo.application.controller.transfer.todo;

import com.nar.hexademo.domain.usecase.todo.CreateTodoUseCase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateTodoRequestDto {
    @NotNull
    private Long userId;

    @NotBlank
    private String title;

    @NotNull
    private Boolean completed;

    public CreateTodoUseCase toUseCase() {
        return CreateTodoUseCase.builder()
                .userId(userId)
                .title(title)
                .completed(completed)
                .build();
    }
}
