package com.nar.hexademo.infra.mapper.todo;

import com.nar.hexademo.domain.aggregate.todo.TodoAggregate;
import com.nar.hexademo.infra.entity.todo.TodoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TodoMapper {
    TodoMapper INSTANCE = Mappers.getMapper(TodoMapper.class);

    TodoAggregate entityToAggregate(TodoEntity entity);
}
