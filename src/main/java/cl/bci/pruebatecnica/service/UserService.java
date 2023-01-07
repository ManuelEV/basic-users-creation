package cl.bci.pruebatecnica.service;

import cl.bci.pruebatecnica.dto.UserDTO;
import cl.bci.pruebatecnica.model.User;
import cl.bci.pruebatecnica.response.UserResponse;

public interface UserService {

    User storeUser(UserDTO userDTO);

}
