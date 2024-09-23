package br.com.exemplo.aula.controllers;

import br.com.exemplo.aula.services.NutricionistaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// Exemplo
@WebMvcTest(controllers = NutricionistaController.class)
    // realiza os testes apenas dos compoonentes de Controller
@AutoConfigureMockMvc
class NutricionistaControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    NutricionistaService nutricionistaService;

    @Test
    void listarnutricionistas() throws Exception {

        mvc.perform(get("/nutricionistas")
                        .header("Authorization","token fake")
                )
                .andExpect(status().isOk());

    }

    @Test
    void postnutricionistas() throws Exception {

        mvc.perform(
                post("/nutricionistas")
                    .header("Authorization","token fake")
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

    }
}