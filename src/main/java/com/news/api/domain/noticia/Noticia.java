package com.news.api.domain.noticia;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "noticias")
@Entity(name = "Noticia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Noticia {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String resumo;
    private String conteudo;
    @Getter
    private LocalDateTime dataPublicacao;

    private String nomeAutor;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

public Noticia(DadosNovaNoticia dados) {
    this.titulo = dados.titulo();
    this.resumo = dados.resumo();
    this.conteudo = dados.conteudo();
    this.nomeAutor = dados.nomeAutor();
    this.categoria = dados.categoria();
    }

    public void atualizarNoticia(DadosAtualizarNoticia dados) {
    if (dados.conteudo() != null) {
        this.conteudo = dados.conteudo();
    }
    if (dados.titulo() != null) {
        this.titulo = dados.titulo();
    }
    if (dados.resumo() != null) {
        this.resumo = dados.resumo();
    }
    }

}
