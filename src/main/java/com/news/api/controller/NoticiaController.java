package com.news.api.controller;

import com.news.api.domain.noticia.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import com.news.api.repository.NoticiaRepository;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/noticias")
@SecurityRequirement(name = "bearer-key")
public class NoticiaController {

    @Autowired
    private NoticiaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity publicarNoticia(@RequestBody @Valid DadosNovaNoticia dados, UriComponentsBuilder uriBuilder) {

        var noticia = new Noticia(dados);
        noticia.setDataPublicacao(LocalDateTime.now());
        repository.save(noticia);

        var uri = uriBuilder.path("/noticias/{id}").buildAndExpand(noticia.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhadosNoticia(noticia));

    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhadosNoticia>> listar(@PageableDefault(size = 10)Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosDetalhadosNoticia::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity editarNoticia(@RequestBody @Valid DadosAtualizarNoticia dados) {
        var noticia = repository.getReferenceById(dados.id());
        noticia.atualizarNoticia(dados);

        return ResponseEntity.ok(new DadosDetalhadosNoticia(noticia));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity editarNoticia(@RequestBody @Valid DadosAtualizarNoticia dados, @PathVariable Long id) {
        var noticia = repository.getReferenceById(id);
        noticia.atualizarNoticia(dados);
        return ResponseEntity.ok(new DadosDetalhadosNoticia(noticia));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarNotícia(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarNoticia(@RequestBody @Valid DadosDeletarNoticia dados) {
        if (repository.existsById(dados.id())) {
            repository.deleteById(dados.id());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
