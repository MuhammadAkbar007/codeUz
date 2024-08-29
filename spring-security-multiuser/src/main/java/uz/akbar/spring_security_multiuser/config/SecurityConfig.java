package uz.akbar.spring_security_multiuser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Autowired
//    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        // authentication - Foydalanuvchining identifikatsiya qilish.
        // Ya'ni berilgan login va parolli user bor yoki yo'qligini aniqlash.
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance()); // parol shifrlanmayotganini ko'rsatish uchun
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // authorization - Foydalanuvchining tizimdagi huquqlarini tekshirish.
        // Ya'ni foydalanuvchi murojat qilayotgan API-larni ishlatishga ruxsati bor yoki yo'qligini tekshirishdir.
        http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                authorizationManagerRequestMatcherRegistry
                        .requestMatchers("/profile/registration").permitAll()
//                        .requestMatchers("/task").permitAll() // for all methods
//                        .requestMatchers("/task/*").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/task/*").permitAll()
                        .requestMatchers("/task/finished", "/task/my/all").permitAll()
                        .requestMatchers(HttpMethod.GET, "/task/**").permitAll() // all GET after /task/
//                        .requestMatchers("/task/finished/all").hasRole("USER") // ROLE_USER
                        .requestMatchers("/task/finished/all").hasAnyRole("USER", "ADMIN") // ROLE_USER
                        .anyRequest()
                        .authenticated())
//                formLogin(Customizer.withDefaults()  comment it to use httpBasic
        ;

        http.httpBasic(Customizer.withDefaults());

//        http.csrf(Customizer.withDefaults()); // csrf yoqilgan
        http.csrf(AbstractHttpConfigurer::disable); // csrf o'chirilgan
//        http.cors(Customizer.withDefaults()); // cors yoqilgan
        http.cors(AbstractHttpConfigurer::disable); // cors o'chirilgan
        return http.build();
    }

}
