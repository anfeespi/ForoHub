package alura.challenge.forohub.service;

import alura.challenge.forohub.dto.CreateTopicDTO;
import alura.challenge.forohub.dto.TopicDTO;
import alura.challenge.forohub.dto.UpdateTopicDTO;
import alura.challenge.forohub.model.Topic;
import alura.challenge.forohub.repository.TopicRepository;
import alura.challenge.forohub.util.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private DataMapper dataMapper;

    public List<TopicDTO> findAll() {
        return topicRepository.findAll().stream().filter(Topic::getStatus).map(dataMapper::entityToDto).collect(Collectors.toList());
    }

    public Page<TopicDTO> getTopics(Pageable pageable) {
        return new PageImpl<>(findAll(), pageable, topicRepository.count());
    }

    public TopicDTO findById(Long id) {
        return topicRepository.findById(id).map(dataMapper::entityToDto).orElse(null);
    }

    public TopicDTO create(CreateTopicDTO dto) {
        return dataMapper.entityToDto(
                topicRepository.save(dataMapper.dtoToEntity(dto))
        );
    }

    public void delete(Long id) {
        topicRepository.getReferenceById(id).delete();
    }

    public void update(UpdateTopicDTO dto) {
        topicRepository.getReferenceById(dto.id()).update(dto);
    }
}
