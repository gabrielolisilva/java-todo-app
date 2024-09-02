package gabrieldev.backend.repositories;

import gabrieldev.backend.models.TeamMembersModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TeamMembersRepository extends JpaRepository<TeamMembersModel, UUID> {
    public List<TeamMembersModel> getTeamMembersByTeamId(UUID teamId);
}
