package cl.bci.pruebatecnica.service;

import cl.bci.pruebatecnica.PruebaTecnicaBciApplication;
import cl.bci.pruebatecnica.dto.PhoneDTO;
import cl.bci.pruebatecnica.dto.UserDTO;
import cl.bci.pruebatecnica.mapper.Mapper;
import cl.bci.pruebatecnica.model.Phone;
import cl.bci.pruebatecnica.model.User;
import cl.bci.pruebatecnica.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = PruebaTecnicaBciApplication.class)
class UserServiceImplTest {

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private ValidationService validationService;
    @MockBean
    private TokenService tokenService;
    @MockBean
    private Mapper mapper;
    @Autowired
    private UserService userService;

    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        PhoneDTO phoneDTO = new PhoneDTO("1234567", "1", "57");
        userDTO = new UserDTO("Juan", "juan@gmail.com", "secret-password", List.of(phoneDTO));

        User user = new User();
        user.setId(UUID.fromString("123e4567-e89b-12d3-a456-426655440000"));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        Phone phone = new Phone();
        phone.setId(1L);
        phone.setUser(user);
        phone.setNumber("1234567");
        phone.setCityCode("1");
        phone.setCountryCode("57");

        when(mapper.toUser(userDTO)).thenReturn(user);
        when(mapper.toPhone(phoneDTO)).thenReturn(phone);
        when(tokenService.generateToken("juan@gmail.com")).thenReturn("1234");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void storeUser() {
        User testUser = userService.storeUser(userDTO);

        Assertions.assertTrue(testUser.isActive());
        Assertions.assertEquals(testUser.getCreated(), testUser.getLastLogin());
        Assertions.assertEquals("Juan", testUser.getName());
        Assertions.assertEquals("juan@gmail.com", testUser.getEmail());
        Assertions.assertEquals("secret-password", testUser.getPassword());
        Assertions.assertEquals(UUID.fromString("123e4567-e89b-12d3-a456-426655440000"), testUser.getId());
        Assertions.assertEquals("1234", testUser.getToken());
        Assertions.assertEquals(1, testUser.getPhones().size());
    }
}