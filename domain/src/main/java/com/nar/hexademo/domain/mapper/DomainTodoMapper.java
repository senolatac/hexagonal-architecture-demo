package com.nar.hexademo.domain.mapper;

import com.nar.hexademo.domain.aggregate.todo.TodoAggregate;
import com.nar.hexademo.domain.event.todo.CreateTodoEvent;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DomainTodoMapper {

    DomainTodoMapper INSTANCE = Mappers.getMapper(DomainTodoMapper.class);

    CreateTodoEvent aggregateToEvent(TodoAggregate aggregate);
}
