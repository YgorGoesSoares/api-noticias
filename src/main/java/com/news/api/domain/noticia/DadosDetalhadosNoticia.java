package com.news.api.domain.noticia;

import java.time.LocalDateTime;

public record DadosDetalhadosNoticia(
        Long id,
        String titulo,
        String resumo,
        String conteudo,
        LocalDateTime dataPublicacao,
        String nomeAutor,
        Categoria categoria
        ) {

    public DadosDetalhadosNoticia (Noticia noticia) {
        this(noticia.getId(), noticia.getTitulo(), noticia.getResumo(), noticia.getConteudo(), noticia.getDataPublicacao(), noticia.getNomeAutor(), noticia.getCategoria());
    }


}
