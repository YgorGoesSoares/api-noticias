package com.news.api.domain.noticia;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarNoticia(
        @NotNull
        Long id,
        String titulo,
        String resumo,
        String conteudo
) {

}
