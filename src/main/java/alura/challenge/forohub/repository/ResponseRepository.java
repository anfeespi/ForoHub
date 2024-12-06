package alura.challenge.forohub.repository;

import alura.challenge.forohub.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseRepository extends JpaRepository<Response, Long> {
}
