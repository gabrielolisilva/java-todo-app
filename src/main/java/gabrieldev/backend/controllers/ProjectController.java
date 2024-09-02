package gabrieldev.backend.controllers;

import gabrieldev.backend.dto.ProjectDTO;
import gabrieldev.backend.models.ProjectModel;
import gabrieldev.backend.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    protected ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectModel>> getProjects() throws Exception {
        return ResponseEntity.ok(projectService.getProjects());
    }

    @PostMapping
    public ResponseEntity<ProjectModel> createProject(@RequestBody ProjectDTO projectDTO) throws Exception {
        return ResponseEntity.ok(projectService.createProject(projectDTO));
    }
}
