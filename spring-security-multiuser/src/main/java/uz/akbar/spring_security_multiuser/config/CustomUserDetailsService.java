package uz.akbar.spring_security_multiuser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.akbar.spring_security_multiuser.entity.ProfileEntity;
import uz.akbar.spring_security_multiuser.repository.ProfileRepository;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    ProfileRepository profileRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ProfileEntity> optional = profileRepository.findByPhoneAndVisibleTrue(username);

        if (optional.isEmpty()) throw new UsernameNotFoundException(username);

        ProfileEntity profile = optional.get();

        CustomUserDetails userDetails = new CustomUserDetails(profile);
        return userDetails;
    }
}
