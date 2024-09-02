package gabrieldev.backend.services;

import gabrieldev.backend.dto.TeamMembersDTO;
import gabrieldev.backend.models.TeamMembersModel;
import gabrieldev.backend.models.TeamModel;
import gabrieldev.backend.models.UserModel;
import gabrieldev.backend.repositories.TeamMembersRepository;
import gabrieldev.backend.repositories.TeamRepository;
import gabrieldev.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TeamMembersService {
    @Autowired
    private TeamMembersRepository teamMembersRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    public List<TeamMembersModel> getTeamMembers() throws Exception {
        try {
            return teamMembersRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Error getting team members", e);
        }
    }

    public List<TeamMembersModel> getTeamMembersByTeamId(UUID teamId) throws Exception {
        try {
            return teamMembersRepository.getTeamMembersByTeamId(teamId);
        } catch (Exception e) {
            throw new Exception("Error getting team members", e);
        }
    }

    public TeamMembersModel createTeamMember(TeamMembersDTO teamMembersDTO) throws Exception {
        TeamModel team = teamRepository.findById(teamMembersDTO.team_id())
                .orElseThrow(() -> new Exception("Team não encontrado"));

        UserModel user = userRepository.findById(teamMembersDTO.user_id())
                .orElseThrow(() -> new Exception("User não encontrado"));

        TeamMembersModel teamMembersModel = new TeamMembersModel(
                team,
                user,
                teamMembersDTO.role().toString(),
                LocalDateTime.now()
        );

        return teamMembersRepository.save(teamMembersModel);
    }

    public TeamMembersModel deleteTeamMember(UUID id) throws Exception {
        Optional<TeamMembersModel> teamMembers = teamMembersRepository.findById(id);
        if (teamMembers.isEmpty()) {
            throw new Exception("Team member not found");
        }

        TeamMembersModel teamMembersDB = teamMembers.get();
        teamMembersRepository.delete(teamMembersDB);
        return teamMembersDB;
    }
}
