package com.darkzy.disney.Controller;

import com.darkzy.disney.dto.LoginRespuesta;
import com.darkzy.disney.dto.UsuarioDto;
import com.darkzy.disney.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;

@RestController
//@RequestMapping
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/auth/register")
    public String registro(@RequestBody UsuarioDto usuario) {
        
        if (!checarEmail(usuario.getEmail())) {
            return "Debes ingresar un correo valido";
        }
        
        usuarioService.registrarUsuario(usuario);
        return "exito";
    }

    private boolean checarEmail(String email) {
        if (Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$").matcher(email).matches()) {
            return true;
        } else {
            return false;
        }
    }

    @PostMapping("/auth/login")
    public LoginRespuesta inicioSesion(@RequestBody UsuarioDto usuario) {

        return new LoginRespuesta(usuarioService.iniciarSesion(usuario));
    }
}
