package org.example.companyemployee.security;

import lombok.RequiredArgsConstructor;
import org.example.companyemployee.entity.User;
import org.example.companyemployee.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> byEmail = userRepository.findByEmail(username);

        if (byEmail.isEmpty()) {
            throw new UsernameNotFoundException("User with " + username + " does not exists!");
        }

        return new SpringUser(byEmail.get());
    }
}
