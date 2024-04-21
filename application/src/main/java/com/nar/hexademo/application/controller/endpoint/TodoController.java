package com.nar.hexademo.application.controller.endpoint;

import com.nar.hexademo.application.controller.mapper.todo.TodoMapper;
import com.nar.hexademo.application.controller.transfer.todo.CreateTodoRequestDto;
import com.nar.hexademo.domain.usecasehandler.todo.CreateTodoUseCaseHandler;
import com.nar.hexademo.domain.usecasehandler.todo.GetTodosUseCaseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/todos")
@RequiredArgsConstructor
public class TodoController {
    private final GetTodosUseCaseHandler getTodosUseCaseHandler;
    private final CreateTodoUseCaseHandler createTodoUseCaseHandler;

    @Value("${custom.variable}")
    private String CUSTOM_VALUE;

    @GetMapping("test-log")
    public void testLog() {
        log.info("test-log found");
    }

    @GetMapping("test-exception")
    public void testException() {
        try {
            throw new RuntimeException("Unknown exception");
        } catch (Exception e) {
            log.error("Exception occurred", e);
        }
    }

    @GetMapping("test")
    public ResponseEntity<?> testEnvVal() {
        return ResponseEntity.ok(CUSTOM_VALUE);
    }

    @GetMapping("all")
    public ResponseEntity<?> getAllTodos() {
        var todos = getTodosUseCaseHandler.handle();
        return ResponseEntity.ok(TodoMapper.INSTANCE.aggregateListToTodoResponseDtoList(todos));
    }

    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody @Valid CreateTodoRequestDto dto) {
        var todo = createTodoUseCaseHandler.handle(TodoMapper.INSTANCE.createTodoRequestDtoToUseCase(dto));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(TodoMapper.INSTANCE.aggregateToTodoResponseDto(todo));
    }
}
