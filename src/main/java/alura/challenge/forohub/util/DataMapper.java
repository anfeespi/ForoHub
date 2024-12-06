package alura.challenge.forohub.util;

import alura.challenge.forohub.dto.*;
import alura.challenge.forohub.model.Response;
import alura.challenge.forohub.model.Topic;
import alura.challenge.forohub.model.User;
import alura.challenge.forohub.repository.TopicRepository;
import alura.challenge.forohub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataMapper {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;

    public User dtoToEntity(UserDTO dto) {
        return new User(null, dto.name(), dto.email(), passwordEncoder.encode(dto.password()));
    }

    public UserDTO entityToDto(User user) {
        return new UserDTO(user.getUsername(), user.getEmail(), user.getPassword());
    }

    public Topic dtoToEntity(CreateTopicDTO dto) {
        return new Topic(null, dto.title(), dto.message(), dto.date(), dto.status(), userRepository.getReferenceById(dto.author()), null);
    }

    public TopicDTO entityToDto(Topic topic) {
        return new TopicDTO(topic.getIdTopic(), topic.getTitle(), topic.getMessage(), topic.getDate(), topic.getAuthor().getName());
    }

    public Response dtoToEntity(CreateResponseDTO dto) {
        return new Response(null, dto.message(), topicRepository.getReferenceById(dto.topic()), dto.createdAt(), userRepository.getReferenceById(dto.author()), dto.solution());
    }

    public ResponseDTO entityToDto(Response response) {
        return new ResponseDTO(response.getIdResponse(), response.getMessage(), response.getTopic().getIdTopic(), response.getCreatedAt(), response.getAuthor().getName(), response.getSolution());
    }
}
