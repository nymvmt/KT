package sample.app.todo.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sample.app.todo.api.dto.request.create.CreateTodoBody;
import sample.app.todo.api.dto.request.delete.DeleteBulkTodoBody;
import sample.app.todo.api.dto.request.update.UpdateTodoBody;
import sample.app.todo.api.dto.response.TodoResponse;
import sample.app.todo.api.service.TodoService;

import java.util.List;

/**
 * Todo 관련 REST API를 처리하는 컨트롤러
 * 할일 목록의 생성, 조회, 수정, 삭제 기능을 제공
 */
@RestController
@RequestMapping("/api/todos") //공통적으로 들어가는 매핑 부분
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    /**
     * [할일 추가 API]
     * param: request 할일 생성 요청 정보 (제목, 내용 등)
     * return 생성된 할일 정보
     */
    @PostMapping
    public ResponseEntity<TodoResponse> createTodo(@RequestBody CreateTodoBody request) {

        System.out.println("controller request = " + request);
        TodoResponse response = todoService.createTodo(request);
        return ResponseEntity.ok(response);
    }

    /**
     * [모든 할일 목록 조회 API]
     * return: 전체 할일 목록
     */
    @GetMapping
    public ResponseEntity<List<TodoResponse>>getTodos() {
        List<TodoResponse> response = todoService.getTodos();
        return ResponseEntity.ok(response);
    }

    /**
     * [특정 할일 조회 API]
     * param: id 조회할 할일의 고유 ID
     * return: 해당 ID의 할일 정보
     */
    @GetMapping("/{id}")
    public ResponseEntity<TodoResponse> getTodo(@PathVariable String id) {
        TodoResponse response = todoService.getTodo(id);
        return ResponseEntity.ok(response);
    }

    /**
     * [할일 수정 API]
     * param: id 수정할 할일의 고유 ID
     * param: request 수정할 내용 (제목, 내용, 완료상태 등)
     * return: 성공 응답
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTodo(@PathVariable String id, @RequestBody UpdateTodoBody request) {
        todoService.updateTodo(id, request);
        return ResponseEntity.ok().build();
    }

    /**
     * [할일 개별 삭제 API]
     * param: id 삭제할 할일의 고유 ID
     * return: 성공 응답
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String id) {
        todoService.deleteTodo(id);
        return ResponseEntity.ok().build();
    }

    /**
     * [할일 대량 삭제 API]
     * param request 삭제할 할일들의 ID 목록
     * return: 성공 응답
     */
    @DeleteMapping("/bulk")
    public ResponseEntity<Void> deleteBulkTodos(@RequestBody DeleteBulkTodoBody request) {
        todoService.deleteBulkTodos(request);
        return ResponseEntity.ok().build();
    }
}
