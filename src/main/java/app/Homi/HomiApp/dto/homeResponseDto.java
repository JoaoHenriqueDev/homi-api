package app.Homi.HomiApp.dto;

import java.util.List;
import java.util.UUID;

public record homeResponseDto(UUID idUser, String profileFotoUrl, List<groupResponseDto> families) {
}
