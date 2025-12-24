package app.Homi.HomiApp.dto;

import java.math.BigInteger;
import java.util.UUID;

public record familyResponseDto(UUID id, String name, String description, UUID invite) {
}
