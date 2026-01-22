package app.Homi.HomiApp.dto.group;

import jakarta.validation.constraints.NotBlank;

public record groupRequestUpdateDto(
        @NotBlank(message = "Nome invalido")
        String name,
        String description) {
}
