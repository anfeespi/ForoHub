package alura.challenge.forohub.controller;

import alura.challenge.forohub.dto.CreateTopicDTO;
import alura.challenge.forohub.dto.TopicDTO;
import alura.challenge.forohub.dto.UpdateTopicDTO;
import alura.challenge.forohub.service.TopicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ContextConfiguration(classes = TopicControllerTest.TestConfig.class)
class TopicControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<CreateTopicDTO> createTopicJson;

    @Autowired
    private JacksonTester<UpdateTopicDTO> updateTopicJson;

    @Autowired
    private TopicService topicService; // Mock configurado en TestConfig

    @Test
    @DisplayName("Debería devolver http 400 cuando la solicitud POST no tenga datos")
    @WithMockUser
    void addTopic_escenario1() throws Exception {
        var response = mvc.perform(post("/topic"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Debería devolver http 201 cuando la solicitud POST reciba un JSON válido")
    @WithMockUser
    void addTopic_escenario2() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        var createTopicDTO = new CreateTopicDTO("Nuevo Tópico", "Contenido del tópico", now, true, Long.parseLong("1"));
        when(topicService.create(any(CreateTopicDTO.class))).thenReturn(new TopicDTO(Long.parseLong("1"), "Nuevo Tópico", "Contenido del tópico", now, "anfeespi"));

        var response = mvc.perform(post("/topic")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createTopicJson.write(createTopicDTO).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("Debería devolver http 400 cuando la solicitud PUT no tenga datos")
    @WithMockUser
    void updateTopic_escenario1() throws Exception {
        var response = mvc.perform(put("/topic"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Debería devolver http 202 cuando la solicitud PUT reciba un JSON válido")
    @WithMockUser
    void updateTopic_escenario2() throws Exception {
        var updateTopicDTO = new UpdateTopicDTO(1L, "Título actualizado", "Contenido actualizado");

        var response = mvc.perform(put("/topic")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateTopicJson.write(updateTopicDTO).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.ACCEPTED.value());
    }

    @Test
    @DisplayName("Debería devolver http 200 cuando se elimina un tópico")
    @WithMockUser
    void deleteTopic() throws Exception {
        var topicId = 1L;

        var response = mvc.perform(delete("/topic/{id}", topicId))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.ACCEPTED.value());
    }

    @Configuration
    static class TestConfig {

        @Bean
        public TopicService topicService() {
            return Mockito.mock(TopicService.class);
        }
    }
}
