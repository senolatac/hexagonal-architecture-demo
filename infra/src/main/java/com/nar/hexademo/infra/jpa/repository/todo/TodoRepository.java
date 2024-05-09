package com.nar.hexademo.infra.jpa.repository.todo;

import com.nar.hexademo.infra.entity.todo.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TodoRepository extends JpaRepository<TodoEntity, Long>, JpaSpecificationExecutor<TodoEntity> {
}
