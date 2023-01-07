package cl.bci.pruebatecnica.mapper;

import cl.bci.pruebatecnica.dto.PhoneDTO;
import cl.bci.pruebatecnica.dto.UserDTO;
import cl.bci.pruebatecnica.model.Phone;
import cl.bci.pruebatecnica.model.User;
import cl.bci.pruebatecnica.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public User toUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        return user;
    }

    public UserResponse toUserResponse(User user) {
        return new UserResponse(user.getId(),
                user.getCreated(),
                user.getModified(),
                user.getLastLogin(),
                user.getToken(),
                user.isActive());
    }

    public Phone toPhone(PhoneDTO phoneDTO) {
        Phone phone = new Phone();
        phone.setNumber(phoneDTO.getNumber());
        phone.setCityCode(phoneDTO.getCityCode());
        phone.setCountryCode(phoneDTO.getCountryCode());
        return phone;
    }

}
