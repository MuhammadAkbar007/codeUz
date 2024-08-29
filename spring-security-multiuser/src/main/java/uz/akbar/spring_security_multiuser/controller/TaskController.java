package uz.akbar.spring_security_multiuser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.akbar.spring_security_multiuser.dto.TaskDto;
import uz.akbar.spring_security_multiuser.service.TaskService;

import java.util.List;

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
