package alura.challenge.forohub.controller;

import alura.challenge.forohub.dto.CreateTopicDTO;
import alura.challenge.forohub.dto.UpdateTopicDTO;
import alura.challenge.forohub.service.TopicService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topic")
@SecurityRequirement(name = "bearer-key")
@Transactional
public class TopicController {
    @Autowired
    private TopicService topicService;

    @GetMapping
    public ResponseEntity getAllTopics(@PageableDefault(size = 15) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(topicService.getTopics(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity getTopic(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(topicService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTopic(@PathVariable Long id) {
        topicService.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping
    public ResponseEntity addTopic(@RequestBody CreateTopicDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(topicService.create(dto));
    }

    @PutMapping
    public ResponseEntity updateTopic(@RequestBody UpdateTopicDTO dto) {
        topicService.update(dto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
