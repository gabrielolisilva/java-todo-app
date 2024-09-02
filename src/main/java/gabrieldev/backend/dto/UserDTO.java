package gabrieldev.backend.dto;

import gabrieldev.backend.enums.UserRoleEnum;

public record UserDTO(String name, String email, String password, UserRoleEnum role) {
}
