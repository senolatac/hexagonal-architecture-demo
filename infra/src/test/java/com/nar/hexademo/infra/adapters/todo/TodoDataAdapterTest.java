package com.nar.hexademo.infra.adapters.todo;

import com.nar.hexademo.domain.aggregate.todo.TodoAggregate;
import com.nar.hexademo.domain.usecase.todo.CreateTodoUseCase;
import com.nar.hexademo.infra.entity.todo.TodoEntity;
import com.nar.hexademo.infra.repository.todo.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TodoDataAdapterTest {

    @InjectMocks
    private TodoDataAdapter todoDataAdapter;

    @Mock
    private TodoRepository todoRepository;

    @Test
    void save_success() {
        CreateTodoUseCase useCase = CreateTodoUseCase.builder()
                .title("test")
                .completed(true)
                .userId(1L)
                .build();
        TodoEntity entity = TodoEntity.builder()
                .id(1L)
                .title(useCase.getTitle())
                .userId(useCase.getUserId())
                .completed(useCase.getCompleted())
                .build();

        when(todoRepository.save(any(TodoEntity.class))).thenReturn(entity);

        var aggregate = todoDataAdapter.save(useCase);

        assertThat(aggregate).isNotNull()
                .returns(1L, TodoAggregate::getId);
    }
}