package br.com.exemplo.aula.services;

import br.com.exemplo.aula.controllers.dto.PacienteRequestDTO;
import br.com.exemplo.aula.controllers.dto.PacienteResponseDTO;
import br.com.exemplo.aula.entities.Paciente;
import br.com.exemplo.aula.mapper.PacienteMapper;
import br.com.exemplo.aula.repositories.PacienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // permite a execução do mockito
class PacienteServiceTest {

    @Mock // classe que é dependencia do Service
    PacienteRepository pacienteRepository;

    @InjectMocks //todos os mocks criados serão usados nessa classe
    PacienteService pacienteService;

    // Não é obrigatório para os exercicios
    @Spy  // Traz o comportamento completo do PacienteMapper para o PacienteService
            PacienteMapper pacienteMapper = Mappers.getMapper(PacienteMapper.class);

    Paciente paciente;

    @BeforeEach
    public void setup() {
        paciente = new Paciente(
                1L,
                "nome",
                LocalDate.now(),
                "111.111.111-11",
                "99999999",
                "email@email"
        );
    }

    @Test
    @DisplayName("Busca paciente e retorna valor para um paciente")
    void buscarPaciente() {

        new HashSet<String>(Set.of("a", "b"));

        // quando eu executar o método x, então retorno a resposta y
        // Given
        when(pacienteRepository.findById(anyLong())).thenReturn(Optional.ofNullable(paciente));

        // When
        PacienteResponseDTO resultado = pacienteService.buscarPaciente(1L);

        // Then
        assertNotNull(resultado);
        assertEquals(paciente.getId(), resultado.getId());

        // valida se o when foi executado pelo menos uma vez
        verify(pacienteRepository).findById(anyLong());
//        verify(pacienteRepository, times(2)).findById(anyLong());

    }

    // Não é obrigatório para os exercicios
    @Test
    void buscarPacienteThrow() {

        assertThrows(
                RuntimeException.class,
                () -> pacienteService.buscarPaciente(0L)
        );
    }

    @Test
    void removerPaciente() {
        doNothing().when(pacienteRepository).deleteById(anyLong());

        assertDoesNotThrow(
                () -> pacienteService.removerPaciente(1L)
        );

        verify(pacienteRepository).deleteById(anyLong());
    }

    // EX 02
    @Test
    void listarPacientes() {

        List<Paciente> pacientes = new ArrayList<>();
        pacientes.add(paciente);

        when(pacienteRepository.findAll()).thenReturn(pacientes);

        // When
        var resultado = pacienteService.listarPacientes();

        // Then
        assertNotNull(resultado);
        assertEquals(pacientes.get(0).getId(), resultado.get(0).getId());

        verify(pacienteRepository).findAll();
    }

    @Test
    void salvarPaciente() {

        PacienteRequestDTO pacienteRequestDTO = new PacienteRequestDTO(
                paciente.getNome(),
                paciente.getDataNascimento(),
                paciente.getCpf(),
                paciente.getTelefone(),
                paciente.getEmail(),
                1L
        );

        when(pacienteRepository.save(any())).thenReturn(paciente);

        // When
        var resultado = pacienteService.salvarPaciente(pacienteRequestDTO);

        // Then
        assertNotNull(resultado);
        assertEquals(pacienteRequestDTO.getNome(), resultado.getNome());

        verify(pacienteRepository).save(any());
    }

    @Test
    void atualizarPaciente() {
        PacienteRequestDTO pacienteRequestDTO = new PacienteRequestDTO(
                paciente.getNome(),
                paciente.getDataNascimento(),
                paciente.getCpf(),
                paciente.getTelefone(),
                paciente.getEmail(),
                1L
        );

        when(pacienteRepository.findById(anyLong())).thenReturn(Optional.ofNullable(paciente));
        when(pacienteRepository.save(any(Paciente.class))).thenReturn(paciente);

        // When
        var resultado = pacienteService.atualizarPaciente(1L, pacienteRequestDTO);

        // Then
        assertNotNull(resultado);
        assertEquals(pacienteRequestDTO.getNome(), resultado.getNome());

        verify(pacienteRepository, times(1)).findById(anyLong());
        verify(pacienteRepository).save(any());
    }
}