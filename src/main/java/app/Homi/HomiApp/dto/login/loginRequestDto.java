package app.Homi.HomiApp.dto.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record loginRequestDto(
        @NotBlank(message = "Email ou senha incorretos")
        @Email(message = "Email ou senha incorretos")
        String email,
        @NotBlank(message = "Email ou senha incorretos")
        String password) {
}
