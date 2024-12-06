package alura.challenge.forohub.dto;

import java.time.LocalDateTime;

public record CreateTopicDTO(
        String title,
        String message,
        LocalDateTime date,
        Boolean status,
        Long author
) {
}
