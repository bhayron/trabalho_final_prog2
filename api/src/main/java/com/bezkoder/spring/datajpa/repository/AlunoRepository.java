package com.bezkoder.spring.datajpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.datajpa.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
	List<Aluno> findByPublished(boolean published);
	List<Aluno> findByNomeContaining(String nome);
}
