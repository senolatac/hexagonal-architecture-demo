package com.nar.hexademo.infra.repository.todo;

import com.nar.hexademo.infra.entity.todo.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
}
