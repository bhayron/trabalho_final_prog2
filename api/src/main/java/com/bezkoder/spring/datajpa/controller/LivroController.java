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

import com.bezkoder.spring.datajpa.model.Livro;
import com.bezkoder.spring.datajpa.repository.LivroRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class LivroController {

	@Autowired
	LivroRepository livroRepository;

	@GetMapping("/livros")
	public ResponseEntity<List<Livro>> getAllALivros(@RequestParam(required = false) String nome) {
		try {
			List<Livro> livros = new ArrayList<Livro>();

			if (nome == null)
            livroRepository.findAll().forEach(livros::add);
			else
            livroRepository.findByNomeContaining(nome).forEach(livros::add);

			if (livros.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(livros, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/livros/{id}")
	public ResponseEntity<Livro> getLivroById(@PathVariable("id") long id) {
		Optional<Livro> livroData = livroRepository.findById(id);

		if (livroData.isPresent()) {
			return new ResponseEntity<>(livroData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/livros")
	public ResponseEntity<Livro> createLivro(@RequestBody Livro livro) {
		try {
			Livro _livro = livroRepository
					.save(new Livro(livro.getNome(), livro.getCurso(), false));
			return new ResponseEntity<>(_livro, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/livros/{id}")
	public ResponseEntity<Livro> updateLivro(@PathVariable("id") long id, @RequestBody Livro livro) {
		Optional<Livro> livroData = livroRepository.findById(id);

		if (livroData.isPresent()) {
			Livro _livro = livroData.get();
			_livro.setNome(livro.getNome());
			_livro.setCurso(livro.getCurso());
			_livro.setPublished(livro.isPublished());
			return new ResponseEntity<>(livroRepository.save(_livro), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/livros/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		try {
			livroRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@GetMapping("/livros/published")
	public ResponseEntity<List<Livro>> findByPublished() {
		try {
			List<Livro> livros = livroRepository.findByPublished(true);

			if (livros.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(livros, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

