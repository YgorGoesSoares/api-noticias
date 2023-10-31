package com.news.api.repository;

import com.news.api.domain.noticia.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticiaRepository extends JpaRepository<Noticia, Long> {


}
