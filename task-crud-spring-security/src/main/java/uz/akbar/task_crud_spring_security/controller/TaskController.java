package uz.akbar.task_crud_spring_security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import uz.akbar.task_crud_spring_security.dto.TaskDto;
import uz.akbar.task_crud_spring_security.service.TaskService;

/**
 * TaskController
 */
@Controller
@RequestMapping("/task")
public class TaskController {

	@Autowired
	TaskService service;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody TaskDto dto) {
		TaskDto result = service.create(dto);
		return ResponseEntity.ok(result);
	}

	@GetMapping
	public ResponseEntity<?> getAll() {
		List<TaskDto> result = service.getAll();
		return ResponseEntity.ok(result);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable String id) {
		TaskDto result = service.getById(id);
		return ResponseEntity.ok(result);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable String id, @RequestBody TaskDto dto) {
		Boolean result = service.update(id, dto);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}
