package alura.challenge.forohub.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ResponseDTO(
        @NotNull(message = "The id is mandatory") Long idResponse,
        String message,
        Long topic,
        LocalDateTime createdAt,
        String author,
        String solution
) {
}
