package gabrieldev.backend.dto;

import gabrieldev.backend.enums.TeamMembersRoleEnum;

import java.util.UUID;

public record TeamMembersDTO(UUID team_id, UUID user_id, TeamMembersRoleEnum role) {
}
