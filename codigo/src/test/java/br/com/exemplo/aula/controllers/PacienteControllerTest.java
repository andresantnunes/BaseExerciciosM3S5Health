package br.com.exemplo.aula.controllers;

import br.com.exemplo.aula.controllers.dto.PacienteRequestDTO;
import br.com.exemplo.aula.controllers.dto.PacienteResponseDTO;
import br.com.exemplo.aula.services.PacienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// EX 05
@WebMvcTest(controllers = PacienteController.class)
@AutoConfigureMockMvc
@ActiveProfiles("Test")
class PacienteControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    PacienteService pacienteService;

    @Test
    void listarPacientes() throws Exception {

        PacienteResponseDTO pacienteResponseDTO = new PacienteResponseDTO(
                1L,
                "Nome",
                LocalDate.now(),
                "CPF",
                "1111111",
                "mail@main"
        );

        when(pacienteService.listarPacientes()).thenReturn(List.of(pacienteResponseDTO));

        mvc.perform(get("/pacientes"))
                .andExpect(status().isOk())
                .andExpect(
                        jsonPath("$[0].nome").value(pacienteResponseDTO.getNome())
                )
        ;

        verify(pacienteService).listarPacientes();
    }

    @Test
    void adicionarPaciente() throws Exception {
        mvc.perform(post("/pacientes")
                        .header("Authorization","token fake")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                             {
                                 "nome": "texto",
                                 "dataNascimento": "10/10/1010",
                                 "cpf": "texto",
                                 "telefone": "texto",
                                 "email": "texto",
                                 "idEndereco": 1
                             }"""))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("texto"));

    }

    @Test
    void search() {
    }

    @Test
    void remove() {
    }

    @Test
    void update() {
    }
}