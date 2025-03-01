package com.exemplo.olamundo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.exemplo.olamundo.entity.Endereco;
import com.exemplo.olamundo.repository.EnderecoRepository;

@Service
public class EnderecoService {


    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco salvar(Endereco endereco) {
       try {
        return enderecoRepository.save(endereco);
       } catch (Exception e) {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao cadastrar endereço.");
       }
        
    }

    public List<Endereco> listarTodos() {
        return enderecoRepository.findAll();
    }

    public Endereco buscarPorId(Long id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado."));
    }

    public void excluir(long id) {
        if (!enderecoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado.");
        }
        enderecoRepository.deleteById(id);
    }

    



}
