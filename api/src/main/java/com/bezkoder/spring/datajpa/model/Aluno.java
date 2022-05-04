
package com.bezkoder.spring.datajpa.model;

import javax.persistence.*;

@Entity
@Table(name = "alunos")
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "curso")
	private String curso;

	@Column(name = "published")
	private boolean published;

	public Aluno() {

	}

	public Aluno(String nome, String curso, boolean published) {
		this.nome = nome;
		this.curso = curso;
		this.published = published;
	}

	public long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean isPublished) {
		this.published = isPublished;
	}

	@Override
	public String toString() {
		return "Tutorial [id=" + id + ", nome=" + nome + ", desc=" + curso + ", published=" + published + "]";
	}

}
