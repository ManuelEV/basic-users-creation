package cl.bci.pruebatecnica.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    @NotBlank
    private String name;
    @Pattern(regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "El campo debe tener formato de email")
    private String email;
    @NotBlank
    private String password;
    @Valid
    List<PhoneDTO> phones;
}
