package gabrieldev.backend.dto;

import gabrieldev.backend.enums.TaskStatusEnum;

import java.time.LocalDate;
import java.util.UUID;

public record TaskDTO(UUID project_id,
                      String title,
                      String description,
                      TaskStatusEnum status,
                      UUID assignedTo,
                      LocalDate dueDate) {
}
