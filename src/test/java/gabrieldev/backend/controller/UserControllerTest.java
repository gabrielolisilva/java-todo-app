package gabrieldev.backend.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import gabrieldev.backend.controllers.UserController;
import gabrieldev.backend.dto.UserDTO;
import gabrieldev.backend.enums.UserRoleEnum;
import gabrieldev.backend.models.UserModel;
import gabrieldev.backend.services.UserService;

public class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUsers() throws Exception {
        List<UserModel> userList = Arrays.asList(new UserModel(), new UserModel());
        when(userService.getUsers()).thenReturn(userList);

        ResponseEntity<List<UserModel>> response = userController.getUsers();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userList, response.getBody());
    }

    @Test
    void getUser() throws Exception {
        UUID randomUuid = UUID.randomUUID();
        UserModel newUser = new UserModel();
        newUser.setId(randomUuid);

        when(userService.getUser(randomUuid)).thenReturn(newUser);

        ResponseEntity<UserModel> response = userController.getUser(randomUuid);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(newUser, response.getBody());
    }

    @Test
    void createUser() throws Exception {
        UserDTO userDTO = new UserDTO(
            "Teste",
            "teste@teste",
            "123456",
            UserRoleEnum.ADMIN
        );

        UserModel userModel = new UserModel(userDTO.name(),
        userDTO.email(),
        userDTO.password(),
        userDTO.role().toString(),
        null,
        null);

        when(userService.createUser(userDTO)).thenReturn(userModel);

        ResponseEntity<UserModel> response = userController.createUser(userDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userModel, response.getBody());
    }

    @Test
    void updateUser() throws Exception {
        UserDTO userDTO = new UserDTO(
            "Teste",
            "teste@teste",
            "123456",
            UserRoleEnum.ADMIN
        );
        UUID randomUuid = UUID.randomUUID();

        UserModel userModel = new UserModel();
        when(userService.updateUser(randomUuid, userDTO)).thenReturn(userModel);

        ResponseEntity<UserModel> response = userController.updateUser(randomUuid, userDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userModel, response.getBody());
    }

    @Test
    void deleteUser() throws Exception {
        UserModel userModel = new UserModel();
        UUID randomUuid = UUID.randomUUID();
        when(userService.deleteUser(randomUuid)).thenReturn(userModel);

        ResponseEntity<UserModel> response = userController.deleteUser(randomUuid);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userModel, response.getBody());
    }
}
