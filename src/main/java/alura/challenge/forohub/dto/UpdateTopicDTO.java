package alura.challenge.forohub.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateTopicDTO(
        @NotNull(message = "Must indicate the id") Long id,
        String title,
        String message
) {
}
