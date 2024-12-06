package alura.challenge.forohub.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record UserDTO(@NotNull(message = "The username must be not null") String name,
                      @NotNull(message = "The email must be present") @Email(message = "The email must be valid") String email,
                      @NotNull(message = "The password must be not null") @Length(min = 8, message = "The password is too short") String password) {
}
