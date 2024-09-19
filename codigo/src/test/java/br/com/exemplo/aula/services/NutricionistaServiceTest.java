package br.com.exemplo.aula.services;

import br.com.exemplo.aula.controllers.dto.NutricionistaRequestDTO;
import br.com.exemplo.aula.entities.Nutricionista;
import br.com.exemplo.aula.repositories.NutricionistaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
@ActiveProfiles("Test")
class NutricionistaServiceTest {

    @Autowired
    NutricionistaService nutricionistaService;

    @MockBean
    NutricionistaRepository repository;

    @Test
    void listarNutricionistas() {

        var resultado =nutricionistaService.listarNutricionistas();

        assertNotNull(resultado);
    }

    @Test
    void salvarNutricionista() {

        NutricionistaRequestDTO nutricionistaRequestDTO =
                new NutricionistaRequestDTO("tx",
                        "tx",
                        1,
                        1L,
                        "tx",
                        "tx");

        Nutricionista nutricionista = new Nutricionista(1L,
                "tx",
                "tx",
                1,
                "1",
                "tx");

        when(repository.save(any())).thenReturn(nutricionista);

        var resultado =nutricionistaService.salvarNutricionista(nutricionistaRequestDTO);

        assertNotNull(resultado);
        assertEquals(nutricionistaRequestDTO.getNome(), resultado.getNome());

    }
}