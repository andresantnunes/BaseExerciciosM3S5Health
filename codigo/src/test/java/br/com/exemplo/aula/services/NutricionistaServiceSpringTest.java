package br.com.exemplo.aula.services;

import br.com.exemplo.aula.controllers.dto.NutricionistaRequestDTO;
import br.com.exemplo.aula.entities.Nutricionista;
import br.com.exemplo.aula.repositories.NutricionistaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


// EX 04
@SpringBootTest
@ActiveProfiles("Test")
class NutricionistaServiceSpringTest {

    @Autowired
    NutricionistaService nutricionistaService;

    @MockBean
    NutricionistaRepository repository;

    Nutricionista nutricionista;

    @BeforeEach
    void setup(){
        nutricionista = new Nutricionista(1L,
            "tx",
            "tx",
            1,
            "1",
            "tx");
    }

    @Test
    void listarNutricionistas() {

        List<Nutricionista> nutricionistas = new ArrayList<>();
        nutricionistas.add(nutricionista);

        when(repository.findAll()).thenReturn(nutricionistas);

        var resultado = nutricionistaService.listarNutricionistas();

        assertNotNull(resultado);
        assertEquals(nutricionistas.get(0).getNome(), resultado.get(0).getNome());
        verify(repository).findAll();
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



        when(repository.save(any())).thenReturn(nutricionista);

        var resultado =nutricionistaService.salvarNutricionista(nutricionistaRequestDTO);

        assertNotNull(resultado);
        assertEquals(nutricionistaRequestDTO.getNome(), resultado.getNome());

    }
}