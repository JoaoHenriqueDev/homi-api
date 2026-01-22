package app.Homi.HomiApp.dto.group;

import java.util.UUID;

public record groupResponseDto(UUID id, String name, String description, UUID invite, String fotoUrl) {
}
