package com.example.challenge.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.challenge.entities.RoleEntity;
import com.example.challenge.entities.UserEntity;
import com.example.challenge.repository.interfaces.UserRepository;

 
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    private UserRepository repo;
 
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = repo.findByUsername(username).get();
        if (user != null) {
            return new User(user.getUsername(), user.getPassword(), buildSimpleGrantedAuthorities(user.getRoles()));
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
 
    private static List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(final Set<RoleEntity> roles) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (RoleEntity role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
}
