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

import com.bezkoder.spring.datajpa.model.Aluno;
import com.bezkoder.spring.datajpa.repository.AlunoRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class AlunoController {

	@Autowired
	AlunoRepository alunoRepository;

	@GetMapping("/alunos")
	public ResponseEntity<List<Aluno>> getAllAlunos(@RequestParam(required = false) String nome) {
		try {
			List<Aluno> alunos = new ArrayList<Aluno>();

			if (nome == null)
            alunoRepository.findAll().forEach(alunos::add);
			else
            alunoRepository.findByNomeContaining(nome).forEach(alunos::add);

			if (alunos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(alunos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/alunos/{id}")
	public ResponseEntity<Aluno> getAlunoById(@PathVariable("id") long id) {
		Optional<Aluno> alunoData = alunoRepository.findById(id);

		if (alunoData.isPresent()) {
			return new ResponseEntity<>(alunoData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/alunos")
	public ResponseEntity<Aluno> createAluno(@RequestBody Aluno aluno) {
		try {
			Aluno _aluno = alunoRepository
					.save(new Aluno(aluno.getNome(), aluno.getCurso(), false));
			return new ResponseEntity<>(_aluno, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/alunos/{id}")
	public ResponseEntity<Aluno> updateAluno(@PathVariable("id") long id, @RequestBody Aluno aluno) {
		Optional<Aluno> alunoData = alunoRepository.findById(id);

		if (alunoData.isPresent()) {
			Aluno _aluno = alunoData.get();
			_aluno.setNome(aluno.getNome());
			_aluno.setCurso(aluno.getCurso());
			_aluno.setPublished(aluno.isPublished());
			return new ResponseEntity<>(alunoRepository.save(_aluno), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/alunos/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		try {
			alunoRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@GetMapping("/alunos/published")
	public ResponseEntity<List<Aluno>> findByPublished() {
		try {
			List<Aluno> alunos = alunoRepository.findByPublished(true);

			if (alunos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(alunos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

