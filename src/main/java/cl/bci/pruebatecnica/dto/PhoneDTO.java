package cl.bci.pruebatecnica.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PhoneDTO {
    @NotBlank
    private String number;
    @NotBlank
    private String cityCode;
    @NotBlank
    private String countryCode;
}
