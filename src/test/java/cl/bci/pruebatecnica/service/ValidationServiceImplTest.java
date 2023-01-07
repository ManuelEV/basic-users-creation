package cl.bci.pruebatecnica.service;

import cl.bci.pruebatecnica.PruebaTecnicaBciApplication;
import cl.bci.pruebatecnica.model.User;
import cl.bci.pruebatecnica.repository.UserRepository;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = PruebaTecnicaBciApplication.class)
class ValidationServiceImplTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;




    private final String EXISTING_EMAIL = "existing@email.com";
    private final String EMAIL = "valid@email.com";

    @BeforeEach
    void setUp() {


        when(userRepository.findByEmail(EXISTING_EMAIL)).thenReturn(Optional.of(new User()));
        when(userRepository.findByEmail(EMAIL)).thenReturn(Optional.empty());

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void validatePassword() {
        String validPassword = "qw123asdaA";
        validationService.validatePassword(validPassword);
    }

    @Test
    void validatePasswordException() {
        String invalidPassword = "asd";
        Assertions.assertThrows(ValidationException.class, () -> validationService.validatePassword(invalidPassword));
    }

    @Test
    void validateEmail() {

        Optional<User> optionalUser = userRepository.findByEmail(EMAIL);

        Assertions.assertFalse(optionalUser.isPresent());

        validationService.validateEmail(EMAIL);

    }

    @Test
    void validateEmailException() {

        Optional<User> emptyOptionalUser = userRepository.findByEmail(EXISTING_EMAIL);

        Assertions.assertTrue(emptyOptionalUser.isPresent());

        Assertions.assertThrows(ValidationException.class, () -> validationService.validateEmail(EXISTING_EMAIL));

    }

}