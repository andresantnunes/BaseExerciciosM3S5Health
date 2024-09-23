package br.com.exemplo.aula.controllers;

import br.com.exemplo.aula.entities.Consulta;
import br.com.exemplo.aula.entities.Nutricionista;
import br.com.exemplo.aula.entities.Paciente;
import br.com.exemplo.aula.repositories.PacienteRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//EX 07
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("Test")
class ConsultaControllerTest {

    @Autowired
    MockMvc mvc;


    Consulta consulta;

    @BeforeEach
    void setup() throws Exception {
        Nutricionista nutricionista = new Nutricionista();
        nutricionista.setId(1L);

        Paciente paciente = new Paciente();
        paciente.setId(1L);

        consulta = new Consulta(
                1L,
                nutricionista,
                paciente,
                LocalDate.of(2010, 10, 10),
                "texto"
        );
    }


    @Test
    void salvarConsulta() throws Exception {

        mvc.perform(post("/consultas")
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
                .andExpect(jsonPath("$.observacoes").value(consulta.getObservacoes()));
    }

    @Test
    void listarConsultas() throws Exception {

        mvc.perform(post("/pacientes")
                        .header("Authorization", "token fake")
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

        mvc.perform(post("/nutricionistas")
                        .header("Authorization", "token fake")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "nome": "texto",
                                    "matricula": "texto",
                                    "tempoExperiencia": 1,
                                    "idEndereco": 1,
                                    "crn": "texto",
                                    "especialidade": "texto"
                                }""")
                )
                .andExpect(status().isOk());


        mvc.perform(post("/consultas")
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
                .andExpect(jsonPath("$.observacoes").value(consulta.getObservacoes()));

        mvc.perform(get("/consultas")
                        .header("Authorization", "token fake")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void buscarConsultaPorId() throws Exception {
        mvc.perform(post("/consultas")
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
                .andExpect(jsonPath("$.observacoes").value(consulta.getObservacoes()));

        mvc.perform(get("/consultas/1")
                        .header("Authorization", "token fake")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.observacoes").value(consulta.getObservacoes()));
    }

    @Test
    void atualizarConsultaPorId() throws Exception {
        mvc.perform(post("/consultas")
                        .header("Authorization", "token fake")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                   "idNutricionista": 1,
                                   "idPaciente": 1,
                                   "data": "10/10/2011",
                                   "observacoes": "texto"
                                  
                                }"""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.observacoes").value(consulta.getObservacoes()));

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
                .andExpect(jsonPath("$.observacoes").value(consulta.getObservacoes()));
    }
}