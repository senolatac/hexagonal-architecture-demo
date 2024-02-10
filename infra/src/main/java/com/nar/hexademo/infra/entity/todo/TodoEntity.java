package com.nar.hexademo.infra.entity.todo;

import com.nar.hexademo.domain.aggregate.todo.TodoAggregate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "todo")
public class TodoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "completed", nullable = false)
    private Boolean completed;

    public TodoAggregate toAggregate() {
        return TodoAggregate.builder()
                .id(id)
                .title(title)
                .userId(userId)
                .completed(completed)
                .build();
    }
}