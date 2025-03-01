package com.exemplo.olamundo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.exemplo.olamundo.entity.Usuario;
import com.exemplo.olamundo.service.UsuarioService;

@RestController
@RequestMapping("/Usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario cadastrar(@RequestBody Usuario usuario) {
        return usuarioService.cadastrar(usuario);
    }

    @GetMapping("/listartodos")
    public List<Usuario> listarTodos() {
        return usuarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    }

    @GetMapping("/cpf/{cpf}")
    public Usuario buscarPorCpf(@PathVariable String cpf) {
        return usuarioService.buscarPorCpf(cpf);
    }

    @GetMapping("/nome/{nome}")
    public List<Usuario> buscarPorNome(@PathVariable String nome) {
        return usuarioService.buscarPorNome(nome);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        usuarioService.excluirPorId(id);
    }

    @PutMapping("/{id}")
    public Usuario atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.atualizar(id, usuario);
    }
}