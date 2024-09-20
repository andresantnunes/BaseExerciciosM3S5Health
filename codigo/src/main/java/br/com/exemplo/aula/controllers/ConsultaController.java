package br.com.exemplo.aula.controllers;

import br.com.exemplo.aula.services.ConsultaService;
import br.com.exemplo.aula.controllers.dto.ConsultaRequestDTO;
import br.com.exemplo.aula.controllers.dto.ConsultaResponseDTO;
import br.com.exemplo.aula.controllers.dto.ConsultaResponseListDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping()
    public ConsultaResponseDTO salvarConsulta(@RequestBody ConsultaRequestDTO request) {
        return consultaService.salvarConsulta(request);
    }

    @GetMapping()
    public List<ConsultaResponseListDTO> listarConsultas() {
        var consultas = consultaService.listarConsultas();
        if (consultas.isEmpty()){
            return null;
        } else {
            return consultas;
        }

    }

    @GetMapping("/{id}")
    public ConsultaResponseDTO buscarConsultaPorId(
            @PathVariable("id") Long id
    ) {
        var consulta = consultaService.buscarConsulta(id);
        return consulta;
    }

    @PutMapping("/{id}")
    public ConsultaResponseDTO atualizarConsultaPorId(
            @PathVariable("id") Long id,
            @RequestBody ConsultaRequestDTO consultaRequest
    ) {
        var consulta = consultaService.atualizarConsulta(id, consultaRequest);
        return consulta;
    }
    
}
