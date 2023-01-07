package cl.bci.pruebatecnica.mapper;

import cl.bci.pruebatecnica.PruebaTecnicaBciApplication;
import cl.bci.pruebatecnica.dto.PhoneDTO;
import cl.bci.pruebatecnica.dto.UserDTO;
import cl.bci.pruebatecnica.model.Phone;
import cl.bci.pruebatecnica.model.User;
import cl.bci.pruebatecnica.response.UserResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.UUID;

@SpringBootTest(classes = PruebaTecnicaBciApplication.class)
class MapperTest {

    private UserDTO userDTO;
    private PhoneDTO phoneDTO;
    private User user;
    @Autowired
    private Mapper mapper;

    @BeforeEach
    void setUp() {
        phoneDTO = new PhoneDTO("1234567", "1", "57");
        userDTO = new UserDTO("Juan", "juan@gmail.com", "secret-password", new ArrayList<>());
        user = new User();
        user.setId(UUID.fromString("123e4567-e89b-12d3-a456-426655440000"));
        user.setToken("1234");
        user.setActive(true);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void toUser() {
        User testUser = mapper.toUser(userDTO);
        Assertions.assertEquals("Juan", testUser.getName());
        Assertions.assertEquals("juan@gmail.com", testUser.getEmail());
        Assertions.assertEquals("secret-password", testUser.getPassword());
    }

    @Test
    void toUserResponse() {
        UserResponse userResponse = mapper.toUserResponse(user);
        Assertions.assertEquals(UUID.fromString("123e4567-e89b-12d3-a456-426655440000"), userResponse.id());
        Assertions.assertEquals("1234", userResponse.token());
        Assertions.assertTrue(userResponse.isActive());
    }

    @Test
    void toPhone() {
        Phone phone = mapper.toPhone(phoneDTO);
        Assertions.assertEquals("1234567", phone.getNumber());
        Assertions.assertEquals("1", phone.getCityCode());
        Assertions.assertEquals("57", phone.getCountryCode());
    }
}