package gabrieldev.backend.services;

import gabrieldev.backend.dto.TeamDTO;
import gabrieldev.backend.models.TeamModel;
import gabrieldev.backend.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;

    public List<TeamModel> getTeams() throws Exception {
        try {
            return teamRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Error while getting teams", e);
        }
    }

    public TeamModel getTeam(UUID id) throws Exception {
        try {
            return teamRepository.findById(id).orElseThrow(() -> new Exception("Team not found"));
        } catch (Exception e) {
            throw new Exception("Error while getting team", e);
        }
    }

    public TeamModel createTeam(TeamDTO teamDTO) throws Exception {
        try {
            TeamModel team = new TeamModel(teamDTO.name(),
                    teamDTO.description(),
                    null,
                    null);

            return teamRepository.save(team);
        } catch (Exception e) {
            throw new Exception("Error while creating team", e);
        }
    }

    public TeamModel updateTeam(UUID id, TeamDTO teamDTO) throws Exception {
        try {
            TeamModel team = teamRepository.findById(id).orElseThrow(() -> new Exception("Team not found"));

            if (teamDTO.name() != null) {
                team.setName(teamDTO.name());
            }

            if (teamDTO.description() != null) {
                team.setDescription(teamDTO.description());
            }

            return teamRepository.save(team);
        } catch (Exception e) {
            throw new Exception("Error while updating team", e);
        }
    }

    public TeamModel deleteTeam(UUID id) throws Exception {
        try {
            TeamModel team = teamRepository.findById(id).orElseThrow(() -> new Exception("Team not found"));
            teamRepository.delete(team);
            return team;
        } catch (Exception e) {
            throw new Exception("Error while deleting team", e);
        }
    }
}
