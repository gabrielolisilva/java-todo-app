package gabrieldev.backend.controllers;

import gabrieldev.backend.dto.TeamDTO;
import gabrieldev.backend.models.TeamModel;
import gabrieldev.backend.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/teams")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @GetMapping
    public ResponseEntity<List<TeamModel>> getTeams() throws Exception {
        return ResponseEntity.ok(teamService.getTeams());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamModel> getTeam(@PathVariable UUID id) throws Exception {
        return ResponseEntity.ok(teamService.getTeam(id));
    }

    @PostMapping
    public ResponseEntity<TeamModel> createTeam(@RequestBody TeamDTO teamDTO) throws Exception {
        return ResponseEntity.ok(teamService.createTeam(teamDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamModel> updateTeam(@PathVariable UUID id, @RequestBody TeamDTO teamDTO) throws Exception {
        return ResponseEntity.ok(teamService.updateTeam(id, teamDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TeamModel> deleteTeam(@PathVariable UUID id) throws Exception {
        return ResponseEntity.ok(teamService.deleteTeam(id));
    }
}
