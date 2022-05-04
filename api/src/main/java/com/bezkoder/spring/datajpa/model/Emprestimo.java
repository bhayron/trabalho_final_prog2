package com.bezkoder.spring.datajpa.model;

import javax.persistence.*;

@Entity
@Table(name = "emprestimos")
public class Emprestimo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "aluno")
	private String aluno;

	@Column(name = "livro")
	private String livro;

    @Column(name = "dataEmprestimo")
	private String dataEmprestimo;

    @Column(name = "dataDevolucao")
	private String dataDevolucao;

	@Column(name = "published")
	private boolean published;

	public Emprestimo() {

	}

	public Emprestimo(String aluno, String livro,String dataEmprestimo, String dataDevolucao, boolean published) {
		this.aluno = aluno;
		this.livro = livro;
		this.dataEmprestimo = dataEmprestimo;
		this.dataDevolucao = dataDevolucao;
		this.published = published;
	}

	public long getId() {
		return id;
	}

	public String getEmprestimo() {
		return aluno;
	}

	public void setEmorestimo(String aluno) {
		this.aluno = aluno;
	}

	public String getEmorestimo() {
		return livro;
	}

	public void setlivro(String livro) {
		this.livro = livro;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean isPublished) {
		this.published = isPublished;
	}

	@Override
	public String toString() {
		return "Tutorial [id=" + id + ", aluno=" + aluno + ", desc=" + livro + ", published=" + published + "]";
	}

}
