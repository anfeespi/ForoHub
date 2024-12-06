package alura.challenge.forohub.dto;

import java.time.LocalDateTime;

public record CreateResponseDTO(
        String message,
        Long topic,
        LocalDateTime createdAt,
        Long author,
        String solution
) {
}
