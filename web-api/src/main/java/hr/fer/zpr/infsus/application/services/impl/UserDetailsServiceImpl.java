package hr.fer.zpr.infsus.application.services.impl;

import hr.fer.zpr.infsus.application.Constants;
import hr.fer.zpr.infsus.domain.EUserType;
import hr.fer.zpr.infsus.domain.User;
import hr.fer.zpr.infsus.domain.UserDetailsImpl;
import hr.fer.zpr.infsus.domain.UserType;
import hr.fer.zpr.infsus.infrastructure.UserRepository;
import hr.fer.zpr.infsus.infrastructure.UserTypeRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;

    public UserDetailsServiceImpl(UserRepository userRepository, UserTypeRepository userTypeRepository) {
        this.userRepository = userRepository;
        this.userTypeRepository = userTypeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Jwt principal = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isEmpty()) {
            UserType ut = userTypeRepository.findByTypeName(EUserType.USER.name()).get();
            User newUser = new User(principal.getClaimAsString(Constants.USERNAME_CLAIM), principal.getClaimAsString(Constants.EMAIL_CLAIM), ut);
            userRepository.save(newUser);
            return new UserDetailsImpl(newUser);
        }
        return new UserDetailsImpl(user.get());
    }
}
