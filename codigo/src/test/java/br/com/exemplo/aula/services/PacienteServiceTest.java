package br.com.exemplo.aula.services;

import br.com.exemplo.aula.controllers.dto.PacienteResponseDTO;
import br.com.exemplo.aula.entities.Paciente;
import br.com.exemplo.aula.repositories.PacienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // permite a execução do mockito
class PacienteServiceTest {

    @Mock // classe que é dependencia do Service
    PacienteRepository pacienteRepository;

    @InjectMocks //todos os mocks criados serão usados nessa classe
    PacienteService pacienteService;
    Paciente paciente;
    @BeforeEach
    public void setup(){
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
        // any() ->  qualquer objeto
        // anyLong() -> qualquer Long
        // anyString() -> qualquer String
        // anyInt() -> qualquer Int

        // quando eu executar o método x, então retorno a resposta y
        when(pacienteRepository.findById(anyLong())).thenReturn(Optional.ofNullable(paciente));

        PacienteResponseDTO resultado = pacienteService.buscarPaciente(1L);

        assertNotNull(resultado);
        assertEquals(paciente.getId(), resultado.getId());

        // valida se o when foi executado pelo menos uma vez
        verify(pacienteRepository).findById(anyLong());
//        verify(pacienteRepository, times(2)).findById(anyLong());

    }

    @Test
    @DisplayName("Busca paciente e retorna valor nulo")
    void buscarPacienteErro() {
        PacienteResponseDTO resultado = pacienteService.buscarPaciente(1L);

        assertNull(resultado);
    }
}