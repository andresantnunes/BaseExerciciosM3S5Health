package br.com.exemplo.aula.controllers;

import br.com.exemplo.aula.entities.Consulta;
import br.com.exemplo.aula.entities.Nutricionista;
import br.com.exemplo.aula.entities.Paciente;
import br.com.exemplo.aula.repositories.ConsultaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


// EX 06
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("Test")
class ConsultaControllerTestEx06 {

    @Autowired
    MockMvc mvc;

    @MockBean
    ConsultaRepository consultaRepository;

    Consulta consulta;

    @BeforeEach
    void setup() {
        consulta = new Consulta(
                1L,
                new Nutricionista(),
                new Paciente(),
                LocalDate.now(),
                "observacoes"
        );
    }

    @Test
    void buscarConsultaPorID() throws Exception {

        when(consultaRepository.findById(anyLong())).thenReturn(Optional.of(consulta));

        mvc.perform(get("/consultas/1")
                        .header("Authorization", "token fake")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(consulta.getId()));

        verify(consultaRepository).findById(anyLong());
    }

    @Test
    void alterarConsultaPorId() throws Exception {

        when(consultaRepository.findById(anyLong())).thenReturn(Optional.of(consulta));
        when(consultaRepository.save(any())).thenReturn(consulta);

        mvc.perform(put("/consultas/1")
                        .header("Authorization", "token fake")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                   "idNutricionista": 1,
                                   "idPaciente": 1,
                                   "data": "10/10/2010",
                                   "observacoes": "texto"
                                  
                                }"""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(consulta.getId()));

        ;
        verify(consultaRepository).findById(anyLong());
        verify(consultaRepository).save(any());

    }

}