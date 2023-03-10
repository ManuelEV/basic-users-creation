package cl.bci.pruebatecnica.service;

import cl.bci.pruebatecnica.PruebaTecnicaBciApplication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = PruebaTecnicaBciApplication.class)
class TokenServiceImplTest {

    @Autowired
    private TokenService tokenService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void generateToken() {
        tokenService.generateToken("mySubject");
    }
}