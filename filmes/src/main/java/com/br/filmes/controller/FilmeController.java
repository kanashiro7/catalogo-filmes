package com.br.filmes.controller;


import com.br.filmes.dto.FilmeDTO;
import com.br.filmes.model.Filme;
import com.br.filmes.service.FilmeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes") //define o prefixo da URL (http://localhost:8080/filmes)
public class FilmeController {
    private final FilmeService filmeService;

    //injeção de dependência via construtor
    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    //POST - Cadastrar filme
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FilmeDTO cadastrar(@Valid @RequestBody FilmeDTO filmeDTO) {
        return filmeService.cadastrar(filmeDTO);
    }

    //GET - Listar todos os filmes
    @GetMapping
    public List<FilmeDTO> listarTodos() {
        return filmeService.listarTodos();
    }

    //GET - Buscar por id
    @GetMapping("/{id}")
    public FilmeDTO buscarPorId(@PathVariable Long id) {
        return filmeService.buscarPorId(id);
    }

    // PUT - Atualizar filme
    @PutMapping("/{id}")
    public FilmeDTO atualizar(@PathVariable Long id,  @RequestBody FilmeDTO filmeDTO) {
        return  filmeService.atualizar(id, filmeDTO);
    }

    //DELETE - Deletar filme
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        filmeService.deletar(id);
    }
}
