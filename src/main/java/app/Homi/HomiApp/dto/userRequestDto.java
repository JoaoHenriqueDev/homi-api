package app.Homi.HomiApp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record userRequestDto(
        @NotBlank(message = "Um ou mais campos não foram preenchidos corretamente")
        @Email(message = "Um ou mais campos não foram preenchidos corretamente")
        String email,
        @NotBlank(message = "Um ou mais campos não foram preenchidos corretamente")
        String name,
        @NotBlank(message = "Um ou mais campos não foram preenchidos corretamente")
        String password,
        @NotBlank(message = "Um ou mais campos não foram preenchidos corretamente")
        String celular) {
}
