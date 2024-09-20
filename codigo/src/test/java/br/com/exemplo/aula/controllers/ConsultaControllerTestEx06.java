package br.com.exemplo.aula.controllers;

import br.com.exemplo.aula.repositories.ConsultaRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ConsultaControllerTestEx06 {

    @Autowired
    MockMvc mvc;

    @MockBean
    ConsultaRepository consultaRepository;

    @Test
    void buscarConsultaPorID() throws Exception {
        mvc.perform(get("/consultas/1")
                        .header("Authorization","token fake")
                )
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.nome").value("texto"));

    }

    @Test
    void alterarConsultaPorId() throws Exception {
        mvc.perform(put("/consultas/1")
                        .header("Authorization","token fake")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                             {
                                "idNutricionista": 1,
                                "idPaciente": 1,
                                "data": "10/10/2010",
                                "observacoes": "texto"
                               
                             }"""))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.nome").value("texto"));

    }

}