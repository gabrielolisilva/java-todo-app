package gabrieldev.backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import gabrieldev.backend.dto.UserDTO;
import gabrieldev.backend.enums.UserRoleEnum;
import gabrieldev.backend.models.UserModel;
import gabrieldev.backend.repositories.UserRepository;

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUsers() throws Exception {
        List<UserModel> usersList = Arrays.asList(new UserModel(), new UserModel());
        when(userRepository.findAll()).thenReturn(usersList);

        List<UserModel> responseService = userService.getUsers();
        assertEquals(usersList, responseService);
    }

    @Test
    void getUser() throws Exception {
        UUID randomUuid = UUID.randomUUID();
        UserModel userDB = new UserModel();
        userDB.setId(randomUuid);

        Optional<UserModel> userSearch = Optional.of(userDB);
        when(userRepository.findById(randomUuid)).thenReturn(userSearch);

        UserModel responseService = userService.getUser(randomUuid);
        assertEquals(userDB, responseService);
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

        when(userRepository.save(userModel)).thenReturn(userModel);
    }
}
