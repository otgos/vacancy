package com.vacancy.vacancy.service;

import com.vacancy.vacancy.entity.Role;
import com.vacancy.vacancy.entity.User;
import com.vacancy.vacancy.repository.RoleRepository;
import com.vacancy.vacancy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user!=null){
            return user;
        }else{
            throw new UsernameNotFoundException("USER NOT FOUND");
        }
    }

    public User saveUser(User user){
        User checkUser = userRepository.findByEmail(user.getEmail());
        if(checkUser==null) {
            Role role = roleRepository.findByRole("ROLE_USER");
            if(role!=null){
                ArrayList<Role> roles = new ArrayList<>();
                roles.add(role);
                user.setRole(roles);
                return userRepository.save(user);
            }

        }
        return null;
    }

}
