package gabrieldev.backend.services;

import gabrieldev.backend.dto.ProjectDTO;
import gabrieldev.backend.models.ProjectModel;
import gabrieldev.backend.models.TeamModel;
import gabrieldev.backend.repositories.ProjectRepository;
import gabrieldev.backend.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TeamRepository teamRepository;

    public List<ProjectModel> getProjects() throws Exception {
        try {
            return projectRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Error getting projects", e);
        }
    }

    public ProjectModel createProject(ProjectDTO projectDTO) throws Exception {
        TeamModel teamDB = teamRepository.findById(projectDTO.team_id())
                .orElseThrow(() -> new Exception("Team not found"));

        ProjectModel projectModel = new ProjectModel(
                projectDTO.name(),
                projectDTO.description(),
                teamDB,
                null,
                null
        );

        return projectRepository.save(projectModel);
    }
}
