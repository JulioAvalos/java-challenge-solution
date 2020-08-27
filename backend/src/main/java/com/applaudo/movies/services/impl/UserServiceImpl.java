package com.applaudo.movies.services.impl;

import com.applaudo.movies.domain.User;
import com.applaudo.movies.repositories.UserRepository;
import com.applaudo.movies.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> listAll() {
        return userRepository.findAll();
    }

    @Override
    public User add(User obj) {
        return userRepository.save(obj);
    }

    @Override
    public User modify(Long id, User obj) {
        return userRepository.save(obj);
    }

    @Override
    public User getById(Long id) {
        Optional<User> op = userRepository.findById(id);
        return op.orElseGet(User::new);
    }

    @Override
    public boolean remove(Long id) {
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOneByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User doesn't exist", username));
        }

        List<GrantedAuthority> roles = new ArrayList<>();

        user.getRoles().forEach(role -> {
            roles.add(new SimpleGrantedAuthority(role.getName()));
        });

        UserDetails ud = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
        return ud;
    }
}
