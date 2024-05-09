package com.nar.hexademo.infra.jpa.specs.todo;

import com.nar.hexademo.infra.entity.todo.TodoEntity;
import com.nar.hexademo.infra.entity.todo.TodoEntity_;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import static org.springframework.data.jpa.domain.Specification.where;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TodoSpecs {

    public static Specification<TodoEntity> byTitle(String title) {
        return (root, query, builder) ->
                builder.equal(root.get(TodoEntity_.TITLE), title);
    }

    public static Specification<TodoEntity> byUserId(Long userId) {
        return (root, query, builder) ->
                builder.equal(root.get(TodoEntity_.USER_ID), userId);
    }

    public static Specification<TodoEntity> byUserIdAndTitle(Long userId, String title) {
        Specification<TodoEntity> spec = where(null);
        if (userId != null) {
            spec = spec.and(byUserId(userId));
        }
        if (title != null) {
            spec = spec.and(byTitle(title));
        }
        return spec;
    }
}
