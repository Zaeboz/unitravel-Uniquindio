package test;

import co.edu.uniquindio.unitravel.entidades.Usuario;
import co.edu.uniquindio.unitravel.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

public class UsuarioTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Test
    public void registrar(){
        Usuario usuario = new Usuario("92282","pepito","pepe@gmail.com","12345678");
        Usuario usuarioGuardado = usuarioRepo.save(usuario);

        Assertions.assertNotNull(usuarioGuardado);
    }

    @Test
    public void eliminar (){
        Usuario usuario = new Usuario("92282","pepito","pepe@gmail.com","12345678");
        Usuario usuarioGuardado = usuarioRepo.save(usuario);

        usuarioRepo.delete(usuarioGuardado);

        Optional<Usuario> usuarioBuscado = usuarioRepo.findById("92282");
        Assertions.assertNotNull(usuarioBuscado);
    }

    @Test
    public void actualizar (){
        Usuario usuario = new Usuario("92282","pepito","pepe@gmail.com","12345678");
        Usuario usuarioGuardado = usuarioRepo.save(usuario);

        usuarioGuardado.setPassword("abc123");
        usuarioRepo.save(usuarioGuardado);

        Usuario usuarioBuscado = usuarioRepo.findById("92282").orElse(null);
        Assertions.assertEquals("abc123",usuarioBuscado.getPassword());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listar(){
        List<Usuario> usuarios = usuarioRepo.findAll();

        System.out.println(usuarios);
    }
}


