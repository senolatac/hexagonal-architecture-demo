package com.nar.hexademo.infra.jpa.repository.todo;

import com.nar.hexademo.infra.entity.todo.TodoEntity;
import com.nar.hexademo.infra.jpa.specs.todo.TodoSpecs;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    private static final String TITLE = "test";

    @Test
    void findByTitle() {
        saveTodo();

        Optional<TodoEntity> todoEntity1 = todoRepository.findOne(TodoSpecs.byTitle(TITLE));
        Optional<TodoEntity> todoEntity2 = todoRepository.findOne(TodoSpecs.byUserIdAndTitle(1L, TITLE));
        Optional<TodoEntity> todoEntity3 = todoRepository.findOne(TodoSpecs.byUserIdAndTitle(null, TITLE));

        assertThat(todoEntity1.isPresent()).isTrue();
        assertThat(todoEntity2.isPresent()).isTrue();
        assertThat(todoEntity3.isPresent()).isTrue();
    }

    private void saveTodo() {
        TodoEntity todo = TodoEntity.builder()
                .title(TITLE)
                .userId(1L)
                .completed(true)
                .build();
        todoRepository.save(todo);
    }

}