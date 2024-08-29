package uz.akbar.spring_security_multiuser.service;

import org.springframework.stereotype.Service;
import uz.akbar.spring_security_multiuser.dto.TaskDto;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * TaskService
 */
@Service
public class TaskService {

	List<TaskDto> taskList;

	public TaskService() {
		taskList = new LinkedList<>();

		TaskDto task1 = new TaskDto();
		task1.setId(UUID.randomUUID().toString());
		task1.setTitle("Bozor");
		task1.setContent("Bozorga borib meva-chevalar olib kelish kerak.");
		task1.setCreatedDate(LocalDateTime.now());
		taskList.add(task1);

		TaskDto task2 = new TaskDto();
		task2.setId(UUID.randomUUID().toString());
		task2.setTitle("Spring Security");
		task2.setContent("Dasturlash.uz ga kirib Spring Securityni o'rganishim kerak.");
		task2.setCreatedDate(LocalDateTime.now());
		taskList.add(task2);
	}

	public TaskDto create(TaskDto dto) {
		dto.setId(UUID.randomUUID().toString());
		dto.setCreatedDate(LocalDateTime.now());
		taskList.add(dto);
		return dto;
	}

	public List<TaskDto> getAll() {
		return taskList;
	}

	public TaskDto getById(String id) {
		for (TaskDto task : taskList) {
			if (task.getId().equals(id)) {
				return task;
			}
		}
		return null;
	}

	public Boolean update(String id, TaskDto dto) {
		TaskDto exists = getById(id);

		if (exists == null) {
			return null;
		}

		exists.setTitle(dto.getTitle());
		exists.setContent(dto.getContent());
		return true;
	}

	public Boolean delete(String id) {
		return taskList.removeIf(task -> task.getId().equals(id));
	}

}
