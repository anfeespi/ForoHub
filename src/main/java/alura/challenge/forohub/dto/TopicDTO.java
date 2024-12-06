package alura.challenge.forohub.dto;

import java.time.LocalDateTime;

public record TopicDTO(
        Long id,
        String title,
        String message,
        LocalDateTime date,
        String author) {
}
