package alura.challenge.forohub.service;

import alura.challenge.forohub.dto.CreateResponseDTO;
import alura.challenge.forohub.dto.ResponseDTO;
import alura.challenge.forohub.repository.ResponseRepository;
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
public class ResponseService {
    @Autowired
    private ResponseRepository responseRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private DataMapper dataMapper;

    public List<ResponseDTO> findAll() {
        return responseRepository.findAll().stream().map(dataMapper::entityToDto).collect(Collectors.toList());
    }

    public Page<ResponseDTO> findAll(Pageable pageable) {
        return new PageImpl<>(findAll(), pageable, responseRepository.count());
    }

    public Page<ResponseDTO> getResponses(Pageable pageable, Long idTopic) {
        return new PageImpl<>(topicRepository.getReferenceById(idTopic).getResponses().stream().map(dataMapper::entityToDto).toList(), pageable, topicRepository.getReferenceById(idTopic).getResponses().stream().toList().stream().count());
    }

    public ResponseDTO findById(Long id) {
        return responseRepository.findById(id).map(dataMapper::entityToDto).orElse(null);
    }

    public ResponseDTO respond(CreateResponseDTO dto) {
        return dataMapper.entityToDto(
                responseRepository.save(dataMapper.dtoToEntity(dto))
        );
    }

    public void delete(Long id) {
        responseRepository.deleteById(id);
    }

    public void update(ResponseDTO dto) {
        responseRepository.getReferenceById(dto.idResponse()).update(dto);
    }
}
