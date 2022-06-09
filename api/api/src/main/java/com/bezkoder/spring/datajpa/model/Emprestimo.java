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

	public Emprestimo(String aluno, String livro, String dataEmprestimo, String dataDevolucao, boolean published) {
		this.aluno = aluno;
		this.livro = livro;
		this.dataEmprestimo = dataEmprestimo;
		this.dataDevolucao = dataDevolucao;
		this.published = published;
	}

	public long getId() {
		return id;
	}

	public String getAluno() {
		return aluno;
	}

	public void setAluno(String aluno) {
		this.aluno = aluno;
	}

	public String getLivro() {
		return livro;
	}

	public void setLivro(String livro) {
		this.livro = livro;
	}

	public String getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(String dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public String getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(String dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}


	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean isPublished) {
		this.published = isPublished;
	}

	@Override
	public String toString() {
		return "Tutorial [id=" + id + ", aluno=" + aluno + ", desc=" + livro + ", published=" + published + ",livro=" + livro + ", dataEmprestimo=" + dataEmprestimo + ", dataDevolucao=" + dataDevolucao + "]";
	}

}
