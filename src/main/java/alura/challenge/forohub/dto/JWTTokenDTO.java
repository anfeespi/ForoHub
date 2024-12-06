package alura.challenge.forohub.dto;

import jakarta.validation.constraints.NotNull;

public record JWTTokenDTO(@NotNull String token) {
}
