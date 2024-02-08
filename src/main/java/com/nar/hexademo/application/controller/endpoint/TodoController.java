package com.nar.hexademo.application.controller.endpoint;

import com.nar.hexademo.application.controller.transfer.todo.CreateTodoRequestDto;
import com.nar.hexademo.application.controller.transfer.todo.TodoResponseDto;
import com.nar.hexademo.domain.usecasehandler.todo.CreateTodoUseCaseHandler;
import com.nar.hexademo.domain.usecasehandler.todo.GetTodosUseCaseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/todos")
@RequiredArgsConstructor
public class TodoController {
    private final GetTodosUseCaseHandler getTodosUseCaseHandler;
    private final CreateTodoUseCaseHandler createTodoUseCaseHandler;

    @GetMapping("all")
    public ResponseEntity<?> getAllTodos() {
        var todos = getTodosUseCaseHandler.handle();
        return ResponseEntity.ok(TodoResponseDto.fromAggregateList(todos));
    }

    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody @Valid CreateTodoRequestDto dto) {
        var todo = createTodoUseCaseHandler.handle(dto.toUseCase());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(TodoResponseDto.fromAggregate(todo));
    }
}
