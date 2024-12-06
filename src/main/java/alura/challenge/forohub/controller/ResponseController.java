package alura.challenge.forohub.controller;

import alura.challenge.forohub.dto.CreateResponseDTO;
import alura.challenge.forohub.dto.ResponseDTO;
import alura.challenge.forohub.service.ResponseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/response")
@SecurityRequirement(name = "bearer-key")
@Transactional
public class ResponseController {
    @Autowired
    private ResponseService responseService;

    @GetMapping
    public ResponseEntity getAllResponses(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity getResponseByTopic(@PageableDefault(size = 10) Pageable pageable, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseService.getResponses(pageable, id));
    }

    @GetMapping("/spec/{id}")
    public ResponseEntity getResponse(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteResponse(@PathVariable Long id) {
        responseService.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping
    public ResponseEntity addResponse(@RequestBody CreateResponseDTO dto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseService.respond(dto));
    }

    @PutMapping
    public ResponseEntity updateResponse(@RequestBody ResponseDTO dto) {
        responseService.update(dto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }


}
