package gabrieldev.backend.controllers;

import gabrieldev.backend.dto.TeamMembersDTO;
import gabrieldev.backend.models.TeamMembersModel;
import gabrieldev.backend.services.TeamMembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/team-members")
public class TeamMembersController {
    @Autowired
    private TeamMembersService teamMembersService;

    @GetMapping
    public ResponseEntity<List<TeamMembersModel>> getTeamMembers() throws Exception {

        return ResponseEntity.ok(teamMembersService.getTeamMembers());
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<List<TeamMembersModel>> getTeamMembersByTeamId(@PathVariable UUID teamId) throws Exception {
        return ResponseEntity.ok(teamMembersService.getTeamMembersByTeamId(teamId));
    }

    @PostMapping
    public ResponseEntity<TeamMembersModel> createTeamMember(@RequestBody TeamMembersDTO teamMembersDTO) throws Exception {
        return ResponseEntity.ok(teamMembersService.createTeamMember(teamMembersDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TeamMembersModel> deleteTeamMember(@PathVariable UUID id) throws Exception {
        return ResponseEntity.ok(teamMembersService.deleteTeamMember(id));
    }
}
