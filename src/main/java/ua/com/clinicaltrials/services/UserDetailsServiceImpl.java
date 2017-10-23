package ua.com.clinicaltrials.services;

import ua.com.clinicaltrials.domain.Role;
import ua.com.clinicaltrials.domain.User;
import ua.com.clinicaltrials.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Igor on 29-Jun-16.
 */
@Service
@ComponentScan(basePackages = {
        "ua.com.clinicaltrials.controllers",
        "ua.com.clinicaltrials.domain",
        "ua.com.clinicaltrials.repositories",
        "ua.com.clinicaltrials.services",
        "ua.com.clinicaltrials.validator"})
public class UserDetailsServiceImpl implements UserDetailsService {
   @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userRepository.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("Username does not exist");
            }
        } catch (Exception e){
            throw new UsernameNotFoundException("Username does not exist");
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        Role role = user.getRole();
        grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
