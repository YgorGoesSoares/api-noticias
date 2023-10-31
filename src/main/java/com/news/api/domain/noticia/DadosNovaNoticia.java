package com.news.api.domain.noticia;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosNovaNoticia(
        @NotBlank
        String titulo,
        @NotBlank
        String resumo,
        @NotBlank
        String conteudo,
        @NotBlank
        String nomeAutor,
        @NotNull
        Categoria categoria


) {
}
