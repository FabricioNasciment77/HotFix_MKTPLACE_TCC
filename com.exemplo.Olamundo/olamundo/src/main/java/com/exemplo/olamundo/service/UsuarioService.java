package com.exemplo.olamundo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.exemplo.olamundo.entity.Usuario;
import com.exemplo.olamundo.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario cadastrar(Usuario usuario) {
        try
        {
            return usuarioRepository.save(usuario);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao cadastrar usuário.");
        }
        
    }
    
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }



    public Usuario buscarPorId(Long id) {
    return usuarioRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));

    }

    public Usuario buscarPorCpf(String cpf) {
        return usuarioRepository.findAll().stream() 
                .filter(u -> u.getCpf().equals(cpf))
                .findFirst()   
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));
    }  
    
    public List<Usuario> buscarPorNome(String nome) {
        List<Usuario> usuarios = usuarioRepository.findAll().stream() 
                .filter(u -> u.getNome().equalsIgnoreCase(nome))
                .toList();

        if (usuarios.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.");
        }   
        return usuarios;
    }
    
    public void excluirPorId(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.");
        }
        usuarioRepository.deleteById(id);
    }

    public Usuario atualizar(Long id, Usuario usuario) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.");
        }
        usuario.setId(id);
        return usuarioRepository.save(usuario);
    }
}








