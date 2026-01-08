package app.Homi.HomiApp.dto;

import java.util.UUID;

public record groupResponseDto(UUID id, String name, String description, UUID invite, String fotoUrl) {
}
