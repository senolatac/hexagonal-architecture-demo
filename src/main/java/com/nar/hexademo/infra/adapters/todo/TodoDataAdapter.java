package com.nar.hexademo.infra.adapters.todo;

import com.nar.hexademo.domain.aggregate.todo.TodoAggregate;
import com.nar.hexademo.domain.port.todo.TodoDataPort;
import com.nar.hexademo.domain.usecase.todo.CreateTodoUseCase;
import com.nar.hexademo.infra.entity.todo.TodoEntity;
import com.nar.hexademo.infra.mapper.todo.TodoMapper;
import com.nar.hexademo.infra.repository.todo.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoDataAdapter implements TodoDataPort {
    private final TodoRepository todoRepository;

    @Override
    public TodoAggregate save(CreateTodoUseCase useCase) {
        TodoEntity entity = TodoEntity.builder()
                .title(useCase.getTitle())
                .userId(useCase.getUserId())
                .completed(useCase.getCompleted())
                .build();
        return TodoMapper.INSTANCE.entityToAggregate(todoRepository.save(entity));
    }
}
