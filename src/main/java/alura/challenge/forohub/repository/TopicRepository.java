package alura.challenge.forohub.repository;

import alura.challenge.forohub.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
