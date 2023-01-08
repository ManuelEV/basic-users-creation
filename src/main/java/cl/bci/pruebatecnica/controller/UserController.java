package cl.bci.pruebatecnica.controller;

import cl.bci.pruebatecnica.dto.UserDTO;
import cl.bci.pruebatecnica.model.User;
import cl.bci.pruebatecnica.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<User> storeUser(@Valid @RequestBody UserDTO userDTO) {

        return ResponseEntity.ok(userService.storeUser(userDTO));
    }

}
