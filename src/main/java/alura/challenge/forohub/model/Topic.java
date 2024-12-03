package alura.challenge.forohub.model;

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
    private Integer idTopic;
    private String title;
    private String message;
    private LocalDateTime date;
    private Boolean status;
    @ManyToOne
    @JoinColumn(name = "idUser")
    private User author;
    @ManyToOne
    @JoinColumn(name = "idCourse")
    private Course course;
    @OneToMany(mappedBy = "topic")
    private Set<Response> responses;
}
