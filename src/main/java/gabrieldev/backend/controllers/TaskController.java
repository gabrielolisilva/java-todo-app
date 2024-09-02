package gabrieldev.backend.controllers;

import gabrieldev.backend.dto.TaskDTO;
import gabrieldev.backend.models.TaskModel;
import gabrieldev.backend.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskModel>> getAllTasks() throws Exception {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskModel> getTask(@PathVariable UUID id) throws Exception {
        return ResponseEntity.ok(taskService.getTask(id));
    }

    @PostMapping
    public ResponseEntity<TaskModel> createTask(@RequestBody TaskDTO taskDTO) throws Exception {
        TaskModel taskModel = taskService.createTask(taskDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskModel> updateTask(@PathVariable UUID id, @RequestBody TaskDTO taskDTO) throws Exception {
        TaskModel taskUpdated = taskService.updateTask(id, taskDTO);
        return ResponseEntity.status(HttpStatus.OK).body(taskUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskModel> deleteTask(@PathVariable UUID id) throws Exception {
        TaskModel taskDeleted = taskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.OK).body(taskDeleted);
    }
}
