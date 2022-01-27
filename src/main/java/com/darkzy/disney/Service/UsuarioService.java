package com.darkzy.disney.Service;

import com.darkzy.disney.Configuration.CustomUserDetailsService;
import com.darkzy.disney.Configuration.EmailSenderService;
import com.darkzy.disney.Configuration.JwtUtils;
import com.darkzy.disney.dto.UsuarioDto;
import com.darkzy.disney.Model.Usuario;
import com.darkzy.disney.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
	private EmailSenderService senderService;

    private void checarEmail(String email){
        if (usuarioRepository.existsByEmail(email)) {
            throw new IllegalStateException("El email ya esta en uso");
        }
    }

    private void enviarEmail(String correo) {
        senderService.sendMailSender(correo, "Usuario Registrado", "hola " + correo + " gracias por registrarte");
    }

    public void registrarUsuario(UsuarioDto usuario) {

        checarEmail(usuario.getEmail());

        Usuario usuario1 = new Usuario();

        usuario1.setEmail(usuario.getEmail());
        usuario1.setContraseña(bCryptPasswordEncoder.encode(usuario.getContraseña()));

        enviarEmail(usuario1.getEmail());

        usuarioRepository.save(usuario1);
    }

    public String iniciarSesion(UsuarioDto usuario) {
        Authentication authentication = authenticate(usuario);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(usuario.getEmail());

        return jwtUtils.generateToken(userDetails);
    }

    private Authentication authenticate(UsuarioDto usuario) {
        Authentication authentication;

        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getContraseña()));

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Email y/o contraseña incorrecta");
        }

        return authentication;
    }
}
