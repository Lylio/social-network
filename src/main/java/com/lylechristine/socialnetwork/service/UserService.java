package com.lylechristine.socialnetwork.service;

import com.lylechristine.socialnetwork.model.Role;
import com.lylechristine.socialnetwork.model.User;
import com.lylechristine.socialnetwork.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository users; private final PasswordEncoder encoder;
    public UserService(UserRepository users, PasswordEncoder encoder){this.users=users;this.encoder=encoder;}
    @Transactional
    public User register(String username,String email,String password){
        if(users.existsByUsername(username)) throw new IllegalArgumentException("Username already exists");
        if(users.existsByEmail(email)) throw new IllegalArgumentException("Email already exists");
        User u=new User(); u.setUsername(username.trim());u.setEmail(email.trim());u.setPassword(encoder.encode(password));u.setRole(Role.USER);return users.save(u);
    }
    public User require(String username){return users.findByUsername(username).orElseThrow();}
    @Override public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User u=users.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
        return org.springframework.security.core.userdetails.User.withUsername(u.getUsername()).password(u.getPassword()).roles(u.getRole().name()).disabled(!u.isEnabled()).build();
    }
}
