package alura.challenge.forohub.model;

import alura.challenge.forohub.dto.UpdateTopicDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Table(name = "topic")
@Entity(name = "Topic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idTopic")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTopic;
    private String title;
    private String message;
    private LocalDateTime date;
    private Boolean status;
    @ManyToOne
    @JoinColumn(name = "idUser")
    private User author;
    @OneToMany(mappedBy = "topic")
    private Set<Response> responses;

    public void update(UpdateTopicDTO updateTopicDTO) {
        if (updateTopicDTO.title() != null) {
            this.title = updateTopicDTO.title();
        }
        if (updateTopicDTO.message() != null) {
            this.message = updateTopicDTO.message();
        }
    }

    public void delete() {
        this.status = false;
    }
}
