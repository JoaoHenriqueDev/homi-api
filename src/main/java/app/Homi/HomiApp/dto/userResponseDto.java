package app.Homi.HomiApp.dto;

import java.math.BigInteger;
import java.util.UUID;

public record userResponseDto(UUID id, String email, String name, String fotoUrl, String celular) {
}
