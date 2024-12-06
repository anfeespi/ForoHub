package alura.challenge.forohub.service;

import alura.challenge.forohub.dto.UserDTO;
import alura.challenge.forohub.repository.UserRepository;
import alura.challenge.forohub.util.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DataMapper dataMapper;

    public UserDTO registerUser(UserDTO userDTO) {
        return dataMapper.entityToDto(
                userRepository.save(dataMapper.dtoToEntity(userDTO))
        );
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByName(username);
    }
}
