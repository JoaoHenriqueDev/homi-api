package app.Homi.HomiApp.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record userRequestUpdateDto(
        @NotBlank(message = "Um ou mais campos não foram preenchidos corretamente")
        @Email(message = "Um ou mais campos não foram preenchidos corretamente")
        String email,
        @NotBlank(message = "Um ou mais campos não foram preenchidos corretamente")
        String name,
        @NotBlank(message = "Um ou mais campos não foram preenchidos corretamente")
        String celular) {
}
