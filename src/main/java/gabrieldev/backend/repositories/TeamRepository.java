package gabrieldev.backend.repositories;

import gabrieldev.backend.models.TeamModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TeamRepository extends JpaRepository<TeamModel, UUID> {
}
