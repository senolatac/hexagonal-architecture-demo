package com.nar.hexademo.application.controller.transfer.todo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateTodoRequestDto {
    @NotNull
    private Long userId;

    @NotBlank
    private String title;

    @NotNull
    private Boolean completed;
}
