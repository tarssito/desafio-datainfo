package br.com.tarssito.services;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.TypedQuery;

import br.com.tarssito.dto.UsuarioDTO;
import br.com.tarssito.model.Funcao;
import br.com.tarssito.model.Usuario;

@SuppressWarnings("serial")
@Named
public class UsuarioService extends AbstractService<Usuario, Long>{

	@Inject
	private FuncaoService funcaoService;
	
	public Usuario fromDTO(UsuarioDTO usuarioDTO, Long id) {
		Funcao funcao = funcaoService.findById(usuarioDTO.getCo_funcao());
		return new Usuario(id, usuarioDTO.getNu_cpf(), usuarioDTO.getNo_usuario(), usuarioDTO.getDe_email(),
				usuarioDTO.getIc_situacao(), usuarioDTO.getIc_perfil_acesso(), funcao, usuarioDTO.getNu_telefone());
	}
	
	public Usuario findByCpf(String cpf) {
		TypedQuery<Usuario> createQuery = this.getEntityManager()
				.createQuery("select u from Usuario u where u.cpf = :cpf" , Usuario.class);
		createQuery.setParameter(":cpf", cpf);
		return createQuery.getSingleResult();
	}
	
}
