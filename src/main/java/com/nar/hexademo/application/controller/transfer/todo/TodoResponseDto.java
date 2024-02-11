package com.nar.hexademo.application.controller.transfer.todo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TodoResponseDto {
    private Long id;
    private Long userId;
    private String title;
    private Boolean completed;
}
