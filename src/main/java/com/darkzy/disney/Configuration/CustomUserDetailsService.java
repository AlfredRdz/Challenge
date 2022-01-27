package com.darkzy.disney.Configuration;

import com.darkzy.disney.Model.Usuario;
import com.darkzy.disney.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UsuarioRepository usuarioRepo;

    public CustomUserDetailsService(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Usuario> jwtUser = usuarioRepo.findByEmail(email);
        return jwtUser.map(usuario -> new org.springframework.security.core.userdetails.User(usuario.getEmail(), usuario.getContraseña(), new ArrayList<>())).orElseThrow(()-> new UsernameNotFoundException("Email no encontrado"));

    }
}
