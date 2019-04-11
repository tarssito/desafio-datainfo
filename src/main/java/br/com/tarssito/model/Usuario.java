package br.com.tarssito.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.tarssito.util.Model;

@Entity
@Table(name = "usuario_externo")
public class Usuario implements Model<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "co_usuario_externo")
	private Long id;

	@Column(name = "nu_cpf", length = 11, unique=true)
	private String cpf;

	@Column(name = "no_usuario", length = 60)
	private String nome;

	@Column(name = "de_email")
	private String email;

	@Column(name = "ic_situacao", length = 1)
	private String situacao;

	@Column(name = "ic_perfil_acesso")
	private int perfil;
	
	@ManyToOne
	@JoinColumn(name="co_funcao")
	private Funcao funcao;

	@Column(name = "nu_telefone", length = 11)
	private String telefone;

	public Usuario(String cpf, String nome, String email, String situacao, int perfil, Funcao funcao,
			String telefone) {
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.situacao = situacao;
		this.perfil = perfil;
		this.funcao = funcao;
		this.telefone = telefone;
	}

	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
	public int getPerfil() {
		return perfil;
	}

	public void setPerfil(int perfil) {
		this.perfil = perfil;
	}

	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
