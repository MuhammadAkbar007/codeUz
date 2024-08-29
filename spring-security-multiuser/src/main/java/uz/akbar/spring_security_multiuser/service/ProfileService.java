package uz.akbar.spring_security_multiuser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import uz.akbar.spring_security_multiuser.dto.ProfileDto;
import uz.akbar.spring_security_multiuser.entity.ProfileEntity;
import uz.akbar.spring_security_multiuser.repository.ProfileRepository;

import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    ProfileRepository profileRepository;

    public ProfileDto registration(ProfileDto dto) {
        Optional<ProfileEntity> optional = profileRepository.findByPhoneAndVisibleTrue(dto.getPhone());
        if (optional.isPresent()) {
            return null;
        }

        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPhone(dto.getPhone());
        entity.setPassword(dto.getPassword());
        entity.setRole(dto.getRole());
        profileRepository.save(entity);

        dto.setId(entity.getId());
        return dto;
    }
}
