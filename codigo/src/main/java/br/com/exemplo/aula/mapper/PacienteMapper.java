package br.com.exemplo.aula.mapper;

import br.com.exemplo.aula.controllers.dto.PacienteResponseDTO;
import br.com.exemplo.aula.entities.Paciente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PacienteMapper {

    PacienteResponseDTO toResponse(Paciente entity);
}
