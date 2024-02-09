package com.nar.hexademo.application.controller.transfer.todo;

import com.nar.hexademo.domain.aggregate.todo.TodoAggregate;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class TodoResponseDto {
    private Long id;
    private Long userId;
    private String title;
    private Boolean completed;

    public static TodoResponseDto fromAggregate(TodoAggregate aggregate) {
        return TodoResponseDto.builder()
                .id(aggregate.getId())
                .title(aggregate.getTitle())
                .userId(aggregate.getUserId())
                .completed(aggregate.getCompleted())
                .build();
    }

    public static List<TodoResponseDto> fromAggregateList(List<TodoAggregate> list) {
        return list.stream()
                .map(TodoResponseDto::fromAggregate)
                .toList();
    }
}
