package com.nar.hexademo.application.controller.mapper.todo;

import com.nar.hexademo.application.controller.transfer.todo.CreateTodoRequestDto;
import com.nar.hexademo.application.controller.transfer.todo.TodoResponseDto;
import com.nar.hexademo.domain.aggregate.todo.TodoAggregate;
import com.nar.hexademo.domain.usecase.todo.CreateTodoUseCase;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TodoMapper {
    TodoMapper INSTANCE = Mappers.getMapper(TodoMapper.class);

    CreateTodoUseCase createTodoRequestDtoToUseCase(CreateTodoRequestDto dto);

    List<TodoResponseDto> aggregateListToTodoResponseDtoList(List<TodoAggregate> list);

    TodoResponseDto aggregateToTodoResponseDto(TodoAggregate aggregate);
}
