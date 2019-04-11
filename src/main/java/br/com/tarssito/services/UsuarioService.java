package br.com.tarssito.services;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.tarssito.dto.UsuarioDTO;
import br.com.tarssito.model.Funcao;
import br.com.tarssito.model.Usuario;

@SuppressWarnings("serial")
@Named
public class UsuarioService extends AbstractService<Usuario, Long>{

	@Inject
	private FuncaoService funcaoService;
	
	public Usuario fromInsertDTO(UsuarioDTO usuarioDTO) {
		Funcao funcao = funcaoService.findById(usuarioDTO.getCo_funcao());
		return new Usuario(usuarioDTO.getNu_cpf(), usuarioDTO.getNo_usuario(), usuarioDTO.getDe_email(),
				usuarioDTO.getIc_situacao(), usuarioDTO.getIc_perfil_acesso(), funcao, usuarioDTO.getNu_telefone());
	}
	
	public Usuario fromUpdateDTO(UsuarioDTO usuarioDTO, Long id) {
		Usuario usuario = findById(id);
		usuario.setSituacao(usuarioDTO.getIc_situacao());
		return usuario;
	}
}
