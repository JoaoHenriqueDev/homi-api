package app.Homi.HomiApp.dto;

import app.Homi.HomiApp.dto.group.groupResponseDto;

import java.util.List;
import java.util.UUID;

public record homeResponseDto(UUID idUser, String profileFotoUrl, List<groupResponseDto> families) {
}
