package br.com.tarssito.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.tarssito.util.Model;

@SuppressWarnings("serial")
@Entity
@Table(name = "funcao_usuario_externo")
public class Funcao implements Model<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "co_funcao")
	private Long id;

	@Column(name = "no_funcao", length = 50)
	private String nome;

	public Funcao() {
	}

	public Funcao(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
