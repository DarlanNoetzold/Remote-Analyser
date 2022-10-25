package tech.noetzold.remoteanalyser.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tech.noetzold.remoteanalyser.repository.UserRepository;
import tech.noetzold.remoteanalyser.model.UserImp;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository usuarioRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserImp usuario = usuarioRepo.findByUsername(username);

        Set<GrantedAuthority> authorities = new HashSet<>();

        User userSpring = new User(usuario.getUsername(), usuario.getPassword(), authorities);

        return userSpring;
    }
}
