package com.br.filmes.service;

import com.br.filmes.dto.FilmeDTO;
import com.br.filmes.exception.FIlmeNotFoundException;
import com.br.filmes.model.Filme;
import com.br.filmes.repository.FilmeRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class FilmeService {
    @Autowired
    private FilmeRepository filmeRepository;

    private final ModelMapper modelMapper = new ModelMapper(); // injeção manual (alternativa)

    // MÉTODOS PRINCIPAIS

    // Listar todos os filmes (converendo para DTO)
    public List<FilmeDTO> listarTodos() {
        return filmeRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Buscar por id (com tratamento do erro)
    public FilmeDTO buscarPorId(Long id) {
        Filme filme = filmeRepository.findById(id)
                .orElseThrow(() -> new FIlmeNotFoundException(id));
        return toDTO(filme);
    }

    // Cadastrar (recebe DTO e converte para entidade)
    public FilmeDTO cadastrar(@Valid FilmeDTO filmeDTO) {
        Filme filme = toEntity(filmeDTO);
        Filme filmeSalvo = filmeRepository.save(filme);
        return toDTO(filmeSalvo);
    }

    // Atualizar (verifica existência antes)
    public FilmeDTO atualizar(Long id, FilmeDTO filmeDTO) {
        Filme filmeExistente = filmeRepository.findById(id)
                .orElseThrow(() -> new FIlmeNotFoundException(id));

        modelMapper.map(filmeDTO, filmeExistente);
        Filme filmeAtualizado = filmeRepository.save(filmeExistente);

        return toDTO(filmeAtualizado);
    }

    // Deletar
    public void deletar(Long id) {
        if(!filmeRepository.existsById(id)) {
            throw new FIlmeNotFoundException(id);
        }
        filmeRepository.deleteById(id);
    }

    //MÉTODOS AUXILIARES (CONVERSÃO)

    private FilmeDTO toDTO(Filme filme) {
        return modelMapper.map(filme, FilmeDTO.class);
    }

    private Filme toEntity(FilmeDTO filmeDTO) {
        return modelMapper.map(filmeDTO, Filme.class);
    }
}
