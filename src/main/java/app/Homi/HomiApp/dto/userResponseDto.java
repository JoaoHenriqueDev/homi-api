package app.Homi.HomiApp.dto;

import java.math.BigInteger;

public record userResponseDto(BigInteger id, String email, String name,String fotoUrl, String celular) {
}
