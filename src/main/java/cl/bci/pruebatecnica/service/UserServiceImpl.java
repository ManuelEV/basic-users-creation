package cl.bci.pruebatecnica.service;

import cl.bci.pruebatecnica.dto.UserDTO;
import cl.bci.pruebatecnica.mapper.Mapper;
import cl.bci.pruebatecnica.model.Phone;
import cl.bci.pruebatecnica.model.User;
import cl.bci.pruebatecnica.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ValidationService validationService;
    private final TokenService tokenService;
    private final Mapper mapper;

    @Override
    public User storeUser(UserDTO userDTO) {

        validationService.validatePassword(userDTO.getPassword());
        validationService.validateEmail(userDTO.getEmail());

        User user = mapper.toUser(userDTO);

        List<Phone> phones = userDTO
                .getPhones()
                .stream()
                .map(mapper::toPhone)
                .peek(phone -> phone.setUser(user))
                .toList();

        LocalDateTime now = LocalDateTime.now();

        user.setCreated(now);
        user.setLastLogin(now);
        user.setPhones(phones);
        user.setActive(true);
        String token = tokenService.generateToken(user.getEmail());
        user.setToken(token);

        userRepository.save(user);

        return user;
    }

}
