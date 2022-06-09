package com.bezkoder.spring.datajpa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.datajpa.model.Emprestimo;
import com.bezkoder.spring.datajpa.repository.EmprestimoRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class EmprestimoController {

	@Autowired
	EmprestimoRepository emprestimoRepository;

	@GetMapping("/emprestimos")
	public ResponseEntity<List<Emprestimo>> getAllAlunos(@RequestParam(required = false) String nome) {
		try {
			List<Emprestimo> alunos = new ArrayList<Emprestimo>();

			if (nome == null)
            emprestimoRepository.findAll().forEach(alunos::add);
			else
            emprestimoRepository.findByAlunoContaining(nome).forEach(alunos::add);

			if (alunos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(alunos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/emprestimos/{id}")
	public ResponseEntity<Emprestimo> getAlunoById(@PathVariable("id") long id) {
		Optional<Emprestimo> alunoData = emprestimoRepository.findById(id);

		if (alunoData.isPresent()) {
			return new ResponseEntity<>(alunoData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/emprestimos")
	public ResponseEntity<Emprestimo> createAluno(@RequestBody Emprestimo emprestimo) {
		try {
			Emprestimo _emprestimo = emprestimoRepository
					.save(new Emprestimo(emprestimo.getAluno(), emprestimo.getLivro(), emprestimo.getDataEmprestimo(), emprestimo.getDataDevolucao(), false));
			return new ResponseEntity<>(_emprestimo, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/emprestimos/{id}")
	public ResponseEntity<Emprestimo> updateAluno(@PathVariable("id") long id, @RequestBody Emprestimo emprestimo) {
		Optional<Emprestimo> emprestimoData = emprestimoRepository.findById(id);

		if (emprestimoData.isPresent()) {
			Emprestimo _emprestimo = emprestimoData.get();
			_emprestimo.setAluno(emprestimo.getAluno());
			_emprestimo.setLivro(emprestimo.getLivro());
			_emprestimo.setDataEmprestimo(emprestimo.getDataEmprestimo());
			_emprestimo.setDataDevolucao(emprestimo.getDataDevolucao());
			_emprestimo.setPublished(emprestimo.isPublished());
			return new ResponseEntity<>(emprestimoRepository.save(_emprestimo), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/emprestimos/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		try {
			emprestimoRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@GetMapping("/emprestimos/published")
	public ResponseEntity<List<Emprestimo>> findByPublished() {
		try {
			List<Emprestimo> alunos = emprestimoRepository.findByPublished(true);

			if (alunos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(alunos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

