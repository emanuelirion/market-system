package com.emanuel.services.impi;

import com.emanuel.modal.User;
import com.emanuel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

//Ponte entre Spring Security e o banco de dados, ele busca o usuario e compara a senha
//Diz ao Spring Security como buscar um usuário para login

@Service
public class CustomUserImplementation implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        User user = userRepository.findByEmail(username);
        if(user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().toString());

        Collection<GrantedAuthority> authorities =  Collections.singletonList(authority);
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),user.getPassword(), authorities
        );

    }
}
