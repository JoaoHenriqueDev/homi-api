package app.Homi.HomiApp.dto;

import java.math.BigInteger;

public record familyResponseDto(BigInteger id, String name, String description, BigInteger idUser, String invite) {
}
