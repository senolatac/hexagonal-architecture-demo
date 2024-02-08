package com.nar.hexademo.domain.aggregate.todo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TodoAggregate {
    private Long id;
    private Long userId;
    private String title;
    private Boolean completed;
}
