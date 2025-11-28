package app.Homi.HomiApp.dto;

import java.math.BigInteger;
import java.time.LocalDateTime;

public record inviteRequestDto(BigInteger idFamily, String invite, BigInteger creatorUserId, LocalDateTime dataExpires) {
}
