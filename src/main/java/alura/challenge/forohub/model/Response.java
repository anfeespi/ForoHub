package alura.challenge.forohub.model;

import alura.challenge.forohub.dto.ResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "response")
@Entity(name = "Response")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idResponse")
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResponse;
    private String message;
    @ManyToOne
    @JoinColumn(name = "idTopic")
    private Topic topic;
    private LocalDateTime createdAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser")
    private User author;
    private String solution;

    public void update(ResponseDTO dto) {
        if (dto.message() != null && !dto.message().isEmpty()) {
            this.message = dto.message();
        }
        if (dto.solution() != null && !dto.solution().isEmpty()) {
            this.solution = dto.solution();
        }
        if (dto.createdAt() != null) {
            this.createdAt = dto.createdAt();
        }
    }
}
