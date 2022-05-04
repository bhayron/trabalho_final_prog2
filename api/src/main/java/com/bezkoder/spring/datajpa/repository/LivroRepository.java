package com.bezkoder.spring.datajpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.datajpa.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
	List<Livro> findByPublished(boolean published);
	List<Livro> findByNomeContaining(String nome);
}
