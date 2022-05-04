package com.bezkoder.spring.datajpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.datajpa.model.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
	List<Emprestimo> findByPublished(boolean published);
	List<Emprestimo> findByAlunoContaining(String aluno);
}
