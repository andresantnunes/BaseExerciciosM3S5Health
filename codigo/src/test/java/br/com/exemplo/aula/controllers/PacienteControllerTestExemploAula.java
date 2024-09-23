package br.com.exemplo.aula.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("Test")

// Exemplo de teste de Integração
class PacienteControllerTestExemploAula {

    @Autowired
    MockMvc mvc;

    @Test
    void listarPacientes() throws Exception {
        mvc.perform(get("/pacientes"))
                .andExpect(status().isOk())
        ;
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
}