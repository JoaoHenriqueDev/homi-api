package app.Homi.HomiApp.dto.user;

import java.util.UUID;

public record userResponseDto(UUID id, String email, String name, String fotoUrl, String celular) {
}
