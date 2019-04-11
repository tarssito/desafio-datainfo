package br.com.tarssito.ws;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.tarssito.dto.UsuarioDTO;
import br.com.tarssito.model.Usuario;
import br.com.tarssito.services.UsuarioService;

@Named
@Path("usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

	@Inject
	private UsuarioService service;

	@GET
	public Response findAll() {
		List<Usuario> usuarios = service.findAll();
		List<UsuarioDTO> usuarioDTO = usuarios.stream().map(obj -> new UsuarioDTO(obj))
				.collect(Collectors.toList());
		GenericEntity<List<UsuarioDTO>> entity = new GenericEntity<List<UsuarioDTO>>(usuarioDTO) {
		};
		return Response.status(Status.OK).entity(entity).build();
	}

	@GET
	@Path("/{id}")
	public Response findById(@PathParam("id") Long id) {
		Usuario usuario = service.findById(id);
		UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
		GenericEntity<UsuarioDTO> entity = new GenericEntity<UsuarioDTO>(usuarioDTO) {
		};
		return Response.status(Status.OK).entity(entity).build();
	}

	@POST
	public Response insert(UsuarioDTO usuarioDTO) {
		try {
			Usuario usuario = service.fromInsertDTO(usuarioDTO);
			service.save(usuario);
			return Response.status(Status.CREATED).build();
		} catch (ConstraintViolationException cvex) {
			final Set<String> erros = cvex.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
					.collect(Collectors.toSet());
			return Response.status(Response.Status.BAD_REQUEST).entity(erros).build();
		} catch (Exception ex) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Instabilidade no serviço").build();
		}
	}

	@PUT
	@Path("/{id}")
	public Response update(UsuarioDTO usuarioDTO, @PathParam("id") Long id) {
		Usuario usuario = service.fromUpdateDTO(usuarioDTO, id);
		service.save(usuario);
		return Response.ok().build();
	}

	@DELETE
	@Path("/{id}")
	public Response update(@PathParam("id") Long id) {
		Usuario usuario = service.findById(id);
		service.remove(usuario);
		return Response.ok().build();
	}
}
