package gabrieldev.backend.repositories;

import gabrieldev.backend.models.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<TaskModel, UUID> {
    public List<TaskModel> getTasksByProjectId(UUID projectId);
}
