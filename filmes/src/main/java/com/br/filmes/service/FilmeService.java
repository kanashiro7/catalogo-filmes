package com.br.filmes.service;

import com.br.filmes.DTO.FilmeDTO;
import com.br.filmes.model.Filme;
import com.br.filmes.repository.FilmeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class FilmeService {
    private final FilmeRepository filmeRepository;
    private final ModelMapper modelMapper;

    public List<FilmeDTO> listarTodos() {
        return filmeRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public FilmeDTO buscarPorId(Long id) {
        Filme filme = filmeRepository.findById(id)
                .orElseThrow(() -> new FilmeNotFoundException(id));
        return toDTO(filme);
    }

}
