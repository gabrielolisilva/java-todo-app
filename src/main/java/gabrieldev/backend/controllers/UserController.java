package gabrieldev.backend.controllers;

import gabrieldev.backend.dto.UserDTO;
import gabrieldev.backend.models.UserModel;
import gabrieldev.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserModel>> getUsers() throws Exception {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUser(@PathVariable UUID id) {
        UserModel userModel = userService.getUser(id);
        return ResponseEntity.ok(userModel);
    }

    @PostMapping
    public ResponseEntity<UserModel> createUser(@RequestBody UserDTO userDTO) throws Exception {
        UserModel userModel = userService.createUser(userDTO);
        return ResponseEntity.status(201).body(userModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable UUID id, @RequestBody UserDTO userDTO) throws Exception {
        UserModel userModel = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(userModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserModel> deleteUser(@PathVariable UUID id) throws Exception {
        UserModel userModel = userService.deleteUser(id);
        return ResponseEntity.ok(userModel);
    }
}
