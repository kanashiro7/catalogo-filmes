package com.br.filmes.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilmeDTO {
    private Long Id;

    @NotBlank(message = "Título é obrigatório")
    @Size(max = 100, message = "Título deve ter no máximo 100 caracteres")

    private String titulo;

    @NotBlank(message = "Descrição é obrigatória")
    @Size(max = 500, message = "Descrição deve ter no máximo 500 caracteres")
    private String descricao;

    @NotNull(message = "Ano de lançamento é obrigatória")
    @Min(value = 1000, message = "Ano do lançamento é inválido")
    @Max(value = 2100, message = "Ano de lançamento inválido")

    private Integer anoLancamento;

    private LocalDateTime dataCadastro;
}
