package gabrieldev.backend.repositories;

import gabrieldev.backend.models.ProjectModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjectRepository extends JpaRepository<ProjectModel, UUID> {
}
