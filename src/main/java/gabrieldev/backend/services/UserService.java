package gabrieldev.backend.services;

import gabrieldev.backend.dto.UserDTO;
import gabrieldev.backend.exceptions.EventNotFoundedException;
import gabrieldev.backend.models.UserModel;
import gabrieldev.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserModel> getUsers() throws Exception {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Error while getting users", e);
        }
    }

    public UserModel getUser(UUID id) {
        Optional<UserModel> userModel = userRepository.findById(id);
        if (userModel.isEmpty()) {
            throw new EventNotFoundedException();
        }

        return userModel.get();
    }

    public UserModel createUser(UserDTO userDTO) throws Exception {
        try {
            UserModel userModel = new UserModel(userDTO.name(),
                    userDTO.email(),
                    userDTO.password(),
                    userDTO.role().toString(),
                    null,
                    null);
            return userRepository.save(userModel);
        } catch (Exception e) {
            throw new Exception("Error while creating user", e);
        }
    }

    public UserModel updateUser(UUID id, UserDTO userDTO) throws Exception {
        Optional<UserModel> userModel = userRepository.findById(id);
        if (userModel.isEmpty()) {
            throw new Exception("User not found");
        }

        UserModel userToUpdate = userModel.get();
        if (userDTO.name() != null) {
            userToUpdate.setName(userDTO.name());
        }

        if (userDTO.email() != null) {
            userToUpdate.setEmail(userDTO.email());
        }

        if (userDTO.password() != null) {
            userToUpdate.setPassword(userDTO.password());
        }

        if (userDTO.role() != null) {
            userToUpdate.setRole(userDTO.role().toString());
        }

        return userRepository.save(userToUpdate);
    }

    public UserModel deleteUser(UUID id) throws Exception {
        Optional<UserModel> userModel = userRepository.findById(id);
        if (userModel.isEmpty()) {
            throw new Exception("User not found");
        }

        UserModel userDB = userModel.get();
        userRepository.delete(userDB);
        return userDB;
    }
}
