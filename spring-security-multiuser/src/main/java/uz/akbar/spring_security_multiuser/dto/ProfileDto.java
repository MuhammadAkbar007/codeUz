package uz.akbar.spring_security_multiuser.dto;

import lombok.Getter;
import lombok.Setter;
import uz.akbar.spring_security_multiuser.enums.ProfileRole;

@Getter
@Setter
public class ProfileDto {
    private String id;
    private String name;
    private String surname;
    private String phone;
    private String password;
    private ProfileRole role;
}
