package com.exemplo.olamundo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



import com.exemplo.olamundo.entity.Endereco;
import com.exemplo.olamundo.service.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {



    @Autowired
    private EnderecoService enderecoService;

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public Endereco cadastrar(@RequestBody Endereco endereco) {
        return enderecoService.salvar(endereco);
    }   

    @GetMapping("/listartodos")
    public List<Endereco> listarTodos() {
        return enderecoService.listarTodos();
    }
    
    @GetMapping("/{id}")
    public Endereco buscarPorId(@PathVariable Long id) {
        return enderecoService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        enderecoService.excluir(id);
    }



}
