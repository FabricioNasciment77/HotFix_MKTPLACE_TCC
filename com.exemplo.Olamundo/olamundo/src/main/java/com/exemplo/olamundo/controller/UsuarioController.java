package com.exemplo.olamundo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.exemplo.olamundo.entity.Usuario;
import com.exemplo.olamundo.repository.UsuarioRepository;

@RestController
@RequestMapping("/Usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

     @PostMapping("/cadastrar")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Usuario cadastrar(@RequestBody Usuario usuario) {
        try
        {
            return usuarioRepository.save(usuario);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void excluirPorId(@PathVariable Long id) {
        var usuario = usuarioRepository.findById(id);
        if(usuario.isEmpty()){ 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        usuarioRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Usuario atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        var usuarioBanco = usuarioRepository.findById(id);
        if(usuarioBanco.isEmpty()){ 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        usuario.setId(id);
        return usuarioRepository.save(usuario);
    }


    @GetMapping("/listartodos")
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario Id(@PathVariable Long id) {

        var usuario = usuarioRepository.findById(id);
        if(usuario.isEmpty()){ 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return usuario.get();
    }

    @GetMapping("cpf/{cpf}")
    public Usuario Id(@PathVariable String cpf) {

        var usuario = usuarioRepository.findAll().stream().filter(u -> u.getCpf().equals(cpf)).findFirst();
        if(usuario.isEmpty()){ 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return usuario.get();
    }

    @GetMapping("nome/{nome}")
    public List<Usuario> Nome(@PathVariable String nome) {

        var usuario = usuarioRepository.findAll().stream().filter(u -> u.getNome().equals(nome)).toList();
        if(usuario.isEmpty()){ 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return usuario;
    }

}
