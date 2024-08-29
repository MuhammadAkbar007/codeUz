package uz.akbar.spring_security_multiuser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.akbar.spring_security_multiuser.dto.ProfileDto;
import uz.akbar.spring_security_multiuser.service.ProfileService;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @PostMapping("/registration")
    public ResponseEntity<ProfileDto> create(@RequestBody ProfileDto profileDto) {
        ProfileDto result = profileService.registration(profileDto);
        return ResponseEntity.ok(result);
    }
}
