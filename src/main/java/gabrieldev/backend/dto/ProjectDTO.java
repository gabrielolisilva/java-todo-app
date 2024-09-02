package gabrieldev.backend.dto;

import java.util.UUID;

public record ProjectDTO(String name, String description, UUID team_id) {
}
