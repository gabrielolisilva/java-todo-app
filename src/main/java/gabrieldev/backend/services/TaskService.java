package gabrieldev.backend.services;

import gabrieldev.backend.dto.TaskDTO;
import gabrieldev.backend.models.ProjectModel;
import gabrieldev.backend.models.TaskModel;
import gabrieldev.backend.models.UserModel;
import gabrieldev.backend.repositories.ProjectRepository;
import gabrieldev.backend.repositories.TaskRepository;
import gabrieldev.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    public List<TaskModel> getAllTasks() throws Exception {
        try {
            return taskRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Error getting tasks", e);
        }
    }

    public TaskModel getTask(UUID id) throws Exception {
        Optional<TaskModel> taskDB = taskRepository.findById(id);
        if (taskDB.isEmpty()) {
            throw new Exception("Task not founded");
        }

        return taskDB.get();
    }

    public TaskModel createTask(TaskDTO taskDTO) throws Exception {
            Optional<ProjectModel> projectDB = projectRepository.findById(taskDTO.project_id());
            Optional<UserModel> userDB = userRepository.findById(taskDTO.assignedTo());
            if (projectDB.isEmpty() || userDB.isEmpty()) {
                throw new Exception("Project or user not founded");
            }

            ProjectModel project = projectDB.get();
            UserModel user = userDB.get();

            TaskModel taskModel = new TaskModel(
                    project,
                    taskDTO.title(),
                    taskDTO.description(),
                    taskDTO.status().toString(),
                    user,
                    taskDTO.dueDate(),
                    null,
                    null);

        try {
            taskRepository.save(taskModel);
            return taskModel;
        } catch (Exception e) {
            throw new Exception("Error creating task", e);
        }
    }

    public TaskModel updateTask(UUID id, TaskDTO taskDTO) throws Exception {
        Optional<TaskModel> taskDB = taskRepository.findById(id);
        if (taskDB.isEmpty()) {
            throw new Exception("Task not founded");
        }

        TaskModel taskToUpdate = taskDB.get();
        if (taskDTO.title() != null) {
            taskToUpdate.setTitle(taskDTO.title());
        }

        if (taskDTO.description() != null) {
            taskToUpdate.setDescription(taskDTO.description());
        }

        if (taskDTO.status() != null) {
            taskToUpdate.setStatus(taskDTO.status().toString());
        }

        if (taskDTO.assignedTo() != null) {
            Optional<UserModel> userAssigned = userRepository.findById(taskDTO.assignedTo());
            if (userAssigned.isEmpty()) {
                throw new Exception("New user not founded");
            }

            UserModel newUser = userAssigned.get();
            taskToUpdate.setAssignedTo(newUser);
        }

        if (taskDTO.dueDate() != null) {
            taskToUpdate.setDueDate(taskDTO.dueDate());
        }

        return taskRepository.save(taskToUpdate);
    }

    public TaskModel deleteTask(UUID id) throws Exception {
        Optional<TaskModel> taskDB = taskRepository.findById(id);
        if (taskDB.isEmpty()) {
            throw new Exception("Task not founded");
        }

        TaskModel taskToDelete = taskDB.get();
        taskRepository.delete(taskToDelete);
        return taskToDelete;
    }
}
