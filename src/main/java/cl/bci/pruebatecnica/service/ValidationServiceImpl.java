package cl.bci.pruebatecnica.service;

import cl.bci.pruebatecnica.model.User;
import cl.bci.pruebatecnica.repository.UserRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ValidationServiceImpl implements ValidationService {

    @Value("${general.regex.password}")
    private String PASSWORD_REGEX;

    private final UserRepository userRepository;

    @Override
    public void validatePassword(String password) {

        boolean isValid = Pattern.matches(PASSWORD_REGEX, password);

        if (!isValid) {
            throw new ValidationException("The password is invalid");
        }

    }

    @Override
    public void validateEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            throw new ValidationException("El correo ya se encuentra registrado");
        }

    }
}
